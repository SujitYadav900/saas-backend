package com.example.maxcrm.MaxCrm.Service;

import com.example.maxcrm.MaxCrm.Dao.CountryDao;

public interface CountryService {
    CountryDao insert(CountryDao dao);
    CountryDao update(CountryDao dao);
    void delete(String country);
    Iterable<CountryDao> getAll();
    Iterable<CountryDao> getAllActive();
}
