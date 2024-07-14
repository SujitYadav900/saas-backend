package com.example.maxcrm.MaxCrm.Controller;

import com.example.maxcrm.MaxCrm.Dao.CityDao;
import com.example.maxcrm.MaxCrm.Service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/city")
public class CityController {
    @Autowired
    CityService cityService;

    @GetMapping("/getall")
    public Iterable<CityDao> findAll() {
        return cityService.findAll();
    }

    @GetMapping("/getallbyState")
    public Iterable<CityDao> findAllBystate(@RequestParam("state") String state) {
        return cityService.findByState(state);
    }

    @PostMapping("/insert")
    public CityDao insert(@RequestBody CityDao cityDao) {
        return cityService.insert(cityDao);
    }

    @PostMapping("/update")
    public CityDao update(@RequestBody CityDao cityDao) {
        return cityService.update(cityDao);
    }

    @DeleteMapping("/delete")
    public void delete(@RequestParam("city") String city) {
        cityService.delete(city);
    }
}
