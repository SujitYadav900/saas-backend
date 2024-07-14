package com.whatsappbuisness.wsbuissness.CombinePackadge.ApiService.SendingServiceImpl;

import com.whatsappbuisness.wsbuissness.CombinePackadge.Messages.MessageDao;
import com.whatsappbuisness.wsbuissness.CombinePackadge.Messages.MessageType;
import com.whatsappbuisness.wsbuissness.CombinePackadge.Messages.TemplateMessageDao.TemplateText.TemplateMessageDao;
import com.whatsappbuisness.wsbuissness.CombinePackadge.Messages.TextMessage.TextMessageDao;
import com.whatsappbuisness.wsbuissness.CombinePackadge.RabbitMqQue.RabbitMqqueService;
import com.whatsappbuisness.wsbuissness.CombinePackadge.Subscription.SubscriptionDao;
import com.whatsappbuisness.wsbuissness.CombinePackadge.Subscription.SubscriptionService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;

/*
 Author=Supreet Singh
 Date= 10/03/21 4:22 PM
*/
@SpringBootTest
class MessageSendServiceTest {
    @Autowired
    RabbitMqqueService rabbitMqqueService;
    @Autowired
    SubscriptionService subscriptionService;

    @Test
    void InsertMessage() throws InterruptedException {
        SubscriptionDao subscriptionDao= subscriptionService.getByActive(20210334);
        System.out.println(subscriptionDao.toString());
        MessageDao messageDao = new MessageDao();
        messageDao.setAccountId(20210334);
        messageDao.setSubscriptionDao(subscriptionDao);
        messageDao.setTo("919999420594");
        messageDao.setType(MessageType.template);
        TemplateMessageDao templateMessageDao=new TemplateMessageDao();
        messageDao.setTemplate(templateMessageDao);

      /*  TextMessageDao textMessageDao=new TextMessageDao("Hello How are you buddy?");
        messageDao.setText(textMessageDao);*/
        for(int i=0;i<100;i++) {
            rabbitMqqueService.pushToQue("Main_Que", "Main_Que", messageDao);
        }
        Thread.sleep(1);






    }
}
