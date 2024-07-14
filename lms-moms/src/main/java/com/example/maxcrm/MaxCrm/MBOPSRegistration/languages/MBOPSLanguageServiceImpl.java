package com.example.maxcrm.MaxCrm.MBOPSRegistration.languages;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MBOPSLanguageServiceImpl implements MBOPSLanguageService {
    @Autowired
    private MBOPSLanguageRepo repo;
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Override
    public MBOPSLanguageDao insert(MBOPSLanguageDao dao) {
        logger.info("Inserting LanguageDao {}",dao);
        return repo.save(dao);
    }

    @Override
    public MBOPSLanguageDao update(MBOPSLanguageDao dao) {
        logger.info("Updating LanguageDao {}",dao);
        return repo.save(dao);
    }

    @Override
    public void delete(int id) {
        logger.info("deleting  LanguageDao with ID {}",id);
        repo.deleteById(id);
    }

    @Override
    public List<MBOPSLanguageDao> getAll() {
        logger.info("Getting all LanguageDao ");
        return repo.findAll();
    }
}
