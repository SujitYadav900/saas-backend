package com.example.maxcrm.MaxCrm.Repo;

import com.example.maxcrm.MaxCrm.Dao.FeaturesDao;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface FeatureRepo extends CrudRepository<FeaturesDao,Integer> {
    @Query("from FeaturesDao  order by id desc ")
    Iterable<FeaturesDao> findBy();
    @Query("from FeaturesDao  where status=1 order by id desc")
    Iterable<FeaturesDao> findAllActive();
}
