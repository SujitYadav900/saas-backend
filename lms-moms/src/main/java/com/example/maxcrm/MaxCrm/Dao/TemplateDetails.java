package com.example.maxcrm.MaxCrm.Dao;

public class TemplateDetails {
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCount() {
        return count;
    }

    @Override
    public String toString() {
        return "TemplateDetails{" +
                "status='" + status + '\'' +
                ", count='" + count + '\'' +
                '}';
    }

    public void setCount(String count) {
        this.count = count;
    }

    private String status;
    private String count;
}
