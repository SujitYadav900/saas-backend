package com.example.maxcrm.MaxCrm.Controller;

import com.example.maxcrm.MaxCrm.Dao.LeadSourceUser;
import com.example.maxcrm.MaxCrm.ServiceImpl.LeadSourceUserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/api/leadsourceuser")
public class LeadSourceUserController {

    @Autowired
    LeadSourceUserServiceImpl service;

    @GetMapping("/getall")
    public Iterable<LeadSourceUser> getAll(){
        return service.getAll();
    }

    @PostMapping("/insert")
    public LeadSourceUser insert(@RequestBody LeadSourceUser leadSourceUser) throws Exception {

        return service.insert(leadSourceUser);
    }

    @DeleteMapping("/delete")
    public void delete(@RequestParam long id){
        service.delete(id);
    }

    @GetMapping("/getallbysourceid")
    public List<LeadSourceUser> getAllBySucId(@RequestParam long lead_Source_id) throws SQLException {

        System.out.println(lead_Source_id);
        //long longId = Long.parseLong(id);
        return service.getAllBySourceId(lead_Source_id);
    }


}
