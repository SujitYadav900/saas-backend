package com.whatsappbuisness.wsbuissness.CombinePackadge.Subscription.SubscriptionFeature;

public class SubscriptionFeatureDao {


    @Override
    public String toString() {
        return "SubscriptionFeatureDao{" +
                "accountId=" + accountId +
                ", featureName='" + featureName + '\'' +
                ", sts=" + sts +
                '}';
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public String getFeatureName() {
        return featureName;
    }

    public void setFeatureName(String featureName) {
        this.featureName = featureName;
    }

    public boolean isSts() {
        return sts;
    }

    public void setSts(boolean sts) {
        this.sts = sts;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private int id;
    private int accountId;
    private String featureName;
    private boolean sts;


}
