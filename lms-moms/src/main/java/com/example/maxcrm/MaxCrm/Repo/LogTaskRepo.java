package com.example.maxcrm.MaxCrm.Repo;

import com.example.maxcrm.MaxCrm.Dao.LogTaskDao;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface LogTaskRepo extends CrudRepository<LogTaskDao,Long> {
    @Query("from LogTaskDao  where leadId=?1 order by id desc")
    Iterable<LogTaskDao> findAllByLeadId(long leadId);
}
