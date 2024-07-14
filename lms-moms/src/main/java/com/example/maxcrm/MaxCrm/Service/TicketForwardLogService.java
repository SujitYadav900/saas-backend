package com.example.maxcrm.MaxCrm.Service;

import com.example.maxcrm.MaxCrm.Dao.TicketForwardLogDao;

public interface TicketForwardLogService {

    TicketForwardLogDao insert(TicketForwardLogDao dao);


    Iterable<TicketForwardLogDao> findAll(long ticketId);
}
