package com.whatsappbuisness.wsbuissness.CombinePackadge.WebhookDao;
/*
 Author=Supreet Singh
 Date= 12/03/21 5:28 PM
*/


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface WebhookRepo extends CrudRepository<WebhookDao,Long> {
    @Query("from WebhookDao  where hasWebhook=TRUE ")
    List<WebhookDao> findAllact();
}
