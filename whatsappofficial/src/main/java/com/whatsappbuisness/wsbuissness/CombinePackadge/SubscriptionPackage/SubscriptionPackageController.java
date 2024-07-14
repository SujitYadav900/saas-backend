package com.whatsappbuisness.wsbuissness.CombinePackadge.SubscriptionPackage;

import com.whatsappbuisness.wsbuissness.CombinePackadge.Common.SessionRetrival.SessionRetrievalService;
import com.whatsappbuisness.wsbuissness.CombinePackadge.Common.SessionRetrival.UsermasterDao;
import com.whatsappbuisness.wsbuissness.CombinePackadge.CounterGeneration.CounterGenerationService;
import com.whatsappbuisness.wsbuissness.CombinePackadge.Datetiming.DateTiming;
import com.whatsappbuisness.wsbuissness.CombinePackadge.TokenStore.TokenGenerationErrorHandling;
import com.whatsappbuisness.wsbuissness.CombinePackadge.TokenStore.TokenStoreDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/subscriptionpackage")
public class SubscriptionPackageController {

    public static Logger logger = LoggerFactory.getLogger(SubscriptionPackageController.class);

    @Autowired
    SubscriptionPackageService subscriptionPackageService;

    @Autowired
    SessionRetrievalService sessionRetrievalService;
    @Autowired
    CounterGenerationService counterGenerationService;

    @PostMapping("/")
    SubScriptionPackageDao insert(@RequestBody SubScriptionPackageDao subScriptionPackageDao, Authentication authentication){
        logger.info("The value of Subscription Dao is {}", subScriptionPackageDao);
        UsermasterDao usermasterDao = sessionRetrievalService.get(authentication);
            subScriptionPackageDao.setId(counterGenerationService.generateUid());
            subScriptionPackageDao.setAccountId(usermasterDao.getAccountId());
            subScriptionPackageDao.setCreateDate(DateTiming.getDateRedable());
            subScriptionPackageDao.setCreateDateFilter(DateTiming.getDateFilterDate());
            subScriptionPackageDao.setUpdateDate(DateTiming.getDateRedable());
        return subscriptionPackageService.insert(subScriptionPackageDao);
    }
    @PostMapping("/statuschange")
    SubScriptionPackageDao SubscriptionPackageStatusChange(@RequestBody SubScriptionPackageDao subScriptionPackageDao, Authentication authentication){
            UsermasterDao usermasterDao = sessionRetrievalService.get(authentication);
            subScriptionPackageDao.setAccountId(usermasterDao.getAccountId());
            subScriptionPackageDao.setUpdateDate(DateTiming.getDateRedable());
        return subscriptionPackageService.insert(subScriptionPackageDao);
    }
    @GetMapping("/get")
    List<SubScriptionPackageDao> getByDateAndStatus(@RequestParam("startdate") String startDate, @RequestParam("enddate") String endDate, @RequestParam("status") boolean status, Authentication authentication){
            UsermasterDao usermasterDao = sessionRetrievalService.get(authentication);
            return subscriptionPackageService.getByDateAndStatus(usermasterDao.getId(),startDate,endDate,status);
    }
    @GetMapping("/getbyid")
    SubScriptionPackageDao getById(@RequestParam("startdate") int id, Authentication authentication){
            UsermasterDao usermasterDao = sessionRetrievalService.get(authentication);
            return subscriptionPackageService.getBYId(id);
    }
    @PostMapping("/addsubscriptiontoclient")
    SubScriptionPackageDao addSubscriptionPackageToClient(@RequestBody SubScriptionPackageDao subScriptionPackageDao, Authentication authentication){
        UsermasterDao usermasterDao = sessionRetrievalService.get(authentication);
        subScriptionPackageDao.setAccountId(usermasterDao.getAccountId());
        return subscriptionPackageService.addSubscriptionPackageToClient(subScriptionPackageDao);
    }

}
