package com.example.maxcrm.MaxCrm.CombinePackage.Webhooks;

import com.example.maxcrm.MaxCrm.Utility.UtilityClass;
import okhttp3.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

@Service
public class WebhookServiceImpl implements WebhookService {
    static Logger logger= LoggerFactory.getLogger(WebhookServiceImpl.class);
    static String webhookUrl= UtilityClass.propertyService.findProperty("Application","WebhookUrl");

    public static final OkHttpClient client = new OkHttpClient.Builder()
            .readTimeout(5, TimeUnit.SECONDS)
            .writeTimeout(5, TimeUnit.SECONDS)
            .connectTimeout(5,TimeUnit.SECONDS)
            .connectionPool(new ConnectionPool(10,10,TimeUnit.SECONDS))

            .build();
    @Override
    public String makeWebhookCall(WebhookDao webhookDao) {
        String json=webhookDao.convertToJson();


        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType, json);
        Request request = new Request.Builder()
                .url(webhookUrl)
                .post(body)
                .addHeader("content-type", "application/json")
                .build();
        Response response;
        try {
             response = client.newCall(request).execute();
             String responseString=response.body().string();
             logger.info("Response From Webhook {} and Response is {}",webhookDao,responseString);
             return responseString;
        } catch (IOException e) {
            logger.error("Error Occured while calling webhook",e);
            return "Error Occured with status code "+e.getMessage();

        }

    }
}
