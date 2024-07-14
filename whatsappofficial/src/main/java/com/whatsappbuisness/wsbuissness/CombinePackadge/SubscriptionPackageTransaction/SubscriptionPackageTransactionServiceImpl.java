package com.whatsappbuisness.wsbuissness.CombinePackadge.SubscriptionPackageTransaction;

import com.whatsappbuisness.wsbuissness.CombinePackadge.Filtering.FilterDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SubscriptionPackageTransactionServiceImpl implements SubscriptionPackageTransactionService{

    @Autowired
    SubscriptionPackageTransactionRepo subscriptionPackageTransactionRepo;

    @Autowired
    MongoTemplate mongoTemplate;
    @Override
    public SubscriptionPackageTransactionDao insert(SubscriptionPackageTransactionDao subscriptionPackageTransactionDao) {
//        List<SubscriptionPackageTransactionDao> subscriptionPackageTransactionDaoList = subscriptionPackageTransactionRepo.findByAccountId(subscriptionPackageTransactionDao.getAccountId());
//        for(int i =0;i<subscriptionPackageTransactionDaoList.size();i++){
//            subscriptionPackageTransactionDaoList.get(i).setStatus(false);
//        }
//        subscriptionPackageTransactionDao.setStatus(true);
//        if(subscriptionPackageTransactionDaoList == null){
//            subscriptionPackageTransactionDaoList = new ArrayList<>();
//        }
//        subscriptionPackageTransactionDaoList.add(subscriptionPackageTransactionDao);
//        subscriptionPackageTransactionRepo.saveAll(subscriptionPackageTransactionDaoList);
        subscriptionPackageTransactionRepo.save(subscriptionPackageTransactionDao);
        return subscriptionPackageTransactionDao;
    }

    @Override
    public List<SubscriptionPackageTransactionDao> get(FilterDao filterDao) {
        Query query = new Query();
        if(filterDao.getAccountId() == 0){
           query.addCriteria(Criteria.where("status").is(filterDao.isStatus()).and("createDateFilter").gte(Long.parseLong(filterDao.getStartdate())).lte(Long.parseLong(filterDao.getEnddate()))).with(Sort.by(Sort.Order.desc("createDateFilter")));
        }else{
           query.addCriteria(Criteria.where("accountId").is(filterDao.getAccountId()).and("status").is(filterDao.isStatus()).and("createDateFilter").gte(Long.parseLong(filterDao.getStartdate())).lte(Long.parseLong(filterDao.getEnddate()))).with(Sort.by(Sort.Order.desc("createDateFilter")));
        }
        return mongoTemplate.find(query, SubscriptionPackageTransactionDao.class, "SubscriptionPackageTransactionDao");
    }

    @Override
    public void changeAccountSubscriptionStatus(long accountId, boolean status) {
        SubscriptionPackageTransactionDao subscriptionPackageTransactionDao = subscriptionPackageTransactionRepo.findByAccountIdAndStatus(accountId, status);
        if(subscriptionPackageTransactionDao != null){
            subscriptionPackageTransactionDao.setStatus(!status);
            subscriptionPackageTransactionRepo.save(subscriptionPackageTransactionDao);
        }
    }
}
