package com.whatsappbuisness.wsbuissness.CombinePackadge.
        ApiService.SendingServiceImpl;
/*
 Author=Supreet Singh
 Date= 11/02/21 6:19 PM
*/



import com.whatsappbuisness.wsbuissness.CombinePackadge.ApiService.TokenServiceWs.TokenService;
import com.whatsappbuisness.wsbuissness.CombinePackadge.KarixWaOfficial.KarixDao.*;
import com.whatsappbuisness.wsbuissness.CombinePackadge.KarixWaOfficial.KarixDao.Template;
import com.whatsappbuisness.wsbuissness.CombinePackadge.Messages.*;

import com.whatsappbuisness.wsbuissness.CombinePackadge.Messages.ImageMessage.ImageMessageDao;
import com.whatsappbuisness.wsbuissness.CombinePackadge.Messages.TemplateMessageDao.ComponentsDao;
import com.whatsappbuisness.wsbuissness.CombinePackadge.RabbitMqQue.QueName;
import com.whatsappbuisness.wsbuissness.CombinePackadge.RabbitMqQue.RabbitMqqueService;
import com.whatsappbuisness.wsbuissness.CombinePackadge.Subscription.SubscriptionDao;
import com.whatsappbuisness.wsbuissness.CombinePackadge.Subscription.SubscriptionService;
import com.whatsappbuisness.wsbuissness.CombinePackadge.catalog.CatalogMessageService;
import com.whatsappbuisness.wsbuissness.CombinePackadge.whatsappCloudAPI.*;
import okhttp3.*;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.tika.Tika;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.stereotype.Service;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.multipart.MultipartFile;


import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;
@Service
public class MessageSendServiceImpl implements MessageSendService {
    public static final MediaType mediaType = MediaType.get("application/json; charset=utf-8");
    private static final Logger logger= LoggerFactory.getLogger(MessageSendServiceImpl.class);
    @Autowired
    RabbitMqqueService rabbitMqqueService;
    @Autowired
    TokenService tokenService;

    @Autowired
    KarixMessageService karixMessageService;

    @Autowired
    CloudAPIService cloudAPIService;

    @Autowired
    private CatalogMessageService catalogMessageService;
    @Autowired
    SubscriptionService subscriptionService;
    private MessageDao sendWaUnOfMessage(MessageDao messageDao)
    {
        return  messageDao;
    }


    private MessageDao sendWaMessage(MessageDao messageDao)
    {
        String json = messageDao.toJson();
        //logger.info("Before Sending Message type is {} {}", messageDao.getType(), json);
        RequestBody body = RequestBody.create(json, mediaType);
        Response response = null;
        try {
            Request request = new Request.Builder()
                    .url(messageDao.getSubscriptionDao().getBaseUrl() + "v1/messages/")
                    .method("POST", body)
                    .addHeader("Content-Type", "application/json")
                    .addHeader("Authorization", "Bearer " + tokenService.getToken(messageDao.getAccountId())).build();

            response = client.newCall(request).execute();
            String responseStr = response.body().string();

            int responseCode = response.code();
            if (responseCode >= 200 && responseCode <= 300) {
                messageDao.setMessageStatus(MessageStatus.SENT);

                JSONObject jsonObject = new JSONObject(responseStr);
                JSONArray arr = jsonObject.getJSONArray("messages");
                String messageId = arr.getJSONObject(0).getString("id");
                messageDao.setId(messageId);
                logger.info("After Successfully sending Message {}",responseStr);
            } else {
                messageDao.setMessageStatus(MessageStatus.FAILED);
                logger.error("Failed To Send Message {}",responseStr);
            }
        } catch (Exception e) {
            messageDao.setMessageStatus(MessageStatus.FAILED);
            e.printStackTrace();
        } finally {
            if (response != null) {
                response.close();
            }
        }
        return messageDao;
    }


    public static final OkHttpClient client = new OkHttpClient.Builder()
            .readTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
            .connectTimeout(60,TimeUnit.SECONDS)
            .connectionPool(new ConnectionPool(27,150,TimeUnit.SECONDS))
            .build();
    @Override
    public void hitApi(MessageDao messageDao) {
//        logger.info("MessageSendService hitAPI {}",messageDao);
        if(messageDao.getSubscriptionDao().isTest())
        {
            messageDao.setMessageStatus(MessageStatus.SENT);
           logger.info("Testing Message Send So Action Will Be Taken");
        }else {
            switch (messageDao.getMessageOf())
            {
                case WAOF:

                    if(messageDao.getSubscriptionDao().getGateway() == Gateway.KARIX){
                        messageDao = sendWaMessageByKarix(messageDao);
                    }else if(messageDao.getSubscriptionDao().getGateway() == Gateway.CLOUDAPI){
                        messageDao = sendMessageByCloudAPI(messageDao);
                    }
                    else{
                        messageDao= sendWaMessage(messageDao);
                    }


                    break;
                case WAUNOF:
                    messageDao= sendWaUnOfMessage(messageDao);
                    break;
            }
        }
        try{
            if(messageDao.getSubscriptionDao().isShowMessageContent() && messageDao.getType()==MessageType.template) {
                messageDao.setMessageContent(messageDao.getTemplate().getTemplateBodyText());
                List<ComponentsDao> components = messageDao.getTemplate().getComponents();
                for (int i = 0; i < components.size(); i++) {
                    if (components.get(i).getType().equalsIgnoreCase("body")) {
                        for (int j = 1; j <= components.get(i).getParameters().size(); j++) {
                            messageDao.setMessageContent(messageDao.getMessageContent().replace("{{" + j + "}}", components.get(i).getParameters().get(j-1).getText()));
                        }
                    }
                }
            }
        }catch (Exception ex){}
       rabbitMqqueService.pushToQue(QueName.Mongo_Que.routing,QueName.Mongo_Que.queName, messageDao);

    }

    private MessageDao sendMessageByCloudAPI(MessageDao messageDao) {

//        logger.info("sendMessageByCloudAPI {}",messageDao);
        if (messageDao.getType() == MessageType.CATALOGUE) {
            try{
                messageDao.getCatalogMessageDao().setTo(messageDao.getTo());
                catalogMessageService.sendCatalogueMessage(messageDao.getCatalogMessageDao(), messageDao.getSubscriptionDao());
            }catch (Exception ex){

            }

        } else {
            CloudAPIDao cloudAPIDao = prepareMessageforCloudAPI(messageDao);
            messageDao = cloudAPIService.sendMessageByCloudAPI(cloudAPIDao, messageDao);
        }

        return messageDao;
    }

    private CloudAPIDao prepareMessageforCloudAPI(MessageDao messageDao) {
        CloudAPIDao cloudAPIDao = new CloudAPIDao();
        cloudAPIDao.setMessaging_product("whatsapp");
        cloudAPIDao.setTo(messageDao.getTo());
        switch (messageDao.getType()) {
            case text:{
                cloudAPIDao.setPreview_url(false);
                cloudAPIDao.setType("text");
                Text text = new Text();
                text.setBody(messageDao.getText().getBody());
                cloudAPIDao.setText(text);
            }
            break;
            case image:{
                cloudAPIDao.setType("image");
                Image image = new Image();
                try{
                    if(messageDao.getImage().getId().length()>10){
                        image.setId(messageDao.getImage().getId());
                    }
                }catch (NullPointerException ex){
//                    image.setLink(messageDao.getImage().getLink());
                    if(messageDao.getImage().getLink().equalsIgnoreCase("") || messageDao.getImage().getLink().equalsIgnoreCase("NA")){
                        MediaCommonObj mediaCommonObj = new MediaCommonObj();
                        mediaCommonObj.setBaseContent(messageDao.getAttachementBase64());
                        image.setLink(convertBase64ToShareableLink(mediaCommonObj,messageDao.getAccountId()));
                    }else{
                        image.setLink(messageDao.getImage().getLink());                    }
                }
                try{
                    image.setCaption(messageDao.getImage().getCaption());
                }catch (NullPointerException e){}

                cloudAPIDao.setImage(image);

            }
            break;
            case document:{
                cloudAPIDao.setType("document");
                Document document = new Document();
                document.setFilename(messageDao.getDocument().getFilename());
                try{
                    if(messageDao.getDocument().getId().length()>10){
                        document.setId(messageDao.getDocument().getId());
                    }
                }catch (NullPointerException ex){
//                    document.setLink(messageDao.getDocument().getLink());
                    if(messageDao.getDocument().getLink().equalsIgnoreCase("") || messageDao.getDocument().getLink().equalsIgnoreCase("NA")){
                        MediaCommonObj mediaCommonObj = new MediaCommonObj();
                        mediaCommonObj.setBaseContent(messageDao.getAttachementBase64());
                        document.setLink(convertBase64ToShareableLink(mediaCommonObj,messageDao.getAccountId()));
                    }else{
                        document.setLink(messageDao.getDocument().getLink());
                }

                }
                try{
                    document.setCaption(messageDao.getDocument().getCaption());
                }catch (NullPointerException e){}
                cloudAPIDao.setDocument(document);
            }
            break;
            case audio:{
                cloudAPIDao.setType("audio");
                Audio audio = new Audio();
                try{
                    if(messageDao.getAudio().getId().length()>10){
                        audio.setId(messageDao.getAudio().getId());
                    }
                }catch (NullPointerException ex){
//                    audio.setLink(messageDao.getAudio().getLink());
                    if(messageDao.getAudio().getLink().equalsIgnoreCase("") || messageDao.getAudio().getLink().equalsIgnoreCase("NA")){
                        MediaCommonObj mediaCommonObj = new MediaCommonObj();
                        mediaCommonObj.setBaseContent(messageDao.getAttachementBase64());
                        audio.setLink(convertBase64ToShareableLink(mediaCommonObj,messageDao.getAccountId()));
                    }else{
                        audio.setLink(messageDao.getAudio().getLink());
                    }
                }
                cloudAPIDao.setAudio(audio);
            }
            break;
            case video:{
                cloudAPIDao.setType("video");
                Video video = new Video();
                try{
                    if(messageDao.getVideo().getId().length()>10){
                        video.setId(messageDao.getVideo().getId());
                    }
                }catch (NullPointerException ex){
//                    video.setLink(messageDao.getVideo().getLink());
                    if(messageDao.getVideo().getLink().equalsIgnoreCase("") || messageDao.getVideo().getLink().equalsIgnoreCase("NA")){
                        MediaCommonObj mediaCommonObj = new MediaCommonObj();
                        mediaCommonObj.setBaseContent(messageDao.getAttachementBase64());
                        video.setLink(convertBase64ToShareableLink(mediaCommonObj,messageDao.getAccountId()));
                    }else{
                        video.setLink(messageDao.getVideo().getLink());
                    }
                }
                try{
                    video.setCaption(messageDao.getVideo().getCaption());
                }catch (NullPointerException e){}
                cloudAPIDao.setVideo(video);
            }
            break;
            case interactive:{
                cloudAPIDao.setType("interactive");
                cloudAPIDao.setInteractive(messageDao.getInteractive());
            }break;
            case location:{
                cloudAPIDao.setType("location");
                cloudAPIDao.setLocation(messageDao.getLocation());
            }
            break;
            case template:{
                cloudAPIDao.setRecipient_type("individual");
                cloudAPIDao.setType("template");
                com.whatsappbuisness.wsbuissness.CombinePackadge.whatsappCloudAPI.Template template = new com.whatsappbuisness.wsbuissness.CombinePackadge.whatsappCloudAPI.Template();
                Language language = new Language();
                language.setCode(messageDao.getTemplate().getLanguage().getCode());
                template.setLanguage(language);
                template.setName(messageDao.getTemplate().getName());
                List<Components> components = new ArrayList<>();
                int buttonIndex = 0;
                for (ComponentsDao componentsDao : messageDao.getTemplate().getComponents()) {
                    if(componentsDao.getType().equalsIgnoreCase("header")){
                        Components component = new Components();
                        component.setType("header");
                        List<Parameters> parameters = new ArrayList<>();
                        for(int j = 0;j<componentsDao.getParameters().size();j++){
                            Parameters parameter = new Parameters();
                            if(componentsDao.getParameters().get(j).getType().equalsIgnoreCase("image")){

                                parameter.setType("image");
                                Image image = new Image();
//                                image.setLink(componentsDao.getParameters().get(0).getImage().getLink());
                                if(componentsDao.getParameters().get(0).getImage().getLink().equalsIgnoreCase("") || componentsDao.getParameters().get(0).getImage().getLink().equalsIgnoreCase("NA")){
                                    try{
                                        Thread.sleep(5000);
                                        image.setLink(convertBase64ToShareableLink(componentsDao.getParameters().get(0).getImage(), messageDao.getAccountId()));
                                    }catch (Exception e){
                                        logger.info("Base64 url i not created");
                                    }
                                }else{
                                    image.setLink(componentsDao.getParameters().get(0).getImage().getLink());
                                }
                                parameter.setImage(image);
                                parameters.add(parameter);

                            }else if(componentsDao.getParameters().get(j).getType().equalsIgnoreCase("document")){

                                parameter.setType("document");
                                Document document = new Document();
//                                document.setLink(componentsDao.getParameters().get(0).getDocument().getLink());
                                if(componentsDao.getParameters().get(0).getDocument().getLink().equalsIgnoreCase("") || componentsDao.getParameters().get(0).getDocument().getLink().equalsIgnoreCase("NA")){
                                    try{
                                        Thread.sleep(5000);
                                        document.setLink(convertBase64ToShareableLink(componentsDao.getParameters().get(0).getDocument(), messageDao.getAccountId()));
                                    }catch (Exception e){
                                        logger.info("Base64 url i not created");
                                    }
                                }else{
                                    document.setLink(componentsDao.getParameters().get(0).getDocument().getLink());
                                }
                                parameter.setDocument(document);
                                parameters.add(parameter);
                            }
                            else if(componentsDao.getParameters().get(j).getType().equalsIgnoreCase("video")){

                                parameter.setType("video");
                                Video video = new Video();
//                                video.setLink(componentsDao.getParameters().get(0).getVideo().getLink());
                                if(componentsDao.getParameters().get(0).getVideo().getLink().equalsIgnoreCase("") || componentsDao.getParameters().get(0).getVideo().getLink().equalsIgnoreCase("NA")){
                                    try{
                                        Thread.sleep(5000);
                                        video.setLink(convertBase64ToShareableLink(componentsDao.getParameters().get(0).getVideo(), messageDao.getAccountId()));
                                    }catch (Exception e){
                                        logger.info("Base64 url i not created");
                                    }
                                }else{
                                    video.setLink(componentsDao.getParameters().get(0).getVideo().getLink());
                                }
                                parameter.setVideo(video);
                                parameters.add(parameter);
                            }
                        }
                        component.setParameters(parameters);
                        components.add(component);
                    }
                    if(componentsDao.getType().equalsIgnoreCase("body")){
                        Components component = new Components();
                        component.setType("body");
                        List<Parameters> parameters = new ArrayList<>();
                        for(int j = 0;j<componentsDao.getParameters().size();j++){
                            if(componentsDao.getParameters().get(j).getType().equalsIgnoreCase("text")){
                                Parameters parameter = new Parameters();
                                parameter.setType("text");
                                parameter.setText(componentsDao.getParameters().get(j).getText());
                                parameters.add(parameter);
                            }
                        }
                        component.setParameters(parameters);
                        components.add(component);
                    }
                    if(componentsDao.getType().equalsIgnoreCase("button")){
                        if(componentsDao.getSub_type().equalsIgnoreCase("url")){
                            if(componentsDao.getUrlType().equalsIgnoreCase("static")){
                                logger.info("The Static url button Called:: No need Object");
                            }else{
                                Components component = new Components();
                                component.setType("button");
                                component.setSub_type("url");
                                List<Parameters> parameters = new ArrayList<>();
                                component.setIndex(String.valueOf(buttonIndex));
                                Parameters parameter = new Parameters();
                                parameter.setType("text");
                                parameter.setText(componentsDao.getParameters().get(0).getText());
                                parameters.add(parameter);
                                component.setParameters(parameters);
                                components.add(component);
                                buttonIndex++;
                            }
                        }else{
                            Components component = new Components();
                            component.setType("button");
                            component.setSub_type("quick_reply");
                            List<Parameters> parameters = new ArrayList<>();
                            component.setIndex(String.valueOf(buttonIndex));
                            Parameters parameter = new Parameters();
                            parameter.setType("payload");
                            parameter.setPayload("PAYLOAD");
                            parameters.add(parameter);
                            component.setParameters(parameters);
                            components.add(component);
                        }
                    }//inner if closed
                }// component List Closed
                template.setComponents(components);
                cloudAPIDao.setTemplate(template);
            }//template case part closed
            break;
        }
        return cloudAPIDao;
    }

    public MessageDao sendWaMessageByKarix(MessageDao messageDao) {
        KarixMessageDao karixMessageDao = prepareMessageForKarix(messageDao);
        messageDao = karixMessageService.sendMessageKarix(karixMessageDao, messageDao);
        return messageDao;
    }

    private KarixMessageDao prepareMessageForKarix(MessageDao messageDao) {
        KarixMessageDao karixMessageDao = new KarixMessageDao();
        Message message = new Message();
        message.setChannel("WABA");
        Preferences preferences = new Preferences();
        preferences.setWebHookDNId("1001");
        message.setPreferences(preferences);
        Recipient recipient = new Recipient();
        recipient.setTo(messageDao.getTo());
        recipient.setRecipient_type("individual");
        Reference reference = new Reference();
        reference.setMessageTag1("Message Tag Val1");
        reference.setConversationId("Some Optional Conversation ID");
        reference.setCust_ref("Some Customer Ref");
        recipient.setReference(reference);
        message.setRecipient(recipient);
        Sender sender = new Sender();
        sender.setFrom(messageDao.getSubscriptionDao().getWaVerfiedNumber());
        message.setSender(sender);
        MetaData metaData = new MetaData();
        metaData.setVersion("v1.0.9");
        karixMessageDao.setMetaData(metaData);
        Content content = new Content();
        content.setPreview_url(false);
        switch (messageDao.getType()) {
            case image:{
                content.setType("ATTACHMENT");
                Attachment attachment = new Attachment();
                attachment.setType("image");
                attachment.setCaption(messageDao.getImage().getCaption());
                attachment.setAttachmentData(messageDao.getAttachementBase64());
                attachment.setMimeType(messageDao.getMimeType());
                content.setAttachment(attachment);
            }
                break;
            case document:{
                content.setType("ATTACHMENT");
                Attachment attachment = new Attachment();
                attachment.setType("document");
                attachment.setFileName(messageDao.getDocument().getFilename());
                attachment.setCaption(messageDao.getDocument().getCaption());
                attachment.setAttachmentData(messageDao.getAttachementBase64());
                attachment.setMimeType(messageDao.getMimeType());
                content.setAttachment(attachment);
            }
            break;
            case text:{
                content.setType("TEXT");
                content.setText(messageDao.getText().getBody());
            }
            break;
            case interactive:{
                content.setType("interactive");
                content.setInteractive(messageDao.getInteractive());
            }
            break;
            case location:{
                content.setType("location");
                content.setLocation(messageDao.getLocation());
            }
            break;
            case template:{
                int parameterCount = 0;
                content.setType("TEMPLATE");
                MediaTemplate mediaTemplate = new MediaTemplate();
                for (ComponentsDao componentsDao : messageDao.getTemplate().getComponents()) {

//                    logger.info("The value of ComponentsDao is "+componentsDao.getType() );
                    HashMap<Integer,String> bodyParamterValues = new HashMap<>();
                    if(componentsDao.getType().equalsIgnoreCase("header")){
                        for(int j = 0;j<componentsDao.getParameters().size();j++){

                            if(componentsDao.getParameters().get(j).getType().equalsIgnoreCase("image")){
                                content.setType("MEDIA_TEMPLATE");
                                mediaTemplate.setTemplateId(messageDao.getTemplate().getName());
                                Media media = new Media();
                                media.setType(componentsDao.getParameters().get(j).getType());
                                if(componentsDao.getParameters().get(0).getImage().getLink().equalsIgnoreCase("") || componentsDao.getParameters().get(0).getImage().getLink().equalsIgnoreCase("NA")){
                                    try {
                                        Thread.sleep(5000);
                                        media.setUrl(convertBase64ToShareableLink(componentsDao.getParameters().get(0).getImage(), messageDao.getAccountId()));
                                    }catch (Exception e){
                                        logger.info(messageDao.getAccountId() + " base 64 url is not created");
                                    }
                                }else{
                                    media.setUrl(componentsDao.getParameters().get(0).getImage().getLink());
                                }
                                mediaTemplate.setMedia(media);
                                content.setMediaTemplate(mediaTemplate);

                            }else if(componentsDao.getParameters().get(j).getType().equalsIgnoreCase("document")){
                                content.setType("MEDIA_TEMPLATE");
                                mediaTemplate.setTemplateId(messageDao.getTemplate().getName());
                                Media media = new Media();
                                media.setType(componentsDao.getParameters().get(j).getType());
                                if(componentsDao.getParameters().get(0).getDocument().getLink().equalsIgnoreCase("") || componentsDao.getParameters().get(0).getDocument().getLink().equalsIgnoreCase("NA")){
                                    try {
                                        Thread.sleep(5000);
                                        media.setUrl(convertBase64ToShareableLink(componentsDao.getParameters().get(0).getDocument(), messageDao.getAccountId()));
                                    }catch (Exception e){
                                        logger.info(messageDao.getAccountId() + " base 64 url is not created");
                                    }
                                }else{
                                    media.setUrl(componentsDao.getParameters().get(0).getDocument().getLink());
                                }
                                media.setFileName(componentsDao.getParameters().get(0).getDocument().getFilename());
                                mediaTemplate.setMedia(media);
                                content.setMediaTemplate(mediaTemplate);
                            }
                            else if(componentsDao.getParameters().get(j).getType().equalsIgnoreCase("video")){
                                content.setType("MEDIA_TEMPLATE");
                                mediaTemplate.setTemplateId(messageDao.getTemplate().getName());
                                Media media = new Media();
                                media.setType(componentsDao.getParameters().get(j).getType());
                                media.setFileName(componentsDao.getParameters().get(0).getVideo().getFilename());
                                if(componentsDao.getParameters().get(0).getVideo().getLink().equalsIgnoreCase("") || componentsDao.getParameters().get(0).getVideo().getLink().equalsIgnoreCase("NA")){
                                    try {
                                        Thread.sleep(5000);
                                        media.setUrl(convertBase64ToShareableLink(componentsDao.getParameters().get(0).getVideo(), messageDao.getAccountId()));
                                    }catch (Exception e){
                                        logger.info(messageDao.getAccountId() + " base 64 url is not created");
                                    }
                                }else{
                                    media.setUrl(componentsDao.getParameters().get(0).getVideo().getLink());
                                }
                                mediaTemplate.setMedia(media);
                                content.setMediaTemplate(mediaTemplate);
                            }
                        }
                    }
                    if(componentsDao.getType().equalsIgnoreCase("button")){
                        if(componentsDao.getSub_type().equalsIgnoreCase("url")){

                            Buttons buttons = new Buttons();
                            List<Actions> actions = new ArrayList<>();

                            for(int j = 0;j<componentsDao.getParameters().size();j++){
                                if(componentsDao.getParameters().get(j).getType().equalsIgnoreCase("text")){
                                    Actions actions1 = new Actions();
                                    actions1.setIndex("0");
                                    actions1.setType("url");
                                    actions1.setPayload(componentsDao.getParameters().get(j).getText().trim());
                                    actions.add(actions1);
                                }
                            }
                            buttons.setActions(actions);
                            mediaTemplate.setButtons(buttons);
                            content.setMediaTemplate(mediaTemplate);

                        }else{
                            logger.info("Button is static either reply, Call or static url ");
                        }
                    }

                    if(componentsDao.getType().equalsIgnoreCase("body")){
                        for(int j = 0;j<componentsDao.getParameters().size();j++){
                            if(componentsDao.getParameters().get(j).getType().equalsIgnoreCase("text")){
                                bodyParamterValues.put(j, componentsDao.getParameters().get(j).getText());
                            }
                        }
                        if(content.getType().equalsIgnoreCase("MEDIA_TEMPLATE")){
                            mediaTemplate.setBodyParameterValues(bodyParamterValues);
                            content.setMediaTemplate(mediaTemplate);
                        }else{
                            Template template = new Template();
                            template.setTemplateId(messageDao.getTemplate().getName());
                            template.setParameterValues(bodyParamterValues);
                            content.setTemplate(template);
                            parameterCount++;
                        }
                    }
                }// component List Closed
                if(content.getType().equalsIgnoreCase("TEMPLATE") && parameterCount == 0){
                    Template template = new Template();
                    template.setTemplateId(messageDao.getTemplate().getName());
                    content.setTemplate(template);
                }
            }//template case part closed
                break;
            case video:{
                content.setType("ATTACHMENT");
                Attachment attachment = new Attachment();
                attachment.setType("video");
                attachment.setCaption(messageDao.getVideo().getCaption());
                attachment.setAttachmentData(messageDao.getAttachementBase64());
                attachment.setMimeType(messageDao.getMimeType());
                content.setAttachment(attachment);

            }
                break;
        }
        if(content.getType().equalsIgnoreCase(null)){
            content.setType("TEMPLATE");
        }
        if(content.getType().equalsIgnoreCase("TEMPLATE")){
            for (ComponentsDao componentsDao : messageDao.getTemplate().getComponents()) {
                if(componentsDao.getType().equalsIgnoreCase("button")){
                    HashMap<Integer, String> parameterValues = content.getTemplate().getParameterValues();
                    content.getMediaTemplate().setBodyParameterValues(parameterValues);
                    content.getMediaTemplate().setTemplateId(content.getTemplate().getTemplateId());
                    content.setTemplate(null);
                    content.setType("MEDIA_TEMPLATE");
                }
            }
        }
        message.setContent(content);
        karixMessageDao.setMessage(message);
//        logger.info("Message is in the prepareMessageForKarix in karixMessageDaoafter part {}",karixMessageDao);
        return karixMessageDao;
    }

    private String convertBase64ToShareableLink(MediaCommonObj media, long accountID) {
        String fileName="file";
        if(media.getBaseContent()!=null){
            try {
            byte[] downloadfile = Base64.getDecoder().decode(media.getBaseContent());
                InputStream inputStream = new ByteArrayInputStream(downloadfile);
                String mimeType = new Tika().detect(inputStream);
                if(mimeType!= "" && mimeType!=null){
                    if(mimeType.equalsIgnoreCase("video/quicktime")){
                       mimeType = "video/mp4";
                    }
                    if(!StringUtils.isEmpty(mimeType))
                    {
                        String fileExtension = MimeTypeUtils.parseMimeType(mimeType).getSubtype();
                        fileName = fileName+"."+fileExtension;
                    }
                    return storeFileTODrive(new MockMultipartFile(fileName, fileName, mimeType, downloadfile),accountID , fileName);
                }
                return "No Link Generated..";
            }catch (Exception e){
                e.printStackTrace();
                return "Exception Occurs";
            }
        }else{
            return "No Link Generated";
        }

    }
    private String storeFileTODrive(MultipartFile multipartFile, long accountId, String fileName) {
        try {
            byte[] bytes = multipartFile.getBytes();
            RequestBody body = RequestBody.create(bytes,MediaType.parse(multipartFile.getContentType()));
            RequestBody requestBody = new MultipartBody.Builder().setType(MultipartBody.FORM)
                    .addFormDataPart("file",fileName,body)
                    .build();
            Request request = new Request.Builder()
                    .url("https://wa.chatmybot.in/gateway/drive/v1/drive/?id="+accountId)
                    .post(requestBody)
                    .build();
            Response response = null;
            response = client.newCall(request).execute();
            String resString = response.body().string();
            logger.info("The response from Drive is "+ resString);
            JSONObject jsonObject = new JSONObject(resString);
            long id = jsonObject.getLong("id");
            return "https://wa.chatmybot.in/Shareablelinks/" +accountId+"/" + id + '/' + fileName;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
