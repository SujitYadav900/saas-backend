package com.whatsappbuisness.wsbuissness.CombinePackadge.CountryWisePriceDaoList;

import java.util.List;
public interface CountryWisePriceDaoListService {

    CountryWisePriceListDao getCountryPrice(String country);
    List<CountryWisePriceListDao> getAll();
    void refreshCountryHashmap();

}
