package com.whatsappbuisness.wsbuissness.CombinePackadge.CountryWisePrice;

import java.io.Serializable;

public class CountryWiseRateRetDao implements Serializable {
    @Override
    public String toString() {
        return "CountryWiseRateRetDao{" +
                "countryWisePriceDao=" + countryWisePriceDao +
                ", status=" + status +
                '}';
    }

    public CountryWisePriceDao getCountryWisePriceDao() {
        return countryWisePriceDao;
    }

    public CountryWiseRateRetDao(CountryWisePriceDao countryWisePriceDao, StatusDao status) {
        this.countryWisePriceDao = countryWisePriceDao;
        this.status = status;
    }

    public void setCountryWisePriceDao(CountryWisePriceDao countryWisePriceDao) {
        this.countryWisePriceDao = countryWisePriceDao;
    }

    public StatusDao getStatus() {
        return status;
    }

    public void setStatus(StatusDao status) {
        this.status = status;
    }

    private CountryWisePriceDao  countryWisePriceDao;
    private StatusDao status;
}
