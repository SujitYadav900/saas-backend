package com.example.maxcrm.MaxCrm.ServiceImpl;

import com.example.maxcrm.MaxCrm.Dao.*;
import com.example.maxcrm.MaxCrm.Service.*;
import com.example.maxcrm.MaxCrm.Service.*;
import com.example.maxcrm.MaxCrm.Utility.UtilityClass;
import com.example.maxcrm.MaxCrm.Dao.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class PasswordRecoveryServiceImpl implements PasswordRecoveryService {

    private final Logger logger = LoggerFactory.getLogger(PasswordRecoveryServiceImpl.class);

    @Autowired
    private UserMasterService userMasterService;

    @Autowired
    private TextMessageService textMessageService;

    @Autowired
    private TemplateDocumentService templateDocumentService;

    @Autowired
    private MailObjectService mailObjectService;


    @Override
    public String recoverPassword(String value, String mode) throws Exception {

        logger.info("Resetting Password for username/email/phonenumber :: {} and recovery mode :: {}", value,mode);
        UserMasterDao user = userMasterService.findUserForRecovery(value);
        if (user == null) {
            logger.info("user {} not found", value);
            throw new Exception("Sorry Couldn't find user with provided details!!");
        }
        String newpassword = UtilityClass.generateOtp();
        UserMasterDao tempUser = user;
        tempUser.setPassword(newpassword);
        userMasterService.updateUser(newpassword,user.getId());


        CampaignObjectDao campaignDataService = user.convertForCampaign();


        if (mode.equals("text")) {
            String templateID = UtilityClass.propertyService.findProperty("Application","PasswordRecoveryTemplateID");
            String senderId = UtilityClass.propertyService.findProperty("Campaign", "SenderId");
            //TemplateDocument templateDocument = templateDocumentService.findById(" 5e4a5a34ba54f431ea097c34");
            TemplateDocument templateDocument = templateDocumentService.findById(templateID);


            List<CampaignObjectDao> al = new ArrayList<>();
            al.add(campaignDataService);

            MessageCampaignDao messageCampaignDao = new MessageCampaignDao(templateDocument.getParams(), al,templateDocument.getTemplateIdHidden());
            TextMessage textMessage = messageCampaignDao.convertToObject(senderId);
            textMessage.getSmsTemplateParams().get(0).setTemplateParams(user.getUsername()+"~"+newpassword);
            //textMessageService.sendTextMessage(textMessage);

            //====================================================

            TextMessageSimpleDao textMessageSimpleDao = new TextMessageSimpleDao();
            String content = templateDocument.getTemplate();
            content = convertString(content,user);
            //textMessageSimpleDao.setContent(templateDocument.getTemplate());
            textMessageSimpleDao.setContent(content);
            textMessageSimpleDao.setDst(user.getMobile());
            textMessageSimpleDao.setSenderId(senderId);
            //USE THIS METHOD FOR SENDING DATA VIA PARAMS
            textMessageService.sendTextMessageSingle(textMessageSimpleDao);
            return "New Password sent to your registered phone number";

        } else {

     /*       TemplateDocument templateDocument = templateDocumentService.findById("5e4a5a1fba54f431ea097c33");
            MailCampaignDao mailCampaignDao = new MailCampaignDao(templateDocument.getTemplate(), templateDocument.getParams(), campaignDataService, templateDocument.getTemplateSubject());
            MailObjectDao mailObjectDao = mailCampaignDao.generateMailObject();
            mailObjectService.insert(mailObjectDao);*/

            return "New Password sent to your registered email address";
        }


    }

    private String convertString(String content, UserMasterDao userMasterDao) {
        logger.info("Converting string  content :: {} , usermasterdoa :: {}",content,userMasterDao);
        content = content.replaceAll("@email", userMasterDao.getEmail());
        content = content.replaceAll("@phonenumber", userMasterDao.getMobile());
        content = content.replaceAll("@fullname", userMasterDao.getFirstName()+ " "+userMasterDao.getLastName());
        content = content.replaceAll("@otp", userMasterDao.getPassword());
        content = content.replaceAll("@password", userMasterDao.getPassword());


        return content;
    }

    private long generatePassword() {

        return new Random().nextInt();
    }
}
