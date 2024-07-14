package com.whatsappbuisness.wsbuissness.CombinePackadge.RabbitMqQue.ApiQue;

import com.rabbitmq.client.Channel;
import com.whatsappbuisness.wsbuissness.CombinePackadge.ApiService.ContactCheck.ContactCheckService;
import com.whatsappbuisness.wsbuissness.CombinePackadge.ApiService.ContactCheck.ContactNotFoundExepction;
import com.whatsappbuisness.wsbuissness.CombinePackadge.CountryWisePrice.*;
import com.whatsappbuisness.wsbuissness.CombinePackadge.Messages.Gateway;
import com.whatsappbuisness.wsbuissness.CombinePackadge.Messages.MessageDao;

import com.whatsappbuisness.wsbuissness.CombinePackadge.Messages.MessageStatus;
import com.whatsappbuisness.wsbuissness.CombinePackadge.RabbitMqQue.QueName;
import com.whatsappbuisness.wsbuissness.CombinePackadge.RabbitMqQue.RabbitMqqueService;
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
public class ContactCheckQueApi {

    private static final Logger logger = LoggerFactory.getLogger(ContactCheckQueApi.class);

    @Autowired
    ContactCheckService contactCheckService;

    @Autowired
    RabbitMqqueService rabbitMqqueService;


    @Autowired
    CountryWisePriceService countryWisePriceService;
    @RabbitListener(queues = "Contact_Que_Api",concurrency = "5")
    public void WebPanelQue(MessageDao messageDao, Channel channel, @Header(AmqpHeaders.DELIVERY_TAG) long tag) throws IOException {
//        logger.info("Getting Data in contact Api checkque {}", messageDao);
        messageDao.setId(generateUid());
        try {
            check(messageDao);
        }catch (Exception ew)
        {
            channel.basicAck(tag,false);
        }
    }

    public void check(MessageDao messageDao) {
//        logger.info("check messageDao {}",messageDao);
        try {
            if(messageDao.getSubscriptionDao().getGateway()== Gateway.AMEYO){
                contactCheckService.checkContact(messageDao);
            }
            forwardMessage(messageDao);
            //logger.info("Contact Checked Success Will Forward to SendWs Que {}",messageDao);
        } catch (ContactNotFoundExepction contactNotFoundExepction) {
            messageDao.setMessageStatus(MessageStatus.INVALIDDST);
            rollBackAndSaveToDb(messageDao);
            logger.error("Contact Checked Failed As Dst was Invalid {}");
        }

        catch (Exception e) {
            e.printStackTrace();
            messageDao.setMessageStatus(MessageStatus.FAILED);
            rollBackAndSaveToDb(messageDao);
            logger.error("Contact Checked Other Exception Happened {}",messageDao);
        }
    }
    public String generateUid() {
        return UUID.randomUUID().toString();
    }
    private void forwardMessage(MessageDao messageDao)
    {
//        logger.info("forwardMessage {}", messageDao);
        rabbitMqqueService.pushToQue(QueName.SendWs_QUE_API.routing,QueName.SendWs_QUE_API.queName,messageDao );
    }
    private void rollBackAndSaveToDb(MessageDao messageDao)
    {
        rabbitMqqueService.pushToQue(QueName.Mongo_Que.routing,QueName.Mongo_Que.queName, messageDao);
    }
}

