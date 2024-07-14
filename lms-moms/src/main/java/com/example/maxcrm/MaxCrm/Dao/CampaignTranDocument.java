package com.example.maxcrm.MaxCrm.Dao;

import org.springframework.data.mongodb.core.index.Indexed;

import java.util.List;

public class CampaignTranDocument {

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public long getDateTiming() {
        return dateTiming;
    }

    public void setDateTiming(long dateTiming) {
        this.dateTiming = dateTiming;
    }

    public int getDateFilter() {
        return dateFilter;
    }

    public void setDateFilter(int dateFilter) {
        this.dateFilter = dateFilter;
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

    public String getTemplateType() {
        return templateType;
    }

    public void setTemplateType(String templateType) {
        this.templateType = templateType;
    }

    public List<String> getUrl() {
        return url;
    }

    public void setUrl(List<String> url) {
        this.url = url;
    }

    public List<LeadMasterDao> getAl() {
        return al;
    }

    public void setAl(List<LeadMasterDao> al) {
        this.al = al;
    }

    public byte getStatus() {
        return status;
    }

    public void setStatus(byte status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "CampaignTranDocument{" +
                "id='" + id + '\'' +
                ", dateTiming=" + dateTiming +
                ", dateFilter=" + dateFilter +
                ", createBy='" + createBy + '\'' +
                ", createAt='" + createAt + '\'' +
                ", message='" + message + '\'' +
                ", templateType='" + templateType + '\'' +
                ", templateVariable='" + templateVariable + '\'' +
                ", url=" + url +
                ", al=" + al +
                ", status=" + status +
                '}';
    }

    private String id;
    @Indexed
    private long dateTiming;
    private int dateFilter;
    private String createBy;
    private String createAt;
    private String message;
    private String templateType; //mail or message
    private String templateVariable; //lead/user

    public String getTemplateVariable() {
        return templateVariable;
    }

    public void setTemplateVariable(String templateVariable) {
        this.templateVariable = templateVariable;
    }

    private List<String> url;
    private List<LeadMasterDao> al;
    private byte status; //0 = pending 1=sent

}
