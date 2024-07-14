package com.whatsappbuisness.wsbuissness.CombinePackadge.TokenStore;


import com.whatsappbuisness.wsbuissness.CombinePackadge.Common.SessionRetrival.SessionRetrievalService;
import com.whatsappbuisness.wsbuissness.CombinePackadge.Common.SessionRetrival.UsermasterDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/externaltoken")
public class TokenStoreController {
    @Autowired
    TokenStoreService tokenStoreService;
    @Autowired
    SessionRetrievalService sessionRetrievalService;

    @GetMapping("/validatetoken")
    public TokenStoreDao validate(@RequestParam("token") String token) throws Exception {
        try {
            return tokenStoreService.validate(token);
        } catch (Exception ew) {
            throw new Exception("No Token Can be found !!");
        }
    }

    @GetMapping("/")
    public TokenStoreDao get(Authentication authentication) throws Exception {
        UsermasterDao usermasterDao = sessionRetrievalService.get(authentication);
        try {
            return tokenStoreService.getToken(usermasterDao);
        } catch (Exception ew) {
            throw new Exception("No Token Can be found !!");
        }
    }

    @GetMapping("/generatetoken")
    public TokenStoreDao generate(Authentication authentication) {

        UsermasterDao usermasterDao = sessionRetrievalService.get(authentication);
        return tokenStoreService.generate(usermasterDao);
    }

}
