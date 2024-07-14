package com.example.maxcrm.MaxCrm.Repo;

import com.example.maxcrm.MaxCrm.Dao.LoginHistoryDao;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface LoginHistoryRepo extends CrudRepository<LoginHistoryDao,Long> {
    @Query("from LoginHistoryDao  where userId=?1 order by id desc")
    List<LoginHistoryDao> getAllByUserId(int userId);

    @Query("from LoginHistoryDao  where userId=?1 order by id desc")
    LoginHistoryDao getAllByUserIdOne(int userId);
}
