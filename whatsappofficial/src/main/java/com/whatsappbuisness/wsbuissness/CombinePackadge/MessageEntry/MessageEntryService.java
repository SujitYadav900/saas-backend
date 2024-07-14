package com.whatsappbuisness.wsbuissness.CombinePackadge.MessageEntry;
/*
 Author=Supreet Singh
 Date= 11/03/21 11:34 AM
*/

import com.whatsappbuisness.wsbuissness.CombinePackadge.Messages.MessageDao;

import java.util.List;

public interface MessageEntryService {
    MessageEntryResponseDao messageInsertService(MessageDao messageDao,boolean isPanel);
    MessageEntryResponseDao messageBulkInsertService(List<MessageDao> al,long accountId,boolean isPanel);

    void insertToQue(MessageDao messageDao);
    void insertToApiQue(MessageDao messageDao);
}
