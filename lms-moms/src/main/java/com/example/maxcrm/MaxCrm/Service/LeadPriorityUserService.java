package com.example.maxcrm.MaxCrm.Service;


import com.example.maxcrm.MaxCrm.Dao.LeadPriorityUserDao;

public interface LeadPriorityUserService {

    Iterable<LeadPriorityUserDao> getAll();
    LeadPriorityUserDao insert(LeadPriorityUserDao priorityUserDao) throws Exception;
    void delete(long id);

}
