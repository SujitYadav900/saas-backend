package com.example.maxcrm.MaxCrm.MBOPSRegistration.severities;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MBOPSSeverityServiceImpl implements MBOPSSeverityService{

    @Autowired
    private MBOPSSeverityRepo severityRepo;
     private final Logger logger = LoggerFactory.getLogger(getClass());


    @Override
    public MBOPSSeverityDao insert(MBOPSSeverityDao severityDao) {
        logger.info("Inserting severityDao {}",severityDao);
        return severityRepo.save(severityDao);
    }

    @Override
    public MBOPSSeverityDao update(MBOPSSeverityDao severityDao) {
        logger.info("updating severityDao {}",severityDao);
        return severityRepo.save(severityDao);
    }

    @Override
    public void delete(String name) {
        logger.info("deleting severityDao wiht name {}",name);
         severityRepo.deleteById(name);
    }

    @Override
    public List<MBOPSSeverityDao> getAll() {
        logger.info("finding all severityDao {}");
        return severityRepo.findAll();
    }

    @Override
    public MBOPSSeverityDao findByName(String severityName) {
        logger.info("updating severityDao with name {}",severityName);
        return severityRepo.findBySerName(severityName);
    }
}
