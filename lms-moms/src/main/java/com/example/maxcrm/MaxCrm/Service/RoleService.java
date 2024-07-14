package com.example.maxcrm.MaxCrm.Service;

import com.example.maxcrm.MaxCrm.Dao.RoleDao;
import com.example.maxcrm.MaxCrm.Dao.UserMasterDao;
import org.springframework.security.core.GrantedAuthority;

import java.sql.SQLException;
import java.util.Collection;
import java.util.HashMap;

public interface RoleService {
    RoleDao insertRole(RoleDao dao) throws Exception;
    RoleDao updateRole(RoleDao dao) throws Exception;
    Iterable<RoleDao> getAll();
    Collection<GrantedAuthority> getAllRolesByDepartment(String dep);
    void deleteRole(int id);
    RoleDao findByName(String roleName);
    HashMap<String,Boolean> getAllRolesByDepartmentAfterLogin(String department);
    UserMasterDao findRandomUser(String department) throws SQLException;
    HashMap<Integer,String> getFeatures(int roleId);
}
