package com.whatsappbuisness.wsbuissness.CombinePackadge.ApiService.TokenServiceWs;

import com.whatsappbuisness.wsbuissness.CombinePackadge.ApiService.Media.MediaService;
import com.whatsappbuisness.wsbuissness.CombinePackadge.Datetiming.DateTiming;
import com.whatsappbuisness.wsbuissness.CombinePackadge.Subscription.SubscriptionDao;
import com.whatsappbuisness.wsbuissness.CombinePackadge.Subscription.SubscriptionService;
import okhttp3.*;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/*
 Author=Suit  Yadav
 Date= 25/feb/23
*/
@Service
public class TokenServiceImpl implements TokenService {
    public static final OkHttpClient client = new OkHttpClient.Builder()
            .readTimeout(20, TimeUnit.SECONDS)
            .writeTimeout(20, TimeUnit.SECONDS)
            .connectTimeout(20, TimeUnit.SECONDS)
            .connectionPool(new ConnectionPool(10, 120, TimeUnit.SECONDS))
            .build();
    private static final Logger logger = LoggerFactory.getLogger(TokenService.class);
    public static MediaType mediaType = MediaType.parse("application/json");
    @Autowired
    TokenRepo tokenRepo;
    @Autowired
    SubscriptionService subscriptionService;

    @Override
    public String getToken(long id) throws Exception {
        TokenServiceDao tokenServiceDao = null;
        String accessToken = "";
        try {
                tokenServiceDao = tokenRepo.findById(id).get();


            if (tokenServiceDao.getExpired() <= DateTiming.getDateFilterDate()) {
                logger.info(" Token As Expired {} generate new token", id);
                accessToken = generateNewToken(id);

            } else {

                logger.info("Will Give Same Access Token from db {}", id);
                accessToken = tokenServiceDao.getAccessToken();
            }
        } catch (Exception ew) {
            tokenServiceDao = null;
            logger.info("ERROR OCCURRED getToken  {}",ew.getMessage());
            ew.printStackTrace();
        }

        if (tokenServiceDao == null) {
            logger.info("No Access Token Can be found so will generate one {}", id);
            accessToken = generateNewToken(id);
        }
       // logger.info("Getting Acccess Token {}", accessToken);
        return accessToken;
    }

    String generateNewToken(long id) throws Exception {
        TokenServiceDao tokenServiceDao = new TokenServiceDao();
        SubscriptionDao subscriptionDao = subscriptionService.getByActive(id);
       String credential = Credentials.basic(subscriptionDao.getUserName(), subscriptionDao.getPassword());
        RequestBody body = RequestBody.create("", mediaType);
        Request request = new Request.Builder()
               .url(subscriptionDao.getBaseUrl()+"v1/users/login")
                .method("POST", body)
                .addHeader("Content-Type", "application/json")
                .addHeader("Authorization",  credential)
                .build();
       // logger.info("Will Try Getting Access Token {}",request);
        Response response = client.newCall(request).execute();
        //logger.info("After Hitting Url Response Was {}",response);
        if (response.code() != 200) {
        return   "Failed To Generate Access Token";
        } else {
            String tokenRes = response.body().string();
            //logger.info("Getting Data From Token Api {}", tokenRes);
            JSONObject jsonObject = new JSONObject(tokenRes);
            JSONArray array = jsonObject.getJSONArray("users");
            String accessToken = array.getJSONObject(0).getString("token");
            int expiresIn = Integer.parseInt(array.getJSONObject(0).getString("expires_after").split(" ")[0].replaceAll("-", ""));
            tokenServiceDao.setAccessToken(accessToken);
            tokenServiceDao.setExpired(expiresIn);
            tokenServiceDao.setId(id);
            //logger.info("Generated Token {} ", tokenServiceDao);
            tokenRepo.save(tokenServiceDao);

        }
        return tokenServiceDao.getAccessToken();

    }

    public static void main(String[] args) throws Exception {
        TokenServiceImpl tokenService=new TokenServiceImpl();
        tokenService.generateNewToken(20339);
    }

}
