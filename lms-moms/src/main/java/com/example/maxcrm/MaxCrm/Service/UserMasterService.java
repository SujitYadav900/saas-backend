package com.example.maxcrm.MaxCrm.Service;

import com.example.maxcrm.MaxCrm.Dao.MenuDynamicDatabaseDao;
import com.example.maxcrm.MaxCrm.Dao.UserMasterDao;

import java.util.List;

public interface UserMasterService {
    Iterable<UserMasterDao> findByDepartment(String department);
    Iterable<UserMasterDao> findByReportTo(String reportTo);
    List<UserMasterDao> findByReportList(String list); // this method will return all memebers of team
    Iterable<UserMasterDao> getAllUsers();
    Iterable<UserMasterDao> getAllActiveUsers();
    UserMasterDao insert(UserMasterDao userMasterDao) throws Exception;
    UserMasterDao update(UserMasterDao userMasterDao) throws Exception;
    UserMasterDao findByUsername(String username);
    List<MenuDynamicDatabaseDao> loadMenu(String dep) throws Exception;
    UserMasterDao findById(int id);
    UserMasterDao findUserForRecovery(String value);
    UserMasterDao getRandomUserDepartment(String department);
    void updateUser(String password,int userId);
    void sendNotificationUser(String templateId,UserMasterDao userMasterDao);
    String findListOfUserUpload(byte type,String typeValue);
    String findReportingUser(int userid);

}
