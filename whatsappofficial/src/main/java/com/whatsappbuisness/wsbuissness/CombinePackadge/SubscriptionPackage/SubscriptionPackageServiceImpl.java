package com.whatsappbuisness.wsbuissness.CombinePackadge.SubscriptionPackage;

import com.whatsappbuisness.wsbuissness.CombinePackadge.ClientSignUp.ClientSignUpDao;
import com.whatsappbuisness.wsbuissness.CombinePackadge.ClientSignUp.ClientSignUpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubscriptionPackageServiceImpl implements SubscriptionPackageService{

    @Autowired
    SubscriptionPackageRepo subscriptionPackageRepo;
    @Autowired
    MongoTemplate mongoTemplate;
    @Autowired
    ClientSignUpService clientSignUpService;

    @Override
    public SubScriptionPackageDao insert(SubScriptionPackageDao subScriptionPackageDao) {return subscriptionPackageRepo.save(subScriptionPackageDao);}

    @Override
    public List<SubScriptionPackageDao> getByDateAndStatus(long accountId, String startDate, String endDate, boolean status) {
        Query query = new Query(Criteria.where("status").is(status).and("createDateFilter").gte(Long.parseLong(startDate.replaceAll("-",""))).lte(Long.parseLong(endDate.replaceAll("-","")))).with(Sort.by(Sort.Order.desc("createDateFilter")));
        return mongoTemplate.find(query,SubScriptionPackageDao.class,"SubscriptionPackageDao");
    }

    @Override
    public SubScriptionPackageDao getBYId(int id) {
        return subscriptionPackageRepo.findById(id);
    }

    @Override
    public SubScriptionPackageDao addSubscriptionPackageToClient(SubScriptionPackageDao subScriptionPackageDao) {
        ClientSignUpDao clientSignUpDao = clientSignUpService.getById(String.valueOf(subScriptionPackageDao.getAccountId()));
        clientSignUpService.addSubscriptionPackageToClient(clientSignUpDao,subScriptionPackageDao);
        return subScriptionPackageDao;
    }

}
