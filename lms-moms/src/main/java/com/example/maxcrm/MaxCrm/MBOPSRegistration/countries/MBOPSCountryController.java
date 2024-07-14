package com.example.maxcrm.MaxCrm.MBOPSRegistration.countries;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/mbops/countries")
public class MBOPSCountryController {

    @Autowired
    private MBOPSCountryService service;

    @PostMapping("/insert")
    public MBOPSCountryDao insert(@RequestBody MBOPSCountryDao dao){
        return service.insert(dao);
    }

    @PostMapping("/update")
    public MBOPSCountryDao update(@RequestBody MBOPSCountryDao dao){
        return service.update(dao);
    }

    @DeleteMapping("/delete")
    public void delete(@RequestParam int id){
        service.delete(id);
    }

    @GetMapping("/getall")
    public List<MBOPSCountryDao> getAll(){
        return service.getAll();
    }

}
