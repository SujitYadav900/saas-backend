package com.example.maxcrm.MaxCrm.Controller;

import com.example.maxcrm.MaxCrm.Dao.DepartmentDao;
import com.example.maxcrm.MaxCrm.Service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/department")
public class DepartmentController {

    @Autowired
    private DepartmentService service;

    @PostMapping("/insert")
    public DepartmentDao insert(@RequestBody DepartmentDao dao){
        return service.insert(dao);
    }

    @PostMapping("/update")
    public DepartmentDao update(@RequestBody DepartmentDao dao){
        return service.insert(dao);
    }

    @DeleteMapping("/delete")
    public void delete(@RequestParam int id){
        service.delete(id);
    }

    @GetMapping("/getall")
    public Iterable<DepartmentDao> getall(){
        return service.getAll();
    }

    @GetMapping("/getallactive")
    public Iterable<DepartmentDao> getallactive(){
        return service.getAllActive();
    }

}
