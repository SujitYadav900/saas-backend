package com.example.maxcrm.MaxCrm.Controller;

import com.example.maxcrm.MaxCrm.Dao.LogEventDao;
import com.example.maxcrm.MaxCrm.Dao.UserMasterDao;
import com.example.maxcrm.MaxCrm.Service.LogEventService;
import com.example.maxcrm.MaxCrm.Utility.UtilityClass;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/logevent")
public class LogEventController {
    Logger logger = LoggerFactory.getLogger(LogEventController.class);
    @Autowired
    LogEventService logEventService;
    @GetMapping("/getbyleadid")
    public Iterable<LogEventDao> findAllByLeadId(@RequestParam("id")long id)
    {
        return logEventService.findAllByLeadId(id);
    }

    @GetMapping("/getlogbyeventanduserid")
    public Iterable<LogEventDao> getlogEventByeventTypeAnduserId(@RequestParam("eventType") String eventType,@RequestParam("userId")  int userId){

        return  logEventService.getlogEventByeventTypeAnduserId(eventType,userId);
    }

    @PostMapping("/insert")
    public LogEventDao insert(@RequestBody LogEventDao logEventDao, Authentication authentication)
    {
        UserMasterDao u = (UserMasterDao) authentication.getPrincipal();
        logEventDao.setCreateBy(u.getUsername());
        logEventDao.setUpdateBy(u.getUsername());
        logEventDao.setCreateAt(UtilityClass.getDateRedable());
        logEventDao.setUpdateAt(UtilityClass.getDateRedable());
        return logEventService.insert(logEventDao);
    }
    @PostMapping("/update")
    public LogEventDao update(@RequestBody LogEventDao logEventDao, Authentication authentication)
    {
        UserMasterDao u = (UserMasterDao) authentication.getPrincipal();
        logEventDao.setUpdateBy(u.getUsername());
        logEventDao.setUpdateAt(UtilityClass.getDateRedable());
        return logEventService.update(logEventDao);
    }
    @DeleteMapping("/delete")
    public void delete(@RequestParam("id")long id,@RequestParam("leadId")long leadId, Authentication authentication)
    {
        UserMasterDao u = (UserMasterDao) authentication.getPrincipal();
        logger.info("Deleting Event by {} id {}",u.getUsername(),id);
        logEventService.delete(id,u.getUsername(),leadId);
    }
}
