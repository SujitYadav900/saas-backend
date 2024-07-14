package com.example.maxcrm.MaxCrm.Controller;

import com.example.maxcrm.MaxCrm.Dao.UserMasterDao;
import com.example.maxcrm.MaxCrm.Service.UserMasterService;
import com.example.maxcrm.MaxCrm.Utility.UtilityClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserMasterController {
    @Autowired
    UserMasterService userMasterService;

    @PostMapping("/insertdemouser")
    public void insertDemoUser(@RequestBody UserMasterDao userMasterDao) throws Exception {

        boolean letUserCreatePassword = Boolean.parseBoolean(UtilityClass.propertyService.findProperty("CRMDemo","letUserCreatePassword"));
        boolean sendEmailOnCreation = Boolean.parseBoolean(UtilityClass.propertyService.findProperty("CRMDemo","sendEmailOnCreation"));
        boolean twoStepAutheEnabled = Boolean.parseBoolean(UtilityClass.propertyService.findProperty("CRMDemo","twoStepAutheEnabled"));
        int demoPeriod = Integer.parseInt(UtilityClass.propertyService.findProperty("CRMDemo","demoPeriod"));
        String demoUserRole = UtilityClass.propertyService.findProperty("CRMDemo","demoUserRole");
        String deafaultAccountStatus = UtilityClass.propertyService.findProperty("CRMDemo","deafaultAccountStatus");

        System.out.println("letUserCreatePassword >>> "+letUserCreatePassword);
        userMasterDao.setLastUpdateBy(userMasterDao.getUsername());
        //userMasterDao.setCreateByUserId();
        userMasterDao.setLastUpdate(UtilityClass.getDateRedable());
        userMasterDao.setCreateBy(userMasterDao.getUsername());
        userMasterDao.setCreateDate(UtilityClass.getDateRedable());
        userMasterDao.setTwoStepAuthentication(twoStepAutheEnabled);
        userMasterDao.setSendMailOnCreation(sendEmailOnCreation);
        userMasterDao.setDepartment(demoUserRole);
        userMasterDao.setStatus(deafaultAccountStatus);
        userMasterDao.setDemoPeriod(demoPeriod);
        userMasterDao.setDemo(true);

        if(letUserCreatePassword == false){
            userMasterDao.setPassword(UtilityClass.generateOtp());
        }

        userMasterService.insert(userMasterDao);
    }

    @PostMapping("/insertuser")
    public UserMasterDao insertUser(@RequestBody UserMasterDao userMasterDao, Authentication authentication) throws Exception {
        UserMasterDao u = (UserMasterDao) authentication.getPrincipal();
        userMasterDao.setLastUpdateBy(u.getLastUpdateBy());
        userMasterDao.setCreateByUserId(u.getId());
        userMasterDao.setLastUpdate(UtilityClass.getDateRedable());
        userMasterDao.setCreateBy(u.getUsername());
        userMasterDao.setCreateDate(UtilityClass.getDateRedable());

      return   userMasterService.insert(userMasterDao);
    }
    @PostMapping("/updateuser")
    public UserMasterDao updateUser(@RequestBody UserMasterDao userMasterDao, Authentication authentication) throws Exception {
        UserMasterDao u = (UserMasterDao) authentication.getPrincipal();
        userMasterDao.setLastUpdateBy(u.getUsername());
        userMasterDao.setCreateByUserId(u.getId());
        userMasterDao.setLastUpdate(UtilityClass.getDateRedable());
        return   userMasterService.update(userMasterDao);
    }
    @GetMapping("/getuserbydepartment")
    public Iterable<UserMasterDao> getUserByDepartment(@RequestParam("department")String department)
    {
        return   userMasterService.findByDepartment(department);
    }

    @GetMapping("/getusermyteam")
    public Iterable<UserMasterDao> getUserTeam(Authentication authentication)
    {
        UserMasterDao userMasterDao= (UserMasterDao) authentication.getPrincipal();
        return   userMasterService.findByReportList(UtilityClass.userlist.get(userMasterDao.getId()));
    }

    @GetMapping("/getalluser")
    public Iterable<UserMasterDao> getUser()
    {
        return   userMasterService.getAllUsers();
    }

    @GetMapping("/getallactive")
    public Iterable<UserMasterDao> getActiveUsers()
    {
        return   userMasterService.getAllActiveUsers();
    }

    @GetMapping("/getuserById")
    public UserMasterDao getUserByUserId(@RequestParam("id")int id)
    {
        return   userMasterService.findById(id);
    }

    @GetMapping("/getuserbyusername")
    public UserMasterDao getUserByUsername(@RequestParam("username")String username)
    {
        return   userMasterService.findByUsername(username);
    }

}
