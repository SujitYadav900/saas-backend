package com.example.maxcrm.MaxCrm.Dao;

public class CommonCreateInfo {

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getUdateDate() {
        return udateDate;
    }

    public void setUdateDate(String udateDate) {
        this.udateDate = udateDate;
    }

    private String createBy;
    private String updateBy;
    private String createDate;
    private String udateDate;
}
