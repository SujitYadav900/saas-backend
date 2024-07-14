package com.example.maxcrm.MaxCrm.Service;

import com.example.maxcrm.MaxCrm.Dao.LeadReportCardDao;

import java.sql.SQLException;
import java.util.List;

public interface LeadReportCardService {

    List<LeadReportCardDao> getReport(String id, String startdate, String enddate,int dateTypeFlag) throws SQLException;
    List<LeadReportCardDao> getBusiness(String id, String startdate, String enddate) throws SQLException;
    List<LeadReportCardDao> getLeadStats(String startdate, String enddate,boolean isFilter,String clientType,String id) throws SQLException;
    List<LeadReportCardDao> getLeadStatsUser(String startdate, String enddate) throws SQLException;
    List<LeadReportCardDao> getLeadStatsDetailed(String startdate, String enddate,String flag,String dateflag,String clientTypeFlag) throws SQLException;
    List<LeadReportCardDao> getLeadStatsAppointmentDate(String startdate, String enddate) throws SQLException;
    List<LeadReportCardDao> getTransferStats(String startdate, String enddate,String username) throws SQLException;


}
