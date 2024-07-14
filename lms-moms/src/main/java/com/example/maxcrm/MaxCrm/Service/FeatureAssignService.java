package com.example.maxcrm.MaxCrm.Service;

import com.example.maxcrm.MaxCrm.Dao.FeatureAsssignDao;
import com.example.maxcrm.MaxCrm.Dao.RoleDao;
import org.springframework.data.jpa.repository.Query;

import java.util.HashMap;
import java.util.List;

public interface FeatureAssignService  {
    FeatureAsssignDao insert(FeatureAsssignDao featureAsssignDao);
    void delete(int id);





}
