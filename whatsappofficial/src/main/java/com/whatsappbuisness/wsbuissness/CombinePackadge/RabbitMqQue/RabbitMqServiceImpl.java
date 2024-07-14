package com.whatsappbuisness.wsbuissness.CombinePackadge.RabbitMqQue;
/*
 Author=Supreet Singh
 Date= 07/02/21 3:08 PM
*/


import com.whatsappbuisness.wsbuissness.CombinePackadge.Messages.MessageDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RabbitMqServiceImpl implements RabbitMqqueService {
    @Autowired
    private AmqpTemplate amqpTemplate;
    private static final Logger logger= LoggerFactory.getLogger(RabbitMqServiceImpl.class);
    @Override
    public void pushToQue(String exchange, String que, Object obj) {
//        logger.info("Message will Send to Message Queue {}",obj);
        amqpTemplate.convertAndSend(exchange,que,obj);
    }

    @Override
    public void pushToQueJson(String exchange, String que, String json) {
        amqpTemplate.convertAndSend(exchange,que,json);
    }
    @Override
    public void sendListOfMessages(List<MessageDao> messages, String exchangeName, String routingKey) {
        amqpTemplate.convertAndSend(exchangeName, routingKey, messages);
    }


}
