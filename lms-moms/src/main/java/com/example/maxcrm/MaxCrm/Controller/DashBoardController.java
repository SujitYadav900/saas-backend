package com.example.maxcrm.MaxCrm.Controller;

import com.example.maxcrm.MaxCrm.Dao.DashboardDao;
import com.example.maxcrm.MaxCrm.Dao.UserMasterDao;
import com.example.maxcrm.MaxCrm.Service.DashboardReporting;
import com.example.maxcrm.MaxCrm.ServiceImpl.DashboardServiceImpl;
import com.example.maxcrm.MaxCrm.Utility.UtilityClass;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/api/dashboard")
@RestController
public class DashBoardController {
    private final Logger logger= LoggerFactory.getLogger(DashboardServiceImpl.class);
    @Autowired
    DashboardReporting dashboardReporting;
    @GetMapping("/loadfirstpage")
    List<DashboardDao> findReport(Authentication authentication, @RequestParam("url")String url) throws Exception {
      logger.info("Dashboad api is call ::"+url);
        String[] datearr= UtilityClass.getFirstDateAndEndDate();
        UserMasterDao userMasterDao= (UserMasterDao) authentication.getPrincipal();
        return dashboardReporting.reportDashboard(Integer.parseInt(datearr[0].replaceAll("-","")),url,Integer.parseInt(datearr[1].replaceAll("-","")),userMasterDao.getId());
    }

}
