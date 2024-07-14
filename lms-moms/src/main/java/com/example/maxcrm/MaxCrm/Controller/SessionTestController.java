package com.example.maxcrm.MaxCrm.Controller;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
//import sun.text.normalizer.ICUBinary;

@RestController
@RequestMapping("/api/session")
public class SessionTestController {

    @GetMapping("/check")
    public int checkSession(Authentication authentication){
        if(authentication == null) return 403;
        return 200;
    }
}
