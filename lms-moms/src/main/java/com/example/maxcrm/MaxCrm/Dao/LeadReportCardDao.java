package com.example.maxcrm.MaxCrm.Dao;

public class LeadReportCardDao {

    private int seq;
    private String description;
    private int count;
    private int convertCount;
    private int rejectCount;
    private int pendingCount;
    private int assignedCount;
    private int assessmentScheduled;
    private int rescheduled;
    private int noShowPending;
    private String clientType;//for showing clientType in 'Lead Stats Date Wise' report
    private String leadIds;

    //FOR TRANSFER STATS
    private String transferedBy;
    private String transferedFrom;
    private String transferedTo;

    public int getConvertCount() {
        return convertCount;
    }

    public void setConvertCount(int convertCount) {
        this.convertCount = convertCount;
    }

    public String getTransferedBy() {
        return transferedBy;
    }

    public void setTransferedBy(String transferedBy) {
        this.transferedBy = transferedBy;
    }

    public String getTransferedFrom() {
        return transferedFrom;
    }

    public void setTransferedFrom(String transferedFrom) {
        this.transferedFrom = transferedFrom;
    }

    public String getTransferedTo() {
        return transferedTo;
    }

    public String getLeadIds() {
        return leadIds;
    }

    public void setLeadIds(String leadIds) {
        this.leadIds = leadIds;
    }

    @Override
    public String toString() {
        return "LeadReportCardDao{" +
                "seq=" + seq +
                ", description='" + description + '\'' +
                ", count=" + count +
                ", convertCount=" + convertCount +
                ", rejectCount=" + rejectCount +
                ", pendingCount=" + pendingCount +
                ", assignedCount=" + assignedCount +
                ", assessmentScheduled=" + assessmentScheduled +
                ", rescheduled=" + rescheduled +
                ", noShowPending=" + noShowPending +
                ", clientType='" + clientType + '\'' +
                ", leadIds='" + leadIds + '\'' +
                ", transferedBy='" + transferedBy + '\'' +
                ", transferedFrom='" + transferedFrom + '\'' +
                ", transferedTo='" + transferedTo + '\'' +
                '}';
    }

    public void setTransferedTo(String transferedTo) {
        this.transferedTo = transferedTo;
    }

    public int getSeq() {
        return seq;
    }

    public void setSeq(int seq) {
        this.seq = seq;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getRejectCount() {
        return rejectCount;
    }

    public void setRejectCount(int rejectCount) {
        this.rejectCount = rejectCount;
    }

    public int getPendingCount() {
        return pendingCount;
    }

    public void setPendingCount(int pendingCount) {
        this.pendingCount = pendingCount;
    }

    public int getAssignedCount() {
        return assignedCount;
    }

    public void setAssignedCount(int assignedCount) {
        this.assignedCount = assignedCount;
    }

    public int getAssessmentScheduled() {
        return assessmentScheduled;
    }

    public void setAssessmentScheduled(int assessmentScheduled) {
        this.assessmentScheduled = assessmentScheduled;
    }

    public int getRescheduled() {
        return rescheduled;
    }

    public void setRescheduled(int rescheduled) {
        this.rescheduled = rescheduled;
    }

    public int getNoShowPending() {
        return noShowPending;
    }

    public void setNoShowPending(int noShowPending) {
        this.noShowPending = noShowPending;
    }

    public String getClientType() {
        return clientType;
    }

    public void setClientType(String clientType) {
        this.clientType = clientType;
    }


}
