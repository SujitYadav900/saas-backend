package com.example.maxcrm.MaxCrm.Service;

import com.example.maxcrm.MaxCrm.Dao.LeadPriorityDao;

public interface LeadPriorityService {
    LeadPriorityDao insert(LeadPriorityDao dao) throws Exception;
    LeadPriorityDao update(LeadPriorityDao dao) throws Exception;
    int findRandomUser(String type);
    void delete(int id);
    Iterable<LeadPriorityDao>  findAll();
    Iterable<LeadPriorityDao> findActive();
}
