package com.whatsappbuisness.wsbuissness.CombinePackadge.WebhookDao;

import com.whatsappbuisness.wsbuissness.CombinePackadge.Common.SessionRetrival.SessionRetrievalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

/*
 Author=Supreet Singh
 Date= 12/03/21 5:31 PM
*/
@RestController
@RequestMapping("/webhook")
public class WebhookController {
    @Autowired
    WebhookService webhookService;
    @Autowired
    SessionRetrievalService retrievalService;
    @GetMapping("/get")
    public WebhookDao getWebhook(Authentication authentication)
    {
        return webhookService.getWebhook(retrievalService.get(authentication).getAccountId());
    }
    @PostMapping("/update")
    public WebhookDao save(@RequestBody WebhookDao webhookDao)
    {
        return webhookService.update(webhookDao);
    }
}
