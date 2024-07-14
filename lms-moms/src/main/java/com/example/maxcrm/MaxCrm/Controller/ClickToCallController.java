package com.example.maxcrm.MaxCrm.Controller;

import com.example.maxcrm.MaxCrm.Dao.LogEventDao;
import com.example.maxcrm.MaxCrm.Dao.UserMasterDao;
import com.example.maxcrm.MaxCrm.Service.ClickToCallService;
import com.example.maxcrm.MaxCrm.Utility.UtilityClass;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/clicktocall")
public class ClickToCallController {
    @Autowired
    ClickToCallService clickToCallService;

    private static Logger logger= LoggerFactory.getLogger(ClickToCallController.class);
    @PostMapping("/placeacall")
    public String placeACall(Authentication authentication, @RequestBody LogEventDao logEventDao, @RequestParam("tophonenumber")String tophonenumber) throws Exception {

        logger.info("ClickToCallController placeacall method call");
        UserMasterDao userMasterDao= (UserMasterDao) authentication.getPrincipal();
        String date= UtilityClass.getDateRedable();
        logEventDao.setCreateBy(userMasterDao.getUsername());
        logEventDao.setUpdateBy(userMasterDao.getUsername());
        logEventDao.setCreateAt(date);
        logEventDao.setUpdateAt(date);


         logger.info("ClickToCallController LogEventDao :: {}",logEventDao);
        clickToCallService.makeACall(logEventDao,userMasterDao.getMobile(),tophonenumber);
        return "Successfully Placed Call!!You will receive a call on "+userMasterDao.getMobile();
    }


}
