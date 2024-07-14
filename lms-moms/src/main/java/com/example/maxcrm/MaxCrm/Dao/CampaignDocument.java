package com.example.maxcrm.MaxCrm.Dao;

import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import java.util.List;
import java.util.Map;

@Document(collection = "CampaignDocument")
public class CampaignDocument {
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public String getTemplateId() {
        return templateId;
    }

    public void setTemplateId(String templateId) {
        this.templateId = templateId;
    }




    @Id
    private String id;
    private String createDate;
    private String createBy;
    private String templateId;
    private int totalReciept;
    private int totalClicks;
    private int sentCount;
    private int deliverCount;
    private int readCount;
    private Map<Integer,Boolean> excludedLeadIdList;

    @Indexed
    private int datefilter;
    private String name;
    private String templateName;

    private String url;

    public byte getStatus() {
        return status;
    }

    public void setStatus(byte status) {
        this.status = status;
    }

    private byte status;

    public int getTotalReciept() {
        return totalReciept;
    }

    @Override
    public String toString() {
        return "CampaignDocument{" +
                "id='" + id + '\'' +
                ", createDate='" + createDate + '\'' +
                ", createBy='" + createBy + '\'' +
                ", templateId='" + templateId + '\'' +
                ", totalReciept=" + totalReciept +
                ", totalClicks=" + totalClicks +
                ", sentCount=" + sentCount +
                ", deliverCount=" + deliverCount +
                ", readCount=" + readCount +
                ", datefilter=" + datefilter +
                ", name='" + name + '\'' +
                ", templateName='" + templateName + '\'' +
                ", url='" + url + '\'' +
                ", status=" + status +
                '}';
    }

    public void setTotalReciept(int totalReciept) {
        this.totalReciept = totalReciept;
    }






    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }



    public String getTemplateName() {
        return templateName;
    }

    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }


    public int getTotalClicks() {
        return totalClicks;
    }

    public void setTotalClicks(int totalClicks) {
        this.totalClicks = totalClicks;
    }

    public int getDatefilter() {
        return datefilter;
    }

    public void setDatefilter(int datefilter) {
        this.datefilter = datefilter;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSentCount() {
        return sentCount;
    }

    public void setSentCount(int sentCount) {
        this.sentCount = sentCount;
    }

    public int getDeliverCount() {
        return deliverCount;
    }

    public void setDeliverCount(int deliverCount) {
        this.deliverCount = deliverCount;
    }

    public int getReadCount() {
        return readCount;
    }

    public void setReadCount(int readCount) {
        this.readCount = readCount;
    }

    public Map<Integer, Boolean> getExcludedLeadIdList() {
        return excludedLeadIdList;
    }

    public void setExcludedLeadIdList(Map<Integer, Boolean> excludedLeadIdList) {
        this.excludedLeadIdList = excludedLeadIdList;
    }
}
