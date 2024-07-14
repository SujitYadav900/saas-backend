package com.example.maxcrm.MaxCrm.CombinePackage.MyOperator;

import com.example.maxcrm.MaxCrm.OkHttp.OkHttpSingleTon;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.Request;
import okhttp3.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class RecordingUrlServiceimpl implements RecordingUrlService{

    Logger logger = LoggerFactory.getLogger(RecordingUrlServiceimpl.class);

    @Override
    public String fetchRecrodingUrl(String token, String recordFileName) {
        try{
            String url = "https://developers.myoperator.co/recordings/link?token="+token+"&file="+recordFileName;

            Request request = new Request.Builder()
                    .url(url)
                    .method("GET", null)
                    .addHeader("Accept", "application/json")
                    .build();
            Response response = OkHttpSingleTon.client.newCall(request).execute();
            String responseStr=response.body().string();
            logger.info("CommonLeadMasterController :: insertleadbyivr :: responseStr  : {}",responseStr);
            ObjectMapper mapper=new ObjectMapper();
            RecordingUrlDao RecordingUrlResponseObject = mapper.readValue(responseStr, RecordingUrlDao.class);
            logger.info("CommonLeadMasterController :: insertleadbyivr :: RecordingUrlResponseObject  : {}",RecordingUrlResponseObject);
            String playableUrl = RecordingUrlResponseObject.getUrl();
            logger.info("CommonLeadMasterController :: insertleadbyivr :: playableUrl  : {}",playableUrl);
            return playableUrl;
        }catch(Exception e){
            e.printStackTrace();
        }
     return null;
    }
}
