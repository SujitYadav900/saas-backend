package com.whatsappbuisness.wsbuissness.CombinePackadge.RabbitMqQue.Webhooks;

import com.google.gson.Gson;
import com.rabbitmq.client.Channel;
import com.whatsappbuisness.wsbuissness.CombinePackadge.Datetiming.DateTiming;
import com.whatsappbuisness.wsbuissness.CombinePackadge.Messages.MessageDao;

import com.whatsappbuisness.wsbuissness.CombinePackadge.RabbitMqQue.QueName;
import com.whatsappbuisness.wsbuissness.CombinePackadge.RabbitMqQue.RabbitMqqueService;
import com.whatsappbuisness.wsbuissness.CombinePackadge.RabbitMqQue.Webhooks.Webhook.DlrUpdatesDao;
import com.whatsappbuisness.wsbuissness.CombinePackadge.RabbitMqQue.Webhooks.Webhook.WebhookDao;
import com.whatsappbuisness.wsbuissness.CombinePackadge.RabbitMqQue.Webhooks.Webhook.WebhookMessasgeDao;
import okhttp3.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/*
 Author=Supreet Singh
 Date= 08/02/21 5:27 PM
*/
@Component
public class WebhookQue {
    public static final OkHttpClient client = new OkHttpClient.Builder()
            .readTimeout(20, TimeUnit.SECONDS)
            .writeTimeout(20, TimeUnit.SECONDS)
            .connectTimeout(20, TimeUnit.SECONDS)
            .connectionPool(new ConnectionPool(10, 120, TimeUnit.SECONDS))
            .build();
    private static final Logger logger = LoggerFactory.getLogger(WebhookQue.class);

    @RabbitListener(queues = "Webhook_Que")
    public void WebPanelQue(WebhookDao webhookDao, Channel channel, @Header(AmqpHeaders.DELIVERY_TAG) long tag) throws IOException{
        //logger.info("Getting Data Inside Webhook_Que event que {}", webhookDao);
        try {
            hitApi(webhookDao);
        } catch (Exception ew) {
            channel.basicAck(tag,false);
        }

    }

    private void hitApi(WebhookDao webhookDao) {
        String json = new Gson().toJson(webhookDao);
        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(json, mediaType);
        Request request = new Request.Builder()
                .url(webhookDao.getWebhookUrl())
                .post(body)
                .addHeader("cookie", "PHPSESSID=ek8500afed9rb6p1egghb2mq9h; JSESSIONID=D311DD74B013F2D7951E2D7D757A62E2")
                .addHeader("content-type", "application/json")
                .build();
        Response response = null;
        try {
            response = client.newCall(request).execute();

           // logger.info("After Hitting Api Response  is {} and text is {}", response, response.body().string());
        } catch (IOException e) {
           logger.error("Failed To Hit API ",e.getMessage());
        } finally {
            if (response != null) {
                response.close();
            }
        }

    }

}
