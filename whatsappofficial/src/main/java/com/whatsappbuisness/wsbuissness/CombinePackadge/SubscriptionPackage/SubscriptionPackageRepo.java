package com.whatsappbuisness.wsbuissness.CombinePackadge.SubscriptionPackage;

import org.springframework.data.mongodb.repository.MongoRepository;


public interface SubscriptionPackageRepo extends MongoRepository<SubScriptionPackageDao,String> {

    SubScriptionPackageDao findById(int id);
}
