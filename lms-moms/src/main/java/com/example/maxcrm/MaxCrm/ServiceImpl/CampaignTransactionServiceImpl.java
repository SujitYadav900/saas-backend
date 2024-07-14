package com.example.maxcrm.MaxCrm.ServiceImpl;

import com.example.maxcrm.MaxCrm.Dao.CampaignDocument;
import com.example.maxcrm.MaxCrm.Dao.CampaignObjectDao;
import com.example.maxcrm.MaxCrm.Dao.PaginationMainDao;
import com.example.maxcrm.MaxCrm.Repo.CampaignTransactionRepo;
import com.example.maxcrm.MaxCrm.Service.CampaignDocumentService;
import com.example.maxcrm.MaxCrm.Service.CampaignTransactionService;
import com.example.maxcrm.MaxCrm.Utility.UtilityClass;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CampaignTransactionServiceImpl implements CampaignTransactionService {
    @Autowired
    CampaignTransactionRepo campaignTransactionRepo;
    @Autowired
    CampaignDocumentService campaignDocumentService;
    @Autowired
    MongoTemplate mongoTemplate;
    Logger logger = LoggerFactory.getLogger(CampaignTransactionService.class);


    @Override
    public void saveAll(List<CampaignObjectDao> al) {


            MongoDatabase database = mongoTemplate.getDb();
            MongoCollection<Document> collection = database.getCollection("CampaignTransactionDocument");
            List<Document> aldoc = new ArrayList<>();
            CampaignObjectDao campaignObjectDao;
            Document document;
            long dateTiming = UtilityClass.fullDateLong();
            for (int i = 0; i < al.size(); i++) {
                campaignObjectDao = al.get(i);
                document = new Document();
                document.put("_id", campaignObjectDao.getId());
                document.put("datetiming", dateTiming);
                document.put("campaignId", campaignObjectDao.getCampaignId());
                document.put("leadId", campaignObjectDao.getLeadId());
                document.put("lastForward", campaignObjectDao.getLastForward());
                document.put("openCount", campaignObjectDao.getOpenCount());
                document.put("messageId", campaignObjectDao.getMessageId());
                document.put("dlrStatus", campaignObjectDao.getDlrStatus());

                aldoc.add(document);
            }
            collection.insertMany(aldoc);




    }



    @Override
    public CampaignObjectDao updateClick(String id, String ip, String userAgent) {
        long dateTiming = UtilityClass.fullDateLong();
        Query query = new Query(Criteria.where("id").is(id));
        Update update = new Update();
        update.inc("openCount", 1);
        update.set("ip", ip);
        update.set("datetiming", dateTiming);
        update.set("timing", UtilityClass.getDateRedable());
        update.set("userAgent", userAgent);
        return mongoTemplate.findAndModify(query, update, FindAndModifyOptions.options().returnNew(true), CampaignObjectDao.class);

    }

    @Override
    public void updateDlrStatus(String messageId, String status) throws Exception {
        logger.info("Updating DLR to :: {} for CampaignObjectDao with MessageID :: {}",status,messageId);
        long dateTiming = UtilityClass.fullDateLong();
        Query query = new Query(Criteria.where("messageId").is(messageId));

        Update update = new Update();
        update.set("dlrStatus", status);
        CampaignObjectDao campaignObjectDao = mongoTemplate.findAndModify(query, update, FindAndModifyOptions.options().returnNew(true), CampaignObjectDao.class);
        //update the dlr wise count in campaign
        if(campaignObjectDao != null){
            campaignDocumentService.increaseStatusCount(campaignObjectDao.getCampaignId(),status);
        }


    }



    @Override
    public void deleteTransactionBeforeDate(long datefilter) {
        Query query = new Query(Criteria.where("datetiming").lt(datefilter));
        mongoTemplate.remove(query, CampaignObjectDao.class);
    }

    @Override
    public PaginationMainDao<CampaignObjectDao> finddata(String campaignId, int offset, int limit, int totalCount) {

        PaginationMainDao<CampaignObjectDao> paginationDao = new PaginationMainDao<>();
        Pageable pageable =
                PageRequest.of(offset, limit,Sort.by(Sort.Direction.DESC, "datetiming"));


        Query query = new Query(Criteria.where("campaignId").is(campaignId));
        query.with(pageable);

        List<CampaignObjectDao>al=campaignTransactionRepo.findByCampaignId(campaignId,pageable);


        paginationDao.setData(al);
        paginationDao.setRecordsFiltered(totalCount);
        paginationDao.setRecordsTotal(totalCount);

        return paginationDao;
    }
}
