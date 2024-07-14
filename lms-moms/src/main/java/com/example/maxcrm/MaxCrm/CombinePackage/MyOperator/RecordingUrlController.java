package com.example.maxcrm.MaxCrm.CombinePackage.MyOperator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController("api/myoperator")
public class RecordingUrlController {

    @Autowired
    RecordingUrlService recordingUrlService;

    @GetMapping("/fetchrecrodingurl")
    String fetchRecrodingUrl(@RequestParam("recordFileName") String recordFileName,@RequestParam("token") String token){

        return recordingUrlService.fetchRecrodingUrl(token,recordFileName);
    }

}
