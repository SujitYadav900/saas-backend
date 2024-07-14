package com.example.maxcrm.MaxCrm.Repo;

import com.example.maxcrm.MaxCrm.Dao.LeadPriorityUserDao;
import org.springframework.data.repository.CrudRepository;

public interface LeadPriorityUserRepository extends CrudRepository<LeadPriorityUserDao,Long> {
    //@Query("from LeadSourceUser  where lead_source_id=?1")
    //Iterable<LeadSourceUser> getAllBySourceId(long id);


}
