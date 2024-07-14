package com.example.maxcrm.MaxCrm.Controller;

import com.example.maxcrm.MaxCrm.Dao.LeadTypeDao;
import com.example.maxcrm.MaxCrm.Service.LeadTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/leadtype")
public class LeadTypeController {
    @Autowired
    LeadTypeService leadTypeService;

    @GetMapping("/getall")
    public Iterable<LeadTypeDao> findAll() {
        return leadTypeService.findAll();
    }

    @GetMapping("/getactive")
    public Iterable<LeadTypeDao> findActive() {
        return leadTypeService.findActive();
    }

    @PostMapping("/insert")
    public LeadTypeDao insert(@RequestBody LeadTypeDao leadTypeDao) throws Exception {
        return leadTypeService.insert(leadTypeDao);
    }

    @PostMapping("/update")
    public LeadTypeDao update(@RequestBody LeadTypeDao leadTypeDao) throws Exception {
        return leadTypeService.update(leadTypeDao);
    }

    @DeleteMapping("/delete")
    public void update(@RequestParam("id") int id) {
        leadTypeService.delete(id);
    }

}
