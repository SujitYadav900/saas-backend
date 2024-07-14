package com.example.maxcrm.MaxCrm.ServiceImpl;

import com.example.maxcrm.MaxCrm.Dao.DayWiseCampaignDao;
import com.example.maxcrm.MaxCrm.Repo.DayWiseCampaignRepo;
import com.example.maxcrm.MaxCrm.Service.DayWiseCampaignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class DayWiseCampaignServiceImpl implements DayWiseCampaignService {

    @Autowired
    DayWiseCampaignRepo dayWiseCampaignRepo;
    @Override
    public DayWiseCampaignDao insert(DayWiseCampaignDao campaignDao) {
        return dayWiseCampaignRepo.insert(campaignDao);
    }

    @Override
    public DayWiseCampaignDao update(DayWiseCampaignDao campaignDao) {
        return dayWiseCampaignRepo.save(campaignDao);
    }

    @Override
    public void delete(String id) {
        dayWiseCampaignRepo.deleteById(id);

    }

    @Override
    public List<DayWiseCampaignDao> getAll() {
        return dayWiseCampaignRepo.findAll(  Sort.by(Sort.Direction.DESC,"id"));
    }

    @Override
    public List<DayWiseCampaignDao> getActive() {
        return dayWiseCampaignRepo.getActive();
    }
}
