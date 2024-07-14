package com.whatsappbuisness.wsbuissness.CombinePackadge.Subscription;
/*
 Author=Supreet Singh
 Date= 09/03/21 11:12 AM
*/

import com.whatsappbuisness.wsbuissness.CombinePackadge.Common.VoucherType;

public interface SubscriptionService {
    SubscriptionDao insert(SubscriptionDao subscriptionDao) throws Exception;
    SubscriptionDao update(SubscriptionDao subscriptionDao);
    void updateSendTemplate(long accountId,String ref,double ded,VoucherType voucherType,double creditAmount);
    void updateSendSession(long accountId, String ref, double ded, VoucherType voucherType,double creditAmount);
    void updateTemplateCreation(long accountId,String ref,double ded);
    void expireSubscriptions();

    SubscriptionDao getByActive(long accountId);
    void updateLocally(SubscriptionDao subscriptionDao);

    SubscriptionDao restoreLatestSubscription(long accountId);
}
