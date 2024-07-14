package com.example.maxcrm.MaxCrm.Dao;

public class MsgStatusDao {

    private String status;
    private long count;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
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
        return "MsgStatusDao{" +
                "status='" + status + '\'' +
                ", count=" + count +
                '}';
    }
}
