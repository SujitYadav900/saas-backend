package com.whatsappbuisness.wsbuissness.CombinePackadge.ApiService.ContactCheck;
/*
 Author=Supreet Singh
 Date= 10/03/21 11:23 AM
*/

import com.whatsappbuisness.wsbuissness.CombinePackadge.Messages.MessageDao;

public interface ContactCheckService {
    MessageDao checkContact(MessageDao messageDao) throws Exception;

}
