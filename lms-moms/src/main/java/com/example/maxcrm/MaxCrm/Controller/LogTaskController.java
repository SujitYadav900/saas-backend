package com.example.maxcrm.MaxCrm.Controller;

import com.example.maxcrm.MaxCrm.Dao.LeadMasterDao;
import com.example.maxcrm.MaxCrm.Dao.LogTaskDao;
import com.example.maxcrm.MaxCrm.Dao.MaskingDao;
import com.example.maxcrm.MaxCrm.Dao.UserMasterDao;
import com.example.maxcrm.MaxCrm.Service.LeadMasterService;
import com.example.maxcrm.MaxCrm.Service.LogTaskService;
import com.example.maxcrm.MaxCrm.Utility.UtilityClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/api/logtask")
public class LogTaskController {
    @Autowired
    LogTaskService logTaskService;
    @Autowired
    LeadMasterService leadMasterService;


    @GetMapping("/getlogtask")
    Iterable<LogTaskDao> findByLeadId(@RequestParam("id")long id)
    {
        return logTaskService.findAllByLeadId(id);
    }


    @PostMapping("/insert")
    LogTaskDao insert(@RequestBody LogTaskDao logTaskDao,@RequestParam("increasetime")boolean increaseTime, Authentication authentication) throws SQLException {
        UserMasterDao userMasterDao= (UserMasterDao) authentication.getPrincipal();
        logTaskDao.setCreateBy(userMasterDao.getUsername());
        logTaskDao.setCreateDate(UtilityClass.getDateRedable());

        logTaskDao.setUpdateBy(userMasterDao.getUsername());
        logTaskDao.setUpdateDate(UtilityClass.getDateRedable());
        logTaskDao.setAssignedTo(userMasterDao.getId());
        logTaskDao.setUrl("lead?id="+logTaskDao.getLeadId());

        LeadMasterDao leadMasterDao = leadMasterService.findById(logTaskDao.getLeadId());
        leadMasterDao.setFollowUpMessage(logTaskDao.getMessage());
        leadMasterDao.setFollowUpTime(logTaskDao.getDateTimeTask());
        try {
            leadMasterService.update(leadMasterDao);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return  logTaskService.insert(logTaskDao);
    }
    @PostMapping("/insertautomactic")
    LogTaskDao insertautomactic(@RequestBody LogTaskDao logTaskDao,@RequestParam("minstoincreate")int minstoincrease, Authentication authentication) throws SQLException {

        String newdate = UtilityClass.increaseTime(UtilityClass.getDateMysql(),minstoincrease);
        logTaskDao.setDateTimeTask(newdate);
        UserMasterDao userMasterDao= (UserMasterDao) authentication.getPrincipal();
        logTaskDao.setCreateBy(userMasterDao.getUsername());
        logTaskDao.setCreateDate(UtilityClass.getDateRedable());
        logTaskDao.setUpdateBy(userMasterDao.getUsername());
        logTaskDao.setUpdateDate(UtilityClass.getDateRedable());
        logTaskDao.setUrl("lead?id="+logTaskDao.getLeadId());
        logTaskDao.setTxtMsgNotification(UtilityClass.propertyService.findProperty("Lead", "SendTextMessageAutomatedTaskLead").equalsIgnoreCase("1"));
        logTaskDao.setAssignedTo(userMasterDao.getId());

        return  logTaskService.insert(logTaskDao);
    }
    @PostMapping("/update")
    LogTaskDao update(@RequestBody LogTaskDao logTaskDao, Authentication authentication) throws SQLException {
        UserMasterDao userMasterDao= (UserMasterDao) authentication.getPrincipal();
        logTaskDao.setUpdateBy(userMasterDao.getUsername());
        logTaskDao.setUpdateDate(UtilityClass.getDateRedable());
        logTaskDao.setAssignedTo(userMasterDao.getId());
        return logTaskService.update(logTaskDao);
    }

    @DeleteMapping("/delete")
    void delete(@RequestParam("id")long id,@RequestParam("leadId")long leadId,Authentication authentication)
    {
        UserMasterDao userMasterDao= (UserMasterDao) authentication.getPrincipal();
        logTaskService.delete(id,userMasterDao.getUsername(),leadId);

    }

    @GetMapping("/getdashboardlogs")
    public List<LogTaskDao> getDashboardLogs(Authentication authentication,@RequestParam(defaultValue = "team") String flag){
        UserMasterDao user = (UserMasterDao) authentication.getPrincipal();
        MaskingDao maskingDao=new MaskingDao().convertHashMapToMasking(user.getFeatures());
        String userList = null;

        if(flag.equalsIgnoreCase("team")){
             userList = UtilityClass.userlist.get(user.getId());//eg:- 232,23123,12312,2323,2323,3232
        }else{
            userList = String.valueOf(user.getId());//eg:- 34342
        }

        return logTaskService.findAllForDashboard(userList,maskingDao);
    }
    @GetMapping("/getlogdownloadreport")
    public String getLogDownloadReport(Authentication authentication, @RequestParam(defaultValue = "team") String flag) {

        System.out.println("Flag value is "+flag);
        UserMasterDao user = (UserMasterDao) authentication.getPrincipal();
        MaskingDao maskingDao=new MaskingDao().convertHashMapToMasking(user.getFeatures());
        String userList = null;

        if(flag.equalsIgnoreCase("team")){
            System.out.println("if part used ");
            userList = UtilityClass.userlist.get(user.getId());//eg:- 232,23123,12312,2323,2323,3232
            List<LogTaskDao> logTaskList = logTaskService.findAllForDashboard(userList, maskingDao);
            logTaskService.downloadLogTask(logTaskList);
            return  "Success";
        }else{
            System.out.println("else part used ");
            userList = String.valueOf(user.getId());//eg:- 34342
            logTaskService.findAllForDashboard(userList,maskingDao);
            List<LogTaskDao> logTaskList = logTaskService.findAllForDashboard(userList, maskingDao);
            logTaskService.downloadLogTask(logTaskList);
            return  "Success";
        }


    }

}
