package com.example.maxcrm.MaxCrm.Repo;

import com.example.maxcrm.MaxCrm.Dao.LeadPriorityDao;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface LeadPriorityRepository extends CrudRepository<LeadPriorityDao,Integer> {
    @Query("from LeadPriorityDao  where status=1 order by name asc")
    Iterable<LeadPriorityDao> findActive();
    @Query("from LeadPriorityDao  order by id desc")
    Iterable<LeadPriorityDao> findAll();
}
