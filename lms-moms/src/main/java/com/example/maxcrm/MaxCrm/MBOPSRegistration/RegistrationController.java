package com.example.maxcrm.MaxCrm.MBOPSRegistration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/mbops")
public class RegistrationController {

    @Autowired
    private RegistrationService registrationService;
    @PostMapping("/register")
    public void register(@RequestParam long leadId,@RequestParam boolean useCustomIds,@RequestParam String flag) throws Exception {
        registrationService.registerParent(leadId,useCustomIds,flag);
    }
}
