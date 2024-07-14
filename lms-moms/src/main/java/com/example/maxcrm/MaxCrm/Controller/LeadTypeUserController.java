package com.example.maxcrm.MaxCrm.Controller;

import com.example.maxcrm.MaxCrm.Dao.LeadTypeUserDao;
import com.example.maxcrm.MaxCrm.ServiceImpl.LeadTypeUserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/api/leadtypeuser")
public class LeadTypeUserController {

    @Autowired
    LeadTypeUserServiceImpl service;

    @GetMapping("/getall")
    public Iterable<LeadTypeUserDao> getAll(){
        return service.getAll();
    }

    @PostMapping("/insert")
    public LeadTypeUserDao insert(@RequestBody LeadTypeUserDao leadTypeUserDao) throws Exception {

        System.out.println("leadTypeuserdao  "+leadTypeUserDao);
        return service.insert(leadTypeUserDao);
    }

    @DeleteMapping("/delete")
    public void delete(@RequestParam long id){
        service.delete(id);
    }

    @GetMapping("/getallbytypeid")
    public List<LeadTypeUserDao> getAllByTypeId(@RequestParam long lead_type_id) throws SQLException {

        System.out.println(lead_type_id);
        //long longId = Long.parseLong(id);
        return service.getAllByTypeId(lead_type_id);
    }


}
