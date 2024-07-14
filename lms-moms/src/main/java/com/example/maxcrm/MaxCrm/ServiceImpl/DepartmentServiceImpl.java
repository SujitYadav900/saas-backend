package com.example.maxcrm.MaxCrm.ServiceImpl;

import com.example.maxcrm.MaxCrm.Dao.DepartmentDao;
import com.example.maxcrm.MaxCrm.Repo.DepartmentRepo;
import com.example.maxcrm.MaxCrm.Service.DepartmentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    Logger logger = LoggerFactory.getLogger(DepartmentServiceImpl.class);
    @Autowired
    DataSource dataSource;

    @Autowired
    DepartmentRepo repo;

    @Override
    public DepartmentDao insert(DepartmentDao dao) {
        logger.info("saving DepartmentDao {}",dao);
        return repo.save(dao);
    }

    @Override
    public DepartmentDao update(DepartmentDao dao) {
        logger.info("updating DepartmentDao {}",dao);
        return repo.save(dao);
    }



    @Override
    public void delete(int id) {
        logger.info("Deleting DepartmentDao with id {}",id);
        repo.deleteById(id);
    }

    @Override
    public Iterable<DepartmentDao> getAll() {
        logger.info("finding all DepartmentDao ");
        return repo.findAll();
    }

    @Override
    public Iterable<DepartmentDao> getAllActive() {
        logger.info("finding all active DepartmentDao");
        return repo.getAllActive();
    }
}
