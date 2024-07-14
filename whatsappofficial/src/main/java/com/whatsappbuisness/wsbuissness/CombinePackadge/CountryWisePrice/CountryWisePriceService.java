package com.whatsappbuisness.wsbuissness.CombinePackadge.CountryWisePrice;

import java.util.List;

public interface CountryWisePriceService {
    /**
     * will initiaze price will automatically create ratelist from a 0 account
     * @param accountId
     */
    void initialize(long accountId);

    /**
     * will get from cachhe
     * @param countryWisePricePk

     * @return check enum for return status
     */
    CountryWiseRateRetDao get(CountryWisePricePk countryWisePricePk);

    /**
     * update function
     * @param countryWisePriceDao
     * @return
     */
    CountryWisePriceDao update(CountryWisePriceDao countryWisePriceDao);

    /**
     * This function will refresh cache in the application
     */
    void refreshCache();

    List<CountryWisePriceDao> getByAccountId(long accountId);





    void delete(CountryWisePriceDao countryWisePriceDao);
}
