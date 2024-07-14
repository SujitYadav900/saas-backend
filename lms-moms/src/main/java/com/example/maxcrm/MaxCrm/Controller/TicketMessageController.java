package com.example.maxcrm.MaxCrm.Controller;

import com.example.maxcrm.MaxCrm.Dao.TicketMessageDao;
import com.example.maxcrm.MaxCrm.Dao.UserMasterDao;
import com.example.maxcrm.MaxCrm.Service.NotificationService;
import com.example.maxcrm.MaxCrm.Service.TicketMessageService;
import com.example.maxcrm.MaxCrm.Utility.UtilityClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/api/ticketmessage")
public class TicketMessageController {

    @Autowired
    private TicketMessageService service;

    @Autowired
    private NotificationService notificationService;


    @GetMapping("/getallbyticket")
    public List<TicketMessageDao> getallbyticketid(@RequestParam long id, @RequestParam int offset, @RequestParam int limit) {
        return service.findAllByTicketId(id, offset, limit);
    }

    @PostMapping("/insert")
    public TicketMessageDao insert(@RequestBody TicketMessageDao dao, Authentication auth) throws SQLException {
        UserMasterDao user = (UserMasterDao) auth.getPrincipal();
        dao.setSender(user.getUsername());
        dao.setSenddate(UtilityClass.getDateRedable());
        TicketMessageDao ticketMessageDao = service.insert(dao);


        return ticketMessageDao;
    }


    @DeleteMapping("/delete")
    public void delete(@RequestParam int id) {
        service.delete(id);
    }
}
