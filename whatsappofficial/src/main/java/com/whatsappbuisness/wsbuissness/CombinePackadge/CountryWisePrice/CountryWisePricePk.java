package com.whatsappbuisness.wsbuissness.CombinePackadge.CountryWisePrice;

import java.io.Serializable;

public class CountryWisePricePk implements Serializable {
    public CountryWisePricePk(String countryCode, long accountId) {
        this.countryCode = countryCode;
        this.accountId = accountId;
    }

    public CountryWisePricePk() {
    }

    private String countryCode;
    private long accountId;
}
