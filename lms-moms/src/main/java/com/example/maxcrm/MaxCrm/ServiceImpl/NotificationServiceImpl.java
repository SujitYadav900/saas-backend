package com.example.maxcrm.MaxCrm.ServiceImpl;

import com.example.maxcrm.MaxCrm.Dao.*;
import com.example.maxcrm.MaxCrm.Repo.NotificationRepo;
import com.example.maxcrm.MaxCrm.Service.*;
import com.example.maxcrm.MaxCrm.Utility.UtilityClass;
import com.example.maxcrm.MaxCrm.Dao.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class NotificationServiceImpl implements NotificationService {
    @Qualifier("dataSource")
    @Autowired
    DataSource dataSource;

    @Autowired
    NotificationRepo notificationRepo;
    Logger logger = LoggerFactory.getLogger(NotificationServiceImpl.class);
    @Autowired
    TextMessageService textMessageService;
    @Autowired
    TemplateDocumentService templateDocumentService;
    @Autowired
    UrlShortnerService urlShortnerService;


    @Autowired
    CounterService counterService;

    private void notificationSevicejson(int userId, String json) {
        logger.info("Publishing Notification {} ", json);



    }

    @Override
    public NotificationDao insertNotification(NotificationDao notificationDao, Connection con) throws SQLException {
        try {
            PreparedStatement stmt = con.prepareStatement("INSERT INTO `Tbl_Notification` (`id`, `assignTo`, `createAt`, `createBy`, `datetimingnotification`, `message`, `notificationTiming`, `readStatus`, `readTiming`, `redirectUrl`, `sendMessage`, `sendStatus`, `subject`,innerSubject,leadId) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?,?);");
            stmt.setLong(1, notificationDao.getId());
            stmt.setInt(2, notificationDao.getTo());
            stmt.setString(3, notificationDao.getCreateAt());
            stmt.setString(4, notificationDao.getCreateBy());
            stmt.setLong(5, notificationDao.getDatetiming());
            stmt.setString(6, notificationDao.getMessage());
            stmt.setString(7, notificationDao.getNotificationTiming());
            stmt.setBoolean(8, false);
            stmt.setString(9, "");
            stmt.setString(10, notificationDao.getRedirectUrl());
            stmt.setBoolean(11, notificationDao.isSendMessage());
            stmt.setBoolean(12, false);
            stmt.setString(13, notificationDao.getSubject());
            stmt.setString(14, notificationDao.getInnerSubject());
            stmt.setLong(15,notificationDao.getLeadId());
            stmt.executeUpdate();
            logger.info("Inserting Notication {}", notificationDao);
        } catch (SQLException sql) {
            throw sql;

        }
        return notificationDao;
    }

    @Override
    public NotificationDao insertSingleNotification(NotificationDao notificationDao) throws SQLException {
        Connection con = null;

        notificationDao.setId(counterService.getCounter(1, 1));
        try {
            con = dataSource.getConnection();
            insertNotification(notificationDao, con);
        } catch (SQLException sql) {
            throw sql;
        } finally {
            con.close();
        }
        return notificationDao;
    }


    @Override
    public NotificationDao updateNotification(NotificationDao notificationDao, Connection con) throws SQLException {
        try {
            PreparedStatement stmt = con.prepareStatement("delete from Tbl_Notification where id=?;");
            stmt.setLong(1, notificationDao.getId());
            stmt.executeUpdate();
            stmt = con.prepareStatement("INSERT INTO `Tbl_Notification` (`id`, `assignTo`, `createAt`, `createBy`, `datetimingnotification`, `message`, `notificationTiming`, `readStatus`, `readTiming`, `redirectUrl`, `sendMessage`, `sendStatus`, `subject`,innerSubject,leadId) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?,?);");
            stmt.setLong(1, notificationDao.getId());
            stmt.setInt(2, notificationDao.getTo());
            stmt.setString(3, notificationDao.getCreateAt());
            stmt.setString(4, notificationDao.getCreateBy());
            stmt.setLong(5, notificationDao.getDatetiming());
            stmt.setString(6, notificationDao.getMessage());
            stmt.setString(7, notificationDao.getNotificationTiming());
            stmt.setBoolean(8, false);
            stmt.setString(9, "");
            stmt.setString(10, notificationDao.getRedirectUrl());
            stmt.setBoolean(11, notificationDao.isSendMessage());
            stmt.setBoolean(12, false);
            stmt.setString(13, notificationDao.getSubject());
            stmt.setString(14, notificationDao.getInnerSubject());
            stmt.setLong(15,notificationDao.getLeadId());
            stmt.executeUpdate();
            logger.info("Updateing Notication {}", notificationDao);

        } catch (SQLException sql) {
            throw sql;
        }
        return notificationDao;
    }

    @Override
    public NotificationDao updateNotificationSnooze(NotificationDao notificationDao) {
        return notificationRepo.save(notificationDao);
    }

    @Override
    public void pushNotification() throws Exception {
        Connection con = null;
        try {
            con = dataSource.getConnection();
            long minDate= Long.parseLong(UtilityClass.dateFilterDate()+"0000");
            PreparedStatement stmt = con.prepareStatement("SELECT Tbl_Notification.id,Tbl_Notification.message,Tbl_UserMaster.mobile,   Tbl_Notification.sendMessage ,`Tbl_Notification`.`createBy`,`Tbl_Notification`.`innerSubject`,`Tbl_Notification`.`notificationTiming`,`Tbl_Notification`.`redirectUrl`,`Tbl_Notification`.`subject`,Tbl_Notification.assignTo,Tbl_Notification.leadId FROM Tbl_Notification inner join Tbl_UserMaster on Tbl_Notification.assignTo=Tbl_UserMaster.id WHERE Tbl_Notification.datetimingnotification <= ? and  Tbl_Notification.datetimingnotification > ?   AND sendStatus = 0");
            stmt.setLong(1, UtilityClass.fullDateLong());
            stmt.setLong(2,minDate);









            logger.info("Query {}", stmt);
            ResultSet rs = stmt.executeQuery();
            TextMessage textMessage = null;
            CampaignObjectDao campaignObjectDao;
            List<CampaignObjectDao> al = new ArrayList<>();
            StringBuilder sb = new StringBuilder();
            List<NotificationDao> alnotifi = new ArrayList<>();
            NotificationDao notificationDao = null;
            int sendnotificationcoun = 0;
            String mobile;
            String url;
            String message;

            long id;
            int totalnoti = 0;
            String siteUrl=UtilityClass.propertyService.findProperty("Application","siteUrl");
            while (rs.next()) {
                id = rs.getLong(1);
                message = rs.getString(2);
                url=siteUrl+rs.getString("redirectUrl");
                message=message +"";
                mobile = rs.getString(3);
                sb.append(id);
                sb.append(",");
                notificationDao = new NotificationDao();
                notificationDao.setId(id);
                notificationDao.setMessage(message);
                notificationDao.setSendMessage(rs.getBoolean(4));
                notificationDao.setCreateBy(rs.getString(5));
                notificationDao.setInnerSubject(rs.getString(6));
                notificationDao.setNotificationTiming(rs.getString(7));
                notificationDao.setRedirectUrl(rs.getString(8));
                notificationDao.setSubject(rs.getString(9));
                notificationDao.setAssignTo(rs.getInt(10));
                notificationDao.setLeadId(rs.getLong(11));
                alnotifi.add(notificationDao);
                totalnoti++;


                if (notificationDao.isSendMessage()) {
                    campaignObjectDao = new CampaignObjectDao();
                    campaignObjectDao.setPhonenumber(mobile);
                    campaignObjectDao.setMessage(message);
                    campaignObjectDao.setLeadId(notificationDao.getLeadId());
                    campaignObjectDao.setUrl(url);
                    al.add(campaignObjectDao);
                    sendnotificationcoun++;
                }


            }
            con.close();
            try {
                al = urlShortnerService.urlShortnerServer(al);
            }catch (Exception ew)
            {

            }
            if (sendnotificationcoun > 0) {
                String senderId = UtilityClass.propertyService.findProperty("Usermaster", "UserSenderId");
                logger.info("senderId >> {}",senderId);
                String notificationTemplate=UtilityClass.propertyService.findProperty("Usermaster", "NotificationTemplate");
                logger.info("notificationTemplate >> {} ",notificationTemplate);
                TemplateDocument templateDocument = templateDocumentService.findById(notificationTemplate);
                MessageCampaignDao messageCampaignDao = new MessageCampaignDao(templateDocument.getParams(), al,templateDocument.getTemplateIdHidden());
                textMessage = messageCampaignDao.convertToObject(senderId);
                textMessageService.sendTextMessage(textMessage);
            }
            if (totalnoti > 0) {
                try {
                    for (int i = 0; i < alnotifi.size(); i++) {
                        notificationDao = alnotifi.get(i);
                        String json = notificationDao.convertObjectToJson(notificationDao);
                        notificationSevicejson(notificationDao.getTo(), json);
                    }
                }catch (Exception ew)
                {
                    logger.error("Failed To Update To in real Time Notifications");
                }
                con = dataSource.getConnection();
                String idlist = sb.substring(0, sb.toString().lastIndexOf(","));
                stmt = con.prepareStatement("update Tbl_Notification set Tbl_Notification.sendStatus=? where id in(" + idlist + ")");
                stmt.setBoolean(1,true);
                stmt.executeUpdate();
                logger.info("Updating Notification Sent Status {}",stmt);
                logger.info("Notification Send Successfull {}", sendnotificationcoun);
            }

        } catch (SQLException sql) {
            logger.error("Error Occureded {}", sql);
            sql.printStackTrace();

        } finally {
            con.close();
        }

    }


    @Override
    public void delete(long id) {
        notificationRepo.deleteById(id);
    }

    @Override
    public void bulkInsertNotification(List<NotificationDao> al, Connection con) throws SQLException {

        PreparedStatement stmt = con.prepareStatement("INSERT INTO `Tbl_Notification` (`id`, `assignTo`, `createAt`, `createBy`, `datetimingnotification`, `message`, `notificationTiming`, `readStatus`, `readTiming`, `redirectUrl`, `sendMessage`, `sendStatus`, `subject`,innerSubject,leadId) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?,?);");

        NotificationDao notificationDao;
        for (int i = 0; i < al.size(); i++) {

            notificationDao = al.get(i);
            stmt.setLong(1, notificationDao.getId());
            stmt.setInt(2, notificationDao.getTo());
            stmt.setString(3, notificationDao.getCreateAt());
            stmt.setString(4, notificationDao.getCreateBy());
            stmt.setLong(5, notificationDao.getDatetiming());
            stmt.setString(6, notificationDao.getMessage());
            stmt.setString(7, notificationDao.getNotificationTiming());
            stmt.setBoolean(8, false);
            stmt.setString(9, "");
            stmt.setString(10, notificationDao.getRedirectUrl());
            stmt.setBoolean(11, notificationDao.isSendMessage());
            stmt.setBoolean(12, false);
            stmt.setString(13, notificationDao.getSubject());
            stmt.setString(14, notificationDao.getInnerSubject());
            stmt.setLong(15,notificationDao.getLeadId());
            stmt.addBatch();

        }
        stmt.executeBatch();


    }


    @Override
    public void updateReadStatus(long id) throws SQLException {
        Connection con = null;
        try {
            con = dataSource.getConnection();
            PreparedStatement stmt = con.prepareStatement("update Tbl_Notification set readStatus=?,readTiming=? where id=?;");
            stmt.setBoolean(1, true);
            stmt.setString(2, UtilityClass.getDateRedable());
            stmt.setLong(3, id);
            stmt.executeUpdate();
            logger.info("Updating read status {}", id);
        } catch (SQLException sql) {
            throw sql;

        } finally {
            con.close();
        }

    }

    @Override
    public void snoozeNotification(long id, int minsToIncrease, String prevDate) throws SQLException {
        String newdate = UtilityClass.increaseTime(prevDate, minsToIncrease);
        long newdatelong = Long.parseLong(newdate.replaceAll("-", "").replaceAll(":", "").replaceAll(" ", ""));
        Connection con = null;
        try {
            con = dataSource.getConnection();
            PreparedStatement stmt = con.prepareStatement("update Tbl_Notification set Tbl_Notification.notificationTiming=?,Tbl_Notification.datetimingnotification=? where  id=?;");
            stmt.setString(1, newdate);
            stmt.setLong(2, newdatelong);
            stmt.setLong(3, id);
            stmt.executeUpdate();
        } catch (SQLException sql) {
            sql.printStackTrace();

        } finally {
            con.close();
        }


    }


    @Override
    public List<NotificationDao> getByUserId(int userId, int offset, int limit) throws SQLException {
        Connection con = null;
        List<NotificationDao> al = new ArrayList<>();
        try {
            con = dataSource.getConnection();
            CallableStatement stmt = con.prepareCall("call USP_GETNOTIFACATIONS(?,?)");
            stmt.setInt(1, userId);
            stmt.setLong(2, UtilityClass.fullDateLong());

            ResultSet rs = stmt.executeQuery();
            NotificationDao model = null;
            while (rs.next()) {
                model = new NotificationDao();
                model.setId(rs.getLong(1));
                model.setCreateAt(rs.getString(2));
                model.setCreateBy(rs.getString(3));
                model.setMessage(rs.getString(4));
                model.setNotificationTiming(rs.getString(5));
                model.setReadStatus(rs.getBoolean(6));
                model.setReadTiming(rs.getString(7));
                model.setRedirectUrl(rs.getString(8));
                model.setSubject(rs.getString(9));
                model.setInnerSubject(rs.getString(10));
                al.add(model);
            }

        } catch (SQLException sql) {
            throw sql;

        } finally {
            con.close();
        }
        return al;
    }

    @Override
    public List<NotificationDao> getByUserIdAndInnerSubject(int userId, String innerSubject, int offset, int limit) throws SQLException {
        Connection con = null;
        List<NotificationDao> al = new ArrayList<>();
        try {
            con = dataSource.getConnection();
            PreparedStatement stmt = con.prepareStatement("SELECT `Tbl_Notification`.`id`, `Tbl_Notification`.`createAt`, `Tbl_Notification`.`createBy`, `Tbl_Notification`.`message`, `Tbl_Notification`.`notificationTiming`, `Tbl_Notification`.`readStatus`, `Tbl_Notification`.`readTiming`, `Tbl_Notification`.`redirectUrl`, `Tbl_Notification`.`subject`,Tbl_Notification.innerSubject FROM `Tbl_Notification` where assignTo=? and datetimingnotification<=? and Tbl_Notification.innerSubject=? order by datetimingnotification desc limit ?,? ;");
            stmt.setInt(1, userId);
            stmt.setLong(2, UtilityClass.fullDateLong());
            stmt.setString(3, innerSubject);
            stmt.setInt(4, limit);
            stmt.setInt(5, offset);
            ResultSet rs = stmt.executeQuery();
            NotificationDao model = null;
            while (rs.next()) {
                model = new NotificationDao();
                model.setId(rs.getLong(1));
                model.setCreateAt(rs.getString(2));
                model.setCreateBy(rs.getString(3));
                model.setMessage(rs.getString(4));
                model.setNotificationTiming(rs.getString(5));
                model.setReadStatus(rs.getBoolean(6));
                model.setReadTiming(rs.getString(7));
                model.setRedirectUrl(rs.getString(8));
                model.setSubject(rs.getString(9));
                model.setInnerSubject(rs.getString(10));
                al.add(model);
            }

        } catch (SQLException sql) {
            throw sql;

        } finally {
            con.close();
        }
        return al;
    }
}
