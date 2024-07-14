package com.example.maxcrm.MaxCrm.ServiceImpl;

import com.example.maxcrm.MaxCrm.Dao.PropertyDao;
import com.example.maxcrm.MaxCrm.Repo.PropertyRepository;
import com.example.maxcrm.MaxCrm.Service.PropertyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PropertyServiceImpl extends PropertyCls implements PropertyService {

    private final Logger logger = LoggerFactory.getLogger(PropertyServiceImpl.class);

    @Autowired
    private PropertyRepository repository;
    @Override
    public PropertyDao insert(PropertyDao dao) throws Exception {
        logger.info("inserting property dao {}",dao.toString());
        logger.info("doa.type {}",dao.getType());
        if(dao.getType().contains(",||") ){
            throw new Exception("Please Remove Illegal characterset ',||'");
        }
        try {
            dao = repository.save(dao);
        }catch (Exception e){
            logger.error("Error Occured!! {}", e);
            String message = handleException(e.getMessage());
            logger.error("Error Occured!! {}", message);
            throw new Exception(message);
        }
        return dao;
    }

    @Override
    public PropertyDao update(PropertyDao dao) throws Exception {
        logger.info("updating property dao {}",dao.toString());
        if(dao.getType().contains("\\,\\|\\|") ){
            throw new Exception("Please Remove Illegal characterset '\\,\\|\\|'");
        }
        try {
            dao = repository.save(dao);
        }catch (Exception e){
            logger.error("Error Occured!! {}", e);
            String message = handleException(e.getMessage());
            logger.error("Error Occured!! {}", message);
            throw new Exception(message);
        }
        return dao;
    }

    @Override
    public void delete(long id) {
        logger.info("deleting property with id {}",id);
        repository.deleteById(id);
    }

    @Override
    public Iterable<PropertyDao> getProperties() {
        logger.info("finding all property dao ");
        return repository.findAll();

    }

    @Override
    public List<PropertyDao> getPropertyByType(String type) {

        logger.info("finding all property dao by type {}",type);
        return repository.getPropertyByType(type);
    }



    @Override
    public  String findProperty(String type, String propertyName)  {

        return propertieslist.get(type+"_"+propertyName);

    }

    private static String handleException(String message) {

        String retmsg = null;

        if (message.contains("[unqpropertytype]")) {
            retmsg = "Property Already Exists!!";

        } else {
            retmsg = "Property Cannot Be Created!!Please Try Again Later";
        }
        return retmsg;
    }
}
