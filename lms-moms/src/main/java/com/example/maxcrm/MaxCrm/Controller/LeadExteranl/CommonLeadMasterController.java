package com.example.maxcrm.MaxCrm.Controller.LeadExteranl;

import com.example.maxcrm.MaxCrm.CombinePackage.MyOperator.RecordingUrlService;
import com.example.maxcrm.MaxCrm.Dao.*;
import com.example.maxcrm.MaxCrm.Exception.ValidationFailException;
import com.example.maxcrm.MaxCrm.OkHttp.OkHttpSingleTon;
import com.example.maxcrm.MaxCrm.Repo.LeadMasterRepo;
import com.example.maxcrm.MaxCrm.Service.*;
import com.example.maxcrm.MaxCrm.Utility.UtilityClass;
import okhttp3.Request;
import okhttp3.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.*;

@RestController
@RequestMapping("/allow/lead")
public class CommonLeadMasterController {

    @Autowired
    LeadMasterRepo leadMasterRepo;
    @Autowired
    LeadSourceService leadSourceService;
    @Autowired
    LeadMasterService leadMasterService;
    @Autowired
    TextMessageService textMessageService;
    @Autowired
    NotificationService notificationService;
    @Autowired
    PropertyService propertyService;
    @Autowired
    LeadTypeService leadTypeService;
    @Autowired
    ProductMasterService productMasterService;
    @Autowired
    FacebookAddService facebookAddService;
    @Autowired
    UserMasterService userMasterService;

    @Autowired
    RecordingUrlService recordingUrlService;

    @Autowired
    LogEventService logEventService;
    @Autowired
    LeadStageService leadStageService;

    @Autowired
    LeadStatusService leadStatusService;
    @Autowired
    TemplateDocumentService templateDocumentService;
    Logger logger = LoggerFactory.getLogger(CommonLeadMasterController.class);
    Map<String, Integer> countryToCodeMap = new HashMap<>();
    @Autowired
    private CampaignTransactionService campaignTransactionService;
    private Map<String, Integer> isoToCodeMap;

    private static String handleException(String message) {

        String retmsg = null;

        if (message.contains("[unqphonenumber]")) {
            retmsg = "Lead Already Exists!!";

        } else {
            retmsg = "Lead Cannot Be Created!!Please Try Again Later";
        }
        return retmsg;
    }

    @PostMapping("/createleadsource")
    public ResponseDao createLeadSource(@RequestParam("securename") String secureName,
                                        @RequestParam("securetoken") String secureToken,
                                        @RequestParam("sourcename") String sourceName,
                                        @RequestParam(name = "create", required = false, defaultValue = "false") boolean create) throws Exception {


        logger.info("CreateLeadSource API :: secureName :: {}", secureName);
        logger.info("CreateLeadSource API :: secureToken :: {}", secureToken);
        logger.info("CreateLeadSource API :: sourcename :: {}", sourceName);
        logger.info("CreateLeadSource API :: create :: {}", create);
        ResponseDao responseDao = new ResponseDao();
        LeadSourceDao secureSourceDao = leadSourceService.findByName(secureName);

        if (secureSourceDao == null || !secureSourceDao.getAccessToken().equals(secureToken)) {
            responseDao.setStatusCode(403);
            responseDao.setId(0);
            responseDao.setMsg("Invalid Token");
            return responseDao;
        }

        LeadSourceDao leadSourceDao = leadSourceService.findByName(sourceName);
        if (leadSourceDao != null) {
            responseDao.setStatusCode(200);
            responseDao.setId(leadSourceDao.getId());
            responseDao.setMsg(leadSourceDao.getAccessToken());
            return responseDao;
        } else if (!create && leadSourceDao == null) {
            responseDao.setStatusCode(404);
            responseDao.setId(0);
            responseDao.setMsg("Lead Source Not Found");
            return responseDao;
        }

        // CREATE NEW LEAD SOURCE AND RETURN TOKEN
        LeadSourceDao newLeadSourceDao = new LeadSourceDao();
        newLeadSourceDao.setName(sourceName);
        newLeadSourceDao.setStatus((byte) 1);
        newLeadSourceDao.setDescription("Lead Source Created With API on " + UtilityClass.getDateRedable());
        newLeadSourceDao.setAssignBy("Type");
        newLeadSourceDao.setInitialStage("Conversation");
        newLeadSourceDao.setInitialStatus("Pending");
        newLeadSourceDao.setInitialType("FreshLeads");
        newLeadSourceDao.setSendnotification((byte) 0);
        newLeadSourceDao.setSendnotificationothers((byte) 0);
        newLeadSourceDao.setAccessToken(UUID.randomUUID().toString());

        newLeadSourceDao = leadSourceService.insert(newLeadSourceDao);
        responseDao.setId(newLeadSourceDao.getId());
        responseDao.setStatusCode(201);
        responseDao.setMsg(newLeadSourceDao.getAccessToken());

        return responseDao;
    }

    @PostMapping("/updatewhastappdlr")
    public int updateWhatsappDlr(@RequestBody WhatsAppAck whatsAppAck,
                                 @RequestParam("token") String token) throws Exception {

        logger.info("Token :: {}", token);
        if (!token.equals("whatsappdlrapi123")) {
            return 403;
        }


        logger.info("WhatsappA :: {}", whatsAppAck);
        List messageidAr = (List) whatsAppAck.getAck().get("unqId");

        for (Object unqId : messageidAr) {
            String messageid = unqId.toString();
            String status = whatsAppAck.getAck().get("status").toString();
            logger.info("UniqueID :: {}", messageid);
            logger.info("Status :: {}", status);
            campaignTransactionService.updateDlrStatus(messageid, status);
        }
        return 200;
    }


    // receive leads from Mind Belief to MOM's Belief
    // @CrossOrigin("*")
    @PostMapping("/crmleadtransfer")
    public int crmleadtransfer(@RequestParam("source") String source,
                               @RequestParam("token") String token,
                               @RequestParam("test") boolean testing,
                               @RequestParam("leadowner") String leadOwner,
                               @RequestBody LeadMasterDao leadMasterDao) throws Exception {

        logger.info("LeadMasterDao transferred from Center : {}", leadMasterDao);
        List<LogEventDao> logEvents = leadMasterDao.getLogEvents();
        logger.info("LogEvents transferred from Center : {}", logEvents);

        if (testing) {
            logger.info("Test success !! returning 200");
            return 200;
        }


        LeadMasterDao leadMasterDaotemp = leadMasterService.findByPhonenumber(leadMasterDao.getPhonenumber());
        if (leadMasterDaotemp != null) {
//            String tempAssmentnoteMoms = leadMasterDaotemp.getAssessmentNotes();
//            String tempAssmentnoteCenter = leadMasterDao.getAssessmentNotes();
//            leadMasterDaotemp.setAssessmentNotes(tempAssmentnoteMoms +" "+ tempAssmentnoteCenter );
            updateCurrentLead(leadMasterDaotemp);
            return 200;
        }


        LeadSourceDao leadSourceDao = leadSourceService.findLeadByLeadSource(source);

        logger.info("lsd {}", leadSourceDao);

        if (leadSourceDao.getStatus() == 0) {
            throw new Exception("This Lead Source Is Not Enabled");
        }
        if (leadSourceDao == null) {
            throw new Exception("Lead Source Cannot be found!!");
        }
        if (!token.equals(leadSourceDao.getAccessToken())) {
            throw new Exception("Invalid Access Token");
        }
        int userId = 0;
        try {
            UserMasterDao user = userMasterService.findByUsername(leadOwner);
            if (user != null) {
                userId = user.getId();
            } else {
                userId = findUserByType(leadSourceDao.getAssignBy(), source, leadMasterDao.getInterestedProduct(), leadMasterDao.getClientType());
            }
        } catch (Exception e) {
            logger.info("Exception while fetching user for lead owner : {}", e.getMessage());
            userId = findUserByType(leadSourceDao.getAssignBy(), source, leadMasterDao.getInterestedProduct(), leadMasterDao.getClientType());
        }

        leadMasterDao.setLastForward(userId);

        leadMasterDao.setClientType(leadSourceDao.getInitialType());
        leadMasterDao.setLeadStage(leadSourceDao.getInitialStage());
        leadMasterDao.setLeadStatus(leadSourceDao.getInitialStatus());

        leadMasterDao.setCreateBy("System");
        leadMasterDao.setCreateDate(UtilityClass.getDateRedable());

        leadMasterDao.setLeadDate(UtilityClass.getLeadDate());
        leadMasterDao.setLeadDatefilter(UtilityClass.dateFilterDate());

        leadMasterDao.setDateFilter(UtilityClass.dateFilterDate());
        leadMasterDao.setUpdateBy("System");
        leadMasterDao.setUpdateDate(UtilityClass.getDateRedable());
        //leadMasterDao.setLeadSource(source);
        leadMasterDao.setLeadsourceinner("CRMTransfer");

        leadMasterDao.setInterestedAgent("");
        leadMasterDao.setInterestedDate("");
        leadMasterDao.setInterestedDateFilter(0);

        leadMasterDao.setProfilingAgent("");
        leadMasterDao.setProfilingAgentId(0);
        leadMasterDao.setProfilingDate("");
        leadMasterDao.setProfilingDateFilter(0);


        logger.info("Inserting Lead >>> {}", leadMasterDao);
        try {
            leadMasterDao = leadMasterService.insert(leadMasterDao);

            List<LogEventDao> eventDaoList2 = new ArrayList<>();
            LogEventDao logEventDao = null;
            for (LogEventDao tempLogEventDao : logEvents) {
                logEventDao = tempLogEventDao;
                logEventDao.setLeadId(leadMasterDao.getId());
                eventDaoList2.add(logEventDao);
            }
            logEventService.insertMany(eventDaoList2);

            NotificationDao notificationDao = new NotificationDao();
            notificationDao.setCreateBy("System");
            notificationDao.setDatetiming(UtilityClass.fullDateLong());
            notificationDao.setCreateAt(UtilityClass.getDateRedable());
            notificationDao.setTo(userId);
            notificationDao.setSubject("New Lead From " + source);
            notificationDao.setSendMessage(UtilityClass.propertyService.findProperty("Lead", "Send_Text_Message_On_Lead").equalsIgnoreCase("1"));
            notificationDao.setInnerSubject("NOTIFY");
            notificationDao.setRedirectUrl("lead?id=" + leadMasterDao.getId());
            notificationDao.setLeadId(leadMasterDao.getId());
            notificationDao.setNotificationTiming(UtilityClass.getDateMysql());
            notificationDao.setMessage("New Lead From " + source);
            notificationService.insertSingleNotification(notificationDao);
        } catch (Exception e) {
            logger.error("Error Occured!! {}", e);
            String message = handleException(e.getMessage());
            logger.error("Error Occured!! {}", message);
            throw new Exception(message);
        }


        return 200;

    }


    private String formatPhonenumber(String phonenumber) {
        int len = phonenumber.length();
        if (len > 10) {
            phonenumber = phonenumber.substring(len - 10, len);
            return phonenumber;
        } else {
            return phonenumber;
        }

    }

    private String formatPhonenumber(LeadMasterDao leadMasterDao) {

        if (leadMasterDao.getClientType().contains("International") || leadMasterDao.getClientType().contains("international")) {
            logger.info("Formatting Phone number as per Internation Requirement");

            String rawphone_number = leadMasterDao.getPhonenumber();
            rawphone_number = rawphone_number.replaceAll("[^0-9]", "");
            return rawphone_number;
        } else {
            logger.info("Formatting Phone number as per local Requirement");
            String rawphone_number = leadMasterDao.getPhonenumber();
            rawphone_number = rawphone_number.replaceAll("[^0-9]", "");
            int len = rawphone_number.length();
            if (len > 10) {
                leadMasterDao.setPhonenumber(rawphone_number.substring(len - 10, len));
                return leadMasterDao.getPhonenumber();
            } else {
                leadMasterDao.setPhonenumber(rawphone_number);
                return leadMasterDao.getPhonenumber();
            }
        }

    }


    void pushNotification(LeadMasterDao leadMasterDao, LeadSourceDao leadSourceDao) throws Exception {
        String senderId = UtilityClass.propertyService.findProperty("Lead", "LeadMessagingSenderId");
        if (leadSourceDao.getSendnotification() == 1) {

            TemplateDocument templateDocument = templateDocumentService.findById(leadSourceDao.getTemplate());

            MessageCampaignDao messageCampaignDao = new MessageCampaignDao(templateDocument.getParams(), leadMasterDao.convertToObjectMail(), templateDocument.getTemplateIdHidden());
            TextMessage textMessage = messageCampaignDao.convertToObject(senderId);
            textMessage.setSender(senderId);
            textMessageService.sendTextMessage(textMessage);
        }
        if (leadSourceDao.getSendnotificationothers() == 1) {
            TemplateDocument templateDocument = templateDocumentService.findById(leadSourceDao.getTemplateothers());
            String[] phonenumber = leadSourceDao.getPhonenumbers().split(",");
            MessageCampaignDao messageCampaignDao = new MessageCampaignDao(templateDocument.getParams(), leadMasterDao.convertToObjectMail(), templateDocument.getTemplateIdHidden(), phonenumber);
            TextMessage textMessage = messageCampaignDao.convertToObject(senderId);

            textMessage.setSender(senderId);

            textMessageService.sendTextMessage(textMessage);
        }


    }

    private int findUserByType(String type, String leadSource, String product, String clientType) throws Exception {
        int userId = 0;
        if (type.equalsIgnoreCase("Source")) {
            validationString(leadSource, "As Lead is assigned by Source parameter source cannot be empty");
            userId = leadSourceService.findRanDomUserId(leadSource);
            if (userId == 0) {
                throw new Exception("No User Is Assigned To This Lead Source!!");
            }
        } else if (type.equalsIgnoreCase("Type")) {
            validationString(type, "As Lead is assigned by Type parameter type cannot be empty");
            userId = leadTypeService.findRandomUser(clientType);
            if (userId == 0) {
                throw new Exception("No User Is Assigned To This Lead Type!!");
            }
        } else if (type.equalsIgnoreCase("Product")) {
            validationString(product, "As Lead is assigned by Product parameter product cannot be empty");
            userId = productMasterService.findRandomUser(product);
            if (userId == 0) {
                logger.error("Error Capturing Lead As No User Can Be Found Inside Source");
                throw new Exception("No User Is Assigned To This Lead Product!!");
            }

        }
        return userId;

    }

    @GetMapping("/insertfacebook")
    public String insertFaceBook(@RequestParam("hub.verify_token") String token, @RequestParam("hub.challenge") String challenge, @RequestParam("source") String source, @RequestParam("accesstoken") String accesstoken, @RequestParam("product") String product, @RequestParam("type") String type, @RequestParam("test") boolean testing) {
        logger.info("Token {} Hub Challenge {}", token, challenge);
        UtilityClass.facebookAccessToken = token;

        return challenge;

    }

    @GetMapping("/insertleadivrnew")
    public int insertLeadIvrFirstStage(@RequestParam("calltype") String callType, @RequestParam("recordingurl") String recordingUrl, @RequestParam("mobile") String mobile) throws Exception {


        System.out.println("insertleadivrnew");
        if (callType.equalsIgnoreCase("c2c")) {
            mobile = formatPhonenumber(mobile);
            leadMasterService.updateLeadRecordingUrl(mobile, recordingUrl);
            LeadMasterDao leadMasterDao = leadMasterService.findByPhonenumber(mobile);
            UserMasterDao userMasterDao = userMasterService.findById(leadMasterDao.getLastForward());
            HashMap<String, String> hashMap = new HashMap<>();
            hashMap.put("@agentName", userMasterDao.getFirstName() + " " + userMasterDao.getLastName());
            hashMap.put("@agentNumber", userMasterDao.getMobile());
            hashMap.put("@email", userMasterDao.getEmail());
            hashMap.put("notifycentre", "false");

            logger.info("Lead Type is : {}", leadMasterDao.getClientType());
            LeadTypeDao leadTypeDao = leadTypeService.findByName(leadMasterDao.getClientType());
            logger.info("increasing C2C Attempts count");
            leadMasterService.updateC2cAttempts(leadMasterDao.getId());
            logger.info("Send SMS after C2C? : {}", leadTypeDao.isSendSms());
            if (leadTypeDao.isSendSms()) {
                if (recordingUrl.equalsIgnoreCase("0")) { // if call was not picked
                    logger.info("Call not answered/connected");
                    leadMasterService.singleNotificationWithParameter(leadMasterDao, UtilityClass.propertyService.findProperty("IVR", "Click_To_Call_Failed_Template"), "", hashMap);
                } else { //if call was picked
                    logger.info("Call answered");
                    leadMasterService.singleNotificationWithParameter(leadMasterDao, UtilityClass.propertyService.findProperty("IVR", "Click_To_Call_Success_Template"), "", hashMap);
                }
            }


        }


        return 200;


    }

    private void updateCurrentLead(LeadMasterDao leadMasterDaotemp) throws SQLException {
        logger.info("Duplicate lead so will update counter {}", leadMasterDaotemp);
        if (leadMasterDaotemp.getLeadSource().equalsIgnoreCase("Center_Transfer")){
            leadMasterService.updateLeadCounterAndUpdateTransferLead(leadMasterDaotemp.getId(), leadMasterDaotemp.getLeadSource());
        }
        else {
            leadMasterService.updateLeadCounterAndUpdateDate(leadMasterDaotemp.getId(), leadMasterDaotemp.getLeadSource());
        }
        NotificationDao notificationDao = new NotificationDao();
        notificationDao.setCreateBy("System");
        notificationDao.setDatetiming(UtilityClass.fullDateLong());
        notificationDao.setCreateAt(UtilityClass.getDateRedable());
        notificationDao.setTo(leadMasterDaotemp.getLastForward());
        notificationDao.setSubject("New Lead From " + leadMasterDaotemp.getLeadSource());
        notificationDao.setSendMessage(UtilityClass.propertyService.findProperty("Lead", "Send_Text_Message_On_Lead").equalsIgnoreCase("1"));
        notificationDao.setInnerSubject("NOTIFY");
        notificationDao.setRedirectUrl("lead?id=" + leadMasterDaotemp.getId());
        notificationDao.setLeadId(leadMasterDaotemp.getId());
        notificationDao.setNotificationTiming(UtilityClass.getDateMysql());
        notificationDao.setMessage("New Lead From " + leadMasterDaotemp.getLeadSource());
        notificationService.insertSingleNotification(notificationDao);
    }

    //API FOR UPDATING LEAD STAGE AND STATUS WITH MBOPS-CHILD-ID
    @GetMapping("/updatestageandstatus")
    public String updateLeadStageAndStatus(@RequestParam("source") String source,
                                           @RequestParam("token") String token,
                                           @RequestParam("mbopschildid") String mbopschildid,
                                           @RequestParam("stage") String stage,
                                           @RequestParam("status") String status,
                                           @RequestParam(required = false, name = "assessmentNotes") String assessmentNotes) throws Exception {
        logger.info("Received Lead Stage and Status update request for MBOPS-Child-ID >>  {}", mbopschildid);
        logger.info("Lead Source >> {}", source);
        logger.info("Token >> {}", token);
        logger.info("Lead Stage >> {}", stage);
        logger.info("Lead Status >> {}", status);

        LeadSourceDao leadSourceDao = leadSourceService.findLeadByLeadSource(source);
        logger.info("LeadSourceDao >> {}", leadSourceDao);

        if (leadSourceDao.getStatus() == 0) {
            return "This Lead Source Is Not Enabled";
        }
        if (leadSourceDao == null) {
            return "Lead Source Cannot be found!!";
        }
        if (!token.equals(leadSourceDao.getAccessToken())) {
            return "Invalid Access Token";
        }

        int updated = 0;
        if (assessmentNotes == null) {
            updated = leadMasterService.updateLeadStageAndStatusByChildId(mbopschildid, stage, status, assessmentNotes, false);
        } else {
            updated = leadMasterService.updateLeadStageAndStatusByChildId(mbopschildid, stage, status, assessmentNotes, true);
        }

        if (updated == 0) {
            return "Update failed";
        }
        // notificationSenderForAPI(mbopschildid,"mbopschildid",stage,status);
        return "Success";
    }


    //API FOR UPDATING LEAD STAGE AND STATUS WITH MBOPS-CHILD-ID
    @GetMapping("/updatestageandstatusbyphone")
    public String updateLeadStageAndStatusbyphone(@RequestParam("source") String source,
                                                  @RequestParam("token") String token,
                                                  @RequestParam("phonenumber") String phonenumber,
                                                  @RequestParam("stage") String stage,
                                                  @RequestParam("status") String status,
                                                  @RequestParam(required = false, name = "assessmentNotes") String assessmentNotes) throws Exception {
        logger.info("Received Lead Stage and Status update request for phonenumber >>  {}", phonenumber);
        logger.info("Lead Source >> {}", source);
        logger.info("Token >> {}", token);
        logger.info("Lead Stage >> {}", stage);
        logger.info("Lead Status >> {}", status);

//        LeadSourceDao leadSourceDao = leadSourceService.findLeadByLeadSource(source);
//        logger.info("LeadSourceDao >> {}", leadSourceDao);
//
//        if (leadSourceDao.getStatus() == 0) {
//            return "This Lead Source Is Not Enabled";
//        }
//        if (leadSourceDao == null) {
//            return "Lead Source Cannot be found!!";
//        }
//        if (!token.equals(leadSourceDao.getAccessToken())) {
//            return "Invalid Access Token";
//        }
        System.out.println("status is PRESENT" + leadStageService.leadStatusExists(status));
        System.out.println("Stage is PRESENT" + leadStageService.leadStageExist(stage));

        if (leadStageService.leadStatusExists(status) && leadStageService.leadStageExist(stage)) {
            int updated = 0;
            if (assessmentNotes == null) {
                updated = leadMasterService.updateLeadStageAndStatusByPhoneNumber(phonenumber, stage, status, assessmentNotes, false);
            } else {
                updated = leadMasterService.updateLeadStageAndStatusByPhoneNumber(phonenumber, stage, status, assessmentNotes, true);
            }

            if (updated == 0) {
                return "Disposition Missing on Moms CRM";
            }
            //hence setting custom lead source notificationSenderForAPI(phonenumber,"phonenumber",stage,status);

            return "Successfully Update Other CRM";
        } else {
            logger.error("Lead Stage or Status is not exist");
            return "Disposition Missing on Moms CRM";
        }


    }

    private void notificationSenderForAPI(String token, String findCase, String leadStage, String leadStatus) throws Exception {
        LeadMasterDao leadMasterDao = null;
        logger.info("find leadMasterDao by : {}", findCase);

        if (findCase.equalsIgnoreCase("mbopschildid")) {
            logger.info("finding leadMasterDao by MBOPS child ID : {}", token);
            leadMasterDao = leadMasterService.findByMBOPSChildId(token);

        } else if (findCase.equalsIgnoreCase("phonenumber")) {
            logger.info("finding leadMasterDao by phonenumber : {}", token);
            leadMasterDao = leadMasterService.findByPhonenumber(token);
        }

        UserMasterDao userMasterDao = userMasterService.findById(leadMasterDao.getLastForward());
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("@agentName", userMasterDao.getFirstName() + " " + userMasterDao.getLastName());
        hashMap.put("@agentNumber", userMasterDao.getMobile());
        hashMap.put("@agentEmail", userMasterDao.getEmail());
        hashMap.put("notifycentre", "false");

        StageOptionDocument stageOptionDocument = leadStageService.getStatusByStage(leadStage, leadStatus);
        if (stageOptionDocument == null) {
            throw new Exception("Invalid Stage or Status");
        }
        logger.info("stageOptionDocument > {}", stageOptionDocument);
        if (stageOptionDocument.getTemplateId().length() > 5) {

            String[] templates = stageOptionDocument.getTemplateId().split("\\|");
            logger.info("templates > {}", templates);
            for (String templateid : templates) {
                logger.info("Sending notification with templateid : {}", templateid);
                leadMasterService.singleNotificationWithParameter(leadMasterDao, templateid, "", hashMap);
            }
        }

    }

    //API FOR FETCHING LEAD DATA FROM LMS TO OTHER SOURCE
    @GetMapping("/fetchlead")
    public LeadResponseDao fetchLead(@RequestParam("source") String source, @RequestParam("token") String token, @RequestParam("phonenumber") String phonenumber) throws Exception {
        logger.info("Received Fetch request for Phone Number >>  {}", phonenumber);
        logger.info("Lead Source >> {}", source);
        logger.info("Token >> {}", token);

        LeadSourceDao leadSourceDao = leadSourceService.findLeadByLeadSource(source);
        logger.info("LeadSourceDao >> {}", leadSourceDao);

        if (leadSourceDao.getStatus() == 0) {
            throw new Exception("This Lead Source Is Not Enabled");
        }
        if (leadSourceDao == null) {
            throw new Exception("Lead Source Cannot be found!!");
        }
        if (!token.equals(leadSourceDao.getAccessToken())) {
            throw new Exception("Invalid Access Token");
        }

        LeadMasterDao leadMasterDao = leadMasterService.findByPhonenumber(phonenumber);
        LeadResponseDao res = new LeadResponseDao();
        res.setStatusCode(200);
        if (leadMasterDao != null) {
            res.setLeadId(leadMasterDao.getId());
            res.setLeadStage(leadMasterDao.getLeadStage());
            res.setLeadStatus(leadMasterDao.getLeadStatus());
            res.setLeadOwner(userMasterService.findById(leadMasterDao.getLastForward()).getUsername());
        } else {

            res.setLeadStage("Not found");
            res.setLeadStatus("Not found");
            res.setLeadOwner("Not found");
        }

        return res;
    }

    //    temporary method for mom's belief
    @GetMapping("/{leadservice}/insertlead")
    public int insertLead(@PathVariable("leadservice") String leadService,
                          @RequestParam("source") String source,
                          @RequestParam("product") String product,
                          @RequestParam("type") String type,
                          @RequestParam("adid") long adid,
                          @RequestParam("campaign_name") String campaign_name,
                          @RequestParam("raw_city") String raw_city,
                          @RequestParam("rawphone_number") String rawphone_number,
                          @RequestParam("raw_email") String raw_email,
                          @RequestParam("full_name") String full_name,
                          @RequestParam("keyword") String keyword,
                          @RequestParam("created_time") String created_time,
                          @RequestParam(name = "messenger", required = false, defaultValue = "") String messenger,
                          @RequestParam(name = "active", required = false, defaultValue = "") String active,
                          @RequestParam(name = "ad_form_data", required = false, defaultValue = "") String adFormData,
                          @RequestParam(name = "page_name", required = false, defaultValue = "") String pageName,
                          @RequestParam("token") String token,
                          @RequestParam("test") boolean testing,
                          @RequestParam(required = false, name = "pincode") String pincode,
                          @RequestParam(required = false, name = "leadscore") Integer leadscore,
                          @RequestParam(required = false, name = "preferredCallingTime", defaultValue = "NA") String preferredCallingTime) throws Exception {

        logger.info("Received lead from {}", leadService);
        logger.info("source >> {}", source);
        logger.info("product >> {}", product);
        logger.info("type >> {}", type);
        logger.info("adid >> {}", adid);
        logger.info("campaign_name >> {}", campaign_name);
        logger.info("raw_city >> {}", raw_city);
        logger.info("rawphone_number >> {}", rawphone_number);
        logger.info("raw_email >> {}", raw_email);
        logger.info("full_name >> {}", full_name);
        logger.info("keyword >> {}", keyword);
        logger.info("created_time >> {}", created_time);
        logger.info("messenger >> {}", messenger);
        logger.info("active >> {}", active);
        logger.info("adFormData >> {}", adFormData);
        logger.info("pageName >> {}", pageName);
        logger.info("token >> {}", token);
        logger.info("test >> {}", testing);
        logger.info("LeadScore >> {}", leadscore);
        logger.info("preferredCallingTime >> {}", preferredCallingTime);

        //-------------------------------------
        //THE INITIAL SOURCE WHICH WAS SET WHILE SENDING LEAD
        LeadSourceDao initialSourceDao = leadSourceService.findLeadByLeadSource(source);

        logger.info("Initial LeadSource {}", initialSourceDao);

        if (initialSourceDao == null) {
            throw new Exception("Lead Source Cannot be found!!");
        }

        if (initialSourceDao.getStatus() == 0) {
            throw new Exception("This Lead Source Is Not Enabled");
        }

        if (!token.equals(initialSourceDao.getAccessToken())) {
            throw new Exception("Invalid Access Token");
        }
        //-------------------------------------

        campaign_name = campaign_name.replaceAll("\\|", "-");

        if (leadscore == null) {
            leadscore = 0;
        }
        if (pincode == null) {
            pincode = "0";
        }
        logger.info("Pincode >> {}", pincode);


        if (testing) {
            return 200;
        }

        rawphone_number = rawphone_number.replaceAll("[^0-9]", "");

        if (campaign_name.contains("International") || campaign_name.contains("international") || campaign_name.contains("GCC")) {
            logger.info("Campaign Name contains word 'international' , hence saving phone number as received");
        } else {
            logger.info("Campaign Name Doesnt contain word 'international' , hence formatting phone number");
            rawphone_number = formatPhonenumber(rawphone_number);
        }

        LeadMasterDao leadMasterDaotemp = leadMasterService.findByPhonenumber(rawphone_number);
        if (leadMasterDaotemp != null) {

            logger.info("Lead with this phone number already exists, updating lead");
            leadMasterDaotemp.setLeadSource(source);
            //Change By Rahul on 19-02-2022
            leadMasterDaotemp.setClientType(type);
            leadMasterService.update(leadMasterDaotemp);
            //---------------------------------
            updateCurrentLead(leadMasterDaotemp);
            return 200;
        }

        LeadMasterDao leadMasterDao = new LeadMasterDao();
        LeadSourceDao leadSourceDao = null;
        int userId = 0;
        if (campaign_name.contains("International") || campaign_name.contains("international") || campaign_name.contains("GCC")) {
            logger.info("Campaign Name contains word 'international' , hence setting custom lead source");
            String newsource = source;// UtilityClass.propertyService.findProperty("Lead", "deafaultLeadSourceForInternationalLead");
            leadMasterDao.setLeadSource(newsource);
            leadSourceDao = leadSourceService.findLeadByLeadSource(newsource);
            leadMasterDao.setClientType(leadSourceDao.getInitialType());
            //type="International";
            userId = findUserByType(leadSourceDao.getAssignBy(), newsource, product, leadSourceDao.getInitialType());
        } else {
            logger.info("Campaign Name Doesnt contain word 'international' , hence setting zapier-provided lead source");
            leadMasterDao.setLeadSource(source);
            leadMasterDao.setClientType(type);
            leadSourceDao = leadSourceService.findLeadByLeadSource(source);
            userId = findUserByType(leadSourceDao.getAssignBy(), source, product, type);
        }


        leadMasterDao.setLeadScore(leadscore);
        logger.info("LeadSource For Lead {}", leadSourceDao);


        leadMasterDao.setParentName(full_name);
        leadMasterDao.setPhonenumber(rawphone_number);
        leadMasterDao.setEmail(raw_email);
        leadMasterDao.setCity(raw_city);
        leadMasterDao.setLeadPriority(campaign_name);
        leadMasterDao.setKeyword(keyword);
        leadMasterDao.setAdId(adid);
        leadMasterDao.setLastForward(userId);
        leadMasterDao.setInterestedProduct(product);
        leadMasterDao.setPincode(pincode);
        leadMasterDao.setMessenger(messenger);
        leadMasterDao.setActive(active);
        leadMasterDao.setAdFormData(adFormData);
        leadMasterDao.setPageName(pageName);

        leadMasterDao.setCreateBy("System");
        leadMasterDao.setCreateDate(UtilityClass.getDateRedable());

        leadMasterDao.setLeadDate(UtilityClass.getLeadDate());
        leadMasterDao.setLeadDatefilter(UtilityClass.dateFilterDate());

        leadMasterDao.setDateFilter(UtilityClass.dateFilterDate());
        leadMasterDao.setUpdateBy("System");
        leadMasterDao.setUpdateDate(UtilityClass.getDateRedable());
        leadMasterDao.setLeadsourceinner("API");

        if (!preferredCallingTime.equalsIgnoreCase("NA")) {
            leadMasterDao.setPreferredCallingTime(preferredCallingTime);
            leadMasterDao.setPreferredCallingSlot(findCallingSlot(preferredCallingTime));
        }

        logger.info("Inserting Lead >>> {}", leadMasterDao);
        try {
            leadMasterDao = leadMasterService.insert(leadMasterDao);
            NotificationDao notificationDao = new NotificationDao();
            notificationDao.setCreateBy("System");
            notificationDao.setDatetiming(UtilityClass.fullDateLong());
            notificationDao.setCreateAt(UtilityClass.getDateRedable());
            notificationDao.setTo(userId);
            notificationDao.setSubject("New Lead From " + source);
            notificationDao.setSendMessage(UtilityClass.propertyService.findProperty("Lead", "Send_Text_Message_On_Lead").equalsIgnoreCase("1"));
            notificationDao.setInnerSubject("NOTIFY");
            notificationDao.setRedirectUrl("lead?id=" + leadMasterDao.getId());
            notificationDao.setLeadId(leadMasterDao.getId());
            notificationDao.setNotificationTiming(UtilityClass.getDateMysql());
            notificationDao.setMessage("New Lead From " + source);
            notificationService.insertSingleNotification(notificationDao);
        } catch (Exception e) {
            logger.error("Error Occured!! {}", e);
            String message = handleException(e.getMessage());
            logger.error("Error Occured!! {}", message);
            throw new Exception(message);
        }


        return 200;

    }

    @GetMapping("/{leadservice}/insertfacebooklead")
    public int insertFaceBookLead(@PathVariable("leadservice") String leadService, @RequestBody ZapierDao dao, @RequestParam("source") String source, @RequestParam("product") String product, @RequestParam("type") String type, @RequestParam("test") boolean testing) throws Exception {

        logger.info("Received lead from {}", leadService);

        if (testing) {
            return 200;
        }
        LeadMasterDao leadMasterDaotemp = leadMasterService.findByPhonenumber(dao.getPhone_number());
        if (leadMasterDaotemp != null) {

            updateCurrentLead(leadMasterDaotemp);
            return 200;
        }
        LeadSourceDao leadSourceDao = leadSourceService.findLeadByLeadSource(source);
        if (leadSourceDao.getStatus() == 0) {
            throw new Exception("This Lead Source Is Not Enabled");
        }
        if (leadSourceDao == null) {
            throw new Exception("Lead Source Cannot be found!!");
        }
        int userId = findUserByType(leadSourceDao.getAssignBy(), source, product, type);

        LeadMasterDao leadMasterDao = dao.convertToLeadMaster();
        leadMasterDao.setLastForward(userId);
        leadMasterDao.setInterestedProduct(product);
        leadMasterDao.setClientType(type);
        leadMasterDao.setLeadSource(source);
        leadMasterDao.setCreateBy("System");
        leadMasterDao.setCreateDate(UtilityClass.getDateRedable());
        leadMasterDao.setDateFilter(UtilityClass.dateFilterDate());
        leadMasterDao.setUpdateBy("System");
        leadMasterDao.setUpdateDate(UtilityClass.getDateRedable());
        leadMasterDao.setLeadsourceinner("API");

        logger.info("Inserting Lead >>> {}", leadMasterDao);
        leadMasterDao = leadMasterService.insert(leadMasterDao);

        NotificationDao notificationDao = new NotificationDao();
        notificationDao.setCreateBy("System");
        notificationDao.setDatetiming(UtilityClass.fullDateLong());
        notificationDao.setCreateAt(UtilityClass.getDateRedable());
        notificationDao.setTo(userId);
        notificationDao.setSubject("New Lead From " + source);
        notificationDao.setSendMessage(UtilityClass.propertyService.findProperty("Lead", "Send_Text_Message_On_Lead").equalsIgnoreCase("1"));
        notificationDao.setInnerSubject("NOTIFY");
        notificationDao.setRedirectUrl("lead?id=" + leadMasterDao.getId());
        notificationDao.setLeadId(leadMasterDao.getId());
        notificationDao.setNotificationTiming(UtilityClass.getDateMysql());
        notificationDao.setMessage("New Lead From " + source);
        notificationService.insertSingleNotification(notificationDao);

        return 200;

    }

    @PostMapping("/insertfacebook")
    public int insertFaceBookPostMethod(@RequestBody FacebookPayloadDao facebookPayloadDao, @RequestParam("source") String source, @RequestParam("accesstoken") String accesstoken, @RequestParam("product") String product, @RequestParam("type") String type, @RequestParam("test") boolean testing) throws Exception {
        if (testing) {
            return 200;
        }
        logger.info("Incoming Lead From Facebook {}", facebookPayloadDao);
        LeadSourceDao leadSourceDao = leadSourceService.findLeadByLeadSource(source);
        if (leadSourceDao.getStatus() == 0) {
            throw new Exception("This Lead Source Is Not Enabled");
        }
        if (!leadSourceDao.getAccessToken().equalsIgnoreCase(accesstoken)) {
            throw new Exception("Invalid Access Token");
        }
        if (leadSourceDao == null) {
            throw new Exception("Lead Source Cannot Be " + source);
        }
        int userId = findUserByType(leadSourceDao.getAssignBy(), source, product, type);
        String date = UtilityClass.getDateRedable();
        int dateFilter = UtilityClass.dateFilterDate();

        List<FacebookDao> alfacebookDao = facebookAddService.getLeadDetailsById(facebookPayloadDao.getChanges());
        List<FacebookFieldDataDao> field_data;
        List<LeadMasterDao> leadMasterDaoList = new ArrayList<>();
        if (leadMasterDaoList.size() == 0) {
            throw new Exception("Cannot Post Empty Lead Facebook");
        }
        LeadMasterDao leadMasterDao;
        for (int i = 0; i < alfacebookDao.size(); i++) {
            leadMasterDao = new LeadMasterDao();
            field_data = alfacebookDao.get(i).getField_data();
            for (int m = 0; m < field_data.size(); m++) {

                String field = field_data.get(i).getName();
                String value = convertArrayToString(field_data.get(i).getValues());
                switch (field) {
                    case "email":
                        leadMasterDao.setEmail(value);
                        break;
                    case "phone_number":
                        leadMasterDao.setPhonenumber(value);
                        break;
                    case "full_name":
                        leadMasterDao.setFirstName(value);
                        break;

                }


            }

            leadMasterDao.setLeadStage(leadSourceDao.getInitialStage());
            leadMasterDao.setLeadStatus(leadSourceDao.getInitialStatus());
            leadMasterDao.setLastForward(userId);
            leadMasterDao.setCreateBy("System");
            leadMasterDao.setUpdateBy("System");
            leadMasterDao.setCreateDate(date);
            leadMasterDao.setUpdateDate(date);
            leadMasterDao.setDateFilter(dateFilter);
            leadMasterDao.setUpdatedatefilter(dateFilter);
            leadMasterDao.setInterestedProduct(product);
            leadMasterDao.setInterestedProduct(product);
            leadMasterDao.setClientType(type);
            leadMasterDao.setLeadSource(source);
            leadMasterDaoList.add(leadMasterDao);
        }
        UserMasterDao userMasterDao = new UserMasterDao();
        userMasterDao.setUsername("System");
        //String message = leadMasterService.bulkInsert(leadMasterDaoList, userMasterDao);
        leadMasterService.bulkInsert(leadMasterDaoList, userMasterDao);

        //logger.info("Suucessfully Inserted Leads {}", message);
        for (int i = 0; i < leadMasterDaoList.size(); i++) {
            leadMasterDao = leadMasterDaoList.get(i);
            try {
                pushNotification(leadMasterDao, leadSourceDao);
            } catch (Exception ew) {
                logger.error("Error", ew);
                logger.error("Failed To Sent MEssage {}", ew.getMessage());
            }
        }
        return 200;
    }

    private String convertArrayToString(String[] arr) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < arr.length; i++) {
            sb.append(arr[i]);
            sb.append(",");
        }
        return sb.toString();
    }

    private void validate(LeadMasterDao leadMasterDao) throws Exception {
        validationString(leadMasterDao.getParentName(), "Required Parameter parentName Cannot Be Found");
        validationString(leadMasterDao.getPhonenumber(), "Required Parameter phonenumber Cannot Be Found");
        validationString(leadMasterDao.getEmail(), "Required Parameter email Cannot Be Found");
        validationString(leadMasterDao.getCity(), "Required Parameter city Cannot Be Found");
    }

    private void validationString(String value, String errorname) throws ValidationFailException {
        try {
            if (value.length() == 0 || value == null) {
                throw new ValidationFailException(errorname);
            }
        } catch (Exception ew) {
            throw new ValidationFailException(errorname);
        }
    }

    @PostConstruct
    private void onInit() {
        isoToCodeMap = new HashMap<>();
        isoToCodeMap.put("0", 0);
        isoToCodeMap.put("us", 1);
        isoToCodeMap.put("in", 2);
        isoToCodeMap.put("au", 3);
        isoToCodeMap.put("gb", 4);
        isoToCodeMap.put("cn", 5);
        isoToCodeMap.put("ae", 6);
        isoToCodeMap.put("ca", 7);
        isoToCodeMap.put("sa", 8);
        isoToCodeMap.put("kw", 9);
        isoToCodeMap.put("bd", 10);
        isoToCodeMap.put("qa", 11);
        isoToCodeMap.put("om", 12);
        isoToCodeMap.put("se", 13);
        isoToCodeMap.put("bh", 14);
        isoToCodeMap.put("cz", 15);
        isoToCodeMap.put("np", 16);
        isoToCodeMap.put("de", 17);

//        ==============================


        countryToCodeMap.put("NA", 0);
        countryToCodeMap.put("United States of America (USA)", 1);
        countryToCodeMap.put("India", 2);
        countryToCodeMap.put("Australia", 3);
        countryToCodeMap.put("United Kingdom", 4);
        countryToCodeMap.put("China", 5);
        countryToCodeMap.put("United Arab Emirates", 6);
        countryToCodeMap.put("Canada", 7);
        countryToCodeMap.put("Saudi Arabia", 8);
        countryToCodeMap.put("Kuwait", 9);
        countryToCodeMap.put("Bangladesh", 10);
        countryToCodeMap.put("Qatar", 11);
        countryToCodeMap.put("Oman", 12);
        countryToCodeMap.put("Sweden", 13);
        countryToCodeMap.put("Bahrain", 14);
        countryToCodeMap.put("Czech Republic", 15);
        countryToCodeMap.put("Nepal", 16);
        countryToCodeMap.put("Germany", 17);
    }

    private String findCallingSlot(String date) {
        String slot = "";
        try {
            Integer time = Integer.parseInt(date.split(" ")[1].replaceAll(":", ""));
            String amPm = date.split(" ")[2];

            if ((time >= 900 && time < 1200) && amPm.equalsIgnoreCase("am")) {
                slot = "Morning";
            } else if (((time >= 100 && time <= 300) && (time >= 1200 && time <= 1259)) && amPm.equalsIgnoreCase("pm")) {
                slot = "Afternoon";
            } else if ((time >= 301 && time <= 600) && amPm.equalsIgnoreCase("pm")) {
                slot = "Evening";
            } else {
                slot = "Other";
            }
        } catch (Exception ex) {
            logger.warn("Exception while finding slot for calling the lead!");
        }
        return slot;
    }

    // THIS API IS BEING USED BY MOM'S BELIEF TO INSERT LEADS (2021-06-23)
    @PostMapping("/insertlead")
    public ResponseDao insertLeadDynamic(@ModelAttribute LeadMasterDao leadMasterDao,
                                         @RequestParam("source") String source,
                                         @RequestParam("accesstoken") String accesstoken,
                                         @RequestParam("product") String product,
                                         @RequestParam("type") String type,
                                         @RequestParam("test") boolean testing,
                                         @RequestParam(required = false, name = "leadscore") Integer leadscore,
                                         @RequestParam(required = false, name = "country", defaultValue = "NA") String country,
                                         @RequestParam(required = false, name = "countrytype", defaultValue = "NONE") String countrytype) throws Exception {
        logger.info("Inserting Lead via insertlead  leadMasterDao >>> {}", leadMasterDao);
        logger.info("Inserting Lead via insertlead source >>> {}", source);
        logger.info("Inserting Lead via insertlead accesstoken >>> {}", accesstoken);
        logger.info("Inserting Lead via insertlead product >>> {}", product);
        logger.info("Inserting Lead via insertlead type >>> {}", type);
        logger.info("Inserting Lead via insertlead leadscore >>> {}", leadscore);
        logger.info("Inserting Lead via insertlead country >>> {}", country);
        logger.info("Inserting Lead via insertlead countrytype >>> {}", countrytype);


        //-----------------------------------
        logger.info("New Lead From Source {}", source);
        LeadSourceDao leadSourceDao = leadSourceService.findLeadByLeadSource(source);
        if (leadSourceDao == null) {
            throw new Exception("Lead Source Cannot be found!!");
        }

        if (leadSourceDao.getStatus() == 0) {
            throw new Exception("This Lead Source Is Not Enabled");
        }

        if (!accesstoken.equalsIgnoreCase(leadSourceDao.getAccessToken())) {
            throw new Exception("Invalid Token!!");
        }
        //-----------------------------------
        if (leadscore == null) {
            leadscore = 0;
        }

        validate(leadMasterDao);
        ResponseDao res = new ResponseDao();
        if (testing) {
            res.setStatusCode(200);
            return res;
        }

        //String rawPhoneNumber = leadMasterDao.getPhonenumber();
        //String phoneNumber = leadMasterDao.getPhonenumber();
        //phoneNumber = phoneNumber.replaceAll("[^0-9]", "");
        leadMasterDao.setClientType(type);
        leadMasterDao.setPhonenumber(formatPhonenumber(leadMasterDao));

        LeadMasterDao leadMasterDaotemp = leadMasterService.findByPhonenumber(leadMasterDao.getPhonenumber());

        //logger.info("leadMasterDaotemp :: {}",leadMasterDaotemp);
        if (leadMasterDaotemp != null) {

            String msg = "Lead Already Present";
            if (source.equalsIgnoreCase("RazorPay")) {
                logger.info("Setting payment for RazorPay Lead");
                leadMasterDaotemp.setPayment(leadMasterDao.getPayment());
                leadMasterDaotemp.setPaymentStatus(leadMasterDao.isPaymentStatus());
                leadMasterService.update(leadMasterDaotemp);
                msg += ", Updated Payment";
            }

            if (source.equalsIgnoreCase("OTPVerification")) {
                logger.info("updating OTP Status :: ");
                leadMasterDaotemp.setOtpStatus(leadMasterDao.getOtpStatus());
                leadMasterService.update(leadMasterDaotemp);
                msg += ", Updated OTP Status";
            }

            //leadMasterDaotemp.setLeadSource(source);
            updateCurrentLead(leadMasterDaotemp);
            res.setStatusCode(200);
            res.setMsg(msg);
            res.setId(leadMasterDaotemp.getId());
            return res;
        }


        int userId = findUserByType(leadSourceDao.getAssignBy(), source, product, type);
        leadMasterDao.setLeadScore(leadscore);
        leadMasterDao.setLastForward(userId);
        leadMasterDao.setCreateBy("System");
        leadMasterDao.setUpdateBy("System");
        leadMasterDao.setInterestedProduct(product);
        leadMasterDao.setClientType(type);
        leadMasterDao.setLeadSource(source);
        leadMasterDao.setLeadStage(leadSourceDao.getInitialStage());
        leadMasterDao.setLeadStatus(leadSourceDao.getInitialStatus());

        if (leadMasterDao.getCreateDate() == null) {
            leadMasterDao.setCreateDate(UtilityClass.getDateRedable());
            leadMasterDao.setDateFilter(UtilityClass.dateFilterDate());
        } else {
            //dd-mm-yyyy hh:mm:ss
            String[] dateFilterArr = leadMasterDao.getCreateDate().split(" ")[0].split("-");
            String dateFilterStr = dateFilterArr[2] + dateFilterArr[1] + dateFilterArr[0];
            int dateFilter = Integer.parseInt(dateFilterStr);
            leadMasterDao.setDateFilter(dateFilter);
        }


        leadMasterDao.setUpdatedatefilter(UtilityClass.dateFilterDate());
        leadMasterDao.setUpdateDate(UtilityClass.getDateRedable());
        leadMasterDao.setLeadsourceinner("API");
        leadMasterDao.setClientType(leadSourceDao.getInitialType());
        leadMasterDao.setInterestedProduct(product);
        leadMasterDao.setClientType(type);
        leadMasterDao.setLeadSource(source);

        //CHECK IF COUNTRY TYPE IS SET
        if (countrytype.equalsIgnoreCase("iso")) {
            leadMasterDao.setCountry(String.valueOf(isoToCodeMap.get(country)));
            //leadMasterDao.setCountry(country);
        } else if (countrytype.equalsIgnoreCase("name")) {
            leadMasterDao.setCountry(String.valueOf(countryToCodeMap.get(country)));
            //leadMasterDao.setCountry(country);
        }


        leadMasterDao.setPreferredCallingSlot(findCallingSlot(leadMasterDao.getPreferredCallingTime()));
        leadMasterDao = leadMasterService.insert(leadMasterDao);
        logger.info("Success inserted lead {}", leadMasterDao);
        NotificationDao notificationDao = new NotificationDao();
        notificationDao.setCreateBy("System");
        notificationDao.setDatetiming(UtilityClass.fullDateLong());
        notificationDao.setCreateAt(UtilityClass.getDateRedable());
        notificationDao.setTo(userId);
        notificationDao.setSubject("New Lead From " + source);
        notificationDao.setSendMessage(UtilityClass.propertyService.findProperty("Lead", "Send_Text_Message_On_Lead").equalsIgnoreCase("1"));
        notificationDao.setInnerSubject("NOTIFY");
        notificationDao.setRedirectUrl("lead?id=" + leadMasterDao.getId());
        notificationDao.setLeadId(leadMasterDao.getId());
        notificationDao.setNotificationTiming(UtilityClass.getDateMysql());
        notificationDao.setMessage("New Lead From " + source);
        notificationService.insertSingleNotification(notificationDao);
        res.setStatusCode(200);
        res.setMsg("Inserted New Lead");
        res.setId(leadMasterDao.getId());
        return res;
    }


    @PostMapping("/insertleadgoogleaddwords")
    public int insertLeadgoogleaddword(@RequestBody GoogleAddwordLeadDao googleAddwordLeadDao, @RequestParam("source") String source, @RequestParam("product") String product, @RequestParam("type") String type, @RequestParam("test") boolean testing, @RequestParam("accesstoken") String accesstoken) throws Exception {
        if (testing) {
            return 200;
        }
        logger.info("Inserting Google Add {}", googleAddwordLeadDao);
        if (googleAddwordLeadDao.getUser_column_data().size() == 0) {
            throw new Exception("Invalid Google Lead");
        }
        LeadSourceDao leadSourceDao = leadSourceService.findLeadByLeadSource(source);
        if (leadSourceDao.getStatus() == 0) {
            throw new Exception("This Lead Source Is Not Enabled");
        }
        if (!accesstoken.equalsIgnoreCase(leadSourceDao.getAccessToken())) {
            throw new Exception("Invalid Access Token");
        }
        LeadMasterDao leadMasterDao = new LeadMasterDao();

        int userId = findUserByType(leadSourceDao.getAssignBy(), source, product, type);
        List<GoogleAddwordInnerDetails> al = googleAddwordLeadDao.getUser_column_data();
        for (int i = 0; i < al.size(); i++) {
            if (al.get(i).getColumn_name().equalsIgnoreCase("Full Name")) {
                leadMasterDao.setFirstName(al.get(i).getString_value());
            } else if (al.get(i).getColumn_name().equalsIgnoreCase("User Email")) {
                leadMasterDao.setEmail(al.get(i).getString_value());

            } else if (al.get(i).getColumn_name().equalsIgnoreCase("User Phone")) {
                leadMasterDao.setPhonenumber(al.get(i).getString_value());


            } else if (al.get(i).getColumn_name().equalsIgnoreCase("Postal Code")) {
                leadMasterDao.setPincode(al.get(i).getString_value());


            }
        }

        leadMasterDao.setDescrip(googleAddwordLeadDao.getGoogle_key());
        leadMasterDao.setLastForward(userId);
        leadMasterDao.setCreateBy("System");
        leadMasterDao.setUpdateBy("System");
        leadMasterDao.setInterestedProduct(product);
        leadMasterDao.setClientType(type);
        leadMasterDao.setLeadStage(leadSourceDao.getInitialStage());
        leadMasterDao.setLeadStatus(leadSourceDao.getInitialStatus());
        leadMasterDao.setDateFilter(UtilityClass.dateFilterDate());
        leadMasterDao.setUpdatedatefilter(UtilityClass.dateFilterDate());
        leadMasterDao.setCreateDate(UtilityClass.getDateRedable());
        leadMasterDao.setUpdateDate(UtilityClass.getDateRedable());
        leadMasterDao.setLeadsourceinner("API");
        leadMasterDao.setClientType(leadSourceDao.getInitialType());
        leadMasterDao.setInterestedProduct(product);
        leadMasterDao.setClientType(type);
        leadMasterDao.setLeadSource(source);
        if (googleAddwordLeadDao.getIs_test()) {

            logger.info("Was Test Lead Will Print Only {}", leadMasterDao);
        } else {

            leadMasterDao = leadMasterService.insert(leadMasterDao);
            logger.info("Successfully Saved lead To Database {}", leadMasterDao);
            NotificationDao notificationDao = new NotificationDao();
            notificationDao.setCreateBy("System");
            notificationDao.setDatetiming(UtilityClass.fullDateLong());
            notificationDao.setCreateAt(UtilityClass.getDateRedable());
            notificationDao.setTo(userId);
            notificationDao.setSubject("New Lead From " + source);
            notificationDao.setSendMessage(UtilityClass.propertyService.findProperty("Lead", "Send_Text_Message_On_Lead").equalsIgnoreCase("1"));
            notificationDao.setInnerSubject("NOTIFY");
            notificationDao.setRedirectUrl("lead?id=" + leadMasterDao.getId());
            notificationDao.setNotificationTiming(UtilityClass.getDateMysql());
            notificationDao.setLeadId(leadMasterDao.getId());
            notificationDao.setMessage("New Lead From " + source);
            notificationService.insertSingleNotification(notificationDao);

            try {
                pushNotification(leadMasterDao, leadSourceDao);
            } catch (Exception ew) {
                logger.error("Error", ew);
                logger.error("Failed To Sent MEssage {}", ew.getMessage());
            }
        }


        return 200;
    }


    @PostMapping("/insertleadjustdial")
    public String insertLeadJustdial(HttpServletRequest request) throws Exception {

        String leadid = request.getParameter("leadid");
        String leadtype = request.getParameter("leadtype");
        String prefix = request.getParameter("prefix");
        String name = request.getParameter("name");
        String mobile = request.getParameter("mobile");
        String phone = request.getParameter("phone");
        String email = request.getParameter("email");
        String date = request.getParameter("date");
        String category = request.getParameter("category");
        String city = request.getParameter("city");
        String Area = request.getParameter("Area");
        String brancharea = request.getParameter("brancharea");
        String company = request.getParameter("company");
        String pincode = request.getParameter("pincode");
        String time = request.getParameter("time");
        String branchpin = request.getParameter("branchpin");
        String leadSource = "JustDial";
        StringBuilder sb = new StringBuilder();
        int userId = 0;
        LeadSourceDao leadSourceDao = UtilityClass.alleadsourcedao.get(leadSource);

        userId = leadSourceService.findRandomUserId(leadSourceDao.getId());
        LeadMasterDao leadMasterModel = new LeadMasterDao();
        if (userId == 0) {
            throw new Exception("No User Found In Lead Source!");
        }
        leadMasterModel.setLastForward(userId);
        leadMasterModel.setCreateBy("System");
        leadMasterModel.setUpdateBy("System");


        leadMasterModel.setLeadStatus(leadSourceDao.getInitialStatus());
        leadMasterModel.setLeadStage(leadSourceDao.getInitialStage());
        leadMasterModel.setClientType(leadSourceDao.getInitialType());
        leadMasterModel.setPhonenumber(mobile + " " + phone);
        leadMasterModel.setSalutation("Mr");
        leadMasterModel.setUpdatedatefilter(UtilityClass.dateFilterDate());
        leadMasterModel.setFirstName(name);
        leadMasterModel.setCompany(company);
        leadMasterModel.setEmail(email);
        leadMasterModel.setPhonenumber(mobile + " " + phone);
        leadMasterModel.setDateFilter(UtilityClass.dateFilterDate());
        leadMasterModel.setCreateDate(UtilityClass.getDateRedable());
        leadMasterModel.setUpdateDate(UtilityClass.getDateRedable());
        leadMasterModel.setLeadSource(leadSource);
        leadMasterModel.setLeadsourceinner("API");
        leadMasterModel.setCity(city);
        leadMasterModel.setPincode(pincode);
        leadMasterModel.setInterestedProduct(category);
        sb.append("Lead Type={");
        sb.append(leadtype);
        sb.append("}");
        sb.append("Category={");
        sb.append(category);
        sb.append("}");
        sb.append("city={");
        sb.append(city);
        sb.append("}");
        sb.append("Area={");
        sb.append(Area);
        sb.append("}");
        sb.append("BranchArea={");
        sb.append(brancharea);
        sb.append("}");
        sb.append("Pincode={");
        sb.append(pincode);
        sb.append("}");
        sb.append("Time={");
        sb.append(time);
        sb.append("}");
        leadMasterModel.setDescrip(sb.toString());
        logger.info("After Validation Api Request {}", leadMasterModel);
        if (leadMasterModel.getInterestedProduct().length() == 0) {
            leadMasterModel.setInterestedProduct("NA");
        }
        leadMasterModel = leadMasterService.insert(leadMasterModel);
        logger.info("Success inserted lead {}", leadMasterModel);


        NotificationDao notificationDao = new NotificationDao();
        notificationDao.setCreateBy("System");
        notificationDao.setDatetiming(UtilityClass.fullDateLong());
        notificationDao.setCreateAt(UtilityClass.getDateRedable());
        notificationDao.setTo(userId);
        notificationDao.setSubject("New Lead From " + leadSource);
        notificationDao.setSendMessage(UtilityClass.propertyService.findProperty("Lead", "Send_Text_Message_On_Lead").equalsIgnoreCase("1"));
        notificationDao.setInnerSubject("NOTIFY");
        notificationDao.setRedirectUrl("lead?id=" + leadMasterModel.getId());
        notificationDao.setNotificationTiming(UtilityClass.getDateMysql());
        notificationDao.setLeadId(leadMasterModel.getId());
        notificationDao.setMessage("New Lead From " + leadSource);
        notificationService.insertSingleNotification(notificationDao);
        try {
            pushNotification(leadMasterModel, leadSourceDao);
        } catch (Exception ew) {
            logger.error("Error", ew);
            logger.error("Failed To Sent MEssage {}", ew.getMessage());
        }
        return "RECEIVED";
    }

    @PostMapping("/insertleadbyivr")
    public ResponseDao insertLeadByIvr(
            @RequestParam("clientType") String clientType,
            @RequestParam("product") String product,
            @RequestParam("source") String source,
            @RequestParam("accessToken") String accessToken,
            @RequestParam("phonenumber") String clientPhoneNumber,
            @RequestParam("call_type") String call_type,
            @RequestParam("call_status") String call_status,
            @RequestParam("agentNo") String agentNo,
            @RequestParam(name = "recordingURL", defaultValue = "") String recordingURL,
            @RequestParam(name = "recordFileName", defaultValue = "") String recordFileName,
            @RequestParam("callStartTime") String callStartTime,
            @RequestParam("callEndTime") String callEndTime,
            @RequestParam("location") String location,
            @RequestParam("duration") String duration) throws Exception {

        logger.info("CommonLeadMasterController :: insertleadbyivr :: clientPhoneNumber {}", clientPhoneNumber);
        logger.info("CommonLeadMasterController :: insertleadbyivr :: clientType {}", clientType);
        logger.info("CommonLeadMasterController :: insertleadbyivr :: product {}", product);
        logger.info("CommonLeadMasterController :: insertleadbyivr :: source {}", source);
        logger.info("CommonLeadMasterController :: insertleadbyivr :: accessToken {}", accessToken);
        logger.info("CommonLeadMasterController :: insertleadbyivr :: call_type {}", call_type);
        logger.info("CommonLeadMasterController :: insertleadbyivr :: call_status {}", call_status);
        logger.info("CommonLeadMasterController :: insertleadbyivr :: agentNo {}", agentNo);
        logger.info("CommonLeadMasterController :: insertleadbyivr :: recordingURL {}", recordingURL);
        logger.info("CommonLeadMasterController :: insertleadbyivr :: recordFileName {}", recordFileName);
        logger.info("CommonLeadMasterController :: insertleadbyivr :: callStartTime {}", callStartTime);
        logger.info("CommonLeadMasterController :: insertleadbyivr :: callEndTime {}", callEndTime);
        logger.info("CommonLeadMasterController :: insertleadbyivr :: location {}", location);
        logger.info("CommonLeadMasterController :: insertleadbyivr :: duration {}", duration);


        logger.info("New Lead From Source {}", source);
        LeadSourceDao leadSourceDao = leadSourceService.findLeadByLeadSource(source);
        if (leadSourceDao == null) {
            throw new Exception("Lead Source Cannot be found!!");
        }

        if (leadSourceDao.getStatus() == 0) {
            throw new Exception("This Lead Source Is Not Enabled");
        }

        if (!accessToken.equalsIgnoreCase(leadSourceDao.getAccessToken())) {
            throw new Exception("Invalid Token!!");
        }
        if (clientPhoneNumber.trim().startsWith("91")) {
            //clientPhoneNumber = StringUtils.right(clientPhoneNumber,10);
            String[] split = clientPhoneNumber.trim().split("91");
            clientPhoneNumber = split[1];
            System.out.println("number in the function" + clientPhoneNumber);
        }
        String token = UtilityClass.propertyService.findProperty("Application", "token");
        logger.info("\"CommonLeadMasterController :: insertleadbyivr :: token {}", token);
        String playablerecordingurl = recordingUrlService.fetchRecrodingUrl(token, recordFileName);
        Boolean CheckLeadStatus = false;
        //System.out.println("yeh hai phone number before checking"+ clientPhoneNumber);
        boolean existsByPhonenumber = leadMasterRepo.existsByPhonenumber(clientPhoneNumber);
        //System.out.println("yeh hai bollean of phone number "+existsByPhonenumber);
        LeadMasterDao leadMasterDao = leadMasterService.findByPhonenumber(clientPhoneNumber);
        if (existsByPhonenumber) {

//            leadMasterDao.setUpdateDate(UtilityClass.getDateRedable());
//            leadMasterDao.setUpdatedatefilter(UtilityClass.dateFilterDate());
//            leadMasterService.update(leadMasterDao);
//            logger.info("Update Existing IVR Lead {}", leadMasterDao);
            leadMasterDao.setCallStatus(call_status);
            //Change By Rahul on 19-02-2022
            leadMasterDao.setLeadSource(source);
            leadMasterDao.setClientType(clientType);
            System.out.println("Ye raha leadmasterdao ka client type object" + leadMasterDao.getClientType());
            leadMasterService.update(leadMasterDao);
            //-------------------------------------
            logger.info("Update Existing IVR Lead");

        } else {
            int userId = findUserByType(leadSourceDao.getAssignBy(), source, product, clientType);
            System.out.println("else part user id " + userId);
            leadMasterDao = new LeadMasterDao();
            leadMasterDao.setCreateBy("system");
            leadMasterDao.setLastForward(userId);
            leadMasterDao.setUpdateBy(leadMasterDao.getCreateBy());
            leadMasterDao.setPhonenumber(clientPhoneNumber);
            leadMasterDao.setLeadSource(source);
            leadMasterDao.setCreateDate(UtilityClass.getDateRedable());
            leadMasterDao.setUpdateDate(UtilityClass.getDateRedable());
            leadMasterDao.setDateFilter(UtilityClass.dateFilterDate());
            leadMasterDao.setUpdatedatefilter(UtilityClass.dateFilterDate());
            leadMasterDao.setLeadDatefilter(UtilityClass.dateFilterDate());
            leadMasterDao.setLeadStage(leadSourceDao.getInitialStage());
            leadMasterDao.setLeadStatus(leadSourceDao.getInitialStatus());
            leadMasterDao.setCallStatus(call_status);
            leadMasterDao.setLeadsourceinner("Application");
            leadMasterDao.setClientType(clientType);
            leadMasterService.insert(leadMasterDao);
            logger.info("\"CommonLeadMasterController :: insertleadbyivr :: Insert new IVR Lead {}", leadMasterDao);

            NotificationDao notificationDao = new NotificationDao();
            notificationDao.setCreateBy("System");
            notificationDao.setDatetiming(UtilityClass.fullDateLong());
            notificationDao.setCreateAt(UtilityClass.getDateRedable());
            notificationDao.setTo(userId);
            notificationDao.setSubject("New Lead From " + source);
            notificationDao.setSendMessage(UtilityClass.propertyService.findProperty("Lead", "Send_Text_Message_On_Lead").equalsIgnoreCase("1"));
            notificationDao.setInnerSubject("NOTIFY");
            notificationDao.setRedirectUrl("lead?id=" + leadMasterDao.getId());
            notificationDao.setLeadId(leadMasterDao.getId());
            notificationDao.setNotificationTiming(UtilityClass.getDateMysql());
            notificationDao.setMessage("New Lead From " + source);
            notificationService.insertSingleNotification(notificationDao);
            CheckLeadStatus = true;

        }
        int userId = findUserByType(leadSourceDao.getAssignBy(), source, product, clientType);
        logger.info("\"CommonLeadMasterController :: insertleadbyivr :: User ID {}", userId);

        LogEventDao logEventDao = new LogEventDao();

        logEventDao.setPhonenumber(clientPhoneNumber);
        logEventDao.setLocation(location);
        logEventDao.setDuration(duration);
        logEventDao.setAgentNo(agentNo);
        logEventDao.setUserId(userId);
        logEventDao.setCall_type(call_type);
        logEventDao.setCall_status(call_status);
        logEventDao.setRecordingUrl(playablerecordingurl);
        logEventDao.setRecordFileName(recordFileName);
        logEventDao.setEventType(source);
        logEventDao.setCallEndTime(UtilityClass.epepochToHumanReadableDate(callEndTime));
        logEventDao.setCallStartTime(UtilityClass.epepochToHumanReadableDate(callStartTime));
        logEventDao.setCall_type(call_type);
        logEventDao.setCreateAt(UtilityClass.getDateRedable());
        logEventDao.setUpdateAt(UtilityClass.getDateRedable());
        logEventDao.setLeadId(leadMasterDao.getId());

        if (call_status.trim().equalsIgnoreCase("connected")) {
            logEventDao.setMessage("Call is connected");
        } else {
            logEventDao.setMessage("Call is not connected");
        }
        logger.info("CommonLeadMasterController :: insertleadbyivr :: Going to Inserting LogEventDao {}", logEventDao);

        LogEventDao insertedLogEventDao = logEventService.insert(logEventDao);
        logger.info("CommonLeadMasterController :: insertleadbyivr :: getting response after inserting LogEventDao {}", insertedLogEventDao);

        ResponseDao res = new ResponseDao();
        res.setStatusCode(200);
        if (CheckLeadStatus == true) {
            res.setMsg("Lead inserted");
        } else {
            res.setMsg("Lead Updated");
        }
        res.setId(leadMasterDao.getId());
        return res;
    }

    @PostMapping("/leadInsertByWati")
    ResponseDao leadInsertByWati(@RequestBody WatiLeadMasterDao watiLeadMasterDao,
                                 @RequestParam("source") String source,
                                 @RequestParam("accessToken") String accessToken,
                                 @RequestParam("product") String product,
                                 @RequestParam("clientType") String clientType) throws Exception {

        logger.info("CommonLeadMasterController :: leadInsertByWati :: watiLeadMasterDao {}", watiLeadMasterDao);
        logger.info("New Lead From Source {}", source);
        logger.info("New Lead From accessToken {}", accessToken);
        logger.info("New Lead From product {}", product);
        logger.info("New Lead From clientType {}", clientType);

        LeadSourceDao leadSourceDao = leadSourceService.findLeadByLeadSource(source);
        if (leadSourceDao == null) {
            throw new Exception("Lead Source Cannot be found!!");
        }

        if (leadSourceDao.getStatus() == 0) {
            throw new Exception("This Lead Source Is Not Enabled");
        }

        if (!accessToken.equalsIgnoreCase(leadSourceDao.getAccessToken())) {
            throw new Exception("Invalid Token!!");
        }

        if (watiLeadMasterDao.getWaId().startsWith("+91")) {
            watiLeadMasterDao.setWaId(formatPhonenumber(watiLeadMasterDao.getWaId()));
        }
        Boolean CheckLeadStatus = false;
        LeadMasterDao leadMasterDao = leadMasterService.findByPhonenumber(watiLeadMasterDao.getWaId());
        if (leadMasterDao != null) {

            leadMasterDao.setUpdateDate(UtilityClass.getDateRedable());
            leadMasterDao.setUpdatedatefilter(UtilityClass.dateFilterDate());
            //Change By Rahul on 19-02-2022
            leadMasterDao.setLeadSource(source);
            leadMasterDao.setClientType(clientType);
            //----------------------------------
            leadMasterService.update(leadMasterDao);
            logger.info("Update Existing IVR Lead {}", leadMasterDao);

        } else {
            int userId = findUserByType(leadSourceDao.getAssignBy(), source, product, clientType);
            leadMasterDao = new LeadMasterDao();
            leadMasterDao.setCreateBy("system");
            leadMasterDao.setLastForward(userId);
            leadMasterDao.setUpdateBy(leadMasterDao.getCreateBy());
            leadMasterDao.setPhonenumber(watiLeadMasterDao.getWaId());
            leadMasterDao.setLeadSource(source);
            leadMasterDao.setCreateDate(UtilityClass.getDateRedable());
            leadMasterDao.setUpdateDate(UtilityClass.getDateRedable());
            leadMasterDao.setDateFilter(UtilityClass.dateFilterDate());
            leadMasterDao.setUpdatedatefilter(UtilityClass.dateFilterDate());
            //  leadMasterDao.setLeadDatefilter(UtilityClass.leadDatefilter(leadMasterDao.getLeadDate()));
            leadMasterDao.setLeadStage(leadSourceDao.getInitialStage());
            leadMasterDao.setLeadStatus(leadSourceDao.getInitialStatus());
            leadMasterDao.setLeadsourceinner("Application");
            leadMasterService.insert(leadMasterDao);
            logger.info("CommonLeadMasterController :: leadInsertByWati :: Insert new IVR Lead {}", leadMasterDao);

            NotificationDao notificationDao = new NotificationDao();
            notificationDao.setCreateBy("System");
            notificationDao.setDatetiming(UtilityClass.fullDateLong());
            notificationDao.setCreateAt(UtilityClass.getDateRedable());
            notificationDao.setTo(userId);
            notificationDao.setSubject("New Lead From " + source);
            notificationDao.setSendMessage(UtilityClass.propertyService.findProperty("Lead", "Send_Text_Message_On_Lead").equalsIgnoreCase("1"));
            notificationDao.setInnerSubject("NOTIFY");
            notificationDao.setRedirectUrl("lead?id=" + leadMasterDao.getId());
            notificationDao.setLeadId(leadMasterDao.getId());
            notificationDao.setNotificationTiming(UtilityClass.getDateMysql());
            notificationDao.setMessage("New Lead From " + source);
            notificationService.insertSingleNotification(notificationDao);
            CheckLeadStatus = true;

        }

        ResponseDao res = new ResponseDao();
        res.setStatusCode(200);
        if (CheckLeadStatus == true) {
            res.setMsg("Lead inserted");
        } else {
            res.setMsg("Lead Updated");
        }
        res.setId(leadMasterDao.getId());
        return res;
    }

    //API  FOR JUST_DIAL
    @GetMapping("/insertleabyjustdial")
      public ResponseEntity<Object> insertLead(
            @RequestParam String leadid,
            @RequestParam(required = false, defaultValue = "NA") String leadtype,
            @RequestParam(required = false, defaultValue = "Mr") String prefix,
            @RequestParam(required = false, defaultValue = "NA")  String name,
            @RequestParam String mobile,
            @RequestParam(required = false,defaultValue = "NA") String phone,
            @RequestParam(required = false, defaultValue = "NA") String email,
            @RequestParam String date,
            @RequestParam(required = false, defaultValue = "NA") String category,
            @RequestParam(required = false, defaultValue = "NA") String area,
            @RequestParam String city,
            @RequestParam(required = false, defaultValue = "NA") String brancharea,
            @RequestParam(required = false,defaultValue = "0") int dncmobile,
            @RequestParam(required = false,defaultValue = "0") int dncphone,
            @RequestParam String company,
            @RequestParam   String pincode,
            @RequestParam(required = false, defaultValue = "NA") String time,
            @RequestParam(required = false, defaultValue = "NA") String branchpin,
            @RequestParam (required = false,defaultValue = "NA") String parentid) throws Exception {
        logger.info("Getting values from JustDial source  leadid {} leadtype {} prefix {} name {} mobile {} phone {} email {} date {} category {} area {} city {} brancharea {}  dncmobile {} dncphone {} company {} pincode {} time {} branchpin {} parentid {}",
                leadid, leadtype, prefix, name, mobile, phone, email, date, category, area, city, brancharea, dncmobile, dncphone, company, pincode, time, branchpin, parentid);

        String leadSource = "Justdial";
        StringBuilder sb = new StringBuilder();
        int userId = 0;
        LeadSourceDao leadSourceDao = UtilityClass.alleadsourcedao.get(leadSource);
        logger.info("leadSourceDao {}",leadSourceDao);
        userId = leadSourceService.findRandomUserId(leadSourceDao.getId());
        LeadMasterDao leadMasterModel = new LeadMasterDao();
        if (userId == 0) {
            throw new Exception("No User Found In Lead Source!");
        }
        leadMasterModel.setLastForward(userId);
        leadMasterModel.setCreateBy("System");
        leadMasterModel.setUpdateBy("System");


        leadMasterModel.setLeadStatus(leadSourceDao.getInitialStatus());
        leadMasterModel.setLeadStage(leadSourceDao.getInitialStage());
        leadMasterModel.setClientType(leadSourceDao.getInitialType());
        leadMasterModel.setPhonenumber(mobile + " " + phone);
        leadMasterModel.setSalutation(prefix);
        leadMasterModel.setUpdatedatefilter(UtilityClass.dateFilterDate());
        leadMasterModel.setFirstName(name);
        leadMasterModel.setCompany(company);
        leadMasterModel.setEmail(email);
        leadMasterModel.setPhonenumber(mobile + " " + phone);
        leadMasterModel.setDateFilter(UtilityClass.dateFilterDate());
        leadMasterModel.setCreateDate(UtilityClass.getDateRedable());
        leadMasterModel.setUpdateDate(UtilityClass.getDateRedable());
        leadMasterModel.setLeadSource(leadSource);
        leadMasterModel.setLeadsourceinner("API");
        leadMasterModel.setCity(city);
        leadMasterModel.setPincode(pincode);
        leadMasterModel.setInterestedProduct(category);
        sb.append("lead Id={");
        sb.append("leadid");
        sb.append("}");
        sb.append("Lead Type={");
        sb.append(leadtype);
        sb.append("}");
        sb.append("Category={");
        sb.append(category);
        sb.append("}");
        sb.append("city={");
        sb.append(city);
        sb.append("}");
        sb.append("Area={");
        sb.append(area);
        sb.append("}");
        sb.append("BranchArea={");
        sb.append(brancharea);
        sb.append("}");
        sb.append("Pincode={");
        sb.append(pincode);
        sb.append("}");
        sb.append("Time={");
        sb.append(time);
        sb.append("}");
        sb.append("dncmobile={");
        sb.append(dncmobile);
        sb.append("}");
        sb.append("dncphone={");
        sb.append(dncphone);
        sb.append("}");
        sb.append("branchpin={");
        sb.append(branchpin);
        sb.append("}");
        sb.append("parentid={");
        sb.append(parentid);
        sb.append("}");


        leadMasterModel.setDescrip(sb.toString());
        logger.info("After Validation Api Request {}", leadMasterModel);
        if (leadMasterModel.getInterestedProduct().length() == 0) {
            leadMasterModel.setInterestedProduct("NA");
        }
        leadMasterModel = leadMasterService.insert(leadMasterModel);
        logger.info("Success inserted lead {}", leadMasterModel);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
    }

