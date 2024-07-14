package com.example.maxcrm.MaxCrm.Dao;

import javax.persistence.*;

@Entity
@Table(name = "Tbl_LeadSource", uniqueConstraints={@UniqueConstraint(name = "unqleadsource",columnNames = "name")})

public class LeadSourceDao {
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

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int id;
    private String name;
    private String description;
    private byte status;
    private byte sendnotification;
    private String template;// comma separted strings
    private byte sendnotificationothers;
    private String phonenumbers;
    private String templateothers;// comma separted strings
    private String initialStage;
    private String initialStatus;
    private String initialType;
    private String accessToken;

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }



    @Override
    public String toString() {
        return "LeadSourceDao{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", status=" + status +
                ", sendnotification=" + sendnotification +
                ", template='" + template + '\'' +
                ", sendnotificationothers=" + sendnotificationothers +
                ", phonenumbers='" + phonenumbers + '\'' +
                ", templateothers='" + templateothers + '\'' +
                ", accessToken='" + accessToken + '\'' +
                ", assignBy='" + assignBy + '\'' +
                ", initialStage='" + initialStage + '\'' +
                ", initialStatus='" + initialStatus + '\'' +
                ", initialType='" + initialType + '\'' +
                '}';
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAssignBy() {
        return assignBy;
    }

    public void setAssignBy(String assignBy) {
        this.assignBy = assignBy;
    }

    private String assignBy;

    public String getInitialStage() {
        return initialStage;
    }

    public void setInitialStage(String initialStage) {
        this.initialStage = initialStage;
    }

    public String getInitialStatus() {
        return initialStatus;
    }

    public void setInitialStatus(String initialStatus) {
        this.initialStatus = initialStatus;
    }



    public String getInitialType() {
        return initialType;
    }

    public void setInitialType(String initialType) {
        this.initialType = initialType;
    }






}
