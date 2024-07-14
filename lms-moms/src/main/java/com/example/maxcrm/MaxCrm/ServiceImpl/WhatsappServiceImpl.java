package com.example.maxcrm.MaxCrm.ServiceImpl;

import com.example.maxcrm.MaxCrm.Dao.WhatsappDao;
import com.example.maxcrm.MaxCrm.Dao.WhatsappResponseDao;
import com.example.maxcrm.MaxCrm.OkHttp.OkHttpSingleTon;
import com.example.maxcrm.MaxCrm.Service.WhatsappService;
import com.example.maxcrm.MaxCrm.Utility.UtilityClass;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import okhttp3.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
@Service
public class WhatsappServiceImpl implements WhatsappService {
    Logger logger= LoggerFactory.getLogger(WhatsappServiceImpl.class);
    @Override
    public List<WhatsappResponseDao> sendMessageWhatsapp(List<WhatsappDao> whatsappDao) {
        List<WhatsappResponseDao> responseDaoList = null;
        String url= UtilityClass.propertyService.findProperty("Whatsapp","baseUrl");
        String accessToken=UtilityClass.propertyService.findProperty("Whatsapp","accessToken");
        String  json=new WhatsappDao().convertToJson(whatsappDao);
        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType, json);
        Request request = new Request.Builder()
                .url(url+"api/v1/sendmanytomany")
                .post(body)
                .addHeader("content-type", "application/json")
                .addHeader("access-token", accessToken)
                .build();

        try {
            Response response = OkHttpSingleTon.client.newCall(request).execute();
            Gson gson = new Gson();
            Type userType = new TypeToken<ArrayList<WhatsappResponseDao>>(){}.getType();
           responseDaoList = gson.fromJson(response.body().string(), userType);
            logger.info("After Completing WhatsappRequest {}",responseDaoList);

        } catch (IOException e) {
            logger.error("Error While Making REquest to Whatsapp {}",e);
            e.printStackTrace();
        }
        return responseDaoList;
    }
}
