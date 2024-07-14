package com.example.maxcrm.MaxCrm.MBOPSRegistration.grades;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MBOPSGradeServiceImpl implements MBOPSGradesService{

    @Autowired
    private MBOPSGradesRepo gradesRepo;
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public MBOPSGradesDao insert(MBOPSGradesDao gradesDao) {
        logger.info("inserting gradesDao {}",gradesDao);
        return gradesRepo.save(gradesDao);
    }

    @Override
    public MBOPSGradesDao update(MBOPSGradesDao gradesDao) {
        logger.info("updating gradesDao {}",gradesDao);
        return gradesRepo.save(gradesDao);
    }

    @Override
    public void delete(int id) {
        logger.info("deleting gradesDao with id{}",id);
        gradesRepo.deleteById(id);
    }

    @Override
    public List<MBOPSGradesDao> getAll() {
        logger.info("finding all gradesDao ");
        return gradesRepo.findAll();
    }

    @Override
    public MBOPSGradesDao findByName(String gradeName) {
        logger.info("finding gradesDao with name  {}",gradeName);
        return gradesRepo.findByName(gradeName);
    }
}
