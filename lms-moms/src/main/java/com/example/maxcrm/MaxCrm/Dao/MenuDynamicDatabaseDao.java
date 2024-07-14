package com.example.maxcrm.MaxCrm.Dao;

import com.google.gson.Gson;

import java.util.Arrays;
import java.util.List;

public class MenuDynamicDatabaseDao {
    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }


    private String menuName;

    public String getMenuIcon() {
        return menuIcon;
    }

    @Override
    public String toString() {
        return "MenuDynamicDatabaseDao{" +
                "menuName='" + menuName + '\'' +
                ", menuIcon='" + menuIcon + '\'' +
                ", listment=" + Arrays.toString(listment) +
                '}';
    }

    public void setMenuIcon(String menuIcon) {
        this.menuIcon = menuIcon;
    }

    private String menuIcon;

    public MenuInnerDynamic[] getListment() {
        return listment;
    }

    public void setListment(MenuInnerDynamic[] listment) {
        this.listment = listment;
    }

    private MenuInnerDynamic [] listment;


    public String convertJson(List<MenuDynamicDatabaseDao> dao)
    {
        return new Gson().toJson(dao);
    }
}
