package com.example.maxcrm.MaxCrm.Schdules;

import com.example.maxcrm.MaxCrm.Service.NotificationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class NotificationSendingSchdules {
    Logger logger = LoggerFactory.getLogger(NotificationSendingSchdules.class);
    @Autowired
    NotificationService notificationService;

   // @Scheduled(initialDelay = 1000,fixedRate = 120000)//
    public void runSchduleMessage() {
        logger.info("Sending Notification Schdule");
        try {
           // notificationService.pushNotification();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
