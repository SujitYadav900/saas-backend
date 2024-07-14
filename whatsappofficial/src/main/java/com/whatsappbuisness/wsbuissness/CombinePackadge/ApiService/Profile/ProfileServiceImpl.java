package com.whatsappbuisness.wsbuissness.CombinePackadge.ApiService.Profile;

import com.whatsappbuisness.wsbuissness.CombinePackadge.ApiService.TokenServiceWs.TokenService;
import com.whatsappbuisness.wsbuissness.CombinePackadge.RabbitMqQue.RabbitMqqueService;
import com.whatsappbuisness.wsbuissness.CombinePackadge.Subscription.SubscriptionDao;
import okhttp3.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/*
 Author=Supreet Singh
 Date= 11/02/21 10:10 PM
*/
@Service
public class ProfileServiceImpl implements ProfileService {

    public static final MediaType mediaType = MediaType.get("application/json; charset=utf-8");
    private static final Logger logger= LoggerFactory.getLogger(ProfileServiceImpl.class);
    @Autowired
    RabbitMqqueService rabbitMqqueService;
    @Autowired
    TokenService tokenService;

    public static final OkHttpClient client = new OkHttpClient.Builder()
            .readTimeout(20, TimeUnit.SECONDS)
            .writeTimeout(20, TimeUnit.SECONDS)
            .connectTimeout(20,TimeUnit.SECONDS)
            .connectionPool(new ConnectionPool(10,120,TimeUnit.SECONDS))
            .build();
    @Override
    public String getProfileAbout(SubscriptionDao accountMasterDao) throws Exception {
        String response="";

        Request request = new Request.Builder()
                .url(accountMasterDao.getBaseUrl()+"v1/settings/profile/about")
                .method("GET", null)
                .addHeader("Content-Type", "application/json")
                .addHeader("Authorization", "Bearer "+tokenService.getToken(accountMasterDao.getAccountId()))
                .build();

        try {
             response = client.newCall(request).execute().body().string();
            logger.info("Reponse from api is {}",response);
        } catch (IOException e) {
            logger.error("Error Occured while getting profile {}",e.getMessage());
            e.printStackTrace();
            response="ERROR";
        }
        return response;
    }

    @Override
    public String getProfilePic(SubscriptionDao accountMasterDao) throws Exception {
        String response="";

        Request request = new Request.Builder()
                .url(accountMasterDao.getBaseUrl()+"v1/settings/profile/photo?format=link")
                .method("GET", null)
                .addHeader("Content-Type", "application/json")
                .addHeader("Authorization", "Bearer "+tokenService.getToken(accountMasterDao.getAccountId()))
                .build();

        try {
            response = client.newCall(request).execute().body().string();
            logger.info("Reponse from api is {}",response);
        } catch (IOException e) {
            logger.error("Error Occured while getting profile {}",e.getMessage());
            e.printStackTrace();
            response="ERROR";
        }
        return response;
    }

    @Override
    public String setProfileAbout(ProfileDao profile, SubscriptionDao accountMasterDao) throws Exception {
        logger.info("Will update profile about of {} to {}",accountMasterDao.getAccountId(),profile);
        String response="";
        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create( profile.toJson(),mediaType);
        Request request = new Request.Builder()
                .url(accountMasterDao.getBaseUrl()+"v1/settings/profile/about")
                .method("PATCH", body)
                .addHeader("Content-Type", "application/json")
                .addHeader("Authorization", "Bearer "+tokenService.getToken(accountMasterDao.getAccountId()))
                .build();
        try {
            response= client.newCall(request).execute().body().string();
        } catch (IOException e) {
            logger.info("Updting Profile info failed {}",e.getMessage());
            response=e.getMessage();
            e.printStackTrace();
        }
        return response;
    }

    @Override
    public String setProfilePic(byte[] profilePic, SubscriptionDao accountMasterDao) throws Exception {
        String response="";
        if(profilePic.length>3000000)
        {
            throw new Exception("Cannot Upload File Greater Then 3 MB");
        }
        logger.info("Setting Profile pic of account {}",accountMasterDao.getAccountId());


        RequestBody body = RequestBody.create( profilePic,MediaType.parse("image/jpeg"));
        Request request = new Request.Builder()
                .url(accountMasterDao.getBaseUrl()+"v1/settings/profile/photo")
                .method("POST", body)
                .addHeader("Content-Type", "image/jpeg")
                .addHeader("Authorization", "Bearer "+tokenService.getToken(accountMasterDao.getAccountId()))
                .build();
        try {
            response=client.newCall(request).execute().body().string();
        } catch (IOException e) {
            logger.info("Updting Profile info failed {}",e.getMessage());
            e.printStackTrace();
        }
        return response;
    }

    @Override
    public String getBuissnessProfile(SubscriptionDao accountMasterDao) throws Exception {
        String response="";

        Request request = new Request.Builder()
                .url(accountMasterDao.getBaseUrl()+"v1/settings/business/profile")
                .method("GET", null)
                .addHeader("Content-Type", "application/json")
                .addHeader("Authorization", "Bearer "+tokenService.getToken(accountMasterDao.getAccountId()))
                .build();

        try {
            response = client.newCall(request).execute().body().string();
            logger.info("Reponse from api is {}",response);
        } catch (IOException e) {
            logger.error("Error Occured while getting profile {}",e.getMessage());
            e.printStackTrace();
            response="ERROR";
        }
        return response;
    }

    @Override
    public String setBuissnessProfile(ProfileBuisnessDao profile,SubscriptionDao accountMasterDao) throws Exception {
        logger.info("Will update buisnness profile about of {} to {}",accountMasterDao.getAccountId(),profile);
        String response="";
        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create( profile.toJson(),mediaType);
        Request request = new Request.Builder()
                .url(accountMasterDao.getBaseUrl()+"v1/settings/business/profile")
                .method("POST", body)
                .addHeader("Content-Type", "application/json")
                .addHeader("Authorization", "Bearer "+tokenService.getToken(accountMasterDao.getAccountId()))
                .build();
        try {
            response=  client.newCall(request).execute().body().string();
        } catch (IOException e) {
            response=e.getMessage();
            logger.info("Updting Profile info failed {}",e.getMessage());
            e.printStackTrace();
        }
        return response;
    }

    @Override
    public void updateWebhook(long id, SubscriptionDao accountMasterDao) {
        logger.info("Will update Webhook URl of id {}",id);
        Response resp = null;
        String response="";
        try {
        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create( "{\n" +
                "\t\"webhooks\": {\n" +
                "       \"url\": \"https://wa.chatmybot.in/gateway/wabuissness/v1/api/webhook/"+id+"\"\n" +
                "    }\n" +
                "}",mediaType);
        Request request = new Request.Builder()
                .url(accountMasterDao.getBaseUrl()+"/v1/settings/application")
                .method("POST", body)
                .addHeader("Content-Type", "application/json")
                .addHeader("Authorization", "Bearer "+tokenService.getToken(accountMasterDao.getAccountId()))
                .build();

            resp=  client.newCall(request).execute();
            response=  resp.body().string();
            logger.info("After updating webhook response was {}",response);

        } catch (Exception e) {
            response=e.getMessage();
            logger.info("Updting Profile info failed {}",e.getMessage());
            e.printStackTrace();
        }
        finally {
            resp.close();
        }

    }

// Author=Ritu Redhu ----

    @Override
    public void updateKarixWebhook(long id, SubscriptionDao accountMasterDao) {
//        logger.info("Will update Webhook URl of id {}",id);
//        String responsestr="";
//        Response response = null;
//        try {
//            MediaType mediaType = MediaType.parse("application/json");
//            RequestBody body = RequestBody.create( "{\n" +
//                    "\t\"webhooks\": {\n" +
//                    "       \"url\": \"https://wa.chatmybot.in/gateway/wabuissness/v1/api/webhook/karix"+20336+"\"\n" +
//                    "    }\n" +
//                    "}",mediaType);
//            Request request = new Request.Builder()
//                    .url(accountMasterDao.getBaseUrl()+"/v1/settings/application")
//                    .method("POST", body)
//                    .addHeader("Content-Type", "application/json")
//                    .addHeader("Authorization", "Bearer "+tokenService.getToken(accountMasterDao.getAccountId()))
//                    .build();
//
//            response=  client.newCall(request).execute();
//            responsestr=  response.body().string();
//            logger.info("After updating webhook response was {}",responsestr);
//
//        } catch (Exception e) {
//            responsestr=e.getMessage();
//            logger.info("Updting Profile info failed {}",e.getMessage());
//            e.printStackTrace();
//        }
//        finally {
//            response.close();
//        }
    }
}
