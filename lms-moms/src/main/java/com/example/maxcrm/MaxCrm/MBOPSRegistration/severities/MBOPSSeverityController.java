package com.example.maxcrm.MaxCrm.MBOPSRegistration.severities;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/mbops/severity")
public class MBOPSSeverityController {

    @Autowired
    private MBOPSSeverityService severityService;

    @GetMapping("/insert")
    MBOPSSeverityDao insert(@RequestBody MBOPSSeverityDao severityDao){
        return severityService.insert(severityDao);
    }

    @GetMapping("/update")
    MBOPSSeverityDao udpate(@RequestBody MBOPSSeverityDao severityDao){
        return severityService.update(severityDao);
    }

    @GetMapping("/delete")
    void delete(@RequestParam String name){
        severityService.delete(name);
    }

    @GetMapping("/findall")
    List<MBOPSSeverityDao> findAll(){
        return severityService.getAll();
    }

    @GetMapping("/findbyname")
    MBOPSSeverityDao findByName(@RequestParam String name){
        return severityService.findByName(name);
    }
}
