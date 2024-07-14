package com.example.maxcrm.MaxCrm.Controller;

import com.example.maxcrm.MaxCrm.Dao.StateDao;
import com.example.maxcrm.MaxCrm.Service.StateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/state")
public class StateController {
    @Autowired
    StateService stateService;

    @GetMapping("/getall")
    public Iterable<StateDao> findAll() {
        return stateService.findAll();
    }
    @GetMapping("/getallbycountry")
    public Iterable<StateDao> getallbycountry(@RequestParam("country")String country) {
        return stateService.findByCountry(country);
    }

    @PostMapping("/insert")
    public StateDao insert(@RequestBody StateDao stateDao) {
        return stateService.insert(stateDao);
    }
    @PostMapping("/update")
    public StateDao update(@RequestBody StateDao stateDao) {
        return stateService.update(stateDao);
    }
    @DeleteMapping("/delete")
    public void delete(@RequestParam("state") String state) {
         stateService.delete(state);
    }
}
