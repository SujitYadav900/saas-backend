package com.example.maxcrm.MaxCrm.Service.HIS;

import com.example.maxcrm.MaxCrm.Dao.HIS.AppointmentDao;
import com.example.maxcrm.MaxCrm.Dao.HIS.HisAppointment;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

public interface HisAllServices {

    HisAppointment getAllConfirmedAppointment(String token,int pagenumber,int items) throws IOException;
    HisAppointment getAllUncomfiredAppointment(String token,int pagenumber,int items) throws IOException;
    String getToken();
    void updateStatus(HashMap<String, AppointmentDao> hashMap,String selectquery) throws SQLException;
    void generateTokenFirstTime();


}
