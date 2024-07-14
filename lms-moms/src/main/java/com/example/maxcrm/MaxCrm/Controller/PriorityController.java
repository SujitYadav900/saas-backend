package com.example.maxcrm.MaxCrm.Controller;

import com.example.maxcrm.MaxCrm.Dao.PriorityDao;
import com.example.maxcrm.MaxCrm.Service.PriorityService;
import com.example.maxcrm.MaxCrm.Utility.UtilityClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/priority")
public class PriorityController {

    @Autowired
    private PriorityService service;

    @PostMapping("/insert")
    public PriorityDao insert(@RequestBody PriorityDao dao) throws Exception {
        dao.setCreateat(UtilityClass.dateFilterDate());
        return service.insert(dao);
    }

    @PostMapping("/update")
    public PriorityDao update(@RequestBody PriorityDao dao) throws Exception {
        return service.insert(dao);
    }

    @DeleteMapping("/delete")
    public void delete(@RequestParam int id){
        service.delete(id);
    }

    @GetMapping("/getall")
    public Iterable<PriorityDao> getall(){
        return service.getAll();
    }

    @GetMapping("/getallactive")
    public Iterable<PriorityDao> getallactive(){
        return service.getAllActive();
    }

}
