package com.example.maxcrm.MaxCrm.Service;

import com.example.maxcrm.MaxCrm.Dao.MenuDao;

public interface MenuService {
    MenuDao insert(MenuDao menuDao) throws Exception;
    MenuDao update(MenuDao menuDao) throws Exception;
    Iterable<MenuDao> getAll();
    void delete(int id);
}
