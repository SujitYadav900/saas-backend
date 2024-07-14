package com.example.maxcrm.MaxCrm.ServiceImpl;

import com.example.maxcrm.MaxCrm.Dao.FacebookDao;
import com.example.maxcrm.MaxCrm.Dao.FacebookPayloadInnerDao;
import com.example.maxcrm.MaxCrm.OkHttp.OkHttpSingleTon;
import com.example.maxcrm.MaxCrm.Service.FacebookAddService;
import com.example.maxcrm.MaxCrm.Utility.UtilityClass;
import com.google.gson.Gson;

import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class FacebookAddServiceImpl implements FacebookAddService {
    Logger logger= LoggerFactory.getLogger(FacebookAddService.class);
    @Override
    public List<FacebookDao> getLeadDetailsById(List<FacebookPayloadInnerDao> al) {
        List<FacebookDao> alfaFacebookDaos = new ArrayList<>();
        for (int i = 0; i < al.size(); i++)
        {
            try{
               FacebookDao facebookDao= makeRequest(al.get(i).getValue().getLeadgen_id());
               alfaFacebookDaos.add(facebookDao);
            }catch (Exception ew)
            {
                logger.error("Error Occured While Retriving Lead!!");

            }
        }
        return alfaFacebookDaos;
    }
    private FacebookDao makeRequest(String leadId) throws Exception {
        String faceBookPrefix=UtilityClass.propertyService.findProperty("Application","FacebookUrl");
        try {


            Request request = new Request.Builder()
                    .url(faceBookPrefix+ leadId)
                    .get()

                    .addHeader("authorization", "Bearer " + UtilityClass.facebookAccessToken)
                    .build();

            Response response = OkHttpSingleTon.client.newCall(request).execute();
            ResponseBody responseBody = response.body();
            logger.info("Response Body of leadid {} Is {}",leadId,responseBody.string());
            FacebookDao entity = new Gson().fromJson(responseBody.string(), FacebookDao.class);
            return entity;
        }catch (Exception ew)
        {
            logger.error("Error Occured While Retrieving Lead {}",ew);
            throw new Exception("Error Occuered While Retriving Lead");
        }

    }
}
