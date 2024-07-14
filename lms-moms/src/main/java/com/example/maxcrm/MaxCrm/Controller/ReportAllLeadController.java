package com.example.maxcrm.MaxCrm.Controller;

import com.example.maxcrm.MaxCrm.Dao.ReportAllLeadDao;
import com.example.maxcrm.MaxCrm.Dao.UserMasterDao;
import com.example.maxcrm.MaxCrm.Service.ReportAllLeadService;
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
@RequestMapping("/api/reportalllead")
public class ReportAllLeadController {

    Logger loger  = LoggerFactory.getLogger(ReportAllLeadController.class);
    @Autowired
    private ReportAllLeadService reportAllLeadService;

    @GetMapping("/getreport")
    public List<ReportAllLeadDao> getreport(@RequestParam("flag") int flag,
                                            @RequestParam("searchvalue") String searchvalue,
                                            @RequestParam(name ="clienttypevalue", required = false,defaultValue = "0") String clientTypeValue,
                                            @RequestParam("startdate") String startdate,
                                            @RequestParam("enddate") String enddate,
                                            @RequestParam("userlist") String userlist,
                                            @RequestParam("dateTypeFlag") int dateTypeFlag,
                                            @RequestParam(name = "userflag",required = false,defaultValue = "0") int userflag,
                                            Authentication auth) throws Exception {


        //userflag = 0,for one user
        //userflag = 1,for user's team

        UserMasterDao user = (UserMasterDao) auth.getPrincipal();
        if(userflag == 0){
            if(userlist.equalsIgnoreCase("0")){
                userlist = UtilityClass.userlist.get(user.getId());
            }
        }else{
            userlist = UtilityClass.userlist.get(Integer.parseInt(userlist));
        }
        return reportAllLeadService.getReport(flag,searchvalue,clientTypeValue,startdate,enddate,userlist,dateTypeFlag);
    }

}
