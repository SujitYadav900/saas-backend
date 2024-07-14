package com.example.maxcrm.MaxCrm.Controller;

import com.example.maxcrm.MaxCrm.Dao.LeadTicketStatusDao;
import com.example.maxcrm.MaxCrm.Service.LeadTicketStatusService;
import com.example.maxcrm.MaxCrm.Utility.UtilityClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/leadticketstatus")
public class LeadTicketStatusController {

    @Autowired
    private LeadTicketStatusService service;

    @GetMapping("/getall")
    public Iterable<LeadTicketStatusDao> getall(){
        return  service.findAll();
    }

    @GetMapping("/getallactive")
    public Iterable<LeadTicketStatusDao> getallactive(){
        return  service.findAllActive();
    }

    @PostMapping("/insert")
    public LeadTicketStatusDao insert(@RequestBody LeadTicketStatusDao dao) throws Exception {
        dao.setCreateat(UtilityClass.dateFilterDate());
        return  service.insert(dao);
    }

    @PostMapping("/update")
    public LeadTicketStatusDao update(@RequestBody LeadTicketStatusDao dao) throws Exception {
        return  service.update(dao);
    }

    @DeleteMapping("/delete")
    public void insert(@RequestParam("id") int id){
        service.delete(id);
    }

    @GetMapping("/getallbyvalue")
    public LeadTicketStatusDao getallbyvalue(@RequestParam String name){
        return service.findAllActiveByValue(name);
    }

}
