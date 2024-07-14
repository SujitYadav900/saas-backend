package com.example.maxcrm.MaxCrm.Dao;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "Tbl_Role" , uniqueConstraints={@UniqueConstraint(name = "unqrolename",columnNames = "roleName")})
public class RoleDao  {
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleDesc() {
        return roleDesc;
    }

    public void setRoleDesc(String roleDesc) {
        this.roleDesc = roleDesc;
    }

    public byte getStatus() {
        return status;
    }

    public void setStatus(byte status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "RoleDao{" +
                "id=" + id +
                ", roleName='" + roleName + '\'' +
                ", roleDesc='" + roleDesc + '\'' +
                ", status=" + status +
                '}';
    }

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int id;
    @NotBlank(message = "Rolename is mandatory")
    @Column(unique=true)
    private String roleName;
    private String roleDesc;
    private  byte status;

}
