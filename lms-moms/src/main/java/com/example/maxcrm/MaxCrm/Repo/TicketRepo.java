package com.example.maxcrm.MaxCrm.Repo;

import com.example.maxcrm.MaxCrm.Dao.TicketDao;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface TicketRepo extends CrudRepository<TicketDao, Long> {

    //@Query("from LeadSourceUser  where lead_source_id=?1")
    //Iterable<LeadSourceUser> getAllBySourceId(long id);

    @Query("from TicketDao where lastforward=?1 and createdby=?2")
    Iterable<TicketDao> getAllByCreator(String id,String id2);











}
