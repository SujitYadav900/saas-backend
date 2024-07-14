package com.whatsappbuisness.wsbuissness.CombinePackadge.CountryWisePriceDaoList;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CountryWisePriceDaoListRepo extends JpaRepository<CountryWisePriceListDao,Integer> {

    CountryWisePriceListDao findByCountryCode(String countryCode);
}
