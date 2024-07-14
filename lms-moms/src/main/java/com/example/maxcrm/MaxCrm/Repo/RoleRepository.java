package com.example.maxcrm.MaxCrm.Repo;

import com.example.maxcrm.MaxCrm.Dao.RoleDao;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface RoleRepository extends CrudRepository<RoleDao,Integer> {

    @Query("from RoleDao  where status=1 order by roleName asc")
    Iterable<RoleRepository> findActive();
    @Query("from RoleDao  order by id desc")
    Iterable<RoleDao> findAll();

    @Query("from RoleDao  where roleName=?1")
    RoleDao findByName(String roleName);



}
