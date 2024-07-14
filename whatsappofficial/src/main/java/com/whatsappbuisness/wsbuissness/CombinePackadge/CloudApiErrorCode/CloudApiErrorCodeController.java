package com.whatsappbuisness.wsbuissness.CombinePackadge.CloudApiErrorCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/cloudapierrorcode")
public class CloudApiErrorCodeController {

    @Autowired
    CloudApiErrorCodeService cloudApiErrorCodeService;

    @GetMapping("/refreshcahe")
    String refreshcahe() {
        return cloudApiErrorCodeService.refreshCache();
    }
}
