package com.whatsappbuisness.wsbuissness.CombinePackadge.Subscription.SubscriptionFeature;

import org.springframework.beans.factory.annotation.Autowired;

public class SubscriptionFeatureServiceImpl  implements SubscriptionFeatureService{
    @Autowired
    SubscriptionFeatureRepo subscriptionFeatureRepo;
    @Override
    public Iterable<SubscriptionFeatureDao> get() {
        return subscriptionFeatureRepo.findAll();
    }
}
