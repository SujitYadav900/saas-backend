package com.example.maxcrm.MaxCrm.Controller;

import com.example.maxcrm.MaxCrm.Dao.LoginHistoryDao;
import com.example.maxcrm.MaxCrm.Service.LoginHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.util.List;

@RequestMapping("/api/loginhistory")
@RestController
public class LoginHistoryController {
    @Autowired
    LoginHistoryService loginHistoryService;


    @GetMapping("/getbyuserid")
    LoginHistoryDao getByUserId(@RequestParam("id")int userId) throws SQLException {
        return loginHistoryService.getByUserIdSingle(userId);
    }

    @GetMapping("/getlasttenhistory")
    public List<LoginHistoryDao> getlasttenhistory(@RequestParam("userId") int userId) throws SQLException {
        return loginHistoryService.lastTenHistory(userId);
    }

}
