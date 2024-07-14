package com.example.maxcrm.MaxCrm.Service;

import com.example.maxcrm.MaxCrm.Dao.CampaignTodayAccountPostingDao;

import java.util.List;

public interface CampaignTodayAccountPostingService {
    void saveData(CampaignTodayAccountPostingDao campaignTodayAccountPostingDao);
    void bulkUpload(List<CampaignTodayAccountPostingDao> campaignTodayAccountPostingDaos);

}
