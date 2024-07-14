package com.whatsappbuisness.wsbuissness.CombinePackadge.CountryWisePrice;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class CountryWisePriceServiceTest {
    @Autowired
    CountryWisePriceService countryWisePriceService;
    private static final Logger logger= LoggerFactory.getLogger(CountryWisePriceServiceTest.class);

    @Test
    void initialize() {
    }

    @Test
    void get() {
      CountryWiseRateRetDao countryWiseRateRetDao=  countryWisePriceService.get(new CountryWisePricePk("91",1));
        logger.info("Country {} ",countryWiseRateRetDao);
        countryWiseRateRetDao=  countryWisePriceService.get(new CountryWisePricePk("92",1));
        logger.info("Country {} ",countryWiseRateRetDao);
    }

    @Test
    void update() {
    }

    @Test
    void refreshCache() {
    }

    @Test
    void getByAccountId() {
        List<CountryWisePriceDao> data =countryWisePriceService.getByAccountId(1);
        logger.info("data {} ",data);
    }
}