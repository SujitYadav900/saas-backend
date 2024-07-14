package com.example.maxcrm.MaxCrm.Repo;

import com.example.maxcrm.MaxCrm.Dao.RolePageDao;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface RolePageRepository extends CrudRepository<RolePageDao,Long> {
    @Query("from RolePageDao where roleId=?1 order by id desc")
    Iterable<RolePageDao> getByRole(int role);
}
