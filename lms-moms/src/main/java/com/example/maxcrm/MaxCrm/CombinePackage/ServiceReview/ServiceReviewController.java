package com.example.maxcrm.MaxCrm.CombinePackage.ServiceReview;

import com.example.maxcrm.MaxCrm.Dao.UserMasterDao;
import com.example.maxcrm.MaxCrm.Utility.UtilityClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/reviewservice")
public class ServiceReviewController {

    @Autowired
    private ServiceReviewService reviewService;

    @PostMapping("/insert")
    public ServiceReviewDao insert(@RequestBody ServiceReviewDao reviewDao, Authentication auth){
        UserMasterDao user = (UserMasterDao) auth.getPrincipal();

        reviewDao.setCreateBy(user.getUsername());
        reviewDao.setCreateDate(UtilityClass.getDateRedable());
        reviewDao.setCreateDateFilter(UtilityClass.dateFilterDate());

        reviewDao.setUpdateBy(user.getUsername());
        reviewDao.setUpdateDate(UtilityClass.getDateRedable());
        reviewDao.setUpdateDateFilter(UtilityClass.dateFilterDate());

        return reviewService.insert(reviewDao);
    }

    @PostMapping("/update")
    public ServiceReviewDao update(@RequestBody ServiceReviewDao reviewDao,Authentication auth){
        UserMasterDao user = (UserMasterDao) auth.getPrincipal();
        reviewDao.setUpdateBy(user.getUsername());
        reviewDao.setUpdateDate(UtilityClass.getDateRedable());
        reviewDao.setUpdateDateFilter(UtilityClass.dateFilterDate());
        return reviewService.update(reviewDao);
    }

    @GetMapping("/getbyleadid")
    public ServiceReviewDao getByLeadId(@RequestParam long leadid){
        return  reviewService.findByLeadId(leadid);
    }
}
