package com.example.maxcrm.MaxCrm.Service;

import com.example.maxcrm.MaxCrm.Dao.LeadTypeUserDao;

public interface LeadTypeUserService {

    Iterable<LeadTypeUserDao> getAll();
    LeadTypeUserDao insert(LeadTypeUserDao leadTypeUserDao) throws Exception;
    void delete(long id);

}
