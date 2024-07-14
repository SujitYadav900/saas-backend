package com.whatsappbuisness.wsbuissness;


import com.whatsappbuisness.wsbuissness.CombinePackadge.ApiService.Profile.ProfileService;
import okhttp3.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

@SpringBootTest
class WsbuissnessApplicationTests {
    @Autowired
    ProfileService profileService;

    void sendMessage(String dst, String templateId) throws IOException {
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType, "{\n\t\"to\": \"" + dst + "\",\n\t\"type\": \"template\",\n\t\"template\": {\n\t\t\"namespace\": \"60c813f1_bf7f_4aee_afa4_2a87cc648e98\",\n    \t\"name\": \"" + templateId + "\",\n        \"language\": {\n    \t\t\"policy\": \"deterministic\",\n    \t\t\"code\": \"en\"\n        },\n        \"components\": [{\n            \"type\": \"body\",\n            \"parameters\": [\n                \n            ]\n        }]\n\t}\n}\n");
        Request request = new Request.Builder()
                .url("https://prpservices.ameyo.net:9115/v1/messages/")
                .method("POST", body)
                .addHeader("Content-Type", "application/json")
                .addHeader("Authorization", "Bearer eyJhbGciOiAiSFMyNTYiLCAidHlwIjogIkpXVCJ9.eyJ1c2VyIjoiYWRtaW4iLCJpYXQiOjE2MTQ2MDA5MTEsImV4cCI6MTYxNTIwNTcxMSwid2E6cmFuZCI6IjEzZGE5MjUwMDZhNTA0MzlkOTUxYTVhYTE2ZmIyNTgzIn0.rLuSWjJO22xE1g-q99dYofMjHTWJwphWo2sfGIJJHKI")
                .build();
        Response response = client.newCall(request).execute();
        response.close();
    }

    void checkContant(String dst) throws Exception {
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType, "{\n   \"blocking\": \"wait\",\n   \"contacts\": [\n      \"+" + dst + "\"\n   ],\n   \"force_check\": true\n}");
        Request request = new Request.Builder()
                .url("https://prpservices.ameyo.net:9115/v1/contacts")
                .method("POST", body)
                .addHeader("Content-Type", "application/json")
                .addHeader("Authorization", "Bearer eyJhbGciOiAiSFMyNTYiLCAidHlwIjogIkpXVCJ9.eyJ1c2VyIjoiYWRtaW4iLCJpYXQiOjE2MTQ2MDA5MTEsImV4cCI6MTYxNTIwNTcxMSwid2E6cmFuZCI6IjEzZGE5MjUwMDZhNTA0MzlkOTUxYTVhYTE2ZmIyNTgzIn0.rLuSWjJO22xE1g-q99dYofMjHTWJwphWo2sfGIJJHKI")
                .build();
        Response response = client.newCall(request).execute();
        String str = response.body().string();
        if (str.contains("invalid")) {
            throw new Exception("Number Invalid");
        }
        response.close();
    }

    @Test
    void contextLoadsss() {

        String contants =
                "9999420594";


        String[] contactArr = contants.split("\n");
        System.out.println("Contacts list are  " + contactArr.length);
        for (String contact : contactArr) {
            try {
                contact = "91" + contact;
                checkContant(contact);
                // System.out.println("Checking Contact Passed of  "+contact);
                sendMessage(contact, "dltinfo");
                // System.out.println("First Message Send  "+contact);
//                sendMessage(contact, "dlt_alert_autoreply1");
                System.out.println("Sent SuccessFully to  " + contact);
            } catch (Exception ew) {
                System.out.println("Invalid Reciept " + contact);
            }

        }


    }

}
