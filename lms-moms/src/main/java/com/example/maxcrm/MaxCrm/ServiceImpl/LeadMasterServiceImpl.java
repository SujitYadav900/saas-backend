package com.example.maxcrm.MaxCrm.ServiceImpl;

import com.example.maxcrm.MaxCrm.CombinePackage.CentreAppointmentLogs.CentreAppointmentLogs;
import com.example.maxcrm.MaxCrm.CombinePackage.CentreAppointmentLogs.CentreAppointmentLogsService;
import com.example.maxcrm.MaxCrm.CombinePackage.ServiceReview.ServiceReviewDao;
import com.example.maxcrm.MaxCrm.CombinePackage.Wati.WatiDaos.CustomParamDao;
import com.example.maxcrm.MaxCrm.CombinePackage.Wati.WatiDaos.WatiReceiverDao;
import com.example.maxcrm.MaxCrm.CombinePackage.Wati.WatiDaos.WatiTemplateMsgRequestDao;
import com.example.maxcrm.MaxCrm.CombinePackage.Wati.WatiService;
import com.example.maxcrm.MaxCrm.Dao.*;
import com.example.maxcrm.MaxCrm.OkHttp.OkHttpSingleTon;
import com.example.maxcrm.MaxCrm.Repo.LeadMasterRepo;
import com.example.maxcrm.MaxCrm.Service.*;
import com.example.maxcrm.MaxCrm.Utility.UtilityClass;
import com.google.gson.Gson;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.apache.ibatis.jdbc.SQL;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

@Service
public class LeadMasterServiceImpl extends PaginationClass implements LeadMasterService {




    Logger logger = LoggerFactory.getLogger(LeadMasterService.class);
    @Autowired
    LeadMasterRepo leadMasterRepo;
    @Autowired
    LeadStatusTransferService leadStatusTransferService;
    @Autowired
    LogEventService logEventService;
    @Autowired
    DataSource dataSource;
    @Autowired
    CounterService counterService;
    @Autowired
    NotificationService notificationService;
    @Autowired
    LeadSourceService leadSourceService;
    @Autowired
    TemplateDocumentService templateDocumentService;
    @Autowired
    TextMessageService textMessageService;
    @Autowired
    LeadStageService leadStageService;
    @Autowired
    MailObjectService mailObjectService;
    @Autowired
    WhatsappService whatsappService;
    @Autowired
    private CentreAppointmentLogsService appointmentLogsService;
    @Autowired
    private UrlShortnerService urlShortnerService;
    @Autowired
    private WatiService watiService;
    @Autowired
    private UserMasterService userMasterService;

    private Map<String,String> isoToCodeMap ;

    @PostConstruct
    private void onInit(){
        isoToCodeMap = new HashMap<>();
        isoToCodeMap.put( "0","0");
        isoToCodeMap.put( "us","1");
        isoToCodeMap.put( "in","2");
        isoToCodeMap.put("au","3");
        isoToCodeMap.put( "gb","4");
        isoToCodeMap.put( "cn","5");
        isoToCodeMap.put( "ae","6");
        isoToCodeMap.put( "ca","7");
        isoToCodeMap.put( "sa","8");
        isoToCodeMap.put( "kw","9");
        isoToCodeMap.put( "bd","10");
        isoToCodeMap.put( "qa","11");
        isoToCodeMap.put( "om","12");
        isoToCodeMap.put( "se","13");
        isoToCodeMap.put( "bh","14");
        isoToCodeMap.put( "cz","15");
        isoToCodeMap.put( "np","16");
        isoToCodeMap.put("de","17");
    }

    private String getPhonenumber(String phonenumber) {
//        phonenumber = phonenumber.replaceAll("\\+","");
        String[] ar = phonenumber.split("-");
        if (ar.length > 1) {
            return phonenumber;
        } else {
            return "91-" + phonenumber;
        }

    }

    private String formatPhonenumber(LeadMasterDao leadMasterDao) {

        if(leadMasterDao.getClientType().contains("International") || leadMasterDao.getClientType().contains("international")){
            logger.info("Formatting Phone number as per Internation Requirement");

            String rawphone_number = leadMasterDao.getPhonenumber();
            rawphone_number = rawphone_number.replaceAll("[^0-9]", "");
            return rawphone_number;
        }else{
            logger.info("Formatting Phone number as per local Requirement");
            String rawphone_number = leadMasterDao.getPhonenumber();
            rawphone_number = rawphone_number.replaceAll("[^0-9]", "");
            int len = rawphone_number.length();
            if(len > 10){
                leadMasterDao.setPhonenumber(rawphone_number.substring(len-10,len));
                return leadMasterDao.getPhonenumber();
            }else {
                leadMasterDao.setPhonenumber(rawphone_number);
                return leadMasterDao.getPhonenumber();
            }
        }

    }

    private void pushNotification(LeadMasterDao leadMasterDao, LeadSourceDao leadSourceDao) throws Exception {
        String senderId = UtilityClass.propertyService.findProperty("Lead", "LeadMessagingSenderId");


        if (leadSourceDao.getSendnotification() == 1) {

              singleNotification(leadSourceDao.getTemplate(),leadMasterDao);
        }
        if (leadSourceDao.getSendnotificationothers() == 1) {

            TemplateDocument templateDocument = templateDocumentService.findById(leadSourceDao.getTemplateothers());
            logger.info("TemplateDocument >>> {}",templateDocument);
            templateDocument.setTemplate(convertString(templateDocument.getTemplate(), leadMasterDao));
            logger.info("TemplateDocument >>> {}",templateDocument);
            logger.info("TemplateDocument.getParams >>> {}",templateDocument.getParams());
            String[] phonenumber = leadSourceDao.getPhonenumbers().split(",");
            MessageCampaignDao messageCampaignDao = new MessageCampaignDao(templateDocument.getParams(), leadMasterDao.convertToObjectMail(), templateDocument.getTemplateIdHidden(), phonenumber);
            logger.info("messageCampaignDao >>> {}",messageCampaignDao);
            TextMessage textMessage = messageCampaignDao.convertToObject(senderId);
            textMessage.setSender(senderId);
            logger.info("Sending Text Message to Agent >> {}",textMessage);
            textMessageService.sendTextMessage(textMessage);
        }
    }



    @Override
    public LeadMasterDao findByPhonenumber(String phonenumber) {
        return leadMasterRepo.findByPhonenumber(phonenumber);

            }

    @Override
    public LeadMasterDao findByMBOPSChildId(String mbopsChildId) {
        return leadMasterRepo.findByMbopsChildId(mbopsChildId);
    }

    @Override
    public LeadMasterDao insert(LeadMasterDao leadMasterDao) throws Exception {

        leadMasterDao.setPhonenumber(formatPhonenumber(leadMasterDao));
        long id = 0;
        try {
            id = counterService.getCounter(2, 1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        LeadSourceDao leadSourceDao = leadSourceService.findLeadByLeadSource(leadMasterDao.getLeadSource());
        System.out.println("leadSourceDao ka object "+leadSourceDao);
        leadMasterDao.setLeadStage(leadSourceDao.getInitialStage());
        leadMasterDao.setLeadStatus(leadSourceDao.getInitialStatus());

        //leadMasterDao.setPhonenumber(formatPhonenumber(leadMasterDao.getPhonenumber()));
        leadMasterDao.setId(id);
        leadMasterDao.setLastQueryDateFilter(UtilityClass.dateFilterDate());
        leadMasterDao.setLastQueryDate(UtilityClass.getDateRedable());
        logger.info("Inserting {}", leadMasterDao);
        leadMasterDao = leadMasterRepo.save(leadMasterDao);
        try {
            pushNotification(leadMasterDao, leadSourceDao);
        } catch (Exception e) {
            logger.error("Error Occured!! {}", e);
            String message = handleException(e.getMessage());
            logger.error("Error Occured!! {}", message);
            throw new Exception(message);

        }
        return leadMasterDao;
    }

    private static String handleException(String message) {

        String retmsg = null;

        if (message.contains("[unqphonenumber]")) {
            retmsg = "Lead Already Exists!!";

        } else {
            retmsg = "Lead Cannot Be Created!!Please Try Again Later";
        }
        return retmsg;
    }

    public void initialLead(LeadMasterDao leadMasterDao) {

    }

    @Override
    public LeadMasterDao update(LeadMasterDao leadMasterDao, MaskingDao maskingDao) throws SQLException {
        logger.info("Updating {}", leadMasterDao);
        Connection con = null;

        try {

                LeadMasterDao leadMasterDaoTemp = findById(leadMasterDao.getId());
                if (maskingDao.isNumberMasking()) {
                    leadMasterDao.setPhonenumber(leadMasterDaoTemp.getPhonenumber());
                }
                if (maskingDao.isEmailMasking()) {
                    leadMasterDao.setEmail(leadMasterDaoTemp.getEmail());
                }

            con = dataSource.getConnection();

            PreparedStatement stmt = con.prepareStatement("UPDATE `Tbl_LeadMaster` SET `address` = ?, `city` = ?, `clientType` = ?, `company` = ?, `country` = ?, `descrip` = ?, `lastName` = ?, `firstName` = ?, `interestedProduct` = ?, `lastName` = ?, `leadSource` = ?, `lastName` = ?, `pincode` = ?, `prospectiveBuissness` = ?, `salutation` = ?, `state` =?, `updateBy` = ?, `updateDate` = ?,updatedatefilter=?,urnNumber=?, `parentName` = ?, `childName` = ?, `dob` = ?, `childDevDelay` = ?, `professionFilled` = ?, `childPlayPattern` = ?, `typeSchool` = ?, `pageName` = ?, `seekingSupport` = ?, `supportFor` = ?, `interventionAreas` = ?, `formalAssessment` = ?, `ongoingTherapy` = ?, `developmentTime` = ?, `seizureHistory` = ?, `callReqCstTeam` = ?, `personAssigned` = ?, `assignmentDate` = ?, `reviewResult` = ?, `approved` = ?, `typeTherapy` = ?, `callComments` = ?, `cpName` = ?, `registeredInMb` = ?, `registrationDate` = ? , `gender` = ?,phonenumber=?,email=?, `relation`  =  ?, `childLanguage`  =  ?, `parentLanguage`  =  ?, `learningConcern`  =  ?, `difficultyInFriendship`  =  ?, `payment`  =  ?, `clinicalCallTime`  =  ?, `pgBdName`  =  ?, `pgBdManager`  =  ?, `supportOption` = ?, `leadDate` = ?, `leadDateFilter` = ?, `leadScore` = ?, `assessmentNotes` = ?, `countryId` = ? , `stateId` = ? , `doesChildGoToSchool` = ? , `adFormData` = ?, `preferredCallingTime` = ? , `preferredCallingSlot`=?,`paymentStatus` = ?,`callStatus` = ? WHERE `id` = ?;");
            stmt.setString(1, leadMasterDao.getAddress());
            stmt.setString(2, leadMasterDao.getCity());
            stmt.setString(3, leadMasterDao.getClientType());
            stmt.setString(4, leadMasterDao.getCompany());
            stmt.setString(5, leadMasterDao.getCountry());
            stmt.setString(6, leadMasterDao.getDescrip());
            stmt.setString(7, leadMasterDao.getLastName());
            stmt.setString(8, leadMasterDao.getFirstName());
            stmt.setString(9, leadMasterDao.getInterestedProduct());
            stmt.setString(10, leadMasterDao.getLastName());
            stmt.setString(11, leadMasterDao.getLeadSource());
            stmt.setString(12, leadMasterDao.getLastName());
            stmt.setString(13, leadMasterDao.getPincode());
            stmt.setDouble(14, leadMasterDao.getProspectiveBuissness());
            stmt.setString(15, leadMasterDao.getSalutation());
            stmt.setString(16, leadMasterDao.getState());
            stmt.setString(17, leadMasterDao.getUpdateBy());
            stmt.setString(18, leadMasterDao.getUpdateDate());
            stmt.setInt(19, leadMasterDao.getUpdatedatefilter());
            stmt.setString(20, leadMasterDao.getUrnNumber());

            stmt.setString(21, leadMasterDao.getParentName());
            stmt.setString(22, leadMasterDao.getChildName());
            stmt.setString(23, leadMasterDao.getDob());
            stmt.setString(24, leadMasterDao.getChildDevDelay());
            stmt.setString(25, leadMasterDao.getProfessionFilled());//ENABLER
            stmt.setString(26, leadMasterDao.getChildPlayPattern());//BPO
            stmt.setString(27, leadMasterDao.getTypeSchool());//ALTERNATE PHHONE NUMBER
            stmt.setString(28, leadMasterDao.getPageName());
            stmt.setString(29, leadMasterDao.getSeekingSupport());
            stmt.setString(30, leadMasterDao.getSupportFor());
            stmt.setString(31, leadMasterDao.getInterventionAreas());
            stmt.setString(32, leadMasterDao.getFormalAssessment());
            stmt.setString(33, leadMasterDao.getOngoingTherapy());
            stmt.setString(34, leadMasterDao.getDevelopmentTime());
            stmt.setString(35, leadMasterDao.getSeizureHistory());
            stmt.setString(36, leadMasterDao.getCallReqCstTeam());
            stmt.setString(37, leadMasterDao.getPersonAssigned());
            stmt.setString(38, leadMasterDao.getAssignmentDate());
            stmt.setString(39, leadMasterDao.getReviewResult());
            stmt.setString(40, leadMasterDao.getApproved());
            stmt.setString(41, leadMasterDao.getTypeTherapy());
            stmt.setString(42, leadMasterDao.getCallComments());
            stmt.setString(43, leadMasterDao.getCpName());
            stmt.setString(44, leadMasterDao.getRegisteredInMb());
            stmt.setString(45, leadMasterDao.getRegistrationDate());
            stmt.setString(46, leadMasterDao.getGender());
            stmt.setString(47, formatPhonenumber(leadMasterDao));
            stmt.setString(48, leadMasterDao.getEmail());

            stmt.setString(49, leadMasterDao.getRelation());
            stmt.setString(50, leadMasterDao.getChildLanguage());
            stmt.setString(51, leadMasterDao.getParentLanguage());
            stmt.setString(52, leadMasterDao.getLearningConcern());
            stmt.setString(53, leadMasterDao.getDifficultyInFriendship());
            stmt.setInt(54, leadMasterDao.getPayment());
            stmt.setString(55, leadMasterDao.getClinicalCallTime());
            stmt.setString(56, leadMasterDao.getPgBdName());
            stmt.setString(57, leadMasterDao.getPgBdManager());
            stmt.setString(58, leadMasterDao.getSupportOption());
            stmt.setString(59, leadMasterDao.getLeadDate());
            stmt.setInt(60, UtilityClass.leadDatefilter(leadMasterDao.getLeadDate()));
            stmt.setInt(61,leadMasterDao.getLeadScore());

            stmt.setString(62,leadMasterDao.getAssessmentNotes());

            stmt.setLong(63, leadMasterDao.getCountryId());
            stmt.setLong(64, leadMasterDao.getStateId());
            stmt.setString(65, leadMasterDao.getDoesChildGoToSchool());
            stmt.setString(66,leadMasterDao.getAdFormData());

            stmt.setString(67,leadMasterDao.getPreferredCallingTime());
            stmt.setString(68,leadMasterDao.getPreferredCallingSlot());
            stmt.setBoolean(69,leadMasterDao.isPaymentStatus());
            stmt.setString(70,leadMasterDao.getCallStatus());
            stmt.setLong(71, leadMasterDao.getId());

            logger.info("Update Query >>> {}",stmt);
            stmt.executeUpdate();


        } catch (SQLException sql) {
            logger.info("Exception {}"+sql.getMessage());
            throw sql;

        } finally {
            con.close();
        }

     /*   LeadStatusTransferDao leadStatusTransferDao = new LeadStatusTransferDao();
        leadStatusTransferDao.setCreateBy(leadMasterDao.getUpdateBy());
        leadStatusTransferDao.setLeadId(leadMasterDao.getId());
        leadStatusTransferDao.setStatus(leadMasterDao.getLeadStatus());
        leadStatusTransferDao.setReadableDate(UtilityClass.getDateRedable());
        leadStatusTransferDao.setTiming(UtilityClass.fullDateLong());
        try {
            leadStatusTransferService.insert(leadStatusTransferDao);
            logger.info("Inserting Into Lead Status Tranfer {}", leadStatusTransferDao);
        } catch (SQLException e) {
            e.printStackTrace();
        }*/
        return leadMasterDao;

    }

    @Override
    public LeadMasterDao update(LeadMasterDao leadMasterDao) {
        logger.info("Updating LeadMasterDao :: {}",leadMasterDao);
        return leadMasterRepo.save(leadMasterDao);
    }

    @Override
    public void sendBatchNotification(HashMap<Integer, Integer> perUserNotification,UserMasterDao userMasterDao,Connection con) throws SQLException {
        logger.info("Running sendBatchNotification");
        int date = UtilityClass.dateFilterDate();
        String textMessageOnNewLead = UtilityClass.propertyService.findProperty("Lead", "Send_Text_Message_On_Lead");
        logger.info("textMessageOnNewLead ? >> {}",textMessageOnNewLead);
        List<NotificationDao> alnotification = new ArrayList<>();
        NotificationDao notificationDao;
        long notificationCounter = counterService.getCounter(1, perUserNotification.size() + 1);
        String createDate = UtilityClass.getDateRedable();
        int dateFilter = UtilityClass.dateFilterDate();
        String datefilterdate = date + "@" + date;
        long fulldate = UtilityClass.fullDateLong();

        logger.info("Assign Hashmap is {}", perUserNotification);
        for (int userId : perUserNotification.keySet()) {
            int count = perUserNotification.get(userId);
            notificationDao = new NotificationDao();
            notificationDao.setId(notificationCounter);
            notificationDao.setTo(userId);
            notificationDao.setCreateAt(createDate);
            notificationDao.setRedirectUrl("lead?datefiltertype=createdate&innersource=Bulk Upload&statusValue=0&leadSource=0&product=0&leadType=0&userFilter=" + userId + "&datefilter=true&datevalue=" + datefilterdate + "&searchvalue=0&leadstage=0&leadpriority=0");
            notificationDao.setInnerSubject("NOTIFY");
            notificationDao.setNotificationTiming(UtilityClass.getDateMysql());
            notificationDao.setMessage("You have " + count + " new Leads");
            notificationDao.setLeadId(121);
            notificationDao.setSubject("New Lead");
            notificationDao.setCreateBy(userMasterDao.getUsername());
            notificationDao.setSendMessage(textMessageOnNewLead.equalsIgnoreCase("1"));
            notificationDao.setDatetiming(fulldate);
            notificationDao.setAssignTo(userId);
            notificationDao.setDatetimingnotification(fulldate);
            alnotification.add(notificationDao);
            notificationCounter++;

        }


        notificationService.bulkInsertNotification(alnotification, con);
    }

    @Override
    public void updateC2cAttempts(long leadId) {
        logger.info("Updating C2C attempt for leadId {}", leadId);
        Connection con = null;

        try {
            con = dataSource.getConnection();
            PreparedStatement stmt = con.prepareStatement("UPDATE `Tbl_LeadMaster` SET c2cAttempts=c2cAttempts+1 WHERE `id` = ?;");
            stmt.setLong(1,leadId);
            logger.info("Update C2C attempts Query >>> {}",stmt);
            stmt.executeUpdate();
        } catch (SQLException sql) {
            sql.printStackTrace();

        } finally {
            try{
                con.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }

    }

    @Override
    public void setMBOPSParentId(long leadId,String parentId) {
        Connection con = null;
        try{
            con = dataSource.getConnection();
            PreparedStatement stmt = con.prepareStatement("UPDATE Tbl_LeadMaster set mbopsParentId=? where id=?");
            stmt.setString(1,parentId);
            stmt.setLong(2,leadId);

            logger.info("Setting MBOPS ParentID > {}",stmt);
            stmt.executeUpdate();

        }catch(SQLException e){
            e.printStackTrace();
        }finally {
            try {
                con.close();
            }catch (Exception ex){
                ex.printStackTrace();
            }
        }
    }

    @Override
    public void setMBOPSChildId(long leadId,String childId,String flag) {
        Connection con = null;
        try{
            con = dataSource.getConnection();

            PreparedStatement stmt = null;
            if(flag.equalsIgnoreCase("Register1")){
                stmt = con.prepareStatement("UPDATE Tbl_LeadMaster set mbopsChildId=? where id=?");
            }else if(flag.equalsIgnoreCase("Register")){
                stmt = con.prepareStatement("UPDATE Tbl_LeadMaster set mbopsChildEnrollId=? where id=?");
            }

            stmt.setString(1,childId);
            stmt.setLong(2,leadId);

            logger.info("Setting MBOPS ChildID > {}",stmt);
            stmt.executeUpdate();

        }catch(SQLException e){
            e.printStackTrace();
        }finally {
            try {
                con.close();
            }catch (Exception ex){
                ex.printStackTrace();
            }
        }
    }

    @Override
    public String transferleadtocrm(String url,long leadid,String source, String token) throws IOException{
        logger.info("Transferring lead to Mind Belief");
        logger.info("url >> {}",url);
        logger.info("source >> {}",source);
        logger.info("url >> {}",token);
        LeadMasterDao leadMasterDao = findById(leadid);
        List<LogEventDao> logEventDaos = (List<LogEventDao>) logEventService.findAllByLeadId(leadid);
       leadMasterDao.setLogEvents(logEventDaos);
        MediaType mediaType = MediaType.parse("application/json; charset=utf-8");
        String json = new Gson().toJson(leadMasterDao);
        logger.info("BODY >> {}",json);
        RequestBody body = RequestBody.create(mediaType,json);
        String lastForwardUsername=userMasterService.findById(leadMasterDao.getLastForward()).getUsername();

        Request request = new Request.Builder()
                .url(url+"?source="+source+"&token="+token+"&test=false&leadowner="+lastForwardUsername)
                .post(body)
                .build();
        Response response = OkHttpSingleTon.client.newCall(request).execute();
        String res = response.body().string();
        logger.info("Response >> {}",res);
        return res;

    }

    @Override
    public int getAppointmentSlotCount(String timeSlot) throws SQLException {
        int count = 0;
        Connection con = null;
        try{
            con = dataSource.getConnection();
            PreparedStatement stmt = con.prepareStatement("select count(id) from Tbl_LeadMaster where clinicalCallTime=?");
            stmt.setString(1,timeSlot);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                count = rs.getInt(1);
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }finally {
            con.close();
        }
        return count;
    }

    @Override
    public int updateLeadStageAndStatusByChildId(String mbopsChildId,String leadStage, String leadStatus,String assessmnetNotes,boolean hasAssessmentNotes) {
        logger.info("Updating leadStage and leadStatus of lead with ID > {}",mbopsChildId);

        int updated = 0;
        Connection con =  null;
        try{

            String createDate = UtilityClass.getDateRedable();
            int createDateFilter = UtilityClass.dateFilterDate();
            con = dataSource.getConnection();
            PreparedStatement stmt = null;
            if (hasAssessmentNotes){
                stmt = con.prepareStatement("Update Tbl_LeadMaster set leadStage=?, leadStatus=?, updateBy=?,updateDate=?,updatedatefilter=?, assessmentNotes=? where mbopsChildId=? limit 1");
                stmt.setString(1,leadStage);
                stmt.setString(2,leadStatus);
                stmt.setString(3,"API");
                stmt.setString(4,createDate);
                stmt.setInt(5,createDateFilter);
                stmt.setString(6,assessmnetNotes);
                stmt.setString(7,mbopsChildId);
            }else{
                 stmt = con.prepareStatement("Update Tbl_LeadMaster set leadStage=?, leadStatus=?, updateBy=?,updateDate=?,updatedatefilter=? where mbopsChildId=? limit 1");
                stmt.setString(1,leadStage);
                stmt.setString(2,leadStatus);
                stmt.setString(3,"API");
                stmt.setString(4,createDate);
                stmt.setInt(5,createDateFilter);
                stmt.setString(6,mbopsChildId);
            }

            logger.info("Lead Stage and Status Update By API Query > {}",stmt);
            updated = stmt.executeUpdate();

            PreparedStatement stmt2 = con.prepareStatement("INSERT INTO Tbl_LeadUpdateStageStatus_API(`MBLMSCHILD_ID`,`createDate`,`createDateFilter`,`stage`,`status`,`updated`)VALUES(?,?,?,?,?,?);");
            stmt2.setString(1,mbopsChildId);
            stmt2.setString(2,createDate);
            stmt2.setInt(3,createDateFilter);
            stmt2.setString(4,leadStage);
            stmt2.setString(5,leadStatus);
            stmt2.setInt(6,updated);

            logger.info("Log Table Query > {}",stmt);


            stmt2.execute();

        }catch (Exception e){
            logger.warn("Exception while updating leadStage and leadStatus with childID > {}",e);
        }finally {
            try{
                con.close();
            }catch (SQLException sql){
                sql.printStackTrace();
            }
        }

        return updated;
    }

    @Override
    public int updateLeadStageAndStatusByPhoneNumber(String phoneNumber,String leadStage, String leadStatus,String assessmnetNotes,boolean hasAssessmentNotes) {
        logger.info("Updating leadStage and leadStatus of lead with phone numnber > {}",phoneNumber);

        int updated = 0;
        Connection con =  null;
        try{

            String createDate = UtilityClass.getDateRedable();
            int createDateFilter = UtilityClass.dateFilterDate();
            con = dataSource.getConnection();
            PreparedStatement stmt = null;
            if (hasAssessmentNotes){
                stmt = con.prepareStatement("Update Tbl_LeadMaster set leadStage=?, leadStatus=?, updateBy=?,updateDate=?,updatedatefilter=?, assessmentNotes=? where phoneNumber=? limit 1");
                stmt.setString(1,leadStage);
                stmt.setString(2,leadStatus);
                stmt.setString(3,"API");
                stmt.setString(4,createDate);
                stmt.setInt(5,createDateFilter);
                stmt.setString(6,assessmnetNotes);
                stmt.setString(7,phoneNumber);
            }else{
                stmt = con.prepareStatement("Update Tbl_LeadMaster set leadStage=?, leadStatus=?, updateBy=?,updateDate=?,updatedatefilter=? where phoneNumber=? limit 1");
                stmt.setString(1,leadStage);
                stmt.setString(2,leadStatus);
                stmt.setString(3,"API");
                stmt.setString(4,createDate);
                stmt.setInt(5,createDateFilter);
                stmt.setString(6,phoneNumber);
            }

            logger.info("Lead Stage and Status Update By API Query > {}",stmt);
            updated = stmt.executeUpdate();

            PreparedStatement stmt2 = con.prepareStatement("INSERT INTO Tbl_LeadUpdateStageStatus_API(`MBLMSCHILD_ID`,`createDate`,`createDateFilter`,`stage`,`status`,`updated`)VALUES(?,?,?,?,?,?);");
            stmt2.setString(1,phoneNumber);
            stmt2.setString(2,createDate);
            stmt2.setInt(3,createDateFilter);
            stmt2.setString(4,leadStage);
            stmt2.setString(5,leadStatus);
            stmt2.setInt(6,updated);

            logger.info("Log Table Query > {}",stmt);


            stmt2.execute();

        }catch (Exception e){
            logger.warn("Exception while updating leadStage and leadStatus with phoneNumber > {}",e);
        }finally {
            try{
                con.close();
            }catch (SQLException sql){
                sql.printStackTrace();
            }
        }

        return updated;
    }



    @Override
    public Map<String,String> bulkInsert(List<LeadMasterDao> al, UserMasterDao userMasterDao) throws SQLException {

        logger.info("serviceImpl>bulkInsert");
        Connection con = null;
        int totalinsert = 0;
        int alsize = al.size();
        Map<String,String> responseMap =new HashMap<>();

        long startcounter = counterService.getCounter(2, alsize + 1);

        try {
            con = dataSource.getConnection();
            con.setAutoCommit(false);
            PreparedStatement stmt = con.prepareStatement("INSERT ignore INTO `Tbl_LeadMaster` ( `address`, `attachmentList`, `city`, `clientType`, `company`, `country`, `createBy`, `createDate`, `dateFilter`, `descrip`, `email`, `firstName`, `interestedProduct`, `lastForward`, `lastName`, `leadSource`, `leadStatus`, `leadsourceinner`, `phonenumber`, `pincode`, `prospectiveBuissness`, `salutation`, `state`, `updateBy`, `updateDate`,id,leadStage,leadPriority,parentName,childName,dob,childDevDelay,professionFilled,childPlayPattern,typeSchool,pageName,seekingSupport,supportFor,interventionAreas,formalAssessment,ongoingTherapy,developmentTime,seizureHistory,callReqCstTeam,personAssigned,assignmentDate,reviewResult,approved,typeTherapy,callComments,cpName,registeredInMb,registrationDate,gender,relation,childLanguage,parentLanguage,learningConcern,difficultyInFriendship,payment,clinicalCallTime,pgBdName,pgBdManager,supportOption,keyword,leadDate,leadDatefilter,leadScore,assessmentNotes,lastQueryDate,lastQueryDateFilter) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);");

            LeadMasterDao leadMasterDao;

            String createDate = UtilityClass.getDateRedable();
            int dateFilter = UtilityClass.dateFilterDate();
            String leadDate = UtilityClass.getLeadDate();
            HashMap<Integer, Integer> perUserNotification = new HashMap<>();
            for (int i = 0; i < alsize; i++) {
                leadMasterDao = al.get(i);


                if(leadMasterDao.getLeadDate() == ""){
                    leadMasterDao.setLeadDate(leadDate);
                    leadMasterDao.setLeadDatefilter(dateFilter);
                }else{
                    leadMasterDao.setLeadDatefilter(UtilityClass.leadDatefilter(leadMasterDao.getLeadDate()));
                }

                stmt.setString(1, leadMasterDao.getAddress());
                stmt.setString(2, leadMasterDao.getAttachmentList());
                stmt.setString(3, leadMasterDao.getCity());
                stmt.setString(4, leadMasterDao.getClientType());
                stmt.setString(5, leadMasterDao.getCompany());
               // stmt.setString(6, leadMasterDao.getCountry());
                stmt.setString(6, isoToCodeMap.get(leadMasterDao.getCountry()));
                stmt.setString(7, userMasterDao.getUsername());
                stmt.setString(8, createDate);
                stmt.setInt(9, dateFilter);
                stmt.setString(10, leadMasterDao.getDescrip());
                stmt.setString(11, leadMasterDao.getEmail());
                stmt.setString(12, leadMasterDao.getFirstName());
                stmt.setString(13, leadMasterDao.getInterestedProduct());
                stmt.setInt(14, leadMasterDao.getLastForward());
                stmt.setString(15, leadMasterDao.getLastName());
                stmt.setString(16, leadMasterDao.getLeadSource());
                stmt.setString(17, leadMasterDao.getLeadStatus());
                stmt.setString(18, "Bulk Upload");
                stmt.setString(19, formatPhonenumber(leadMasterDao));
                stmt.setString(20, leadMasterDao.getPincode());
                stmt.setDouble(21, leadMasterDao.getProspectiveBuissness());
                stmt.setString(22, leadMasterDao.getSalutation());
                stmt.setString(23, leadMasterDao.getState());
                stmt.setString(24, userMasterDao.getUsername());
                stmt.setString(25, createDate);
                stmt.setLong(26, startcounter);
                stmt.setString(27, leadMasterDao.getLeadStage());
                stmt.setString(28, leadMasterDao.getLeadPriority());//  FB CAMPAIGN
                stmt.setString(29, leadMasterDao.getParentName());
                stmt.setString(30, leadMasterDao.getChildName());
                stmt.setString(31, leadMasterDao.getDob());
                stmt.setString(32, leadMasterDao.getChildDevDelay());
                stmt.setString(33, leadMasterDao.getProfessionFilled());//ENABLER
                stmt.setString(34, leadMasterDao.getChildPlayPattern());//BPO
                stmt.setString(35, leadMasterDao.getTypeSchool());//ALTERNATE PHONE NUMBER

                stmt.setString(36, leadMasterDao.getPageName());
                stmt.setString(37, leadMasterDao.getSeekingSupport());
                stmt.setString(38, leadMasterDao.getSupportFor());
                stmt.setString(39, leadMasterDao.getInterventionAreas());
                stmt.setString(40, leadMasterDao.getFormalAssessment());
                stmt.setString(41, leadMasterDao.getOngoingTherapy());
                stmt.setString(42, leadMasterDao.getDevelopmentTime());
                stmt.setString(43, leadMasterDao.getSeizureHistory());
                stmt.setString(44, leadMasterDao.getCallReqCstTeam());
                stmt.setString(45, leadMasterDao.getPersonAssigned());
                stmt.setString(46, leadMasterDao.getAssignmentDate());
                stmt.setString(47, leadMasterDao.getReviewResult());
                stmt.setString(48, leadMasterDao.getApproved());
                stmt.setString(49, leadMasterDao.getTypeTherapy());
                stmt.setString(50, leadMasterDao.getCallComments());
                stmt.setString(51, leadMasterDao.getCpName());
                stmt.setString(52, leadMasterDao.getRegisteredInMb());
                stmt.setString(53, leadMasterDao.getRegistrationDate());
                stmt.setString(54, leadMasterDao.getGender());

                stmt.setString(55, leadMasterDao.getRelation());
                stmt.setString(56, leadMasterDao.getChildLanguage());
                stmt.setString(57, leadMasterDao.getParentLanguage());
                stmt.setString(58, leadMasterDao.getLearningConcern());
                stmt.setString(59, leadMasterDao.getDifficultyInFriendship());
                stmt.setInt(60, leadMasterDao.getPayment());
                stmt.setString(61, leadMasterDao.getClinicalCallTime());
                stmt.setString(62, leadMasterDao.getPgBdName());
                stmt.setString(63, leadMasterDao.getPgBdManager());
                stmt.setString(64, leadMasterDao.getSupportFor());

                stmt.setString(65, leadMasterDao.getKeyword());//FB KEYWORD
                stmt.setString(66, leadMasterDao.getLeadDate());//ACTAUL DATE ON WHICH LEAD WAS CREATED(NOT UPLOADED IN LMS)
                stmt.setInt(67,leadMasterDao.getLeadDatefilter());
                stmt.setInt(68,leadMasterDao.getLeadScore());

                stmt.setString(69,leadMasterDao.getAssessmentNotes());

                stmt.setString(70,createDate);
                stmt.setInt(71,dateFilter);


                startcounter++;
                logger.info("Adding to batch {}",startcounter);
                stmt.addBatch();
            }


            logger.info("Inserting Lead!!{}", stmt);
            int[] arr = stmt.executeBatch();
            con.commit();
            con.close();
            List<String> phonenumberListFail=new ArrayList<>();
            //totalinsert = arr.length;//this includes both ignored and inserted records

            for(int i=0;i<arr.length;i++)
            {
                if(arr[i]==1){
                    responseMap.put(al.get(i).getPhonenumber(),"Uploaded");
                    totalinsert++;
                    int lastForward=al.get(i).getLastForward();
                    try {
                        int userCount = perUserNotification.get(lastForward);
                        perUserNotification.put(lastForward, userCount + 1);
                    } catch (NullPointerException n) {
                        perUserNotification.put(lastForward, 1);
                    }
                }else{
                    responseMap.put(al.get(i).getPhonenumber(),"Already Present");
                    phonenumberListFail.add(al.get(i).getPhonenumber());
                }
            }
            logger.info("Failed To Insert Phonenumber so will increase Counter {}",phonenumberListFail);


                 updateLeadConterByPhonenumber(phonenumberListFail,perUserNotification,userMasterDao);


            int duplicate = alsize - totalinsert;
//            if(duplicate == 0){
//                return "Successfully Inserted " + totalinsert + " Lead(s)! ";
//            }else{
//                return "Successfully Inserted " + totalinsert + " Lead(s)! Ignored "+duplicate+" leads(Probably Duplicates)";
//            }

            //return "Successfully Inserted " + totalinsert + " Lead(s)! ";
            return responseMap;


        } catch (SQLException sql) {
            con.rollback();
            throw sql;

        } finally {
            try {
                if(!con.isClosed()) {
                    con.close();
                }
                }catch (Exception ew)
            {

            }
        }
    }

    @Override
    public void updateLeadStageAndStatus(long id, UserMasterDao userMasterDao, String leadStage, String leadStatus) throws SQLException {
        Connection con = null;
        try {

            //TEMP DAO  FOR GETTING PREVIOUS STAGE AND STATUS OR LEAD
            LeadMasterDao leadMasterDaoTemp = findById(id);
            con = dataSource.getConnection();
            con.setAutoCommit(false);
            PreparedStatement stmt = null;

            String query = "";
            String dateValue = UtilityClass.getDateRedable();
            int dateFilterValue = UtilityClass.dateFilterDate();
            String agentValue = userMasterDao.getFirstName()+" "+userMasterDao.getLastName()+" ("+userMasterDao.getUsername()+")";
            int agentId = userMasterDao.getId();

            if(leadStage.equalsIgnoreCase("Profiling Done")){

                query = "update Tbl_LeadMaster set leadStatus=?,leadStage=?,profilingDate=?,profilingDateFilter=?,profilingAgent=?,profilingAgentId=?,updateDate=?,updatedatefilter=?,updateBy=?  where id=?;";

                stmt = con.prepareStatement(query);
                stmt.setString(1, leadStatus);
                stmt.setString(2, leadStage);
                stmt.setString(3,dateValue);
                stmt.setInt(4,dateFilterValue);
                stmt.setString(5, agentValue);
                stmt.setInt(6,agentId);
                stmt.setString(7,dateValue);
                stmt.setInt(8,dateFilterValue);
                stmt.setString(9, userMasterDao.getUsername());
                stmt.setLong(10, id);


            }else if(leadStage.equalsIgnoreCase("Interested")){

                query = "update Tbl_LeadMaster set leadStatus=?,leadStage=?,interestedDate=?,interestedDateFilter=?,interestedAgent=?,updateDate=?,updatedatefilter=?,updateBy=? where id=?;";

                stmt = con.prepareStatement(query);
                stmt.setString(1, leadStatus);
                stmt.setString(2, leadStage);
                stmt.setString(3,dateValue);
                stmt.setInt(4,dateFilterValue);
                stmt.setString(5, agentValue);
                stmt.setString(6,dateValue);
                stmt.setInt(7,dateFilterValue);
                stmt.setString(8, userMasterDao.getUsername());
                stmt.setLong(9, id);

            }else{
                agentValue = userMasterDao.getUsername();
                query = "update Tbl_LeadMaster set leadStatus=?,leadStage=?,updateDate=?,updatedatefilter=?,updateBy=? where id=?;";

                stmt = con.prepareStatement(query);
                stmt.setString(1, leadStatus);
                stmt.setString(2, leadStage);
                stmt.setString(3,dateValue);
                stmt.setInt(4,dateFilterValue);
                stmt.setString(5, agentValue);
                stmt.setLong(6, id);
            }



            logger.info("Lead stage change Query >> {}",stmt);

            stmt.executeUpdate();
            LeadStatusTransferDao leadStatusTransferDao = new LeadStatusTransferDao();
            leadStatusTransferDao.setLeadId(id);
            leadStatusTransferDao.setStage(leadStage);
            leadStatusTransferDao.setStatus(leadStatus);
            leadStatusTransferDao.setPreStage(leadMasterDaoTemp.getLeadStage());
            leadStatusTransferDao.setPreStatus(leadMasterDaoTemp.getLeadStatus());
            leadStatusTransferDao.setTiming(UtilityClass.fullDateLong());
            leadStatusTransferDao.setCreateBy(userMasterDao.getUsername());
            leadStatusTransferDao.setReadableDate(UtilityClass.getDateRedable());
            leadStatusTransferService.insert(leadStatusTransferDao);
            if (UtilityClass.propertyService.findProperty("Lead", "UpdateLeadOnStatusChange").equalsIgnoreCase("1")) {
                updateLastUpdateLead(con, userMasterDao.getUsername(), UtilityClass.dateFilterDate(), UtilityClass.getDateRedable(), id);
            }
            con.commit();

        } catch (SQLException sql) {
            con.rollback();
            throw sql;
        } finally {
            con.close();
        }



    }

    @Override
    public int bulkUpdateLeadStageAndStatus(List<LeadTransferDao> al, UserMasterDao userMasterDao, String leadStage, String leadStatus) throws SQLException {
        Connection con = null;
        List<LeadStatusTransferDao> statusTransferDaoList = new ArrayList<>();
        int updateSize = 0;
        try {

            con = dataSource.getConnection();
            con.setAutoCommit(false);
            PreparedStatement stmt = null;
            boolean updateLead = UtilityClass.propertyService.findProperty("Lead", "UpdateLeadOnStatusChange").equalsIgnoreCase("1");

            String query = "";
            String dateValue = UtilityClass.getDateRedable();
            int dateFilterValue = UtilityClass.dateFilterDate();
            String agentValue = userMasterDao.getFirstName()+" "+userMasterDao.getLastName()+" ("+userMasterDao.getUsername()+")";
            int agentId = userMasterDao.getId();

            if(leadStage.equalsIgnoreCase("Profiling Done")){
                    query = "update Tbl_LeadMaster set leadStatus=?,leadStage=?,profilingDate=?,profilingDateFilter=?,profilingAgent=?,profilingAgentId=?, updateDate=?,updatedatefilter=?,updateBy=? where id=?;";
            }
            else if(leadStage.equalsIgnoreCase("Interested")){
                query = "update Tbl_LeadMaster set leadStatus=?,leadStage=?,interestedDate=?,interestedDateFilter=?,interestedAgent=?, updateDate=?,updatedatefilter=?,updateBy=? where id=?;";
            }
            else{
                 query = "update Tbl_LeadMaster set leadStatus=?,leadStage=?, updateDate=?,updatedatefilter=?,updateBy=? where id=?;";
            }

            stmt = con.prepareStatement(query);
            if(leadStage.equalsIgnoreCase("Profiling Done")){
                for(LeadTransferDao transferDao : al){

                    stmt.setString(1, leadStatus);
                    stmt.setString(2, leadStage);
                    stmt.setString(3,dateValue);
                    stmt.setInt(4,dateFilterValue);
                    stmt.setString(5, agentValue);
                    stmt.setInt(6,agentId);
                    stmt.setString(7,dateValue);
                    stmt.setInt(8,dateFilterValue);
                    stmt.setString(9, userMasterDao.getUsername());

                    stmt.setLong(10, transferDao.getLeadId());

                    logger.info("Lead stage change Query >> {}",stmt);

                    stmt.addBatch();

                    LeadMasterDao leadMasterDaoTemp = findById(transferDao.getLeadId());
                    LeadStatusTransferDao leadStatusTransferDao = new LeadStatusTransferDao();
                    leadStatusTransferDao.setLeadId(transferDao.getLeadId());
                    leadStatusTransferDao.setStatus(leadStatus);
                    leadStatusTransferDao.setStage(leadStage);
                    leadStatusTransferDao.setPreStatus(leadMasterDaoTemp.getLeadStatus());
                    leadStatusTransferDao.setPreStage(leadMasterDaoTemp.getLeadStage());
                    leadStatusTransferDao.setTiming(UtilityClass.fullDateLong());
                    leadStatusTransferDao.setCreateBy(userMasterDao.getUsername());
                    leadStatusTransferDao.setReadableDate(UtilityClass.getDateRedable());
                    statusTransferDaoList.add(leadStatusTransferDao);
                    //leadStatusTransferService.insert(leadStatusTransferDao);
                    if (updateLead) {
                        updateLastUpdateLead(con, userMasterDao.getUsername(), UtilityClass.dateFilterDate(), UtilityClass.getDateRedable(), transferDao.getLeadId());

                    }
                }
            }
            else if (leadStage.equalsIgnoreCase("Interested")){
                for(LeadTransferDao transferDao : al){

                    stmt.setString(1, leadStatus);
                    stmt.setString(2, leadStage);
                    stmt.setString(3,dateValue);
                    stmt.setInt(4,dateFilterValue);
                    stmt.setString(5, agentValue);
                    stmt.setString(6,dateValue);
                    stmt.setInt(7,dateFilterValue);
                    stmt.setString(8, userMasterDao.getUsername());

                    stmt.setLong(9, transferDao.getLeadId());

                    logger.info("Lead stage change Query >> {}",stmt);

                    stmt.addBatch();

                    LeadMasterDao leadMasterDaoTemp = findById(transferDao.getLeadId());
                    LeadStatusTransferDao leadStatusTransferDao = new LeadStatusTransferDao();
                    leadStatusTransferDao.setLeadId(transferDao.getLeadId());
                    leadStatusTransferDao.setStatus(leadStatus);
                    leadStatusTransferDao.setStage(leadStage);
                    leadStatusTransferDao.setPreStatus(leadMasterDaoTemp.getLeadStatus());
                    leadStatusTransferDao.setPreStage(leadMasterDaoTemp.getLeadStage());
                    leadStatusTransferDao.setTiming(UtilityClass.fullDateLong());
                    leadStatusTransferDao.setCreateBy(userMasterDao.getUsername());
                    leadStatusTransferDao.setReadableDate(UtilityClass.getDateRedable());
                    statusTransferDaoList.add(leadStatusTransferDao);
                    //leadStatusTransferService.insert(leadStatusTransferDao);
                    if (updateLead) {
                        updateLastUpdateLead(con, userMasterDao.getUsername(), UtilityClass.dateFilterDate(), UtilityClass.getDateRedable(), transferDao.getLeadId());

                    }
                }
            }
            else{
                for(LeadTransferDao transferDao : al){

                    stmt.setString(1, leadStatus);
                    stmt.setString(2, leadStage);
                    stmt.setString(3,dateValue);
                    stmt.setInt(4,dateFilterValue);
                    stmt.setString(5, userMasterDao.getUsername());

                    stmt.setLong(6, transferDao.getLeadId());

                    logger.info("Lead stage change Query >> {}",stmt);

                    stmt.addBatch();

                    LeadMasterDao leadMasterDaoTemp = findById(transferDao.getLeadId());
                    LeadStatusTransferDao leadStatusTransferDao = new LeadStatusTransferDao();
                    leadStatusTransferDao.setLeadId(transferDao.getLeadId());
                    leadStatusTransferDao.setStatus(leadStatus);
                    leadStatusTransferDao.setStage(leadStage);
                    leadStatusTransferDao.setPreStatus(leadMasterDaoTemp.getLeadStatus());
                    leadStatusTransferDao.setPreStage(leadMasterDaoTemp.getLeadStage());
                    leadStatusTransferDao.setTiming(UtilityClass.fullDateLong());
                    leadStatusTransferDao.setCreateBy(userMasterDao.getUsername());
                    leadStatusTransferDao.setReadableDate(UtilityClass.getDateRedable());
                    statusTransferDaoList.add(leadStatusTransferDao);
                    //leadStatusTransferService.insert(leadStatusTransferDao);
                    if (updateLead) {
                        updateLastUpdateLead(con, userMasterDao.getUsername(), UtilityClass.dateFilterDate(), UtilityClass.getDateRedable(), transferDao.getLeadId());

                    }
                }
            }


            int[] updateArr = stmt.executeBatch();

            for(int i=0;i<updateArr.length;i++){
                if(updateArr[i] == 1){
                    updateSize++;
                }
            }


            logger.info("Updated : {} leads ",updateSize);
            con.commit();


        } catch (SQLException sql) {
            con.rollback();
            throw sql;
        } finally {
            con.close();
            leadStatusTransferService.bulkInsert(statusTransferDaoList);
        }

        return updateSize;

    }

    @Override
    public void updateLeadStatus(long id, String createBy, String leadStatus) throws SQLException {

        //TEMP DAO  FOR GETTING PREVIOUS STAGE AND STATUS OR LEAD
        LeadMasterDao leadMasterDaoTemp = findById(id);
        Connection con = null;
        try {
            con = dataSource.getConnection();
            con.setAutoCommit(false);
            PreparedStatement stmt = con.prepareStatement("update Tbl_LeadMaster set leadStatus=? where id=?;");
            stmt.setString(1, leadStatus);
            stmt.setLong(2, id);
            stmt.executeUpdate();

            LeadStatusTransferDao leadStatusTransferDao = new LeadStatusTransferDao();
            leadStatusTransferDao.setLeadId(id);
            leadStatusTransferDao.setStage(leadMasterDaoTemp.getLeadStage());
            leadStatusTransferDao.setStatus(leadStatus);
            leadStatusTransferDao.setPreStage(leadMasterDaoTemp.getLeadStage());
            leadStatusTransferDao.setPreStatus(leadMasterDaoTemp.getLeadStatus());
            leadStatusTransferDao.setTiming(UtilityClass.fullDateLong());
            leadStatusTransferDao.setCreateBy(createBy);
            leadStatusTransferDao.setReadableDate(UtilityClass.getDateRedable());
            leadStatusTransferService.insert(leadStatusTransferDao);

            if (UtilityClass.propertyService.findProperty("Lead", "UpdateLeadOnStatusChange").equalsIgnoreCase("1")) {
                updateLastUpdateLead(con, createBy, UtilityClass.dateFilterDate(), UtilityClass.getDateRedable(), id);

            }
            con.commit();
        } catch (SQLException sql) {
            con.rollback();
            throw sql;
        } finally {
            con.close();
        }

    }

    @Override
    public void convertLead(long id, String createBy, String createDate) throws SQLException {
        Connection con = null;
        try {
            con = dataSource.getConnection();
            PreparedStatement stmt = con.prepareStatement("update Tbl_LeadMaster set Tbl_LeadMaster.convertDate=?,convertDateFilter=?,leadConvert=?,timeTakenToConvert=? where Tbl_LeadMaster.id=?; ");
            String currentdate = UtilityClass.getDateRedable();
            long timedif = UtilityClass.getDateDifferenceInMinutes(createDate, currentdate);
            stmt.setString(1, currentdate);
            stmt.setInt(2, UtilityClass.dateFilterDate());
            stmt.setBoolean(3, true);
            stmt.setString(4, String.valueOf(timedif));
            stmt.setLong(5, id);
            stmt.executeUpdate();
            logger.info("Converting LeadId {} by {} Mins Taken To Convert {}", id, createBy, timedif);


        } catch (SQLException sql) {
            throw sql;

        } finally {
            con.close();
        }
    }

    @Override
    public int findCountForCampaign(String whereQuery) {

        int count = 0;
        String queryCount = "SELECT count(Tbl_LeadMaster.id)  FROM `Tbl_LeadMaster` inner join Tbl_UserMaster on Tbl_LeadMaster.lastForward=Tbl_UserMaster.id wherequery ;";
        queryCount = queryCount.replace("wherequery", whereQuery);
        logger.info("Selected Query Count For Campaign {}", queryCount);
        try {
            Connection con = dataSource.getConnection();
            count = findCount(queryCount, con);
            con.close();
        } catch (SQLException sql) {
            sql.printStackTrace();
        }
        return count;
    }

    @Override
    public void updateUrnNumberOfLead(long id, String urnNumber, String createBy) throws SQLException {
        Connection con = null;

        try {
            con = dataSource.getConnection();
            PreparedStatement stmt =con.prepareStatement("update Tbl_LeadMaster set Tbl_LeadMaster.urnNumber=? where id=?;");
            stmt.setString(1, urnNumber);
            stmt.setLong(2, id);
            stmt.executeUpdate();
            if (UtilityClass.propertyService.findProperty("Lead", "UpdateLeadOnStatusChange").equalsIgnoreCase("1")) {
                updateLastUpdateLead(con, createBy, UtilityClass.dateFilterDate(), UtilityClass.getDateRedable(), id);

            }
        } catch (SQLException sql) {
            logger.error("Error Occured", sql);
        } finally {
            con.close();
        }


    }

    @Override
    public List<CampaignObjectDao> findLeadsForCampaign(String whereQuery, boolean hasurl, CampaignDocument campaignDocument, int offset, int limit) throws SQLException {

        System.out.println("Program working 3");
        List<CampaignObjectDao> al = new ArrayList<>();
        Map<Integer,Boolean> excludedLeadIdList = campaignDocument.getExcludedLeadIdList();
        //String query = "SELECT Tbl_LeadMaster.firstName, Tbl_LeadMaster.lastName, Tbl_LeadMaster.email, Tbl_LeadMaster.phonenumber, Tbl_LeadMaster.state, Tbl_LeadMaster.city, Tbl_LeadMaster.interestedProduct, Tbl_LeadMaster.id, Tbl_LeadMaster.lastForward FROM Tbl_LeadMaster wherequery limit ?,?;";
        String query = "SELECT Tbl_LeadMaster.firstName,Tbl_LeadMaster.lastName,Tbl_LeadMaster.email,Tbl_LeadMaster.phonenumber,Tbl_LeadMaster.state,Tbl_LeadMaster.city,Tbl_LeadMaster.interestedProduct,Tbl_LeadMaster.id,Tbl_LeadMaster.lastForward,Tbl_LeadMaster.parentName,Tbl_LeadMaster.childName,Tbl_LeadMaster.state,Tbl_LeadMaster.city,Tbl_LeadMaster.interestedProduct,Tbl_LeadMaster.mbopsChildId,Tbl_LeadMaster.clinicalCallTime FROM Tbl_LeadMaster wherequery limit ?,?;";
        query = query.replace("wherequery", whereQuery);
        logger.info("Loading Query For Campaign Data {}", query);
        Connection con = null;
        String url = UtilityClass.propertyService.findProperty("Application", "applicationUrl");

        try {
            con = dataSource.getConnection();
            PreparedStatement stmt = con.prepareStatement(query);
            stmt.setInt(1, offset);
            stmt.setInt(2, limit);
            ResultSet rs = stmt.executeQuery();
            CampaignDataService model;
            StringBuilder sb;
            while (rs.next()) {

                model = new CampaignDataService();
                model.setFullname(rs.getString(1) + " " + rs.getString(2));
                model.setEmail(rs.getString(3));
                model.setPhonenumber(rs.getString(4));
                model.setState(rs.getString(5));
                model.setCity(rs.getString(6));
                model.setProduct(rs.getString(7));
                model.setLeadId(rs.getLong(8));

                model.setLastForward(rs.getInt(9));
                if (hasurl) {

                    sb = new StringBuilder();
                    sb.append(url);
                    sb.append("link/allow/offer");
                    sb.append("?campaigntransid=");
                    sb.append(campaignDocument.getId());
                    sb.append(model.getLeadId());
                    logger.info("CampaignDocument has URL :: {}",sb);
                    List<CampaignObjectDao> campaignObjectDaoList =  new ArrayList<>();
                    CampaignObjectDao campaignObjectDao = new CampaignObjectDao();
                    campaignObjectDao.setUrl(sb.toString());
                    campaignObjectDaoList.add(campaignObjectDao);

                    model.setUrl(urlShortnerService.urlShortnerServer(campaignObjectDaoList).get(0).getUrl());

                }
                System.out.println("Program working 6");
                model.setParentName(rs.getString(10));
                model.setChildName(rs.getString(11));
                model.setState(rs.getString(12));
                model.setCity(rs.getString(13));
                model.setProduct(rs.getString(14));
                model.setMbopsChildId(rs.getString(15));
                model.setAppointmentDate(rs.getString(16));

                model.setCampaignId(campaignDocument.getId());
                model.setOpenCount(0);
                model.setId(campaignDocument.getId() + model.getLeadId());

                if(excludedLeadIdList.get(model.getLeadId()) == null){
                    al.add(model);
                }

            }//END OF WHILE LOOP

        } catch (SQLException | IOException sql) {
            sql.printStackTrace();

        } finally {
            try {
                con.close();
            } catch (Exception ew) {

            }
        }

        return al;
    }

    @Override
    public void delete(long id) {
        logger.info("Delete {}", id);
        leadMasterRepo.deleteById(id);
    }


    @Override
    public PaginationDao getAllDynamicQuery(int limit, int offset, String whereQuery, MaskingDao maskingDao) throws SQLException {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT `Tbl_LeadMaster`.`id`, `Tbl_LeadMaster`.`address`, `Tbl_LeadMaster`.`city`, `Tbl_LeadMaster`.`clientType`, `Tbl_LeadMaster`.`company`, `Tbl_LeadMaster`.`convertDate`, `Tbl_LeadMaster`.`country`, `Tbl_LeadMaster`.`createBy`, `Tbl_LeadMaster`.`createDate`, `Tbl_LeadMaster`.`descrip`,");
        if (maskingDao.isEmailMasking()) {

            sb.append("concat('xxxxxxxx',right(Tbl_LeadMaster.email,4)) ");

        } else {
            sb.append(" Tbl_LeadMaster.email ");
        }
        sb.append(", `Tbl_LeadMaster`.`firstName`, `Tbl_LeadMaster`.`interestedProduct`, `Tbl_LeadMaster`.`lastForward`, `Tbl_LeadMaster`.`lastName`, `Tbl_LeadMaster`.`leadSource`, `Tbl_LeadMaster`.`leadStatus`, `Tbl_LeadMaster`.`leadsourceinner`, `Tbl_LeadMaster`.`nextFollowUp`,");
        if (maskingDao.isNumberMasking()) {
            sb.append(" concat('xxxxxxxx',right(Tbl_LeadMaster.phonenumber,2)) ");

        } else {
            sb.append(" Tbl_LeadMaster.phonenumber ");
        }
        sb.append("  , `Tbl_LeadMaster`.`pincode`, `Tbl_LeadMaster`.`prospectiveBuissness`, `Tbl_LeadMaster`.`salutation`, `Tbl_LeadMaster`.`state`, `Tbl_LeadMaster`.`updateBy`, `Tbl_LeadMaster`.`updateDate`, Tbl_UserMaster.username,Tbl_LeadMaster.leadStage,Tbl_LeadMaster.leadConvert,Tbl_LeadMaster.timeTakenToConvert,Tbl_LeadMaster.urnNumber,Tbl_LeadMaster.leadPriority, `Tbl_LeadMaster`.`parentName`, `Tbl_LeadMaster`.`childName`, `Tbl_LeadMaster`.`dob`, `Tbl_LeadMaster`.`childDevDelay`, `Tbl_LeadMaster`.`professionFilled`, `Tbl_LeadMaster`.`childPlayPattern`, `Tbl_LeadMaster`.`typeSchool`, `Tbl_LeadMaster`.`pageName`, `Tbl_LeadMaster`.`seekingSupport`, `Tbl_LeadMaster`.`supportFor`, `Tbl_LeadMaster`.`interventionAreas`, `Tbl_LeadMaster`.`formalAssessment`, `Tbl_LeadMaster`.`ongoingTherapy`, `Tbl_LeadMaster`.`developmentTime`, `Tbl_LeadMaster`.`seizureHistory`, `Tbl_LeadMaster`.`callReqCstTeam`, `Tbl_LeadMaster`.`personAssigned`, `Tbl_LeadMaster`.`assignmentDate`, `Tbl_LeadMaster`.`reviewResult`, `Tbl_LeadMaster`.`approved`, `Tbl_LeadMaster`.`typeTherapy`, `Tbl_LeadMaster`.`callComments`, `Tbl_LeadMaster`.`cpName`, `Tbl_LeadMaster`.`registeredInMb`, `Tbl_LeadMaster`.`registrationDate`, `Tbl_LeadMaster`.`gender`, `Tbl_LeadMaster`.`relation`, `Tbl_LeadMaster`.`childLanguage`, `Tbl_LeadMaster`.`parentLanguage`, `Tbl_LeadMaster`.`learningConcern`, `Tbl_LeadMaster`.`difficultyInFriendship`, `Tbl_LeadMaster`.`payment`, `Tbl_LeadMaster`.`clinicalCallTime`, `Tbl_LeadMaster`.`pgBdName`, `Tbl_LeadMaster`.`pgBdManager`, `Tbl_LeadMaster`.`supportOption`,Tbl_LeadMaster.counter,`Tbl_LeadMaster`.`adId`,`Tbl_LeadMaster`.`keyword`,`Tbl_LeadMaster`.`leadDate`,Tbl_LeadMaster.interestedAgent,Tbl_LeadMaster.interestedDate,Tbl_LeadMaster.profilingAgent,Tbl_LeadMaster.profilingDate,Tbl_LeadMaster.profilingAgentId,Tbl_LeadMaster.c2cAttempts,Tbl_LeadMaster.leadScore,Tbl_LeadMaster.mbopsChildId,Tbl_LeadMaster.mbopsParentId,Tbl_LeadMaster.mbopsChildEnrollId,Tbl_LeadMaster.assessmentNotes,Tbl_LeadMaster.countryId,Tbl_LeadMaster.stateId,Tbl_LeadMaster.doesChildGoToSchool, ");
        //PRE SCHOOL ASSESSMENT FIELDS
        sb.append(" `Tbl_LeadMaster`.`main_Listening1`,`Tbl_LeadMaster`.`main_Listening2`,`Tbl_LeadMaster`.`main_Listening3`,`Tbl_LeadMaster`.`main_Listening4`,`Tbl_LeadMaster`.`main_Listening5`,`Tbl_LeadMaster`.`main_Listening6`,`Tbl_LeadMaster`.`main_Listening7`,`Tbl_LeadMaster`.`main_Listening8`,`Tbl_LeadMaster`.`main_Listening9`,`Tbl_LeadMaster`.`main_Listening10`,`Tbl_LeadMaster`.`main_Listening11`,`Tbl_LeadMaster`.`main_Oral_Expression_Speaking1`,`Tbl_LeadMaster`.`main_Oral_Expression_Speaking2`,`Tbl_LeadMaster`.`main_Oral_Expression_Speaking3`,`Tbl_LeadMaster`.`main_Oral_Expression_Speaking4`,`Tbl_LeadMaster`.`main_Oral_Expression_Speaking5`,`Tbl_LeadMaster`.`main_Oral_Expression_Speaking6`,`Tbl_LeadMaster`.`main_Oral_Expression_Speaking7`,`Tbl_LeadMaster`.`main_Oral_Expression_Speaking8`,`Tbl_LeadMaster`.`main_Oral_Expression_Speaking9`,`Tbl_LeadMaster`.`main_Oral_Expression_Speaking10`,`Tbl_LeadMaster`.`main_Oral_Expression_Speaking11`,`Tbl_LeadMaster`.`main_Oral_Expression_Speaking12`,`Tbl_LeadMaster`.`main_Basic_Reading1`,`Tbl_LeadMaster`.`main_Basic_Reading2`,`Tbl_LeadMaster`.`main_Basic_Reading3`,`Tbl_LeadMaster`.`main_Basic_Reading4`,`Tbl_LeadMaster`.`main_Basic_Reading5`,`Tbl_LeadMaster`.`main_Basic_Reading6`,`Tbl_LeadMaster`.`main_Basic_Reading7`,`Tbl_LeadMaster`.`main_Basic_Reading8`,`Tbl_LeadMaster`.`main_Basic_Reading9`,`Tbl_LeadMaster`.`main_Basic_Reading10`,`Tbl_LeadMaster`.`main_Basic_Reading11`,`Tbl_LeadMaster`.`main_Basic_Reading12`,`Tbl_LeadMaster`.`main_Basic_Reading13`,`Tbl_LeadMaster`.`main_Reading_Comprehension1`,`Tbl_LeadMaster`.`main_Reading_Comprehension2`,`Tbl_LeadMaster`.`main_Reading_Comprehension3`,`Tbl_LeadMaster`.`main_Reading_Comprehension4`,`Tbl_LeadMaster`.`main_Reading_Comprehension5`,`Tbl_LeadMaster`.`main_Reading_Comprehension6`,`Tbl_LeadMaster`.`main_Reading_Comprehension7`,`Tbl_LeadMaster`.`main_Math_Calculations1`,`Tbl_LeadMaster`.`main_Math_Calculations2`,`Tbl_LeadMaster`.`main_Math_Calculations3`,`Tbl_LeadMaster`.`main_Math_Calculations4`,`Tbl_LeadMaster`.`main_Math_Calculations5`,`Tbl_LeadMaster`.`main_Math_Calculations6`,`Tbl_LeadMaster`.`main_Math_Calculations7`,`Tbl_LeadMaster`.`main_Math_Calculations8`,`Tbl_LeadMaster`.`main_Math_Calculations9`,`Tbl_LeadMaster`.`main_Math_Calculations10`,`Tbl_LeadMaster`.`main_Math_Reasoning1`,`Tbl_LeadMaster`.`main_Math_Reasoning2`,`Tbl_LeadMaster`.`main_Math_Reasoning3`,`Tbl_LeadMaster`.`main_Math_Reasoning4`,`Tbl_LeadMaster`.`main_Math_Reasoning5`,`Tbl_LeadMaster`.`main_Math_Reasoning6`,`Tbl_LeadMaster`.`main_Math_Reasoning7`,`Tbl_LeadMaster`.`main_Written_Expression1`,`Tbl_LeadMaster`.`main_Written_Expression2`,`Tbl_LeadMaster`.`main_Written_Expression3`,`Tbl_LeadMaster`.`main_Written_Expression4`,`Tbl_LeadMaster`.`main_Written_Expression5`,`Tbl_LeadMaster`.`main_Written_Expression6`,`Tbl_LeadMaster`.`main_Written_Expression7`,`Tbl_LeadMaster`.`main_Written_Expression8`,`Tbl_LeadMaster`.`main_Written_Expression9`,`Tbl_LeadMaster`.`main_Written_Expression10`,`Tbl_LeadMaster`.`main_Written_Expression11`,`Tbl_LeadMaster`.`main_Written_Expression12`,`Tbl_LeadMaster`.`main_Behavior1`,`Tbl_LeadMaster`.`main_Behavior2`,`Tbl_LeadMaster`.`main_Behavior3`,`Tbl_LeadMaster`.`main_Behavior4`,`Tbl_LeadMaster`.`main_Behavior5`,`Tbl_LeadMaster`.`main_Behavior6`,`Tbl_LeadMaster`.`main_Behavior7`,`Tbl_LeadMaster`.`main_Behavior8`,`Tbl_LeadMaster`.`main_Behavior9`,`Tbl_LeadMaster`.`main_Behavior10`,`Tbl_LeadMaster`.`main_Behavior11`,`Tbl_LeadMaster`.`main_Behavior12`,`Tbl_LeadMaster`.`main_Behavior13`,`Tbl_LeadMaster`.`main_Behavior14`,`Tbl_LeadMaster`.`main_Behavior15`, ");
        //MAINSTREAM SCHOOL ASSESSMENT FIELDS
        sb.append(" `Tbl_LeadMaster`.`pre_Gross_Motor_Skills1`,`Tbl_LeadMaster`.`pre_Gross_Motor_Skills2`,`Tbl_LeadMaster`.`pre_Gross_Motor_Skills3`,`Tbl_LeadMaster`.`pre_Gross_Motor_Skills4`,`Tbl_LeadMaster`.`pre_Gross_Motor_Skills5`,`Tbl_LeadMaster`.`pre_Gross_Motor_Skills6`,`Tbl_LeadMaster`.`pre_Fine_motor_Skills1`,`Tbl_LeadMaster`.`pre_Fine_motor_Skills2`,`Tbl_LeadMaster`.`pre_Fine_motor_Skills3`,`Tbl_LeadMaster`.`pre_Fine_motor_Skills4`,`Tbl_LeadMaster`.`pre_Fine_motor_Skills5`,`Tbl_LeadMaster`.`pre_Fine_motor_Skills6`,`Tbl_LeadMaster`.`pre_Fine_motor_Skills7`,`Tbl_LeadMaster`.`pre_Fine_motor_Skills8`,`Tbl_LeadMaster`.`pre_Fine_motor_Skills9`,`Tbl_LeadMaster`.`pre_Fine_motor_Skills10`,`Tbl_LeadMaster`.`pre_Self_help1`,`Tbl_LeadMaster`.`pre_Self_help2`,`Tbl_LeadMaster`.`pre_Self_help3`,`Tbl_LeadMaster`.`pre_Self_help4`,`Tbl_LeadMaster`.`pre_Self_help5`,`Tbl_LeadMaster`.`pre_Self_help6`,`Tbl_LeadMaster`.`pre_Self_help7`,`Tbl_LeadMaster`.`pre_Self_help8`,`Tbl_LeadMaster`.`pre_Self_help9`,`Tbl_LeadMaster`.`pre_Self_help10`,`Tbl_LeadMaster`.`pre_Social_Emotional1`,`Tbl_LeadMaster`.`pre_Social_Emotional2`,`Tbl_LeadMaster`.`pre_Social_Emotional3`,`Tbl_LeadMaster`.`pre_Social_Emotional4`,`Tbl_LeadMaster`.`pre_Social_Emotional5`,`Tbl_LeadMaster`.`pre_Social_Emotional6`,`Tbl_LeadMaster`.`pre_Social_Emotional7`,`Tbl_LeadMaster`.`pre_Social_Emotional8`,`Tbl_LeadMaster`.`pre_Social_Emotional9`,`Tbl_LeadMaster`.`pre_Social_Emotional10`,`Tbl_LeadMaster`.`pre_Cognitive1`,`Tbl_LeadMaster`.`pre_Cognitive2`,`Tbl_LeadMaster`.`pre_Cognitive3`,`Tbl_LeadMaster`.`pre_Cognitive4`,`Tbl_LeadMaster`.`pre_Cognitive5`,`Tbl_LeadMaster`.`pre_Cognitive6`,`Tbl_LeadMaster`.`pre_Cognitive7`,`Tbl_LeadMaster`.`pre_Cognitive8`,`Tbl_LeadMaster`.`pre_Cognitive9`,`Tbl_LeadMaster`.`pre_Cognitive10`,`Tbl_LeadMaster`.`pre_Cognitive11`,`Tbl_LeadMaster`.`pre_Communication1`,`Tbl_LeadMaster`.`pre_Communication2`,`Tbl_LeadMaster`.`pre_Communication3`,`Tbl_LeadMaster`.`pre_Communication4`,`Tbl_LeadMaster`.`pre_Communication5`,`Tbl_LeadMaster`.`pre_Communication6`,`Tbl_LeadMaster`.`pre_Communication7`,`Tbl_LeadMaster`.`pre_Communication8`,`Tbl_LeadMaster`.`pre_Communication9`,`Tbl_LeadMaster`.`pre_Communication10`,`Tbl_LeadMaster`.`pre_Communication11`,`Tbl_LeadMaster`.`pre_Communication12`,`Tbl_LeadMaster`.`pre_Communication13`,`Tbl_LeadMaster`.`pre_Communication14`,`Tbl_LeadMaster`.`pre_Communication15`,`Tbl_LeadMaster`.`pre_Communication16`,`Tbl_LeadMaster`.`pre_Communication17`,`Tbl_LeadMaster`.`pre_Communication18`,`Tbl_LeadMaster`.`pre_Behaviors1`,`Tbl_LeadMaster`.`pre_Behaviors2`,`Tbl_LeadMaster`.`pre_Behaviors3`,`Tbl_LeadMaster`.`pre_Behaviors4`,`Tbl_LeadMaster`.`pre_Behaviors5`,`Tbl_LeadMaster`.`pre_Behaviors6`, ");
        //GROUP SCORE FIELDS
        sb.append(" `Tbl_LeadMaster`.`mainlistening`,`Tbl_LeadMaster`.`mainloralexpression`,`Tbl_LeadMaster`.`mainbasicreading`,`Tbl_LeadMaster`.`mainreadingcomprehension`,`Tbl_LeadMaster`.`mainmathcalculations`,`Tbl_LeadMaster`.`mainmathreasoning`,`Tbl_LeadMaster`.`mainwrittenexpression`,`Tbl_LeadMaster`.`mainbehavior`,`Tbl_LeadMaster`.`pregrossmotorskills`,`Tbl_LeadMaster`.`prefinemotorskills`,`Tbl_LeadMaster`.`preselfhelp`,`Tbl_LeadMaster`.`presocialemotional`,`Tbl_LeadMaster`.`precognitive`,`Tbl_LeadMaster`.`precommunication`,`Tbl_LeadMaster`.`prebehaviors` ,`Tbl_LeadMaster`.`assessmentDate` ,`Tbl_LeadMaster`.`assessmentDateFilter` ,`Tbl_LeadMaster`.`adFormData`,`Tbl_LeadMaster`.`messenger`,`Tbl_LeadMaster`.`active`,`Tbl_LeadMaster`.`preferredCallingTime`,`Tbl_LeadMaster`.`preferredCallingSlot`,`Tbl_LeadMaster`.`paymentStatus`,`Tbl_LeadMaster`.`callStatus`,`Tbl_LeadMaster`.`followUpTime`,`Tbl_LeadMaster`.`followUpMessage`,`Tbl_LeadMaster`.`otpStatus` ");
        //CONDITIONS
        sb.append(" FROM `Tbl_LeadMaster`inner join Tbl_UserMaster on Tbl_LeadMaster.lastForward=Tbl_UserMaster.id wherequery order by DATE_FORMAT(STR_TO_DATE(updateDate, '%d-%m-%Y %H:%i:%s'), '%Y-%m-%d %H:%i:%s')  desc,id desc limit ?,? ;");

        String query = sb.toString();
        query = query.replace("wherequery", whereQuery);
        String queryCount = "SELECT count(Tbl_LeadMaster.id)  FROM `Tbl_LeadMaster` inner join Tbl_UserMaster on Tbl_LeadMaster.lastForward=Tbl_UserMaster.id wherequery ;";
        queryCount = queryCount.replace("wherequery", whereQuery);
        logger.info("Query Count is {}", queryCount);
        logger.info("Normal Query is {}", query);
        PaginationDao paginationDao = new PaginationDao();
        Connection con = null;
        List<Object> al = new ArrayList<>();
        try {
            con = dataSource.getConnection();
            int count = findCount(queryCount, con);
            paginationDao.setRecordsFiltered(count);
            paginationDao.setRecordsTotal(count);
            if (count == 0) {
                paginationDao.setData(al);
                return paginationDao;
            }

            PreparedStatement stmt = con.prepareStatement(query);
            stmt.setInt(1, offset);
            stmt.setInt(2, limit);
            ResultSet rs = stmt.executeQuery();

            LeadMasterDao leadMasterDao = null;
            while (rs.next()) {
                leadMasterDao = new LeadMasterDao();
                leadMasterDao.setId(rs.getLong(1));
                leadMasterDao.setAddress(rs.getString(2));
                leadMasterDao.setCity(rs.getString(3));
                leadMasterDao.setClientType(rs.getString(4));
                leadMasterDao.setCompany(rs.getString(5));
                leadMasterDao.setConvertDate(rs.getString(6));
                leadMasterDao.setCountry(rs.getString(7));
                leadMasterDao.setCreateBy(rs.getString(8));
                leadMasterDao.setCreateDate(rs.getString(9));
                leadMasterDao.setDescrip(rs.getString(10));
                leadMasterDao.setEmail(rs.getString(11));
                leadMasterDao.setFirstName(rs.getString(12));
                leadMasterDao.setInterestedProduct(rs.getString(13));
                leadMasterDao.setLastForward(rs.getInt(14));
                leadMasterDao.setLastName(rs.getString(15));
                leadMasterDao.setLeadSource(rs.getString(16));
                leadMasterDao.setLeadStatus(rs.getString(17));
                leadMasterDao.setLeadsourceinner(rs.getString(18));
                leadMasterDao.setNextFollowUp(rs.getString(19));
                leadMasterDao.setPhonenumber(rs.getString(20));
                leadMasterDao.setPincode(rs.getString(21));
                leadMasterDao.setProspectiveBuissness(rs.getDouble(22));
                leadMasterDao.setSalutation(rs.getString(23));
                leadMasterDao.setState(rs.getString(24));
                leadMasterDao.setUpdateBy(rs.getString(25));
                leadMasterDao.setUpdateDate(rs.getString(26));
                leadMasterDao.setUsername(rs.getString(27));
                leadMasterDao.setLeadStage(rs.getString(28));
                leadMasterDao.setLeadConvert(rs.getBoolean(29));
                leadMasterDao.setTimeTakenToConvert(rs.getString(30));
                leadMasterDao.setUrnNumber(rs.getString(31));
                leadMasterDao.setLeadPriority(rs.getString(32));// USED AS FB CAMPAIGN FOR MOMSBELIEF
                leadMasterDao.setParentName(rs.getString(33));
                leadMasterDao.setChildName(rs.getString(34));
                leadMasterDao.setDob(rs.getString(35));
                leadMasterDao.setChildDevDelay(rs.getString(36));
                leadMasterDao.setProfessionFilled(rs.getString(37));//ENABLED
                leadMasterDao.setChildPlayPattern(rs.getString(38));//BPO
                leadMasterDao.setTypeSchool(rs.getString(39));//ALTERNATE PHHONE NUMBER

                leadMasterDao.setPageName(rs.getString(40));
                leadMasterDao.setSeekingSupport(rs.getString(41));
                leadMasterDao.setSupportFor(rs.getString(42));
                leadMasterDao.setInterventionAreas(rs.getString(43));
                leadMasterDao.setFormalAssessment(rs.getString(44));
                leadMasterDao.setOngoingTherapy(rs.getString(45));
                leadMasterDao.setDevelopmentTime(rs.getString(46));
                leadMasterDao.setSeizureHistory(rs.getString(47));
                leadMasterDao.setCallReqCstTeam(rs.getString(48));
                leadMasterDao.setPersonAssigned(rs.getString(49));
                leadMasterDao.setAssignmentDate(rs.getString(50));
                leadMasterDao.setReviewResult(rs.getString(51));
                leadMasterDao.setApproved(rs.getString(52));
                leadMasterDao.setTypeTherapy(rs.getString(53));
                leadMasterDao.setCallComments(rs.getString(54));
                leadMasterDao.setCpName(rs.getString(55));
                leadMasterDao.setRegisteredInMb(rs.getString(56));
                leadMasterDao.setRegistrationDate(rs.getString(57));
                leadMasterDao.setGender(rs.getString(58));

                leadMasterDao.setRelation(rs.getString(59));
                leadMasterDao.setChildLanguage(rs.getString(60));
                leadMasterDao.setParentLanguage(rs.getString(61));
                leadMasterDao.setLearningConcern(rs.getString(62));
                leadMasterDao.setDifficultyInFriendship(rs.getString(63));
                leadMasterDao.setPayment(rs.getInt(64));
                leadMasterDao.setClinicalCallTime(rs.getString(65));
                leadMasterDao.setPgBdName(rs.getString(66));
                leadMasterDao.setPgBdManager(rs.getString(67));
                leadMasterDao.setSupportOption(rs.getString(68));
                leadMasterDao.setCounter(rs.getShort(69));
                leadMasterDao.setAdId(rs.getLong(70));
                leadMasterDao.setKeyword(rs.getString(71));
                leadMasterDao.setLeadDate(rs.getString(72));//ACTUAL DATE ON WHICH LEAD WAS CREATED (NOT UPLOADED INTO SYSTEM)
                leadMasterDao.setInterestedAgent(rs.getString(73));
                leadMasterDao.setInterestedDate(rs.getString(74));
                leadMasterDao.setProfilingAgent(rs.getString(75));
                leadMasterDao.setProfilingDate(rs.getString(76));
                leadMasterDao.setProfilingAgentId(rs.getInt(77));
                leadMasterDao.setC2cAttempts(rs.getInt(78));
                leadMasterDao.setLeadScore(rs.getInt(79));
                leadMasterDao.setMbopsChildId(rs.getString(80));
                leadMasterDao.setMbopsParentId(rs.getString(81));
                leadMasterDao.setMbopsChildEnrollId(rs.getString(82));
                leadMasterDao.setAssessmentNotes(rs.getString(83));

                leadMasterDao.setCountryId(rs.getInt(84));
                leadMasterDao.setStateId(rs.getInt(85));
                leadMasterDao.setDoesChildGoToSchool(rs.getString(86));

                //MAINSTREAM SCHOOL ASSESSMENT FIELDS
                leadMasterDao.setMain_Listening1(rs.getString(87));
                leadMasterDao.setMain_Listening2(rs.getString(88));
                leadMasterDao.setMain_Listening3(rs.getString(89));
                leadMasterDao.setMain_Listening4(rs.getString(90));
                leadMasterDao.setMain_Listening5(rs.getString(91));
                leadMasterDao.setMain_Listening6(rs.getString(92));
                leadMasterDao.setMain_Listening7(rs.getString(93));
                leadMasterDao.setMain_Listening8(rs.getString(94));
                leadMasterDao.setMain_Listening9(rs.getString(95));
                leadMasterDao.setMain_Listening10(rs.getString(96));
                leadMasterDao.setMain_Listening11(rs.getString(97));
                leadMasterDao.setMain_Oral_Expression_Speaking1(rs.getString(98));
                leadMasterDao.setMain_Oral_Expression_Speaking2(rs.getString(99));
                leadMasterDao.setMain_Oral_Expression_Speaking3(rs.getString(100));
                leadMasterDao.setMain_Oral_Expression_Speaking4(rs.getString(101));
                leadMasterDao.setMain_Oral_Expression_Speaking5(rs.getString(102));
                leadMasterDao.setMain_Oral_Expression_Speaking6(rs.getString(103));
                leadMasterDao.setMain_Oral_Expression_Speaking7(rs.getString(104));
                leadMasterDao.setMain_Oral_Expression_Speaking8(rs.getString(105));
                leadMasterDao.setMain_Oral_Expression_Speaking9(rs.getString(106));
                leadMasterDao.setMain_Oral_Expression_Speaking10(rs.getString(107));
                leadMasterDao.setMain_Oral_Expression_Speaking11(rs.getString(108));
                leadMasterDao.setMain_Oral_Expression_Speaking12(rs.getString(109));
                leadMasterDao.setMain_Basic_Reading1(rs.getString(110));
                leadMasterDao.setMain_Basic_Reading2(rs.getString(111));
                leadMasterDao.setMain_Basic_Reading3(rs.getString(112));
                leadMasterDao.setMain_Basic_Reading4(rs.getString(113));
                leadMasterDao.setMain_Basic_Reading5(rs.getString(114));
                leadMasterDao.setMain_Basic_Reading6(rs.getString(115));
                leadMasterDao.setMain_Basic_Reading7(rs.getString(116));
                leadMasterDao.setMain_Basic_Reading8(rs.getString(117));
                leadMasterDao.setMain_Basic_Reading9(rs.getString(118));
                leadMasterDao.setMain_Basic_Reading10(rs.getString(119));
                leadMasterDao.setMain_Basic_Reading11(rs.getString(120));
                leadMasterDao.setMain_Basic_Reading12(rs.getString(121));
                leadMasterDao.setMain_Basic_Reading13(rs.getString(122));
                leadMasterDao.setMain_Reading_Comprehension1(rs.getString(123));
                leadMasterDao.setMain_Reading_Comprehension2(rs.getString(124));
                leadMasterDao.setMain_Reading_Comprehension3(rs.getString(125));
                leadMasterDao.setMain_Reading_Comprehension4(rs.getString(126));
                leadMasterDao.setMain_Reading_Comprehension5(rs.getString(127));
                leadMasterDao.setMain_Reading_Comprehension6(rs.getString(128));
                leadMasterDao.setMain_Reading_Comprehension7(rs.getString(129));
                leadMasterDao.setMain_Math_Calculations1(rs.getString(130));
                leadMasterDao.setMain_Math_Calculations2(rs.getString(131));
                leadMasterDao.setMain_Math_Calculations3(rs.getString(132));
                leadMasterDao.setMain_Math_Calculations4(rs.getString(133));
                leadMasterDao.setMain_Math_Calculations5(rs.getString(134));
                leadMasterDao.setMain_Math_Calculations6(rs.getString(135));
                leadMasterDao.setMain_Math_Calculations7(rs.getString(136));
                leadMasterDao.setMain_Math_Calculations8(rs.getString(137));
                leadMasterDao.setMain_Math_Calculations9(rs.getString(138));
                leadMasterDao.setMain_Math_Calculations10(rs.getString(139));
                leadMasterDao.setMain_Math_Reasoning1(rs.getString(140));
                leadMasterDao.setMain_Math_Reasoning2(rs.getString(141));
                leadMasterDao.setMain_Math_Reasoning3(rs.getString(142));
                leadMasterDao.setMain_Math_Reasoning4(rs.getString(143));
                leadMasterDao.setMain_Math_Reasoning5(rs.getString(144));
                leadMasterDao.setMain_Math_Reasoning6(rs.getString(145));
                leadMasterDao.setMain_Math_Reasoning7(rs.getString(146));
                leadMasterDao.setMain_Written_Expression1(rs.getString(147));
                leadMasterDao.setMain_Written_Expression2(rs.getString(148));
                leadMasterDao.setMain_Written_Expression3(rs.getString(149));
                leadMasterDao.setMain_Written_Expression4(rs.getString(150));
                leadMasterDao.setMain_Written_Expression5(rs.getString(151));
                leadMasterDao.setMain_Written_Expression6(rs.getString(152));
                leadMasterDao.setMain_Written_Expression7(rs.getString(153));
                leadMasterDao.setMain_Written_Expression8(rs.getString(154));
                leadMasterDao.setMain_Written_Expression9(rs.getString(155));
                leadMasterDao.setMain_Written_Expression10(rs.getString(156));
                leadMasterDao.setMain_Written_Expression11(rs.getString(157));
                leadMasterDao.setMain_Written_Expression12(rs.getString(158));
                leadMasterDao.setMain_Behavior1(rs.getString(159));
                leadMasterDao.setMain_Behavior2(rs.getString(160));
                leadMasterDao.setMain_Behavior3(rs.getString(161));
                leadMasterDao.setMain_Behavior4(rs.getString(162));
                leadMasterDao.setMain_Behavior5(rs.getString(163));
                leadMasterDao.setMain_Behavior6(rs.getString(164));
                leadMasterDao.setMain_Behavior7(rs.getString(165));
                leadMasterDao.setMain_Behavior8(rs.getString(166));
                leadMasterDao.setMain_Behavior9(rs.getString(167));
                leadMasterDao.setMain_Behavior10(rs.getString(168));
                leadMasterDao.setMain_Behavior11(rs.getString(169));
                leadMasterDao.setMain_Behavior12(rs.getString(170));
                leadMasterDao.setMain_Behavior13(rs.getString(171));
                leadMasterDao.setMain_Behavior14(rs.getString(172));
                leadMasterDao.setMain_Behavior15(rs.getString(173));

                //PRESCHOOL ASSESSMENT FIELDS

                leadMasterDao.setPre_Gross_Motor_Skills1(rs.getString(174));
                leadMasterDao.setPre_Gross_Motor_Skills2(rs.getString(175));
                leadMasterDao.setPre_Gross_Motor_Skills3(rs.getString(176));
                leadMasterDao.setPre_Gross_Motor_Skills4(rs.getString(177));
                leadMasterDao.setPre_Gross_Motor_Skills5(rs.getString(178));
                leadMasterDao.setPre_Gross_Motor_Skills6(rs.getString(179));
                leadMasterDao.setPre_Fine_motor_Skills1(rs.getString(180));
                leadMasterDao.setPre_Fine_motor_Skills2(rs.getString(181));
                leadMasterDao.setPre_Fine_motor_Skills3(rs.getString(182));
                leadMasterDao.setPre_Fine_motor_Skills4(rs.getString(183));
                leadMasterDao.setPre_Fine_motor_Skills5(rs.getString(184));
                leadMasterDao.setPre_Fine_motor_Skills6(rs.getString(185));
                leadMasterDao.setPre_Fine_motor_Skills7(rs.getString(186));
                leadMasterDao.setPre_Fine_motor_Skills8(rs.getString(187));
                leadMasterDao.setPre_Fine_motor_Skills9(rs.getString(188));
                leadMasterDao.setPre_Fine_motor_Skills10(rs.getString(189));
                leadMasterDao.setPre_Self_help1(rs.getString(190));
                leadMasterDao.setPre_Self_help2(rs.getString(191));
                leadMasterDao.setPre_Self_help3(rs.getString(192));
                leadMasterDao.setPre_Self_help4(rs.getString(193));
                leadMasterDao.setPre_Self_help5(rs.getString(194));
                leadMasterDao.setPre_Self_help6(rs.getString(195));
                leadMasterDao.setPre_Self_help7(rs.getString(196));
                leadMasterDao.setPre_Self_help8(rs.getString(197));
                leadMasterDao.setPre_Self_help9(rs.getString(198));
                leadMasterDao.setPre_Self_help10(rs.getString(199));
                leadMasterDao.setPre_Social_Emotional1(rs.getString(200));
                leadMasterDao.setPre_Social_Emotional2(rs.getString(201));
                leadMasterDao.setPre_Social_Emotional3(rs.getString(202));
                leadMasterDao.setPre_Social_Emotional4(rs.getString(203));
                leadMasterDao.setPre_Social_Emotional5(rs.getString(204));
                leadMasterDao.setPre_Social_Emotional6(rs.getString(205));
                leadMasterDao.setPre_Social_Emotional7(rs.getString(206));
                leadMasterDao.setPre_Social_Emotional8(rs.getString(207));
                leadMasterDao.setPre_Social_Emotional9(rs.getString(208));
                leadMasterDao.setPre_Social_Emotional10(rs.getString(209));
                leadMasterDao.setPre_Cognitive1(rs.getString(210));
                leadMasterDao.setPre_Cognitive2(rs.getString(211));
                leadMasterDao.setPre_Cognitive3(rs.getString(212));
                leadMasterDao.setPre_Cognitive4(rs.getString(213));
                leadMasterDao.setPre_Cognitive5(rs.getString(214));
                leadMasterDao.setPre_Cognitive6(rs.getString(215));
                leadMasterDao.setPre_Cognitive7(rs.getString(216));
                leadMasterDao.setPre_Cognitive8(rs.getString(217));
                leadMasterDao.setPre_Cognitive9(rs.getString(218));
                leadMasterDao.setPre_Cognitive10(rs.getString(219));
                leadMasterDao.setPre_Cognitive11(rs.getString(220));
                leadMasterDao.setPre_Communication1(rs.getString(221));
                leadMasterDao.setPre_Communication2(rs.getString(222));
                leadMasterDao.setPre_Communication3(rs.getString(223));
                leadMasterDao.setPre_Communication4(rs.getString(224));
                leadMasterDao.setPre_Communication5(rs.getString(225));
                leadMasterDao.setPre_Communication6(rs.getString(226));
                leadMasterDao.setPre_Communication7(rs.getString(227));
                leadMasterDao.setPre_Communication8(rs.getString(228));
                leadMasterDao.setPre_Communication9(rs.getString(229));
                leadMasterDao.setPre_Communication10(rs.getString(230));
                leadMasterDao.setPre_Communication11(rs.getString(231));
                leadMasterDao.setPre_Communication12(rs.getString(232));
                leadMasterDao.setPre_Communication13(rs.getString(233));
                leadMasterDao.setPre_Communication14(rs.getString(234));
                leadMasterDao.setPre_Communication15(rs.getString(235));
                leadMasterDao.setPre_Communication16(rs.getString(236));
                leadMasterDao.setPre_Communication17(rs.getString(237));
                leadMasterDao.setPre_Communication18(rs.getString(238));
                leadMasterDao.setPre_Behaviors1(rs.getString(239));
                leadMasterDao.setPre_Behaviors2(rs.getString(240));
                leadMasterDao.setPre_Behaviors3(rs.getString(241));
                leadMasterDao.setPre_Behaviors4(rs.getString(242));
                leadMasterDao.setPre_Behaviors5(rs.getString(243));
                leadMasterDao.setPre_Behaviors6(rs.getString(244));
                //GROUP SCORES
                leadMasterDao.setMainlistening(rs.getInt(245));
                leadMasterDao.setMainloralexpression(rs.getInt(246));
                leadMasterDao.setMainbasicreading(rs.getInt(247));
                leadMasterDao.setMainreadingcomprehension(rs.getInt(248));
                leadMasterDao.setMainmathcalculations(rs.getInt(249));
                leadMasterDao.setMainmathreasoning(rs.getInt(250));
                leadMasterDao.setMainwrittenexpression(rs.getInt(251));
                leadMasterDao.setMainbehavior(rs.getInt(252));
                leadMasterDao.setPregrossmotorskills(rs.getInt(253));
                leadMasterDao.setPrefinemotorskills(rs.getInt(254));
                leadMasterDao.setPreselfhelp(rs.getInt(255));
                leadMasterDao.setPresocialemotional(rs.getInt(256));
                leadMasterDao.setPrecognitive(rs.getInt(257));
                leadMasterDao.setPrecommunication(rs.getInt(258));
                leadMasterDao.setPrebehaviors(rs.getInt(259));

                leadMasterDao.setAssessmentDate(rs.getString(260));
                leadMasterDao.setAssessmentDateFilter(rs.getInt(261));
                leadMasterDao.setAdFormData(rs.getString(262));
                leadMasterDao.setMessenger(rs.getString(263));
                leadMasterDao.setActive(rs.getString(264));
                leadMasterDao.setPreferredCallingTime(rs.getString(265));
                leadMasterDao.setPreferredCallingSlot(rs.getString(266));
                leadMasterDao.setPaymentStatus(rs.getBoolean(267));
                leadMasterDao.setCallStatus(rs.getString(268));
                //By Rahul--------------------------------
                leadMasterDao.setFollowUpMessage(rs.getString(269));
                leadMasterDao.setFollowUpTime(rs.getString(270));

                //====================By Sujit==================
                 leadMasterDao.setOtpStatus(rs.getByte(271));
//                 if(leadMasterDao.getId()==118171){
//                      logger.info("ye rhi resultset ::{}"+leadMasterDao);
//                 }
                al.add(leadMasterDao);
            }

            paginationDao.setData(al);

        } catch (SQLException sql) {
            throw sql;

        } finally {
            con.close();
        }
        return paginationDao;


    }


    @Override
    public List<String> getLeadSourceInner() throws Exception {

        logger.info("getting all lead source inner");
        Connection con = null;
        List<String> list = new ArrayList<>();
        try {

             con = dataSource.getConnection();
             PreparedStatement stmt = con.prepareStatement("select leadsourceinner from Tbl_LeadMaster group by leadsourceinner");
             ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                list.add(rs.getString(1));
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            throw ex;
        } finally {
            try {
                con.close();
            } catch (Exception exc) {
                exc.printStackTrace();
                throw exc;
            }
        }
        return list;
    }

    @Override
    public void updateLastUpdateLeadMultiple(Connection con, String updateBy, int updateDate, String upddatestr, String idList) {
        boolean defaultClose = false;
        try {
            if (con == null) {
                con = dataSource.getConnection();
                defaultClose = true;
            }
            PreparedStatement stmt = con.prepareStatement("update Tbl_LeadMaster set updateDate=?,updatedatefilter=?,updateBy=? where id in (" + idList + ");");
            stmt.setString(1, upddatestr);
            stmt.setInt(2, updateDate);
            stmt.setString(3, updateBy);

            stmt.executeUpdate();
            logger.info("Updaing of lead id = {} by= {} date ={}", idList, updateBy, upddatestr);

        } catch (SQLException sql) {
            sql.printStackTrace();
            logger.error("Error", sql);

        } finally {
            if (defaultClose) {
                try {
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public void tranferLead(long id, int userId) {
        Connection con = null;
        try {
            con = dataSource.getConnection();
            PreparedStatement stmt = con.prepareStatement("update Tbl_LeadMaster set Tbl_LeadMaster.lastForward=? where id=?;");
            stmt.setInt(1, userId);
            stmt.setLong(2, id);
            stmt.executeUpdate();
        } catch (SQLException sql) {
            sql.printStackTrace();

        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

    @Override
    public LeadMasterDao findById(long id) {
        return leadMasterRepo.findById(id).get();
    }

    private String convertString(String content, LeadMasterDao leadMasterDao) {
        content = content.replaceAll("@parentName", leadMasterDao.getParentName());
        content = content.replaceAll("@childName", leadMasterDao.getChildName());
        content = content.replaceAll("@phonenumber", leadMasterDao.getPhonenumber());
        content = content.replaceAll("@state", leadMasterDao.getParentName());
        content = content.replaceAll("@city", leadMasterDao.getParentName());
        content = content.replaceAll("@product", leadMasterDao.getParentName());

        return content;
    }

    private String convertStrigWithParameters(String content,LeadMasterDao leadMasterDao,HashMap<String,String> hashMap)
    {
        content = content.replaceAll("@parentName", leadMasterDao.getParentName());
        content = content.replaceAll("@childName", leadMasterDao.getChildName());
        content = content.replaceAll("@phonenumber", leadMasterDao.getPhonenumber());
        content = content.replaceAll("@state", leadMasterDao.getState());
        content = content.replaceAll("@city", leadMasterDao.getCity());
        content = content.replaceAll("@product", leadMasterDao.getInterestedProduct());
        content = content.replaceAll("@email", leadMasterDao.getEmail());
        content = content.replaceAll("@appointmentDate",leadMasterDao.getClinicalCallTime());
        content = content.replaceAll("@mbopschildid",leadMasterDao.getMbopsChildId());

        for(String key:hashMap.keySet())
        {
            content=content.replaceAll(key,hashMap.get(key));
        }
        return content;
    }

    @Override
    public void singleNotification(String templateId, LeadMasterDao leadMasterDao) throws Exception {
//        TemplateDocument templateDocument = templateDocumentService.findById(templateId);
        System.out.println("Push notification ke ander Template ki ID "+templateId);
        System.out.println("Leadmaster Dao is "+ leadMasterDao);
//        String templateId2 = templateId.substring(0, 24).trim();
//        System.out.println("template id after Substring "+ templateId2);
        TemplateDocument templateDocument = null;
        try{
            templateDocument = templateDocumentService.findById(templateId);
        }catch(NoSuchElementException e){
            System.out.println("This is WATI TEMPLATE");
            templateDocument = new TemplateDocument();
//            templateDocument.setTemplateName(campaignDocument.getTemplateName());
            templateDocument.setTemplateStatus((byte)1);
            templateDocument.setTemplate(templateId);
            templateDocument.setTemplateType("Whatsapp");

        }
        System.out.println("In Singlie Notoifation template document is " + templateDocument);
        templateDocument.setTemplate(convertString(templateDocument.getTemplate(), leadMasterDao));

        if (templateDocument.getTemplateType().equalsIgnoreCase("Mail")) {
            List<MailObjectDao> al = new ArrayList<>();
            MailObjectDao mailObjectDao = new MailObjectDao();
            MailObjectMessage mailObjectMessage = new MailObjectMessage();
            mailObjectMessage.setRecipient(leadMasterDao.getEmail());
            mailObjectMessage.setSubject(templateDocument.getTemplateSubject());
            mailObjectMessage.setCustRef("ASdad");

            mailObjectDao.setMessage(mailObjectMessage);
            MailObjectTemplate mailObjectTemplate = new MailObjectTemplate();
            mailObjectTemplate.setTemplateId(templateDocument.getTemplateIdHidden());
            mailObjectTemplate.setTemplateValues(leadMasterDao.convertToObjectMail());
            mailObjectDao.setTemplate(mailObjectTemplate);
            al.add(mailObjectDao);
            logger.info("Sending Mail {}", mailObjectDao);
            mailObjectService.bulkInsertMail(al);

        } else if (templateDocument.getTemplateType().equalsIgnoreCase("Message")) {
            TextMessageSimpleDao textMessageSimpleDao = new TextMessageSimpleDao();
            textMessageSimpleDao.setContent(templateDocument.getTemplate());
            textMessageSimpleDao.setDst(leadMasterDao.getPhonenumber());
            String senderId = UtilityClass.propertyService.findProperty("Campaign", "SenderId");
            textMessageSimpleDao.setSenderId(senderId);
            logger.info("Sending Text Message {}", textMessageSimpleDao);

            //===============
            String wherequery =  " where Tbl_LeadMaster.id=" + leadMasterDao.getId() + " ";
            CampaignDocument campaignDocument=new CampaignDocument();
            campaignDocument.setName("Quick Campaign(Direct)");
            campaignDocument.setTemplateName("Quick Campaign(Direct)");
            campaignDocument.setTotalReciept(1);
            //campaignDocument.setCreateBy(hashMap.get("@agentName"));
            campaignDocument.setCreateDate(UtilityClass.getDateRedable());
            campaignDocument.setDatefilter(UtilityClass.dateFilterDate());

            List<CampaignObjectDao> al = null;
            try {
                al = findLeadsForCampaign(wherequery, false, campaignDocument, 0, 1);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            HashMap<String, String> hashMap = new HashMap<>();
            hashMap.put("notifycentre","false");
            TextMessage tm = new TextMessage();
            InnerTextMessageDao innerTextMessageDao = new InnerTextMessageDao();
            innerTextMessageDao.setMobileNo(textMessageSimpleDao.getDst());
            innerTextMessageDao.setTemplateName(templateDocument.getTemplateName());
            innerTextMessageDao.setTemplateParams(getVariableValues(templateDocument.getParams(),leadMasterDao,hashMap,al));

            List<InnerTextMessageDao> innerTextMessageDaoList = new ArrayList<>();
            innerTextMessageDaoList.add(innerTextMessageDao);


            tm.setSmsTemplateParams(innerTextMessageDaoList);
            tm.setSender(senderId);

            //textMessageService.sendTextMessage(tm);


            //===============

            //USE THIS METHOD TO SEND DATA WITH PARAMS
            textMessageService.sendTextMessageSingle(textMessageSimpleDao);

        } else if (templateDocument.getTemplateType().equalsIgnoreCase("Whatsapp")) {
            String WAServiceProviders = UtilityClass.propertyService.findProperty("Campaign", "WAServiceProvider");
            if(WAServiceProviders.equalsIgnoreCase("WATI")){
                logger.info("SENDING WHATSAPP TEMPLATE MESSAGE WITH WATI");
                WatiTemplateMsgRequestDao watiTemplateMsgRequestDao = new WatiTemplateMsgRequestDao();
                watiTemplateMsgRequestDao.setBroadcast_name(templateId);
                watiTemplateMsgRequestDao.setTemplate_name(templateId);
                List<WatiReceiverDao> watiReceiverDaoList = new ArrayList<>();
                WatiReceiverDao watiReceiverDao = null;

                    watiReceiverDao = new WatiReceiverDao();
                System.out.println("phone number is" + leadMasterDao.getPhonenumber());
                    String phoneNumber = leadMasterDao.getPhonenumber();
                    if(phoneNumber.length() == 10){
                        phoneNumber = "+91"+phoneNumber;

                    watiReceiverDao.setWhatsappNumber(phoneNumber);

                    CustomParamDao customParamDao = new CustomParamDao("","");
                    List<CustomParamDao> customParamDaos = new ArrayList<>();
                    customParamDaos.add(customParamDao);
                    watiReceiverDao.setCustomParams(customParamDaos);

                    watiReceiverDaoList.add(watiReceiverDao);

                }
                watiTemplateMsgRequestDao.setReceivers(watiReceiverDaoList);
                watiService.sendTemplateMessages(watiTemplateMsgRequestDao);

            }else{
                List<WhatsappDao> al = new ArrayList<>();
                WhatsappDao whatsappDao = new WhatsappDao();
                whatsappDao.setContent(templateDocument.getTemplate());
                whatsappDao.setContentType(templateDocument.getContentType());
                whatsappDao.setFileName("asd");
                whatsappDao.setCaption(templateDocument.getTemplateSubject());
                whatsappDao.setPhone(getPhonenumber(leadMasterDao.getPhonenumber()));
                logger.info("Sending Wa Message {}", whatsappDao);
                al.add(whatsappDao);
                whatsappService.sendMessageWhatsapp(al);
            }
        }
    }

    @Override
    public void updateLeadRecordingUrl(String phonenumber, String recordingUrl) throws SQLException {
        Connection con=null;
        try{
            con=dataSource.getConnection();
            PreparedStatement stmt=con.prepareStatement("update Tbl_Log_Event set recordingUrl=? where leadId=(select Tbl_LeadMaster.id from Tbl_LeadMaster where phonenumber=?) and eventType='Click To Call' order by Tbl_Log_Event.id desc limit 1 ;");
            stmt.setString(1,recordingUrl);
            stmt.setString(2,phonenumber);
            stmt.executeUpdate();
            logger.info("Updating Recording Url For Phonenumer {} and recoring url {}",phonenumber,recordingUrl);
        }catch (Exception ew)
        {
            logger.error("Failed To Update Recording Url {}",ew);

        }finally {
            con.close();
       }

    }

    @Override
    public void updateLeadCounterAndUpdateDate(long id,String leadSource) {
        Connection con=null;
        try{


            con=dataSource.getConnection();
            PreparedStatement stmt=con.prepareStatement("update Tbl_LeadMaster set  counter=(counter+1) ,Tbl_LeadMaster.updateDate=? ,Tbl_LeadMaster.updatedatefilter=?,Tbl_LeadMaster.lastQueryDate=?,Tbl_LeadMaster.lastQueryDateFilter=?,Tbl_LeadMaster.leadSource=? where id=? limit 1;");
            stmt.setString(1,UtilityClass.getDateRedable());
            stmt.setInt(2,UtilityClass.dateFilterDate());
            stmt.setString(3,UtilityClass.getDateRedable());
            stmt.setInt(4,UtilityClass.dateFilterDate());
            stmt.setString(5,leadSource);
            stmt.setLong(6,id);
            stmt.executeUpdate();
            logger.info("Updating LeadCounter and Date {}",stmt);

            //CHANGE STAGE AND STATUS OF THE LEAD IF LEAD IS NOT IN JUNKBOX
            //   UPDATING LEAD-STATUS-TRANSFER-DAO TABLE
//            String newStage = UtilityClass.propertyService.findProperty("Lead", "stageForDuplicateLead");
//            String newStatus = UtilityClass.propertyService.findProperty("Lead", "statusForDuplicateLead");
//
//            PreparedStatement stmt2 = con.prepareStatement("update Tbl_LeadMaster set Tbl_LeadMaster.leadStage=?,Tbl_LeadMaster.leadStatus=? where id=? limit 1;");
//            LeadMasterDao leadMasterDao = findById(id);
//                if(!leadMasterDao.getLeadStage().equalsIgnoreCase("Junkbox")){
//                    stmt2.setString(1, newStage);
//                    stmt2.setString(2, newStatus);
//                    stmt2.setLong(3, id);
//
//                    LeadStatusTransferDao leadStatusTransferDao = new LeadStatusTransferDao();
//                    leadStatusTransferDao.setLeadId(leadMasterDao.getId());
//                    leadStatusTransferDao.setStatus(newStage);
//                    leadStatusTransferDao.setTiming(UtilityClass.fullDateLong());
//                    leadStatusTransferDao.setCreateBy("System");
//                    leadStatusTransferDao.setReadableDate(UtilityClass.getDateRedable());
//                    leadStatusTransferService.insert(leadStatusTransferDao);
//
//                    logger.info("Statement For Update Stage and Status of duplicate leads {}",stmt2);
//                    stmt2.executeUpdate();
//
//
//            }




        }catch (Exception ew)
        {
            logger.error("Failed To Update Counter of lead  {}",ew);

        }finally {
            try {
                con.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

        }
    }


    @Override
    public void updateLeadCounterAndUpdateTransferLead(long id,String leadSource) {
        Connection con=null;
        try{
            con=dataSource.getConnection();
            PreparedStatement stmt=con.prepareStatement("update Tbl_LeadMaster set  counter=(counter+1)  ,Tbl_LeadMaster.updatedatefilter=?,Tbl_LeadMaster.lastQueryDate=?,Tbl_LeadMaster.lastQueryDateFilter=?,Tbl_LeadMaster.leadSource=? where id=? limit 1;");
            //stmt.setString(1,UtilityClass.getDateRedable());
            stmt.setInt(1,UtilityClass.dateFilterDate());
            stmt.setString(2,UtilityClass.getDateRedable());
            stmt.setInt(3,UtilityClass.dateFilterDate());
            stmt.setString(4,leadSource);
            stmt.setLong(5,id);
            stmt.executeUpdate();
            logger.info("Updating LeadCounter and Date {}",stmt);



        }catch (Exception ew)
        {
            logger.error("Failed To Update Counter of lead  {}",ew);

        }finally {
            try {
                con.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

        }
    }

    @Override
    public void updateLeadConterByPhonenumber(List<String> phonenumber,HashMap<Integer,Integer> hashMap,UserMasterDao userMasterDao) {
        Connection con=null;
        try{
            con=dataSource.getConnection();



            if(phonenumber.size()>0) {
                String date = UtilityClass.getDateRedable();
                int dateFilter = UtilityClass.dateFilterDate();
                PreparedStatement stmt = con.prepareStatement("update Tbl_LeadMaster set counter=(counter+1) ,Tbl_LeadMaster.updateDate=? ,Tbl_LeadMaster.updatedatefilter=?,Tbl_LeadMaster.lastQueryDate=?,Tbl_LeadMaster.lastQueryDateFilter=? where phonenumber=? limit 1;");
                for (String phone : phonenumber) {
                    stmt.setString(1, date);
                    stmt.setInt(2, dateFilter);
                    stmt.setString(3, date);
                    stmt.setInt(4, dateFilter);
                    stmt.setString(5, phone);

                    stmt.addBatch();
                }

                logger.info("Statement For Update Duplicacy {}",stmt);
                stmt.executeBatch();


                //CHANGE STAGE AND STATUS OF THE LEAD IF LEAD IS NOT IN JUNKBOX
                //   UPDATING LEAD-STATUS-TRANSFER-DAO TABLE
//                String newStage = UtilityClass.propertyService.findProperty("Lead", "stageForDuplicateLead");
//                String newStatus = UtilityClass.propertyService.findProperty("Lead", "statusForDuplicateLead");

//                PreparedStatement stmt2 = con.prepareStatement("update Tbl_LeadMaster set Tbl_LeadMaster.leadStage=?,Tbl_LeadMaster.leadStatus=? where phonenumber=? limit 1;");
//                LeadMasterDao leadMasterDao = null;
//                LeadStatusTransferDao leadStatusTransferDao = null;
//                int statusChangeCount = 0;
//                for (String phone : phonenumber) {
//                    leadMasterDao = findByPhonenumber(phone);
//                    if(!leadMasterDao.getLeadStage().equalsIgnoreCase("Junkbox")){
//                        stmt2.setString(1, newStage);
//                        stmt2.setString(2, newStatus);
//                        stmt2.setString(3, phone);
//
//                        leadStatusTransferDao = new LeadStatusTransferDao();
//                        leadStatusTransferDao.setLeadId(leadMasterDao.getId());
//                        leadStatusTransferDao.setStatus(newStage);
//                        leadStatusTransferDao.setTiming(UtilityClass.fullDateLong());
//                        leadStatusTransferDao.setCreateBy(userMasterDao.getUsername());
//                        leadStatusTransferDao.setReadableDate(UtilityClass.getDateRedable());
//                        leadStatusTransferService.insert(leadStatusTransferDao);
//                        stmt2.addBatch();
//                        statusChangeCount++;
//                    }
//
//                }
//
//                if (statusChangeCount > 0){
//                    logger.info("Statement For Update Stage and Status of duplicate leads {}",stmt2);
//                    stmt2.executeBatch();
//                }

            }
            sendBatchNotification(hashMap,userMasterDao,con);

        }catch (Exception ew)
        {
            logger.error("Error Occured While Update Count {}",ew);
        }finally {
            try {
                con.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }

    }

    @Override
    public String singleNotificationWithParameter(LeadMasterDao leadMasterDao, String templateId,String templateName, HashMap<String, String> hashMap) throws Exception {
        logger.info("Sending Single notification with parameter, template ID > {}",templateId);

        String WAServiceProvider = UtilityClass.propertyService.findProperty("Campaign", "WAServiceProvider");
        //By Rahul-----------------------------------------------------
        TemplateDocument templateDocument = null;
        try{
            templateDocument = templateDocumentService.findById(templateId);
        }catch(NoSuchElementException e){
            logger.info("Exception Occurs");
            templateDocument = new TemplateDocument();
            templateDocument.setTemplateType("Whatsapp");
        }

        if (templateDocument.getTemplateType().equalsIgnoreCase("Whatsapp")){
            if(WAServiceProvider.equalsIgnoreCase("WATI")){
                templateDocument = new TemplateDocument();
                templateDocument.setTemplateName(templateName);
                templateDocument.setTemplateStatus((byte)1);
                templateDocument.setTemplateType("Whatsapp");
                templateDocument.setTemplate(templateName);
            }

        }
//--------------------------------------------------------------------------------
//        TemplateDocument templateDocument = null;//templateDocumentService.findById(campaignDocument.getTemplateId());
//        if (WAServiceProvider.equalsIgnoreCase("WATI")){
//            templateDocument = new TemplateDocument();
//            templateDocument.setTemplateName(templateName);
//            templateDocument.setTemplateStatus((byte)1);
//            templateDocument.setTemplateType("Whatsapp");
//            templateDocument.setTemplate(templateName);
//        }else{
//            templateDocument = templateDocumentService.findById(templateId);
//        }

//        TemplateDocument templateDocument = templateDocumentService.findById(templateId);

        if(hashMap.get("notifycentre").equalsIgnoreCase("true")){
            CentreAppointmentLogs log = new CentreAppointmentLogs();
            log.setLeadId(leadMasterDao.getId());
            log.setUsername(hashMap.get("@agentEmail"));
            log.setCentreName(hashMap.get("@centrename"));
            log.setCentrePin(hashMap.get("@centrepin"));
            log.setNotifiedTo(hashMap.get("centrephonenumber"));
            log.setNotificationType(templateDocument.getTemplateType());
            appointmentLogsService.insert(log);
        }


        if(templateDocument.getTemplate().contains("@mbopschildid") && leadMasterDao.getMbopsChildId() == null){
            logger.info("Template requires MBOPS-Child-ID, not sending message!");
            return "Template requires MBOPS-Child-ID, not sending message!";
        }

           templateDocument.setTemplate(convertStrigWithParameters(templateDocument.getTemplate(), leadMasterDao,hashMap));

        if (templateDocument.getTemplateType().equalsIgnoreCase("Mail")) {
            logger.info("Template Type is Mail > {}",templateDocument);
            List<MailObjectDao> al = new ArrayList<>();
            MailObjectDao mailObjectDao = new MailObjectDao();
            MailObjectMessage mailObjectMessage = new MailObjectMessage();
            if(hashMap.get("notifycentre").equalsIgnoreCase("true")){
                mailObjectMessage.setRecipient(hashMap.get("centreemail"));
            }else{
                mailObjectMessage.setRecipient(leadMasterDao.getEmail());
            }

            mailObjectMessage.setSubject(templateDocument.getTemplateSubject());
            mailObjectMessage.setCustRef("ASdad");
            mailObjectMessage.setMessage(templateDocument.getTemplate());
            mailObjectDao.setMessage(mailObjectMessage);
            MailObjectTemplate mailObjectTemplate = new MailObjectTemplate();
            mailObjectTemplate.setTemplateId(templateDocument.getTemplateIdHidden());
            mailObjectTemplate.setTemplateValues(leadMasterDao.convertToObjectMail());
            mailObjectDao.setTemplate(mailObjectTemplate);
            al.add(mailObjectDao);
            logger.info("Sending Mail {}", mailObjectDao);
            mailObjectService.bulkInsertMail(al);

        } else if (templateDocument.getTemplateType().equalsIgnoreCase("Message")) {
            logger.info("Template Type is Message > {}",templateDocument);
            TextMessageSimpleDao textMessageSimpleDao = new TextMessageSimpleDao();
            textMessageSimpleDao.setContent(templateDocument.getTemplate());
            if(hashMap.get("notifycentre").equalsIgnoreCase("true")){
                textMessageSimpleDao.setDst(hashMap.get("centrephonenumber"));
            }else{
                textMessageSimpleDao.setDst(leadMasterDao.getPhonenumber());
            }

            String senderId = UtilityClass.propertyService.findProperty("Campaign", "SenderId");
            textMessageSimpleDao.setSenderId(senderId);
            logger.info("Sending Text Message {}", textMessageSimpleDao);
            //===============
            String wherequery =  " where Tbl_LeadMaster.id=" + leadMasterDao.getId() + " ";
            CampaignDocument campaignDocument=new CampaignDocument();
            campaignDocument.setName("Quick Campaign(Direct)");
            campaignDocument.setTemplateName("Quick Campaign(Direct)");
            campaignDocument.setTotalReciept(1);
            campaignDocument.setCreateBy(hashMap.get("@agentName"));
            campaignDocument.setCreateDate(UtilityClass.getDateRedable());
            campaignDocument.setDatefilter(UtilityClass.dateFilterDate());

            List<CampaignObjectDao> al = null;
            try {
                al = findLeadsForCampaign(wherequery, false, campaignDocument, 0, 1);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            TextMessage tm = new TextMessage();
            InnerTextMessageDao innerTextMessageDao = new InnerTextMessageDao();
            innerTextMessageDao.setMobileNo(textMessageSimpleDao.getDst());
            innerTextMessageDao.setTemplateName(templateDocument.getTemplateName());
            innerTextMessageDao.setTemplateParams(getVariableValues(templateDocument.getParams(),leadMasterDao,hashMap,al));

            List<InnerTextMessageDao> innerTextMessageDaoList = new ArrayList<>();
            innerTextMessageDaoList.add(innerTextMessageDao);


            tm.setSmsTemplateParams(innerTextMessageDaoList);
            tm.setSender(senderId);

            //textMessageService.sendTextMessage(tm);


            //===============
            //USE THIS METHOD TO SEND MESSAGE WITH DATA IN PARAMS
            textMessageService.sendTextMessageSingle(textMessageSimpleDao);

        } else if (templateDocument.getTemplateType().equalsIgnoreCase("Whatsapp")) {
            logger.info("Template Type is WhatsApp > {}",templateDocument);
            List<WhatsappDao> al = new ArrayList<>();
            WhatsappDao whatsappDao = new WhatsappDao();
            whatsappDao.setContent(templateDocument.getTemplate());
            whatsappDao.setContentType(templateDocument.getContentType());
            whatsappDao.setFileName("asd");
            whatsappDao.setCaption(templateDocument.getTemplateSubject());
            if(hashMap.get("notifycentre").equalsIgnoreCase("true")){
                whatsappDao.setPhone(getPhonenumber(hashMap.get("centrephonenumber")));
            }else{
                whatsappDao.setPhone(getPhonenumber(leadMasterDao.getPhonenumber()));
            }

            logger.info("Sending Wa Message {}", whatsappDao);
            al.add(whatsappDao);

            if (WAServiceProvider.equalsIgnoreCase("WATI")){
               sendWhatsappMessageWATI(templateName,whatsappDao.getPhone());
            }else{
                whatsappService.sendMessageWhatsapp(al);
            }
            //whatsappService.sendMessageWhatsapp(al);


        }
        return "Success";
    }

    private void sendWhatsappMessageWATI(String templateName,String phone) throws Exception {
        logger.info("SENDING WHATSAPP TEMPLATE MESSAGE WITH WATI");
        WatiTemplateMsgRequestDao watiTemplateMsgRequestDao = new WatiTemplateMsgRequestDao();
        watiTemplateMsgRequestDao.setBroadcast_name(templateName);
        watiTemplateMsgRequestDao.setTemplate_name(templateName);
        List<WatiReceiverDao> watiReceiverDaoList = new ArrayList<>();
        WatiReceiverDao watiReceiverDao = new WatiReceiverDao();
        if(phone.length() == 10){
            phone = "91"+phone;
        }
        watiReceiverDao.setWhatsappNumber(phone);
        CustomParamDao customParamDao = new CustomParamDao("","");
        List<CustomParamDao> customParamDaos = new ArrayList<>();
        customParamDaos.add(customParamDao);
        watiReceiverDao.setCustomParams(customParamDaos);
        watiReceiverDaoList.add(watiReceiverDao);

        watiTemplateMsgRequestDao.setReceivers(watiReceiverDaoList);
        watiService.sendTemplateMessages(watiTemplateMsgRequestDao);
    }

    private String getVariableValues(String[] arrtemp,LeadMasterDao leadMasterDao, Map<String,String> hashMap,List<CampaignObjectDao> al){

        CampaignObjectDao campaignObjectDao = al.get(0);
        if(hashMap.get("notifycentre").equalsIgnoreCase("true")){
            campaignObjectDao.setCentreemail(hashMap.get("centreemail"));
            campaignObjectDao.setCentrehead(hashMap.get("@centrehead"));
            campaignObjectDao.setCentrename(hashMap.get("@centrename"));
            campaignObjectDao.setCentrephonenumber(hashMap.get("centrephonenumber"));
            campaignObjectDao.setCentrepin(hashMap.get("@centrepin"));
        }
        StringBuilder sb=new StringBuilder();
            for(int i=0;i<arrtemp.length;i++)
            {
                String content=arrtemp[i].trim();

                logger.info("content (LeadMasterServiceImpl) :: {}",content);
                if(content=="phonenumber")
                {
                    logger.info("Map Value :: ",leadMasterDao.getPhonenumber());
                    sb.append(campaignObjectDao.getPhonenumber());

                }
                else{
                    logger.info("Map Value :: ",al.get(0).getMatchingValue(content));
                    sb.append(campaignObjectDao.getMatchingValue(content));
                }

                if(i!=arrtemp.length-1)
                {
                    sb.append("~");
                }

            }
        return sb.toString();
    }
    @Override
    public HashMap<Integer,Integer> findFreeUser(String stage, String status, String userId,String leadSource,byte type) throws SQLException {
        Connection con=null;
        HashMap<Integer,Integer> hashMap=new HashMap<>();
        String [] arr=userId.split(",");
        if(arr.length==0)
        {
            try {
                throw new Exception("No User Can be found");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        for(String user:arr)
        {
            hashMap.put(Integer.valueOf(user),1);
        }

        StringBuilder sb=new StringBuilder();
        sb.append("select ar.userId from ( select Tbl_LeadMaster.lastForward as userId,count(Tbl_LeadMaster.id) as count from Tbl_LeadMaster where");
        if(type==1) // by source
        {
            sb.append(" leadSource=? and find_in_set(lastForward,?) and leadStage=? and leadStatus=? group by Tbl_LeadMaster.lastForward) ar order by ar.count asc limit 1;");
        }
        else if(type==2) //by product
        {
            sb.append(" interestedProduct=? and find_in_set(lastForward,?) and leadStage=? and leadStatus=? group by Tbl_LeadMaster.lastForward) ar order by ar.count asc limit 1;");

        }
        else{
            sb.append(" clientType=? and find_in_set(lastForward,?) and leadStage=? and leadStatus=? group by Tbl_LeadMaster.lastForward) ar order by ar.count asc limit 1;");

        }
        try{
            con=dataSource.getConnection();
            PreparedStatement stmt=con.prepareStatement("select arout.userId from( select ar.userId,ar.count from  ( select Tbl_LeadMaster.lastForward as userId,count(Tbl_LeadMaster.id)  as count from Tbl_LeadMaster where find_in_set(lastForward,?)  and leadStage=?  and leadStatus=?  group by Tbl_LeadMaster.lastForward) ar union all select Tbl_UserMaster.id,0 from Tbl_UserMaster where find_in_set(id,?))arout group by arout.userId order by sum(arout.count) asc limit 1;");

            stmt.setString(1,userId);
            stmt.setString(2,stage);
            stmt.setString(3,status);
            stmt.setString(4,userId);
            logger.info("Finding Free Query {}",stmt);
            ResultSet rs=stmt.executeQuery();

            while (rs.next())
            {
                hashMap.put(rs.getInt(1),0);
            }
        }catch (Exception ew)
        {
            logger.error("Error Occured While Executing Lead",ew);

        }finally {
            con.close();
        }
        return hashMap;
    }




    @Override
    public void updateLastUpdateLead(Connection con, String updateBy, int updateDate, String upddatestr, long id) {
        boolean defaultClose = false;
        try {
            if (con == null) {
                con = dataSource.getConnection();
                defaultClose = true;
            }
            PreparedStatement stmt = con.prepareStatement("update Tbl_LeadMaster set updateDate=?,updatedatefilter=?,updateBy=? where id=?;");
            stmt.setString(1, upddatestr);
            stmt.setInt(2, updateDate);
            stmt.setString(3, updateBy);
            stmt.setLong(4, id);
            stmt.executeUpdate();
            logger.info("Updaing of lead id = {} by= {} date ={}", id, updateBy, upddatestr);

        } catch (SQLException sql) {
            sql.printStackTrace();
            logger.error("Error", sql);

        } finally {
            if (defaultClose) {
                try {
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private String loadQueryByType(String type) {

        return "SELECT concat(   \"Source_\",Tbl_LeadSource.name), GROUP_CONCAT(Tbl_LeadSource_user.user_id) as list FROM Tbl_LeadSource_user inner join Tbl_LeadSource on Tbl_LeadSource.id=Tbl_LeadSource_user.lead_source_id GROUP BY Tbl_LeadSource_user.lead_source_id\n" +
                "union all\n" +
                "SELECT concat(   \"Type_\",Tbl_LeadType.name), GROUP_CONCAT(Tbl_LeadType_user.user_id) AS list FROM Tbl_LeadType_user INNER JOIN Tbl_LeadType ON Tbl_LeadType.id = Tbl_LeadType_user.lead_type_id GROUP BY Tbl_LeadType_user.lead_type_id\n" +
                "union all\n" +
                "SELECT concat(  \"Product_\",Tbl_ProductMaster.name), GROUP_CONCAT(Tbl_Product_user.user_id) AS list FROM Tbl_Product_user INNER JOIN Tbl_ProductMaster ON Tbl_ProductMaster.id = Tbl_Product_user.product_id GROUP BY Tbl_Product_user.product_id;";

    }

    @Override
    public HashMap<String, String> getUserByType(String type) throws SQLException {
        HashMap<String, String> hashMap = new HashMap<>();
        Connection con = null;
        try {
            con = dataSource.getConnection();
            PreparedStatement stmt = con.prepareStatement(loadQueryByType(type));
            ResultSet rs = stmt.executeQuery();
            DashboardDao model;
            while (rs.next()) {
                hashMap.put(rs.getString(1), rs.getString(2));
            }
        } catch (SQLException sql) {

        } finally {
            con.close();
        }
        return hashMap;
    }

    // =========== MAINSTREAM ASSESSMENT FORM
    @Override
    public LeadMasterDao updateMainstreamAssessmentForm(LeadMasterDao leadMasterDao){

        Connection con = null;
        try{
            con = dataSource.getConnection();
            PreparedStatement stmt = con.prepareStatement("UPDATE Tbl_LeadMaster set `Tbl_LeadMaster`.`main_Listening1` = ? ,`Tbl_LeadMaster`.`main_Listening2` = ? ,`Tbl_LeadMaster`.`main_Listening3` = ? ,`Tbl_LeadMaster`.`main_Listening4` = ? ,`Tbl_LeadMaster`.`main_Listening5` = ? ,`Tbl_LeadMaster`.`main_Listening6` = ? ,`Tbl_LeadMaster`.`main_Listening7` = ? ,`Tbl_LeadMaster`.`main_Listening8` = ? ,`Tbl_LeadMaster`.`main_Listening9` = ? ,`Tbl_LeadMaster`.`main_Listening10` = ? ,`Tbl_LeadMaster`.`main_Listening11` = ? ,`Tbl_LeadMaster`.`main_Oral_Expression_Speaking1` = ? ,`Tbl_LeadMaster`.`main_Oral_Expression_Speaking2` = ? ,`Tbl_LeadMaster`.`main_Oral_Expression_Speaking3` = ? ,`Tbl_LeadMaster`.`main_Oral_Expression_Speaking4` = ? ,`Tbl_LeadMaster`.`main_Oral_Expression_Speaking5` = ? ,`Tbl_LeadMaster`.`main_Oral_Expression_Speaking6` = ? ,`Tbl_LeadMaster`.`main_Oral_Expression_Speaking7` = ? ,`Tbl_LeadMaster`.`main_Oral_Expression_Speaking8` = ? ,`Tbl_LeadMaster`.`main_Oral_Expression_Speaking9` = ? ,`Tbl_LeadMaster`.`main_Oral_Expression_Speaking10` = ? ,`Tbl_LeadMaster`.`main_Oral_Expression_Speaking11` = ? ,`Tbl_LeadMaster`.`main_Oral_Expression_Speaking12` = ? ,`Tbl_LeadMaster`.`main_Basic_Reading1` = ? ,`Tbl_LeadMaster`.`main_Basic_Reading2` = ? ,`Tbl_LeadMaster`.`main_Basic_Reading3` = ? ,`Tbl_LeadMaster`.`main_Basic_Reading4` = ? ,`Tbl_LeadMaster`.`main_Basic_Reading5` = ? ,`Tbl_LeadMaster`.`main_Basic_Reading6` = ? ,`Tbl_LeadMaster`.`main_Basic_Reading7` = ? ,`Tbl_LeadMaster`.`main_Basic_Reading8` = ? ,`Tbl_LeadMaster`.`main_Basic_Reading9` = ? ,`Tbl_LeadMaster`.`main_Basic_Reading10` = ? ,`Tbl_LeadMaster`.`main_Basic_Reading11` = ? ,`Tbl_LeadMaster`.`main_Basic_Reading12` = ? ,`Tbl_LeadMaster`.`main_Basic_Reading13` = ? ,`Tbl_LeadMaster`.`main_Reading_Comprehension1` = ? ,`Tbl_LeadMaster`.`main_Reading_Comprehension2` = ? ,`Tbl_LeadMaster`.`main_Reading_Comprehension3` = ? ,`Tbl_LeadMaster`.`main_Reading_Comprehension4` = ? ,`Tbl_LeadMaster`.`main_Reading_Comprehension5` = ? ,`Tbl_LeadMaster`.`main_Reading_Comprehension6` = ? ,`Tbl_LeadMaster`.`main_Reading_Comprehension7` = ? ,`Tbl_LeadMaster`.`main_Math_Calculations1` = ? ,`Tbl_LeadMaster`.`main_Math_Calculations2` = ? ,`Tbl_LeadMaster`.`main_Math_Calculations3` = ? ,`Tbl_LeadMaster`.`main_Math_Calculations4` = ? ,`Tbl_LeadMaster`.`main_Math_Calculations5` = ? ,`Tbl_LeadMaster`.`main_Math_Calculations6` = ? ,`Tbl_LeadMaster`.`main_Math_Calculations7` = ? ,`Tbl_LeadMaster`.`main_Math_Calculations8` = ? ,`Tbl_LeadMaster`.`main_Math_Calculations9` = ? ,`Tbl_LeadMaster`.`main_Math_Calculations10` = ? ,`Tbl_LeadMaster`.`main_Math_Reasoning1` = ? ,`Tbl_LeadMaster`.`main_Math_Reasoning2` = ? ,`Tbl_LeadMaster`.`main_Math_Reasoning3` = ? ,`Tbl_LeadMaster`.`main_Math_Reasoning4` = ? ,`Tbl_LeadMaster`.`main_Math_Reasoning5` = ? ,`Tbl_LeadMaster`.`main_Math_Reasoning6` = ? ,`Tbl_LeadMaster`.`main_Math_Reasoning7` = ? ,`Tbl_LeadMaster`.`main_Written_Expression1` = ? ,`Tbl_LeadMaster`.`main_Written_Expression2` = ? ,`Tbl_LeadMaster`.`main_Written_Expression3` = ? ,`Tbl_LeadMaster`.`main_Written_Expression4` = ? ,`Tbl_LeadMaster`.`main_Written_Expression5` = ? ,`Tbl_LeadMaster`.`main_Written_Expression6` = ? ,`Tbl_LeadMaster`.`main_Written_Expression7` = ? ,`Tbl_LeadMaster`.`main_Written_Expression8` = ? ,`Tbl_LeadMaster`.`main_Written_Expression9` = ? ,`Tbl_LeadMaster`.`main_Written_Expression10` = ? ,`Tbl_LeadMaster`.`main_Written_Expression11` = ? ,`Tbl_LeadMaster`.`main_Written_Expression12` = ? ,`Tbl_LeadMaster`.`main_Behavior1` = ? ,`Tbl_LeadMaster`.`main_Behavior2` = ? ,`Tbl_LeadMaster`.`main_Behavior3` = ? ,`Tbl_LeadMaster`.`main_Behavior4` = ? ,`Tbl_LeadMaster`.`main_Behavior5` = ? ,`Tbl_LeadMaster`.`main_Behavior6` = ? ,`Tbl_LeadMaster`.`main_Behavior7` = ? ,`Tbl_LeadMaster`.`main_Behavior8` = ? ,`Tbl_LeadMaster`.`main_Behavior9` = ? ,`Tbl_LeadMaster`.`main_Behavior10` = ? ,`Tbl_LeadMaster`.`main_Behavior11` = ? ,`Tbl_LeadMaster`.`main_Behavior12` = ? ,`Tbl_LeadMaster`.`main_Behavior13` = ? ,`Tbl_LeadMaster`.`main_Behavior14` = ? ,`Tbl_LeadMaster`.`main_Behavior15` =? , `Tbl_LeadMaster`.`leadScore` = ?,`Tbl_LeadMaster`.`mainlistening` = ?,`Tbl_LeadMaster`.`mainloralexpression` = ?,`Tbl_LeadMaster`.`mainbasicreading` = ?,`Tbl_LeadMaster`.`mainreadingcomprehension` = ?,`Tbl_LeadMaster`.`mainmathcalculations` = ?,`Tbl_LeadMaster`.`mainmathreasoning` = ?,`Tbl_LeadMaster`.`mainwrittenexpression` = ?,`Tbl_LeadMaster`.`mainbehavior` = ? , `Tbl_LeadMaster`.`updateDate` = ?, `Tbl_LeadMaster`.`updatedatefilter` = ?, `Tbl_LeadMaster`.`updateBy` = ?,`Tbl_LeadMaster`.`assessmentDate` = ?,`Tbl_LeadMaster`.`assessmentDateFilter` = ? WHERE id = ?");

            stmt.setString(1,leadMasterDao.getMain_Listening1());
            stmt.setString(2,leadMasterDao.getMain_Listening2());
            stmt.setString(3,leadMasterDao.getMain_Listening3());
            stmt.setString(4,leadMasterDao.getMain_Listening4());
            stmt.setString(5,leadMasterDao.getMain_Listening5());
            stmt.setString(6,leadMasterDao.getMain_Listening6());
            stmt.setString(7,leadMasterDao.getMain_Listening7());
            stmt.setString(8,leadMasterDao.getMain_Listening8());
            stmt.setString(9,leadMasterDao.getMain_Listening9());
            stmt.setString(10,leadMasterDao.getMain_Listening10());
            stmt.setString(11,leadMasterDao.getMain_Listening11());
            stmt.setString(12,leadMasterDao.getMain_Oral_Expression_Speaking1());
            stmt.setString(13,leadMasterDao.getMain_Oral_Expression_Speaking2());
            stmt.setString(14,leadMasterDao.getMain_Oral_Expression_Speaking3());
            stmt.setString(15,leadMasterDao.getMain_Oral_Expression_Speaking4());
            stmt.setString(16,leadMasterDao.getMain_Oral_Expression_Speaking5());
            stmt.setString(17,leadMasterDao.getMain_Oral_Expression_Speaking6());
            stmt.setString(18,leadMasterDao.getMain_Oral_Expression_Speaking7());
            stmt.setString(19,leadMasterDao.getMain_Oral_Expression_Speaking8());
            stmt.setString(20,leadMasterDao.getMain_Oral_Expression_Speaking9());
            stmt.setString(21,leadMasterDao.getMain_Oral_Expression_Speaking10());
            stmt.setString(22,leadMasterDao.getMain_Oral_Expression_Speaking11());
            stmt.setString(23,leadMasterDao.getMain_Oral_Expression_Speaking12());
            stmt.setString(24,leadMasterDao.getMain_Basic_Reading1());
            stmt.setString(25,leadMasterDao.getMain_Basic_Reading2());
            stmt.setString(26,leadMasterDao.getMain_Basic_Reading3());
            stmt.setString(27,leadMasterDao.getMain_Basic_Reading4());
            stmt.setString(28,leadMasterDao.getMain_Basic_Reading5());
            stmt.setString(29,leadMasterDao.getMain_Basic_Reading6());
            stmt.setString(30,leadMasterDao.getMain_Basic_Reading7());
            stmt.setString(31,leadMasterDao.getMain_Basic_Reading8());
            stmt.setString(32,leadMasterDao.getMain_Basic_Reading9());
            stmt.setString(33,leadMasterDao.getMain_Basic_Reading10());
            stmt.setString(34,leadMasterDao.getMain_Basic_Reading11());
            stmt.setString(35,leadMasterDao.getMain_Basic_Reading12());
            stmt.setString(36,leadMasterDao.getMain_Basic_Reading13());
            stmt.setString(37,leadMasterDao.getMain_Reading_Comprehension1());
            stmt.setString(38,leadMasterDao.getMain_Reading_Comprehension2());
            stmt.setString(39,leadMasterDao.getMain_Reading_Comprehension3());
            stmt.setString(40,leadMasterDao.getMain_Reading_Comprehension4());
            stmt.setString(41,leadMasterDao.getMain_Reading_Comprehension5());
            stmt.setString(42,leadMasterDao.getMain_Reading_Comprehension6());
            stmt.setString(43,leadMasterDao.getMain_Reading_Comprehension7());
            stmt.setString(44,leadMasterDao.getMain_Math_Calculations1());
            stmt.setString(45,leadMasterDao.getMain_Math_Calculations2());
            stmt.setString(46,leadMasterDao.getMain_Math_Calculations3());
            stmt.setString(47,leadMasterDao.getMain_Math_Calculations4());
            stmt.setString(48,leadMasterDao.getMain_Math_Calculations5());
            stmt.setString(49,leadMasterDao.getMain_Math_Calculations6());
            stmt.setString(50,leadMasterDao.getMain_Math_Calculations7());
            stmt.setString(51,leadMasterDao.getMain_Math_Calculations8());
            stmt.setString(52,leadMasterDao.getMain_Math_Calculations9());
            stmt.setString(53,leadMasterDao.getMain_Math_Calculations10());
            stmt.setString(54,leadMasterDao.getMain_Math_Reasoning1());
            stmt.setString(55,leadMasterDao.getMain_Math_Reasoning2());
            stmt.setString(56,leadMasterDao.getMain_Math_Reasoning3());
            stmt.setString(57,leadMasterDao.getMain_Math_Reasoning4());
            stmt.setString(58,leadMasterDao.getMain_Math_Reasoning5());
            stmt.setString(59,leadMasterDao.getMain_Math_Reasoning6());
            stmt.setString(60,leadMasterDao.getMain_Math_Reasoning7());
            stmt.setString(61,leadMasterDao.getMain_Written_Expression1());
            stmt.setString(62,leadMasterDao.getMain_Written_Expression2());
            stmt.setString(63,leadMasterDao.getMain_Written_Expression3());
            stmt.setString(64,leadMasterDao.getMain_Written_Expression4());
            stmt.setString(65,leadMasterDao.getMain_Written_Expression5());
            stmt.setString(66,leadMasterDao.getMain_Written_Expression6());
            stmt.setString(67,leadMasterDao.getMain_Written_Expression7());
            stmt.setString(68,leadMasterDao.getMain_Written_Expression8());
            stmt.setString(69,leadMasterDao.getMain_Written_Expression9());
            stmt.setString(70,leadMasterDao.getMain_Written_Expression10());
            stmt.setString(71,leadMasterDao.getMain_Written_Expression11());
            stmt.setString(72,leadMasterDao.getMain_Written_Expression12());
            stmt.setString(73,leadMasterDao.getMain_Behavior1());
            stmt.setString(74,leadMasterDao.getMain_Behavior2());
            stmt.setString(75,leadMasterDao.getMain_Behavior3());
            stmt.setString(76,leadMasterDao.getMain_Behavior4());
            stmt.setString(77,leadMasterDao.getMain_Behavior5());
            stmt.setString(78,leadMasterDao.getMain_Behavior6());
            stmt.setString(79,leadMasterDao.getMain_Behavior7());
            stmt.setString(80,leadMasterDao.getMain_Behavior8());
            stmt.setString(81,leadMasterDao.getMain_Behavior9());
            stmt.setString(82,leadMasterDao.getMain_Behavior10());
            stmt.setString(83,leadMasterDao.getMain_Behavior11());
            stmt.setString(84,leadMasterDao.getMain_Behavior12());
            stmt.setString(85,leadMasterDao.getMain_Behavior13());
            stmt.setString(86,leadMasterDao.getMain_Behavior14());
            stmt.setString(87,leadMasterDao.getMain_Behavior15());
            stmt.setInt(88,leadMasterDao.getLeadScore());

            stmt.setInt(89,leadMasterDao.getMainlistening());
            stmt.setInt(90,leadMasterDao.getMainloralexpression());
            stmt.setInt(91,leadMasterDao.getMainbasicreading());
            stmt.setInt(92,leadMasterDao.getMainreadingcomprehension());
            stmt.setInt(93,leadMasterDao.getMainmathcalculations());
            stmt.setInt(94,leadMasterDao.getMainmathreasoning());
            stmt.setInt(95,leadMasterDao.getMainwrittenexpression());
            stmt.setInt(96,leadMasterDao.getMainbehavior());

            stmt.setString(97,leadMasterDao.getUpdateDate());
            stmt.setInt(98,leadMasterDao.getUpdatedatefilter());
            stmt.setString(99,leadMasterDao.getUpdateBy());

            stmt.setString(100,UtilityClass.getDateRedable());
            stmt.setInt(101,UtilityClass.dateFilterDate());

            stmt.setLong(102,leadMasterDao.getId());

            logger.info("Updating Mainstream assessment form >>>> {}",stmt);
            stmt.executeUpdate();

        }catch (Exception ex){
            logger.info("Exception while updating mainstream assessment form : {}",ex);
        }finally {
            try{
                con.close();
            }catch (SQLException sql){
                sql.printStackTrace();
            }
        }

        return leadMasterDao;
    }

    //============== PRE SCHOOL ASSESSMNET FORM

    @Override
    public LeadMasterDao updatePreschoolAssessmentForm(LeadMasterDao leadMasterDao){

        Connection con = null;
        try{
            String assessmentDate = UtilityClass.getDateRedable();
            int assessmentDateFilter = UtilityClass.dateFilterDate();
            con = dataSource.getConnection();
            PreparedStatement stmt = con.prepareStatement("UPDATE Tbl_LeadMaster set `Tbl_LeadMaster`.`pre_Gross_Motor_Skills1` = ? ,`Tbl_LeadMaster`.`pre_Gross_Motor_Skills2` = ? ,`Tbl_LeadMaster`.`pre_Gross_Motor_Skills3` = ? ,`Tbl_LeadMaster`.`pre_Gross_Motor_Skills4` = ? ,`Tbl_LeadMaster`.`pre_Gross_Motor_Skills5` = ? ,`Tbl_LeadMaster`.`pre_Gross_Motor_Skills6` = ? ,`Tbl_LeadMaster`.`pre_Fine_motor_Skills1` = ? ,`Tbl_LeadMaster`.`pre_Fine_motor_Skills2` = ? ,`Tbl_LeadMaster`.`pre_Fine_motor_Skills3` = ? ,`Tbl_LeadMaster`.`pre_Fine_motor_Skills4` = ? ,`Tbl_LeadMaster`.`pre_Fine_motor_Skills5` = ? ,`Tbl_LeadMaster`.`pre_Fine_motor_Skills6` = ? ,`Tbl_LeadMaster`.`pre_Fine_motor_Skills7` = ? ,`Tbl_LeadMaster`.`pre_Fine_motor_Skills8` = ? ,`Tbl_LeadMaster`.`pre_Fine_motor_Skills9` = ? ,`Tbl_LeadMaster`.`pre_Fine_motor_Skills10` = ? ,`Tbl_LeadMaster`.`pre_Self_help1` = ? ,`Tbl_LeadMaster`.`pre_Self_help2` = ? ,`Tbl_LeadMaster`.`pre_Self_help3` = ? ,`Tbl_LeadMaster`.`pre_Self_help4` = ? ,`Tbl_LeadMaster`.`pre_Self_help5` = ? ,`Tbl_LeadMaster`.`pre_Self_help6` = ? ,`Tbl_LeadMaster`.`pre_Self_help7` = ? ,`Tbl_LeadMaster`.`pre_Self_help8` = ? ,`Tbl_LeadMaster`.`pre_Self_help9` = ? ,`Tbl_LeadMaster`.`pre_Self_help10` = ? ,`Tbl_LeadMaster`.`pre_Social_Emotional1` = ? ,`Tbl_LeadMaster`.`pre_Social_Emotional2` = ? ,`Tbl_LeadMaster`.`pre_Social_Emotional3` = ? ,`Tbl_LeadMaster`.`pre_Social_Emotional4` = ? ,`Tbl_LeadMaster`.`pre_Social_Emotional5` = ? ,`Tbl_LeadMaster`.`pre_Social_Emotional6` = ? ,`Tbl_LeadMaster`.`pre_Social_Emotional7` = ? ,`Tbl_LeadMaster`.`pre_Social_Emotional8` = ? ,`Tbl_LeadMaster`.`pre_Social_Emotional9` = ? ,`Tbl_LeadMaster`.`pre_Social_Emotional10` = ? ,`Tbl_LeadMaster`.`pre_Cognitive1` = ? ,`Tbl_LeadMaster`.`pre_Cognitive2` = ? ,`Tbl_LeadMaster`.`pre_Cognitive3` = ? ,`Tbl_LeadMaster`.`pre_Cognitive4` = ? ,`Tbl_LeadMaster`.`pre_Cognitive5` = ? ,`Tbl_LeadMaster`.`pre_Cognitive6` = ? ,`Tbl_LeadMaster`.`pre_Cognitive7` = ? ,`Tbl_LeadMaster`.`pre_Cognitive8` = ? ,`Tbl_LeadMaster`.`pre_Cognitive9` = ? ,`Tbl_LeadMaster`.`pre_Cognitive10` = ? ,`Tbl_LeadMaster`.`pre_Cognitive11` = ? ,`Tbl_LeadMaster`.`pre_Communication1` = ? ,`Tbl_LeadMaster`.`pre_Communication2` = ? ,`Tbl_LeadMaster`.`pre_Communication3` = ? ,`Tbl_LeadMaster`.`pre_Communication4` = ? ,`Tbl_LeadMaster`.`pre_Communication5` = ? ,`Tbl_LeadMaster`.`pre_Communication6` = ? ,`Tbl_LeadMaster`.`pre_Communication7` = ? ,`Tbl_LeadMaster`.`pre_Communication8` = ? ,`Tbl_LeadMaster`.`pre_Communication9` = ? ,`Tbl_LeadMaster`.`pre_Communication10` = ? ,`Tbl_LeadMaster`.`pre_Communication11` = ? ,`Tbl_LeadMaster`.`pre_Communication12` = ? ,`Tbl_LeadMaster`.`pre_Communication13` = ? ,`Tbl_LeadMaster`.`pre_Communication14` = ? ,`Tbl_LeadMaster`.`pre_Communication15` = ? ,`Tbl_LeadMaster`.`pre_Communication16` = ? ,`Tbl_LeadMaster`.`pre_Communication17` = ? ,`Tbl_LeadMaster`.`pre_Communication18` = ? ,`Tbl_LeadMaster`.`pre_Behaviors1` = ? ,`Tbl_LeadMaster`.`pre_Behaviors2` = ? ,`Tbl_LeadMaster`.`pre_Behaviors3` = ? ,`Tbl_LeadMaster`.`pre_Behaviors4` = ? ,`Tbl_LeadMaster`.`pre_Behaviors5` = ? ,`Tbl_LeadMaster`.`pre_Behaviors6` = ? , `Tbl_LeadMaster`.`leadScore` = ?,`Tbl_LeadMaster`.`pregrossmotorskills` = ?,`Tbl_LeadMaster`.`prefinemotorskills` = ?,`Tbl_LeadMaster`.`preselfhelp` = ?,`Tbl_LeadMaster`.`presocialemotional` = ?,`Tbl_LeadMaster`.`precognitive` = ?,`Tbl_LeadMaster`.`precommunication` = ?,`Tbl_LeadMaster`.`prebehaviors` = ? , `Tbl_LeadMaster`.`updateDate` = ?, `Tbl_LeadMaster`.`updatedatefilter` = ?, `Tbl_LeadMaster`.`updateBy` = ?,`Tbl_LeadMaster`.`assessmentDate` = ?,`Tbl_LeadMaster`.`assessmentDateFilter` = ? WHERE id = ? ");
            stmt.setString(1,leadMasterDao.getPre_Gross_Motor_Skills1());
            stmt.setString(2,leadMasterDao.getPre_Gross_Motor_Skills2());
            stmt.setString(3,leadMasterDao.getPre_Gross_Motor_Skills3());
            stmt.setString(4,leadMasterDao.getPre_Gross_Motor_Skills4());
            stmt.setString(5,leadMasterDao.getPre_Gross_Motor_Skills5());
            stmt.setString(6,leadMasterDao.getPre_Gross_Motor_Skills6());
            stmt.setString(7,leadMasterDao.getPre_Fine_motor_Skills1());
            stmt.setString(8,leadMasterDao.getPre_Fine_motor_Skills2());
            stmt.setString(9,leadMasterDao.getPre_Fine_motor_Skills3());
            stmt.setString(10,leadMasterDao.getPre_Fine_motor_Skills4());
            stmt.setString(11,leadMasterDao.getPre_Fine_motor_Skills5());
            stmt.setString(12,leadMasterDao.getPre_Fine_motor_Skills6());
            stmt.setString(13,leadMasterDao.getPre_Fine_motor_Skills7());
            stmt.setString(14,leadMasterDao.getPre_Fine_motor_Skills8());
            stmt.setString(15,leadMasterDao.getPre_Fine_motor_Skills9());
            stmt.setString(16,leadMasterDao.getPre_Fine_motor_Skills10());
            stmt.setString(17,leadMasterDao.getPre_Self_help1());
            stmt.setString(18,leadMasterDao.getPre_Self_help2());
            stmt.setString(19,leadMasterDao.getPre_Self_help3());
            stmt.setString(20,leadMasterDao.getPre_Self_help4());
            stmt.setString(21,leadMasterDao.getPre_Self_help5());
            stmt.setString(22,leadMasterDao.getPre_Self_help6());
            stmt.setString(23,leadMasterDao.getPre_Self_help7());
            stmt.setString(24,leadMasterDao.getPre_Self_help8());
            stmt.setString(25,leadMasterDao.getPre_Self_help9());
            stmt.setString(26,leadMasterDao.getPre_Self_help10());
            stmt.setString(27,leadMasterDao.getPre_Social_Emotional1());
            stmt.setString(28,leadMasterDao.getPre_Social_Emotional2());
            stmt.setString(29,leadMasterDao.getPre_Social_Emotional3());
            stmt.setString(30,leadMasterDao.getPre_Social_Emotional4());
            stmt.setString(31,leadMasterDao.getPre_Social_Emotional5());
            stmt.setString(32,leadMasterDao.getPre_Social_Emotional6());
            stmt.setString(33,leadMasterDao.getPre_Social_Emotional7());
            stmt.setString(34,leadMasterDao.getPre_Social_Emotional8());
            stmt.setString(35,leadMasterDao.getPre_Social_Emotional9());
            stmt.setString(36,leadMasterDao.getPre_Social_Emotional10());
            stmt.setString(37,leadMasterDao.getPre_Cognitive1());
            stmt.setString(38,leadMasterDao.getPre_Cognitive2());
            stmt.setString(39,leadMasterDao.getPre_Cognitive3());
            stmt.setString(40,leadMasterDao.getPre_Cognitive4());
            stmt.setString(41,leadMasterDao.getPre_Cognitive5());
            stmt.setString(42,leadMasterDao.getPre_Cognitive6());
            stmt.setString(43,leadMasterDao.getPre_Cognitive7());
            stmt.setString(44,leadMasterDao.getPre_Cognitive8());
            stmt.setString(45,leadMasterDao.getPre_Cognitive9());
            stmt.setString(46,leadMasterDao.getPre_Cognitive10());
            stmt.setString(47,leadMasterDao.getPre_Cognitive11());
            stmt.setString(48,leadMasterDao.getPre_Communication1());
            stmt.setString(49,leadMasterDao.getPre_Communication2());
            stmt.setString(50,leadMasterDao.getPre_Communication3());
            stmt.setString(51,leadMasterDao.getPre_Communication4());
            stmt.setString(52,leadMasterDao.getPre_Communication5());
            stmt.setString(53,leadMasterDao.getPre_Communication6());
            stmt.setString(54,leadMasterDao.getPre_Communication7());
            stmt.setString(55,leadMasterDao.getPre_Communication8());
            stmt.setString(56,leadMasterDao.getPre_Communication9());
            stmt.setString(57,leadMasterDao.getPre_Communication10());
            stmt.setString(58,leadMasterDao.getPre_Communication11());
            stmt.setString(59,leadMasterDao.getPre_Communication12());
            stmt.setString(60,leadMasterDao.getPre_Communication13());
            stmt.setString(61,leadMasterDao.getPre_Communication14());
            stmt.setString(62,leadMasterDao.getPre_Communication15());
            stmt.setString(63,leadMasterDao.getPre_Communication16());
            stmt.setString(64,leadMasterDao.getPre_Communication17());
            stmt.setString(65,leadMasterDao.getPre_Communication18());
            stmt.setString(66,leadMasterDao.getPre_Behaviors1());
            stmt.setString(67,leadMasterDao.getPre_Behaviors2());
            stmt.setString(68,leadMasterDao.getPre_Behaviors3());
            stmt.setString(69,leadMasterDao.getPre_Behaviors4());
            stmt.setString(70,leadMasterDao.getPre_Behaviors5());
            stmt.setString(71,leadMasterDao.getPre_Behaviors6());
            stmt.setInt(72,leadMasterDao.getLeadScore());

            stmt.setInt(73,leadMasterDao.getPregrossmotorskills());
            stmt.setInt(74,leadMasterDao.getPrefinemotorskills());
            stmt.setInt(75,leadMasterDao.getPreselfhelp());
            stmt.setInt(76,leadMasterDao.getPresocialemotional());
            stmt.setInt(77,leadMasterDao.getPrecognitive());
            stmt.setInt(78,leadMasterDao.getPrecommunication());
            stmt.setInt(79,leadMasterDao.getPrebehaviors());

            stmt.setString(80,leadMasterDao.getUpdateDate());
            stmt.setInt(81,leadMasterDao.getUpdatedatefilter());
            stmt.setString(82,leadMasterDao.getUpdateBy());

            stmt.setString(83,assessmentDate);
            stmt.setInt(84,assessmentDateFilter);

            stmt.setLong(85,leadMasterDao.getId());

            logger.info("Updating Preschool assessment form >>> {}",stmt);
            stmt.executeUpdate();


        }catch (Exception ex){
            logger.error("Exception while updating preschool assessment form : {}",ex);
        }finally {
            try{
                con.close();
            }catch (SQLException sql){
                sql.printStackTrace();
            }
        }

        return leadMasterDao;

    }

    @Override
    public List<ServiceReviewDao> getAll(String whereQuery) {

        StringBuilder sb = new StringBuilder();
        sb.append("SELECT `Tbl_LeadMaster`.`parentName`,");
        sb.append("`Tbl_LeadMaster`.`childName`,");
        sb.append("`Tbl_LeadMaster`.`phonenumber`,");
        sb.append("`Tbl_LeadMaster`.`email`,");
        sb.append("`Tbl_LeadMaster`.`city`,");
        sb.append("`Tbl_LeadMaster`.`state`,");
        sb.append("`Tbl_LeadMaster`.`clientType`,");
        sb.append("`Tbl_LeadMaster`.`leadStage`,");
        sb.append("`Tbl_LeadMaster`.`leadStatus`, ");
        sb.append("`Tbl_ServiceReview`.`id`,");
        sb.append("`Tbl_ServiceReview`.`facingAnyChallenges`,");
        sb.append("`Tbl_ServiceReview`.`howsChildDoing`,");
        sb.append("`Tbl_ServiceReview`.`impactScore`,");
        sb.append("`Tbl_ServiceReview`.`interestedInCounsellor`,");
        sb.append("`Tbl_ServiceReview`.`recommendationScore`,");
        sb.append("`Tbl_ServiceReview`.`suggestedService1`,");
        sb.append("`Tbl_ServiceReview`.`suggestedService2`,");
        sb.append("`Tbl_ServiceReview`.`suggestedService3`,");
        sb.append("`Tbl_ServiceReview`.`therapistScore`,");
        sb.append("`Tbl_ServiceReview`.`createDate`,");
        sb.append("`Tbl_ServiceReview`.`createDateFilter`,");
        sb.append("`Tbl_ServiceReview`.`leadId`,");
        sb.append("`Tbl_ServiceReview`.`updateDate`,");
        sb.append("`Tbl_ServiceReview`.`updateDateFilter`,");
        sb.append("`Tbl_ServiceReview`.`createBy`,");
        sb.append("`Tbl_ServiceReview`.`updateBy` ");
        sb.append("FROM `Tbl_ServiceReview`");
        sb.append("inner join `Tbl_LeadMaster` on `Tbl_ServiceReview`.`leadId` = `Tbl_LeadMaster`.`id` where");

        String query = sb.toString();
        query = query.replace("where",whereQuery);

        List<ServiceReviewDao> list = null;
        Connection con = null;
        try{
            con = dataSource.getConnection();
            PreparedStatement stmt = con.prepareStatement(query);
            logger.info("Service Review Repot Query : {}",query);
            ResultSet rs = stmt.executeQuery();
            list = new ArrayList<>();
            ServiceReviewDao dao = null;
            while (rs.next()){
                dao = new ServiceReviewDao();
                //=== LEAD FIELDS
                dao.setParentName(rs.getString(1));
                dao.setChildName(rs.getString(2));
                dao.setPhonenumber(rs.getString(3));
                dao.setEmail(rs.getString(4));
                dao.setCity(rs.getString(5));
                dao.setState(rs.getString(6));
                dao.setClientType(rs.getString(7));
                dao.setLeadStage(rs.getString(8));
                dao.setLeadStatus(rs.getString(9));

                //=== SERVICE REVIEW DAO
                dao.setId(rs.getLong(10));
                dao.setFacingAnyChallenges(rs.getString(11));
                dao.setHowsChildDoing(rs.getString(12));
                dao.setImpactScore(rs.getByte(13));
                dao.setInterestedInCounsellor(rs.getByte(14));
                dao.setRecommendationScore(rs.getByte(15));
                dao.setSuggestedService1(rs.getString(16));
                dao.setSuggestedService2(rs.getString(17));
                dao.setSuggestedService3(rs.getString(18));
                dao.setTherapistScore(rs.getByte(19));
                dao.setCreateDate(rs.getString(20));
                dao.setCreateDateFilter(rs.getInt(21));
                dao.setLeadId(rs.getLong(22));
                dao.setUpdateDate(rs.getString(23));
                dao.setUpdateDateFilter(rs.getInt(24));
                dao.setCreateBy(rs.getString(25));
                dao.setUpdateBy(rs.getString(26));

                list.add(dao);
            }

        }catch (SQLException ex){

        }finally {
            try{
                con.close();
            }catch (SQLException ex){

            }

        }
        return list;
    }

    @Override
    public void bulkSourceChange(List<Long> leadIdList, String newSource,UserMasterDao user) {

        logger.info("Updating of leadSource to {}  for total {} leads ", newSource,leadIdList.size());
        Connection con=null;
        try {
            con = dataSource.getConnection();

            PreparedStatement stmt = con.prepareStatement("update Tbl_LeadMaster set leadSource=? where id=?;");
            List<LogEventDao> eventDaoList = new ArrayList<>();
            LogEventDao logEventDao=null;
            for (Long leadId:leadIdList){
                logEventDao = new LogEventDao();
                logEventDao.setLeadId(leadId);
                logEventDao.setCreateAt(UtilityClass.getDateRedable());
                logEventDao.setUpdateAt(UtilityClass.getDateRedable());
                logEventDao.setCreateBy(user.getUsername());
                logEventDao.setMessage("Changed Source to "+newSource);
                logEventDao.setEventType("Bulk Update");
                eventDaoList.add(logEventDao);

                stmt.setString(1, newSource);
                stmt.setLong(2, leadId);
                stmt.addBatch();
            }
            logEventService.insertMany(eventDaoList);
            stmt.executeBatch();
            } catch (SQLException sql) {
            sql.printStackTrace();
            logger.error("Error", sql);

        } finally {

                try {
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }

        }

    }

    @Override
    public void bulkProgramChange(List<Long> leadIdList, String newProgram,UserMasterDao user) {
        logger.info("Updating program to {}  for total {} leads ", newProgram,leadIdList.size());
        Connection con=null;
        try {
            con = dataSource.getConnection();
            PreparedStatement stmt = con.prepareStatement("update Tbl_LeadMaster set interestedProduct=? where id=?;");
            List<LogEventDao> eventDaoList = new ArrayList<>();
            LogEventDao logEventDao=null;

            for (Long leadId:leadIdList){

                logEventDao = new LogEventDao();
                logEventDao.setLeadId(leadId);
                logEventDao.setCreateAt(UtilityClass.getDateRedable());
                logEventDao.setUpdateAt(UtilityClass.getDateRedable());
                logEventDao.setCreateBy(user.getUsername());
                logEventDao.setMessage("Changed Program to "+newProgram);
                logEventDao.setEventType("Bulk Update");
                eventDaoList.add(logEventDao);

                stmt.setString(1, newProgram);
                stmt.setLong(2, leadId);
                stmt.addBatch();
            }
            stmt.executeBatch();
            logEventService.insertMany(eventDaoList);
        } catch (SQLException sql) {
            sql.printStackTrace();
            logger.error("Error", sql);

        } finally {

            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
    }

    @Override
    public LeadMasterDao upadatesAssessmentNotes(long id, String notes) {
        LeadMasterDao leadMasterDao=findById(id);
        leadMasterDao.setAssessmentNotes(notes);
        leadMasterDao.setUpdateDate(UtilityClass.getDateRedable());
        leadMasterDao.setUpdatedatefilter(UtilityClass.dateFilterDate());
        return  leadMasterRepo.save(leadMasterDao);
    }


    @Override
    public ResponseDao optStatus(String phoneNumber, byte otpStatus) {

        ResponseDao  dao=null;
        LeadMasterDao leadMasterDao= findByPhonenumber(phoneNumber);

        try {
            dao= new ResponseDao();
            leadMasterDao.setOtpStatus(otpStatus);
            leadMasterRepo.save(leadMasterDao);
            dao.setMsg("successfull updated");
            dao.setStatusCode(200);
            dao.setId(leadMasterDao.getId());
        }
        catch (Exception e){

            dao= new ResponseDao();
            logger.info("LeadMasterServiceImpl Error Occured :: {}",e.getMessage());
            e.printStackTrace();
            dao.setStatusCode(500);
            dao.setMsg("Something went wrong");

        }


        logger.info("After verified otp leadMasterDao:: {}" + leadMasterDao);
        return  dao;
    }

}
