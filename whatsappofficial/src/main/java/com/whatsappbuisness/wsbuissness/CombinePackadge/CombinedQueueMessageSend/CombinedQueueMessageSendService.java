package com.whatsappbuisness.wsbuissness.CombinePackadge.CombinedQueueMessageSend;

import com.whatsappbuisness.wsbuissness.CombinePackadge.MessageEntry.MessageEntryResponseDao;
import com.whatsappbuisness.wsbuissness.CombinePackadge.Messages.MessageDao;

import java.util.List;

public interface CombinedQueueMessageSendService {
    MessageEntryResponseDao messageBulkInsertService(List<MessageDao> messageDao, long accountId, boolean b);

}
