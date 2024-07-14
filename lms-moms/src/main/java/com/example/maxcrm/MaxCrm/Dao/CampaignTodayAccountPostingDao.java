package com.example.maxcrm.MaxCrm.Dao;

import javax.persistence.*;

@Entity
@Table(name="Tbl_Campaign_TodayAccountPosting")
public class CampaignTodayAccountPostingDao {
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getCampaignId() {
        return campaignId;
    }

    public void setCampaignId(long campaignId) {
        this.campaignId = campaignId;
    }

    public String getTemplateId() {
        return templateId;
    }

    public void setTemplateId(String templateId) {
        this.templateId = templateId;
    }

    @Override
    public String toString() {
        return "CampaignTodayAccountPostingDao{" +
                "id=" + id +
                ", campaignId=" + campaignId +
                ", templateId='" + templateId + '\'' +
                ", datefilter=" + datefilter +

                ", type=" + type +
                '}';
    }

    public int getDatefilter() {
        return datefilter;
    }

    public void setDatefilter(int datefilter) {
        this.datefilter = datefilter;
    }



    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;



    public byte getType() {
        return type;
    }

    public void setType(byte type) {
        this.type = type;
    }

    private long campaignId;
    private String templateId;
    private int datefilter;

    private byte type;//1=count 2=clicks
    private int msgCount;

    public int getMsgCount() {
        return msgCount;
    }

    public void setMsgCount(int msgCount) {
        this.msgCount = msgCount;
    }
}
