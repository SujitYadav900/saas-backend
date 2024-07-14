package com.example.maxcrm.MaxCrm.CombinePackage.CentreAppointmentLogs;

import javax.persistence.*;

@Entity
@Table(name = "Tbl_CentreAppointmentLogs")
public class CentreAppointmentLogs {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String createDate;
    private int createDateFilter;
    private long leadId;
    private String centrePin;
    private String centreName;
    private String username;
    private String notificationType;
    private String notifiedTo;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public int getCreateDateFilter() {
        return createDateFilter;
    }

    public void setCreateDateFilter(int createDateFilter) {
        this.createDateFilter = createDateFilter;
    }

    public long getLeadId() {
        return leadId;
    }

    public void setLeadId(long leadId) {
        this.leadId = leadId;
    }

    public String getCentrePin() {
        return centrePin;
    }

    public void setCentrePin(String centrePin) {
        this.centrePin = centrePin;
    }

    public String getCentreName() {
        return centreName;
    }

    public void setCentreName(String centreName) {
        this.centreName = centreName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNotificationType() {
        return notificationType;
    }

    public void setNotificationType(String notificationType) {
        this.notificationType = notificationType;
    }

    public String getNotifiedTo() {
        return notifiedTo;
    }

    public void setNotifiedTo(String notifiedTo) {
        this.notifiedTo = notifiedTo;
    }

    @Override
    public String toString() {
        return "CentreAppointmentLogs{" +
                "id=" + id +
                ", createDate='" + createDate + '\'' +
                ", createDateFilter=" + createDateFilter +
                ", leadId=" + leadId +
                ", centrePin='" + centrePin + '\'' +
                ", centreName='" + centreName + '\'' +
                ", username='" + username + '\'' +
                ", notificationType='" + notificationType + '\'' +
                ", notifiedTo='" + notifiedTo + '\'' +
                '}';
    }
}
