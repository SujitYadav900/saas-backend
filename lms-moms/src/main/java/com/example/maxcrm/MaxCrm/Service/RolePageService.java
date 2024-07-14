package com.example.maxcrm.MaxCrm.Service;

import com.example.maxcrm.MaxCrm.Dao.RolePageDao;

public interface RolePageService {
    RolePageDao insert(RolePageDao roleDao) throws Exception;
    RolePageDao update(RolePageDao roleDao);

    void delete(long id);
    Iterable<RolePageDao> getAll();
    Iterable<RolePageDao> getByRole(int role);
}
