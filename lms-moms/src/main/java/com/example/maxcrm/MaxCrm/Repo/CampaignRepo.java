package com.example.maxcrm.MaxCrm.Repo;

import com.example.maxcrm.MaxCrm.Dao.CampaignDocument;
import com.example.maxcrm.MaxCrm.Dao.LeadMasterDaoCampaignDao;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface CampaignRepo extends MongoRepository<CampaignDocument,String> {
    @Query( value="{createBy:?0}", fields="{ al : 0 ,leadMasterDaoCampaignDao: 0 ,shortenUrl:0}")
    List<CampaignDocument> findAllByCreateBy(String createBy, Sort sort);
    @Query( value="{templateId:?0}", fields="{ al : 0 ,leadMasterDaoCampaignDao: 0,shortenUrl:0 }")
    List<CampaignDocument> findByTemplate(String templateId, Sort sort);

    @Query(value="{id:?0}", fields="{ url : 1  }")
    CampaignDocument findUrlById(String campaignId);

    @Query(value = "{status:0,'schduleLong':{  $lt: ?0 }}")
    List<CampaignDocument> findByCampaignDate(long date, Pageable pageable);

    @Query(value = "{id:?0,'leadMasterDaoCampaignDao.open':?1}", fields = "{leadMasterDaoCampaignDao: 1}")
    List<LeadMasterDaoCampaignDao> findByStatusAndId(String id,byte sts);






}
