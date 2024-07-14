package com.example.maxcrm.MaxCrm.ServiceImpl;

import com.example.maxcrm.MaxCrm.Dao.LeadMasterDao;
import com.example.maxcrm.MaxCrm.Dao.LogEventDao;
import com.example.maxcrm.MaxCrm.OkHttp.OkHttpSingleTon;
import com.example.maxcrm.MaxCrm.Service.ClickToCallService;
import com.example.maxcrm.MaxCrm.Service.LeadMasterService;
import com.example.maxcrm.MaxCrm.Service.LogEventService;
import com.example.maxcrm.MaxCrm.Utility.UtilityClass;
import okhttp3.Request;
import okhttp3.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@Service
public class ClickToServiceImpl implements ClickToCallService {
    @Autowired
    LogEventService logEventService;
    @Autowired
    LeadMasterService leadMasterService;


    Logger logger = LoggerFactory.getLogger(ClickToServiceImpl.class);
    @Override
    public void makeACall(LogEventDao logEventDao, String phonenumber, String toPhonenumber) throws Exception {
        String c2curl= UtilityClass.propertyService.findProperty("IVR","ClickToCallUrl");
        String username=UtilityClass.propertyService.findProperty("IVR","IVRUSERNAME");
        String password=UtilityClass.propertyService.findProperty("IVR","IVERPASSWORD");
        LeadMasterDao leadMasterDao= leadMasterService.findById(logEventDao.getLeadId());
        toPhonenumber=leadMasterDao.getPhonenumber();
        c2curl=c2curl.replace("clickToCallUsername",username);
        c2curl=c2curl.replace("clickToCallPassword",password);
        c2curl=c2curl.replace("fromnumber",phonenumber);
        c2curl=c2curl.replace("tonumber",toPhonenumber);
        logger.info("Final Url Is {}",c2curl);
        makeAGetRequest(c2curl); //api call pass string parameter
        logEventService.insert(logEventDao);
        if(UtilityClass.propertyService.findProperty("Lead","UpdateLeadOnClickToCall").equalsIgnoreCase("1"))
        {
            leadMasterService.updateLastUpdateLead(null,logEventDao.getCreateBy(),UtilityClass.dateFilterDate(),logEventDao.getCreateAt(),logEventDao.getLeadId());

        }

    }
//    private  String makeAGetRequest(String urlToRead) throws Exception {
//        StringBuilder result = new StringBuilder();
//        URL url = new URL(urlToRead);
//        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//        conn.setRequestMethod("GET");
//        BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
//        String line;
//        while ((line = rd.readLine()) != null) {
//            result.append(line);
//        }
//        rd.close();
//        String response=result.toString();
//        logger.info("Api Reponse Click To Call "+response);
//        return response;
//    }

        private  String makeAGetRequest(String urlToRead) throws Exception {
            Request request = new Request.Builder()
                    .url(urlToRead)
                    .get()
                    .addHeader("cookie", "__cfduid=d5ae8f4b4cdeb8a5215f06fedd31f21d51592545247")
                    .build();

            Response response = OkHttpSingleTon.client.newCall(request).execute();
            String resString= response.body().string();
            logger.info("Api Reponse Click To Call " + resString);
            return resString;
    }

}
