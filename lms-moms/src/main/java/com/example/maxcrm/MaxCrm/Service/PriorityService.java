package com.example.maxcrm.MaxCrm.Service;

import com.example.maxcrm.MaxCrm.Dao.PriorityDao;

public interface PriorityService {

    PriorityDao insert(PriorityDao dao) throws Exception;
    PriorityDao update(PriorityDao dao) throws Exception;
    void delete(int id);
    Iterable<PriorityDao> getAll();
    Iterable<PriorityDao> getAllActive();
}
