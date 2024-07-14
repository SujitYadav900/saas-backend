package com.example.maxcrm.MaxCrm.MBOPSRegistration.clinicalDomains;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MBOPSClinicalDomainServiceImpl implements MBOPSClinicalDomainService{

    @Autowired
    private MBOPSClinicalDomainRepo domainRepo;
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public MBOPSClinicalDomainDao insert(MBOPSClinicalDomainDao clinicalDomainDao) {
        logger.info("Inserting MBOPSClinicalDomainDao {}",clinicalDomainDao);
        return domainRepo.save(clinicalDomainDao);
    }

    @Override
    public MBOPSClinicalDomainDao update(MBOPSClinicalDomainDao clinicalDomainDao) {
        logger.info("updating MBOPSClinicalDomainDao {}",clinicalDomainDao);
        return domainRepo.save(clinicalDomainDao);
    }

    @Override
    public void delete(int id) {
        logger.info("deleting MBOPSClinicalDomainDao {}",id);
        domainRepo.deleteById(id);
    }

    @Override
    public List<MBOPSClinicalDomainDao> getAll() {
        logger.info("finding all MBOPSClinicalDomainDao ");
        return domainRepo.findAll();
    }

    @Override
    public MBOPSClinicalDomainDao findByName(String domainName) {
        logger.info("finding MBOPSClinicalDomainDao with name {}",domainName);
        return domainRepo.findByClinical_domain(domainName);
    }
}
