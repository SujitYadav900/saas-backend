package com.example.maxcrm.MaxCrm.Service;

import com.example.maxcrm.MaxCrm.Dao.CityDao;

public interface CityService {
    Iterable<CityDao> findAll();
    Iterable<CityDao> findByState(String state);
    CityDao insert(CityDao cityDao);
    CityDao update(CityDao cityDao);
    void delete(String city);
}
