package com.example.maxcrm.MaxCrm.Service;

import com.example.maxcrm.MaxCrm.Dao.UrlAccessDao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface UrlAccessService {
    UrlAccessDao insert(UrlAccessDao urlAccessDao) throws Exception;
    UrlAccessDao update(UrlAccessDao urlAccessDao) throws Exception;
    ArrayList<UrlAccessDao> findAll() throws SQLException;
    Iterable<UrlAccessDao> findAllActive();
    void delete(int id);
    List<UrlAccessDao> getMatchers() throws SQLException;
}
