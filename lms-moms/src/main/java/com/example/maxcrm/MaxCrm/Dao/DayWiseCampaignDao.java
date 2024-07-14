package com.example.maxcrm.MaxCrm.Dao;

import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import java.util.HashMap;

@Document(collection = "DayWiseCampaignDocument")
public class DayWiseCampaignDao {
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStage() {
        return stage;
    }

    public void setStage(String stage) {
        this.stage = stage;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public String getTemplateId() {
        return templateId;
    }

    public void setTemplateId(String templateId) {
        this.templateId = templateId;
    }

    public boolean isHasAttachment() {
        return hasAttachment;
    }

    public void setHasAttachment(boolean hasAttachment) {
        this.hasAttachment = hasAttachment;
    }

    public HashMap<String, String> getTypeWiseClient() {
        return typeWiseClient;
    }

    public void setTypeWiseClient(HashMap<String, String> typeWiseClient) {
        this.typeWiseClient = typeWiseClient;
    }
    @Id
    private String id;

private String stage;
private int day;
private String templateId;
private boolean hasAttachment;
private HashMap<String,String> typeWiseClient;



    public String getLastTime() {
        return lastTime;
    }

    public void setLastTime(String lastTime) {
        this.lastTime = lastTime;
    }

    private String lastTime;

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    private boolean status;

    @Override
    public String toString() {
        return "DayWiseCampaignDao{" +
                "id='" + id + '\'' +
                ", stage='" + stage + '\'' +
                ", day=" + day +
                ", templateId='" + templateId + '\'' +
                ", hasAttachment=" + hasAttachment +
                ", typeWiseClient=" + typeWiseClient +
                ", lastTime='" + lastTime + '\'' +
                ", status=" + status +
                '}';
    }
}
