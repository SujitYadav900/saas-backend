package com.whatsappbuisness.wsbuissness.CombinePackadge.Country;


import com.whatsappbuisness.wsbuissness.CombinePackadge.Messages.ChatSide;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class CountryServiceTest {
    @Autowired
    CountryService countryService;
    private static final Logger logger= LoggerFactory.getLogger(CountryServiceTest.class);
    @Test
    void findByCode() {
        CountryWiseRetDao countryWiseRetDao=countryService.findByCode("929999420594", ChatSide.
                Client);
        //logger.info("Ret {}",countryWiseRetDao);
         countryWiseRetDao=countryService.findByCode("919999420594", ChatSide.
                Client);
    }
}