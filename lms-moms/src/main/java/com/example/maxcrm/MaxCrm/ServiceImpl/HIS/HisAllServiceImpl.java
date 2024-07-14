package com.example.maxcrm.MaxCrm.ServiceImpl.HIS;

import com.example.maxcrm.MaxCrm.Dao.HIS.AppointmentDao;
import com.example.maxcrm.MaxCrm.Dao.HIS.HisAppointment;
import com.example.maxcrm.MaxCrm.Dao.HIS.TokenGeneration;
import com.example.maxcrm.MaxCrm.OkHttp.OkHttpSingleTon;
import com.example.maxcrm.MaxCrm.Service.HIS.HisAllServices;
import com.example.maxcrm.MaxCrm.Utility.UtilityClass;

import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class HisAllServiceImpl implements HisAllServices {
    Logger logger = LoggerFactory.getLogger(HisAllServiceImpl.class);
    private static String token = "0";
    private static String lastTimeTokenGenerated = "0";
     @Qualifier("dataSource")
    @Autowired
    DataSource dataSource;


    public String getToken() {
        long timediference = 0;
        try {
            timediference = UtilityClass.getDateDifferenceInMinutes(lastTimeTokenGenerated, UtilityClass.getDateFull());
        } catch (Exception ew) {
            timediference = 5500;
        }
        if (token.equalsIgnoreCase("0") || timediference > 360) {
            logger.info("Generating New Token");
            try {
                generateToken();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return token;
    }

    @Override
    public void updateStatus(HashMap<String, AppointmentDao> hashMap, String selectquery) throws SQLException {
        Connection con = null;

        try {
            con = dataSource.getConnection();

            PreparedStatement stmt = con.prepareStatement(selectquery);
            ResultSet rs = stmt.executeQuery();
            String phonenumber;
            HashMap<String, AppointmentDao> newhashmap = new HashMap<>();
            long id;
            String status;
            String stage;
            AppointmentDao appointmentDao;
            while (rs.next()) {
                id = rs.getLong(1);
                stage = rs.getString(2);
                status = rs.getString(3);
                phonenumber = rs.getString(4);
                appointmentDao = hashMap.get(phonenumber);
                appointmentDao.setLeadDeatails(status, stage, id);

                newhashmap.put(phonenumber, appointmentDao);

            }
            logger.info("After Updating Hashmap Values Are {}", newhashmap);
            con.close();
            con = dataSource.getConnection();
            updateQueryLeadStatus(con, newhashmap);
            con.close();


        } catch (SQLException sql) {
            sql.printStackTrace();

            logger.error("Error Occured in Generating Query {}", sql);
        } finally {


        }


    }

    @Override
    public void generateTokenFirstTime() {
        try {
            generateToken();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void updateQueryLeadStatus(Connection connection, HashMap<String, AppointmentDao> hashMap) throws SQLException {

        PreparedStatement stmt = connection.prepareStatement("update Tbl_LeadMaster set leadStage=? , leadStatus=? where id=?;");
        AppointmentDao appointmentDao;
        String readabledate = UtilityClass.getDateRedable();
        boolean triggernotifation = false;
        long datefilter = UtilityClass.fullDateLong();
        for (String key : hashMap.keySet()) {
            appointmentDao = hashMap.get(key);
            appointmentDao = convertToFinalObject(appointmentDao);
            stmt.setString(1, appointmentDao.getLeadStage());
            stmt.setString(2, appointmentDao.getLeadStatus());
            stmt.setLong(3, appointmentDao.getId());
            stmt.addBatch();


        }
        System.out.println(stmt);
        stmt.executeBatch();


    }

    private AppointmentDao convertToFinalObject(AppointmentDao appointmentDao) {
        if (appointmentDao.getLeadStage().equalsIgnoreCase("Conversation") || appointmentDao.getLeadStage().equalsIgnoreCase("Appointment(Pending)")) {
            appointmentDao.setNotification(false);
            appointmentDao.setConvertLead(false);
            switch (appointmentDao.getStatus()) {
                case "Unconfirmed":
                    appointmentDao.setLeadStage("Appointment(Pending)");
                    appointmentDao.setLeadStatus("Unconfirmed");
                    break;


                case "Cancelled":
                    appointmentDao.setLeadStage("Cancelled(Appointment)");
                    appointmentDao.setLeadStatus("Cancelled");

                    break;
                case "Confirmed":
                    appointmentDao.setLeadStage("Appointment(Completed)");
                    appointmentDao.setLeadStatus("Pending");

                    break;
                case "Rescheduled":
                    appointmentDao.setLeadStage("Appointment(Pending)");
                    appointmentDao.setLeadStatus("Rescheduled");

                    break;


                case "No Show":
                    appointmentDao.setLeadStage("Not Visited(Appointment)");
                    appointmentDao.setLeadStatus("Not Visited");

                    break;

            }
        }
        if (appointmentDao.getLeadStage().equalsIgnoreCase("Procedure Suggested") || appointmentDao.getLeadStage().equalsIgnoreCase("Procedure Booked")) {
            appointmentDao.setNotification(false);
            appointmentDao.setConvertLead(false);
            switch (appointmentDao.getStatus()) {
                case "Unconfirmed":
                    appointmentDao.setLeadStage("Procedure Booked");
                    appointmentDao.setLeadStatus("Unconfirmed");

                    break;


                case "Cancelled":
                    appointmentDao.setLeadStage("Cancelled(Procedure)");
                    appointmentDao.setLeadStatus("Cancelled");

                    break;
                case "Confirmed":
                    appointmentDao.setLeadStage("Procedure Completed");
                    appointmentDao.setLeadStatus("Procedure Completed");

                    break;
                case "Rescheduled":
                    appointmentDao.setLeadStage("Procedure Booked");
                    appointmentDao.setLeadStatus("Rescheduled");
                    break;


                case "No Show":
                    appointmentDao.setLeadStage("Not Visited(Procedure)");
                    appointmentDao.setLeadStatus("Not Visited");

                    break;

            }

        }


        return appointmentDao;


    }


    private void generateToken() throws Exception {
        logger.info("Generating Token!!");

        MediaType mediaType = MediaType.parse("application/json,text/plain");
        String username = UtilityClass.propertyService.findProperty("Application", "HISUSERNAME");
        String password = UtilityClass.propertyService.findProperty("Application", "HISPASSWORD");
        String url = UtilityClass.propertyService.findProperty("Application", "HISAUTHURL");
        RequestBody body = RequestBody.create(mediaType, "{\"email\":\"" + username + "\",\"password\":\"" + password + "\"}");

        Request request = new Request.Builder()
                .url(url)
                .method("POST", body)
                .addHeader("authority", "directory.staging.thedocweb.com")
                .addHeader("accept", "application/json, text/plain, */*")
                .addHeader("sec-fetch-dest", "empty")
                .addHeader("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3987.149 Safari/537.36")
                .addHeader("content-type", "application/json")
                .addHeader("origin", "https://ui-offline.staging.thedocweb.com")
                .addHeader("sec-fetch-site", "same-site")
                .addHeader("sec-fetch-mode", "cors")
                .addHeader("referer", "https://ui-offline.staging.thedocweb.com/home/")
                .addHeader("accept-language", "en-US,en;q=0.9")
                .addHeader("Content-Type", "text/plain")
                .build();
        String resjson = OkHttpSingleTon.client.newCall(request).execute().body().string();
        logger.info("After Generating Token Response Is {}", resjson);
        TokenGeneration tokenGeneration = new TokenGeneration().convertStringJsonToObject(resjson);
        try {
            if (tokenGeneration.getData().getMessage().equalsIgnoreCase("Invalid credentials.")) {
                logger.error("Error Generating Token as Credentials are invalid!!");
                throw new Exception("Invalid Credentials");
            }
        } catch (Exception ew) {

        }
        lastTimeTokenGenerated = UtilityClass.getDateFull();
        token = tokenGeneration.getData().getToken();
        logger.info("Generated New Token {}", token);


    }

    @Override
    public HisAppointment getAllConfirmedAppointment(String token, int pagenumber, int items) throws IOException {


        String urlHis=UtilityClass.propertyService.findProperty("Application","HisApiCallUrl");


        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType, "{\"appointment_id\":\"\"}");
        Request request = new Request.Builder()
                .url(urlHis+"get_appointment?page=" + pagenumber + "&items=" + items + "")
                .method("POST", body)
                .addHeader("Authorization", "Bearer " + token + "")
                .addHeader("Content-Type", "application/json")
                .build();
        String resjson = OkHttpSingleTon.client.newCall(request).execute().body().string();
        logger.info("Returning json  for pagenumber {} {}",pagenumber,resjson);
        HisAppointment hisAppointments = new HisAppointment().convertJsonToObject(resjson);


        return hisAppointments;
    }

    @Override
    public HisAppointment getAllUncomfiredAppointment(String token, int pagenumber, int items) throws IOException {

        String urlHis=UtilityClass.propertyService.findProperty("Application","HisApiCallUrl");

        Request request = new Request.Builder()
                .url(urlHis+"unconfirmed?page=" + pagenumber + "&items=" + items)
                .method("GET", null)
                .addHeader("Authorization", "Bearer " + token + "")
                .build();

        String resjson = OkHttpSingleTon.client.newCall(request).execute().body().string();
        logger.info("Returning json  for pagenumber {} {}",pagenumber,resjson);
        HisAppointment hisAppointments = new HisAppointment().convertJsonToObject(resjson);


        return hisAppointments;
    }
}
