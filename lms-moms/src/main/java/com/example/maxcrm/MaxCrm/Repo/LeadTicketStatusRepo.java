package com.example.maxcrm.MaxCrm.Repo;

import com.example.maxcrm.MaxCrm.Dao.LeadTicketStatusDao;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface LeadTicketStatusRepo extends CrudRepository<LeadTicketStatusDao,Integer> {

    @Query("from LeadTicketStatusDao order by createat desc ")
    Iterable<LeadTicketStatusDao> findAll();
    @Query("from LeadTicketStatusDao where status=1")
    Iterable<LeadTicketStatusDao> findAllActive();

    @Query("from LeadTicketStatusDao where name=?1 and status=1")
   LeadTicketStatusDao findAllActiveByValue(String name);
}
