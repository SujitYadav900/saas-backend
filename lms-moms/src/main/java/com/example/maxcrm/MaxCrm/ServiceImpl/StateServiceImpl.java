package com.example.maxcrm.MaxCrm.ServiceImpl;

import com.example.maxcrm.MaxCrm.Dao.StateDao;
import com.example.maxcrm.MaxCrm.Repo.StateRepository;
import com.example.maxcrm.MaxCrm.Service.StateService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Service
public class StateServiceImpl implements StateService {
    Logger logger = LoggerFactory.getLogger(StateService.class);
    @Autowired
    StateRepository stateRepository;

    @PersistenceContext
    EntityManager manager;

    @Override
    public Iterable<StateDao> findAll() {
        return stateRepository.findAll();
    }

    @Override
    public Iterable<StateDao> findByCountry(String country) {
        logger.info("Finding State By Country {}", country);

        return stateRepository.findByCountry(country);
    }


    @Override
    public StateDao insert(StateDao stateDao) {
        logger.info("Inserting {}", stateDao);
        return stateRepository.save(stateDao);
    }

    @Override
    public StateDao update(StateDao stateDao) {
        logger.info("Updating {}", stateDao);
        return stateRepository.save(stateDao);
    }

    @Override
    public void delete(String state) {
        logger.info("Deleting {}", state);
        stateRepository.deleteById(state);
    }
}
