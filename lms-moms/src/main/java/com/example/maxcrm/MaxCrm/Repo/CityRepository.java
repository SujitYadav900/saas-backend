package com.example.maxcrm.MaxCrm.Repo;

import com.example.maxcrm.MaxCrm.Dao.CityDao;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface CityRepository extends CrudRepository<CityDao,String> {
    @Query("from CityDao  where status=1 and stateName=?1 order by cityName asc")
    Iterable<CityDao> findCityByState(String state);
}
