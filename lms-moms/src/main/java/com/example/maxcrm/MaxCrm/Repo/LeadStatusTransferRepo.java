package com.example.maxcrm.MaxCrm.Repo;

import com.example.maxcrm.MaxCrm.Dao.LeadStatusTransferDao;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface LeadStatusTransferRepo extends CrudRepository<LeadStatusTransferDao,Long> {
    @Query("from LeadStatusTransferDao  where leadId=?1 order by id desc")
    Iterable<LeadStatusTransferDao> findAllByLeadId(long leadId);
}
