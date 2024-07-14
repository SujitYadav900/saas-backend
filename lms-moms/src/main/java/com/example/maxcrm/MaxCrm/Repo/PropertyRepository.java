package com.example.maxcrm.MaxCrm.Repo;

import com.example.maxcrm.MaxCrm.Dao.PropertyDao;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PropertyRepository extends CrudRepository<PropertyDao,Long> {


    @Query("from PropertyDao where type=?1")
    List<PropertyDao> getPropertyByType(String type);

    @Query("from PropertyDao order by createat desc")
    List<PropertyDao> findAll();
}
