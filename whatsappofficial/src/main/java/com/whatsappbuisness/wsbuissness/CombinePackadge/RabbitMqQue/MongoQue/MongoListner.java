package com.whatsappbuisness.wsbuissness.CombinePackadge.RabbitMqQue.MongoQue;
/*
 Author=Supreet Singh
 Date= 07/02/21 2:07 PM
 */


import com.rabbitmq.client.Channel;
import com.whatsappbuisness.wsbuissness.CombinePackadge.Messages.ChatSide;
import com.whatsappbuisness.wsbuissness.CombinePackadge.Messages.MessageDao;
import com.whatsappbuisness.wsbuissness.CombinePackadge.RabbitMqQue.MongoQue.MongoDB.MessagePersistService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class MongoListner {
	private static final Logger logger = LoggerFactory.getLogger(MongoListner.class);
	@Autowired
	MessagePersistService messagePersistService;

	@RabbitListener(queues = "Mongo_Que")
		public void WebPanelQue(MessageDao messageDao, Channel channel, @Header(AmqpHeaders.DELIVERY_TAG) long tag) throws IOException {
			//     logger.info("Getting data in Mongo Que {}", messageDao);
			try {
				MessageDao messageDao1 = messagePersistService.insert(messageDao);
				if(messageDao1.getChatSide() == ChatSide.Client){
					messagePersistService.webhookCalling(messageDao1,null);
				}
			}catch (Exception ew)
			{
				channel.basicAck(tag,false);
				logger.error("Failed To Insert To Database {}",ew.getMessage());
			}
		}
	}
