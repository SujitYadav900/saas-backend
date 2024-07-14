package com.example.maxcrm.MaxCrm.ServiceImpl;

import com.example.maxcrm.MaxCrm.Dao.LeadTicketStatusDao;
import com.example.maxcrm.MaxCrm.Repo.LeadTicketStatusRepo;
import com.example.maxcrm.MaxCrm.Service.LeadTicketStatusService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LeadTicketStatusServiceImpl implements LeadTicketStatusService {

    Logger logger = LoggerFactory.getLogger(LeadTicketStatusServiceImpl.class);

    @Autowired
    private LeadTicketStatusRepo repo;

    @Override
    public LeadTicketStatusDao insert(LeadTicketStatusDao dao) throws Exception {
        logger.info("Saving Lead Ticket Status {}",dao);
        try {
            dao = repo.save(dao);
        } catch (Exception e) {
            logger.error("Error Occured!! {}", e);
            String message = handleException(e.getMessage());
            logger.error("Error Occured!! {}", message);
            throw new Exception(message);
        }
        return dao;
    }

    @Override
    public LeadTicketStatusDao update(LeadTicketStatusDao dao) throws Exception {
        logger.info("Updating Lead Ticket Status {}",dao);
        try {
            dao = repo.save(dao);
        } catch (Exception e) {
            logger.error("Error Occured!! {}", e);
            String message = handleException(e.getMessage());
            logger.error("Error Occured!! {}", message);
            throw new Exception(message);
        }
        return dao;
    }

    @Override
    public Iterable<LeadTicketStatusDao> findAll() {
        logger.info("finding All Lead Ticket Status ");
        return repo.findAll();
    }


    @Override
    public Iterable<LeadTicketStatusDao> findAllActive() {
        logger.info("finding All Active Lead Ticket Status ");
        return repo.findAllActive();
    }

    @Override
    public void delete(int id) {
        logger.info("Deleting Lead Ticket Status by id {}",id);
        repo.deleteById(id);
    }

    @Override
    public LeadTicketStatusDao findAllActiveByValue(String name) {
        logger.info("finding All Active Lead Ticket Status by value {}",name);
        return repo.findAllActiveByValue(name);
    }

    private static String handleException(String message) {

        String retmsg = null;

        if (message.contains("[unqticketstatus]")) {
            retmsg = "Ticket Status Already Exists!!";

        } else {
            retmsg = "Ticket Status Cannot Be Created!!Please Try Again Later";
        }
        return retmsg;
    }
}
