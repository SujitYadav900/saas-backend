package com.example.maxcrm.MaxCrm.Repo;

import com.example.maxcrm.MaxCrm.Dao.LeadTypeDao;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface LeadTypeRepository extends CrudRepository<LeadTypeDao,Integer> {
    @Query("from LeadTypeDao  where status=1 order by name asc")
    Iterable<LeadTypeDao> findActive();

    @Query("from LeadTypeDao  where name=?1")
   LeadTypeDao findByName(String name);

    @Query("from LeadTypeDao  order by id desc")
    Iterable<LeadTypeDao> findAll();
}
