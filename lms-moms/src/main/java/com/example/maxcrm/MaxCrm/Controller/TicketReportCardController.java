package com.example.maxcrm.MaxCrm.Controller;

import com.example.maxcrm.MaxCrm.Dao.TicketReportCardDao;
import com.example.maxcrm.MaxCrm.Dao.UserMasterDao;
import com.example.maxcrm.MaxCrm.Service.TicketReportCardService;
import com.example.maxcrm.MaxCrm.Utility.UtilityClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/api/ticketreportcard")
public class TicketReportCardController {


    @Autowired
    private TicketReportCardService service;

    @GetMapping("/getreportcard")
    public List<TicketReportCardDao> getTicketReportCard(@RequestParam String userid,
                                                         @RequestParam String startdate,
                                                         @RequestParam String enddate,
                                                         Authentication auth) throws SQLException {
        UserMasterDao user = (UserMasterDao) auth.getPrincipal();
        if(userid.equals("0")){
            userid = UtilityClass.userlist.get(user.getId());
        }

        return service.getTicketReport(userid, startdate, enddate);

    }
}
