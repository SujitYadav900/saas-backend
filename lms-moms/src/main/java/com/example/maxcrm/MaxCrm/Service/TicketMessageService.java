package com.example.maxcrm.MaxCrm.Service;

import com.example.maxcrm.MaxCrm.Dao.TicketMessageDao;

import java.util.List;

public interface TicketMessageService {

    TicketMessageDao insert(TicketMessageDao dao);

    void delete(long id);
    Iterable<TicketMessageDao> findAll();
    List<TicketMessageDao> findAllByTicketId(long id, int offset, int limit);
}
