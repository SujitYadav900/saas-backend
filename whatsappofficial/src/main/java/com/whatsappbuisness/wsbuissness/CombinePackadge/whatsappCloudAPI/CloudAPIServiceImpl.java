package com.whatsappbuisness.wsbuissness.CombinePackadge.whatsappCloudAPI;

import com.google.gson.Gson;
import com.whatsappbuisness.wsbuissness.CombinePackadge.CloudApiErrorCode.CloudApiErrorCodeService;
import com.whatsappbuisness.wsbuissness.CombinePackadge.Messages.MessageDao;
import com.whatsappbuisness.wsbuissness.CombinePackadge.Messages.MessageStatus;
import okhttp3.*;
import org.json.JSONArray;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.json.JSONObject;
import java.io.IOException;
import java.util.Date;

@Service
public class CloudAPIServiceImpl implements CloudAPIService{

    @Autowired
    CloudApiErrorCodeService cloudApiErrorCodeService;

    private final Logger logger = LoggerFactory.getLogger(CloudAPIServiceImpl.class);

    @Override
    public MessageDao sendMessageByCloudAPI(CloudAPIDao cloudAPIDao, MessageDao messageDao) {

        String json = new Gson().toJson(cloudAPIDao);
        MediaType mediaType = MediaType.parse("application/json") ;
        RequestBody requestBody = RequestBody.create(mediaType,json);
        Request request = new Request.Builder()
                .url("https://graph.facebook.com/v16.0/"+messageDao.getSubscriptionDao().getCloudAPIPhoneNumberID()+"/messages")
                .method("POST",requestBody)
                .addHeader("Authorization", "Bearer "+ messageDao.getSubscriptionDao().getCloudAPIToken())
                .addHeader("Content-Type", "application/json")
                .addHeader("Cache-Control", "no-cache, no-store, max-age=0, must-revalidate")
                .addHeader("Pragma", "no-cache")
                .addHeader("Expires", "0")
                .addHeader("Date", String.valueOf(new Date()))
                .build();
        OkHttpClient httpClient = new OkHttpClient().newBuilder().build();
        String responseString;
        Response response = null;
        try {
            response = httpClient.newCall(request).execute();
            responseString = response.body().string();
            JSONObject jsonObject = new JSONObject(responseString);
            int responseCode = response.code();
            if (responseCode == 200 || responseCode == 201) {
                messageDao.setMessageStatus(MessageStatus.SENT);
                messageDao.setId(jsonObject.getJSONArray("messages").getJSONObject(0).getString("id"));
                logger.info("After Succcessfully sending Message {}",responseString);
            } else {
                logger.error("Failed To Send Message {}",responseString);
                messageDao.setMessageStatus(cloudApiErrorCodeService.getCloudApiErrorStatus(String.valueOf(jsonObject.getJSONObject("error").getInt("code"))).getStatus());
            }
        } catch (IOException e) {
            messageDao.setMessageStatus(MessageStatus.FAILED);
            logger.info("The Request is {}",request);
            logger.info("If Exception Occurs in The Request ");
            throw new RuntimeException(e);
        }catch (NullPointerException nullPointerException) {
            messageDao.setMessageStatus(MessageStatus.FAILED);
            logger.info("If Exception Occurs in The nullPointerException Request ");
            throw new RuntimeException(nullPointerException);
        }finally {
            if (response != null) {
                response.close();
            }
        }
        return messageDao;
    }
}
