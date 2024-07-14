package com.example.maxcrm.MaxCrm.ServiceImpl;

import com.example.maxcrm.MaxCrm.Dao.LeadMasterDao;
import com.example.maxcrm.MaxCrm.Dao.LogTaskDao;
import com.example.maxcrm.MaxCrm.Dao.MaskingDao;
import com.example.maxcrm.MaxCrm.Dao.NotificationDao;
import com.example.maxcrm.MaxCrm.Repo.LogTaskRepo;
import com.example.maxcrm.MaxCrm.Service.CounterService;
import com.example.maxcrm.MaxCrm.Service.LeadMasterService;
import com.example.maxcrm.MaxCrm.Service.LogTaskService;
import com.example.maxcrm.MaxCrm.Service.NotificationService;
import com.example.maxcrm.MaxCrm.Utility.UtilityClass;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class LogTaskServiceImpl implements LogTaskService {
    Logger logger = LoggerFactory.getLogger(LogTaskServiceImpl.class);
    @Autowired
    LogTaskRepo logTaskRepo;
    @Autowired
    CounterService counterService;

    @Autowired
    NotificationService notificationService;
    @Autowired
    LeadMasterService leadMasterService;


    @Qualifier("dataSource")
    @Autowired
    DataSource dataSource;

    @Override
    public LogTaskDao insert(LogTaskDao logTaskDao) throws SQLException {
        Connection con = null;
        try {
            long id = counterService.getCounter(1, 1);
            con = dataSource.getConnection();
            con.setAutoCommit(false);
            logTaskDao.setId(id);
            logTaskDao.setRefId(id);
            PreparedStatement stmt = con.prepareStatement("INSERT INTO `Tbl_Log_Task` ( id,`assignedTo`, `createBy`, `createDate`, `dateTimeTask`, `leadId`, `message`, `refId`, `subject`, `updateBy`, `updateDate`, `url`,txtMsgNotification) VALUES ( ?,?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?);");
            stmt.setLong(1, id);
            stmt.setInt(2, logTaskDao.getAssignedTo());
            stmt.setString(3, logTaskDao.getCreateBy());
            stmt.setString(4, logTaskDao.getCreateDate());
            stmt.setString(5, logTaskDao.getDateTimeTask());
            stmt.setLong(6, logTaskDao.getLeadId());
            stmt.setString(7, logTaskDao.getMessage());
            stmt.setLong(8, logTaskDao.getRefId());
            stmt.setString(9, logTaskDao.getSubject());
            stmt.setString(10, logTaskDao.getUpdateBy());
            stmt.setString(11, logTaskDao.getUpdateDate());
            stmt.setString(12, logTaskDao.getUrl());
            stmt.setBoolean(13, logTaskDao.isTxtMsgNotification());
            stmt.executeUpdate();
            logger.info("Inserting {}", logTaskDao);
            long datetime = Long.parseLong(logTaskDao.getDateTimeTask().replaceAll("-", "").replaceAll(" ", "").replaceAll(":", ""));


            NotificationDao notificationDao = new NotificationDao();
            notificationDao.setLeadId(logTaskDao.getLeadId());
            notificationDao.setId(logTaskDao.getId());
            notificationDao.setTo(logTaskDao.getAssignedTo());
            notificationDao.setCreateAt(logTaskDao.getCreateDate());
            notificationDao.setCreateBy(logTaskDao.getCreateBy());
            notificationDao.setInnerSubject("TASK");
            notificationDao.setDatetiming(datetime);
            notificationDao.setMessage(logTaskDao.getMessage());
            notificationDao.setNotificationTiming(logTaskDao.getDateTimeTask());
            notificationDao.setRedirectUrl(logTaskDao.getUrl());
            notificationDao.setSendMessage(logTaskDao.isTxtMsgNotification());
            notificationDao.setSubject(logTaskDao.getSubject());
            notificationService.insertNotification(notificationDao, con);
            if (UtilityClass.propertyService.findProperty("Lead", "UpdateLeadOnTaskCreation").equalsIgnoreCase("1")) {
                leadMasterService.updateLastUpdateLead(con, logTaskDao.getCreateBy(), UtilityClass.dateFilterDate(), logTaskDao.getCreateDate(), logTaskDao.getLeadId());

            }

            con.commit();
        } catch (SQLException sql) {
            sql.printStackTrace();
            con.rollback();
            throw sql;


        } finally {
            con.close();
        }
        return logTaskDao;
    }

    @Override
    public LogTaskDao update(LogTaskDao logTaskDao) throws SQLException {
        Connection con = null;
        try {
            logger.info("Updating {}", logTaskDao);
            logTaskRepo.save(logTaskDao);
            con = dataSource.getConnection();
            long datetime = Long.parseLong(logTaskDao.getDateTimeTask().replaceAll("-", "").replaceAll(" ", "").replaceAll(":", ""));
            NotificationDao notificationDao = new NotificationDao();
            notificationDao.setLeadId(logTaskDao.getLeadId());
            notificationDao.setId(logTaskDao.getId());
            notificationDao.setTo(logTaskDao.getAssignedTo());
            notificationDao.setCreateAt(logTaskDao.getCreateDate());
            notificationDao.setCreateBy(logTaskDao.getCreateBy());
            notificationDao.setDatetiming(datetime);
            notificationDao.setMessage(logTaskDao.getMessage());
            notificationDao.setInnerSubject("TASK");
            notificationDao.setNotificationTiming(logTaskDao.getDateTimeTask());
            notificationDao.setRedirectUrl(logTaskDao.getUrl());
            notificationDao.setSendMessage(logTaskDao.isTxtMsgNotification());
            notificationDao.setSubject(logTaskDao.getSubject());
            notificationService.updateNotification(notificationDao, con);
            if (UtilityClass.propertyService.findProperty("Lead", "UpdateLeadOnTaskUpdating").equalsIgnoreCase("1")) {
                leadMasterService.updateLastUpdateLead(con, logTaskDao.getCreateBy(), UtilityClass.dateFilterDate(), logTaskDao.getCreateDate(), logTaskDao.getLeadId());

            }

        } catch (SQLException sql) {
            throw sql;

        } finally {
            con.close();
        }


        return logTaskDao;
    }

    @Override
    public List<LogTaskDao> getAllUnreadTask(String userIdList) throws SQLException {
        Connection con = null;
        List<LogTaskDao> al = new ArrayList<>();
        LogTaskDao modeltmp;
        try {
            con = dataSource.getConnection();
            CallableStatement stmt = con.prepareCall("call USP_GETCURRENTANDUNPENDINGTASK(?)");
            stmt.setString(1, userIdList);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                modeltmp = new LogTaskDao();
                modeltmp.setDateTimeTask(rs.getString(1));
                modeltmp.setSubject(rs.getString(2));
                modeltmp.setMessage(rs.getString(3));
                modeltmp.setUrl(rs.getString(4));
                modeltmp.setCreateBy(rs.getString(5));
                modeltmp.setCreateDate(rs.getString(6));
                modeltmp.setId(rs.getLong(7));
                al.add(modeltmp);

            }
        } catch (SQLException sql) {
            logger.error("Error", sql);

        } finally {
            con.close();
        }
        return al;
    }

    private void updateUpComingTask(long id, String date) throws SQLException {
        Connection con = null;
        try {
            con = dataSource.getConnection();
            CallableStatement stmt = con.prepareCall("call USP_UPDATEUPCOMINGTASK(?,?)");
            stmt.setLong(1, id);
            stmt.setString(2, date);
            stmt.executeUpdate();
            logger.info("Updating Upcoming Follow up task id{} data{}", id, date);


        } catch (SQLException sql) {
            logger.error("Errror Occur", sql);

        } finally {
            con.close();
        }
    }

    @Override
    public Iterable<LogTaskDao> findAllByLeadId(long id) {
        return logTaskRepo.findAllByLeadId(id);
    }

    @Override
    public void delete(long id, String createBy, long leadId) {
        logger.info("Delting {}", id);
        logTaskRepo.deleteById(id);
        notificationService.delete(id);
        if (UtilityClass.propertyService.findProperty("Lead", "UpdateLeadOnTaskDelete").equalsIgnoreCase("1")) {
            leadMasterService.updateLastUpdateLead(null, createBy, UtilityClass.dateFilterDate(), UtilityClass.getDateRedable(), leadId);

        }
    }

    @Override
    public List<LogTaskDao> findAllForDashboard(String userList, MaskingDao maskingDao) {

        Connection con = null;
        List<LogTaskDao> taskList = null;
        try {

            con = dataSource.getConnection();
            int days = Integer.parseInt(UtilityClass.propertyService.findProperty("Lead", "ShowTasksUpToDays"));
            PreparedStatement stmt;

            if (maskingDao.isNumberMasking()) {
                stmt = con.prepareStatement("select " +
                        "case when notificationTiming<now() then 'Red' else 'Yellow' end as color " +
                        ",Tbl_Log_Task.* " +
                        ",Tbl_UserMaster.username, " +
                        "Tbl_UserMaster.firstName, " +
                        //"concat(Tbl_LeadMaster.firstName,' ', " +
                        //"Tbl_LeadMaster.parentName) as fullname, " +
                        "Tbl_LeadMaster.parentName as fullname, " +
                        "   concat('xxxxxxxx',right(Tbl_LeadMaster.phonenumber,2)), " +
                        "Tbl_Log_Task.leadId " +
                        "from Tbl_Notification " +
                        "inner join Tbl_Log_Task on Tbl_Log_Task.id=Tbl_Notification.id " +
                        "inner join Tbl_UserMaster on Tbl_UserMaster.id=Tbl_Log_Task.assignedTo " +
                        "inner join Tbl_LeadMaster on Tbl_LeadMaster.id=Tbl_Log_Task.leadId " +
                        "where readStatus=false " +
                        "and " +
                        "((datediff( Tbl_Notification.notificationTiming,now())<=" + days + " and datediff( Tbl_Notification.notificationTiming,now())>=0) " +
                        "or " +
                        "notificationTiming<now()) " +
                        "and find_in_set(Tbl_Notification.assignTo,'" + userList + " ') order by Tbl_Log_Task.id desc;");
            } else {
                stmt = con.prepareStatement("select " +
                        "case when notificationTiming<now() then 'Red' else 'Yellow' end as color " +
                        ",Tbl_Log_Task.* " +
                        ",Tbl_UserMaster.username, " +
                        "Tbl_UserMaster.firstName, " +
                        //"concat(Tbl_LeadMaster.firstName,' ', " +
                        // "Tbl_LeadMaster.lastName) as fullname, " +
                        "Tbl_LeadMaster.parentName as fullname, " +
                        "Tbl_LeadMaster.phonenumber, " +
                        "Tbl_Log_Task.leadId " +
                        "from Tbl_Notification " +
                        "inner join Tbl_Log_Task on Tbl_Log_Task.id=Tbl_Notification.id " +
                        "inner join Tbl_UserMaster on Tbl_UserMaster.id=Tbl_Log_Task.assignedTo " +
                        "inner join Tbl_LeadMaster on Tbl_LeadMaster.id=Tbl_Log_Task.leadId " +
                        "where readStatus=false " +
                        "and " +
                        "((datediff( Tbl_Notification.notificationTiming,now())<=" + days + " and datediff( Tbl_Notification.notificationTiming,now())>=0) " +
                        "or " +
                        "notificationTiming<now()) " +
                        "and find_in_set(Tbl_Notification.assignTo,'" + userList + "') order by Tbl_Log_Task.id desc;");
            }
            logger.info("LOGTASK QUERY >> {}", stmt.toString());
            ResultSet rs = stmt.executeQuery();

            taskList = new ArrayList<>();
            LogTaskDao dao;
            while (rs.next()) {

                dao = new LogTaskDao();

                dao.setColor(rs.getString(1));
                dao.setId(rs.getLong(2));
                dao.setAssignedTo(rs.getInt(3));
                dao.setCreateDate(rs.getString(5));
                dao.setDateTimeTask(rs.getString(6));
                dao.setMessage(rs.getString(8));
                dao.setSubject(rs.getString(10));
                dao.setUrl(rs.getString(13));
                dao.setFirstname(rs.getString(16));
                dao.setFullName(rs.getString(17));
                dao.setPhoneNumber(rs.getString(18));
                dao.setLeadId(rs.getInt(19));
                taskList.add(dao);

            }

        } catch (SQLException sql) {
            logger.warn("Error Occured {}", sql);
        } finally {
            try {
                con.close();
            } catch (Exception e) {
                logger.warn("Error Occured {}", e);
            }
        }
        return taskList;
    }

    @Override
    public void downloadLogTask(List<LogTaskDao> logTaskList) {
        System.out.println("Download Method Starts and LogTask is "+logTaskList);
        SXSSFWorkbook workbook = new SXSSFWorkbook(logTaskList.size());
        CreationHelper createHelper = workbook.getCreationHelper();

        // Create a Sheet
        Sheet sheet = workbook.createSheet("Report");

        // Create a Font for styling header cells
        Font headerFont = workbook.createFont();
        headerFont.setBold(true);
        headerFont.setFontHeightInPoints((short) 10);
        headerFont.setColor(IndexedColors.RED.getIndex());

        // Create a CellStyle with the font
        CellStyle headerCellStyle = workbook.createCellStyle();
        headerCellStyle.setFont(headerFont);

        // Create a Row
        Row headerRow = sheet.createRow(0);
        String[] columns = {"Task ID", "Create Date", "User Name", "Subject", "Log Message", "Lead Id", "Date and Time Task"};

        // Create cells
        for (int i = 0; i < columns.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(columns[i]);
            cell.setCellStyle(headerCellStyle);
        }
        logger.info("Added headers");
        System.out.println("Added headers");
        int rowNum = 1;
        String applicationPrefix = UtilityClass.ApplicationPrefix;

        for (LogTaskDao dao : logTaskList) {

            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(dao.getId() + "");
            row.createCell(1).setCellValue(dao.getCreateDate() + "");
            row.createCell(2).setCellValue(dao.getFirstname() + "");
            row.createCell(3).setCellValue(dao.getSubject() + "");
            row.createCell(4).setCellValue(dao.getMessage() + "");
            row.createCell(5).setCellValue(dao.getLeadId() + "");
            row.createCell(6).setCellValue(dao.getDateTimeTask() + "");

        }
            logger.info("Added cells");

            // Write the output to a file
            final String path = System.getProperty("user.dir");

            logger.info("path > {}", path);
            final long timeStamp = System.currentTimeMillis();
            final String fileName = "Report_" + timeStamp + ".xlsx";

            logger.info("writing file");
            FileOutputStream fileOut = null;
            try {
                fileOut = new FileOutputStream(path + fileName);
                logger.info("File writesucsessfuly");
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
            try {
                logger.info("writting workbook");
                workbook.write(fileOut);
                fileOut.close();

                // Closing the workbook
                workbook.dispose();
                workbook.close();

            } catch (Exception e) {
                e.printStackTrace();
            }
            logger.info("retruning report");


        }
    }

