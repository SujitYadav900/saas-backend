package com.whatsappbuisness.wsbuissness.CombinePackadge.Country;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/country")
public class CountryController {


    @Autowired
    CountryService countryService;

    @GetMapping("/")
    public Iterable<CountryDao> get() {
        return countryService.getAll();
    }

    @PostMapping("/")
    public CountryDao save(@RequestBody CountryDao countryDao) {
        return countryService.save(countryDao);
    }

}
