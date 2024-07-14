package com.example.maxcrm.MaxCrm.Repo;

import com.example.maxcrm.MaxCrm.Dao.LogEventDao;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;
import java.util.List;

public interface LogEventRepo extends CrudRepository<LogEventDao,Long> {

    @Query("from LogEventDao  where leadId=?1 order by id desc")
    Iterable<LogEventDao> findByLeadId(long leadId);
 
    Iterable<LogEventDao> findByEventTypeAndUserId(String eventType,int userId);

    Iterable<LogEventDao> findByEventTypeAndUserIdIn(String eventType, List<Integer> userId);

}
