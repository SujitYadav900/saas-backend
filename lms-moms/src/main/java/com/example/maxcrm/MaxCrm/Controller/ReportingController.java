package com.example.maxcrm.MaxCrm.Controller;

import com.example.maxcrm.MaxCrm.Dao.ReportingDao;
import com.example.maxcrm.MaxCrm.Service.ReportingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/userreporting")
public class ReportingController {
    @Autowired
    ReportingService reportingService;

    @GetMapping("/getbyuserid")
    public Iterable<ReportingDao> findByUserId(@RequestParam("id")int id)
    {
        return reportingService.findByUserId(id);
    }

    @PostMapping("/insert")
    public ReportingDao insert(@RequestBody ReportingDao reportingDao)
    {
        return reportingService.insert(reportingDao);
    }

    @PostMapping("/update")
    public ReportingDao update(@RequestBody ReportingDao reportingDao)
    {
        return reportingService.update(reportingDao);
    }
    @DeleteMapping("/delete")
    public void update(@RequestParam("id")long id)
    {
         reportingService.delete(id);
    }
}
