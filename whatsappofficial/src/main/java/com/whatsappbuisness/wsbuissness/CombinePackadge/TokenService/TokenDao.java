package com.whatsappbuisness.wsbuissness.CombinePackadge.TokenService;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity

@Table(name = "Tbl_Token")
public class TokenDao {
    @Override
    public String toString() {
        return "TokenDao{" +
                "accessToken='" + accessToken + '\'' +
                ", accountId=" + accountId +
                ", generateBy=" + generateBy +
                ", generateAt='" + generateAt + '\'' +
                '}';
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public int getGenerateBy() {
        return generateBy;
    }

    public void setGenerateBy(int generateBy) {
        this.generateBy = generateBy;
    }

    public String getGenerateAt() {
        return generateAt;
    }

    public void setGenerateAt(String generateAt) {
        this.generateAt = generateAt;
    }

    private String accessToken;
    @Id
    private int accountId;
    private int generateBy;
    private String generateAt;


}
