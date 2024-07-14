package com.example.maxcrm.MaxCrm.Service;

import com.example.maxcrm.MaxCrm.Dao.LogEventDao;

public interface ClickToCallService {
    void makeACall(LogEventDao logEventDao,String phonenumber,String toPhonenumber) throws Exception;

}
