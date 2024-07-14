package com.whatsappbuisness.wsbuissness.CombinePackadge.TokenService;

import java.io.Serializable;

public class TokenCompositeKey implements Serializable {
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

    private String accessToken;


    private int accountId;


}
