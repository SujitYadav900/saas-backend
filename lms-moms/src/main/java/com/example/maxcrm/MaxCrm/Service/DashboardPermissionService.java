package com.example.maxcrm.MaxCrm.Service;

import com.example.maxcrm.MaxCrm.Dao.DashboardPermissionDao;

import java.util.List;

public interface DashboardPermissionService {

    DashboardPermissionDao insert(DashboardPermissionDao dao);
    void delete(long id);
    List<DashboardPermissionDao> findAllByRoleId(long roleid);

    List<DashboardPermissionDao> findAllByRoleName(String roleName);

}
