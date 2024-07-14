package com.example.maxcrm.MaxCrm.Controller;

import com.example.maxcrm.MaxCrm.Dao.NotificationDao;
import com.example.maxcrm.MaxCrm.Dao.UserMasterDao;
import com.example.maxcrm.MaxCrm.Service.NotificationService;
import com.example.maxcrm.MaxCrm.Utility.UtilityClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/api/notification")
public class NotificationController {

    @Autowired
    NotificationService notificationService;

    @GetMapping("/getnotification")
    public List<NotificationDao> getAllNotification(@RequestParam("offset") int offset, @RequestParam("limit") int limit, Authentication authentication) throws SQLException {
        UserMasterDao userMasterDao = (UserMasterDao) authentication.getPrincipal();
        return notificationService.getByUserId(userMasterDao.getId(), offset, limit);
    }
    @GetMapping("/getnotificationbyinnersubject")
    public List<NotificationDao> getAllNotificationByInnerSubject(@RequestParam("offset") int offset, @RequestParam("limit") int limit,@RequestParam("innersubject") String innerSubject, Authentication authentication) throws SQLException {
        UserMasterDao userMasterDao = (UserMasterDao) authentication.getPrincipal();
        return notificationService.getByUserIdAndInnerSubject(userMasterDao.getId(),innerSubject, offset, limit);
    }
    @PostMapping("/updateNotification")
    NotificationDao updateNotification(@RequestBody NotificationDao notificationDao) {
        return notificationService.updateNotificationSnooze(notificationDao);
    }

    @PostMapping("/updatereadstatus")
    void updateReadStatus(@RequestParam("id") long id) throws SQLException {
        notificationService.updateReadStatus(id);
    }

    @PostMapping("/snoozenotification")
    void snoozeNotification(@RequestParam("id")long id,@RequestParam("timinginmins") int timinginmins,@RequestParam("beforeTime")String beforeTime) throws SQLException {
        beforeTime= UtilityClass.getDateMysql();
        notificationService.snoozeNotification(id,timinginmins,beforeTime);
    }


}
