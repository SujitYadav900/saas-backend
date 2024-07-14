package com.example.maxcrm.MaxCrm.Repo;

import com.example.maxcrm.MaxCrm.Dao.LeadSourceUser;
import org.springframework.data.repository.CrudRepository;

public interface LeadSourceUserRepository extends CrudRepository<LeadSourceUser,Long> {
    //@Query("from LeadSourceUser  where lead_source_id=?1")
    //Iterable<LeadSourceUser> getAllBySourceId(long id);


}
