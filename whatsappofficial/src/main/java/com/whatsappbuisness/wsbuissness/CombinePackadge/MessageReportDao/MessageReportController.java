package com.whatsappbuisness.wsbuissness.CombinePackadge.MessageReportDao;

import com.whatsappbuisness.wsbuissness.CombinePackadge.Common.SessionRetrival.SessionRetrievalService;
import com.whatsappbuisness.wsbuissness.CombinePackadge.Common.SessionRetrival.UsermasterDao;
import com.whatsappbuisness.wsbuissness.CombinePackadge.Datetiming.DateTiming;
import com.whatsappbuisness.wsbuissness.CombinePackadge.Filtering.FilterDao;
import com.whatsappbuisness.wsbuissness.CombinePackadge.Messages.MessageDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/*
 Author=Supreet Singh
 Date= 11/03/21 11:20 AM
*/
@RestController
@RequestMapping("/messagereport")
public class MessageReportController {
    @Autowired
    MessageReportService messageReportService;
    @Autowired
    SessionRetrievalService sessionRetrievalService;
    @PostMapping("/get")
    public List<MessageReportDao> getAll(@RequestBody FilterDao filterDao, Authentication authentication) {
        UsermasterDao usermasterDao=sessionRetrievalService.get(authentication);
        filterDao.setAccountId(usermasterDao.getAccountId());
        filterDao = DateTiming.validateFilterDao(filterDao);
        return messageReportService.getAllByDate(filterDao);
    }

    @GetMapping("getdlr")
    public List<CommonGroupQueryDao> getReportBetweenDate(@RequestParam("startdate") int startdate,
                                                                                                 @RequestParam("enddate") int enddate,
                                                                                                 @RequestParam(name = "chatside", required = false) String chatside,
                                                                                                  @RequestParam(name = "type" , required = false) String type,
                                                                                                    @RequestParam(name="reportBy",required = false) byte reportBy,
                                                                                                    Authentication authentication  ){
        UsermasterDao usermasterDao=sessionRetrievalService.get(authentication);

        return messageReportService.getReportBetweenDate(startdate,enddate,chatside,type,usermasterDao.getAccountId(),reportBy);
    }



    @GetMapping("/getreport")
    public List<MessageDao> reportByAccountGroup(@RequestParam("startdate") int startdate,
                                                               @RequestParam("enddate") int enddate,
                                                               @RequestParam(name = "chatside", required = false) String chatside,
                                                               @RequestParam(name = "type" , required = false) String type,
                                                                @RequestParam(name="reportBy",required = false) byte reportBy,
                                                 Authentication authentication       ) {
        UsermasterDao usermasterDao = sessionRetrievalService.get(authentication);
        return messageReportService.reportByAccountGroup(startdate,enddate,chatside,type,usermasterDao.getAccountId(),reportBy);
    }

//    @GetMapping("/getreport")
//    public List<MessageDao> reportByAccountGroup(@RequestParam("startdate") int startdate,
//                                                 @RequestParam("enddate") int enddate,
//                                                 @RequestParam(name = "chatside", required = false) String chatside,
//                                                 @RequestParam(name = "type" , required = false) String type,
//                                                 Authentication authentication       ) {
//        UsermasterDao usermasterDao = sessionRetrievalService.get(authentication);
//        return messageReportService.reportByAccountGroup(startdate,enddate,chatside,type,usermasterDao.getAccountId());
//    }

}
