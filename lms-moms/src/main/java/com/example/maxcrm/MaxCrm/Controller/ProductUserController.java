package com.example.maxcrm.MaxCrm.Controller;

import com.example.maxcrm.MaxCrm.Dao.ProductUserDao;
import com.example.maxcrm.MaxCrm.ServiceImpl.ProductUserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/api/productuser")
public class ProductUserController {

    @Autowired
    ProductUserServiceImpl service;

    @GetMapping("/getall")
    public Iterable<ProductUserDao> getAll(){
        return service.getAll();
    }

    @PostMapping("/insert")
    public ProductUserDao insert(@RequestBody ProductUserDao productUserDao) throws Exception {

        return service.insert(productUserDao);
    }

    @DeleteMapping("/delete")
    public void delete(@RequestParam long id){
        service.delete(id);
    }

    @GetMapping("/getallbyproductid")
    public List<ProductUserDao> getAllByTypeId(@RequestParam long product_id) throws SQLException {

        return service.getAllByProductId(product_id);
    }


}
