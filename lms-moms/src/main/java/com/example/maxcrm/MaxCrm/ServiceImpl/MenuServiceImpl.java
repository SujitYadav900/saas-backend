package com.example.maxcrm.MaxCrm.ServiceImpl;

import com.example.maxcrm.MaxCrm.Dao.MenuDao;
import com.example.maxcrm.MaxCrm.Repo.MenuRepository;
import com.example.maxcrm.MaxCrm.Service.MenuService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MenuServiceImpl implements MenuService {
    Logger logger = LoggerFactory.getLogger(MenuServiceImpl.class);
    @Autowired
    MenuRepository menuRepository;

    @Override
    public MenuDao insert(MenuDao dao) throws Exception {
        logger.info("Inserting {}",dao);
        try {
            dao = menuRepository.save(dao);
        } catch (Exception e) {
            logger.error("Error Occured!! {}", e);
            String message = handleException(e.getMessage());
            logger.error("Error Occured!! {}", message);
            throw new Exception(message);
        }
        return dao;
    }

    @Override
    public MenuDao update(MenuDao dao) throws Exception {
        logger.info("Updating {}",dao);
        try {
            dao = menuRepository.save(dao);
        } catch (Exception e) {
            logger.error("Error Occured!! {}", e);
            String message = handleException(e.getMessage());
            logger.error("Error Occured!! {}", message);
            throw new Exception(message);
        }
        return dao;
    }

    @Override
    public Iterable<MenuDao> getAll() {
        return menuRepository.findAll();
    }

    @Override
    public void delete(int id) {
        logger.info("Deleting {}",id);
        menuRepository.deleteById(id);
    }

    private static String handleException(String message) {

        String retmsg = null;

        if (message.contains("[unqmenuname]")) {
            retmsg = "Menu Name Already Exists!!";

        } else {
            retmsg = "Menu Name Cannot Be Created!!Please Try Again Later";
        }
        return retmsg;
    }
}
