package com.example.maxcrm.MaxCrm.Controller;


import com.example.maxcrm.MaxCrm.Dao.DashboardPermissionDao;
import com.example.maxcrm.MaxCrm.Dao.UserMasterDao;
import com.example.maxcrm.MaxCrm.Service.DashboardPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/dashboardpermission")
public class DashboardPermissionController {

    @Autowired
    private DashboardPermissionService permissionService;

    @PostMapping("/insert")
    public DashboardPermissionDao insert(@RequestBody DashboardPermissionDao dao){

        return permissionService.insert(dao);
    }

    @DeleteMapping("/delete")
    public void insert(@RequestParam Long id){

        permissionService.delete(id);
    }

    @GetMapping("/getpermissions")
    public List<DashboardPermissionDao> getAll(@RequestParam long roleId){

        return permissionService.findAllByRoleId(roleId);
    }

    //index.jsp(dashboard) calls this to get current user's dashboard pemissions
    @GetMapping("/getpermissionsbyname")
    public List<DashboardPermissionDao> getAllByName(Authentication auth){

        UserMasterDao userMasterDao = (UserMasterDao) auth.getPrincipal();
        return permissionService.findAllByRoleName(userMasterDao.getDepartment());
    }

    //this is access on rolelist.jsp to alot dashboard pemissions to the roles
    @GetMapping("/getpermissionsbyrolename")
    public List<DashboardPermissionDao> getAllByName(@RequestParam("roleName") String roleName){

        return permissionService.findAllByRoleName(roleName);
    }
}
