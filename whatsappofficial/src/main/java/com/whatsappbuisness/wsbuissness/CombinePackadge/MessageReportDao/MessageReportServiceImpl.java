package com.whatsappbuisness.wsbuissness.CombinePackadge.MessageReportDao;
/*
 Author=Supreet Singh
 Date= 09/03/21 2:24 PM
*/

import com.whatsappbuisness.wsbuissness.CombinePackadge.Common.AccountTransactionDao;
import com.whatsappbuisness.wsbuissness.CombinePackadge.Filtering.FilterDao;
import com.whatsappbuisness.wsbuissness.CombinePackadge.Messages.MessageDao;
import com.whatsappbuisness.wsbuissness.CombinePackadge.Messages.MessageStatus;
import com.whatsappbuisness.wsbuissness.CombinePackadge.RabbitMqQue.MongoQue.MongoDB.MessagePersistService;
import com.whatsappbuisness.wsbuissness.CombinePackadge.RefundDao.RefundRepo;
import com.whatsappbuisness.wsbuissness.CombinePackadge.Subscription.SubscriptionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.GroupOperation;
import org.springframework.data.mongodb.core.aggregation.MatchOperation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.*;

@Service
public class MessageReportServiceImpl implements MessageReportService {
    private static final Logger logger = LoggerFactory.getLogger(MessageReportService.class);
    @Autowired
    MessagePersistService messagePersistService;
    @Autowired
    DataSource dataSource;
    @Autowired
    MessageReportRepo messageReportRepo;
    @Autowired
    SubscriptionService subscriptionService;
    @Autowired
    RefundRepo refundRepo;

    @Autowired
    private MongoTemplate mongoTemplate;
    private static final String baseDocumentName="MessageDocument_";


    public static String removeLastChars(String str, int chars) {
        return str.substring(0, str.length() - chars);
    }

    @Override
    public void updateDlr(String id, MessageStatus messageStatus, long account) {

        logger.info("Will Update Dlr of id {} to {} of accountID {}", id, messageStatus, account);
        if(messageStatus==null)
        {
            logger.info("Will Return As Message status is null!!");
            return;
        }
        MessageDao messageDao = messagePersistService.updateDlr(id, messageStatus, account);


    }

    @Override
    public List<MessageReportDao> getAllByDate(FilterDao filterDao) {

        long startDate = Long.parseLong(filterDao.getAccountId() + filterDao.getStartdate());
        long enddate = Long.parseLong(filterDao.getAccountId() + filterDao.getEnddate());
        return messageReportRepo.getReport(startDate, enddate);
    }

    @Override
    public void refundDebits() {


    }

    @Override
    public List<CommonGroupQueryDao> getReportBetweenDate(int startdate, int enddate, String chatside, String type, long accountId,byte reportBy) {
        logger.info("dlr report by:startdate:{} ,enddate:{} , report by :{}",startdate,enddate,reportBy);

        List<Criteria> criteriaList = new ArrayList<>();
        criteriaList.add(Criteria.where("dateFilter").gte(startdate).lte(enddate));
        if (chatside!=null)
        {
            criteriaList.add(Criteria.where("chatSide").is(chatside));
         }
        if (type!=null){
            criteriaList.add(Criteria.where("type").is(type));
        }
        if(reportBy==1){
//            logger.info("dlr report ispanel");
            criteriaList.add(Criteria.where("isPanel").is(true));

        }
        else if(reportBy==2){
//            logger.info("dlr report api");
            criteriaList.add(Criteria.where("isPanel").is(false));

        }
        else{
//            logger.info("dlwr report by ispanel and api");
        }
        Criteria cri = new Criteria();
        cri.andOperator(criteriaList.toArray(new Criteria[criteriaList.size()]));
        GroupOperation groupByStateAndSumPop = group("messageStatus").count().as("count");
        MatchOperation filterStates = match(cri);
        Aggregation aggregation = newAggregation(filterStates, groupByStateAndSumPop);
        logger.info("aggreagation :{}",aggregation);
        AggregationResults<CommonGroupQueryDao> alTemp = mongoTemplate.aggregate(aggregation, baseDocumentName + accountId, CommonGroupQueryDao.class);
        return alTemp.getMappedResults();
    }



    public List<MessageDao> reportByAccountGroup(int startdate, int enddate, String chatside, String type, long accountId,byte reportBy) {
        logger.info("getreport by:startdate:{} ,enddate:{} , report by :{}",startdate,enddate,reportBy);
        Query query = new Query();
        List<Criteria> criteriaList = new ArrayList<>();
        criteriaList.add(Criteria.where("dateFilter").gte(startdate).lte(enddate));
        if (chatside!=null)
        {
            criteriaList.add(Criteria.where("chatSide").is(chatside));
        }
        if (type!=null){
            criteriaList.add(Criteria.where("type").is(type));
        }
        if(reportBy==1){
//            logger.info("ispanel in get report");
            criteriaList.add(Criteria.where("isPanel").is(true));

        }
        else if(reportBy==2){
//            logger.info("api in get report");
            criteriaList.add(Criteria.where("isPanel").is(false));

        }
        else{
//            logger.info("repory by ispanel and api getreport");
        }
        query.addCriteria(new Criteria().andOperator(criteriaList.toArray(new Criteria[criteriaList.size()])));
        logger.info("query for get report:{}",query);
        return mongoTemplate.find(query, MessageDao.class, baseDocumentName + accountId);
    }

    public List<MessageDao> reportByAccountGroup(int startdate, int enddate, String chatside, String type, long accountId) {
        Query query = new Query();
        logger.info("getreport by:startdate:{} ,enddate:{} ",startdate,enddate);
        List<Criteria> criteriaList = new ArrayList<>();
        criteriaList.add(Criteria.where("dateFilter").gte(startdate).lte(enddate));
        if (chatside!=null)
        {
            criteriaList.add(Criteria.where("chatSide").is(chatside));
        }
        if (type!=null){
            criteriaList.add(Criteria.where("type").is(type));
        }
        query.addCriteria(new Criteria().andOperator(criteriaList.toArray(new Criteria[criteriaList.size()])));
        return mongoTemplate.find(query, MessageDao.class, baseDocumentName + accountId);
    }

    void pushToCredit(AccountTransactionDao accountTransactionDao) {

    }

}
