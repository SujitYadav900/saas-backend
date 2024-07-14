package com.example.maxcrm.MaxCrm.Service;

import com.example.maxcrm.MaxCrm.Dao.TicketUpdateLogDao;

public interface TicketUpdateLogService {

    TicketUpdateLogDao insert(TicketUpdateLogDao dao);

    Iterable<TicketUpdateLogDao> findAll(long id);
}
