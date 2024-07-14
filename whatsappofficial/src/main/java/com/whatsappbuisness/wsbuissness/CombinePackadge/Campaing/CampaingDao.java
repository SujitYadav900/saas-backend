package com.whatsappbuisness.wsbuissness.CombinePackadge.Campaing;
/*
 Author=Supreet Singh
 Date= 16/03/21 12:20 PM
*/

import javax.persistence.*;

@Entity
@Table(name="Tbl_Campaign" )
public class CampaingDao {

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCampaingName() {
        return campaingName;
    }

    public void setCampaingName(String campaingName) {
        this.campaingName = campaingName;
    }

    public int getTotalReciepts() {
        return totalReciepts;
    }

    public void setTotalReciepts(int totalReciepts) {
        this.totalReciepts = totalReciepts;
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

    public long getAccountId() {
        return accountId;
    }

    public void setAccountId(long accountId) {
        this.accountId = accountId;
    }

    public byte getiScheduled() {
        return iScheduled;
    }

    public void setiScheduled(byte iScheduled) {
        this.iScheduled = iScheduled;
    }

    public String getScheduledTime() {
        return scheduledTime;
    }

    public void setScheduledTime(String scheduledTime) {
        this.scheduledTime = scheduledTime;
    }

    @Override
    public String toString() {
        return "CampaingDao{" +
                "id=" + id +
                ", campaingName='" + campaingName + '\'' +
                ", totalReciepts=" + totalReciepts +
                ", createBy='" + createBy + '\'' +
                ", createAt='" + createAt + '\'' +
                ", accountId=" + accountId +
                ", template='" + template + '\'' +
                ", iScheduled=" + iScheduled +
                ", scheduledTime='" + scheduledTime + '\'' +
                ", country='" + country + '\'' +
                '}';
    }

    public String getTemplate() {
        return template;
    }

    public void setTemplate(String template) {
        this.template = template;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String campaingName;
    private int totalReciepts;
    private String createBy;
    private String createAt;
    private long accountId;
    private String template;
    private byte iScheduled;
    private String scheduledTime;
    private String country;
}
