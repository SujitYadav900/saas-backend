package com.example.maxcrm.MaxCrm.CombinePackage.ServiceReview;

import java.util.List;

public interface ServiceReviewService {

    ServiceReviewDao insert(ServiceReviewDao reviewDao);
    ServiceReviewDao update(ServiceReviewDao reviewDao);
    List<ServiceReviewDao> getAll();
    ServiceReviewDao findByLeadId(long leadid);
}
