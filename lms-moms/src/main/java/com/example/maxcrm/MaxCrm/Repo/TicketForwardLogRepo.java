package com.example.maxcrm.MaxCrm.Repo;

import com.example.maxcrm.MaxCrm.Dao.TicketForwardLogDao;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface TicketForwardLogRepo extends CrudRepository<TicketForwardLogDao, Long> {

    @Query("from TicketForwardLogDao  where ticketid=?1 order by id desc")
    Iterable<TicketForwardLogDao> findByTicketId(long id);

}
