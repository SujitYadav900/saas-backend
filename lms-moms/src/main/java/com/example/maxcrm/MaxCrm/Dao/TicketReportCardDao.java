package com.example.maxcrm.MaxCrm.Dao;

public class TicketReportCardDao {

    private int seq;
    private String description;
    private double count;

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

    public double getCount() {
        return count;
    }

    public void setCount(double count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "TicketReportCard{" +
                "seq=" + seq +
                ", description='" + description + '\'' +
                ", count=" + count +
                '}';
    }
}
