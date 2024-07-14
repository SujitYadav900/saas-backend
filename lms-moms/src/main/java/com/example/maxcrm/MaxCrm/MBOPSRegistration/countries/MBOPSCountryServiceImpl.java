package com.example.maxcrm.MaxCrm.MBOPSRegistration.countries;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MBOPSCountryServiceImpl implements MBOPSCountryService {

    @Autowired
    private MBOPSCountryRepo repo;
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Override
    public MBOPSCountryDao insert(MBOPSCountryDao dao) {
        logger.info("Inserting Country {}",dao);
        return repo.save(dao);
    }

    @Override
    public MBOPSCountryDao update(MBOPSCountryDao dao) {
        logger.info("Updating Country {}",dao);
        return repo.save(dao);
    }

    @Override
    public void delete(int id) {
        logger.info("deleting  Country wiht ID {}",id);
        repo.deleteById(id);
    }

    @Override
    public List<MBOPSCountryDao> getAll() {
        logger.info("Getting all Countries ");

        return repo.findAll();
    }

    @Override
    public MBOPSCountryDao findByName(String name) {

        logger.info("finding country doa with name {} ",name);
        return repo.findByName(name);
    }
}
