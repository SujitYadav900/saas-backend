package com.example.maxcrm.MaxCrm.Dao;

public class CountMongodb {
    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public long getCount() {
        return count;
    }

    @Override
    public String toString() {
        return "CountMongodb{" +
                "value='" + value + '\'' +
                ", count=" + count +
                '}';
    }

    public void setCount(long count) {
        this.count = count;
    }

    private String value;
    private long count;

}
