package com.whatsappbuisness.wsbuissness.CombinePackadge.KarixWaOfficial.KarixDao;
/*
 Author=Ritu Redhu
 Date= 06/01/23 10:00 AM
*/

import com.whatsappbuisness.wsbuissness.CombinePackadge.Messages.MessageDao;

import java.util.List;

public interface KarixMessageService {


    MessageDao sendMessageKarix(KarixMessageDao karixMessageDaoList, MessageDao messageDao);
}
