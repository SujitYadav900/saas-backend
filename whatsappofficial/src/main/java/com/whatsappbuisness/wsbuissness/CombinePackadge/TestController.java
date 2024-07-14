package com.whatsappbuisness.wsbuissness.CombinePackadge;
/*
 Author=Supreet Singh
 Date= 12/02/21 12:56 PM
*/


import com.google.gson.Gson;
import com.whatsappbuisness.wsbuissness.CombinePackadge.Common.SessionRetrival.SessionRetrievalDao;
import com.whatsappbuisness.wsbuissness.CombinePackadge.Common.SessionRetrival.SessionRetrievalService;
import com.whatsappbuisness.wsbuissness.CombinePackadge.Common.SessionRetrival.UsermasterDao;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/test")
public class TestController {
    @Autowired
    SessionRetrievalService sessionRetrievalService;
    @GetMapping("/getuser")
    public UsermasterDao getUser(Authentication authentication) throws Exception {

        return sessionRetrievalService.get(authentication);
    }


}
