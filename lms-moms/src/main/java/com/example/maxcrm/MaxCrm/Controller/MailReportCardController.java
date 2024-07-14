package com.example.maxcrm.MaxCrm.Controller;

import com.example.maxcrm.MaxCrm.Dao.MailReportCardDao;
import com.example.maxcrm.MaxCrm.Service.MailReportCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/mailreportcard")
public class MailReportCardController {

    @Autowired
    private MailReportCardService service;

    @GetMapping("/getreport")
    public List<MailReportCardDao> getReport(@RequestParam("startdate") int startdate,
                                             @RequestParam("enddate") int enddate){
        return service.getReportCard(startdate, enddate);
    }
}
