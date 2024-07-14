package com.example.maxcrm.MaxCrm.Dao;

import javax.persistence.*;

@Entity
@Table(name = "Tbl_LeadType", uniqueConstraints={@UniqueConstraint(name = "unqleadtype",columnNames = "name")})
public class LeadTypeDao {
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isSendSms() {
        return sendSms;
    }

    public void setSendSms(boolean sendSms) {
        this.sendSms = sendSms;
    }

    @Override
    public String toString() {
        return "LeadTypeDao{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", status=" + status +
                ", sendSms=" + sendSms +
                '}';
    }

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int id;
    private String name;
    private String description;
    private byte status;
    private boolean sendSms;
}
