package com.example.maxcrm.MaxCrm.Dao;

import javax.persistence.*;

@Entity
@Table(name = "Tbl_Log_Event", indexes = {@Index(name = "leadIdindex", columnList="leadId",  unique = false)})
public class LogEventDao {
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getLeadId() {
        return leadId;
    }

    public void setLeadId(long leadId) {
        this.leadId = leadId;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public String getCreateAt() {
        return createAt;
    }

    public void setCreateAt(String createAt) {
        this.createAt = createAt;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;
    private long leadId;
    private String eventType;
    private String createBy;
    private String createAt;
    private String updateBy;
    private String updateAt;
    private String message;
    private String recordingUrl;
    private   int userId;

    public String getRecordingUrl() {
        return recordingUrl;
    }

    public void setRecordingUrl(String recordingUrl) {
        this.recordingUrl = recordingUrl;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "LogEventDao{" +
                "id=" + id +
                ", leadId=" + leadId +
                ", eventType='" + eventType + '\'' +
                ", createBy='" + createBy + '\'' +
                ", createAt='" + createAt + '\'' +
                ", updateBy='" + updateBy + '\'' +
                ", updateAt='" + updateAt + '\'' +
                ", message='" + message + '\'' +
                ", recordingUrl='" + recordingUrl + '\'' +
                ", userId='" + userId + '\'' +
                ", phonenumber='" + phonenumber + '\'' +
                ", call_type='" + call_type + '\'' +
                ", call_status='" + call_status + '\'' +
                ", agentNo='" + agentNo + '\'' +
                ", recordFileName='" + recordFileName + '\'' +
                ", callStartTime='" + callStartTime + '\'' +
                ", callEndTime='" + callEndTime + '\'' +
                ", location='" + location + '\'' +
                ", duration='" + duration + '\'' +
                ", userId='" + userId + '\'' +
                '}';
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public String getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(String updateAt) {
        this.updateAt = updateAt;
    }

    private String phonenumber;
    private String call_type;
    private String call_status;
    private String agentNo;
    private String recordFileName;
    private String callStartTime;
    private String callEndTime;
    private String location;
    private String duration;


    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getCall_type() {
        return call_type;
    }

    public void setCall_type(String call_type) {
        this.call_type = call_type;
    }

    public String getCall_status() {
        return call_status;
    }

    public void setCall_status(String call_status) {
        this.call_status = call_status;
    }

    public String getAgentNo() {
        return agentNo;
    }

    public void setAgentNo(String agentNo) {
        this.agentNo = agentNo;
    }

    public String getRecordFileName() {
        return recordFileName;
    }

    public void setRecordFileName(String recordFileName) {
        this.recordFileName = recordFileName;
    }

    public String getCallStartTime() {
        return callStartTime;
    }

    public void setCallStartTime(String callStartTime) {
        this.callStartTime = callStartTime;
    }

    public String getCallEndTime() {
        return callEndTime;
    }

    public void setCallEndTime(String callEndTime) {
        this.callEndTime = callEndTime;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }
}
