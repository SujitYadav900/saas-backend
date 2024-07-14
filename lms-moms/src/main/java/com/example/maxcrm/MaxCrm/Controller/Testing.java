package com.example.maxcrm.MaxCrm.Controller;

import com.example.maxcrm.MaxCrm.Dao.HIS.AppointmentDao;
import com.example.maxcrm.MaxCrm.Dao.HIS.HisAppointment;
import com.example.maxcrm.MaxCrm.Schdules.HisSchdules;
import com.example.maxcrm.MaxCrm.Service.HIS.HisAllServices;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/api/test")
public class Testing {

    Logger logger = LoggerFactory.getLogger(HisSchdules.class);
    @Autowired
    HisAllServices hisAllServices;
    @GetMapping("/reloadststatus")
    public int reloadStatus()
    {
        logger.info("Running HIS Schdules");
        HashMap<String, AppointmentDao> hashMap=new HashMap<>();
        StringBuilder selectquery=new StringBuilder();
        selectquery.append("SELECT Tbl_LeadMaster.id, Tbl_LeadMaster.leadStage, Tbl_LeadMaster.leadStatus,Tbl_LeadMaster.phonenumber FROM Tbl_LeadMaster where   FIND_IN_SET(phonenumber, '");

        boolean loopstatus=true;
        int pagenumber=1;

        List<AppointmentDao> appointmentDaoList;
        AppointmentDao appointmentDao;
        while (loopstatus)
        {
            HisAppointment alconfirmed;
            try {
                String token= hisAllServices.getToken();
                alconfirmed= hisAllServices.getAllConfirmedAppointment(token,pagenumber,50);
                if(alconfirmed.getMessage().equalsIgnoreCase("Successfully Fetched"))
                {
                    loopstatus=true;
                    appointmentDaoList=alconfirmed.getData().getAppointments();
                    for(int i=0;i<appointmentDaoList.size();i++)
                    {

                        appointmentDao=appointmentDaoList.get(i);
                        selectquery.append(appointmentDao.getPatient_phone());
                        selectquery.append(",");
                        hashMap.put(appointmentDao.getPatient_phone(),appointmentDao);
                    }
                    selectquery.append("')");
                    logger.info("After Running Cron Api Got Reponse {} and Select Query is {}",hashMap,selectquery);
                    try {
                        hisAllServices.updateStatus(hashMap, selectquery.toString());
                    }catch (SQLException ew)
                    {
                        logger.error("Failed To Update Status Error is {}",ew);
                    }
                }
                else
                {
                    logger.info("Confirmed Mamimum page loaded page number is {}",pagenumber);
                    loopstatus=false;
                }


            } catch (IOException e) {
                e.printStackTrace();
            }
            pagenumber++;

        }

        selectquery=new StringBuilder();
        selectquery.append("SELECT Tbl_LeadMaster.id, Tbl_LeadMaster.leadStage, Tbl_LeadMaster.leadStatus,Tbl_LeadMaster.phonenumber FROM Tbl_LeadMaster where   FIND_IN_SET(phonenumber, '");
        hashMap=new HashMap<>();
        loopstatus=true;
        pagenumber=1;
        while (loopstatus)
        {

            HisAppointment alnotconfirmed;
            try {
                String token= hisAllServices.getToken();
                alnotconfirmed= hisAllServices.getAllUncomfiredAppointment(token,pagenumber,50);
                if(alnotconfirmed.getMessage().equalsIgnoreCase("Successfully Fetched"))
                {
                    loopstatus=true;
                    appointmentDaoList=alnotconfirmed.getData().getUnconfirmed_appointments();
                    for(int i=0;i<appointmentDaoList.size();i++)
                    {
                        appointmentDao=appointmentDaoList.get(i);
                        selectquery.append(appointmentDao.getPatient_phone());
                        selectquery.append(",");
                        hashMap.put(appointmentDao.getPatient_phone(),appointmentDao);
                    }
                    selectquery.append("')");
                    logger.info("After Running Cron Api Got Reponse {} and Select Query is {}",hashMap,selectquery);
                    try {
                        hisAllServices.updateStatus(hashMap, selectquery.toString());
                    }catch (SQLException ew)
                    {
                        logger.error("Failed To Update Status Error is {}",ew);
                    }
                }
                else
                {
                    logger.info("UnConfirmed Mamimum page loaded page number is {}",pagenumber);
                    loopstatus=false;
                }


            } catch (IOException e) {
                e.printStackTrace();
            }
            pagenumber++;
        }


return 200;
    }
}
