package com.whatsappbuisness.wsbuissness.CombinePackadge.Subscription.SubscriptionFeature.SubscriptionFeaturePermission;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class SubscriptionFeaturePermissionServiceImpl implements SubscriptionFeaturePermissionService {
    @Autowired
    SubsciptionFeaturePermissionRepo subsciptionFeaturePermissionRepo;

    @Override
    public List<SubscriptionFeaturePermissionDao> get(int accountId) {
        return subsciptionFeaturePermissionRepo.getAllByAccountId(accountId);
    }

    @Override
    public void delete(SubscriptionFeaturePermissionDao subscriptionFeaturePermissionDao) {
        subsciptionFeaturePermissionRepo.delete(subscriptionFeaturePermissionDao);
    }
}
