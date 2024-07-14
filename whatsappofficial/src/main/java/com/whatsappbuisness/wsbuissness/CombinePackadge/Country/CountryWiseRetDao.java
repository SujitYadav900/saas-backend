package com.whatsappbuisness.wsbuissness.CombinePackadge.Country;

import com.whatsappbuisness.wsbuissness.CombinePackadge.CountryWisePrice.StatusDao;

public class CountryWiseRetDao {

    private StatusDao status;
    private CountryDao countryDao;

    public CountryWiseRetDao(StatusDao status, CountryDao countryDao) {
        this.status = status;
        this.countryDao = countryDao;
    }

    public StatusDao getStatus() {
        return status;
    }

    public void setStatus(StatusDao status) {
        this.status = status;
    }

    public CountryDao getCountryDao() {
        return countryDao;
    }

    public void setCountryDao(CountryDao countryDao) {
        this.countryDao = countryDao;
    }

    @Override
    public String toString() {
        return "CountryWiseRetDao{" +
                "status=" + status +
                ", countryDao=" + countryDao +
                '}';
    }
}
