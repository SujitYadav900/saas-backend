package com.whatsappbuisness.wsbuissness.CombinePackadge.Notification;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/notify")
public class NotificationController {

    private static final Logger logger= LoggerFactory.getLogger(NotificationController.class);

    @Autowired
    private NotificationService notificationService;


    @GetMapping("/expiryMail")
    public void notification() throws NoSuchFieldException, IllegalAccessException {
        notificationService.informed();
    }


    @GetMapping("/dailyReport")
    public void dailyReport()
    {
         notificationService.dailyReport();
    }


}
