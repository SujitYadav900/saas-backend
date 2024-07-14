package com.example.maxcrm.MaxCrm.Dao;

public class LeadResponseDao {
    private int statusCode;
    private long leadId;
    private String leadStage;
    private String leadStatus;
    private String leadOwner;

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public long getLeadId() {
        return leadId;
    }

    public void setLeadId(long leadId) {
        this.leadId = leadId;
    }

    public String getLeadStage() {
        return leadStage;
    }

    public void setLeadStage(String leadStage) {
        this.leadStage = leadStage;
    }

    public String getLeadStatus() {
        return leadStatus;
    }

    public void setLeadStatus(String leadStatus) {
        this.leadStatus = leadStatus;
    }

    public String getLeadOwner() {
        return leadOwner;
    }

    public void setLeadOwner(String leadOwner) {
        this.leadOwner = leadOwner;
    }

    @Override
    public String toString() {
        return "LeadResponseDao{" +
                "statusCode=" + statusCode +
                ", leadId=" + leadId +
                ", leadStage='" + leadStage + '\'' +
                ", leadStatus='" + leadStatus + '\'' +
                ", leadOwner='" + leadOwner + '\'' +
                '}';
    }
}
