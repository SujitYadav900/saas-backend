package com.example.maxcrm.MaxCrm.Controller;

import com.example.maxcrm.MaxCrm.Dao.RolePageDao;
import com.example.maxcrm.MaxCrm.Service.RolePageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

@RestController
@RequestMapping("/api/rolepage")
public class RolePageController {
    @Autowired
    RolePageService rolePageService;


    @GetMapping("/getall")
    public Iterable<RolePageDao> getAll() throws SQLException {
        return rolePageService.getAll();
    }

    @GetMapping("/getallbyrole")
    public Iterable<RolePageDao> getAll(@RequestParam("role")int role) throws SQLException {
        return rolePageService.getByRole(role);
    }
    @PostMapping("/insert")
    public RolePageDao insert(@RequestBody RolePageDao dao) throws Exception {
        return rolePageService.insert(dao);
    }


    @PostMapping("/update")
    public RolePageDao update(@RequestBody RolePageDao dao)
    {
        return rolePageService.update(dao);
    }
    @DeleteMapping("/delete")
    public void delete(@RequestParam("id")long id)
    {
        rolePageService.delete(id);
    }

}
