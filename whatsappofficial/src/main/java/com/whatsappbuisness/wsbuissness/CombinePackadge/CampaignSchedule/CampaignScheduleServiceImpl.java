package com.whatsappbuisness.wsbuissness.CombinePackadge.CampaignSchedule;

import com.whatsappbuisness.wsbuissness.CombinePackadge.Campaing.CampaingDao;
import com.whatsappbuisness.wsbuissness.CombinePackadge.Campaing.CampaingService;
import com.whatsappbuisness.wsbuissness.CombinePackadge.MessageEntry.MessageEntryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class CampaignScheduleServiceImpl implements CampaignScheduleService {

    @Autowired
    private MongoTemplate mongoTemplate;
    private static final Logger logger = LoggerFactory.getLogger(CampaignScheduleServiceImpl.class);
    public static final String baseDocumentName = "CampaignScheduleDao";

    @Autowired
    public MessageEntryService messageEntryService;
    @Autowired
    private CampaingService campaingService;

    @Autowired
    DataSource dataSource;

    @Override
    public int checkScheduleTime() {


        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyMMddHHmm");
        LocalDateTime now = LocalDateTime.now();
        String date = dtf.format(now);
        logger.info("start schedular {}", date);

        Query query = new Query();
//        query.addCriteria(Criteria.where("messageDao.scheduleTime").in(Long.parseLong(date)));
        query.addCriteria(Criteria.where("messageDao.scheduleTime").in(Long.parseLong(date)).and("isActive").is(true));
        List<CampaignScheduleDao> scheduleDaoList = mongoTemplate.find(query, CampaignScheduleDao.class, baseDocumentName);
        for (CampaignScheduleDao scheduleDao : scheduleDaoList) {
            messageEntryService.insertToQue(scheduleDao.getMessageDao());
            campaingService.updateScheuledStatus(scheduleDao.getMessageDao().getCampaingId());
            Query querys = new Query();
            querys.addCriteria(Criteria.where("_id").in(scheduleDao.getId()));
            Update update = new Update().set("isActive", false);
            mongoTemplate.upsert(querys, update, CampaignScheduleDao.class);
        }


        return 200;
    }

    @Override
    public List<CampaingDao> getAllActivCampaign(long accountId) {
        List<CampaingDao> list = new ArrayList<>();
        Query query = new Query();
        query.addCriteria(Criteria.where("isActive").in(true).and("messageDao.accountId").in(accountId));
        logger.info("mongoQuery {}", query);
        list = mongoTemplate.find(query, CampaingDao.class, baseDocumentName);
        return list;
    }

    @Override
    public int deleteScheduledByCampaignId(long id) {
        Query query = new Query();
//        query.addCriteria(Criteria.where("messageDao.campaingId").in(id));
        query.addCriteria(Criteria.where("messageDao.campaingId").is(id));
        Update update = new Update();
        update.set("isActive", false);
        logger.info("mongoQuery {}", query);
        try {
            mongoTemplate.updateMulti(query, update, CampaignScheduleDao.class, baseDocumentName);
            updateScheduledCampaign(id);
            return 200;
        } catch (Exception ew) {
            logger.info("ERROR OCCURRED {}", ew.getMessage());
            ew.printStackTrace();
        }

        return 400;
    }

    private void updateScheduledCampaign(long id) {
        logger.info("campaignId {}",id);
        Connection con = null;
        try {
            con = dataSource.getConnection();
            PreparedStatement stmt = con.prepareStatement("update WSBUISSNES.Tbl_Campaign set iScheduled=0 where id=?;");
            stmt.setLong(1, id);
            logger.info("Query {}",stmt);
            int i = stmt.executeUpdate();
        } catch (Exception ew) {
            logger.info("ERROR OCCURRED updateScheduledCampaign  {}", ew.getMessage());
            ew.printStackTrace();
        } finally {
            try {
                con.close();
            } catch (Exception ew) {
                logger.info("updateScheduledCampaign con error {}", ew.getMessage());
                ew.printStackTrace();
            }
        }
    }
}
