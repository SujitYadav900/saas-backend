package com.whatsappbuisness.wsbuissness.CombinePackadge.WebhookDao;
/*
 Author=Supreet Singh
 Date= 12/03/21 5:28 PM
*/

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
@Service
public class WebhookServiceImpl implements WebhookService {
    private static final HashMap<Long, WebhookDao> hashMap = new HashMap<>();
    @Autowired
    WebhookRepo webhookRepo;

    @Bean
    public void loadAllWebhooks() {
        List<WebhookDao> webhookDaos = webhookRepo.findAllact();
        for (WebhookDao webhookDao : webhookDaos) {
            hashMap.put(webhookDao.getId(), webhookDao);
        }
    }

    @Override
    public WebhookDao getWebhook(long id) {
        WebhookDao webhookDao = null;
        try {
            webhookDao = webhookRepo.findById(id).get();
        } catch (Exception ew) {
            webhookDao = new WebhookDao();
            webhookDao.setId(id);
            webhookDao.setDlrUpdates(false);
            webhookDao.setHasWebhook(false);
            webhookDao.setMsgUpdates(false);
            webhookDao = webhookRepo.save(webhookDao);
        }
        return webhookDao;
    }

    @Override
    public WebhookDao update(WebhookDao webhookDao) {
        hashMap.remove(webhookDao.getId());
        webhookDao = webhookRepo.save(webhookDao);
        if (webhookDao.isHasWebhook()) {
            hashMap.put(webhookDao.getId(), webhookDao);
        }
        return webhookDao;
    }

    @Override
    public WebhookDao getCache(long id) {
        return hashMap.get(id);
    }
}
