package com.whatsappbuisness.wsbuissness.CombinePackadge.Template;
/*
 Author=Supreet Singh
 Date= 09/03/21 10:46 AM
*/

import com.whatsappbuisness.wsbuissness.CombinePackadge.Messages.TemplateMessageDao.TemplateText.TemplateMessageDao;

import java.util.List;

public interface TemplateMessageService {
    TemplateMessageDao insert(TemplateMessageDao templateMessageDao);
    TemplateMessageDao update(TemplateMessageDao templateMessageDao);

    List<TemplateMessageDao> findByAccount(long accountId,String application);
    List<TemplateMessageDao> findByAccountAll(long accountId);

    List<TemplateMessageDao> findByAccountIdAndName(long accountId, String name);

    List<TemplateMessageDao> findByAccountAllActive(long accountId);
    TemplateMessageDao findById(String id);
}
