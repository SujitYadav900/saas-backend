package com.whatsappbuisness.wsbuissness.CombinePackadge.Template;

import com.whatsappbuisness.wsbuissness.CombinePackadge.Messages.TemplateMessageDao.TemplateText.TemplateMessageDao;
import com.whatsappbuisness.wsbuissness.CombinePackadge.Subscription.SubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

/*
 Author=Supreet Singh
 Date= 09/03/21 10:52 AM
*/
@Service
public class TemplateMessageServiceImpl implements TemplateMessageService {
    @Autowired
    TemplateRepo templateRepo;

    @Autowired
    SubscriptionService subscriptionService;

    @Override
    public TemplateMessageDao insert(TemplateMessageDao templateMessageDao) {
        templateMessageDao = templateRepo.insert(templateMessageDao);
        subscriptionService.updateTemplateCreation(templateMessageDao.getAccountId(), templateMessageDao.getId(), 1);
        return templateMessageDao;

    }

    @Override
    public TemplateMessageDao update(TemplateMessageDao templateMessageDao) {
        return templateRepo.save(templateMessageDao);
    }

    @Override
    public List<TemplateMessageDao> findByAccount(long accountId, String application) {
        return templateRepo.findAllByAccountIdAndApplicationOrderByIdDesc(accountId, application);
    }

    @Override
    public List<TemplateMessageDao> findByAccountAll(long accountId) {
        return templateRepo.findAllByAccountId(accountId, Sort.by(Sort.Order.desc("_id")));
    }

    @Override
    public List<TemplateMessageDao> findByAccountIdAndName(long accountId, String name) {
        return templateRepo.findAllByAccountIdAndName(accountId,name);
    }

    @Override
    public List<TemplateMessageDao> findByAccountAllActive(long accountId) {
        return templateRepo.findAllByAccountIdAndStatusOrderByIdDesc(accountId,true);
    }

    @Override
    public TemplateMessageDao findById(String id) {
        try {
            return templateRepo.findById(id).get();
        }catch (Exception ew)
        {
            return null;
        }
    }
}
