package com.example.maxcrm.MaxCrm.Controller;

import com.example.maxcrm.MaxCrm.Dao.CountryDao;
import com.example.maxcrm.MaxCrm.Service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/country")
public class CountryController {
    @Autowired
    CountryService countryService;

    @GetMapping("/getall")
    public Iterable<CountryDao> getall() {
        return countryService.getAll();
    }
    @PostMapping("/insert")
    public CountryDao insert(@RequestBody CountryDao countryDao) {
        return countryService.insert(countryDao);
    }
    @PostMapping("/update")
    public CountryDao update(@RequestBody CountryDao countryDao) {
        return countryService.update(countryDao);
    }
    @DeleteMapping("/delete")
    public void delete(@RequestParam("id")String country)
    {
        countryService.delete(country);
    }

}
