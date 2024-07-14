package com.example.maxcrm.MaxCrm.Controller;

import com.example.maxcrm.MaxCrm.Dao.PropertyDao;
import com.example.maxcrm.MaxCrm.Service.PropertyService;
import com.example.maxcrm.MaxCrm.Utility.UtilityClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/property")
public class PropertyController {


    @Autowired
    private PropertyService service;

    @PostMapping("/insert")
    public PropertyDao insert(@RequestBody PropertyDao dao) throws Exception {
        dao.setCreateat(UtilityClass.dateFilterDate());
        return service.insert(dao);
    }

    @PostMapping("/update")
    public PropertyDao update(@RequestBody PropertyDao dao) throws Exception {
        return service.update(dao);
    }

    @DeleteMapping("/delete")
    public void delete(@RequestParam Long id){
        service.delete(id);
    }

    @GetMapping("/getall")
    public Iterable<PropertyDao> getAll(){
        return service.getProperties();
    }

    @GetMapping("/getallbytype")
    public Iterable<PropertyDao> getAll(@RequestParam String type){
        return service.getPropertyByType(type);
    }
}
