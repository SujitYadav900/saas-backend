package com.example.maxcrm.MaxCrm.Repo;

import com.example.maxcrm.MaxCrm.Dao.TicketTypeDao;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface TicketTypeRepo extends CrudRepository<TicketTypeDao, Integer> {

    @Query("from TicketTypeDao where status=1")
    Iterable<TicketTypeDao> getAllActive();

    @Query("from TicketTypeDao order by createat desc")
    Iterable<TicketTypeDao> findAll();
}
