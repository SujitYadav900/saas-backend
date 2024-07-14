package com.example.maxcrm.MaxCrm.Dao;

import javax.persistence.*;

@Entity
@Table(name="Tbl_LeadType_user",uniqueConstraints={@UniqueConstraint(name = "unqleaduser",columnNames = {"lead_type_id","user_id"})})
public class LeadTypeUserDao {
    @Id
    @GeneratedValue
    private long id;
    private long lead_type_id;
    private long user_id;
    @Transient
    private String name;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getLead_type_id() {
        return lead_type_id;
    }

    public void setLead_type_id(long lead_type_id) {
        this.lead_type_id = lead_type_id;
    }

    public long getUser_id() {
        return user_id;
    }

    public void setUser_id(long user_id) {
        this.user_id = user_id;
    }

    @Override
    public String toString() {
        return "LeadTypeUser{" +
                "id=" + id +
                ", lead_type_id=" + lead_type_id +
                ", user_id=" + user_id +
                ", name='" + name + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
