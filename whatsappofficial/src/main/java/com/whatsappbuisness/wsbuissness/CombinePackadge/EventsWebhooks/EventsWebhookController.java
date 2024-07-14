package com.whatsappbuisness.wsbuissness.CombinePackadge.EventsWebhooks;

import com.google.gson.Gson;
import com.whatsappbuisness.wsbuissness.CombinePackadge.ApiService.Media.MediaService;
import com.whatsappbuisness.wsbuissness.CombinePackadge.CampaignSchedule.CampaignScheduleDao;
import com.whatsappbuisness.wsbuissness.CombinePackadge.Common.SessionRetrival.SessionRetrievalService;
import com.whatsappbuisness.wsbuissness.CombinePackadge.Common.TransationType;
import com.whatsappbuisness.wsbuissness.CombinePackadge.Common.VoucherType;
import com.whatsappbuisness.wsbuissness.CombinePackadge.CountryWisePrice.*;
import com.whatsappbuisness.wsbuissness.CombinePackadge.CountryWisePriceDaoList.CountryWisePriceDaoListServiceImpl;
import com.whatsappbuisness.wsbuissness.CombinePackadge.CountryWisePriceDaoList.CountryWisePriceListDao;
import com.whatsappbuisness.wsbuissness.CombinePackadge.CreditService.CreditService;
import com.whatsappbuisness.wsbuissness.CombinePackadge.CreditService.NoCreditFound;
import com.whatsappbuisness.wsbuissness.CombinePackadge.CreditService.ResponseServiceDao;
import com.whatsappbuisness.wsbuissness.CombinePackadge.DriveService.FileUploadDao;
import com.whatsappbuisness.wsbuissness.CombinePackadge.DriveService.FileUploadResponseDao;
import com.whatsappbuisness.wsbuissness.CombinePackadge.DriveService.FileUploadService;
import com.whatsappbuisness.wsbuissness.CombinePackadge.KarixWaOfficial.KarixDao.KarixIncomingWebhookDao;
import com.whatsappbuisness.wsbuissness.CombinePackadge.MessageReportDao.MessageReportService;
import com.whatsappbuisness.wsbuissness.CombinePackadge.Messages.*;
import com.whatsappbuisness.wsbuissness.CombinePackadge.Messages.AudioMessage.AudioMessageDao;
import com.whatsappbuisness.wsbuissness.CombinePackadge.Messages.Crud.MessageRepo;
import com.whatsappbuisness.wsbuissness.CombinePackadge.Messages.DocumentMessage.DocumentMessageDao;
import com.whatsappbuisness.wsbuissness.CombinePackadge.Messages.ImageMessage.ImageMessageDao;
import com.whatsappbuisness.wsbuissness.CombinePackadge.Messages.InteractiveMessageDao.InteractiveMessageDao;
import com.whatsappbuisness.wsbuissness.CombinePackadge.Messages.Location.LocationMessageDao;
import com.whatsappbuisness.wsbuissness.CombinePackadge.Messages.TextMessage.TextMessageDao;
import com.whatsappbuisness.wsbuissness.CombinePackadge.Messages.VideoMessage.VideoMessageDao;
import com.whatsappbuisness.wsbuissness.CombinePackadge.RabbitMqQue.MongoQue.Conversation.ConversationDao;
import com.whatsappbuisness.wsbuissness.CombinePackadge.RabbitMqQue.MongoQue.MongoDB.MessagePersistService;
import com.whatsappbuisness.wsbuissness.CombinePackadge.RabbitMqQue.MongoQue.MongoDB.MessagePersistServiceImpl;
import com.whatsappbuisness.wsbuissness.CombinePackadge.RabbitMqQue.QueName;
import com.whatsappbuisness.wsbuissness.CombinePackadge.RabbitMqQue.RabbitMqqueService;
import com.whatsappbuisness.wsbuissness.CombinePackadge.RabbitMqQue.Webhooks.Webhook.ContactsDao;
import com.whatsappbuisness.wsbuissness.CombinePackadge.RabbitMqQue.Webhooks.Webhook.DlrUpdatesDao;
import com.whatsappbuisness.wsbuissness.CombinePackadge.RabbitMqQue.Webhooks.Webhook.WebhookDao;
import com.whatsappbuisness.wsbuissness.CombinePackadge.Subscription.SubscriptionDao;
import com.whatsappbuisness.wsbuissness.CombinePackadge.Subscription.SubscriptionService;
import com.whatsappbuisness.wsbuissness.CombinePackadge.Utility.CommonClassReturnWithStatus;
import com.whatsappbuisness.wsbuissness.CombinePackadge.WhatsappCloudTemplateMaster.CloudTemplateMasterDao;
import com.whatsappbuisness.wsbuissness.CombinePackadge.WhatsappCloudTemplateMaster.CloudTemplateMasterService;
import com.whatsappbuisness.wsbuissness.CombinePackadge.WsUserORBuisness.StatusDaoUsrOrBsn;
import com.whatsappbuisness.wsbuissness.CombinePackadge.WsUserORBuisness.UserOrBuisnessIntiatedDao;
import com.whatsappbuisness.wsbuissness.CombinePackadge.WsUserORBuisness.UserOrBuisnessService;
import com.whatsappbuisness.wsbuissness.CombinePackadge.catalog.*;
import com.whatsappbuisness.wsbuissness.CombinePackadge.catalog.catalogIncomming.Order;
import com.whatsappbuisness.wsbuissness.CombinePackadge.catalog.catalogIncomming.ProductItem;
import com.whatsappbuisness.wsbuissness.CombinePackadge.whatsappCloudAPI.CloudAPIDao;
import com.whatsappbuisness.wsbuissness.CombinePackadge.whatsappCloudAPI.CloudAPIWebhook.CloudAPIIncomingDao;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.*;

@RestController
@RequestMapping("/api/webhook")
public class EventsWebhookController {
    private static final Logger logger = LoggerFactory.getLogger(EventsWebhookController.class);
    private static final String baseDocumentName="MessageDocument_";

    @Autowired
    RabbitMqqueService rabbitMqqueService;
    @Autowired
    SubscriptionService subscriptionService;
    @Autowired
    UserOrBuisnessService userOrBuisnessService;
    @Autowired
    CreditService creditService;
    @Autowired
    CountryWisePriceService countryWisePriceService;
    @Autowired
    private MessageRepo messageRepo;
    @Autowired
    SessionRetrievalService sessionRetrievalService;
    @Autowired
    CloudTemplateMasterService cloudTemplateMasterService;
    @Autowired
    MediaService mediaService;

    @Autowired
    CatalogMessageService catalogMessageService;

    @Autowired
    MongoTemplate mongoTemplate;

    @Autowired
    MessagePersistService messagePersistService;

    private final String COLLECTION_NAME = "MessageDocument_";



    @PostMapping("/{accountId}")
    public int event(@RequestBody IncomingWebhookDao incomingWebhookDao, @PathVariable("accountId") long accountId) {
        //logger.info("Incoming Messages for others{}", incomingWebhookDao);
        try {
            if (incomingWebhookDao.getStatuses() != null) {
                processDlr(incomingWebhookDao.getStatuses(), accountId);
            }
            if (incomingWebhookDao.getMessages() != null) {
                processMessage(incomingWebhookDao.getMessages(), accountId, incomingWebhookDao.getContacts());
            }


        } catch (Exception ew) {
            logger.error("Error Occured {}", ew);
            ew.printStackTrace();
        }
        return 200;
    }

    @PostMapping("/cloudapi/{accountId}")
    public String cloudAPIPostEvent(@RequestBody( required = false) CloudAPIIncomingDao cloudAPIIncomingDao , @PathVariable("accountId") long accountId, @RequestParam(value = "hub.challenge", defaultValue = "NA") String hub_challenge){
        logger.info("Incoming msg Account ID"+ accountId);
        logger.info("The test Whatsapp Webhook Post value of hub_challenge and cloudAPIIncomingDao is {} and {}"+ hub_challenge, cloudAPIIncomingDao);
        SubscriptionDao subscriptionDao = subscriptionService.getByActive(accountId);
        if(subscriptionDao!=null){
            if(cloudAPIIncomingDao != null && cloudAPIIncomingDao.getObject()!=null && cloudAPIIncomingDao.getObject()!= "null") {
                try {
                    if(!cloudAPIIncomingDao.getEntry().get(0).getChanges().get(0).getField().equalsIgnoreCase("message_template_status_update")) {
                        if (cloudAPIIncomingDao.getEntry().get(0).getChanges().get(0).getValue().getMetadata().getPhone_number_id().equalsIgnoreCase(subscriptionDao.getCloudAPIPhoneNumberID())) {
                            if (cloudAPIIncomingDao.getEntry().get(0).getChanges().get(0).getValue().getStatuses() != null) {
                                DlrUpdatesDao dlrUpdatesDao = processDlrByCloudAPI(cloudAPIIncomingDao, accountId);
                                messagePersistService.webhookCalling(null, dlrUpdatesDao);
//                            webhookCalling(null, dlrUpdatesDao);
                                return hub_challenge;
                            }
                            if (cloudAPIIncomingDao.getEntry().get(0).getChanges().get(0).getValue().getMessages() != null) {
                                MessageDao messageDao = processMessageByCloudAPI(cloudAPIIncomingDao, accountId);
//                            webhookCalling(messageDao, null);
                                return hub_challenge;
                            }
                        }
                    }
                    if(cloudAPIIncomingDao.getEntry().get(0).getChanges().get(0).getField() != null && cloudAPIIncomingDao.getEntry().get(0).getChanges().get(0).getField() != "messages" ){
                        cloudTemplateMasterService.updateCloudTemplateStatus(cloudAPIIncomingDao, accountId);
                    }
                } catch (Exception ew) {
                    logger.error("Error Occured {}", ew);
                    ew.printStackTrace();
                }
            }
        }else{
            logger.error("incoming cloud Subscription not found of account id"+ accountId);
        }
        return hub_challenge;
    }


    @GetMapping("/cloudapi/{accountId}")
    public String cloudAPIGetEvent(@RequestBody( required = false) CloudAPIIncomingDao cloudAPIIncomingDao , @PathVariable("accountId") long accountId, @RequestParam(value = "hub.challenge", defaultValue = "NA") String hub_challenge){
        logger.info("The test Whatsapp Get Webhook value of hub_challenge and cloudAPIIncomingDao is {} and {}"+ hub_challenge, cloudAPIIncomingDao);
        if(cloudAPIIncomingDao != null) {
            try {
                if (cloudAPIIncomingDao.getEntry().get(0).getChanges().get(0).getValue().getStatuses() != null) {
                    DlrUpdatesDao dlrUpdatesDao = processDlrByCloudAPI(cloudAPIIncomingDao, accountId);
                    messagePersistService.webhookCalling(null, dlrUpdatesDao);
//                    webhookCalling(null, dlrUpdatesDao);
                    return hub_challenge;
                }
                if (cloudAPIIncomingDao.getEntry().get(0).getChanges().get(0).getValue().getMessages() != null) {
                    MessageDao messageDao = processMessageByCloudAPI(cloudAPIIncomingDao, accountId);
//                    webhookCalling(messageDao, null);
                    return hub_challenge;
                }
                if(cloudAPIIncomingDao.getEntry().get(0).getChanges().get(0).getField() != null && cloudAPIIncomingDao.getEntry().get(0).getChanges().get(0).getField() != "messages" ){
                    cloudTemplateMasterService.updateCloudTemplateStatus(cloudAPIIncomingDao, accountId);
                }

            } catch (Exception ew) {
                logger.error("Error Occured {}", ew);
                ew.printStackTrace();
            }
        }
        return hub_challenge;
    }
//     void webhookCalling(MessageDao messageDao,DlrUpdatesDao dlrUpdatesDao ) throws Exception{
//        SubscriptionDao subscriptionDao = null;
//        if(messageDao != null){
//            subscriptionDao = subscriptionService.getByActive(messageDao.getAccountId());
//            if(subscriptionDao.getGateway() == Gateway.CLOUDAPI){
//                if(subscriptionDao.isWebhookEnable() == true){
//                    logger.info("When Webhook Enable....");
//                    Query query = new Query(Criteria.where("_id").is(messageDao.getId()));
//                    Update update = new Update();
//                    if(messageDao.getImage() != null){
//                        String shareableURL = mediaService.getMultipartFileByCloudAPI(messageDao.getImage().getId(), subscriptionDao, messageDao.getImage().getMime_type());
//                        messageDao.getImage().setLink(shareableURL);
//                        update.set("image.link", shareableURL);
//                        mongoTemplate.updateFirst(query, update, MessageDao.class, COLLECTION_NAME + messageDao.getAccountId());
//                    }if(messageDao.getDocument() != null){
//                        String shareableURL = mediaService.getMultipartFileByCloudAPI(messageDao.getDocument().getId(), subscriptionDao, messageDao.getDocument().getMime_type());
//                        messageDao.getDocument().setLink(shareableURL);
//                        update.set("document.link", shareableURL);
//                        mongoTemplate.updateFirst(query, update, MessageDao.class, COLLECTION_NAME + messageDao.getAccountId());
//                    }if(messageDao.getAudio() != null){
//                        String shareableURL = mediaService.getMultipartFileByCloudAPI(messageDao.getAudio().getId(), subscriptionDao, messageDao.getAudio().getMime_type());
//                        messageDao.getAudio().setLink(shareableURL);
//                        update.set("audio.link", shareableURL);
//                        mongoTemplate.updateFirst(query, update, MessageDao.class, COLLECTION_NAME + messageDao.getAccountId());
//                    }if(messageDao.getVideo() != null){
//                        String shareableURL = mediaService.getMultipartFileByCloudAPI(messageDao.getVideo().getId(), subscriptionDao, messageDao.getVideo().getMime_type());
//                        messageDao.getVideo().setLink(shareableURL);
//                        update.set("video.link", shareableURL);
//                        mongoTemplate.updateFirst(query, update, MessageDao.class, COLLECTION_NAME + messageDao.getAccountId());
//                    }
//                    WebhookDao  webhookDao = new WebhookDao(messageDao,null, subscriptionDao.getWebhookURL() );
//                    callingExternalUrl(webhookDao, subscriptionDao.getWebhookURL());
//                }if(subscriptionDao.isWebhookEnable() == false && subscriptionDao.isMediaStoretoDriveStatus() == true){
//                    logger.info("When Store Data to DB is Enable....");
//                    Query query = new Query(Criteria.where("_id").is(messageDao.getId()));
//                    Update update = new Update();
//                    if(messageDao.getImage() != null){
//                        String shareableURL = mediaService.getMultipartFileByCloudAPI(messageDao.getImage().getId(), subscriptionDao, messageDao.getImage().getMime_type());
//                        messageDao.getImage().setLink(shareableURL);
//                        update.set("image.link", shareableURL);
//                        mongoTemplate.updateFirst(query, update, MessageDao.class, COLLECTION_NAME + messageDao.getAccountId());
//                    }if(messageDao.getDocument() != null){
//                        String shareableURL = mediaService.getMultipartFileByCloudAPI(messageDao.getDocument().getId(), subscriptionDao, messageDao.getDocument().getMime_type());
//                        messageDao.getDocument().setLink(shareableURL);
//                        update.set("document.link", shareableURL);
//                        mongoTemplate.updateFirst(query, update, MessageDao.class, COLLECTION_NAME + messageDao.getAccountId());
//                    }if(messageDao.getAudio() != null){
//                        String shareableURL = mediaService.getMultipartFileByCloudAPI(messageDao.getAudio().getId(), subscriptionDao, messageDao.getAudio().getMime_type());
//                        messageDao.getAudio().setLink(shareableURL);
//                        update.set("audio.link", shareableURL);
//                        mongoTemplate.updateFirst(query, update, MessageDao.class, COLLECTION_NAME + messageDao.getAccountId());
//                    }if(messageDao.getVideo() != null){
//                        String shareableURL = mediaService.getMultipartFileByCloudAPI(messageDao.getVideo().getId(), subscriptionDao, messageDao.getVideo().getMime_type());
//                        messageDao.getVideo().setLink(shareableURL);
//                        update.set("video.link", shareableURL);
//                        mongoTemplate.updateFirst(query, update, MessageDao.class, COLLECTION_NAME + messageDao.getAccountId());
//                    }
//                }else{
//                    logger.info("The Webhook is not Configured and also msg not store in DB");
//                }
//            }if(subscriptionDao.getGateway() == Gateway.AMEYO){
//
//                if(subscriptionDao.isWebhookEnable() == true){
//                    Query query = new Query(Criteria.where("_id").is(messageDao.getId()));
//                    Update update = new Update();
//                    if(messageDao.getImage() != null){
//                        String shareableURL = mediaService.getMultipartFileByAmeyoServer(messageDao.getImage().getId(), subscriptionDao, messageDao.getImage().getMime_type());
//                        messageDao.getImage().setLink(shareableURL);
//                        update.set("image.link", shareableURL);
//                        mongoTemplate.updateFirst(query, update, MessageDao.class, COLLECTION_NAME + messageDao.getAccountId());
//                    }if(messageDao.getDocument() != null){
//                        String shareableURL = mediaService.getMultipartFileByAmeyoServer(messageDao.getDocument().getId(), subscriptionDao, messageDao.getDocument().getMime_type());
//                        messageDao.getDocument().setLink(shareableURL);
//                        update.set("document.link", shareableURL);
//                        mongoTemplate.updateFirst(query, update, MessageDao.class, COLLECTION_NAME + messageDao.getAccountId());
//                    }if(messageDao.getAudio() != null){
//                        String shareableURL = mediaService.getMultipartFileByAmeyoServer(messageDao.getAudio().getId(), subscriptionDao, messageDao.getAudio().getMime_type());
//                        messageDao.getAudio().setLink(shareableURL);
//                        update.set("audio.link", shareableURL);
//                        mongoTemplate.updateFirst(query, update, MessageDao.class, COLLECTION_NAME + messageDao.getAccountId());
//                    }if(messageDao.getVideo() != null){
//                        String shareableURL = mediaService.getMultipartFileByAmeyoServer(messageDao.getVideo().getId(), subscriptionDao, messageDao.getVideo().getMime_type());
//                        messageDao.getVideo().setLink(shareableURL);
//                        update.set("video.link", shareableURL);
//                        mongoTemplate.updateFirst(query, update, MessageDao.class, COLLECTION_NAME + messageDao.getAccountId());
//                    }
//                    WebhookDao  webhookDao = new WebhookDao(messageDao,null, subscriptionDao.getWebhookURL() );
//                    callingExternalUrl(webhookDao, subscriptionDao.getWebhookURL());
//                }if(subscriptionDao.isWebhookEnable() == false && subscriptionDao.isMediaStoretoDriveStatus() == true){
//                    Query query = new Query(Criteria.where("_id").is(messageDao.getId()));
//                    Update update = new Update();
//                    if(messageDao.getImage() != null){
//                        String shareableURL = mediaService.getMultipartFileByAmeyoServer(messageDao.getImage().getId(), subscriptionDao, messageDao.getImage().getMime_type());
//                        messageDao.getImage().setLink(shareableURL);
//                        update.set("image.link", shareableURL);
//                        mongoTemplate.updateFirst(query, update, MessageDao.class, COLLECTION_NAME + messageDao.getAccountId());
//                    }if(messageDao.getDocument() != null){
//                        String shareableURL = mediaService.getMultipartFileByAmeyoServer(messageDao.getDocument().getId(), subscriptionDao, messageDao.getDocument().getMime_type());
//                        messageDao.getDocument().setLink(shareableURL);
//                        update.set("document.link", shareableURL);
//                        mongoTemplate.updateFirst(query, update, MessageDao.class, COLLECTION_NAME + messageDao.getAccountId());
//                    }if(messageDao.getAudio() != null){
//                        String shareableURL = mediaService.getMultipartFileByAmeyoServer(messageDao.getAudio().getId(), subscriptionDao, messageDao.getAudio().getMime_type());
//                        messageDao.getAudio().setLink(shareableURL);
//                        update.set("audio.link", shareableURL);
//                        mongoTemplate.updateFirst(query, update, MessageDao.class, COLLECTION_NAME + messageDao.getAccountId());
//                    }if(messageDao.getVideo() != null){
//                        String shareableURL = mediaService.getMultipartFileByAmeyoServer(messageDao.getVideo().getId(), subscriptionDao, messageDao.getVideo().getMime_type());
//                        messageDao.getVideo().setLink(shareableURL);
//                        update.set("video.link", shareableURL);
//                        mongoTemplate.updateFirst(query, update, MessageDao.class, COLLECTION_NAME + messageDao.getAccountId());
//                    }
//                }else{
//                    logger.info("The Webhook is not Configured and also msg not store in DB");
//                }
//            }
//            if(subscriptionDao.getGateway() == Gateway.KARIX){
//                if(subscriptionDao.isWebhookEnable() == true){
//                    Query query = new Query(Criteria.where("_id").is(messageDao.getId()));
//                    Update update = new Update();
//                    if(messageDao.getImage() != null){
//                        String shareableURL = mediaService.getMultipartFileByKarixServer(messageDao.getImage().getId(), subscriptionDao, messageDao.getImage().getMime_type());
//                        messageDao.getImage().setLink(shareableURL);
//                        update.set("image.link", shareableURL);
//                        mongoTemplate.updateFirst(query, update, MessageDao.class, COLLECTION_NAME + messageDao.getAccountId());
//                    }if(messageDao.getDocument() != null){
//                        String shareableURL = mediaService.getMultipartFileByKarixServer(messageDao.getDocument().getId(), subscriptionDao, messageDao.getDocument().getMime_type());
//                        messageDao.getDocument().setLink(shareableURL);
//                        update.set("document.link", shareableURL);
//                        mongoTemplate.updateFirst(query, update, MessageDao.class, COLLECTION_NAME + messageDao.getAccountId());
//                    }if(messageDao.getAudio() != null){
//                        String shareableURL = mediaService.getMultipartFileByKarixServer(messageDao.getAudio().getId(), subscriptionDao, messageDao.getAudio().getMime_type());
//                        messageDao.getAudio().setLink(shareableURL);
//                        update.set("audio.link", shareableURL);
//                        mongoTemplate.updateFirst(query, update, MessageDao.class, COLLECTION_NAME + messageDao.getAccountId());
//                    }if(messageDao.getVideo() != null){
//                        String shareableURL = mediaService.getMultipartFileByKarixServer(messageDao.getVideo().getId(), subscriptionDao, messageDao.getVideo().getMime_type());
//                        messageDao.getVideo().setLink(shareableURL);
//                        update.set("video.link", shareableURL);
//                        mongoTemplate.updateFirst(query, update, MessageDao.class, COLLECTION_NAME + messageDao.getAccountId());
//                    }
//                    WebhookDao  webhookDao = new WebhookDao(messageDao,null, subscriptionDao.getWebhookURL() );
//                    callingExternalUrl(webhookDao, subscriptionDao.getWebhookURL());
//                } if(subscriptionDao.isWebhookEnable() == false && subscriptionDao.isMediaStoretoDriveStatus() == true){
//                    Query query = new Query(Criteria.where("_id").is(messageDao.getId()));
//                    Update update = new Update();
//                    if(messageDao.getImage() != null){
//                        String shareableURL = mediaService.getMultipartFileByKarixServer(messageDao.getImage().getId(), subscriptionDao, messageDao.getImage().getMime_type());
//                        messageDao.getImage().setLink(shareableURL);
//                        update.set("image.link", shareableURL);
//                        mongoTemplate.updateFirst(query, update, MessageDao.class, COLLECTION_NAME + messageDao.getAccountId());
//                    }if(messageDao.getDocument() != null){
//                        String shareableURL = mediaService.getMultipartFileByKarixServer(messageDao.getDocument().getId(), subscriptionDao, messageDao.getDocument().getMime_type());
//                        messageDao.getDocument().setLink(shareableURL);
//                        update.set("document.link", shareableURL);
//                        mongoTemplate.updateFirst(query, update, MessageDao.class, COLLECTION_NAME + messageDao.getAccountId());
//                    }if(messageDao.getAudio() != null){
//                        String shareableURL = mediaService.getMultipartFileByKarixServer(messageDao.getAudio().getId(), subscriptionDao, messageDao.getAudio().getMime_type());
//                        messageDao.getAudio().setLink(shareableURL);
//                        update.set("audio.link", shareableURL);
//                        mongoTemplate.updateFirst(query, update, MessageDao.class, COLLECTION_NAME + messageDao.getAccountId());
//                    }if(messageDao.getVideo() != null){
//                        String shareableURL = mediaService.getMultipartFileByKarixServer(messageDao.getVideo().getId(), subscriptionDao, messageDao.getVideo().getMime_type());
//                        messageDao.getVideo().setLink(shareableURL);
//                        update.set("video.link", shareableURL);
//                        mongoTemplate.updateFirst(query, update, MessageDao.class, COLLECTION_NAME + messageDao.getAccountId());
//                    }
//                }else{
//                    logger.info("The Webhook is not Configured and also msg not store in DB");
//                }
//            }
//
//        }else{
//            subscriptionDao = subscriptionService.getByActive(dlrUpdatesDao.getAccountId());
//            if(subscriptionDao.isWebhookEnable() == true){
//                WebhookDao webhookDao = new WebhookDao(null,dlrUpdatesDao, subscriptionDao.getWebhookURL() );
//                callingExternalUrl(webhookDao, subscriptionDao.getWebhookURL());
//            }else{
//                logger.info("The Webhook is not Configured");
//            }
//        }
//    }

//    private void callingExternalUrl(WebhookDao webhookDao, String webhookUrl) {
//        String json = new Gson().toJson(webhookDao);
//        MediaType mediaType = MediaType.parse("application/json");
//        okhttp3.RequestBody body = okhttp3.RequestBody.create(mediaType, json);
//        Request request = new Request.Builder()
//                .url(webhookUrl)
//                .method("POST", body)
//                .addHeader("Content-Type", "application/json")
//                .build();
//        Response response = null;
//        try {
//            OkHttpClient httpClient = new OkHttpClient().newBuilder().build();
//            response = httpClient.newCall(request).execute();
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//        response.close();
//    }


    private MessageDao processMessageByCloudAPI(CloudAPIIncomingDao cloudAPIIncomingDao, long accountId) {
        MessageDao messageDao = new MessageDao();
        ContactsDao contactsDao = new ContactsDao();
        contactsDao.setWa_id(cloudAPIIncomingDao.getEntry().get(0).getChanges().get(0).getValue().getContacts().get(0).getWa_id());
        messageDao.setMessageId(cloudAPIIncomingDao.getEntry().get(0).getChanges().get(0).getValue().getMessages().get(0).getId());
        messageDao = checkIncomingNumberCountryCode(messageDao,cloudAPIIncomingDao );
        messageDao.setProfileName(cloudAPIIncomingDao.getEntry().get(0).getChanges().get(0).getValue().getContacts().get(0).getProfile().getName());
        String messageType = cloudAPIIncomingDao.getEntry().get(0).getChanges().get(0).getValue().getMessages().get(0).getType();
        if(messageType.equalsIgnoreCase("text")){
            messageDao.setType(MessageType.text);
            TextMessageDao textMessageDao = new TextMessageDao();
            textMessageDao.setBody(cloudAPIIncomingDao.getEntry().get(0).getChanges().get(0).getValue().getMessages().get(0).getText().getBody());
            messageDao.setText(textMessageDao);
        }
        else if(messageType.equalsIgnoreCase("image")){
            messageDao.setType(MessageType.image);
            ImageMessageDao imageMessageDao = new ImageMessageDao();
            imageMessageDao.setSha256(cloudAPIIncomingDao.getEntry().get(0).getChanges().get(0).getValue().getMessages().get(0).getImage().getSha256());
            imageMessageDao.setId(cloudAPIIncomingDao.getEntry().get(0).getChanges().get(0).getValue().getMessages().get(0).getImage().getId());
            imageMessageDao.setMime_type(cloudAPIIncomingDao.getEntry().get(0).getChanges().get(0).getValue().getMessages().get(0).getImage().getMime_type());
            try{
                imageMessageDao.setCaption(cloudAPIIncomingDao.getEntry().get(0).getChanges().get(0).getValue().getMessages().get(0).getImage().getCaption());
            }catch (Exception e){
                logger.info("The Caption is not present");
            }
            messageDao.setImage(imageMessageDao);
        }
        else if(messageType.equalsIgnoreCase("document")){
            messageDao.setType(MessageType.document);
            DocumentMessageDao documentMessageDao = new DocumentMessageDao();
            documentMessageDao.setSha256(cloudAPIIncomingDao.getEntry().get(0).getChanges().get(0).getValue().getMessages().get(0).getDocument().getSha256());
            documentMessageDao.setId(cloudAPIIncomingDao.getEntry().get(0).getChanges().get(0).getValue().getMessages().get(0).getDocument().getId());
            documentMessageDao.setMime_type(cloudAPIIncomingDao.getEntry().get(0).getChanges().get(0).getValue().getMessages().get(0).getDocument().getMime_type());
            try{
                documentMessageDao.setCaption(cloudAPIIncomingDao.getEntry().get(0).getChanges().get(0).getValue().getMessages().get(0).getDocument().getCaption());
            }catch (Exception e){
                logger.info("The Caption is not present");
            }
            messageDao.setDocument(documentMessageDao);
        }
        else if(messageType.equalsIgnoreCase("audio")){
            messageDao.setType(MessageType.audio);
            AudioMessageDao audioMessageDao = new AudioMessageDao();
            audioMessageDao.setSha256(cloudAPIIncomingDao.getEntry().get(0).getChanges().get(0).getValue().getMessages().get(0).getAudio().getSha256());
            audioMessageDao.setId(cloudAPIIncomingDao.getEntry().get(0).getChanges().get(0).getValue().getMessages().get(0).getAudio().getId());
            audioMessageDao.setMime_type(cloudAPIIncomingDao.getEntry().get(0).getChanges().get(0).getValue().getMessages().get(0).getAudio().getMime_type());
            messageDao.setAudio(audioMessageDao);
        }
        else if(messageType.equalsIgnoreCase("video")){
            messageDao.setType(MessageType.video);
            VideoMessageDao videoMessageDao = new VideoMessageDao();
            videoMessageDao.setSha256(cloudAPIIncomingDao.getEntry().get(0).getChanges().get(0).getValue().getMessages().get(0).getVideo().getSha256());
            videoMessageDao.setId(cloudAPIIncomingDao.getEntry().get(0).getChanges().get(0).getValue().getMessages().get(0).getVideo().getId());
            videoMessageDao.setMime_type(cloudAPIIncomingDao.getEntry().get(0).getChanges().get(0).getValue().getMessages().get(0).getVideo().getMime_type());
                try{
                    videoMessageDao.setCaption(cloudAPIIncomingDao.getEntry().get(0).getChanges().get(0).getValue().getMessages().get(0).getVideo().getCaption());
                }catch (Exception e){
                    logger.info("The Caption is not present");
                }
            messageDao.setVideo(videoMessageDao);
        }
        else if(messageType.equalsIgnoreCase("location")){
            messageDao.setType(MessageType.location);
            messageDao.setLocation(cloudAPIIncomingDao.getEntry().get(0).getChanges().get(0).getValue().getMessages().get(0).getLocation());
        }else if(messageType.equalsIgnoreCase("interactive")){
            messageDao.setType(MessageType.interactive);
            messageDao.setInteractive(cloudAPIIncomingDao.getEntry().get(0).getChanges().get(0).getValue().getMessages().get(0).getInteractive());
        }
        else if(messageType.equalsIgnoreCase("button")){
            messageDao.setType(MessageType.button);
            messageDao.setButton(cloudAPIIncomingDao.getEntry().get(0).getChanges().get(0).getValue().getMessages().get(0).getButton());
        }
        else if (messageType.equalsIgnoreCase("order")) {
            Order newOrder = cloudAPIIncomingDao.getEntry().get(0).getChanges().get(0).getValue().getMessages().get(0).getOrder();
            logger.info("Getting OrderDao Object >>>> {}", newOrder);
            messageDao.setType(MessageType.ORDER);
            messageDao.setCatalogMessageDao(setCatalogeMessage(newOrder));
        }
        if(cloudAPIIncomingDao.getEntry().get(0).getChanges().get(0).getValue().getMessages().get(0).getContext() != null) {
            Query query = new Query();
            query.addCriteria(Criteria.where("_id").is(cloudAPIIncomingDao.getEntry().get(0).getChanges().get(0).getValue().getMessages().get(0).getContext().getId()));
            List<MessageDao> messageDaos = mongoTemplate.find(query, MessageDao.class, baseDocumentName + accountId);

            if (messageDaos.size() > 0) {
                logger.info(" MessagDaos: {}", messageDaos.get(0));

                if (messageDaos.get(0).getType() == MessageType.text) {
                    messageDao.setContext(cloudAPIIncomingDao.getEntry().get(0).getChanges().get(0).getValue().getMessages().get(0).getContext());
                    messageDao.getContext().setText(messageDaos.get(0).getText().getBody());
                    logger.info(" Message Type : {} ", messageDaos.get(0).getType());

                }
                else if (messageDaos.get(0).getType() == MessageType.image && messageDaos.get(0).getImage().getCaption() != null) {
                    logger.info(" Message Type : {} and Caption: {}", messageDaos.get(0).getType(), messageDaos.get(0).getImage().getCaption());
                    messageDao.setContext(cloudAPIIncomingDao.getEntry().get(0).getChanges().get(0).getValue().getMessages().get(0).getContext());

                    messageDao.getContext().setText(messageDaos.get(0).getImage().getCaption());
                } else if (messageDaos.get(0).getType() == MessageType.video && messageDaos.get(0).getVideo().getCaption() != null) {
                    logger.info(" Message Type : {} and Caption: {}", messageDaos.get(0).getType(), messageDaos.get(0).getVideo().getCaption());
                    messageDao.setContext(cloudAPIIncomingDao.getEntry().get(0).getChanges().get(0).getValue().getMessages().get(0).getContext());

                    messageDao.getContext().setText(messageDaos.get(0).getVideo().getCaption());
                } else if (messageDaos.get(0).getType() == MessageType.document && messageDaos.get(0).getDocument().getCaption() != null) {
                    logger.info(" Message Type : {} and Caption: {}", messageDaos.get(0).getType(), messageDaos.get(0).getDocument().getCaption());
                    messageDao.setContext(cloudAPIIncomingDao.getEntry().get(0).getChanges().get(0).getValue().getMessages().get(0).getContext());

                    messageDao.getContext().setText(messageDaos.get(0).getDocument().getCaption());
                }
                else {
                    logger.info("The Reply Message Type : {}", messageDaos.get(0).getType());
                }

            }


        }
        List<MessageDao> messageDaoList = new ArrayList<>();
        messageDaoList.add(messageDao);
        List<ContactsDao> contactsDaoList = new ArrayList<>();
        contactsDaoList.add(contactsDao);
        List<MessageDao> messageDaos = processMessage(messageDaoList, accountId, contactsDaoList);

        return messageDaos.get(0);
    }



    private CatalogMessageDao setCatalogeMessage(Order order) {
        // logger.info("setCatalogeMessage  catalogId>>>>{} retailer_id >>>>> {}  title >>> {}  currency >>>>> {} quantity >>>> {} price >>>> {}",catalogId, retailer_id, title, currency, quantity, price);
        logger.info("OrderDao >>>>> {}", order);
        CatalogMessageDao catalogMessageDao = new CatalogMessageDao();
        List<ProductItems> productItemsList = new ArrayList<>();

        Action action = new Action();
        action.setCatalog_id(order.getCatalog_id());


        for (ProductItem items : order.getProduct_items()) {
            ProductItems productItems = new ProductItems();
            productItems.setProduct_retailer_id(items.getProduct_retailer_id());
            productItems.setVariantsTitle(findVariantsTitleByCatalogeIdAndItemsId(order.getCatalog_id(), items.getProduct_retailer_id()));
            productItems.setINR(items.getCurrency());
            productItems.setQuantity(Integer.parseInt(items.getQuantity()));
            productItems.setItem_price(Double.parseDouble(items.getItem_price()));
            productItemsList.add(productItems);
        }

        Sections sections = new Sections();
        sections.setProduct_items(productItemsList);

        action.setSections(Collections.singletonList(sections));

        Interactive interactive = new Interactive();
        interactive.setAction(action);
        catalogMessageDao.setInteractive(interactive);
        logger.info("Getting CatalogMessageDao >>>>> {} ", catalogMessageDao);
        return catalogMessageDao;
    }


    private String findVariantsTitleByCatalogeIdAndItemsId(String catalogId, String productRetailerId) {
        return catalogMessageService.getVariantsTitleByCatalogIdAndRetailerProductId(catalogId, productRetailerId);
    }
    private MessageDao checkIncomingNumberCountryCode(MessageDao messageDao, CloudAPIIncomingDao cloudAPIIncomingDao){
        String incomingNumber = cloudAPIIncomingDao.getEntry().get(0).getChanges().get(0).getValue().getContacts().get(0).getWa_id();
        for(Map.Entry<String, CountryWisePriceListDao> hashmap : CountryWisePriceDaoListServiceImpl.countryHashmapByCountryCode.entrySet()){
                if(incomingNumber.startsWith(hashmap.getValue().getNumberCode())){
                    messageDao.setCountryCode(hashmap.getValue().getCountryCode());
                    break;
                }
        }

//        if(length == 12){
//            if (incomingNumber.startsWith("91")) {
//                messageDao.setCountryCode("IND");
//            }
//            else if(incomingNumber.startsWith("92")){
//                messageDao.setCountryCode("PAK");
//            }
//        }else{
//            if (incomingNumber.startsWith("44")) {
//                messageDao.setCountryCode("UK");
//            } else if (incomingNumber.startsWith("1")) {
//                messageDao.setCountryCode("USA");
//            } else if (incomingNumber.startsWith("55")) {
//                messageDao.setCountryCode("Brazil");
//            } else if (incomingNumber.startsWith("971")) {
//                messageDao.setCountryCode("UAE");
//            } else {
//                logger.info("Unknown country code");
//            }
//        }
        return messageDao;
    }

    private DlrUpdatesDao processDlrByCloudAPI(CloudAPIIncomingDao cloudAPIIncomingDao, long accountId) {
        DlrStatusDao dlrStatusDao = new DlrStatusDao();
        dlrStatusDao.setId(cloudAPIIncomingDao.getEntry().get(0).getChanges().get(0).getValue().getStatuses().get(0).getId());
        dlrStatusDao.setStatus(cloudAPIIncomingDao.getEntry().get(0).getChanges().get(0).getValue().getStatuses().get(0).getStatus());
        dlrStatusDao.setTimestamp(cloudAPIIncomingDao.getEntry().get(0).getChanges().get(0).getValue().getStatuses().get(0).getTimestamp());
        dlrStatusDao.setRecipient_id(cloudAPIIncomingDao.getEntry().get(0).getChanges().get(0).getValue().getStatuses().get(0).getRecipient_id());
        List<DlrStatusDao> list = new ArrayList<>();
        list.add(dlrStatusDao);
        DlrUpdatesDao dlrUpdatesDao = processDlr(list, accountId);
        return dlrUpdatesDao;
    }

    // Author=Ritu Redhu ---->

    @PostMapping("/karix/{accountId}")
    public int karixEvent(@RequestBody KarixIncomingWebhookDao karixIncomingWebhookDao, @PathVariable("accountId") long accountId) {
        logger.info("Incoming Messages  {}", karixIncomingWebhookDao);
        try {
            if (karixIncomingWebhookDao.getNotificationAttributes()!= null) {
                DlrUpdatesDao dlrUpdatesDao = karixDlrUpdate(karixIncomingWebhookDao, accountId);
                messagePersistService.webhookCalling(null, dlrUpdatesDao);
//                webhookCalling(null, dlrUpdatesDao);
            }
            if (karixIncomingWebhookDao.getEventContent() != null) {
                List<MessageDao> messageDaos = karixProcessMessage(karixIncomingWebhookDao, accountId);
//                webhookCalling(messageDaos.get(0), null);
            }
        } catch (Exception ew) {
            logger.error("Error Occured {}", ew);
            ew.printStackTrace();
        }
        return 200;
    }

    private List<MessageDao> karixProcessMessage(KarixIncomingWebhookDao karixIncomingWebhookDao, long accountId) throws UnsupportedEncodingException {
        MessageDao messageDao = new MessageDao();
        ContactsDao contactsDao = new ContactsDao();
        contactsDao.setWa_id(karixIncomingWebhookDao.getEventContent().getMessage().getFrom());
        messageDao.setMessageId(karixIncomingWebhookDao.getEventContent().getMessage().getId());
//        messageDao.setCountryCode("IND");
        for (Map.Entry<String, CountryWisePriceListDao> hashmap : CountryWisePriceDaoListServiceImpl.countryHashmapByCountryCode.entrySet()) {
            if (contactsDao.getWa_id().startsWith(hashmap.getValue().getNumberCode())) {
                messageDao.setCountryCode(hashmap.getValue().getCountryCode());
                break;
            }
        }
        if (karixIncomingWebhookDao.getEventContent().getMessage().getText() != null) {
            messageDao.setType(MessageType.text);
            TextMessageDao textMessageDao = new TextMessageDao();
            textMessageDao.setBody(karixIncomingWebhookDao.getEventContent().getMessage().getText().getBody());
            messageDao.setText(textMessageDao);
        }

        if (karixIncomingWebhookDao.getEventContent().getMessage().getLocation() != null) {
            messageDao.setType(MessageType.location);
            messageDao.setLocation(karixIncomingWebhookDao.getEventContent().getMessage().getLocation());
        }

            if (karixIncomingWebhookDao.getEventContent().getMessage().getVideo() != null) {
                messageDao.setType(MessageType.video);
                VideoMessageDao videoMessageDao = new VideoMessageDao();
                videoMessageDao.setSha256(karixIncomingWebhookDao.getEventContent().getMessage().getVideo().getSha256());
                String[] splitfilelink = karixIncomingWebhookDao.getEventContent().getMessage().getVideo().getFileLink().split("=");
                String splitfilelinkString = splitfilelink[1];
                videoMessageDao.setId(splitfilelinkString);
                videoMessageDao.setMime_type(karixIncomingWebhookDao.getEventContent().getMessage().getVideo().getMime_type());
                messageDao.setVideo(videoMessageDao);
            }
            if (karixIncomingWebhookDao.getEventContent().getMessage().getImage() != null) {
                messageDao.setType(MessageType.image);
                ImageMessageDao imageMessageDao = new ImageMessageDao();
                imageMessageDao.setSha256(karixIncomingWebhookDao.getEventContent().getMessage().getImage().getSha256());
                String[] splitfilelink = karixIncomingWebhookDao.getEventContent().getMessage().getImage().getFileLink().split("=");
                String splitfilelinkString = splitfilelink[1];
                imageMessageDao.setId(splitfilelinkString);
                logger.info("value of id before encode {}", splitfilelinkString);
                imageMessageDao.setMime_type(karixIncomingWebhookDao.getEventContent().getMessage().getImage().getMime_type());
                try {
                    imageMessageDao.setCaption(karixIncomingWebhookDao.getEventContent().getMessage().getImage().getCaption());
                } catch (Exception e) {
                    logger.info("No Caption Received");
                }
                messageDao.setImage(imageMessageDao);
            }
            if (karixIncomingWebhookDao.getEventContent().getMessage().getDocument() != null) {
                logger.info("Incoming karixIncomingWebhookDao.getEventContent().get getDocument !=null response  {}", karixIncomingWebhookDao.getEventContent().getMessage().getDocument());
                messageDao.setType(MessageType.document);
                DocumentMessageDao documentMessageDao = new DocumentMessageDao();
                documentMessageDao.setSha256(karixIncomingWebhookDao.getEventContent().getMessage().getDocument().getSha256());
                String[] splitfilelink = karixIncomingWebhookDao.getEventContent().getMessage().getDocument().getFileLink().split("=");
                String splitfilelinkString = splitfilelink[1];
                documentMessageDao.setId(splitfilelinkString);
                logger.info("value of id before encode {}", splitfilelinkString);
                documentMessageDao.setMime_type(karixIncomingWebhookDao.getEventContent().getMessage().getDocument().getMime_type());
                messageDao.setDocument(documentMessageDao);
            }
            if (karixIncomingWebhookDao.getEventContent().getMessage().getButton() != null) {
                logger.info("Incoming karixIncomingWebhookDao.getEventContent().getMessage().getButton() !=null response  {}", karixIncomingWebhookDao.getEventContent().getMessage().getButton());
                messageDao.setType(MessageType.button);
                messageDao.setButton(karixIncomingWebhookDao.getEventContent().getMessage().getButton());
            }
            if (karixIncomingWebhookDao.getEventContent().getMessage().getInteractive() != null) {
                logger.info("Incoming karixIncomingWebhookDao.getEventContent().getMessage().getInteractive() !=null response  {}", karixIncomingWebhookDao.getEventContent().getMessage().getInteractive());
                messageDao.setType(MessageType.interactive);
                messageDao.setInteractive(karixIncomingWebhookDao.getEventContent().getMessage().getInteractive());
            }
            if(karixIncomingWebhookDao.getEventContent().getMessage().getProfileName() !=null) {
            	logger.info("Incoming karixIncomingWebhookDao.getEventContent().getMessage().getInteractive() !=null response  {}", karixIncomingWebhookDao.getEventContent().getMessage().getProfileName());
            	messageDao.setProfileName(karixIncomingWebhookDao.getEventContent().getMessage().getProfileName());
            	
            }
            List<MessageDao> listMessageDao = new ArrayList<>();
            listMessageDao.add(messageDao);
            List<ContactsDao> listCotactDao = new ArrayList<>();
            listCotactDao.add(contactsDao);
            return processMessage(listMessageDao, accountId, listCotactDao);
        }

        public DlrUpdatesDao karixDlrUpdate (KarixIncomingWebhookDao karixIncomingWebhookDao,long accountId){
            DlrStatusDao dlrStatusDao = new DlrStatusDao();
            dlrStatusDao.setId(karixIncomingWebhookDao.getEvents().getMid());
            dlrStatusDao.setStatus(karixIncomingWebhookDao.getNotificationAttributes().getStatus());
            dlrStatusDao.setTimestamp(karixIncomingWebhookDao.getEvents().getTimestamp());
//        dlrStatusDao.setRecipient_id(karixIncomingWebhookDao.getConvDetails().getWaConvId());
            List<DlrStatusDao> list = new ArrayList<>();
            list.add(dlrStatusDao);
            return processDlr(list, accountId);
        }
        // <-----  Author=Ritu Redhu

        private void checkUsrOrBsns (MessageDao messageDao) throws NoCreditFound {
            CommonClassReturnWithStatus<UserOrBuisnessIntiatedDao, StatusDaoUsrOrBsn> commonClassReturnWithStatus = null;
            if (messageDao.getChatSide() == ChatSide.Client) {
                commonClassReturnWithStatus = userOrBuisnessService.checkSessionWithCatagoryRedis(messageDao.getTo(), messageDao.getCountryCode(), messageDao.getAccountId(), messageDao.getChatSide(), messageDao.getCountryWiseRateRetDao().getCountryWisePriceDao().getUserInitiatedRates(), "USER");
            } else {
//            commonClassReturnWithStatus = userOrBuisnessService.checkSessionWithCatagoryRedis(messageDao.getTo(), messageDao.getCountryCode(), messageDao.getAccountId(), messageDao.getChatSide(), messageDao.getCountryWiseRateRetDao().getCountryWisePriceDao().getBsnsInitiatedRates(), messageDao.getTemplate().getCategory());
                if (messageDao.getTemplate().getCategory().equalsIgnoreCase("MARKETING")) {
                    commonClassReturnWithStatus = userOrBuisnessService.checkSessionWithCatagoryRedis(messageDao.getTo(), messageDao.getCountryCode(), messageDao.getAccountId(), messageDao.getChatSide(), messageDao.getCountryWiseRateRetDao().getCountryWisePriceDao().getMarketingRates(), messageDao.getTemplate().getCategory());
                } else if (messageDao.getTemplate().getCategory().equalsIgnoreCase("UTILITY")) {
                    commonClassReturnWithStatus = userOrBuisnessService.checkSessionWithCatagoryRedis(messageDao.getTo(), messageDao.getCountryCode(), messageDao.getAccountId(), messageDao.getChatSide(), messageDao.getCountryWiseRateRetDao().getCountryWisePriceDao().getUtilityRates(), messageDao.getTemplate().getCategory());
                } else if (messageDao.getTemplate().getCategory().equalsIgnoreCase("AUTHENTICATION")) {
                    commonClassReturnWithStatus = userOrBuisnessService.checkSessionWithCatagoryRedis(messageDao.getTo(), messageDao.getCountryCode(), messageDao.getAccountId(), messageDao.getChatSide(), messageDao.getCountryWiseRateRetDao().getCountryWisePriceDao().getAuthenicationRates(), messageDao.getTemplate().getCategory());
                }
            }
            switch (commonClassReturnWithStatus.getStatus()) {
                case NEW:
                    ResponseServiceDao responseServiceDao = null;
                    if (messageDao.getType() != MessageType.template) {
                        responseServiceDao = creditService.validateCredit(messageDao.getAccountId(), messageDao.getCountryWiseRateRetDao().getCountryWisePriceDao().getUserInitiatedRates());
                    } else {
                        if (messageDao.getTemplate().getCategory().equalsIgnoreCase("MARKETING")) {
                            responseServiceDao = creditService.validateCredit(messageDao.getAccountId(), messageDao.getCountryWiseRateRetDao().getCountryWisePriceDao().getMarketingRates());
                        } else if (messageDao.getTemplate().getCategory().equalsIgnoreCase("UTILITY")) {
                            responseServiceDao = creditService.validateCredit(messageDao.getAccountId(), messageDao.getCountryWiseRateRetDao().getCountryWisePriceDao().getUtilityRates());
                        } else if (messageDao.getTemplate().getCategory().equalsIgnoreCase("AUTHENTICATION")) {
                            responseServiceDao = creditService.validateCredit(messageDao.getAccountId(), messageDao.getCountryWiseRateRetDao().getCountryWisePriceDao().getAuthenicationRates());
                        }
                    }
//                ResponseServiceDao responseServiceDao = creditService.validateCredit(messageDao.getAccountId(), messageDao.getCountryWiseRateRetDao().getCountryWisePriceDao().getBsnsInitiatedRates());
                    if (responseServiceDao.getStatus() != 200) {

                        UserOrBuisnessIntiatedDao userOrBuisnessIntiatedDao = commonClassReturnWithStatus.getData();
                        userOrBuisnessService.deleteUserOrBuisnessIntiatedDaoById(userOrBuisnessIntiatedDao.getId());
                        logger.info("Session Deleted");
                        throw new NoCreditFound("Credit Cannot be Found");
                    }
                    if (messageDao.getType() != MessageType.template) {
                        creditService.creditServer(TransationType.DR, messageDao.getCountryWiseRateRetDao().getCountryWisePriceDao().getUserInitiatedRates(), commonClassReturnWithStatus.getData().getRefId(), VoucherType.Debit_Usr_Session, messageDao.getAccountId());
                    } else {
                        if (messageDao.getTemplate().getCategory().equalsIgnoreCase("MARKETING")) {
                            creditService.creditServer(TransationType.DR, messageDao.getCountryWiseRateRetDao().getCountryWisePriceDao().getMarketingRates(), commonClassReturnWithStatus.getData().getRefId(), VoucherType.Debit_Bsns_Session, messageDao.getAccountId());
                        } else if (messageDao.getTemplate().getCategory().equalsIgnoreCase("UITILTY")) {
                            creditService.creditServer(TransationType.DR, messageDao.getCountryWiseRateRetDao().getCountryWisePriceDao().getUtilityRates(), commonClassReturnWithStatus.getData().getRefId(), VoucherType.Debit_Bsns_Session, messageDao.getAccountId());
                        } else if (messageDao.getTemplate().getCategory().equalsIgnoreCase("AUTHENTICATION")) {
                            creditService.creditServer(TransationType.DR, messageDao.getCountryWiseRateRetDao().getCountryWisePriceDao().getAuthenicationRates(), commonClassReturnWithStatus.getData().getRefId(), VoucherType.Debit_Bsns_Session, messageDao.getAccountId());
                        }
                    }
//                creditService.creditServer(TransationType.DR, messageDao.getCountryWiseRateRetDao().getCountryWisePriceDao().getBsnsInitiatedRates(), commonClassReturnWithStatus.getData().getRefId(), VoucherType.Debit_Bsns_Session, messageDao.getAccountId());
                    break;
                case EXISTS:
                    break;


            }

        }
        private MessageDao checkCountryPrice (MessageDao messageDao) throws
        CountryNotFoundExeption, CountryDisabledExeption {
            CountryWiseRateRetDao countryWiseRateRetDao = countryWisePriceService.get(new CountryWisePricePk(messageDao.getCountryCode(), messageDao.getAccountId()));
            switch (countryWiseRateRetDao.getStatus()) {
                case NOTFOUND:
                    throw new CountryNotFoundExeption("Requested Country Code Cannot be Found!!");

                case NOTALLOWED:
                    throw new CountryDisabledExeption("Requested Country Code has been disabled!!");

                case OK:
                    messageDao.setCountryWiseRateRetDao(countryWiseRateRetDao);
                    break;

            }
            return messageDao;


        }
        List<MessageDao> processMessage (List < MessageDao > messageDaos,long accountId, List<ContactsDao > contactsDaos)
        {
            SubscriptionDao subscriptionDao = subscriptionService.getByActive(accountId);
            int i = 0;
            for (MessageDao messageDao : messageDaos) {

                logger.info("Will Process Incoming Message {}", messageDao);
                logger.info("Will Process contactsDaos Message {}", contactsDaos);
                logger.info("Will Process accountId  {}", accountId);
                messageDao.setChatSide(ChatSide.Client);
                messageDao.setTo(contactsDaos.get(i).getWa_id());
                messageDao.setAccountId(accountId);
                messageDao.setMessageId(messageDao.getId());
                messageDao.setSubscriptionDao(subscriptionDao);
                i++;

                try {
                    messageDao = checkCountryPrice(messageDao);
                    checkUsrOrBsns(messageDao);
                    rabbitMqqueService.pushToQue(QueName.Mongo_Que.routing, QueName.Mongo_Que.queName, messageDao);

                } catch (Exception e) {
                    logger.error("Failed to insert Event webhook as Exception thrown {}", e);
                }
            }
            return messageDaos;
        }


        DlrUpdatesDao processDlr (List < DlrStatusDao > statuses,long accountId){
            logger.info("The value of status in Status Dao is {}", statuses.get(0));
            DlrUpdatesDao dlrUpdatesDao = null;
            boolean update = true;
            for (DlrStatusDao status : statuses) {


                dlrUpdatesDao = new DlrUpdatesDao();
                dlrUpdatesDao.setId(status.getId());
                dlrUpdatesDao.setRecipient_id(status.getRecipient_id());
                dlrUpdatesDao.setAccountId(accountId);
                switch (status.getStatus()) {
                    case "delivered":
                        dlrUpdatesDao.setStatus(MessageStatus.DLV);
                        break;

                    case "read":
                        dlrUpdatesDao.setStatus(MessageStatus.VIEW);
                        break;
                    case "failed":
                        dlrUpdatesDao.setStatus(MessageStatus.FAILED);
                        break;
                    case "deleted":
                        dlrUpdatesDao.setStatus(MessageStatus.DLT);
                        break;
                    case "sent":
                        dlrUpdatesDao.setStatus(MessageStatus.SENT);
                        update = false;
                        break;
                    case "Queued":
                        dlrUpdatesDao.setStatus(MessageStatus.QUEUED);
                        break;
                    case "Not Sent":
                        dlrUpdatesDao.setStatus(MessageStatus.FAILED);
                        break;
                    default:
                        logger.error("Message Status is Unhandled {}", status.getStatus());
                        dlrUpdatesDao.setStatus(MessageStatus.FAILED);
                        break;

                }
                logger.info("Will Update DLR of msgs {}", statuses);
                if (update) {
                    rabbitMqqueService.pushToQue(QueName.DLR_Que.routing, QueName.DLR_Que.queName, dlrUpdatesDao);
                }

            }
            return dlrUpdatesDao;

        }
    }


