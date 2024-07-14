package com.example.maxcrm.MaxCrm.Controller;

import com.example.maxcrm.MaxCrm.Dao.LeadStatusDao;
import com.example.maxcrm.MaxCrm.Service.LeadStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/leadstatus")
public class LeadStatusController {
@Autowired
LeadStatusService leadStatusService;

    @GetMapping("/getall")
    public Iterable<LeadStatusDao> findAll()
    {
        return leadStatusService.findAll();
    }
    @GetMapping("/getactive")
    public Iterable<LeadStatusDao> findActive()
    {
        return leadStatusService.findActive();
    }
    @PostMapping("/insert")
    public LeadStatusDao insert(@RequestBody LeadStatusDao menuDao) throws Exception {
        return leadStatusService.insert(menuDao);
    }

    @PostMapping("/update")
    public LeadStatusDao update(@RequestBody LeadStatusDao menuDao) throws Exception {
        return leadStatusService.update(menuDao);
    }

    @DeleteMapping("/delete")
    public void update(@RequestParam("id")int id)
    {
        leadStatusService.delete(id);
    }

}
