package com.example.maxcrm.MaxCrm.Repo;

import com.example.maxcrm.MaxCrm.Dao.LeadTransferDao;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface LeadTransferRepo extends CrudRepository<LeadTransferDao,Long> {
    @Query("from LeadTransferDao  where leadId=?1 order by id desc")
    Iterable<LeadTransferDao> findByLeadId(long leadId);
}
