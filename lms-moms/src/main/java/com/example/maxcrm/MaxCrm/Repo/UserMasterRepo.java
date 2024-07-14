package com.example.maxcrm.MaxCrm.Repo;

import com.example.maxcrm.MaxCrm.Dao.UserMasterDao;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserMasterRepo extends CrudRepository<UserMasterDao,Integer> {
    @Query("from UserMasterDao  where department=?1 order by firstName asc ")
    Iterable<UserMasterDao> getUserByDepartment(String department);

    @Query("from UserMasterDao  where reportTo=?1 order by firstName asc ")
    Iterable<UserMasterDao> getUserByReportsTo(String reporTo);

    @Query("from UserMasterDao  where id = :ids order by firstName asc ")
    Iterable<UserMasterDao> findByReportList(@Param("ids")List<Integer>  idlist);

    @Query("from UserMasterDao order by id desc")
    Iterable<UserMasterDao> findAll();

    @Query("from UserMasterDao where status='active' order by firstName asc ")
    Iterable<UserMasterDao> findAllActive();

    @Query("from UserMasterDao  where username=?1 or email=?1 or mobile=?1 ")
    UserMasterDao findByUsername(String username);

    @Query("from UserMasterDao  where username=?1 or email=?1 or mobile=?1")
    UserMasterDao findUserForRecovery(String value);


}
