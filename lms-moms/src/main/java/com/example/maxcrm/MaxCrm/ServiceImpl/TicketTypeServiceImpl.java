package com.example.maxcrm.MaxCrm.ServiceImpl;

import com.example.maxcrm.MaxCrm.Dao.TicketTypeDao;
import com.example.maxcrm.MaxCrm.Repo.TicketTypeRepo;
import com.example.maxcrm.MaxCrm.Service.TicketTypeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TicketTypeServiceImpl implements TicketTypeService {

    Logger logger = LoggerFactory.getLogger(TicketTypeServiceImpl.class);

    @Autowired
    TicketTypeRepo repo;

    @Override
    public TicketTypeDao insert(TicketTypeDao dao) throws Exception {
        logger.info("saving TicketTypeDao {} ",dao);
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
    public TicketTypeDao update(TicketTypeDao dao) throws Exception {
        logger.info("updating TicketTypeDao {} ",dao);
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
    public void delete(int id) {
        logger.info("deleting TicketTypeDao by id{} ",id);
            repo.deleteById(id);
    }

    @Override
    public Iterable<TicketTypeDao> getAll() {
        logger.info("finding all TicketTypeDao");

        return repo.findAll();
    }

    @Override
    public Iterable<TicketTypeDao> getAllActive() {
        logger.info("finding all active TicketTypeDao ");
        return repo.getAllActive();
    }

    private static String handleException(String message) {

        String retmsg = null;

        if (message.contains("[unqtickettype]")) {
            retmsg = "Ticket Type Already Exists!!";

        } else {
            retmsg = "Tciket Type Cannot Be Created!!Please Try Again Later";
        }
        return retmsg;
    }

}
