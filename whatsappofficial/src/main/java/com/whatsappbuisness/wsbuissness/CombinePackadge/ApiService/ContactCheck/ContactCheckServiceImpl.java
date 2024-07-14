package com.whatsappbuisness.wsbuissness.CombinePackadge.ApiService.ContactCheck;
/*
 Author=Supreet Singh
 Date= 10/03/21 11:24 AM
*/

import com.whatsappbuisness.wsbuissness.CombinePackadge.ApiService.Media.MediaService;
import com.whatsappbuisness.wsbuissness.CombinePackadge.ApiService.TokenServiceWs.TokenService;
import com.whatsappbuisness.wsbuissness.CombinePackadge.Messages.MessageDao;
import okhttp3.*;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

@Service
public class ContactCheckServiceImpl implements ContactCheckService {
    public static final OkHttpClient client = new OkHttpClient.Builder()
            .readTimeout(20, TimeUnit.SECONDS)
            .writeTimeout(20, TimeUnit.SECONDS)
            .connectTimeout(20, TimeUnit.SECONDS)
            .connectionPool(new ConnectionPool(10, 120, TimeUnit.SECONDS))
            .build();
    private static final Logger logger = LoggerFactory.getLogger(MediaService.class);
    public static MediaType mediaType = MediaType.parse("application/json");
    @Autowired
    TokenService tokenService;

    @Override
    public MessageDao checkContact(MessageDao messageDao) throws Exception {
        if(messageDao.getSubscriptionDao().isTest())
        {
            logger.info("No Contact Check As Test Account");
        }else {
            String accessToken = tokenService.getToken(messageDao.getAccountId());
            RequestBody body = RequestBody.create("{\n   \"blocking\": \"wait\",\n   \"contacts\": [\n      \"+" + messageDao.getTo() + "\"\n   ],\n   \"force_check\": true\n}", mediaType);
            Request request = new Request.Builder()
                    .url(messageDao.getSubscriptionDao().getBaseUrl() + "v1/contacts")
                    .method("POST", body)
                    .addHeader("Content-Type", "application/json")
                    .addHeader("Authorization", "Bearer " + accessToken)
                    .build();
            //logger.info("Request From Contact Check  {}", request);
            Response response = client.newCall(request).execute();
            if (response.code() > 300) {
                logger.error("Failed To Hit Contact Check Api {}", response.code());
                throw new IOException("Server Returned Error Code " + response.code());
            } else {
                String resString = response.body().string();
                JSONObject jsonObject = new JSONObject(resString);
                JSONArray jsonArray = jsonObject.getJSONArray("contacts");
                String status = jsonArray.getJSONObject(0).getString("status");
                if (!status.equalsIgnoreCase("valid")) {
                    throw new ContactNotFoundExepction("Contact Cannot Be Found");
                }
                if (status.equalsIgnoreCase("valid")) {
                    messageDao.setTo(jsonArray.getJSONObject(0).getString("wa_id"));
                }
            }
        }
        return messageDao;
    }
}
