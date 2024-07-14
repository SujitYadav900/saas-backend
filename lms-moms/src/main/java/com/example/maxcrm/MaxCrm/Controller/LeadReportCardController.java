package com.example.maxcrm.MaxCrm.Controller;

import com.example.maxcrm.MaxCrm.Dao.LeadReportCardDao;
import com.example.maxcrm.MaxCrm.Dao.UserMasterDao;
import com.example.maxcrm.MaxCrm.Service.LeadReportCardService;
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
@RequestMapping("/api/leadreportcard")
public class LeadReportCardController {

    @Autowired
    private LeadReportCardService service;


    @GetMapping("/getreportcard")
    public List<LeadReportCardDao> getthismonthreport(@RequestParam("userid") String id,
                                                      @RequestParam("startdate") String startdate,
                                                      @RequestParam("enddate") String enddate,
                                                      @RequestParam("dateTypeFlag") int dateTypeFlag,
                                                      Authentication auth) throws SQLException {

        UserMasterDao user = (UserMasterDao) auth.getPrincipal();
      if(id.equals("0")){
          id = UtilityClass.userlist.get(user.getId());
      }

        return service.getReport(id,startdate,enddate,dateTypeFlag);
    }

    @GetMapping("/getbusinessreport")
    public List<LeadReportCardDao> getthismonthbusiness(@RequestParam("userid") String id,
                                                      @RequestParam("startdate") String startdate,
                                                      @RequestParam("enddate") String enddate,
                                                        Authentication auth) throws SQLException {

        UserMasterDao user = (UserMasterDao) auth.getPrincipal();
        if(id.equals("0")){
            id = UtilityClass.userlist.get(user.getId());
        }

        return service.getBusiness(id,startdate,enddate);
    }


    //jsp > leadstats.jsp
    //shows only clientType and source stats (assigned and converted leads)
    @GetMapping("/getleadstats")
    public List<LeadReportCardDao> getleadstats( @RequestParam("userid") String id,
                                                 @RequestParam("startdate") String startdate,
                                                 @RequestParam("enddate") String enddate,
                                                 @RequestParam("isFilter") boolean isFilter,
                                                 @RequestParam("clientType") String clientType,
                                                 Authentication auth) throws SQLException {

        UserMasterDao user = (UserMasterDao) auth.getPrincipal();
        if(id.equals("0")){
            id = UtilityClass.userlist.get(user.getId());
        }

        return service.getLeadStats(startdate,enddate,isFilter,clientType,id);
    }


    //jsp > leadstatsuser.jsp
    //shows only clientType and source stats (assigned and converted leads)
    @GetMapping("/getleadstatsuser")
    public List<LeadReportCardDao> getleadstatsuser(@RequestParam("startdate") String startdate,@RequestParam("enddate") String enddate) throws SQLException {

       return service.getLeadStatsUser(startdate,enddate);
    }

    //jsp > leadstatsdetailed.jsp
    //shows only clientType and source stats (assigned and converted leads)
    @GetMapping("/getleadstatsdetailed")
    public List<LeadReportCardDao> getleadstatsdetailed(@RequestParam("startdate") String startdate,
                                                        @RequestParam("enddate") String enddate,
                                                        @RequestParam("flag") String flag,
                                                        @RequestParam("dateflag") String dateflag,
                                                        @RequestParam(value = "clientTypeFlag",required = false) String clientTypeFlag) throws SQLException {

        //clientTypeFlag is only set from 'Lead Stats Date Wise' else is null
        if (clientTypeFlag == null){
            clientTypeFlag = "none";//doesnt matter set any value
        }
        return service.getLeadStatsDetailed(startdate,enddate,flag,dateflag,clientTypeFlag);
    }

    //jsp > leadstatsappointmentdate.jsp
    //shows only appointment date  and count per date (base on clinicalCallTime)
    @GetMapping("/getleadstatsappointmentdate")
    public List<LeadReportCardDao> getleadstatsappointmentdate(@RequestParam("startdate") String startdate,@RequestParam("enddate") String enddate) throws SQLException {

        return service.getLeadStatsAppointmentDate(startdate,enddate);
    }

    //jsp > leadstatsappointmentdate.jsp
    //shows only appointment date  and count per date (base on clinicalCallTime)
    @GetMapping("/gettransferstats")
    public List<LeadReportCardDao> getTransfereStats(@RequestParam("startdate") String startdate,@RequestParam("enddate") String enddate,@RequestParam("username") String username) throws SQLException {

        return service.getTransferStats(startdate,enddate,username);
    }

}
