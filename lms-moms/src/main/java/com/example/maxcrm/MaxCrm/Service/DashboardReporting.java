package com.example.maxcrm.MaxCrm.Service;

import com.example.maxcrm.MaxCrm.Dao.DashboardDao;

import java.util.List;

public interface DashboardReporting {
    List<DashboardDao> reportDashboard(int startdate,String type,int enddate,int user) throws Exception;
}
