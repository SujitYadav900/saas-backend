package com.whatsappbuisness.wsbuissness.CombinePackadge.CampaignSchedule;
/*
 Author=Sujit  Yadav
 Date= 16/01/2k3 12:20 AM
*/

import com.whatsappbuisness.wsbuissness.CombinePackadge.Campaing.CampaingDao;
import com.whatsappbuisness.wsbuissness.CombinePackadge.Common.SessionRetrival.SessionRetrievalService;
import com.whatsappbuisness.wsbuissness.CombinePackadge.Common.SessionRetrival.UsermasterDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/schedular")
public class CampaignScheduleController {

    @Autowired
    private CampaignScheduleService service;
    @Autowired
    SessionRetrievalService sessionRetrievalService;

    @GetMapping("/checkDateTime")
    private int checkScheduleTime() {
        return service.checkScheduleTime();
    }

    @GetMapping("/")
    public List<CampaingDao> getAllByAccountId(Authentication authentication) {
        UsermasterDao usermasterDao = sessionRetrievalService.get(authentication);
        return service.getAllActivCampaign(usermasterDao.getAccountId());
    }

    @DeleteMapping("/delete")
    public int deleteByCampaignId(@RequestParam("id") long id) {
        return service.deleteScheduledByCampaignId(id);
    }


}
