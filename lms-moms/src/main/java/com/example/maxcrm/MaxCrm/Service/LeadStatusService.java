package com.example.maxcrm.MaxCrm.Service;

import com.example.maxcrm.MaxCrm.Dao.LeadStatusDao;

public interface LeadStatusService {
    LeadStatusDao insert(LeadStatusDao leadStatusDao) throws Exception;
    LeadStatusDao update(LeadStatusDao leadStatusDao) throws Exception;
    Iterable<LeadStatusDao> findActive();
    Iterable<LeadStatusDao> findAll();
    void delete(int id);

    boolean leadStatusExist(String status);
}
