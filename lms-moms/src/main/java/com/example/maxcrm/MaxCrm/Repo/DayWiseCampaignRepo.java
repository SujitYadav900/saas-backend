package com.example.maxcrm.MaxCrm.Repo;

import com.example.maxcrm.MaxCrm.Dao.DayWiseCampaignDao;
import com.example.maxcrm.MaxCrm.Dao.TemplateDocument;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface DayWiseCampaignRepo extends MongoRepository<DayWiseCampaignDao,String> {

    @Query("{status:true}")
    List<DayWiseCampaignDao> getActive();




}
