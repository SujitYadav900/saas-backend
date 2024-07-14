package com.whatsappbuisness.wsbuissness.CombinePackadge.Features.DirectChatFeature.UserAsignDao;

import java.io.Serializable;

public class UserCompositeKeyDao implements Serializable {
    @Override
    public String toString() {
        return "UserCompositeKeyDao{" +
                "accountId=" + accountId +
                ", userId=" + userId +
                '}';
    }

    public long getAccountId() {
        return accountId;
    }

    public void setAccountId(long accountId) {
        this.accountId = accountId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    private long accountId;
    private long userId;

}
