package com.example.maxcrm.MaxCrm.Service;

import com.example.maxcrm.MaxCrm.Dao.LoginHistoryDao;

import java.sql.SQLException;
import java.util.List;

public interface LoginHistoryService {
    List<LoginHistoryDao> getByUserId(int userId);
    LoginHistoryDao getByUserIdSingle(int userId) throws SQLException;
    LoginHistoryDao insert(LoginHistoryDao loginHistoryDao);
    List<LoginHistoryDao> lastTenHistory(int userid) throws SQLException;

}
