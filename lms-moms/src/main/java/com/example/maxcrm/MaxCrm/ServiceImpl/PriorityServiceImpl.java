package com.example.maxcrm.MaxCrm.ServiceImpl;

import com.example.maxcrm.MaxCrm.Dao.PriorityDao;
import com.example.maxcrm.MaxCrm.Repo.PriorityRepo;
import com.example.maxcrm.MaxCrm.Service.PriorityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PriorityServiceImpl implements PriorityService {

    Logger logger = LoggerFactory.getLogger(PriorityServiceImpl.class);
    @Autowired
    PriorityRepo repo;


    @Override
    public PriorityDao insert(PriorityDao dao) throws Exception {
        logger.info("Saving PriorityDao {}",dao);
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
    public PriorityDao update(PriorityDao dao) throws Exception {
        logger.info("Updating PriorityDao {}",dao);
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
        logger.info("deleting PriorityDao wiht id",id);
            repo.deleteById(id);
    }

    @Override
    public Iterable<PriorityDao> getAll() {
        logger.info("finding all PriorityDao ");
        return repo.findAll();
    }

    @Override
    public Iterable<PriorityDao> getAllActive() {
        logger.info("finding all Active PriorityDao ");
        return repo.getAllActive();
    }

    private static String handleException(String message) {

        String retmsg = null;

        if (message.contains("[unqpriority]")) {
            retmsg = "Ticket Priority Already Exists!!";

        } else {
            retmsg = "Ticket Priority Cannot Be Created!!Please Try Again Later";
        }
        return retmsg;
    }
}
