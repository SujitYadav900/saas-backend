package com.example.maxcrm.MaxCrm.Controller;

import com.example.maxcrm.MaxCrm.Dao.TicketTypeDao;
import com.example.maxcrm.MaxCrm.Service.TicketTypeService;
import com.example.maxcrm.MaxCrm.Utility.UtilityClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/tickettype")
public class TicketTypeController {

    @Autowired
    private TicketTypeService service;

    @PostMapping("/insert")
    public TicketTypeDao insert(@RequestBody TicketTypeDao dao) throws Exception {
        dao.setCreateat(UtilityClass.dateFilterDate());
        return service.insert(dao);
    }

    @PostMapping("/update")
    public TicketTypeDao update(@RequestBody TicketTypeDao dao) throws Exception {
        return service.insert(dao);
    }

    @DeleteMapping("/delete")
    public void delete(@RequestParam int id){
        service.delete(id);
    }

    @GetMapping("/getall")
    public Iterable<TicketTypeDao> getall(){
        return service.getAll();
    }

    @GetMapping("/getallactive")
    public Iterable<TicketTypeDao> getallactive(){
        return service.getAllActive();
    }

}
