package com.example.maxcrm.MaxCrm.Repo;

import com.example.maxcrm.MaxCrm.Dao.ReportingDao;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface ReportingRepository  extends CrudRepository<ReportingDao,Long> {
    @Query("from ReportingDao  where userId=?1")
    Iterable<ReportingDao> getReportTo(int id);
}
