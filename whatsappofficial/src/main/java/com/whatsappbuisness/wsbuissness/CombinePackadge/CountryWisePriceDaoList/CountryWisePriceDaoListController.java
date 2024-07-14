package com.whatsappbuisness.wsbuissness.CombinePackadge.CountryWisePriceDaoList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/countrywisepricelist")
public class CountryWisePriceDaoListController {

    @Autowired
    CountryWisePriceDaoListService countryWisePriceDaoListService;

    @GetMapping("/")
        CountryWisePriceListDao get(@RequestParam("countryCode") String countryCode){
        return countryWisePriceDaoListService.getCountryPrice(countryCode);
        }
    @GetMapping("/getall")
    List<CountryWisePriceListDao> getAll(){
        return countryWisePriceDaoListService.getAll();
    }

    @GetMapping("/refresh")
    void refreshCountryHashmap(){countryWisePriceDaoListService.refreshCountryHashmap();}
}
