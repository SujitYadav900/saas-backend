package com.example.maxcrm.MaxCrm.Service;

import com.example.maxcrm.MaxCrm.Dao.StateDao;

public interface StateService {
    Iterable<StateDao> findAll();
    Iterable<StateDao> findByCountry(String country);

    StateDao insert(StateDao stateDao);
    StateDao update(StateDao stateDao);
    void delete(String state);
}
