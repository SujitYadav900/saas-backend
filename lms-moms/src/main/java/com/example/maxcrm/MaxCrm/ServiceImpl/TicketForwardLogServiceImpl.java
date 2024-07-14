package com.example.maxcrm.MaxCrm.ServiceImpl;

import com.example.maxcrm.MaxCrm.Dao.TicketForwardLogDao;
import com.example.maxcrm.MaxCrm.Repo.TicketForwardLogRepo;
import com.example.maxcrm.MaxCrm.Service.TicketForwardLogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TicketForwardLogServiceImpl implements TicketForwardLogService {

    Logger logger = LoggerFactory.getLogger(TicketForwardLogServiceImpl.class);

    @Autowired
    TicketForwardLogRepo repo;

    @Override
    public TicketForwardLogDao insert(TicketForwardLogDao dao) {
        logger.info("Saving TicketForwardLogDao {}",dao);
        return repo.save(dao);
    }



    @Override
    public Iterable<TicketForwardLogDao> findAll(long ticketId) {
        logger.info("finding all TicketForwardLogDao");
        return repo.findByTicketId(ticketId);
    }
}
