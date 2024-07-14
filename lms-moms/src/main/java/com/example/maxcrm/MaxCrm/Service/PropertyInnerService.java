package com.example.maxcrm.MaxCrm.Service;

import com.example.maxcrm.MaxCrm.Dao.PropertyInnerDao;

import java.sql.SQLException;
import java.util.List;

public interface PropertyInnerService {

    PropertyInnerDao insert(PropertyInnerDao dao) throws Exception;
    PropertyInnerDao update(PropertyInnerDao dao) throws Exception;
    void delete(long id);

    List<PropertyInnerDao> getAll(String type) throws SQLException;
    PropertyInnerDao findPropetyInnerById(long id);
    PropertyInnerDao findPropetyInnerByName(String name);
    Iterable<PropertyInnerDao> findAllByPropertyType(String type);
   void refreshProperty() throws SQLException;
}
