package com.example.maxcrm.MaxCrm.Service;

import com.example.maxcrm.MaxCrm.Dao.PropertyDao;

import java.util.List;

public interface PropertyService {

    PropertyDao insert(PropertyDao dao) throws Exception;
    PropertyDao update(PropertyDao dao) throws Exception;
    void delete(long id);
    Iterable<PropertyDao> getProperties();
    List<PropertyDao> getPropertyByType(String type);
    String findProperty(String type,String propertyName);

}
