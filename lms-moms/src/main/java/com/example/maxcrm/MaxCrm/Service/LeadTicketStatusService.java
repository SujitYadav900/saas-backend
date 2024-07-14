package com.example.maxcrm.MaxCrm.Service;

import com.example.maxcrm.MaxCrm.Dao.LeadTicketStatusDao;

public interface LeadTicketStatusService {

    LeadTicketStatusDao insert(LeadTicketStatusDao dao) throws Exception;
    LeadTicketStatusDao update(LeadTicketStatusDao dao) throws Exception;
    Iterable<LeadTicketStatusDao> findAll();
    Iterable<LeadTicketStatusDao> findAllActive();
    void delete(int id);
    LeadTicketStatusDao findAllActiveByValue(String name);
}
