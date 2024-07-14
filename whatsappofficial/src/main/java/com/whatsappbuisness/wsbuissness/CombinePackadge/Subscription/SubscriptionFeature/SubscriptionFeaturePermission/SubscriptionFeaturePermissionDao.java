package com.whatsappbuisness.wsbuissness.CombinePackadge.Subscription.SubscriptionFeature.SubscriptionFeaturePermission;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@Table(name = "Tbl_Subscription_Feature_Permission")
@IdClass(SubcriptionFeaturePermissionComponsite.class)
public class SubscriptionFeaturePermissionDao {

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

    @Override
    public String toString() {
        return "SubscriptionFeaturePermissionDao{" +
                "accountId=" + accountId +
                ", subFeatureId=" + subFeatureId +
                '}';
    }
    @Id
    private int accountId;
    @Id
    private int subFeatureId;
}
