package com.example.maxcrm.MaxCrm.CombinePackage.Wati;

import com.example.maxcrm.MaxCrm.CombinePackage.Wati.WatiDaos.WatiTemplateMsgRequestDao;
import com.example.maxcrm.MaxCrm.Dao.TemplateDocument;
import com.example.maxcrm.MaxCrm.OkHttp.OkHttpSingleTon;
import com.example.maxcrm.MaxCrm.Utility.UtilityClass;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import okhttp3.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;

@Service
public class WatiServiceImpl implements WatiService{

    private final Logger logger = LoggerFactory.getLogger(getClass());
    private final String SEND_TEMPLATE_MSG_URL="https://live-server-2555.wati.io/api/v1/getMessageTemplates?pageSize=250&pageNumber=0";
    private final String GET_ALL_TEMPLATES_URL="https://live-server-2555.wati.io/api/v1/sendTemplateMessages";
    @Override
    public List<TemplateDocument> getAllTemplates() throws Exception {
        List<TemplateDocument> list =new ArrayList<>();
       // String url = "https://live-server-2555.wati.io/api/v1/getMessageTemplates?pageSize=10&pageNumber=0";
        String accessToken = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJqdGkiOiJlYmNlY2IzNS1iNDBhLTRiZDktOWFhNS0xMDliZTAzNjVhZTkiLCJ1bmlxdWVfbmFtZSI6Ikl0QG1vbXNiZWxpZWYuY29tIiwibmFtZWlkIjoiSXRAbW9tc2JlbGllZi5jb20iLCJlbWFpbCI6Ikl0QG1vbXNiZWxpZWYuY29tIiwiYXV0aF90aW1lIjoiMTIvMTQvMjAyMSAwOTo0NToyMyIsImRiX25hbWUiOiIyNTU1IiwiaHR0cDovL3NjaGVtYXMubWljcm9zb2Z0LmNvbS93cy8yMDA4LzA2L2lkZW50aXR5L2NsYWltcy9yb2xlIjoiQURNSU5JU1RSQVRPUiIsImV4cCI6MjUzNDAyMzAwODAwLCJpc3MiOiJDbGFyZV9BSSIsImF1ZCI6IkNsYXJlX0FJIn0.LIL7SdD_AdVFUeSqq-ChVuwe30RZlFFBlVcFVSb8fp8";
        Request request = new Request.Builder()
                .url(SEND_TEMPLATE_MSG_URL)
                .method("GET", null)
                .addHeader("Accept", "application/json")
                .addHeader("Authorization", "Bearer "+getToken())
                .build();

        Response response = OkHttpSingleTon.client.newCall(request).execute();
        String responseStr = response.body().string();
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readValue(responseStr, JsonNode.class);
        JsonNode jsonNode1= jsonNode.get("messageTemplates");
        TemplateDocument templateDocument =null;
         if (jsonNode1.isArray())
         {
             for ( JsonNode js: jsonNode1){
                 templateDocument = new TemplateDocument();
                 templateDocument.setId(js.get("id").textValue());
                 templateDocument.setTemplateName(js.get("elementName").textValue());
                 byte status=0;
                 if(js.get("status").textValue().equalsIgnoreCase("APPROVED")){
                     status=1;
                 }
                 templateDocument.setTemplateStatus(status);
                 templateDocument.setTemplateContent(js.get("body").textValue());
                 list.add(templateDocument);
             }
         }
        return  list;
    }

    @Override
    public void sendTemplateMessages(WatiTemplateMsgRequestDao msgRequestDao) throws Exception {

        Gson gson = new Gson();
        String json = gson.toJson(msgRequestDao);

        logger.info("WATI JSON BODY :: {} ",json);

        //String url = "https://live-server-2555.wati.io/api/v1/sendTemplateMessages";
        //String accessToken = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJqdGkiOiJlYmNlY2IzNS1iNDBhLTRiZDktOWFhNS0xMDliZTAzNjVhZTkiLCJ1bmlxdWVfbmFtZSI6Ikl0QG1vbXNiZWxpZWYuY29tIiwibmFtZWlkIjoiSXRAbW9tc2JlbGllZi5jb20iLCJlbWFpbCI6Ikl0QG1vbXNiZWxpZWYuY29tIiwiYXV0aF90aW1lIjoiMTIvMTQvMjAyMSAwOTo0NToyMyIsImRiX25hbWUiOiIyNTU1IiwiaHR0cDovL3NjaGVtYXMubWljcm9zb2Z0LmNvbS93cy8yMDA4LzA2L2lkZW50aXR5L2NsYWltcy9yb2xlIjoiQURNSU5JU1RSQVRPUiIsImV4cCI6MjUzNDAyMzAwODAwLCJpc3MiOiJDbGFyZV9BSSIsImF1ZCI6IkNsYXJlX0FJIn0.LIL7SdD_AdVFUeSqq-ChVuwe30RZlFFBlVcFVSb8fp8";

        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType,json);

        Request request = new Request.Builder()
                .url(GET_ALL_TEMPLATES_URL)
                .method("POST", body)
                .addHeader("Accept", "application/json")
                .addHeader("Authorization", "Bearer "+getToken())
                .build();
        Map<String,HashMap> responseMap = null;
        try {
            Response response = OkHttpSingleTon.client.newCall(request).execute();
            String responseStr = response.body().string();
            logger.info("ResponseStr > {}",responseStr);
            responseMap = new ObjectMapper().readValue(responseStr, HashMap.class);
            logger.info("ResponseMap > {}",responseMap);

        }catch (Exception ex){
            logger.error("Error Occured while creating child >> {}",ex);
            }


    }

    private String getToken() throws Exception {

        String token=null;
        token= UtilityClass.propertyService.findProperty("Campaign", "WatiAccessToken");
        if(token == null){
            throw new Exception("Wati Access Token Not Found");
        }
        return token;
    }
    public static void main(String[] args) throws Exception {
        new WatiServiceImpl().getAllTemplates();
    }
}
