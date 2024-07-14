package com.example.maxcrm.MaxCrm.MBOPSRegistration.states;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MBOPSStateServiceImpl implements MBOPSStateService {

    @Autowired
    private MBOPSStateRepo repo;
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Override
    public MBOPSStateDao insert(MBOPSStateDao dao) {
        logger.info("Inserting StateDao {}",dao);
        return repo.save(dao);
    }

    @Override
    public MBOPSStateDao update(MBOPSStateDao dao) {
        logger.info("Updating StateDao {}",dao);
        return repo.save(dao);
    }

    @Override
    public void delete(int id) {
        logger.info("deleting  StateDao wiht ID {}",id);
        repo.deleteById(id);
    }

    @Override
    public List<MBOPSStateDao> getAll() {
        logger.info("Getting all StateDao ");
        return repo.findAll();
    }

    @Override
    public MBOPSStateDao findByName(String name) {
        logger.info("finding stateDao with name {}",name);
        return repo.findByName(name);
    }

    @Override
    public List<MBOPSStateDao> findByCountryId(int id) {
        logger.info("finding stateDao with country ID {}",id);
        return repo.findByCountryId(id);
    }

}
