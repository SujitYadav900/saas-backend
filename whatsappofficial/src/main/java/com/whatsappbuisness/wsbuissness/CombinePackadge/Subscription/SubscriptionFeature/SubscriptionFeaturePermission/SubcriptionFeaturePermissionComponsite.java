package com.whatsappbuisness.wsbuissness.CombinePackadge.Subscription.SubscriptionFeature.SubscriptionFeaturePermission;

import java.io.Serializable;

public class SubcriptionFeaturePermissionComponsite implements Serializable {
    @Override
    public String toString() {
        return "SubcriptionFeaturePermissionComponsite{" +
                "accountId=" + accountId +
                ", subFeatureId=" + subFeatureId +
                '}';
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public int getSubFeatureId() {
        return subFeatureId;
    }

    public void setSubFeatureId(int subFeatureId) {
        this.subFeatureId = subFeatureId;
    }

    private int accountId;
    private int subFeatureId;


}
