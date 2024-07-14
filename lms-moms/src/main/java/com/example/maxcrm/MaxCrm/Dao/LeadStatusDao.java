package com.example.maxcrm.MaxCrm.Dao;

import javax.persistence.*;

@Entity
@Table(name = "Tbl_LeadStatus", uniqueConstraints={@UniqueConstraint(name = "unqleadstatus",columnNames = "name")})
public class LeadStatusDao {
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

    public byte getStatus() {
        return status;
    }

    public void setStatus(byte status) {
        this.status = status;
    }

    public byte getSendnotification() {
        return sendnotification;
    }

    public void setSendnotification(byte sendnotification) {
        this.sendnotification = sendnotification;
    }

    public String getTemplate() {
        return template;
    }

    public void setTemplate(String template) {
        this.template = template;
    }

    public byte getSendnotificationothers() {
        return sendnotificationothers;
    }

    public void setSendnotificationothers(byte sendnotificationothers) {
        this.sendnotificationothers = sendnotificationothers;
    }

    public String getPhonenumbers() {
        return phonenumbers;
    }

    public void setPhonenumbers(String phonenumbers) {
        this.phonenumbers = phonenumbers;
    }

    public String getTemplateothers() {
        return templateothers;
    }

    public void setTemplateothers(String templateothers) {
        this.templateothers = templateothers;
    }

    @Override
    public String toString() {
        return "LeadStatusDao{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", color='" + color + '\'' +
                ", status=" + status +
                ", sendnotification=" + sendnotification +
                ", template='" + template + '\'' +
                ", sendnotificationothers=" + sendnotificationothers +
                ", phonenumbers='" + phonenumbers + '\'' +
                ", templateothers='" + templateothers + '\'' +
                '}';
    }

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int id;
    private String name;
    private String description;
    private String color;
    private byte status;

    private byte sendnotification;
    private String template;// comma separted strings
    private byte sendnotificationothers;
    private String phonenumbers;
    private String templateothers;// comma separted strings

}
