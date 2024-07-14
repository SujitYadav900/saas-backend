package com.example.maxcrm.MaxCrm.Dao;

import com.google.gson.Gson;

import javax.persistence.*;

@Entity
@Table(name = "Tbl_Notification", indexes = {@Index(name = "datetimingindex", columnList="datetimingnotification",  unique = false)})
public class NotificationDao {
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getDatetiming() {
        return datetimingnotification;
    }

    public void setDatetiming(long datetimingnotification) {
        this.datetimingnotification = datetimingnotification;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public boolean isReadStatus() {
        return readStatus;
    }

    public void setReadStatus(boolean readStatus) {
        this.readStatus = readStatus;
    }

    public String getRedirectUrl() {
        return redirectUrl;
    }

    public void setRedirectUrl(String redirectUrl) {
        this.redirectUrl = redirectUrl;
    }

    public int getTo() {
        return assignTo;
    }

    public void setTo(int assignTo) {
        this.assignTo = assignTo;
    }

    public String getCreateAt() {
        return createAt;
    }

    public void setCreateAt(String createAt) {
        this.createAt = createAt;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public boolean isSendStatus() {
        return sendStatus;
    }

    public void setSendStatus(boolean sendStatus) {
        this.sendStatus = sendStatus;
    }

    public String getNotificationTiming() {
        return notificationTiming;
    }

    public void setNotificationTiming(String notificationTiming) {
        this.notificationTiming = notificationTiming;
    }

    public String getReadTiming() {
        return readTiming;
    }

    public void setReadTiming(String readTiming) {
        this.readTiming = readTiming;
    }

    public boolean isSendMessage() {
        return sendMessage;
    }

    public void setSendMessage(boolean sendMessage) {
        this.sendMessage = sendMessage;
    }



    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;

    public long getLeadId() {
        return leadId;
    }

    public void setLeadId(long leadId) {
        this.leadId = leadId;
    }

    private long leadId;
    private long datetimingnotification;
    private String message;
    private String subject;
    private boolean readStatus;
    private String redirectUrl;
    private int assignTo;
    private String createAt;
    private String createBy;
    private boolean sendStatus;
    private String notificationTiming;
    private String readTiming;
    private boolean sendMessage;
    private String innerSubject;


    public long getDatetimingnotification() {
        return datetimingnotification;
    }

    public void setDatetimingnotification(long datetimingnotification) {
        this.datetimingnotification = datetimingnotification;
    }

    @Override
    public String toString() {
        return "NotificationDao{" +
                "id=" + id +
                ", datetimingnotification=" + datetimingnotification +
                ", message='" + message + '\'' +
                ", subject='" + subject + '\'' +
                ", readStatus=" + readStatus +
                ", redirectUrl='" + redirectUrl + '\'' +
                ", assignTo=" + assignTo +
                ", createAt='" + createAt + '\'' +
                ", createBy='" + createBy + '\'' +
                ", sendStatus=" + sendStatus +
                ", notificationTiming='" + notificationTiming + '\'' +
                ", readTiming='" + readTiming + '\'' +
                ", sendMessage=" + sendMessage +
                ", innerSubject='" + innerSubject + '\'' +
                '}';
    }

    public int getAssignTo() {
        return assignTo;
    }

    public void setAssignTo(int assignTo) {
        this.assignTo = assignTo;
    }

    public String getInnerSubject() {
        return innerSubject;
    }

    public void setInnerSubject(String innerSubject) {
        this.innerSubject = innerSubject;
    }


    public String convertObjectToJson(NotificationDao notificationDao)
    {
        return new Gson().toJson(notificationDao);
    }




}
