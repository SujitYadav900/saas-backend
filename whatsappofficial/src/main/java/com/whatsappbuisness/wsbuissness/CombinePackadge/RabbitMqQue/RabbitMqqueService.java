package com.whatsappbuisness.wsbuissness.CombinePackadge.RabbitMqQue;
/*
 Author=Supreet Singh
 Date= 07/02/21 3:05 PM
*/

import com.whatsappbuisness.wsbuissness.CombinePackadge.Messages.MessageDao;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public interface RabbitMqqueService  {

    void pushToQue(String routing,String que,Object obj);
    void pushToQueJson(String routing,String que,String  json );
    void sendListOfMessages(List<MessageDao> messages, String exchangeName, String routingKey);
}
