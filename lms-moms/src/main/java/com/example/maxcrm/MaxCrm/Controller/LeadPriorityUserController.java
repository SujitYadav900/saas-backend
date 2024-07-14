package com.example.maxcrm.MaxCrm.Controller;

import com.example.maxcrm.MaxCrm.Dao.LeadPriorityUserDao;
import com.example.maxcrm.MaxCrm.ServiceImpl.LeadPriorityUserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/api/leadpriorityuser")
public class LeadPriorityUserController {

    @Autowired
    LeadPriorityUserServiceImpl service;

    @GetMapping("/getall")
    public Iterable<LeadPriorityUserDao> getAll(){
        return service.getAll();
    }

    @PostMapping("/insert")
    public LeadPriorityUserDao insert(@RequestBody LeadPriorityUserDao priorityUserDao) throws Exception {

        System.out.println("LeadPriorityUserDao  "+priorityUserDao);
        return service.insert(priorityUserDao);
    }

    @DeleteMapping("/delete")
    public void delete(@RequestParam long id){
        service.delete(id);
    }

    @GetMapping("/getallbypriorityid")
    public List<LeadPriorityUserDao> getAllByTypeId(@RequestParam long lead_priority_id) throws SQLException {

        System.out.println(lead_priority_id);
        //long longId = Long.parseLong(id);
        return service.getAllByTypeId(lead_priority_id);
    }


}
