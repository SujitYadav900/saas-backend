package com.whatsappbuisness.wsbuissness.CombinePackadge.RabbitMqQue.DlrQue;

import com.rabbitmq.client.Channel;
import com.whatsappbuisness.wsbuissness.CombinePackadge.MessageReportDao.MessageReportService;
import com.whatsappbuisness.wsbuissness.CombinePackadge.Messages.MessageStatus;
import com.whatsappbuisness.wsbuissness.CombinePackadge.RabbitMqQue.Webhooks.Webhook.DlrUpdatesDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

import java.io.IOException;

/*
 Author=Supreet Singh
 Date= 09/02/21 5:32 PM
*/
@Component
public class DlrQue {
    private static final Logger logger = LoggerFactory.getLogger(DlrQue.class);
    @Autowired
    MessageReportService messageReportService;


    @RabbitListener(queues = "DLR_Que")
    public void WebPanelQue(DlrUpdatesDao dlrUpdatesDao, Channel channel, @Header(AmqpHeaders.DELIVERY_TAG) long tag) throws IOException {
        //logger.info("Getting Data Into DLR Que {}", dlrUpdatesDao);
        try {
            messageStatus(dlrUpdatesDao);
        }catch (Exception ew)
        {
            channel.basicAck(tag,false);
            ew.printStackTrace();
            logger.error("Failed To Update DLr {}",ew.getMessage());
        }
    }

    private void messageStatus(DlrUpdatesDao dlrUpdatesDao) {
        MessageStatus messageStatus=dlrUpdatesDao.getStatus();

//        logger.info("The value of DLR Object");
        messageReportService.updateDlr(dlrUpdatesDao.getId(), messageStatus, dlrUpdatesDao.getAccountId());


    }

}
