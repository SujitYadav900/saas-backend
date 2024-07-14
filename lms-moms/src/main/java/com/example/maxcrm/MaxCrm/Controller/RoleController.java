package com.example.maxcrm.MaxCrm.Controller;

import com.example.maxcrm.MaxCrm.Dao.RoleDao;
import com.example.maxcrm.MaxCrm.Dao.UserMasterDao;
import com.example.maxcrm.MaxCrm.Service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequestMapping("/api/roles")
public class RoleController {
    @Autowired
    RoleService roleService;

    @GetMapping("/getroles")
    public Iterable<RoleDao> findAll()
    {
        return  roleService.getAll();
    }

    @GetMapping("/getrolebyname")
    public RoleDao getByName(@RequestParam String roleName){
        return roleService.findByName(roleName);
    }
    @PostMapping("/insert")
    public RoleDao insert(@RequestBody RoleDao dao) throws Exception {

        return  roleService.insertRole(dao);
    }
    @PostMapping("/update")
    public RoleDao update(@RequestBody RoleDao dao) throws Exception {
        return  roleService.updateRole(dao);
    }

    @DeleteMapping("/delete")
    public void delete(@RequestParam("id")int id)
    {
         roleService.deleteRole(id);
    }

    @GetMapping("/getroleafterlogin")
    public HashMap<String,Boolean> getRoleAfterLogin(Authentication authentication)
    {
        UserMasterDao userMasterDao= (UserMasterDao) authentication.getPrincipal();
        return roleService.getAllRolesByDepartmentAfterLogin(userMasterDao.getDepartment());
    }
}
