package com.example.maxcrm.MaxCrm.Repo;

import com.example.maxcrm.MaxCrm.Dao.LeadTypeUserDao;
import org.springframework.data.repository.CrudRepository;

public interface LeadTypeUserRepository extends CrudRepository<LeadTypeUserDao,Long> {
    //@Query("from LeadSourceUser  where lead_source_id=?1")
    //Iterable<LeadSourceUser> getAllBySourceId(long id);


}
