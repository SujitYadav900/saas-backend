package com.example.maxcrm.MaxCrm.Controller;

import com.example.maxcrm.MaxCrm.Dao.PropertyInnerDao;
import com.example.maxcrm.MaxCrm.Service.PropertyInnerService;
import com.example.maxcrm.MaxCrm.Utility.UtilityClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/api/propertyinner")
public class PropertyInnerController {

    @Autowired
    private PropertyInnerService service;

    @PostMapping("/insert")
    public PropertyInnerDao insert(@RequestBody PropertyInnerDao dao) throws Exception {
        dao.setCreateat(UtilityClass.dateFilterDate());
        return service.insert(dao);
    }

    @PostMapping("/update")
    public PropertyInnerDao update(@RequestBody PropertyInnerDao dao) throws Exception {
        return service.update(dao);
    }

    @DeleteMapping("/delete")
    public void delete(@RequestParam long id){
        service.delete(id);
    }

    @GetMapping("/getall")
    public List<PropertyInnerDao> getAll(@RequestParam("property")String property) throws SQLException {
        return service.getAll(property);
    }

    @GetMapping("/getbyid")
    public PropertyInnerDao getById(@RequestParam long id){
        return service.findPropetyInnerById(id);
    }

    @GetMapping("/getbyname")
    public PropertyInnerDao getByName(@RequestParam String name){
        return service.findPropetyInnerByName(name);
    }

    @GetMapping("/getbypropertytype")
    public Iterable<PropertyInnerDao> getByPropertyType(@RequestParam String type){
        return service.findAllByPropertyType(type);
    }



}
