package com.whatsappbuisness.wsbuissness.CombinePackadge.RabbitMqQue.MongoQue.Conversation;
/*
 Author=Supreet Singh
 Date= 10/03/21 1:09 PM
*/

import com.whatsappbuisness.wsbuissness.CombinePackadge.Datetiming.DateTiming;
import com.whatsappbuisness.wsbuissness.CombinePackadge.PaginationDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

@Service
public class ConversationServiceImpl implements ConversationService {
    @Autowired
    MongoTemplate mongoTemplate;
    public static String collectionBaseName = "ConversationDocument_";

    Logger logger= LoggerFactory.getLogger(getClass());

    @Override
    public void unread(String id, long accountId) {
        Query query = new Query(Criteria.where("_id").is(id));
        Update update = new Update();
        update.set("unread", false);
        update.set("unreadMessage", 0);

        mongoTemplate.updateFirst(query, update, ConversationDao.class, collectionBaseName + accountId);

    }

    @Override
    public List<ConversationDao> getOnlyUnread(long accountId) {
        Query query = new Query(Criteria.where("unread").is(true));
        query.with(PageRequest.of(0, 10, Sort.by(Sort.Order.desc("lastMessageTime"))));
        return mongoTemplate.find(query, ConversationDao.class, collectionBaseName + accountId);
    }

    @Override
    public PaginationDao<ConversationDao> getAll(FilterDao filterDao) {
        PaginationDao<ConversationDao> paginationDao = new PaginationDao<>();
        List<ConversationDao> al = new ArrayList<>();
        Query query = new Query();
        if (!filterDao.getSearch().equalsIgnoreCase("0")) {
//            Pattern pattern = Pattern.compile("^"+Pattern.quote(filterDao.getSearch()), Pattern.CASE_INSENSITIVE);
//            query.addCriteria(Criteria.where("_id").regex(pattern));
            Pattern pattern = Pattern.compile(".*" + Pattern.quote(filterDao.getSearch()) + ".*", Pattern.CASE_INSENSITIVE);
            Criteria criteria = new Criteria().orOperator(
                    Criteria.where("_id").regex(pattern),
                    Criteria.where("contactName").regex(pattern)
            );

            query.addCriteria(criteria);
            logger.info("query in mongo:{}",query);


        }
        paginationDao.setTotal(mongoTemplate.count(query, collectionBaseName + filterDao.getAccountId()));
        if (paginationDao.getTotal() == 0) {
            paginationDao.setData(al);
            return paginationDao;
        }
        query.with(PageRequest.of(filterDao.getOffset(), filterDao.getLimit(), Sort.by(Sort.Order.desc("lastMessageTime"))));
        al = mongoTemplate.find(query, ConversationDao.class, collectionBaseName + filterDao.getAccountId());
        paginationDao.setData(al);
        return paginationDao;
    }

    @Override
    public void saveContact(String contact, String id, long accountId) {
        Query query = new Query(Criteria.where("_id").is(id));
        Update update = new Update();
        update.set("contactName", contact);
        update.set("isSaved", true);
        mongoTemplate.updateFirst(query, update, collectionBaseName + accountId);


    }
}
