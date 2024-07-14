package com.example.maxcrm.MaxCrm.Service;

import com.example.maxcrm.MaxCrm.Dao.ProductMasterDao;

public interface ProductMasterService {

    Iterable<ProductMasterDao> findAll();
    Iterable<ProductMasterDao> findAllAvailable();
    ProductMasterDao insert(ProductMasterDao productMasterDao) throws Exception;
    ProductMasterDao update(ProductMasterDao productMasterDao ) throws Exception;
    int findRandomUser(String product);
    void delete(int id);
    ProductMasterDao findByName(String productName);
}
