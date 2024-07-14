package com.example.maxcrm.MaxCrm.Service;

import com.example.maxcrm.MaxCrm.Dao.ReportAllTicketDao;

import java.util.List;

public interface ReportAllTicketService {

    List<ReportAllTicketDao>  getReport(int flag, String searchvalue, String startdate, String enndate, String userlist) throws Exception;
}
