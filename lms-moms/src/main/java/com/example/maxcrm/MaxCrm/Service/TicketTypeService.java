package com.example.maxcrm.MaxCrm.Service;

import com.example.maxcrm.MaxCrm.Dao.TicketTypeDao;

public interface TicketTypeService {

    TicketTypeDao insert(TicketTypeDao dao) throws Exception;
    TicketTypeDao update(TicketTypeDao dao) throws Exception;
    void delete(int id);
    Iterable<TicketTypeDao> getAll();
    Iterable<TicketTypeDao> getAllActive();

}
