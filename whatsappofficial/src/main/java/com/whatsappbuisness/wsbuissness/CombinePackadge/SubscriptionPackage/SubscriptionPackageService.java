package com.whatsappbuisness.wsbuissness.CombinePackadge.SubscriptionPackage;

import java.util.List;

public interface SubscriptionPackageService {
    SubScriptionPackageDao insert(SubScriptionPackageDao subScriptionPackageDao);

    List<SubScriptionPackageDao> getByDateAndStatus(long accountId, String startDate, String endDate, boolean status);

    SubScriptionPackageDao getBYId(int id);

    SubScriptionPackageDao addSubscriptionPackageToClient(SubScriptionPackageDao subScriptionPackageDao);

}
