package com.whatsappbuisness.wsbuissness.CombinePackadge.Features;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Tbl_Feature_Dao")
public class FeatureDao {

    @Override
    public String toString() {
        return "FeatureDao{" +
                "accountId=" + accountId +
                ", directChatEnabled=" + directChatEnabled +
                '}';
    }

    public long getAccountId() {
        return accountId;
    }

    public void setAccountId(long accountId) {
        this.accountId = accountId;
    }

    public boolean isDirectChatEnabled() {
        return directChatEnabled;
    }

    public void setDirectChatEnabled(boolean directChatEnabled) {
        this.directChatEnabled = directChatEnabled;
    }

    @Id
    private long accountId;
    private boolean directChatEnabled;




}
