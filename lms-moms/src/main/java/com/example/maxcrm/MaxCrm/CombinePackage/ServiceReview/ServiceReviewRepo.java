package com.example.maxcrm.MaxCrm.CombinePackage.ServiceReview;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ServiceReviewRepo extends JpaRepository<ServiceReviewDao,Long> {

    ServiceReviewDao findByLeadId(long leadid);

}
