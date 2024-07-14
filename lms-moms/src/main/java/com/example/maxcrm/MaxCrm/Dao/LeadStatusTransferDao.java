package com.example.maxcrm.MaxCrm.Dao;

import javax.persistence.*;

@Entity
@Table(name = "Tbl_LeadStatusTransfer")
public class LeadStatusTransferDao {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;
    private String status;
    private String stage;
    private String preStatus;
    private String preStage;
    private long leadId;
    private long timing;
    private String readableDate;
    private String createBy;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public long getLeadId() {
        return leadId;
    }

    public void setLeadId(long leadId) {
        this.leadId = leadId;
    }

    public long getTiming() {
        return timing;
    }

    public void setTiming(long timing) {
        this.timing = timing;
    }

    public String getReadableDate() {
        return readableDate;
    }

    public void setReadableDate(String readableDate) {
        this.readableDate = readableDate;
    }

    public String getStage() {
        return stage;
    }

    public void setStage(String stage) {
        this.stage = stage;
    }

    public String getPreStatus() {
        return preStatus;
    }

    public void setPreStatus(String preStatus) {
        this.preStatus = preStatus;
    }

    public String getPreStage() {
        return preStage;
    }

    public void setPreStage(String preStage) {
        this.preStage = preStage;
    }

    @Override
    public String toString() {
        return "LeadStatusTransferDao{" +
                "id=" + id +
                ", status='" + status + '\'' +
                ", stage='" + stage + '\'' +
                ", preStatus='" + preStatus + '\'' +
                ", preStage='" + preStage + '\'' +
                ", leadId=" + leadId +
                ", timing=" + timing +
                ", readableDate='" + readableDate + '\'' +
                ", createBy='" + createBy + '\'' +
                '}';
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }
}
