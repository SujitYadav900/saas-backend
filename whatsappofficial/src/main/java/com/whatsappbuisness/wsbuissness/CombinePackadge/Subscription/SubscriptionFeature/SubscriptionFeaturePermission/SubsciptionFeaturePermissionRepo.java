package com.whatsappbuisness.wsbuissness.CombinePackadge.Subscription.SubscriptionFeature.SubscriptionFeaturePermission;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SubsciptionFeaturePermissionRepo extends CrudRepository<SubscriptionFeaturePermissionDao,SubcriptionFeaturePermissionComponsite> {


    List<SubscriptionFeaturePermissionDao>    getAllByAccountId(long accountId);




}
