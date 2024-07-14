package com.example.maxcrm.MaxCrm.Controller;

import com.example.maxcrm.MaxCrm.Dao.ReportAllTicketDao;
import com.example.maxcrm.MaxCrm.Dao.UserMasterDao;
import com.example.maxcrm.MaxCrm.Service.ReportAllTicketService;
import com.example.maxcrm.MaxCrm.Utility.UtilityClass;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/reportallticket")
public class ReportAllTicketController {

    Logger loger  = LoggerFactory.getLogger(ReportAllTicketController.class);
    @Autowired
    private ReportAllTicketService reportAllTicketService;

    @GetMapping("/getreport")
    public List<ReportAllTicketDao> getreport(@RequestParam("flag") int flag,
                                              @RequestParam("searchvalue") String searchvalue,
                                              @RequestParam("startdate") String startdate,
                                              @RequestParam("enddate") String enddate,
                                              @RequestParam("userlist") String userlist,
                                              Authentication auth) throws Exception {
        UserMasterDao user = (UserMasterDao) auth.getPrincipal();
        if(userlist.equalsIgnoreCase("0")){
            userlist = UtilityClass.userlist.get(user.getId());
        }

        System.out.println(startdate+" "+enddate);
        loger.info("start end {} {}",startdate,enddate);
        return reportAllTicketService.getReport(flag,searchvalue,startdate,enddate,userlist);
    }

}
