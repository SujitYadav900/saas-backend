package com.example.maxcrm.MaxCrm.Repo;

import com.example.maxcrm.MaxCrm.Dao.MenuDao;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface MenuRepository extends CrudRepository<MenuDao,Integer> {
    @Query("from MenuDao order by id desc")
    Iterable<MenuDao> findAll();

    @Query("from MenuDao where status=1 order by menuName asc")
    Iterable<MenuDao> findAllActive();
}
