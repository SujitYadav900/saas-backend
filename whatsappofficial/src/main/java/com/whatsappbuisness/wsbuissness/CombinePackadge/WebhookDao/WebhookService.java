package com.whatsappbuisness.wsbuissness.CombinePackadge.WebhookDao;
/*
 Author=Supreet Singh
 Date= 12/03/21 5:27 PM
*/

public interface WebhookService {
    WebhookDao getWebhook(long id);
    WebhookDao update(WebhookDao webhookDao);
    WebhookDao getCache(long id);

}
