package com.example.maxcrm.MaxCrm.Dao;

import javax.persistence.*;

@Entity
@Table(name = "Tbl_FeatureAssign")
public class FeatureAsssignDao {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private int featureId;
    private int roleId;

    @Override
    public String toString() {
        return "FeatureAsssignDao{" +
                "id=" + id +
                ", featureId=" + featureId +
                ", roleId=" + roleId +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getFeatureId() {
        return featureId;
    }

    public void setFeatureId(int featureId) {
        this.featureId = featureId;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }


}
