package com.example.maxcrm.MaxCrm.Repo;

import com.example.maxcrm.MaxCrm.Dao.TicketUpdateLogDao;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface TicketUpdateLogRepo extends CrudRepository<TicketUpdateLogDao,Integer> {
    @Query("from TicketUpdateLogDao where ticketid=?1 order by id desc")
    Iterable<TicketUpdateLogDao> findByTicketId(long id);
}
