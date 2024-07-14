package com.whatsappbuisness.wsbuissness.CombinePackadge.ApiService.Health;
/*
 Author=Supreet Singh
 Date= 12/02/21 3:11 PM
*/


import com.whatsappbuisness.wsbuissness.CombinePackadge.Subscription.SubscriptionDao;

public interface HealthService {
    HealthDao getHealth(SubscriptionDao subscriptionDao) throws Exception;

}
