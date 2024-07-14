package com.example.maxcrm.MaxCrm.Repo;

import com.example.maxcrm.MaxCrm.Dao.PriorityDao;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface PriorityRepo extends CrudRepository<PriorityDao, Integer> {

    @Query("from PriorityDao where status=1")
    Iterable<PriorityDao> getAllActive();

    @Query("from PriorityDao order by createat desc ")
    Iterable<PriorityDao> findAll();
}
