package com.example.maxcrm.MaxCrm.CombinePackage.ServiceReview;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceProviderImpl implements ServiceReviewService{

    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private ServiceReviewRepo reviewRepo;

    @Override
    public ServiceReviewDao insert(ServiceReviewDao reviewDao) {
        logger.info("Inserting ReviewDao  : {}",reviewDao);
        return reviewRepo.save(reviewDao);
    }

    @Override
    public ServiceReviewDao update(ServiceReviewDao reviewDao) {
        logger.info("Updating ReviewDao  : {}",reviewDao);
        return reviewRepo.save(reviewDao);
    }

    @Override
    public List<ServiceReviewDao> getAll() {
        logger.info("Getting all ReviewDao ");
        return reviewRepo.findAll();
    }

    @Override
    public ServiceReviewDao findByLeadId(long leadid) {
        logger.info("Getting ReviewDao by leadid : {} ",leadid);
        return reviewRepo.findByLeadId(leadid);
    }
}
