package com.example.maxcrm.MaxCrm.Service;

import com.example.maxcrm.MaxCrm.Dao.LogTaskDao;
import com.example.maxcrm.MaxCrm.Dao.MaskingDao;

import java.sql.SQLException;
import java.util.List;

public interface LogTaskService {
    LogTaskDao insert(LogTaskDao logTaskDao) throws SQLException;
    LogTaskDao update(LogTaskDao logTaskDao) throws SQLException;
    List<LogTaskDao> getAllUnreadTask(String userIdList) throws SQLException;
    Iterable<LogTaskDao> findAllByLeadId(long id);
    void delete(long id,String createby,long leadId);
    List<LogTaskDao> findAllForDashboard(String userlist, MaskingDao maskingDao);

    void downloadLogTask(List<LogTaskDao> logTaskList);
}
