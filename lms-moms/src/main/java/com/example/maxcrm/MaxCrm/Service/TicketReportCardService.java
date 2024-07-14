package com.example.maxcrm.MaxCrm.Service;

import com.example.maxcrm.MaxCrm.Dao.TicketReportCardDao;

import java.sql.SQLException;
import java.util.List;

public interface TicketReportCardService {

    List<TicketReportCardDao> getTicketReport(String id,String fromdate, String todate) throws SQLException;
}
