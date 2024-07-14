package com.example.maxcrm.MaxCrm.Repo;

import com.example.maxcrm.MaxCrm.Dao.CountryDao;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface CountryRepository extends CrudRepository<CountryDao,String> {
    @Query("from  CountryDao  where status=1 order by country asc")
Iterable<CountryDao> findActive();

}
