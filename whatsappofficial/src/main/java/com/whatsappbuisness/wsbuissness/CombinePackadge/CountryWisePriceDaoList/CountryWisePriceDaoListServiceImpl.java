package com.whatsappbuisness.wsbuissness.CombinePackadge.CountryWisePriceDaoList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class CountryWisePriceDaoListServiceImpl implements CountryWisePriceDaoListService{

    public static HashMap<String, CountryWisePriceListDao> countryHashmapByCountryCode = new HashMap<>();
    public static HashMap<String, CountryWisePriceListDao> countryHashmapByNumberCode = new HashMap<>();

    @Autowired
    CountryWisePriceDaoListRepo countryWisePriceDaoListRepo;
    @Override
    public CountryWisePriceListDao getCountryPrice(String country) {
        return countryWisePriceDaoListRepo.findByCountryCode(country);
    }

    @Override
    public List<CountryWisePriceListDao> getAll() {
        return countryWisePriceDaoListRepo.findAll();
    }

    @Override
    public void refreshCountryHashmap() {
        List<CountryWisePriceListDao> countryWisePriceList = countryWisePriceDaoListRepo.findAll();
        for (CountryWisePriceListDao countryWisePriceListDao: countryWisePriceList) {
            countryHashmapByCountryCode.put(countryWisePriceListDao.getCountryCode(),countryWisePriceListDao);
            countryHashmapByNumberCode.put(countryWisePriceListDao.getNumberCode(), countryWisePriceListDao);
        }
    }
}
