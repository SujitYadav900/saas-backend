package com.example.maxcrm.MaxCrm.Service;

import com.example.maxcrm.MaxCrm.Dao.DepartmentDao;

public interface DepartmentService {

    DepartmentDao insert(DepartmentDao dao);
    DepartmentDao update(DepartmentDao dao);

    void delete(int id);
    Iterable<DepartmentDao> getAll();
    Iterable<DepartmentDao> getAllActive();
}
