package com.whatsappbuisness.wsbuissness.CombinePackadge.KarixWaOfficial.KarixDao;
/*
 Author=Ritu Redhu
 Date= 06/01/23 10:00 AM
*/
import com.google.gson.Gson;
import com.whatsappbuisness.wsbuissness.CombinePackadge.Messages.MessageDao;
import com.whatsappbuisness.wsbuissness.CombinePackadge.Messages.MessageStatus;
import okhttp3.*;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class KarixMessageServiceImpl implements KarixMessageService{

    private static final Logger logger = LoggerFactory.getLogger(KarixMessageServiceImpl.class);
    @Override
    public MessageDao sendMessageKarix(KarixMessageDao karixMessageDao, MessageDao messageDao) {
        String json=new Gson().toJson(karixMessageDao);
//        logger.info("The karixMessageDao JSON  is {}", json);
        MediaType mediaType = MediaType.parse("application/json");
        RequestBody requestBody = RequestBody.create(mediaType,json);
        Request request = new Request.Builder()
                .url("https://rcmapi.instaalerts.zone/services/rcm/sendMessage")
                .method("POST",requestBody)
                .addHeader("Authentication", "Bearer "+ messageDao.getSubscriptionDao().getKarixToken())
                .addHeader("Content-Type", "application/json")
                .build();
        //logger.info("The Request is "+ request);
        OkHttpClient httpClient = new OkHttpClient().newBuilder().build();
        String responseString;
        Response response = null;
        try {
            response = httpClient.newCall(request).execute();
            responseString = response.body().string();
            int responseCode = response.code();
            if (responseCode == 200 || responseCode == 201) {
                messageDao.setMessageStatus(MessageStatus.SENT);
                JSONObject jsonObject = new JSONObject(responseString);
                String messageId = jsonObject.getString("mid");
                messageDao.setId(messageId);
                logger.info("After Succcessfully sending Message {}",responseString);
            } else {
                messageDao.setMessageStatus(MessageStatus.FAILED);
                logger.error("Failed To Send Message {}",responseString);
            }
        } catch (IOException e) {
            messageDao.setMessageStatus(MessageStatus.FAILED);
            logger.info("If Exception Occurs in The Request ");
            throw new RuntimeException(e);
        }finally {
            if (response != null) {
                response.close();
            }
        }
        return messageDao;
    }
}
