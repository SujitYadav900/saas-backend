package com.example.maxcrm.MaxCrm.Service;

import com.example.maxcrm.MaxCrm.Dao.ReportAllLeadDao;

import java.util.List;

public interface ReportAllLeadService {

    List<ReportAllLeadDao>  getReport(int flag,String searchvalue, String clientTypeValue,String startdate, String enndate, String userlist,int dateTypeFlag) throws Exception;
}
