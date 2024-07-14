package com.example.maxcrm.MaxCrm.ServiceImpl;

import com.example.maxcrm.MaxCrm.Dao.RolePageDao;
import com.example.maxcrm.MaxCrm.Repo.RolePageRepository;
import com.example.maxcrm.MaxCrm.Service.RolePageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RolePageServiceImpl implements RolePageService {
    Logger logger = LoggerFactory.getLogger(RolePageService.class);
    @Autowired
    RolePageRepository rolePageRepository;

    @Override
    public RolePageDao insert(RolePageDao roleDao) throws Exception {
        logger.info("Inserting {}",roleDao);
        try{
            roleDao= rolePageRepository.save(roleDao);
        }catch (Exception ew)
        {
            throw new Exception("Already Exists!!");
        }
        return roleDao;

    }

    @Override
    public RolePageDao update(RolePageDao roleDao) {
        logger.info("Updating {}",roleDao);
        return rolePageRepository.save(roleDao);
    }

    @Override
    public void delete(long id) {
        logger.info("Deleting {}",id);
        rolePageRepository.deleteById(id);
    }

    @Override
    public Iterable<RolePageDao> getAll() {
        return rolePageRepository.findAll();
    }

    @Override
    public Iterable<RolePageDao> getByRole(int role) {
        return rolePageRepository.getByRole(role);
    }
}
