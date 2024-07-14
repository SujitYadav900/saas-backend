package com.example.maxcrm.MaxCrm.Service;

import com.example.maxcrm.MaxCrm.Dao.ProductUserDao;

public interface ProductUserService {

    Iterable<ProductUserDao> getAll();
    ProductUserDao insert(ProductUserDao productUserDao) throws Exception;
    void delete(long id);

}
