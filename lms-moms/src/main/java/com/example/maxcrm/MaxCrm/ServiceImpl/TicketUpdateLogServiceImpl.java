package com.example.maxcrm.MaxCrm.ServiceImpl;

import com.example.maxcrm.MaxCrm.Dao.TicketUpdateLogDao;
import com.example.maxcrm.MaxCrm.Repo.TicketUpdateLogRepo;
import com.example.maxcrm.MaxCrm.Service.TicketUpdateLogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TicketUpdateLogServiceImpl implements TicketUpdateLogService {

    Logger logger = LoggerFactory.getLogger(TicketUpdateLogServiceImpl.class);

    @Autowired
    TicketUpdateLogRepo repo;


    @Override
    public TicketUpdateLogDao insert(TicketUpdateLogDao dao) {
        logger.info("Saving ticketUpdateLogDao {}", dao);
        return repo.save(dao);
    }


    @Override
    public Iterable<TicketUpdateLogDao> findAll(long id) {
         logger.info("finding all TicketUpdateDaos ");
        return repo.findByTicketId(id);
    }
}
