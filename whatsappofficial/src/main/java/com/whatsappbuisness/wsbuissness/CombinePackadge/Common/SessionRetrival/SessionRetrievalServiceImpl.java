package com.whatsappbuisness.wsbuissness.CombinePackadge.Common.SessionRetrival;
/*
 Author=Supreet Singh
 Date= 11/03/21 3:20 PM
*/


import com.whatsappbuisness.wsbuissness.CombinePackadge.TokenStore.TokenGenerationErrorHandling;
import com.whatsappbuisness.wsbuissness.CombinePackadge.TokenStore.TokenStoreDao;
import com.whatsappbuisness.wsbuissness.CombinePackadge.TokenStore.TokenStoreService;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Service
public class SessionRetrievalServiceImpl implements SessionRetrievalService {
    private final Logger logger= LoggerFactory.getLogger(SessionRetrievalService.class);
    @Autowired
    TokenStoreService tokenStoreService;


    @Override
    public UsermasterDao get(Authentication authentication) {

            JSONObject jsonObject = new JSONObject(authentication);


            Map<String, Object> map= jsonObject.getJSONObject("userAuthentication").getJSONObject("details").getJSONObject("userAuthentication").getJSONObject("principal").toMap();
            UsermasterDao usermasterDao=new UsermasterDao();
            usermasterDao.setAccountId(Long.parseLong(String.valueOf(map.get("accountId"))));
            usermasterDao.setPhone(String.valueOf(map.get("phone")));
            usermasterDao.setFullname(String.valueOf(map.get("fullname")));
            usermasterDao.setId((int) map.get("id"));
            return usermasterDao;



    }

    @Override
    public TokenStoreDao validate(HttpServletRequest httpServletRequest) throws TokenGenerationErrorHandling {
        String accessToken=httpServletRequest.getParameter("accessToken");
        if(accessToken==null)
        {
            accessToken= httpServletRequest.getHeader("accessToken");
        }
        if(accessToken==null)
        {
            throw new TokenGenerationErrorHandling("Invalid Access Token");
        }
        TokenStoreDao tokenDao = null;
        try {
            tokenDao = tokenStoreService.validate(accessToken);
        } catch (Exception ew) {
            throw new TokenGenerationErrorHandling("Invalid Access Token");
        }
        return tokenDao;
    }
}
