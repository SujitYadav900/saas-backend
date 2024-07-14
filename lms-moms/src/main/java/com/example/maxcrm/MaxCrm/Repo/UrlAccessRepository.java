package com.example.maxcrm.MaxCrm.Repo;

import com.example.maxcrm.MaxCrm.Dao.UrlAccessDao;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface UrlAccessRepository extends CrudRepository<UrlAccessDao,Integer> {
    @Query("from UrlAccessDao where status=1 order by roleName asc")
    Iterable<UrlAccessDao> getActive();
}
