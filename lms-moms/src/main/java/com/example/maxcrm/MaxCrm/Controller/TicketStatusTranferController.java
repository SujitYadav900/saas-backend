package com.example.maxcrm.MaxCrm.Controller;

import com.example.maxcrm.MaxCrm.Dao.TicketUpdateLogDao;
import com.example.maxcrm.MaxCrm.Service.TicketUpdateLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/ticketststransfer")
public class TicketStatusTranferController {

    @Autowired
    TicketUpdateLogService service;






    @GetMapping("/getall")
    public Iterable<TicketUpdateLogDao> getall(@RequestParam long id){
        return service.findAll(id);
    }



}
