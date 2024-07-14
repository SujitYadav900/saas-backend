package com.example.maxcrm.MaxCrm.Controller;


import com.example.maxcrm.MaxCrm.Dao.ProductMasterDao;
import com.example.maxcrm.MaxCrm.Service.ProductMasterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/product")
public class ProductMasterController {

    @Autowired
    ProductMasterService service;

    @ResponseBody
    @GetMapping("/test")
    public String hell(){
        return "hello";
    }

    @GetMapping("/getall")
    public Iterable<ProductMasterDao> getAll(){
        return service.findAll();
    }

    @GetMapping("/getbyname")
    public ProductMasterDao getByName(@RequestParam String productName){
        return service.findByName(productName);
    }

    @GetMapping("/getallavailable")
    public Iterable<ProductMasterDao> getAllAvailable(){
        return service.findAllAvailable();
    }

    @PostMapping("/insert")
    public ProductMasterDao insert(@RequestBody ProductMasterDao productMasterDao) throws Exception {
        return service.insert(productMasterDao);
    }

    @PostMapping("/update")
    public ProductMasterDao update(@RequestBody ProductMasterDao productMasterDao ) throws Exception {
        return service.update(productMasterDao);
    }

    @DeleteMapping("/delete")
    public void delete(@RequestParam int id){
        service.delete(id);
    }



}
