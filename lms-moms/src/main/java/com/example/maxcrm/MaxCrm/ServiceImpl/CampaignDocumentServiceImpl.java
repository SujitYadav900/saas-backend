package com.example.maxcrm.MaxCrm.ServiceImpl;

import com.example.maxcrm.MaxCrm.Dao.CampaignDocument;
import com.example.maxcrm.MaxCrm.Dao.CampaignObjectDao;
import com.example.maxcrm.MaxCrm.Dao.NotificationDao;
import com.example.maxcrm.MaxCrm.Repo.CampaignRepo;
import com.example.maxcrm.MaxCrm.Service.CampaignDocumentService;
import com.example.maxcrm.MaxCrm.Service.CampaignTransactionService;
import com.example.maxcrm.MaxCrm.Service.NotificationService;
import com.example.maxcrm.MaxCrm.Utility.UtilityClass;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CampaignDocumentServiceImpl implements CampaignDocumentService {
    Logger logger = LoggerFactory.getLogger(CampaignDocumentServiceImpl.class);
    @Autowired
    CampaignRepo campaignRepo;

    @Autowired
    CampaignTransactionService campaignTransactionService;
    @Autowired
    NotificationService notificationService;


    @Autowired
    MongoTemplate mongoTemplate;

    @Override
    public List<CampaignDocument> findAllByCreateBy(String createBy) {
        return campaignRepo.findAllByCreateBy(createBy, Sort.by(Sort.Direction.DESC, "id"));
    }


    @Override
    public CampaignDocument insert(CampaignDocument campaignDocument) {

        campaignDocument = campaignRepo.insert(campaignDocument);
        logger.info("Inserting {}", campaignDocument);

        return campaignDocument;
    }

    @Override
    public void deleteOldCampaignData(int date) {
        logger.info("Deleting Data Less Than {}", date);
        Query query = new Query(Criteria.where("datefilter").lte(date));
        mongoTemplate.remove(query, CampaignDocument.class);
    }


    @Override
    public void delete(String id) {
        logger.info("Deleting {}", id);
        campaignRepo.deleteById(id);

    }


    @Override
    public String updateClick(String cmpTransId, String ip, String userAgent) throws Exception {



        CampaignObjectDao campaignObjectDao = campaignTransactionService.updateClick(cmpTransId, ip, userAgent);
        if(campaignObjectDao==null)
        {
            logger.error("Invalid Campaing Transaction {}",cmpTransId);
            throw new Exception("Request Page Cannot Be Found!!");
        }
        String campaignId = campaignObjectDao.getCampaignId();

        Query query = new Query(Criteria.where("id").is(campaignId));
        Update update = new Update();
        update.inc("totalClicks", 1);
        CampaignDocument campaignDocument = mongoTemplate.findAndModify(query, update, FindAndModifyOptions.options().returnNew(true), CampaignDocument.class);
        if(campaignDocument==null)
        {
            logger.error("Invalid Campaing {}",campaignId);
            throw new Exception("Campaing Cannot Be Found!!TRy Again Later");
        }
        NotificationDao notificationDao = new NotificationDao();
        String createTiming = UtilityClass.getDateMysql();
        notificationDao.setAssignTo(campaignObjectDao.getLastForward());
        notificationDao.setSubject("Lead Clicked");
        notificationDao.setRedirectUrl("lead?id=" + campaignObjectDao.getLeadId());
        notificationDao.setNotificationTiming(createTiming);
        notificationDao.setInnerSubject("CLICK");
        String sendmessage = UtilityClass.propertyService.findProperty("Campaign", "Send_Message_On_Click");
        notificationDao.setLeadId(campaignObjectDao.getLeadId());
        notificationDao.setSendMessage(false);
        if (sendmessage.equalsIgnoreCase("1")) {
            notificationDao.setSendMessage(true);
        }
        notificationDao.setMessage("Lead Opened The Campaign Link");
        notificationDao.setTo(campaignObjectDao.getLastForward());
        notificationDao.setCreateAt(createTiming);
        notificationDao.setDatetiming(UtilityClass.fullDateLong());
        notificationDao.setCreateBy("SYSTEM");
        notificationService.insertSingleNotification(notificationDao);
        logger.info("Successufully Registering Click Of Url {}",campaignDocument);
        return campaignDocument.getUrl();


    }

    @Override
    public void increaseStatusCount(String campaignId,String dlrStatus) {
        logger.info("Increasing DLR count for status :: {} for CampaignDocument with campaignId :: {}",dlrStatus,campaignId);
        Query query = new Query(Criteria.where("id").is(campaignId));
        Update update = new Update();

        if(dlrStatus.equalsIgnoreCase("Sent")){
            update.inc("sentCount", 1);
        }else if(dlrStatus.equalsIgnoreCase("Delivered")){
            update.inc("deliverCount", 1);
        }else if(dlrStatus.equalsIgnoreCase("Viewed")){
            update.inc("readCount", 1);
        }

        mongoTemplate.updateFirst(query, update,CampaignDocument.class);
    }

    @Override
    public void updatestatus(String id, byte status) {
        Query query = new Query(Criteria.where("id").is(id));
        Update update = new Update();
        update.set("status", status);
        mongoTemplate.updateFirst(query,update,CampaignDocument.class);
    }
}
