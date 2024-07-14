package com.example.maxcrm.MaxCrm.MBOPSRegistration.states;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/mbops/states")
public class MBOPSStatesController {

    @Autowired
    private MBOPSStateService service;

    @PostMapping("/insert")
    public MBOPSStateDao insert(@RequestBody MBOPSStateDao dao){
        return service.insert(dao);
    }

    @PostMapping("/update")
    public MBOPSStateDao update(@RequestBody MBOPSStateDao dao){
        return service.update(dao);
    }

    @DeleteMapping("/delete")
    public void delete(@RequestParam int id){
        service.delete(id);
    }

    @GetMapping("/getall")
    public List<MBOPSStateDao> getAll(){
        return service.getAll();
    }

    @GetMapping("/getallbycountryid")
    public List<MBOPSStateDao> getallbycountryid(@RequestParam int countryid){
        return service.findByCountryId(countryid);
    }
}
