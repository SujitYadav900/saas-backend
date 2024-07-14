package com.example.maxcrm.MaxCrm.ServiceImpl;

import com.example.maxcrm.MaxCrm.Dao.CampaignTodayAccountPostingDao;
import com.example.maxcrm.MaxCrm.Repo.CampaignTodayAccountPostingRepo;
import com.example.maxcrm.MaxCrm.Service.CampaignTodayAccountPostingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
    public class CampaignTodayAccountPostingServiceImpl implements CampaignTodayAccountPostingService {
    @Autowired
    CampaignTodayAccountPostingRepo campaignTodayAccountPostingRepo;
    Logger logger = LoggerFactory.getLogger(CampaignTodayAccountPostingServiceImpl.class);
    @Override
    @Async("PersistMessage")
    public void saveData(CampaignTodayAccountPostingDao campaignTodayAccountPostingDao) {
        logger.info("Inserting {}",campaignTodayAccountPostingDao);
        campaignTodayAccountPostingRepo.save(campaignTodayAccountPostingDao);
    }

    @Override
    @Async("PersistMessage")
    public void bulkUpload(List<CampaignTodayAccountPostingDao> campaignTodayAccountPostingDaos) {
        campaignTodayAccountPostingRepo.saveAll(campaignTodayAccountPostingDaos);

    }


}
