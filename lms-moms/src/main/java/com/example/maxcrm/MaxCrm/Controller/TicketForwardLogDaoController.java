package com.example.maxcrm.MaxCrm.Controller;

import com.example.maxcrm.MaxCrm.Dao.TicketForwardLogDao;
import com.example.maxcrm.MaxCrm.Service.TicketForwardLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/ticketforwardlog")
public class TicketForwardLogDaoController {

    @Autowired
    TicketForwardLogService service;






    @GetMapping("/getall")
    public Iterable<TicketForwardLogDao> findall(@RequestParam long id){
        return service.findAll(id);
    }
}
