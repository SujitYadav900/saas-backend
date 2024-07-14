package com.example.maxcrm.MaxCrm.Dao;

import javax.persistence.*;
// URGENT
//  THIS DAO IS BEING USED FOR 'CAMPAIGN NAME' INSTEAD OF PRIORITY
// ONLY JSP CODE IS CHANGED NO DAO LAYES IS CHANGED
@Entity
@Table(name = "Tbl_LeadPriority", uniqueConstraints={@UniqueConstraint(name = "unqleadpriority",columnNames = "name")})
public class LeadPriorityDao {
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return description;
    }

    public void setDesc(String desc) {
        this.description = desc;
    }

    public byte getStatus() {
        return status;
    }

    public void setStatus(byte status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "LeadPriorityDao{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", color='" + color + '\'' +
                ", status=" + status +
                '}';
    }

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int id;
    private String name;
    private String description;
    private String color;
    private byte status;
}
