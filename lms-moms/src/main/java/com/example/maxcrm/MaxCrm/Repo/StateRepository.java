package com.example.maxcrm.MaxCrm.Repo;

import com.example.maxcrm.MaxCrm.Dao.StateDao;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface StateRepository extends CrudRepository<StateDao,String> {
    @Query("from StateDao where status=1 and country=?1 order by stateName asc")
    Iterable<StateDao> findByCountry(String country);

}
