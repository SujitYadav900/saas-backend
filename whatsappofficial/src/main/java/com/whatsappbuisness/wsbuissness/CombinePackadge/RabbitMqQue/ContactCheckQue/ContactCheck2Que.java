package com.whatsappbuisness.wsbuissness.CombinePackadge.RabbitMqQue.ContactCheckQue;
/*
 Author=Supreet Singh
 Date= 07/02/21 2:12 PM
*/

import com.rabbitmq.client.Channel;
import com.whatsappbuisness.wsbuissness.CombinePackadge.ApiService.ContactCheck.ContactCheckService;
import com.whatsappbuisness.wsbuissness.CombinePackadge.ApiService.ContactCheck.ContactNotFoundExepction;
import com.whatsappbuisness.wsbuissness.CombinePackadge.CountryWisePrice.CountryWisePriceService;
import com.whatsappbuisness.wsbuissness.CombinePackadge.Messages.Gateway;
import com.whatsappbuisness.wsbuissness.CombinePackadge.Messages.MessageDao;
import com.whatsappbuisness.wsbuissness.CombinePackadge.Messages.MessageStatus;
import com.whatsappbuisness.wsbuissness.CombinePackadge.RabbitMqQue.QueName;
import com.whatsappbuisness.wsbuissness.CombinePackadge.RabbitMqQue.RabbitMqqueService;
import com.whatsappbuisness.wsbuissness.CombinePackadge.WsUserORBuisness.UserOrBuisnessService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.UUID;

@Component
public class ContactCheck2Que {

    private static final Logger logger = LoggerFactory.getLogger(ContactCheck2Que.class);

    @Autowired
    ContactCheckService contactCheckService;

    @Autowired
    RabbitMqqueService rabbitMqqueService;
    @Autowired
    CountryWisePriceService countryWisePriceService;

    @Autowired
    UserOrBuisnessService userOrBuisnessService;


    @RabbitListener(queues = "Contact_Que2", concurrency = "5")
    public void WebPanelQue(MessageDao messageDao, Channel channel, @Header(AmqpHeaders.DELIVERY_TAG) long tag) throws IOException {
        messageDao.setId(generateUid());
        try {
            check(messageDao);
        } catch (Exception ew) {
            channel.basicAck(tag,false);
            ew.printStackTrace();
        }
    }

    public void check(MessageDao messageDao) {
        try {
            if(messageDao.getSubscriptionDao().getGateway()== Gateway.AMEYO){
                contactCheckService.checkContact(messageDao);
            }
            forwardMessage(messageDao);
        } catch (ContactNotFoundExepction contactNotFoundExepction) {
            messageDao.setMessageStatus(MessageStatus.INVALIDDST);
            rollBackAndSaveToDb(messageDao);
            logger.error("Contact Checked Failed As Dst was Invalid {}");
        }
        catch (Exception e) {
            e.printStackTrace();
            messageDao.setMessageStatus(MessageStatus.FAILED);
            rollBackAndSaveToDb(messageDao);
            logger.error("Contact Checked Other Exception Happened {}", messageDao);
        }
    }





    public String generateUid() {
        return UUID.randomUUID().toString();
    }

    private void forwardMessage(MessageDao messageDao) {
        rabbitMqqueService.pushToQue(QueName.SendWs_QUE2.routing, QueName.SendWs_QUE2.queName, messageDao);
    }

    private void rollBackAndSaveToDb(MessageDao messageDao) {
        rabbitMqqueService.pushToQue(QueName.Mongo_Que.routing, QueName.Mongo_Que.queName, messageDao);
    }
}
