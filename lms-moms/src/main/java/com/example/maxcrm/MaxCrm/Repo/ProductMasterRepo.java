package com.example.maxcrm.MaxCrm.Repo;

import com.example.maxcrm.MaxCrm.Dao.ProductMasterDao;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface ProductMasterRepo extends CrudRepository<ProductMasterDao, Integer> {

    @Query("from ProductMasterDao where status=1")
    Iterable<ProductMasterDao> findAllAvailable();

    @Query("from ProductMasterDao order by id desc")
    Iterable<ProductMasterDao> findAll();

    ProductMasterDao findByName(String productName);



}
