package com.example.maxcrm.MaxCrm.Repo;

import com.example.maxcrm.MaxCrm.Dao.DepartmentDao;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface DepartmentRepo  extends CrudRepository<DepartmentDao, Integer> {

    @Query("from DepartmentDao where status=1")
    Iterable<DepartmentDao> getAllActive();
}
