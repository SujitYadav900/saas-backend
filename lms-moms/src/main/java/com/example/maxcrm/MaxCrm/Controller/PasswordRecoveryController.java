package com.example.maxcrm.MaxCrm.Controller;

import com.example.maxcrm.MaxCrm.Service.PasswordRecoveryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/allow/api/passwordreset")
public class PasswordRecoveryController {

    @Autowired
    private PasswordRecoveryService passwordRecoveryService;

    @PostMapping("/reset")
    public String reset(@RequestParam("username") String username, @RequestParam("mode") String mode) throws Exception {
        System.out.println("Resetting Password for :: "+username+", with mode :: "+mode);
        return passwordRecoveryService.recoverPassword(username,mode);
    }
}
