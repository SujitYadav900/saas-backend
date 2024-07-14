package com.whatsappbuisness.wsbuissness.CombinePackadge.Campaing;

import com.whatsappbuisness.wsbuissness.CombinePackadge.Common.SessionRetrival.SessionRetrievalService;
import com.whatsappbuisness.wsbuissness.CombinePackadge.Common.SessionRetrival.UsermasterDao;
import com.whatsappbuisness.wsbuissness.CombinePackadge.Datetiming.DateTiming;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/*
 Author=Supreet Singh
 Date= 16/03/21 12:26 PM
*/
@RestController
@RequestMapping("/campaign")
public class CampaingController {


    @Autowired
    CampaingService campaingService;
    @Autowired
    SessionRetrievalService sessionRetrievalService;

    private static final Logger logger= LoggerFactory.getLogger(CampaingController.class);

    @GetMapping("/")
    public List<CampaingDao> getAllByAccountId(Authentication authentication) {
        UsermasterDao usermasterDao = sessionRetrievalService.get(authentication);
        return campaingService.getAllByAccountId(usermasterDao.getAccountId());
    }
    
    @GetMapping("/{accountId}")
    public List<CampaingDao> getAccountByReport(@PathVariable("accountId") long accountId) {
    	
        return campaingService.getAllByAccountId(accountId);
    }

    @PostMapping("/insert")
    public CampaingDao insert(@RequestBody CampaingDao campaingDao, Authentication authentication) {
        UsermasterDao usermasterDao = sessionRetrievalService.get(authentication);
        campaingDao.setAccountId(usermasterDao.getAccountId());
        usermasterDao.setCreateBy(String.valueOf(usermasterDao.getId()));

        if (campaingDao.getiScheduled() != 1) {
            campaingDao.setScheduledTime(DateTiming.getDateRedableFilter());
        } else {
            campaingDao.setScheduledTime(campaingDao.getScheduledTime().substring(0, 4) + "-" + campaingDao.getScheduledTime().substring(4, 6) + "-" + campaingDao.getScheduledTime().substring(6, 8) + " " + campaingDao.getScheduledTime().substring(9, 11) + ":" + campaingDao.getScheduledTime().substring(11, 13) + ":00");
//            logger.info("Else block {}",campaingDao.getScheduledTime());
        }
        campaingDao.setCreateAt(DateTiming.getDateRedable());
        return campaingService.insert(campaingDao);

    }



}
