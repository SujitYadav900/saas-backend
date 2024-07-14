package com.whatsappbuisness.wsbuissness.CombinePackadge.SubscriptionPackageTransaction;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface SubscriptionPackageTransactionRepo extends MongoRepository<SubscriptionPackageTransactionDao,String> {

    List<SubscriptionPackageTransactionDao> findByAccountId(long accountId);
    SubscriptionPackageTransactionDao findByAccountIdAndStatus(long accountId, boolean status);

}
