package com.example.maxcrm.MaxCrm.Service;

import com.example.maxcrm.MaxCrm.Dao.FeaturesDao;

import java.util.HashMap;
import java.util.List;

public interface FeatureService {
    FeaturesDao insertFeatureDao(FeaturesDao featuresDao);
    FeaturesDao updateFeatureDao(FeaturesDao featuresDao);
    Iterable<FeaturesDao> getAllFeature();
    Iterable<FeaturesDao> getAllActive();
    HashMap<String,String> getAssignedRole(String role);
    List<FeaturesDao> getRoleByRoleId(int role);
}
