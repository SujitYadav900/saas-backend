package com.example.maxcrm.MaxCrm.Dao;

public class

ReportAllTicketDao {

    private int seq;
    private String description;
    private int count;

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

    @Override
    public String toString() {
        return "ReportAllLeadDao{" +
                "seq=" + seq +
                ", description='" + description + '\'' +
                ", count=" + count +
                '}';
    }
}
