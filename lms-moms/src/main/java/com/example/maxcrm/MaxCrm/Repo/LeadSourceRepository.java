package com.example.maxcrm.MaxCrm.Repo;

import com.example.maxcrm.MaxCrm.Dao.LeadSourceDao;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface LeadSourceRepository extends CrudRepository<LeadSourceDao,Integer> {
    @Query("from LeadSourceDao  where status=1 order by name asc")
    Iterable<LeadSourceDao> findActive();
    @Query("from  LeadSourceDao  where name=?1 and status=1")
    LeadSourceDao findBySourceName(String leadSource);
    @Query("from LeadSourceDao  order by id desc")
    Iterable<LeadSourceDao> findAll();

    boolean existsByPhonenumbers(String phonenumber);

}
