package com.whatsappbuisness.wsbuissness.CombinePackadge.MessageEntry;

import com.whatsappbuisness.wsbuissness.CombinePackadge.Common.SessionRetrival.SessionRetrievalService;
import com.whatsappbuisness.wsbuissness.CombinePackadge.Common.SessionRetrival.UsermasterDao;
import com.whatsappbuisness.wsbuissness.CombinePackadge.Filtering.FilterDao;
import com.whatsappbuisness.wsbuissness.CombinePackadge.Messages.AudioMessage.AudioMessageDao;
import com.whatsappbuisness.wsbuissness.CombinePackadge.Messages.ContactsMessageDao.ContactsMessageDao;
import com.whatsappbuisness.wsbuissness.CombinePackadge.Messages.DocumentMessage.DocumentMessageDao;
import com.whatsappbuisness.wsbuissness.CombinePackadge.Messages.ImageMessage.ImageMessageDao;
import com.whatsappbuisness.wsbuissness.CombinePackadge.Messages.InteractiveMessageDao.InteractiveMessageDao;
import com.whatsappbuisness.wsbuissness.CombinePackadge.Messages.Location.LocationMessageDao;
import com.whatsappbuisness.wsbuissness.CombinePackadge.Messages.MediaCommonObj;
import com.whatsappbuisness.wsbuissness.CombinePackadge.Messages.MessageDao;
import com.whatsappbuisness.wsbuissness.CombinePackadge.Messages.MessageStatus;
import com.whatsappbuisness.wsbuissness.CombinePackadge.Messages.MessageType;
import com.whatsappbuisness.wsbuissness.CombinePackadge.Messages.TemplateMessageDao.ComponentsDao;
import com.whatsappbuisness.wsbuissness.CombinePackadge.Messages.TemplateMessageDao.ParametersDao;
import com.whatsappbuisness.wsbuissness.CombinePackadge.Messages.TemplateMessageDao.TemplateText.TemplateMessageDao;
import com.whatsappbuisness.wsbuissness.CombinePackadge.Messages.TextMessage.TextMessageDao;
import com.whatsappbuisness.wsbuissness.CombinePackadge.Messages.VideoMessage.VideoMessageDao;
import com.whatsappbuisness.wsbuissness.CombinePackadge.PaginationDao;
import com.whatsappbuisness.wsbuissness.CombinePackadge.RabbitMqQue.MongoQue.MongoDB.GroupByMongoDao;
import com.whatsappbuisness.wsbuissness.CombinePackadge.RabbitMqQue.MongoQue.MongoDB.MessageDeliveryStatusDao;
import com.whatsappbuisness.wsbuissness.CombinePackadge.RabbitMqQue.MongoQue.MongoDB.MessagePersistService;
import com.whatsappbuisness.wsbuissness.CombinePackadge.Template.TemplateMessageService;
import com.whatsappbuisness.wsbuissness.CombinePackadge.TokenStore.TokenGenerationErrorHandling;
import com.whatsappbuisness.wsbuissness.CombinePackadge.TokenStore.TokenStoreDao;
import com.whatsappbuisness.wsbuissness.CombinePackadge.TokenStore.TokenStoreService;
import com.whatsappbuisness.wsbuissness.CombinePackadge.whatsappCloudAPI.*;
import okhttp3.*;
import org.apache.commons.lang3.StringUtils;
import org.apache.tika.Tika;
import org.bouncycastle.util.Arrays;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.core.Authentication;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

/*
 Author=Supreet Singh
 Date= 11/03/21 11:32 AM
*/
@RestController
@RequestMapping("/message")
public class MessageEntryController {
    @Autowired
    MessageEntryService entryService;
    @Autowired
    SessionRetrievalService sessionRetrievalService;
    @Autowired
    MessagePersistService messagePersistService;
    @Autowired
    TemplateMessageService templateMessageService;

    @Autowired
    TokenStoreService tokenStoreService;

    private static final Logger logger = LoggerFactory.getLogger(MessageEntryController.class);

    public static final OkHttpClient client = new OkHttpClient.Builder()
            .readTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
            .connectTimeout(60,TimeUnit.SECONDS)
            .connectionPool(new ConnectionPool(27,150,TimeUnit.SECONDS))
            .build();


    @PostMapping("/")
    public ResponseEntity<MessageEntryResponseDao> insertMessage(@RequestBody MessageDao messageDao, Authentication authentication) throws Exception {
        UsermasterDao usermasterDao = sessionRetrievalService.get(authentication);
        messageDao.setAccountId(usermasterDao.getAccountId());
        MessageEntryResponseDao messageEntryResponseDao = entryService.messageInsertService(messageDao,true);
        if (messageEntryResponseDao.getStatus() == MessageStatus.SENT) {
            return new ResponseEntity<MessageEntryResponseDao>(messageEntryResponseDao, HttpStatus.OK);
        } else {
            return new ResponseEntity<MessageEntryResponseDao>(messageEntryResponseDao, HttpStatus.BAD_REQUEST);
        }


    }


    /*     Will Prepare Message
        And REmove Unneccessary Things*/
    List<MessageDao> prepareMessage(List<MessageDao> messageDaos) throws ValidateFailedExeption {
        MessageDao messageDao;
        for (int i = 0; i < messageDaos.size(); i++) {
            messageDao = messageDaos.get(i);
            switch (messageDao.getType()) {
                case image:

                    checkImage(messageDao.getImage());
                    break;
                case text:
                    checkText(messageDao.getText());
                    break;
                case template:
                    messageDao.setTemplate(checkTemplate(messageDao.getTemplate()));
                    break;
      /*          case contacts:
                    checkContact(messageDao.getContacts());
                    break;*/
                case document:
                    checkDocument(messageDao.getDocument());
                    break;
                case location:
                    checkLocation(messageDao.getLocation());
                    break;
                case video:
                    checkVideo(messageDao.getVideo());
                    break;
                case audio:
                    checkAudio(messageDao.getAudio());
                    break;
                case interactive:
                    checkInteractive(messageDao.getInteractive());
                    break;

                case voice:
                    break;


            }


            messageDaos.set(i, messageDao);
        }


        return messageDaos;
    }

    private void checkAudio(AudioMessageDao messageDao) throws ValidateFailedExeption {
        if (messageDao == null) {
            throw new ValidateFailedExeption("No Audio Object Can be Found!!");
        }
        if (StringUtils.isEmpty(messageDao.getId()) && StringUtils.isEmpty(messageDao.getLink())) {
            throw new ValidateFailedExeption("No Audio Id or Link Can be Found!!");
        }
        if (!StringUtils.isEmpty(messageDao.getId())) {
            messageDao.setLink(null);
        }

        if (!StringUtils.isEmpty(messageDao.getLink())) {
            messageDao.setId(null);
        }
    }

    private void checkInteractive(InteractiveMessageDao messageDao) throws ValidateFailedExeption {
        if (messageDao == null) {
            throw new ValidateFailedExeption("No Interactive Object Can be Found!!");
        }
        if (messageDao.getType() != MessageType.list && messageDao.getType() != MessageType.button) {
            throw new ValidateFailedExeption("No supported type found!!");
        }
        if(messageDao.getType()==MessageType.list && messageDao.getAction().getSections() == null){
            throw new ValidateFailedExeption("Sections can't be null for type LIST message!!");
        }
        if(messageDao.getType()==MessageType.button && messageDao.getAction().getButtons() == null){
            throw new ValidateFailedExeption("Buttons can't be null for type BUTTON message!!");
        }

    }

    private void checkVideo(VideoMessageDao messageDao) throws ValidateFailedExeption {
        if (messageDao == null) {
            throw new ValidateFailedExeption("No Video Object Can be Found!!");
        }
        if (StringUtils.isEmpty(messageDao.getId()) && StringUtils.isEmpty(messageDao.getLink())) {
            throw new ValidateFailedExeption("No Video Id or Link Can be Found!!");
        }
        if (!StringUtils.isEmpty(messageDao.getId())) {
            messageDao.setLink(null);
        }

        if (!StringUtils.isEmpty(messageDao.getLink())) {
            messageDao.setId(null);
        }
    }

    private void checkLocation(LocationMessageDao location) throws ValidateFailedExeption {
        if (location == null) {
            throw new ValidateFailedExeption("No Location Object Can be Found!!");
        }
    }

    private void checkContact(ContactsMessageDao message) throws ValidateFailedExeption {
        if (message == null) {
            throw new ValidateFailedExeption("No Contact Object Can be Found!!");
        }

    }

    private void checkDocument(DocumentMessageDao messageDao) throws ValidateFailedExeption {
        if (messageDao == null) {
            throw new ValidateFailedExeption("No Document Object Can be Found!!");
        }
        if (StringUtils.isEmpty(messageDao.getId()) && StringUtils.isEmpty(messageDao.getLink())) {
            throw new ValidateFailedExeption("No Document Id or Link Can be Found!!");
        }
        if (!StringUtils.isEmpty(messageDao.getId())) {
            messageDao.setLink(null);
        }

        if (!StringUtils.isEmpty(messageDao.getLink())) {
            messageDao.setId(null);
        }
    }

    private void checkText(TextMessageDao message) throws ValidateFailedExeption {
        if (message == null) {
            throw new ValidateFailedExeption("No Text Object Can be Found!!");
        }
        if (StringUtils.isEmpty(message.getBody())) {
            throw new ValidateFailedExeption("No Text Found !!");
        }
    }

    private TemplateMessageDao checkTemplate(TemplateMessageDao message) throws ValidateFailedExeption {
        if (message == null) {
            throw new ValidateFailedExeption("No Template Object Can be Found!!");
        }
        if (StringUtils.isEmpty(message.getId())) {
            throw new ValidateFailedExeption("No Template Id found!!");
        }
        TemplateMessageDao templateMessageDao = templateMessageService.findById(message.getId());
        if (templateMessageDao == null) {

            throw new ValidateFailedExeption("Template Not Found!!");
        }
        if (!templateMessageDao.isStatus()) {
            throw new ValidateFailedExeption("Template Has Not Been Approved Yet!!");
        }
        HashMap<String, ValidateTemplate> hashMap = new HashMap<>();
        List<String> arr = null;

        for (ComponentsDao componentsDao : templateMessageDao.getComponents()) {
            arr = new ArrayList<>();

            for (ParametersDao parametersDao : componentsDao.getParameters()) {
                arr.add(parametersDao.getNameOfParams());
            }


            hashMap.put(componentsDao.getType(), new ValidateTemplate(String.join(",", arr), arr.size()));

        }

       /* if (templateMessageDao.getComponents().size() != message.getComponents().size()) {
            throw new ValidateFailedExeption("Number of Paramter Type Not Matching");
        }*/
        for(int k=0;k<message.getComponents().size();k++){
//        for (ComponentsDao componentsDao : message.getComponents()) {
            ValidateTemplate validateTemplate = hashMap.get(message.getComponents().get(k).getType());
            if (message.getComponents().get(k).getParameters().size() != validateTemplate.getSize()) {
                throw new ValidateFailedExeption("Required Parameter Cannot be Found  inside " + message.getComponents().get(k).getType() + "!! Required are " + validateTemplate.getParams());
            }
            if(message.getComponents().get(k).getType().equalsIgnoreCase("header")){
                for(int j = 0;j<message.getComponents().get(k).getParameters().size();j++){
                    if(message.getComponents().get(k).getParameters().get(j).getType().equalsIgnoreCase("image")){
                        if(message.getComponents().get(k).getParameters().get(0).getImage().getLink().equalsIgnoreCase("") || message.getComponents().get(k).getParameters().get(0).getImage().getLink().equalsIgnoreCase("NA")){
                            try{
                                Thread.sleep(5000);
                                message.getComponents().get(k).getParameters().get(j).getImage().setLink(convertBase64ToShareableLink(message.getComponents().get(k).getParameters().get(0).getImage(), message.getAccountId()));
                                message.getComponents().get(k).getParameters().get(j).getImage().setBaseContent("");
                            }catch (Exception e){
                                logger.info("Base64 url i not created");
                            }
                        }
                    }else if(message.getComponents().get(k).getParameters().get(j).getType().equalsIgnoreCase("document")){
                        if(message.getComponents().get(k).getParameters().get(0).getDocument().getLink().equalsIgnoreCase("") || message.getComponents().get(k).getParameters().get(0).getDocument().getLink().equalsIgnoreCase("NA")){
                            try{
                                Thread.sleep(5000);
                                message.getComponents().get(k).getParameters().get(j).getDocument().setLink(convertBase64ToShareableLink(message.getComponents().get(k).getParameters().get(0).getDocument(), message.getAccountId()));
                                message.getComponents().get(k).getParameters().get(j).getDocument().setBaseContent("");
                            }catch (Exception e){
                                logger.info("Base64 url i not created");
                            }
                        }
                    }
                    else if(message.getComponents().get(k).getParameters().get(j).getType().equalsIgnoreCase("video")){
                        if(message.getComponents().get(k).getParameters().get(0).getVideo().getLink().equalsIgnoreCase("") || message.getComponents().get(k).getParameters().get(0).getVideo().getLink().equalsIgnoreCase("NA")){
                            try{
                                Thread.sleep(5000);
                                message.getComponents().get(k).getParameters().get(0).getVideo().setLink(convertBase64ToShareableLink(message.getComponents().get(k).getParameters().get(0).getVideo(), message.getAccountId()));
                                message.getComponents().get(k).getParameters().get(0).getVideo().setBaseContent("");
                            }catch (Exception e){
                                logger.info("Base64 url i not created");
                            }
                        }
                    }
                }
            }
        }
        templateMessageDao.setComponents(message.getComponents());
        return templateMessageDao;
    }

    private void checkImage(ImageMessageDao messageDao) throws ValidateFailedExeption {
        if (messageDao == null) {
            throw new ValidateFailedExeption("No Image Object Can be Found!!");
        }
        if (StringUtils.isEmpty(messageDao.getId()) && StringUtils.isEmpty(messageDao.getLink())) {
            throw new ValidateFailedExeption("No Image Id or Link Can be Found!!");
        }
        if (!StringUtils.isEmpty(messageDao.getId())) {
            messageDao.setLink(null);
        }
        if (!StringUtils.isEmpty(messageDao.getLink())) {
            messageDao.setId(null);
        }

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
            okhttp3.RequestBody body = okhttp3.RequestBody.create(bytes, MediaType.parse(multipartFile.getContentType()));
            okhttp3.RequestBody requestBody = new MultipartBody.Builder().setType(MultipartBody.FORM)
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

    @PostMapping("/batchapi")
    public ResponseEntity<MessageEntryResponseDao> MessageEntryApiResponseDao(@RequestBody List<MessageDao> messageDao, HttpServletRequest httpServletRequest) throws Exception {
        TokenStoreDao tokenStoreDao = sessionRetrievalService.validate(httpServletRequest);
        MessageEntryResponseDao messageEntryResponseDao = new MessageEntryResponseDao();

        try {
            for(int i=0;i<messageDao.size();i++){
                if(messageDao.get(i).getType() == MessageType.template){
                    messageDao.get(i).getTemplate().setAccountId(tokenStoreDao.getAccountId());
                }
            }
//            logger.info("Batchapi calling {}",messageDao);
            messageDao = prepareMessage(messageDao);
        } catch (ValidateFailedExeption ew) {
            messageEntryResponseDao = new MessageEntryResponseDao();
            messageEntryResponseDao.setMessage(ew.getMessage());
            messageEntryResponseDao.setStatus(MessageStatus.FAILED);
            messageEntryResponseDao.setIds(new ArrayList<>());
            return new ResponseEntity<MessageEntryResponseDao>(messageEntryResponseDao, HttpStatus.BAD_REQUEST);
        }

        messageEntryResponseDao = entryService.messageBulkInsertService(messageDao, tokenStoreDao.getAccountId(),false);
        if (messageEntryResponseDao.getStatus() == MessageStatus.SENT) {
            return new ResponseEntity<MessageEntryResponseDao>(messageEntryResponseDao, HttpStatus.OK);
        } else {
            return new ResponseEntity<MessageEntryResponseDao>(messageEntryResponseDao, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/batchapi")
    public ResponseEntity<MessageEntryResponseDao> base64MessageEntryApi(@RequestParam String templateId,@RequestParam String to, HttpServletRequest httpServletRequest) throws Exception {
        TokenStoreDao tokenStoreDao = sessionRetrievalService.validate(httpServletRequest);
        MessageEntryResponseDao messageEntryResponseDao = new MessageEntryResponseDao();
        List<MessageDao> messageDaoList =new ArrayList<MessageDao>();
        MessageDao message = new MessageDao();
        TemplateMessageDao templateMessageDao= templateMessageService.findById(templateId);
        if(templateMessageDao != null){
            List<ComponentsDao> components = templateMessageDao.getComponents();
            for(int i=0;i<components.size();i++){
                if(components.get(i).getType().equalsIgnoreCase("header") && components.get(i).getParameters().size()!=0 ){

                    messageEntryResponseDao = new MessageEntryResponseDao();
                    messageEntryResponseDao.setMessage("Template Contains Header which is not allowed");
                    messageEntryResponseDao.setStatus(MessageStatus.FAILED);
                    messageEntryResponseDao.setIds(new ArrayList<>());
                    return new ResponseEntity<MessageEntryResponseDao>(messageEntryResponseDao, HttpStatus.BAD_REQUEST);
                }
            }
            message.setTemplate(templateMessageDao);
            message.setType(MessageType.template);
            message.setTo(to);
            messageDaoList.add(message);

            logger.info("BatchBaseApi calling {}",messageDaoList);
            try {
                messageDaoList = prepareMessage(messageDaoList);
            } catch (ValidateFailedExeption ew) {
                messageEntryResponseDao = new MessageEntryResponseDao();
                messageEntryResponseDao.setMessage(ew.getMessage());
                messageEntryResponseDao.setStatus(MessageStatus.FAILED);
                messageEntryResponseDao.setIds(new ArrayList<>());
                return new ResponseEntity<MessageEntryResponseDao>(messageEntryResponseDao, HttpStatus.BAD_REQUEST);
            }
            messageEntryResponseDao = entryService.messageBulkInsertService(messageDaoList, tokenStoreDao.getAccountId(),false);
            if (messageEntryResponseDao.getStatus() == MessageStatus.SENT) {
                return new ResponseEntity<MessageEntryResponseDao>(messageEntryResponseDao, HttpStatus.OK);
            } else {
                return new ResponseEntity<MessageEntryResponseDao>(messageEntryResponseDao, HttpStatus.BAD_REQUEST);
            }
        }else{
            messageEntryResponseDao = new MessageEntryResponseDao();
            messageEntryResponseDao.setMessage("Template not Found");
            messageEntryResponseDao.setStatus(MessageStatus.FAILED);
            messageEntryResponseDao.setIds(new ArrayList<>());
            return new ResponseEntity<MessageEntryResponseDao>(messageEntryResponseDao, HttpStatus.BAD_REQUEST);
        }
    }


    @PostMapping("/getmessagesbyconversationapi")
    public List<MessageDao> getDataApi(@RequestBody FilterDao filterDao,  HttpServletRequest httpServletRequest) throws TokenGenerationErrorHandling {
        TokenStoreDao tokenStoreDao = sessionRetrievalService.validate(httpServletRequest);
        filterDao.setAccountId(tokenStoreDao.getAccountId());
        return messagePersistService.getReport(filterDao);
    }

    @PostMapping("/batch")
    public ResponseEntity<MessageEntryResponseDao> MessageEntryResponseDao(@RequestBody List<MessageDao> messageDao, Authentication authentication) throws Exception {
        UsermasterDao usermasterDao = sessionRetrievalService.get(authentication);
        MessageEntryResponseDao messageEntryResponseDao = new MessageEntryResponseDao();
        try {
            messageDao = prepareMessage(messageDao);
        } catch (ValidateFailedExeption ew) {
            messageEntryResponseDao = new MessageEntryResponseDao();
            messageEntryResponseDao.setMessage(ew.getMessage());
            messageEntryResponseDao.setStatus(MessageStatus.FAILED);
            messageEntryResponseDao.setIds(new ArrayList<>());
            return new ResponseEntity<MessageEntryResponseDao>(messageEntryResponseDao, HttpStatus.BAD_REQUEST);
        }

        messageEntryResponseDao = entryService.messageBulkInsertService(messageDao, usermasterDao.getAccountId(),false);
        if (messageEntryResponseDao.getStatus() == MessageStatus.SENT) {
            return new ResponseEntity<MessageEntryResponseDao>(messageEntryResponseDao, HttpStatus.OK);
        } else {
            return new ResponseEntity<MessageEntryResponseDao>(messageEntryResponseDao, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/batchpanel")
    public ResponseEntity<MessageEntryResponseDao> MessageEntryResponseDaoPanel(@RequestBody List<MessageDao> messageDao, Authentication authentication) throws Exception {
      logger.info("batchpanel message dao {}",messageDao);
        UsermasterDao usermasterDao = sessionRetrievalService.get(authentication);
        MessageEntryResponseDao messageEntryResponseDao = new MessageEntryResponseDao();
        try {
            messageDao = prepareMessage(messageDao);
        } catch (ValidateFailedExeption ew) {
            logger.info("Validation Error occurs");
            messageEntryResponseDao = new MessageEntryResponseDao();
            messageEntryResponseDao.setMessage(ew.getMessage());
            messageEntryResponseDao.setStatus(MessageStatus.FAILED);
            messageEntryResponseDao.setIds(new ArrayList<>());
            return new ResponseEntity<MessageEntryResponseDao>(messageEntryResponseDao, HttpStatus.BAD_REQUEST);
        }catch (Exception ew) {
            logger.info("Error occurs {}", ew);
            messageEntryResponseDao = new MessageEntryResponseDao();
            messageEntryResponseDao.setMessage(ew.getMessage());
            messageEntryResponseDao.setStatus(MessageStatus.FAILED);
            messageEntryResponseDao.setIds(new ArrayList<>());
            return new ResponseEntity<MessageEntryResponseDao>(messageEntryResponseDao, HttpStatus.BAD_REQUEST);
        }
//        logger.info("will hit messageBulkInsertService method");
        messageEntryResponseDao = entryService.messageBulkInsertService(messageDao, usermasterDao.getAccountId(),true);
        if (messageEntryResponseDao.getStatus() == MessageStatus.SENT) {
            return new ResponseEntity<MessageEntryResponseDao>(messageEntryResponseDao, HttpStatus.OK);
        } else {
            return new ResponseEntity<MessageEntryResponseDao>(messageEntryResponseDao, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/getsts")
    public List<GroupByMongoDao> getByGroup(Authentication authentication, @RequestParam("id") long id) {
        UsermasterDao usermasterDao = sessionRetrievalService.get(authentication);
        return messagePersistService.get(usermasterDao.getAccountId(), id);
    }

    @PostMapping("/gettrans")
    public List<MessageDao> getData(@RequestParam("id") long id, Authentication authentication) {
        UsermasterDao usermasterDao = sessionRetrievalService.get(authentication);
        return messagePersistService.getReport(id, usermasterDao.getAccountId());

    }


    @PostMapping("/getmessagesbyconversation")
    public List<MessageDao> getData(@RequestBody FilterDao filterDao, Authentication authentication) {
        UsermasterDao usermasterDao = sessionRetrievalService.get(authentication);
        filterDao.setAccountId(usermasterDao.getAccountId());
        return messagePersistService.getReport(filterDao);
    }

    @PostMapping("/getmessagebydateandnumber")
    public List<MessageDao> getMessages(@RequestBody FilterDao filterDao, Authentication authentication) {
        UsermasterDao usermasterDao = sessionRetrievalService.get(authentication);
        filterDao.setAccountId(usermasterDao.getAccountId());
        return messagePersistService.getMessages(filterDao);
    }
    @GetMapping("/getdelieverystatusdatebasis")
    public List<MessageDeliveryStatusDao> getDelieveryStatusDateBasis(@RequestParam("startdate") String startDate, @RequestParam("enddate") String endDate, @RequestHeader("accessToken") String accessToken ) {
        TokenStoreDao tokenStoreDao = tokenStoreService.validate(accessToken);
        logger.info("startDate is {} EndDate is {} Account Id is {}",startDate,endDate,tokenStoreDao.getAccountId());
        return messagePersistService.getDelieveryStatusDateBasis(startDate,endDate,tokenStoreDao.getAccountId());
    }

    @GetMapping("/getmsgafter")
    public List<MessageDao> getAfterMessage(Authentication authentication, @RequestParam("dateTiming") long dateTiming, @RequestParam("to") String to) {
        UsermasterDao usermasterDao = sessionRetrievalService.get(authentication);
        return messagePersistService.getafterDate(usermasterDao.getAccountId(), dateTiming, to);
    }
    @PostMapping("/getincomingreport")
    public PaginationDao<MessageDao> getIncomingMessageReport(@RequestBody FilterDao filterDao, Authentication authentication) {
        UsermasterDao usermasterDao = sessionRetrievalService.get(authentication);
        filterDao.setAccountId(usermasterDao.getAccountId());
        return messagePersistService.getIncomingMessageReport(filterDao);
    }

    static class ValidateTemplate {
        @Override
        public String toString() {
            return "ValidateTemplate{" +
                    "params='" + params + '\'' +
                    ", size=" + size +
                    '}';
        }

        public String getParams() {
            return params;
        }

        public void setParams(String params) {
            this.params = params;
        }

        public int getSize() {
            return size;
        }

        public void setSize(int size) {
            this.size = size;
        }

        public ValidateTemplate(String params, int size) {
            this.params = params;
            this.size = size;
        }

        private String params;
        private int size;
    }
}

