package com.whatsappbuisness.wsbuissness.CombinePackadge.KarixEmailService;

import okhttp3.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class EmailServiceImpl implements EmailService {
    Logger logger= LoggerFactory.getLogger(EmailServiceImpl.class);
    public static final OkHttpClient client = new OkHttpClient.Builder()
            .readTimeout(120, TimeUnit.SECONDS)
            .writeTimeout(120, TimeUnit.SECONDS)
            .connectTimeout(120,TimeUnit.SECONDS)
            .connectionPool(new ConnectionPool(10,120,TimeUnit.SECONDS))

            .build();



    @Override
    public void sendEmail(List<EmailDao> al) {

        for(EmailDao emailDao:al)
        {
            String json=new EmailDao().toJson(emailDao);
            //logger.info("Making Request with json {}",json);

            MediaType mediaType = MediaType.parse("application/json");
            RequestBody body = RequestBody.create(mediaType, json);
            Request request = new Request.Builder()
                    .url("https://ejson.mgage.solutions/sendEmail")
                    .post(body)
                    .addHeader("content-type", "application/json")
                    .build();
            try {
                Response response = client.newCall(request).execute();
                //logger.info("After Sending Notifcation {}",response.body().string());
            } catch (IOException e) {
                logger.error("Error Occured While Sending");
                e.printStackTrace();
            }

        }



    }
}
