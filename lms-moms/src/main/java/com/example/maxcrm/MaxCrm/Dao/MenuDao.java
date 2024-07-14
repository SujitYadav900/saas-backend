package com.example.maxcrm.MaxCrm.Dao;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "Tbl_Menu", uniqueConstraints={@UniqueConstraint(name = "unqmenuname",columnNames = "menuName")})
public class MenuDao {
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public String getMenuDesc() {
        return menuDesc;
    }

    public void setMenuDesc(String menuDesc) {
        this.menuDesc = menuDesc;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "MenuDao{" +
                "id=" + id +
                ", menuName='" + menuName + '\'' +
                ", menuDesc='" + menuDesc + '\'' +
                ", icon='" + icon + '\'' +
                ", status=" + status +
                '}';
    }

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int id;
    @NotBlank(message = "Menuname is mandatory")
    @Column(unique=true)
    private String menuName;
    private String menuDesc;
    private String icon;
    private int status;
}
