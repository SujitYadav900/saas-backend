package com.example.maxcrm.MaxCrm.Service;

import com.example.maxcrm.MaxCrm.Dao.NotificationDao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface NotificationService {

    NotificationDao insertNotification(NotificationDao notificationDao, Connection connection) throws SQLException;
    NotificationDao insertSingleNotification(NotificationDao notificationDao) throws SQLException;
    NotificationDao updateNotification(NotificationDao notificationDao, Connection connection) throws SQLException;
    NotificationDao updateNotificationSnooze(NotificationDao notificationDao);

    void pushNotification() throws Exception;
    void delete(long id);
    void bulkInsertNotification(List<NotificationDao> al,Connection con) throws SQLException;
    void updateReadStatus(long id) throws SQLException;
    void snoozeNotification(long id,int minsToIncrease,String prevDate) throws SQLException;
    List<NotificationDao> getByUserId(int userId,int offset,int limit) throws SQLException;
    List<NotificationDao> getByUserIdAndInnerSubject(int userId,String innerSubject,int offset,int limit) throws SQLException;

}
