package com.whatsappbuisness.wsbuissness.CombinePackadge.RabbitMqQue.MongoQue.MongoDB;
/*
 Author=Supreet Singh
 Date= 06/02/21 11:20 PM
*/

import com.whatsappbuisness.wsbuissness.CombinePackadge.Filtering.FilterDao;
import com.whatsappbuisness.wsbuissness.CombinePackadge.MessageEntry.MessageFilterDao;
import com.whatsappbuisness.wsbuissness.CombinePackadge.Messages.MessageDao;
import com.whatsappbuisness.wsbuissness.CombinePackadge.Messages.MessageStatus;
import com.whatsappbuisness.wsbuissness.CombinePackadge.PaginationDao;
import com.whatsappbuisness.wsbuissness.CombinePackadge.RabbitMqQue.Webhooks.Webhook.DlrUpdatesDao;
import com.whatsappbuisness.wsbuissness.CombinePackadge.RabbitMqQue.Webhooks.Webhook.WebhookDao;

import java.util.List;

public interface MessagePersistService {

    MessageDao insert(MessageDao messageDao) throws CloneNotSupportedException;
    MessageDao updateDlr(String id, MessageStatus messageStatus,long accountId);
    List<GroupByMongoDao> get(long accountId,long campaingId);
    List<GroupByMongoDao> get(long accountId,int startdate,int enddate);
    List<MessageDao> getReport(long campId,long accountId);
    List<MessageDao> getReport(FilterDao filterDao);
    List<MessageDao> getMessages(FilterDao filterDao);

    List<MessageDao> getafterDate(long accountId,long timing,String id);


    List<MessageDeliveryStatusDao> getDelieveryStatusDateBasis(String startDate, String endDate, long accountId);
    void webhookCalling(MessageDao messageDao, DlrUpdatesDao dlrUpdatesDao ) throws Exception;
    void callingExternalUrl(WebhookDao webhookDao, String webhookUrl);

    PaginationDao<MessageDao> getIncomingMessageReport(FilterDao filterDao);
}
