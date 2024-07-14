package com.whatsappbuisness.wsbuissness.CombinePackadge.TokenStore;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Tbl_Token")
public class TokenStoreDao {
    @Override
    public String toString() {
        return "TokenStoreDao{" +
                "token='" + token + '\'' +
                ", accountId=" + accountId +
                ", userId=" + userId +
                ", lastCreateBy='" + lastCreateBy + '\'' +
                ", createAt='" + createAt + '\'' +
                '}';
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
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

    public String getLastCreateBy() {
        return lastCreateBy;
    }

    public void setLastCreateBy(String lastCreateBy) {
        this.lastCreateBy = lastCreateBy;
    }

    public String getCreateAt() {
        return createAt;
    }

    public void setCreateAt(String createAt) {
        this.createAt = createAt;
    }

    @Id
    private String token;
    private long accountId;
    private long userId;
    private String lastCreateBy;
    private String createAt;


}
