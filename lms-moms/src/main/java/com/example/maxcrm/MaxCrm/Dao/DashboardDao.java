package com.example.maxcrm.MaxCrm.Dao;

public class DashboardDao {
    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getDesc() {
        return desc;
    }

    @Override
    public String toString() {
        return "DashboardDao{" +
                "count=" + count +
                ", desc='" + desc + '\'' +
                ", seq=" + seq +
                '}';
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getSeq() {
        return seq;
    }

    public void setSeq(int seq) {
        this.seq = seq;
    }
    private int count;
    private String desc;
    private int seq;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    private String value;

}
