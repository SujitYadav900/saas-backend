package com.example.maxcrm.MaxCrm.Controller;

import com.example.maxcrm.MaxCrm.Dao.MenuDao;
import com.example.maxcrm.MaxCrm.Service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/menu")
public class MenuController {
    @Autowired
    MenuService menuService;
    @GetMapping("/getall")
    public Iterable<MenuDao> findAll()
    {
        return menuService.getAll();
    }

    @PostMapping("/insert")
    public MenuDao insert(@RequestBody MenuDao menuDao) throws Exception {

        return menuService.insert(menuDao);
    }

    @PostMapping("/update")
    public MenuDao update(@RequestBody MenuDao menuDao) throws Exception {
        return menuService.update(menuDao);
    }

    @DeleteMapping("/delete")
    public void update(@RequestParam("id")int id)
    {
         menuService.delete(id);
    }
}
