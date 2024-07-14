package com.example.maxcrm.MaxCrm.MBOPSRegistration.clinicalDomains;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/mbops/clinicaldomain")
public class MBOPSClinicalDomainController {

    @Autowired
    private MBOPSClinicalDomainService domainService;

    @GetMapping("/insert")
    MBOPSClinicalDomainDao insert(@RequestBody MBOPSClinicalDomainDao domainDao){
        return domainService.insert(domainDao);
    }

    @GetMapping("/update")
    MBOPSClinicalDomainDao update(@RequestBody MBOPSClinicalDomainDao domainDao){
        return domainService.update(domainDao);
    }

    @GetMapping("/delete")
    void delete(@RequestParam int id){
        domainService.delete(id);
    }

    @GetMapping("/findall")
    List<MBOPSClinicalDomainDao> findAll(){
        return domainService.getAll();
    }

    @GetMapping("/findbyname")
    MBOPSClinicalDomainDao findByName(@RequestParam String name){
        return domainService.findByName(name);
    }
}
