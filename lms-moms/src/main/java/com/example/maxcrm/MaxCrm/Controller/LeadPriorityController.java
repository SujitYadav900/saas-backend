package com.example.maxcrm.MaxCrm.Controller;

import com.example.maxcrm.MaxCrm.Dao.LeadPriorityDao;
import com.example.maxcrm.MaxCrm.Service.LeadPriorityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/leadpriority")
public class LeadPriorityController {
    @Autowired
    LeadPriorityService leadPriorityService;

    @GetMapping("/getall")
    public Iterable<LeadPriorityDao> findAll() {
        return leadPriorityService.findAll();
    }

    @GetMapping("/getactive")
    public Iterable<LeadPriorityDao> findActive() {
        return leadPriorityService.findActive();
    }

    @PostMapping("/insert")
    public LeadPriorityDao insert(@RequestBody LeadPriorityDao leadPriorityDao) throws Exception {
        return leadPriorityService.insert(leadPriorityDao);
    }

    @PostMapping("/update")
    public LeadPriorityDao update(@RequestBody LeadPriorityDao leadPriorityDao) throws Exception {
        return leadPriorityService.update(leadPriorityDao);
    }

    @DeleteMapping("/delete")
    public void update(@RequestParam("id") int id) {
        leadPriorityService.delete(id);
    }

}
