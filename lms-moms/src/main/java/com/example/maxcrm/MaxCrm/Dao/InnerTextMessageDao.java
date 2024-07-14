package com.example.maxcrm.MaxCrm.Dao;

public class InnerTextMessageDao {
    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    @Override
    public String toString() {
        return "InnerTextMessageDao{" +
                "mobileNo='" + mobileNo + '\'' +
                ", templateName='" + templateName + '\'' +
                ", templateParams='" + templateParams + '\'' +
                '}';
    }

    public String getTemplateName() {
        return templateName;
    }

    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }

    public String getTemplateParams() {
        return templateParams;
    }

    public void setTemplateParams(String templateParams) {
        this.templateParams = templateParams;
    }

    private String mobileNo;
    private String templateName;
    private String templateParams;





}
