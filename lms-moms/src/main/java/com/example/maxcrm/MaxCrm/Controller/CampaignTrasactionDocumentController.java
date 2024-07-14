package com.example.maxcrm.MaxCrm.Controller;

import com.example.maxcrm.MaxCrm.Dao.CampaignObjectDao;
import com.example.maxcrm.MaxCrm.Dao.PaginationMainDao;
import com.example.maxcrm.MaxCrm.Service.CampaignTransactionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/campaigntrans")
@RestController
public class CampaignTrasactionDocumentController {
    Logger logger= LoggerFactory.getLogger(CampaignTrasactionDocumentController.class);

    @Autowired
    CampaignTransactionService campaignTransactionService;

    @GetMapping("/getbycampaignid")
    public PaginationMainDao<CampaignObjectDao> findAllByCreateBy(@RequestParam("id") String id  , @RequestParam("count")int count, @RequestParam("length") int limit, @RequestParam("start") int offset) {
        return campaignTransactionService.finddata(id,offset,limit,count);
    }





}
