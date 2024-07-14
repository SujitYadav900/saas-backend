package com.whatsappbuisness.wsbuissness.CombinePackadge.RabbitMqQue.MongoQue.Conversation;
/*
 Author=Supreet Singh
 Date= 10/03/21 1:03 PM
*/

import com.whatsappbuisness.wsbuissness.CombinePackadge.PaginationDao;

import java.util.List;

public interface ConversationService {
    void unread(String id,long accountId);
    List<ConversationDao> getOnlyUnread(long accountId);
    PaginationDao<ConversationDao> getAll(FilterDao filterDao);
    void saveContact(String contact,String id,long accountId);

}
