package com.whatsappbuisness.wsbuissness.CombinePackadge.Template;
/*
 Author=Supreet Singh
 Date= 09/03/21 10:45 AM
*/

import com.whatsappbuisness.wsbuissness.CombinePackadge.Messages.TemplateMessageDao.TemplateText.TemplateMessageDao;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface TemplateRepo extends MongoRepository<TemplateMessageDao,String> {
    List<TemplateMessageDao> findAllByAccountIdAndApplicationOrderByIdDesc(long accountId,String application);
    List<TemplateMessageDao> findAllByAccountId(long accountId, Sort sort);
    List<TemplateMessageDao> findAllByAccountIdAndStatusOrderByIdDesc(long accountId,boolean sts);

    List<TemplateMessageDao> findAllByAccountIdAndName(long accountId,String name);
    List<TemplateMessageDao> findAllByIdAndStatusOrderByIdDesc(String id,boolean sts);
}
