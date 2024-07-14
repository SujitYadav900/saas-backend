package com.whatsappbuisness.wsbuissness.CombinePackadge.RabbitMqQue.WhatsappApiQue2;

import com.rabbitmq.client.Channel;
import com.whatsappbuisness.wsbuissness.CombinePackadge.ApiService.SendingServiceImpl.MessageSendService;
import com.whatsappbuisness.wsbuissness.CombinePackadge.Messages.MessageDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class WhatsappApiQue2 {

    private static final Logger logger = LoggerFactory.getLogger(WhatsappApiQue2.class);

    @Autowired
    MessageSendService messageSendService;
    @RabbitListener(queues = "SendWs_QUE2",concurrency = "5")
    void webPanel2Queue(MessageDao messageDao, Channel channel, @Header(AmqpHeaders.DELIVERY_TAG) long tag) throws IOException {
        try{
            logger.info("Message is listnered by que 2");
            messageSendService.hitApi(messageDao);
        }catch (Exception e){
            logger.info("Exception in apiExchangeLister, manually acknowledging the message ");
            channel.basicAck(tag,false);
        }
    }
}
