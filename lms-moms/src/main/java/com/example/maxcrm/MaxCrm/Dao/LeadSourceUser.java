package com.example.maxcrm.MaxCrm.Dao;

import javax.persistence.*;

@Entity
@Table(name="Tbl_LeadSource_user",uniqueConstraints={@UniqueConstraint(name = "unqleaduser",columnNames = {"lead_source_id","user_id"})})
public class LeadSourceUser {
    @Id
    @GeneratedValue
    private long id;
    private long lead_source_id;
    private long user_id;
    @Transient
    private String name;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getLead_source_id() {
        return lead_source_id;
    }

    public void setLead_source_id(long lead_source_id) {
        this.lead_source_id = lead_source_id;
    }

    public long getUser_id() {
        return user_id;
    }

    public void setUser_id(long user_id) {
        this.user_id = user_id;
    }

    @Override
    public String toString() {
        return "LeadSourceUser{" +
                "id=" + id +
                ", lead_source_id=" + lead_source_id +
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
