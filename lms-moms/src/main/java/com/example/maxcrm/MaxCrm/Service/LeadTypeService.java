package com.example.maxcrm.MaxCrm.Service;

import com.example.maxcrm.MaxCrm.Dao.LeadTypeDao;

public interface LeadTypeService {
    LeadTypeDao insert(LeadTypeDao dao) throws Exception;
    LeadTypeDao update(LeadTypeDao dao) throws Exception;
    int findRandomUser(String type);
    void delete(int id);
    Iterable<LeadTypeDao>  findAll();
    Iterable<LeadTypeDao> findActive();
    LeadTypeDao findByName(String name);
}
