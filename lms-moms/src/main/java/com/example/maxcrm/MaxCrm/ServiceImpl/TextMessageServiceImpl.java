package com.example.maxcrm.MaxCrm.ServiceImpl;

import com.example.maxcrm.MaxCrm.Dao.TextMessage;
import com.example.maxcrm.MaxCrm.Dao.TextMessageSimpleDao;
import com.example.maxcrm.MaxCrm.Dao.TextMessageTrans;
import com.example.maxcrm.MaxCrm.OkHttp.OkHttpSingleTon;
import com.example.maxcrm.MaxCrm.Service.TextMessageService;
import com.example.maxcrm.MaxCrm.Utility.UtilityClass;

import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class TextMessageServiceImpl implements TextMessageService {
    Logger logger = LoggerFactory.getLogger(TextMessageService.class);


    @Override
    public void sendTextMessage(TextMessage al) throws Exception {

        String username= UtilityClass.propertyService.findProperty("SMTPConfigs","USERID");
        String password= UtilityClass.propertyService.findProperty("SMTPConfigs","Password");

        logger.info("Sending text message from sender ID :: {}",al.getSender());
        al.setUserName(username);
        al.setPassword(password);

        String json=al.converObjectToJson();
        logger.info("Sending Message (sendTextMessage) :: {}", json);
        try {
            makeARequest(json);
        }catch (Exception ew)
        {
            logger.error("Error Occured While Sending Message {}",ew);
            throw ew;
        }
    }

    @Override
    public void sendTextMessageSingle(TextMessageSimpleDao textMessageSimpleDao) throws Exception {

        logger.info("Sending Message (sendTextMessageSingle) :: {}", textMessageSimpleDao);
        String username= UtilityClass.propertyService.findProperty("SMTPConfigs","USERID");
        String password= UtilityClass.propertyService.findProperty("SMTPConfigs","Password");
        textMessageSimpleDao.setUserName(username);
        textMessageSimpleDao.setPassword(password);
        makeAGetRequest(textMessageSimpleDao);
    }


    //==============================================================================================


    private void makeAGetRequest(TextMessageSimpleDao textMessageSimpleDao) throws IOException {

        String SMSAPIBASEURL= UtilityClass.propertyService.findProperty("SMTPConfigs","SMSAPIBASEURL");
        Request request = new Request.Builder()
                .url(SMSAPIBASEURL+"api/SendMsg.aspx?uname="+textMessageSimpleDao.getUserName()+"&pass="+textMessageSimpleDao.getPassword()+"&send="+textMessageSimpleDao.getSenderId()+"&dest="+textMessageSimpleDao.getDst()+"&msg="+textMessageSimpleDao.getContent())
                .get()
                .addHeader("cookie", "ASP.NET_SessionId=b3zsie3pvsw2w1gie4i0bgyv")
                .build();

        Response response =OkHttpSingleTon. client.newCall(request).execute();
        logger.info("Response From api (makeAGetRequest) :: {}",response.body().string());
    }
    private void makeARequest(String json) throws Exception {
        logger.info("SMS Template JSON :: {} ",json);
        String SMSAPIBASEURL= UtilityClass.propertyService.findProperty("SMTPConfigs","SMSAPIBASEURL");
        logger.info("Making Request");

        SMSAPIBASEURL=SMSAPIBASEURL+"api/SendMsgTemplate.aspx";
        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType, json);
        Request request = new Request.Builder()
                .url(SMSAPIBASEURL)
                .post(body)
                .build();

        String resjson = OkHttpSingleTon.client.newCall(request).execute().body().string();
        logger.info("Response From Api Is (makeARequest) :: {}",resjson);



    }




}
