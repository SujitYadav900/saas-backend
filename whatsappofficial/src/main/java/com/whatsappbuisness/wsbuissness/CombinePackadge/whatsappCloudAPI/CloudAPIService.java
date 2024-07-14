package com.whatsappbuisness.wsbuissness.CombinePackadge.whatsappCloudAPI;

import com.whatsappbuisness.wsbuissness.CombinePackadge.KarixWaOfficial.KarixDao.KarixMessageDao;
import com.whatsappbuisness.wsbuissness.CombinePackadge.Messages.MessageDao;

public interface CloudAPIService {

    MessageDao sendMessageByCloudAPI(CloudAPIDao cloudAPIDao, MessageDao messageDao);
}


