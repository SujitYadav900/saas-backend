package com.example.maxcrm.MaxCrm.Repo;

import com.example.maxcrm.MaxCrm.Dao.PropertyInnerDao;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface PropertyInnerRepository extends CrudRepository<PropertyInnerDao, Long> {

    @Query("from PropertyInnerDao where id=?1")
    PropertyInnerDao findPropetyInnerById(long id);

    @Query("from PropertyInnerDao where name=?1")
    PropertyInnerDao findPropetyInnerByName(String name);

    @Query("from PropertyInnerDao where propertyType=?1")
    Iterable<PropertyInnerDao> findbyPropertyType(String type);




}
