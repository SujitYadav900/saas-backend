package com.whatsappbuisness.wsbuissness.CombinePackadge.ApiService.ContactCheck;

import com.whatsappbuisness.wsbuissness.CombinePackadge.Messages.MessageDao;
import com.whatsappbuisness.wsbuissness.CombinePackadge.Messages.MessageType;
import com.whatsappbuisness.wsbuissness.CombinePackadge.Messages.TextMessage.TextMessageDao;
import com.whatsappbuisness.wsbuissness.CombinePackadge.Subscription.SubscriptionDao;
import com.whatsappbuisness.wsbuissness.CombinePackadge.Subscription.SubscriptionService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

/*
 Author=Supreet Singh
 Date= 10/03/21 5:36 PM
*/
@SpringBootTest
    class ContactCheckServiceImplTest {
    @Autowired
    SubscriptionService subscriptionService;
    @Autowired
    ContactCheckService contactCheckService;
        @Test
        void checkContact() {
            SubscriptionDao subscriptionDao= subscriptionService.getByActive(1);
            System.out.println(subscriptionDao.toString());
            MessageDao messageDao = new MessageDao();
            messageDao.setAccountId(1);
            messageDao.setSubscriptionDao(subscriptionDao);
            messageDao.setTo("919999420594");
            messageDao.setType(MessageType.text);
            TextMessageDao textMessageDao=new TextMessageDao("Hello How are you buddy?");
            messageDao.setText(textMessageDao);
            try {
                contactCheckService.checkContact(messageDao);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
