package com.example.maxcrm.MaxCrm.Controller;

import com.example.maxcrm.MaxCrm.CombinePackage.Wati.WatiDaos.CustomParamDao;
import com.example.maxcrm.MaxCrm.CombinePackage.Wati.WatiDaos.WatiReceiverDao;
import com.example.maxcrm.MaxCrm.CombinePackage.Wati.WatiDaos.WatiTemplateMsgRequestDao;
import com.example.maxcrm.MaxCrm.CombinePackage.Wati.WatiService;
import com.example.maxcrm.MaxCrm.Dao.*;
import com.example.maxcrm.MaxCrm.Service.*;
import com.example.maxcrm.MaxCrm.Utility.UtilityClass;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RequestMapping("/api/campaign")
@RestController
public class CampaignDocumentController {
    Logger logger = LoggerFactory.getLogger(CampaignDocumentController.class);
    @Autowired
    CampaignDocumentService campaignDocumentService;
    @Autowired
    TemplateDocumentService templateDocumentService;
    @Autowired
    UrlShortnerService urlShortnerService;
    @Autowired
    CounterService counterService;
    @Autowired
    LeadMasterService leadMasterService;
    @Autowired
    TextMessageService textMessageService;
    @Autowired
    MailObjectService mailObjectService;
    @Autowired
    CampaignTransactionService campaignTransactionService;
    @Autowired
    UserMasterService userMasterService;
    @Autowired
    WhatsappService whatsappService;
    @Autowired
    private WatiService watiService;


    private String convertString(String content, LeadMasterDao leadMasterDao, HashMap<String, String> hashMap) {
        content = content.replaceAll("@parentName", leadMasterDao.getParentName());
        content = content.replaceAll("@childName", leadMasterDao.getChildName());
        content = content.replaceAll("@phonenumber", leadMasterDao.getParentName());
        content = content.replaceAll("@state", leadMasterDao.getParentName());
        content = content.replaceAll("@city", leadMasterDao.getParentName());
        content = content.replaceAll("@product", leadMasterDao.getParentName());
        for (String key : hashMap.keySet()) {

            content = content.replaceAll(key, hashMap.get(key));

        }
        return content;
    }

    private String convertString(String content, CampaignObjectDao campaignObjectDao) {
        content = content.replaceAll("@parentName", campaignObjectDao.getParentName());
        content = content.replaceAll("@childName", campaignObjectDao.getChildName());
        content = content.replaceAll("@phonenumber", campaignObjectDao.getParentName());
        content = content.replaceAll("@state", campaignObjectDao.getParentName());
        content = content.replaceAll("@city", campaignObjectDao.getParentName());
        content = content.replaceAll("@product", campaignObjectDao.getParentName());
        content = content.replaceAll("@url", campaignObjectDao.getUrl());

        return content;
    }

    @PostMapping("/pushcampaignshort")
    public int pushcampaign(@RequestParam("templateId") String templateId, @RequestParam("id") long leadId, @RequestParam("params") HashMap<String, String> hashMap) throws Exception {

        TemplateDocument templateDocument = templateDocumentService.findById(templateId);//find by templateId from templateDocumentDao
        LeadMasterDao leadMasterDao = leadMasterService.findById(leadId);//find by leadId from leadmasterDao
        templateDocument.setTemplate(convertString(templateDocument.getTemplate(), leadMasterDao, hashMap));

        if (templateDocument.getTemplateType().equalsIgnoreCase("Mail")) {
            List<MailObjectDao> al = new ArrayList<>();
            MailObjectDao mailObjectDao = new MailObjectDao();

            MailObjectMessage mailObjectMessage = new MailObjectMessage();
            mailObjectMessage.setRecipient(leadMasterDao.getEmail());//set email from leadMasterDao
            mailObjectMessage.setSubject(templateDocument.getTemplateSubject());//set subject from templateDocument
            mailObjectMessage.setCustRef("ASdad");

            mailObjectDao.setMessage(mailObjectMessage);
            MailObjectTemplate mailObjectTemplate = new MailObjectTemplate();
            mailObjectTemplate.setTemplateId(templateDocument.getTemplateIdHidden());
            mailObjectTemplate.setTemplateValues(leadMasterDao.convertToObjectMail());
            mailObjectDao.setTemplate(mailObjectTemplate);
            al.add(mailObjectDao);
            mailObjectService.bulkInsertMail(al);

        } else if (templateDocument.getTemplateType().equalsIgnoreCase("Message")) {
            TextMessageSimpleDao textMessageSimpleDao = new TextMessageSimpleDao();
            textMessageSimpleDao.setContent(templateDocument.getTemplate());
            textMessageSimpleDao.setDst(leadMasterDao.getPhonenumber());
            String senderId = UtilityClass.propertyService.findProperty("Campaign", "SenderId");
            textMessageSimpleDao.setSenderId(senderId);
            textMessageService.sendTextMessageSingle(textMessageSimpleDao);

        } else if (templateDocument.getTemplateType().equalsIgnoreCase("Whatsapp")) {

            List<WhatsappDao> al = new ArrayList<>();
            WhatsappDao whatsappDao = new WhatsappDao();
            whatsappDao.setContent(templateDocument.getTemplate());
            whatsappDao.setContentType(templateDocument.getContentType());
            whatsappDao.setFileName("asd");
            whatsappDao.setCaption(templateDocument.getTemplateSubject());
            whatsappDao.setPhone(getPhonenumber(leadMasterDao.getPhonenumber()));
            al.add(whatsappDao);
        }

        return 200;

    }

    public String getPhonenumber(String phonenumber) {
        String[] ar = phonenumber.split("-");
        if (ar.length > 1) {
            return phonenumber;
        } else {
            return "91-" + phonenumber;
        }

    }

    @GetMapping("/getbycreateby")
    public List<CampaignDocument> findAllByCreateBy(@RequestParam("id") String id) {
        return campaignDocumentService.findAllByCreateBy(id);
    }


    private String buildQuery(String statusValue, String leadSource, String product, String leadType, String userFilter, boolean datefilter, String datevalue, int userId, String searchvalue, String leadStage, String innersource, String datefiltertype) {
        StringBuilder sb = new StringBuilder();
        sb.append("where ");
        if (!statusValue.equalsIgnoreCase("0")) {
            sb.append("Tbl_LeadMaster.leadStatus='");
            sb.append(statusValue);
            sb.append("' and ");
        }
        if (!leadSource.equalsIgnoreCase("0")) {
            sb.append("Tbl_LeadMaster.leadSource='");
            sb.append(leadSource);
            sb.append("' and ");
        }
        if (!product.equalsIgnoreCase("0")) {
            sb.append("Tbl_LeadMaster.interestedProduct='");
            sb.append(product);
            sb.append("' and ");
        }
        if (!leadType.equalsIgnoreCase("0")) {
            sb.append("Tbl_LeadMaster.clientType='");
            sb.append(leadType);
            sb.append("' and ");
        }
        if (!leadStage.equalsIgnoreCase("0")) {
            sb.append("Tbl_LeadMaster.leadStage='");
            sb.append(leadStage);
            sb.append("' and ");
        }
        if (!innersource.equalsIgnoreCase("0")) {
            sb.append("Tbl_LeadMaster.leadsourceinner='");
            sb.append(innersource);
            sb.append("' and ");
        }
        if (datefilter == true) {
            if (datefiltertype.equalsIgnoreCase("createdate")) {
                String[] arr = datevalue.split("@");
                sb.append("( Tbl_LeadMaster.dateFilter >=");
                sb.append(arr[0].replaceAll("-", ""));
                sb.append(" and Tbl_LeadMaster.dateFilter<=");
                sb.append(arr[1].replaceAll("-", ""));
                sb.append(" )");
                sb.append(" and ");
            } else if (datefiltertype.equalsIgnoreCase("convertdate")) {
                String[] arr = datevalue.split("@");
                sb.append("( Tbl_LeadMaster.convertDateFilter >=");
                sb.append(arr[0].replaceAll("-", ""));
                sb.append(" and Tbl_LeadMaster.convertDateFilter<=");
                sb.append(arr[1].replaceAll("-", ""));
                sb.append(" )");
                sb.append(" and ");
            } else if (datefiltertype.equalsIgnoreCase("updatedate")) {
                String[] arr = datevalue.split("@");
                sb.append("( Tbl_LeadMaster.updatedatefilter >=");
                sb.append(arr[0].replaceAll("-", ""));
                sb.append(" and Tbl_LeadMaster.updatedatefilter<=");
                sb.append(arr[1].replaceAll("-", ""));
                sb.append(" )");
                sb.append(" and ");
            }
        }
        if (searchvalue.trim().length() > 2) {
            String search = "(Tbl_LeadMaster.city like 'searchvaluestring%' or\n" +
                    "Tbl_LeadMaster.clientType like 'searchvaluestring%' or\n" +
                    "Tbl_LeadMaster.company like 'searchvaluestring%' or\n" +
                    "Tbl_LeadMaster.country like 'searchvaluestring%' or\n" +
                    "Tbl_LeadMaster.email like 'searchvaluestring%' or\n" +
                    "Tbl_LeadMaster.firstName like 'searchvaluestring%' or\n" +
                    "Tbl_LeadMaster.interestedProduct like 'searchvaluestring%' or\n" +
                    "Tbl_LeadMaster.lastName like 'searchvaluestring%' or\n" +
                    "Tbl_LeadMaster.leadSource like 'searchvaluestring%' or\n" +
                    "Tbl_LeadMaster.leadStatus like 'searchvaluestring%' or\n" +
                    "Tbl_LeadMaster.nextFollowUp like 'searchvaluestring%' or\n" +
                    "Tbl_LeadMaster.phonenumber like 'searchvaluestring%' or\n" +
                    "Tbl_LeadMaster.pincode like 'searchvaluestring%' or\n" +
                    "Tbl_LeadMaster.id like 'searchvaluestring%' or\n" +
                    "Tbl_LeadMaster.state like 'searchvaluestring%' )";

            search = search.replaceAll("searchvaluestring", searchvalue.replaceAll(UtilityClass.ApplicationPrefix, ""));

            sb.append(search);
            sb.append(" and ");
        }

        if (!userFilter.equalsIgnoreCase("0")) {
            sb.append("Tbl_LeadMaster.lastForward=");
            sb.append(userFilter);

        } else {
            sb.append("Tbl_LeadMaster.lastForward in (");
            sb.append(UtilityClass.userlist.get(userId));
            sb.append(" ) ");
        }


        return sb.toString();

    }

    @GetMapping("/getleadcount")
    public int leadCount(@RequestParam("statusValue") String statusValue
            , @RequestParam("leadSource") String leadSource
            , @RequestParam("product") String product
            , @RequestParam("leadType") String leadType
            , @RequestParam("userFilter") String userFilter
            , @RequestParam("datefilter") boolean datefilter
            , @RequestParam("datevalue") String datevalue
            , @RequestParam("searchvalue") String searchValue
            , @RequestParam("leadstage") String leadstage
            , @RequestParam("id") long id
            , @RequestParam("innersource") String innersource
            , @RequestParam("datefiltertype") String datefiltertype, Authentication authentication) {
        UserMasterDao userMasterDao = (UserMasterDao) authentication.getPrincipal();
        String wherequery = "";
        if (id == 0) {
            wherequery = buildQuery(statusValue, leadSource, product, leadType, userFilter, datefilter, datevalue, userMasterDao.getId(), searchValue, leadstage, innersource, datefiltertype);
        } else {
            wherequery = " where Tbl_LeadMaster.id=" + id + " ";
        }
        int count = leadMasterService.findCountForCampaign(wherequery);
        return count;
    }

    @PostMapping("/quickcampaignbyidsingledirect")
    public int quickCampaignSingledirect(@RequestParam("id")long id    ,@RequestBody TemplateDocument templateDocument, Authentication authentication) throws Exception {
        UserMasterDao userMasterDao = (UserMasterDao) authentication.getPrincipal();
        String wherequery =  " where Tbl_LeadMaster.id=" + id + " ";
        System.out.println("quickcampaignbyidsingledirect Template Type"+templateDocument.getTemplateType());
        List<CampaignObjectDao> al = null;
        CampaignDocument campaignDocument=new CampaignDocument();
        campaignDocument.setName("Quick Campaign(Direct)");
        campaignDocument.setTemplateName("Quick Campaign(Direct)");
        campaignDocument.setTotalReciept(1);
        campaignDocument.setCreateBy(userMasterDao.getUsername());
        campaignDocument.setCreateDate(UtilityClass.getDateRedable());
        campaignDocument.setDatefilter(UtilityClass.dateFilterDate());


        campaignDocument = campaignDocumentService.insert(campaignDocument);

        try {
            System.out.println("quickcampaignbyidsingledirect: wherequery is"+ wherequery);
            System.out.println("campaignDocument is"+ campaignDocument);
            al = leadMasterService.findLeadsForCampaign(wherequery, false, campaignDocument, 0, 1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (templateDocument.getTemplateType().equalsIgnoreCase("Mail")) {

            try {
                System.out.println("Al is" + al);

                finishMailBatchProcess( templateDocument, al);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (templateDocument.getTemplateType().equalsIgnoreCase("Message")) {

            try {
                logger.info("Finishing Message Batch Process (quickCampaignSingledirect)");
                finishMessageBatchProcess( templateDocument, al);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (templateDocument.getTemplateType().equalsIgnoreCase("Whatsapp")) {

            try {
                finishWhatsappMessage(templateDocument, al);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        campaignDocumentService.updatestatus(campaignDocument.getId(), (byte) 1);
        return 200;
    }

    @PostMapping("/quickcampaignbyidsingle")
    public int quickCampaignSingle(@RequestParam("id")long id    ,@RequestBody CampaignDocument campaignDocument, Authentication authentication) throws Exception {
        UserMasterDao userMasterDao = (UserMasterDao) authentication.getPrincipal();
        String wherequery =  " where Tbl_LeadMaster.id=" + id + " ";
        TemplateDocument templateDocument = templateDocumentService.findById(campaignDocument.getTemplateId());
        if (templateDocument == null) {
            throw new Exception("Template Cannot Be Found!! Please Try Again Later!");
        }
            List<CampaignObjectDao> al = null;

        campaignDocument.setCreateBy(userMasterDao.getUsername());
        campaignDocument.setCreateDate(UtilityClass.getDateRedable());
        campaignDocument.setDatefilter(UtilityClass.dateFilterDate());


        campaignDocument = campaignDocumentService.insert(campaignDocument);

            try {
                System.out.println("quickcampaignbyidsingle: Where Query is"+ wherequery);
                System.out.println("campaignDocument is"+ campaignDocument);
                al = leadMasterService.findLeadsForCampaign(wherequery, templateDocument.isHasUrl(), campaignDocument, 0, 1);
                System.out.println("al is"+ al);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            if (templateDocument.getTemplateType().equalsIgnoreCase("Mail")) {

                try {
                    finishMailBatchProcess( templateDocument, al);

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (templateDocument.getTemplateType().equalsIgnoreCase("Message")) {

                try {
                    logger.info("Finishing Message Batch Process (quickCampaignSingle)");
                    finishMessageBatchProcess( templateDocument, al);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (templateDocument.getTemplateType().equalsIgnoreCase("Whatsapp")) {

                try {
                    finishWhatsappMessage(templateDocument, al);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        campaignDocumentService.updatestatus(campaignDocument.getId(), (byte) 1);
        return 200;
    }



    @PostMapping("/insertcampaign")
    public CampaignDocument campaignDocument(@RequestBody CampaignDocument campaignDocument,
                                             @RequestParam("statusValue") String statusValue
            , @RequestParam("leadSource") String leadSource
            , @RequestParam("product") String product
            , @RequestParam("leadType") String leadType
            , @RequestParam("userFilter") String userFilter
            , @RequestParam("datefilter") boolean datefilter
            , @RequestParam("datevalue") String datevalue
            , @RequestParam("searchvalue") String searchValue
            , @RequestParam("leadstage") String leadstage
            , @RequestParam("id") long id
            , @RequestParam("innersource") String innersource
            , @RequestParam("datefiltertype") String datefiltertype

            , Authentication authentication) throws Exception {


        UserMasterDao userMasterDao = (UserMasterDao) authentication.getPrincipal();


        String wherequery = "";
        if (id == 0) {
            wherequery = buildQuery(statusValue, leadSource, product, leadType, userFilter, datefilter, datevalue, userMasterDao.getId(), searchValue, leadstage, innersource, datefiltertype);
        } else {
            wherequery = " where Tbl_LeadMaster.id=" + id + " ";
        }

        String WAServiceProvider = UtilityClass.propertyService.findProperty("Campaign", "WAServiceProvider");
        TemplateDocument templateDocument = templateDocumentService.findById(campaignDocument.getTemplateId());

        if(templateDocument.getTemplateType()=="Whatsapp"){
            if (WAServiceProvider.equalsIgnoreCase("WATI")){
                templateDocument = new TemplateDocument();
                templateDocument.setTemplateName(campaignDocument.getTemplateName());
                templateDocument.setTemplateStatus((byte)1);
                templateDocument.setTemplateType("Whatsapp");
            }
        }
//        else{
//            templateDocument = templateDocumentService.findById(campaignDocument.getTemplateId());
//        }


        if (templateDocument == null) {
            throw new Exception("Template Cannot Be Found!! Please Try Again Later!");
        }
        int maxLimit = 0;

        if (templateDocument.getTemplateType().equalsIgnoreCase("Mail")) {
            logger.info("Campaign type is Mail");
            maxLimit = Integer.parseInt(UtilityClass.propertyService.findProperty("Campaign", "maximumonecampaignlimitMAIL"));
        }
        if (templateDocument.getTemplateType().equalsIgnoreCase("Message")) {
            logger.info("Campaign type is Message");
            maxLimit = Integer.parseInt(UtilityClass.propertyService.findProperty("Campaign", "maximumonecampaignlimitMSG"));
        }
        if (templateDocument.getTemplateType().equalsIgnoreCase("Whatsapp")) {
            logger.info("Campaign type is Whatsapp");
            maxLimit = Integer.parseInt(UtilityClass.propertyService.findProperty("Campaign", "maximumonecampaignlimitWhatsapp"));
        }
        int count = campaignDocument.getTotalReciept();
        if (count > maxLimit) {
            throw new Exception("Campaign Cannot Be Created Max Limit Exceed " + maxLimit);
        }


        campaignDocument.setCreateBy(userMasterDao.getUsername());
        campaignDocument.setCreateDate(UtilityClass.getDateRedable());
        campaignDocument.setDatefilter(UtilityClass.dateFilterDate());
        campaignDocument = campaignDocumentService.insert(campaignDocument);

        int batch = 10000;
        logger.info("Executing Batch of {}", batch);
        int size = count / batch;
        if (size == 0) {
            size++;
        }

        logger.info("Size Of List is " + size);
        for (int i = 0; i < size; i++) {
            List<CampaignObjectDao> al = null;

            try {
                al = leadMasterService.findLeadsForCampaign(wherequery, templateDocument.isHasUrl(), campaignDocument, i, batch);
            } catch (SQLException e) {
                e.printStackTrace();
            }

            if (templateDocument.getTemplateType().equalsIgnoreCase("Mail")) {

                try {
                    System.out.println("method calling");
                    finishMailBatchProcess( templateDocument, al);
                    System.out.println("method called");
                    System.out.println("wherequery is"+wherequery);
                    System.out.println("templateDocument.isHasUrl() is"+templateDocument.isHasUrl());
                    System.out.println("campaignDocument is"+campaignDocument);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }


            if (templateDocument.getTemplateType().equalsIgnoreCase("Message")) {

                try {
                    logger.info("Finishing Message Batch Process (campaignDocument)");
                    finishMessageBatchProcess( templateDocument, al);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }


            if (templateDocument.getTemplateType().equalsIgnoreCase("Whatsapp")) {

                try {

                    String WAServiceProviders = UtilityClass.propertyService.findProperty("Campaign", "WAServiceProvider");
                    if (WAServiceProviders.equalsIgnoreCase("WATI")){
                        finishWhatsappMessageWATI(al,campaignDocument);
                    }else{
                        finishWhatsappMessage(templateDocument, al);
                    }


                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        }// END OF FOR LOOP
        campaignDocumentService.updatestatus(campaignDocument.getId(), (byte) 1);

        return campaignDocument;

    }//END OF insertcampaign

    private void finishWhatsappMessageWATI(List<CampaignObjectDao> al,CampaignDocument campaignDocument) throws Exception {
        logger.info("SENDING WHATSAPP TEMPLATE MESSAGE WITH WATI");
        WatiTemplateMsgRequestDao watiTemplateMsgRequestDao = new WatiTemplateMsgRequestDao();
        watiTemplateMsgRequestDao.setBroadcast_name(campaignDocument.getName());
        watiTemplateMsgRequestDao.setTemplate_name(campaignDocument.getTemplateName());
        List<WatiReceiverDao> watiReceiverDaoList = new ArrayList<>();
        WatiReceiverDao watiReceiverDao = null;
        for (CampaignObjectDao campaignObjectDao:al){
            watiReceiverDao = new WatiReceiverDao();
            String phoneNumber = campaignObjectDao.getPhonenumber();
            if(phoneNumber.length() == 10){
                phoneNumber = "91"+phoneNumber;
            }
            watiReceiverDao.setWhatsappNumber(phoneNumber);

            CustomParamDao customParamDao = new CustomParamDao("","");
            List<CustomParamDao> customParamDaos = new ArrayList<>();
            customParamDaos.add(customParamDao);
            watiReceiverDao.setCustomParams(customParamDaos);

            watiReceiverDaoList.add(watiReceiverDao);

        }
        watiTemplateMsgRequestDao.setReceivers(watiReceiverDaoList);
        watiService.sendTemplateMessages(watiTemplateMsgRequestDao);


    }

    private void finishWhatsappMessage(TemplateDocument templateDocument, List<CampaignObjectDao> al) {
        List<WhatsappDao> alWhatsappDaos = new ArrayList<>();
        WhatsappDao whatsappDao = new WhatsappDao();
        for (CampaignObjectDao campaignObjectDao : al) {
            String content=convertString(templateDocument.getTemplate(), campaignObjectDao);
            whatsappDao.setContent(content);
            whatsappDao.setCaption(convertString(templateDocument.getTemplateSubject(), campaignObjectDao));
            String fileName="";
            try{
                 fileName = content.substring( content.lastIndexOf('/')+1);
            }catch (Exception ew)
            {

            }
            whatsappDao.setFileName(fileName);
            whatsappDao.setPhone(getPhonenumber(campaignObjectDao.getPhonenumber()));
            whatsappDao.setContentType(templateDocument.getContentType());
            alWhatsappDaos.add(whatsappDao);
        }

        List<WhatsappResponseDao> responseDaoList = whatsappService.sendMessageWhatsapp(alWhatsappDaos);

        for (int i=0;i<responseDaoList.size();i++){
            al.get(i).setDlrStatus(responseDaoList.get(i).getResponseMessage());
            al.get(i).setMessageId(responseDaoList.get(i).getId());
            campaignDocumentService.increaseStatusCount(al.get(i).getCampaignId(),responseDaoList.get(i).getResponseMessage());
        }
        campaignTransactionService.saveAll(al);

    }




    private void finishMessageBatchProcess( TemplateDocument templateDocument, List<CampaignObjectDao> al) throws Exception {

        logger.info("Template is (finishMessageBatchProcess) :: {}",templateDocument);
        logger.info("CampaignObjectDao List is (finishMessageBatchProcess) :: {}",al);

        if (templateDocument.isHasUrl()) {
            al = urlShortnerService.urlShortnerServer(al);
        }
        MessageCampaignDao messageCampaignDao = new MessageCampaignDao(templateDocument.getParams(), al, templateDocument.getTemplateName());
        logger.info("messageCampaignDao is (finishMessageBatchProcess) :: {}",messageCampaignDao);
        //MessageCampaignDao messageCampaignDao = new MessageCampaignDao(templateDocument.getParams(), al, templateDocument.getTemplateIdHidden());
        String senderId = UtilityClass.propertyService.findProperty("Campaign", "SenderId");

        TextMessage textMessage = messageCampaignDao.convertToObject(senderId);


        if(templateDocument.getTemplateName().equalsIgnoreCase("Custom"))
        {
            TextMessageSimpleDao textMessageSimpleDao=new TextMessageSimpleDao();
            textMessageSimpleDao.setContent(templateDocument.getTemplate());
            textMessageSimpleDao.setDst(al.get(0).getPhonenumber());
            textMessageSimpleDao.setSenderId(senderId);
            textMessageService.sendTextMessageSingle(textMessageSimpleDao);
        }
        else{

            TextMessageSimpleDao textMessageSimpleDao=new TextMessageSimpleDao();
            textMessageSimpleDao.setContent(templateDocument.getTemplate());
            textMessageSimpleDao.setDst(al.get(0).getPhonenumber());
            textMessageSimpleDao.setSenderId(senderId);
            textMessageService.sendTextMessageSingle(textMessageSimpleDao);

            //textMessageService.sendTextMessage(textMessage);
        }

        campaignTransactionService.saveAll(al);


    }

    private void finishMailBatchProcess( TemplateDocument templateDocument, List<CampaignObjectDao> al) throws Exception {

        System.out.println("Template Document is"+templateDocument);
        List<MailObjectDao> almail = new ArrayList<>();

        MailObjectDao mailObjectDao;
        for (int i = 0; i < al.size(); i++) {
            System.out.println("CampaignObjectDao object is"+al.size());
            mailObjectDao = new MailObjectDao();
            MailObjectMessage mailObjectMessage = new MailObjectMessage();
            mailObjectMessage.setRecipient(al.get(i).getEmail());
            mailObjectMessage.setSubject(templateDocument.getTemplateSubject());
            mailObjectMessage.setCustRef("ASdad");
            mailObjectMessage.setMessage(templateDocument.getTemplate());
            mailObjectDao.setMessage(mailObjectMessage);
            MailObjectTemplate mailObjectTemplate = new MailObjectTemplate();
            mailObjectTemplate.setTemplateId(templateDocument.getTemplateIdHidden());
            mailObjectTemplate.setTemplateValues(al.get(i));
            mailObjectDao.setTemplate(mailObjectTemplate);
            System.out.println("Ye raha Mail ka Object" + mailObjectDao);
            almail.add(mailObjectDao);
        }

        mailObjectService.bulkInsertMail(almail);
        campaignTransactionService.saveAll(al);
        System.out.println("Mail Sent");
    }


}
