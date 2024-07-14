package com.example.maxcrm.MaxCrm.Service;

import com.example.maxcrm.MaxCrm.Dao.ReportingDao;

import java.sql.SQLException;
import java.util.List;

public interface ReportingService {
    Iterable<ReportingDao> findByUserId(int userid);
    ReportingDao insert(ReportingDao dao);
    ReportingDao update(ReportingDao dao);
    void delete(long id);
    void deleteAndInsertByUserId(int userId, List<ReportingDao> al)throws SQLException;
    void updateUserIdReporting();
    List<ReportingDao> findByReportTo(int reportto);
}
