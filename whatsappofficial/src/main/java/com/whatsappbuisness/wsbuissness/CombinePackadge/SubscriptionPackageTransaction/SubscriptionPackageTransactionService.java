package com.whatsappbuisness.wsbuissness.CombinePackadge.SubscriptionPackageTransaction;

import com.whatsappbuisness.wsbuissness.CombinePackadge.Filtering.FilterDao;

import java.util.List;

public interface SubscriptionPackageTransactionService {

    SubscriptionPackageTransactionDao insert(SubscriptionPackageTransactionDao subscriptionPackageTransactionDao);

    List<SubscriptionPackageTransactionDao> get(FilterDao filterDao);

    void changeAccountSubscriptionStatus(long accountId, boolean status);

}
