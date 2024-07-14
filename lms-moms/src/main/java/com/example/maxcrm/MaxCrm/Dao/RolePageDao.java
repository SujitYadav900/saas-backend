package com.example.maxcrm.MaxCrm.Dao;

import javax.persistence.*;

@Entity
@Table(name = "Tbl_RolePage",uniqueConstraints={@UniqueConstraint(columnNames = {"urlId", "roleId"})})
public class RolePageDao {
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getUrlId() {
        return urlId;
    }

    public void setUrlId(int urlId) {
        this.urlId = urlId;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    @Override
    public String toString() {
        return "RolePageDao{" +
                "id=" + id +
                ", urlId=" + urlId +
                ", roleId=" + roleId +
                '}';
    }

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;
    private int urlId;
    private int roleId;
}
