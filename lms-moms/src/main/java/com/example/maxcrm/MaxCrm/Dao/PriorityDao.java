package com.example.maxcrm.MaxCrm.Dao;

import javax.persistence.*;

@Entity
@Table(name="Tbl_Priority",uniqueConstraints = {@UniqueConstraint(name="unqpriority", columnNames = "name")})
public class PriorityDao {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;
    private String description;
    private byte status;
    private int createat;

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    private String color;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public byte getStatus() {
        return status;
    }

    public void setStatus(byte status) {
        this.status = status;
    }

    public int getCreateat() {
        return createat;
    }

    public void setCreateat(int createat) {
        this.createat = createat;
    }

    @Override
    public String toString() {
        return "PriorityDao{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", status=" + status +
                ", createat=" + createat +
                ", color='" + color + '\'' +
                '}';
    }
}
