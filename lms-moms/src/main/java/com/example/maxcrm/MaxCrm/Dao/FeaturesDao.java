package com.example.maxcrm.MaxCrm.Dao;

import javax.persistence.*;

@Entity
@Table(name = "Tbl_Features", uniqueConstraints={@UniqueConstraint(name = "unqfeaturename",columnNames = "name")})
public class FeaturesDao {
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getDescri() {
        return descri;
    }

    public void setDescri(String descri) {
        this.descri = descri;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;
    private boolean status;
    private String descri;

    @Override
    public String toString() {
        return "FeaturesDao{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", status=" + status +
                ", descri='" + descri + '\'' +
                ", refId=" + refId +
                '}';
    }

    public int getRefId() {
        return refId;
    }

    public void setRefId(int refId) {
        this.refId = refId;
    }

    @Transient
    private int refId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
