package com.example.maxcrm.MaxCrm.Repo;

import com.example.maxcrm.MaxCrm.Dao.CampaignObjectDao;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface CampaignTransactionRepo extends MongoRepository<CampaignObjectDao,String> {


    @Query("{campaignId:?0}")
    List<CampaignObjectDao> findByCampaignId(String campId, Pageable pageable);



}
