package com.example.maxcrm.MaxCrm.Service;

import com.example.maxcrm.MaxCrm.Dao.DayWiseCampaignDao;

import java.util.List;

public interface DayWiseCampaignService {
    DayWiseCampaignDao insert(DayWiseCampaignDao campaignDao);
    DayWiseCampaignDao update(DayWiseCampaignDao campaignDao);
    void delete(String id);
    List<DayWiseCampaignDao> getAll();
    List<DayWiseCampaignDao> getActive();
}
