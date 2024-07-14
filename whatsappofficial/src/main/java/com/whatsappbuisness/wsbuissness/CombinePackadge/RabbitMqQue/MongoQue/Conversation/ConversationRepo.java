package com.whatsappbuisness.wsbuissness.CombinePackadge.RabbitMqQue.MongoQue.Conversation;
/*
 Author=Supreet Singh
 Date= 10/03/21 12:47 PM
*/

import org.springframework.data.mongodb.repository.MongoRepository;

public interface ConversationRepo extends MongoRepository<ConversationDao,String> {
}
