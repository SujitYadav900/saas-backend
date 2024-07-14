package com.example.maxcrm.MaxCrm.ServiceImpl;

import com.example.maxcrm.MaxCrm.Dao.LeadStatusDao;
import com.example.maxcrm.MaxCrm.Repo.LeadStatusRepository;
import com.example.maxcrm.MaxCrm.Service.LeadStatusService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LeadStatusServiceImpl implements LeadStatusService {
    Logger logger = LoggerFactory.getLogger(LeadStatusService.class);
    @Autowired
    LeadStatusRepository leadStatusRepository;

    @Override
    public LeadStatusDao insert(LeadStatusDao dao) throws Exception {
        logger.info("Inserting {}",dao);
        try {
            dao = leadStatusRepository.save(dao);
        } catch (Exception e) {
            logger.error("Error Occured!! {}", e);
            String message = handleException(e.getMessage());
            logger.error("Error Occured!! {}", message);
            throw new Exception(message);
        }
        return dao;
    }

    @Override
    public LeadStatusDao update(LeadStatusDao dao) throws Exception {
        logger.info("Updating {}",dao);
        try {
            dao = leadStatusRepository.save(dao);
        } catch (Exception e) {
            logger.error("Error Occured!! {}", e);
            String message = handleException(e.getMessage());
            logger.error("Error Occured!! {}", message);
            throw new Exception(message);
        }
        return dao;
    }

    @Override
    public Iterable<LeadStatusDao> findActive() {

        return leadStatusRepository.findActive();
    }
    private static String handleException(String message) {

        String retmsg = null;

        if (message.contains("[unqleadstatus]")) {
            retmsg = "Lead Status Already Exists!!";

        } else {
            retmsg = "Lead Status Cannot Be Created!!Please Try Again Later";
        }
        return retmsg;
    }
    @Override
    public Iterable<LeadStatusDao> findAll() {
        return leadStatusRepository.findAll();
    }

    @Override
    public void delete(int id) {
        logger.info("Deleting {}",id);
        leadStatusRepository.deleteById(id);
    }

    @Override
    public boolean leadStatusExist(String status) {
        return leadStatusRepository.existsLeadStatusDaoByName(status);
    }
}
