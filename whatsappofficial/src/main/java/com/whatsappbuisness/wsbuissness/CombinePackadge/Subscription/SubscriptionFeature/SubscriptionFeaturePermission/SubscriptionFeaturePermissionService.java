package com.whatsappbuisness.wsbuissness.CombinePackadge.Subscription.SubscriptionFeature.SubscriptionFeaturePermission;

import java.util.List;

public interface SubscriptionFeaturePermissionService {
    List<SubscriptionFeaturePermissionDao> get(int accountId);
    void delete(SubscriptionFeaturePermissionDao subscriptionFeaturePermissionDao);
}
