package com.example.maxcrm.MaxCrm.Repo;

import com.example.maxcrm.MaxCrm.Dao.LeadStatusDao;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface LeadStatusRepository extends CrudRepository<LeadStatusDao,Integer> {
    @Query("from LeadStatusDao  where status=1 order by name asc")
    Iterable<LeadStatusDao> findActive();

    @Query("from LeadStatusDao  order by id desc")
    Iterable<LeadStatusDao> findAll();

    boolean existsLeadStatusDaoByName(String name);

}
