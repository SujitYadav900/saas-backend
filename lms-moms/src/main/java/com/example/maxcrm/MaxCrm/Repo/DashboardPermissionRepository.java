package com.example.maxcrm.MaxCrm.Repo;

import com.example.maxcrm.MaxCrm.Dao.DashboardPermissionDao;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface DashboardPermissionRepository extends CrudRepository<DashboardPermissionDao,Long> {

    @Query("from DashboardPermissionDao where roleId=?1")
    List<DashboardPermissionDao> findAllByRoleId(long roleId);


}
