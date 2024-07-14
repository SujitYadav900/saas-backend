package com.whatsappbuisness.wsbuissness.CombinePackadge.CombinedQueue3MessageSend;

import com.whatsappbuisness.wsbuissness.CombinePackadge.MessageEntry.MessageEntryResponseDao;
import com.whatsappbuisness.wsbuissness.CombinePackadge.Messages.MessageDao;

import java.util.List;

public interface CombinedQueue3MessageSendService {
    MessageEntryResponseDao messageBulkInsertService(List<MessageDao> messageDao, long accountId, boolean b);

}
