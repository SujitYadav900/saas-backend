package com.example.maxcrm.MaxCrm.Dao;

import javax.persistence.*;

@Entity
@Table(name = "Tbl_Log_Task", indexes = {@Index(name = "leadIdTaskindex", columnList="leadId",  unique = false),@Index(name = "leaddatetimeTaskindex", columnList="leadId",  unique = false)})
public class LogTaskDao {
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public long getLeadId() {
        return leadId;
    }

    public void setLeadId(long leadId) {
        this.leadId = leadId;
    }

    public boolean isTxtMsgNotification() {
        return txtMsgNotification;
    }

    public void setTxtMsgNotification(boolean txtMsgNotification) {
        this.txtMsgNotification = txtMsgNotification;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDateTimeTask() {
        return dateTimeTask;
    }

    public void setDateTimeTask(String dateTimeTask) {
        this.dateTimeTask = dateTimeTask;
    }
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;
    private String subject;
    private String message;
    private String createDate;
    private String updateDate;
    private String createBy;
    private String updateBy;
    private long leadId;
    private boolean txtMsgNotification;
    private String url;
    private String dateTimeTask;
    private long refId;
    @Transient
    private String color;
    @Transient
    private String typ;
    @Transient
    private String firstname;
    @Transient
    private String fullName;
    @Transient
    private String phoneNumber;

    @Override
    public String toString() {
        return "LogTaskDao{" +
                "id=" + id +
                ", subject='" + subject + '\'' +
                ", message='" + message + '\'' +
                ", createDate='" + createDate + '\'' +
                ", updateDate='" + updateDate + '\'' +
                ", createBy='" + createBy + '\'' +
                ", updateBy='" + updateBy + '\'' +
                ", leadId=" + leadId +
                ", txtMsgNotification=" + txtMsgNotification +
                ", url='" + url + '\'' +
                ", dateTimeTask='" + dateTimeTask + '\'' +
                ", refId=" + refId +
                ", color='" + color + '\'' +
                ", typ='" + typ + '\'' +
                ", firstname='" + firstname + '\'' +
                ", fullName='" + fullName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", assignedTo=" + assignedTo +
                '}';
    }


    public long getRefId() {
        return refId;
    }

    public void setRefId(long refId) {
        this.refId = refId;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getTyp() {
        return typ;
    }

    public void setTyp(String typ) {
        this.typ = typ;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getAssignedTo() {
        return assignedTo;
    }

    public void setAssignedTo(int assignedTo) {
        this.assignedTo = assignedTo;
    }

    private int assignedTo;




}
