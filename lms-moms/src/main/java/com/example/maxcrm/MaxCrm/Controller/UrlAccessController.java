package com.example.maxcrm.MaxCrm.Controller;

import com.example.maxcrm.MaxCrm.Dao.UrlAccessDao;
import com.example.maxcrm.MaxCrm.Service.UrlAccessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

@RestController
@RequestMapping("/api/urlaccess")
public class UrlAccessController {
    @Autowired
    UrlAccessService urlAccessService;
    @GetMapping("/getall")
    public Iterable<UrlAccessDao> getAll() throws SQLException {
        return urlAccessService.findAll();
    }
    @GetMapping("/getallactive")
    public Iterable<UrlAccessDao> getallActive() throws SQLException {
        return urlAccessService.findAllActive();
    }
    @PostMapping("/insert")
    public UrlAccessDao insert(@RequestBody UrlAccessDao dao) throws Exception {


        return urlAccessService.insert(dao);
    }


    @PostMapping("/update")
    public UrlAccessDao update(@RequestBody UrlAccessDao dao) throws Exception {
        return urlAccessService.update(dao);
    }
    @DeleteMapping("/delete")
    public void delete(@RequestParam("id")int id)
    {
        urlAccessService.delete(id);
    }

}
