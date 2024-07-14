package com.whatsappbuisness.wsbuissness.CombinePackadge.ApiService.Health;
/*
 Author=Supreet Singh
 Date= 12/02/21 3:11 PM
*/


import com.whatsappbuisness.wsbuissness.CombinePackadge.ApiService.Media.MediaService;
import com.whatsappbuisness.wsbuissness.CombinePackadge.ApiService.TokenServiceWs.TokenService;
import com.whatsappbuisness.wsbuissness.CombinePackadge.RabbitMqQue.RabbitMqqueService;
import com.whatsappbuisness.wsbuissness.CombinePackadge.Subscription.SubscriptionDao;
import okhttp3.ConnectionPool;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

@Service
public class HealthServiceImpl implements HealthService{
    @Autowired
    TokenService tokenService;
    private static final Logger logger= LoggerFactory.getLogger(MediaService.class);


    public static final OkHttpClient client = new OkHttpClient.Builder()
            .readTimeout(20, TimeUnit.SECONDS)
            .writeTimeout(20, TimeUnit.SECONDS)
            .connectTimeout(20,TimeUnit.SECONDS)
            .connectionPool(new ConnectionPool(10,120,TimeUnit.SECONDS))
            .build();
    @Override
    public HealthDao getHealth(SubscriptionDao subscriptionDao) throws Exception {

        Request request = new Request.Builder()
                .url(subscriptionDao.getBaseUrl()+"v1/health")
                .method("GET", null)
                .addHeader("Content-Type", "application/json")
                .addHeader("Authorization", "Bearer "+tokenService.getToken(subscriptionDao.getAccountId()))
                .build();
        try {
            String response= client.newCall(request).execute().body().string();
            return  new HealthDao().toObj(response);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
