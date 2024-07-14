package com.whatsappbuisness.wsbuissness.CombinePackadge.CountryWisePrice;

import com.whatsappbuisness.wsbuissness.CombinePackadge.Common.SessionRetrival.SessionRetrievalService;
import com.whatsappbuisness.wsbuissness.CombinePackadge.Common.SessionRetrival.UsermasterDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/countrywise")
public class CountryWiseController {
    @Autowired
    SessionRetrievalService sessionRetrievalService;
    @Autowired
    CountryWisePriceService countryWisePriceService;

    @GetMapping("/{accountId}")

    public List<CountryWisePriceDao> get(@PathVariable("accountId")long acountId) {


        return countryWisePriceService.getByAccountId(acountId);
    }

    @PostMapping("/")
    public CountryWisePriceDao save(@RequestBody CountryWisePriceDao countryWisePriceDao) {
        return countryWisePriceService.update(countryWisePriceDao);
    }
    @PostMapping("/delete")
    public void delete(@RequestBody CountryWisePriceDao countryWisePriceDao) {
        countryWisePriceService.delete(countryWisePriceDao);
    }


}
