package com.whatsappbuisness.wsbuissness.CombinePackadge.WhatsappCloudTemplateMaster;

import com.google.gson.Gson;
import com.whatsappbuisness.wsbuissness.CombinePackadge.CounterGeneration.CounterGenerationService;
import com.whatsappbuisness.wsbuissness.CombinePackadge.Messages.MediaCommonObj;
import com.whatsappbuisness.wsbuissness.CombinePackadge.Messages.TemplateMessageDao.ComponentsDao;
import com.whatsappbuisness.wsbuissness.CombinePackadge.Messages.TemplateMessageDao.LanguageDao;
import com.whatsappbuisness.wsbuissness.CombinePackadge.Messages.TemplateMessageDao.ParametersDao;
import com.whatsappbuisness.wsbuissness.CombinePackadge.Messages.TemplateMessageDao.TemplateText.TemplateMessageDao;
import com.whatsappbuisness.wsbuissness.CombinePackadge.Subscription.SubscriptionDao;
import com.whatsappbuisness.wsbuissness.CombinePackadge.Subscription.SubscriptionService;
import com.whatsappbuisness.wsbuissness.CombinePackadge.Template.TemplateMessageService;
import com.whatsappbuisness.wsbuissness.CombinePackadge.whatsappCloudAPI.CloudAPIWebhook.CloudAPIIncomingDao;
import com.whatsappbuisness.wsbuissness.CombinePackadge.whatsappCloudAPI.Image;
import okhttp3.*;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;



@Service
public class CloudTemplateMasterServiceImpl implements CloudTemplateMasterService {


    @Autowired
    CloudTemplateMasterRepo cloudTemplateMasterRepo;

    @Autowired
    SubscriptionService subscriptionService;

    @Autowired
    TemplateMessageService templateMessageService;
    @Autowired
    CounterGenerationService counterGenerationService;

    private final Logger logger = LoggerFactory.getLogger(CloudTemplateMasterServiceImpl.class);

    @Override
    public CloudTemplateMasterDao insert(CloudTemplateMasterDao cloudTemplateMasterDao) {
        List<String> tempUrlType = new ArrayList<>();
        for(int i=0;i<cloudTemplateMasterDao.getComponents().size();i++){
            if(cloudTemplateMasterDao.getComponents().get(i).getType().equalsIgnoreCase("BUTTONS")){
                for(int j=0;j<cloudTemplateMasterDao.getComponents().get(i).getButtons().size();j++){
//                    if(cloudTemplateMasterDao.getComponents().get(i).getButtons().get(j).getType().equalsIgnoreCase("url")){
                        tempUrlType.add(cloudTemplateMasterDao.getComponents().get(i).getButtons().get(j).getUrlType());
                        Buttons buttons = cloudTemplateMasterDao.getComponents().get(i).getButtons().get(j);
                        buttons.setUrlType(null);
                        cloudTemplateMasterDao.getComponents().get(i).getButtons().set(j,buttons);
//                    }
                }
            }
        }

        cloudTemplateMasterDao = sendTemplateforApprovalOnCloud(cloudTemplateMasterDao);
        if(cloudTemplateMasterDao.getError() == null){
            for(int i=0;i<cloudTemplateMasterDao.getComponents().size();i++){
                if(cloudTemplateMasterDao.getComponents().get(i).getType().equalsIgnoreCase("BUTTONS")){
                    for(int j=0;j<cloudTemplateMasterDao.getComponents().get(i).getButtons().size();j++){
//                        if(cloudTemplateMasterDao.getComponents().get(i).getButtons().get(j).getType().equalsIgnoreCase("url")){
                            Buttons buttons = cloudTemplateMasterDao.getComponents().get(i).getButtons().get(j);
                            buttons.setUrlType(tempUrlType.get(j));
                            cloudTemplateMasterDao.getComponents().get(i).getButtons().set(j,buttons);
//                        }
                    }
                }
            }
            addCloudTemplateToTemplateDaoObj(cloudTemplateMasterDao, false);
            cloudTemplateMasterDao.setMultipartFile(null);
            return cloudTemplateMasterRepo.save(cloudTemplateMasterDao);
        }
        return cloudTemplateMasterDao;
    }

    private void addCloudTemplateToTemplateDaoObj(CloudTemplateMasterDao cloudTemplateMasterDao, boolean isUpdate) {
        TemplateMessageDao templateMessageDao = null;
        if(!isUpdate){
            templateMessageDao = new TemplateMessageDao();
            templateMessageDao.setId(counterGenerationService.generateUid());
            templateMessageDao.setAccountId(cloudTemplateMasterDao.getAccountId());
            templateMessageDao.setName(cloudTemplateMasterDao.getName());
            templateMessageDao.setNamespace("CloudAPI");
            templateMessageDao.setCategory(cloudTemplateMasterDao.getCategory());
            List<ComponentsDao> componentsDaoList = new ArrayList<>();
            for(int i =0;i<cloudTemplateMasterDao.getComponents().size();i++){
                if(cloudTemplateMasterDao.getComponents().get(i).getType().equalsIgnoreCase("BODY")){

                    if(cloudTemplateMasterDao.getComponents().get(i).getExample() != null){
                      ComponentsDao componentsDao = new ComponentsDao();
                      componentsDao.setType("body");
                      componentsDao.setIndex(0);
                    List<ParametersDao> parametersDaoList = new ArrayList<>();
                    ParametersDao parametersDao = new ParametersDao();
                    int length = cloudTemplateMasterDao.getComponents().get(i).getExample().getBody_text().get(0).size();
                    for(int j =0;j<length;j++){
                        parametersDao.setType("text");
                        parametersDao.setNameOfParams(""+j);
                        parametersDaoList.add(parametersDao);
                    }
                    componentsDao.setParameters(parametersDaoList);
                    componentsDaoList.add(componentsDao);
                    }//if block ends
                    templateMessageDao.setTemplateBodyText(cloudTemplateMasterDao.getComponents().get(i).getText());
                }if(cloudTemplateMasterDao.getComponents().get(i).getType().equalsIgnoreCase("HEADER")) {
                    if (!cloudTemplateMasterDao.getComponents().get(i).getFormat().equalsIgnoreCase("NOMEDIA")) {

                    ComponentsDao componentsDao = new ComponentsDao();
                    componentsDao.setType("header");
                    componentsDao.setIndex(0);
                    List<ParametersDao> parametersDaoList = new ArrayList<>();
                    ParametersDao parametersDao = new ParametersDao();
                    if (cloudTemplateMasterDao.getComponents().get(i).getFormat().equalsIgnoreCase("IMAGE")) {
                        parametersDao.setType("image");
                        MediaCommonObj mediaCommonObj = new MediaCommonObj();
                        mediaCommonObj.setLink("");
                        mediaCommonObj.setFilename("");
                        parametersDao.setImage(mediaCommonObj);
                    }
                    if (cloudTemplateMasterDao.getComponents().get(i).getFormat().equalsIgnoreCase("DOCUMENT")) {
                        parametersDao.setType("document");
                        MediaCommonObj mediaCommonObj = new MediaCommonObj();
                        mediaCommonObj.setLink("");
                        mediaCommonObj.setFilename("");
                        parametersDao.setDocument(mediaCommonObj);
                    }
                    if (cloudTemplateMasterDao.getComponents().get(i).getFormat().equalsIgnoreCase("VIDEO")) {
                        parametersDao.setType("video");
                        MediaCommonObj mediaCommonObj = new MediaCommonObj();
                        mediaCommonObj.setLink("");
                        mediaCommonObj.setFilename("");
                        parametersDao.setVideo(mediaCommonObj);
                    }
                    parametersDao.setNameOfParams("1");
                    parametersDaoList.add(parametersDao);
                    componentsDao.setParameters(parametersDaoList);
                    componentsDaoList.add(componentsDao);
                }
                }
                if(cloudTemplateMasterDao.getComponents().get(i).getType().equalsIgnoreCase("BUTTONS")) {
//
                    for (int k = 0; k < cloudTemplateMasterDao.getComponents().get(i).getButtons().size(); k++) {
                        ComponentsDao componentsDao = new ComponentsDao();
                        componentsDao.setType("button");
                        componentsDao.setIndex(k);
                        List<ParametersDao> parametersDaoList = new ArrayList<>();
                        if (cloudTemplateMasterDao.getComponents().get(i).getButtons().get(k).getType().equalsIgnoreCase("URL")) {

                            if(cloudTemplateMasterDao.getComponents().get(i).getButtons().get(k).getUrlType().equalsIgnoreCase("dynamic")){
                                componentsDao.setSub_type("url");
                                componentsDao.setUrlType(cloudTemplateMasterDao.getComponents().get(i).getButtons().get(k).getUrlType());
                                ParametersDao parametersDao = new ParametersDao();
                                parametersDao.setType("text");
                                parametersDao.setNameOfParams("1");
                                parametersDaoList.add(parametersDao);
                                componentsDao.setParameters(parametersDaoList);
                                componentsDaoList.add(componentsDao);
                            }
//
                        }
//                        if (cloudTemplateMasterDao.getComponents().get(i).getFormat().equalsIgnoreCase("PHONE_NUMBER")) {
//
//                            componentsDao.setSub_type("call");
//                            ParametersDao parametersDao = new ParametersDao();
//                            parametersDao.setType("text");
//                            parametersDao.setNameOfParams("1");
//                            parametersDaoList.add(parametersDao);
//
//                        }
//                        if (cloudTemplateMasterDao.getComponents().get(i).getFormat().equalsIgnoreCase("QUICK_REPLY")) {
//
//                            componentsDao.setSub_type("action");
//                            ParametersDao parametersDao = new ParametersDao();
//                            parametersDao.setType("text");
//                            parametersDao.setNameOfParams("1");
//                            parametersDaoList.add(parametersDao);
//                        }
//                        componentsDao.setParameters(parametersDaoList);
//                        componentsDaoList.add(componentsDao);
                    }
                }
            }
            templateMessageDao.setComponents(componentsDaoList);
            LanguageDao languageDao = new LanguageDao();
            languageDao.setCode(cloudTemplateMasterDao.getLanguage());
            languageDao.setPolicy("CloudAPI");
            templateMessageDao.setLanguage(languageDao);
            templateMessageDao.setStatus(cloudTemplateMasterDao.getStatus().equalsIgnoreCase("APPROVED"));
        }else{
            templateMessageDao = templateMessageService.findByAccountIdAndName(cloudTemplateMasterDao.getAccountId(), cloudTemplateMasterDao.getName()).get(0);
            List<ComponentsDao> componentsDaoList = new ArrayList<>();
            for(int i =0;i<cloudTemplateMasterDao.getComponents().size();i++){
                if(cloudTemplateMasterDao.getComponents().get(i).getType().equalsIgnoreCase("BODY")){
                    ComponentsDao componentsDao = new ComponentsDao();
                    componentsDao.setType("body");
                    componentsDao.setIndex(0);
                    List<ParametersDao> parametersDaoList = new ArrayList<>();
                    ParametersDao parametersDao = new ParametersDao();
                    int length = cloudTemplateMasterDao.getComponents().get(i).getExample().getBody_text().get(0).size();
                    for(int j =0;j<length;j++){
                        parametersDao.setType("text");
                        parametersDao.setNameOfParams(""+j);
                        parametersDaoList.add(parametersDao);
                    }
                    componentsDao.setParameters(parametersDaoList);
                    componentsDaoList.add(componentsDao);
                    templateMessageDao.setTemplateBodyText(cloudTemplateMasterDao.getComponents().get(i).getText());
                }
                if(cloudTemplateMasterDao.getComponents().get(i).getType().equalsIgnoreCase("FOOTER")){
                    ComponentsDao componentsDao = new ComponentsDao();
                    componentsDao.setType("footer");
                    componentsDao.setIndex(0);
                    List<ParametersDao> parametersDaoList = new ArrayList<>();
                    ParametersDao parametersDao = new ParametersDao();
                    parametersDao.setType("text");
                    parametersDao.setNameOfParams("1");
                    parametersDaoList.add(parametersDao);
                    componentsDao.setParameters(parametersDaoList);
                    componentsDaoList.add(componentsDao);
                }
                if(cloudTemplateMasterDao.getComponents().get(i).getType().equalsIgnoreCase("HEADER")){
                    ComponentsDao componentsDao = new ComponentsDao();
                    for(int j=0;j<templateMessageDao.getComponents().size();j++){
                        if(templateMessageDao.getComponents().get(j).getType().contains("header")){
                            componentsDao = templateMessageDao.getComponents().get(j);
                        }
                    }
                    componentsDaoList.add(componentsDao);
                }
//                if(cloudTemplateMasterDao.getComponents().get(i).getType().equalsIgnoreCase("BUTTONS")){
//                    ComponentsDao componentsDao = new ComponentsDao();
//                    for(int j=0;j<templateMessageDao.getComponents().size();j++){
//                        if(templateMessageDao.getComponents().get(j).getType().contains("button")){
//                            componentsDao = templateMessageDao.getComponents().get(j);
//                        }
//                    }
//                    componentsDaoList.add(componentsDao);
//                }
            }
            templateMessageDao.setComponents(componentsDaoList);
            templateMessageDao.setStatus(false);
        }
        if(isUpdate){
            templateMessageService.update(templateMessageDao);
        }else{
            templateMessageService.insert(templateMessageDao);
        }
    }

    private CloudTemplateMasterDao sendTemplateforApprovalOnCloud(CloudTemplateMasterDao cloudTemplateMasterDao){
        SubscriptionDao subscriptionDao = subscriptionService.getByActive(cloudTemplateMasterDao.getAccountId());
        for(int i =0;i<cloudTemplateMasterDao.getComponents().size();i++){
            if(cloudTemplateMasterDao.getComponents().get(i).getType().contains("HEADER")){
                cloudTemplateMasterDao = getTemplateHeaderHandler(cloudTemplateMasterDao);
            }
        }

        String json = new Gson().toJson(cloudTemplateMasterDao);
        MediaType mediaType = MediaType.parse("application/json") ;
        RequestBody requestBody = RequestBody.create(mediaType,json);

        Request request = new Request.Builder()
                .url("https://graph.facebook.com/v16.0/"+ subscriptionDao.getCloudAPIWhatsappID()+"/message_templates")
                .method("POST",requestBody)
                .addHeader("Authorization","Bearer " + subscriptionDao.getCloudAPIToken())
                .build();
        OkHttpClient httpClient = new OkHttpClient().newBuilder().build();
        String responseString;
        Response response = null;
        try {
            response = httpClient.newCall(request).execute();
            responseString = response.body().string();
            int responseCode = response.code();
            if (responseCode == 200 || responseCode == 201) {

                JSONObject jsonObject = new JSONObject(responseString);
                cloudTemplateMasterDao.setId(jsonObject.getString("id"));
                cloudTemplateMasterDao.setStatus(jsonObject.getString("status"));
                cloudTemplateMasterDao.setCategory(jsonObject.getString("category"));
                cloudTemplateMasterDao.setError(null);
                logger.info("After Succcessfully sending request {}",responseString);
            } else {
                cloudTemplateMasterDao.setError(new Gson().fromJson(responseString, CloudErrorObj.class));
                logger.error("Error in Received cloudTemplateMasterDao Response  {}",cloudTemplateMasterDao);
                return cloudTemplateMasterDao;
            }
        } catch (IOException e) {
            logger.info("If Exception Occurs in sending The Request ");
            throw new RuntimeException(e);
        }finally {
            if (response != null) {
                response.close();
            }
        }
        return cloudTemplateMasterDao;
    }

    private CloudTemplateMasterDao getTemplateHeaderHandler(CloudTemplateMasterDao cloudTemplateMasterDao){
        SubscriptionDao subscriptionDao = subscriptionService.getByActive(cloudTemplateMasterDao.getAccountId());
        MultipartFile multipartFile = cloudTemplateMasterDao.getMultipartFile();
        int length = 0;
        String header_handle = "";
        String tempId;
        try {
            length = multipartFile.getBytes().length;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        RequestBody body2 = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("file_length", String.valueOf(length))
                .build();

        Request request = new Request.Builder()
                .url("https://graph.facebook.com/v16.0/"+subscriptionDao.getCloudAPIAppID()+"/uploads?file_type="+multipartFile.getContentType()+"&access_token="+ subscriptionDao.getCloudAPIToken())
                .method("POST", body2)
                .build();

        OkHttpClient okHttpClient = new OkHttpClient().newBuilder().build();
        String responseString;
        Response response = null;
        try {
            response = okHttpClient.newCall(request).execute();
            responseString = response.body().string();
            int code = response.code();
            if(code == 200 || code == 201){
                JSONObject jsonObject = new JSONObject(responseString);
                tempId = jsonObject.getString("id");
                cloudTemplateMasterDao.setError(null);
                logger.info("After Succcessfully sending request from first media api {}",responseString);
            }else{
                cloudTemplateMasterDao.setError(new Gson().fromJson(responseString, CloudErrorObj.class));
                logger.error("Error in Received Response from api first {}",responseString);
                return cloudTemplateMasterDao;
            }
        } catch (IOException e) {
            logger.info("If Exception Occurs in sending The first media api Request ");
            throw new RuntimeException(e);
        }finally {
            if (response != null) {
                response.close();
            }
        }
//        ========================================NowHittingSecondMediaAPI==========================
        RequestBody body = null;
        try {
            body = new MultipartBody.Builder().setType(MultipartBody.FORM)
                    .addFormDataPart("data-binary",multipartFile.getOriginalFilename(),
                            RequestBody.create(MediaType.parse("application/octet-stream"),multipartFile.getBytes()))
                    .build();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Request apirequest = new Request.Builder()
                .url("https://graph.facebook.com/v16.0/"+ tempId +"&file_offset=0")
                .method("POST", body)
                .addHeader("Authorization","OAuth " + subscriptionDao.getCloudAPIToken())
                .build();

        OkHttpClient okHttpClient2 = new OkHttpClient().newBuilder().build();
        String responseString2;
        Response response2 = null;
        try {
            response2 = okHttpClient2.newCall(apirequest).execute();
            responseString2 = response2.body().string();
            int code = response2.code();
            if(code == 200 || code == 201){
                JSONObject jsonObject = new JSONObject(responseString2);
                header_handle = jsonObject.getString("h");

                for(int i=0;i<cloudTemplateMasterDao.getComponents().size();i++){
                    if(cloudTemplateMasterDao.getComponents().get(i).getType().contains("HEADER")){
                        Example example = new Example();
                        List<String> stringList = new ArrayList<>();
                        stringList.add(header_handle);
                        example.setHeader_handle(stringList);
                        cloudTemplateMasterDao.getComponents().get(i).setExample(example);
                    }
                }
                cloudTemplateMasterDao.setError(null);
                logger.info("After Succcessfully sending request from Second media api {}",responseString2);
            }else{
                cloudTemplateMasterDao.setError(new Gson().fromJson(responseString2, CloudErrorObj.class));
                logger.error("Error in Received Response from api Second {}",responseString);
                return cloudTemplateMasterDao;
            }
        } catch (IOException e) {
            logger.info("If Exception Occurs in sending The second media api Request ");
            throw new RuntimeException(e);
        }finally {
            if (response != null) {
                response.close();
            }
        }
        return cloudTemplateMasterDao;
    }


    @Override
    public CloudTemplateMasterDao update(CloudTemplateMasterDao cloudTemplateMasterDao) {
        cloudTemplateMasterDao = sendTemplateforUpdateOnCloud(cloudTemplateMasterDao);
        if(cloudTemplateMasterDao.getError() == null){
            cloudTemplateMasterDao.setStatus("PENDING");
            addCloudTemplateToTemplateDaoObj(cloudTemplateMasterDao, true);
            return cloudTemplateMasterRepo.save(cloudTemplateMasterDao);
        }
        return cloudTemplateMasterDao;
    }

    private CloudTemplateMasterDao sendTemplateforUpdateOnCloud(CloudTemplateMasterDao cloudTemplateMasterDao) {
        SubscriptionDao subscriptionDao = subscriptionService.getByActive(cloudTemplateMasterDao.getAccountId());


        String json = new Gson().toJson(cloudTemplateMasterDao);
        MediaType mediaType = MediaType.parse("application/json") ;
        RequestBody requestBody = RequestBody.create(mediaType,json);

        Request request = new Request.Builder()
                .url("https://graph.facebook.com/v16.0/"+ cloudTemplateMasterDao.getId())
                .method("POST",requestBody)
                .addHeader("Authorization","Bearer " + subscriptionDao.getCloudAPIToken())
                .build();
        OkHttpClient httpClient = new OkHttpClient().newBuilder().build();
        String responseString;
        Response response = null;
        try {
            response = httpClient.newCall(request).execute();
            responseString = response.body().string();
            int responseCode = response.code();
            if (responseCode == 200 || responseCode == 201) {
                cloudTemplateMasterDao.setError(null);
                logger.info("After Succcessfully sending request {}",responseString);
            } else {
                cloudTemplateMasterDao.setError(new Gson().fromJson(responseString, CloudErrorObj.class));
                logger.error("Error in Received Response  {}",responseString);
                return cloudTemplateMasterDao;
            }
        } catch (IOException e) {
            logger.info("If Exception Occurs in sending The Request ");
            throw new RuntimeException(e);
        }finally {
            if (response != null) {
                response.close();
            }
        }
        return cloudTemplateMasterDao;
    }

    @Override
    public CloudTemplateMasterDao getById(String templateId, long accountId) {
        return cloudTemplateMasterRepo.findById(templateId);
    }

    @Override
    public List<CloudTemplateMasterDao> getActiveCloudTemplatesByAccountId(long accountId) {
        return cloudTemplateMasterRepo.findAllByAccountId(accountId);
    }

    @Override
    public void disableTemplate(String templateId, long accountId) {
        CloudTemplateMasterDao cloudTemplateMasterDao = deleteTemplatefromCloud(templateId, accountId);
        disableTemplateFromTemplateDao(cloudTemplateMasterDao);
        cloudTemplateMasterRepo.save(cloudTemplateMasterDao);
    }

    @Override
    public void updateCloudTemplateStatus(CloudAPIIncomingDao cloudAPIIncomingDao, long accountId) {
        CloudTemplateMasterDao cloudTemplateMasterDao = cloudTemplateMasterRepo.findById(cloudAPIIncomingDao.getEntry().get(0).getChanges().get(0).getValue().getMessage_template_id());
        if(cloudAPIIncomingDao.getEntry().get(0).getChanges().get(0).getField().equalsIgnoreCase("message_template_status_update")){
            List<TemplateMessageDao> templateMessageDaos = templateMessageService.findByAccountIdAndName(accountId, cloudTemplateMasterDao.getName());
            for(int i =0;i<templateMessageDaos.size();i++){
                templateMessageDaos.get(i).setStatus(cloudAPIIncomingDao.getEntry().get(0).getChanges().get(0).getValue().getEvent().equalsIgnoreCase("APPROVED"));
            }
            cloudTemplateMasterDao.setStatus(cloudAPIIncomingDao.getEntry().get(0).getChanges().get(0).getValue().getEvent());
            cloudTemplateMasterRepo.save(cloudTemplateMasterDao);
            templateMessageService.update(templateMessageDaos.get(0));
        }
        if(cloudAPIIncomingDao.getEntry().get(0).getChanges().get(0).getField().equalsIgnoreCase("template_category_update")){
            cloudTemplateMasterDao.setCategory(cloudAPIIncomingDao.getEntry().get(0).getChanges().get(0).getValue().getNew_category());
            cloudTemplateMasterRepo.save(cloudTemplateMasterDao);
        }if(cloudAPIIncomingDao.getEntry().get(0).getChanges().get(0).getField().equalsIgnoreCase("phone_number_quality_update")){
            SubscriptionDao subscriptionDao = subscriptionService.getByActive(accountId);
            subscriptionDao.setPhoneNumberEvent(cloudAPIIncomingDao.getEntry().get(0).getChanges().get(0).getValue().getEvent());
            subscriptionDao.setCurrent_limit(cloudAPIIncomingDao.getEntry().get(0).getChanges().get(0).getValue().getCurrent_limit());
            subscriptionService.update(subscriptionDao);
        }

    }

    private void disableTemplateFromTemplateDao(CloudTemplateMasterDao cloudTemplateMasterDao) {

        List<TemplateMessageDao> templateMessageDaoList = templateMessageService.findByAccountIdAndName(cloudTemplateMasterDao.getAccountId(), cloudTemplateMasterDao.getName());
        for(int i =0;i<templateMessageDaoList.size();i++){
            templateMessageDaoList.get(i).setStatus(false);
            templateMessageService.update(templateMessageDaoList.get(i));
        }
    }

    private CloudTemplateMasterDao deleteTemplatefromCloud(String templateId,long accountId) {
        CloudTemplateMasterDao cloudTemplateMasterDao = cloudTemplateMasterRepo.findById(templateId);
        SubscriptionDao subscriptionDao = subscriptionService.getByActive(cloudTemplateMasterDao.getAccountId());
        Request request = new Request.Builder()
                .url("https://graph.facebook.com/v16.0/"+subscriptionDao.getCloudAPIWhatsappID()+"/message_templates?name="+cloudTemplateMasterDao.getName()+"&access_token="+subscriptionDao.getCloudAPIToken())
                .method("DELETE",null)
                .build();
        OkHttpClient httpClient = new OkHttpClient().newBuilder().build();
        String responseString;
        Response response = null;
        try {
            response = httpClient.newCall(request).execute();
            responseString = response.body().string();
            int responseCode = response.code();
            if (responseCode == 200 || responseCode == 201) {
                cloudTemplateMasterDao.setError(null);
                cloudTemplateMasterDao.setStatus("REJECTED");
                logger.info("After Succcessfully sending request {}",responseString);
            } else {
                cloudTemplateMasterDao.setError(new Gson().fromJson(responseString, CloudErrorObj.class));
                logger.error("Error in Received Response  {}",responseString);
            }
            return cloudTemplateMasterDao;
        } catch (IOException e) {
            logger.info("If Exception Occurs in sending The Request ");
            throw new RuntimeException(e);
        }finally {
            if (response != null) {
                response.close();
            }
        }
    }
}
