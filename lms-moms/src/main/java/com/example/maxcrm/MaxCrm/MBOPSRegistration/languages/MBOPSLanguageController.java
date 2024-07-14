package com.example.maxcrm.MaxCrm.MBOPSRegistration.languages;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/mbops/languages")
public class MBOPSLanguageController {


    @Autowired
    private MBOPSLanguageService service;

    @PostMapping("/insert")
    public MBOPSLanguageDao insert(@RequestBody MBOPSLanguageDao dao){
        return service.insert(dao);
    }

    @PostMapping("/update")
    public MBOPSLanguageDao update(@RequestBody MBOPSLanguageDao dao){
        return service.update(dao);
    }

    @DeleteMapping("/delete")
    public void delete(@RequestParam int id){
        service.delete(id);
    }

    @PostMapping("/getall")
    public List<MBOPSLanguageDao> getAll(){
        return service.getAll();
    }

}
