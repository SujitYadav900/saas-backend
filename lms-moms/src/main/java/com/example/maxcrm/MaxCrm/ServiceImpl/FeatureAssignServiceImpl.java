package com.example.maxcrm.MaxCrm.ServiceImpl;

import com.example.maxcrm.MaxCrm.Dao.FeatureAsssignDao;
import com.example.maxcrm.MaxCrm.Repo.FeatureAssignRepo;
import com.example.maxcrm.MaxCrm.Service.FeatureAssignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FeatureAssignServiceImpl implements FeatureAssignService {
    @Autowired
    FeatureAssignRepo featureAssignRepo;

    @Override
    public FeatureAsssignDao insert(FeatureAsssignDao featureAsssignDao) {
        return featureAssignRepo.save(featureAsssignDao);
    }

    @Override
    public void delete(int id) {
        featureAssignRepo.deleteById(id);
    }


}
