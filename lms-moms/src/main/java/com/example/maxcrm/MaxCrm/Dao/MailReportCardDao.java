package com.example.maxcrm.MaxCrm.Dao;

public class MailReportCardDao {
    private int status;
    private long count;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "MailReportCardDao{" +
                "status=" + status +
                ", count=" + count +
                '}';
    }
}
