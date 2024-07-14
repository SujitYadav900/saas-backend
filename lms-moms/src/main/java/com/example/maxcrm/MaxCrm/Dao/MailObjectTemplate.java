package com.example.maxcrm.MaxCrm.Dao;

public class MailObjectTemplate {
    @Override
    public String toString() {
        return "MailObjectTemplate{" +
                "templateId='" + templateId + '\'' +
                ", templateValues=" + templateValues +
                '}';
    }

    public String getTemplateId() {
        return templateId;
    }

    public void setTemplateId(String templateId) {
        this.templateId = templateId;
    }

    public CampaignObjectDao getTemplateValues() {
        return templateValues;
    }

    public void setTemplateValues(CampaignObjectDao templateValues) {
        this.templateValues = templateValues;
    }

    private String templateId;
    private CampaignObjectDao templateValues;
}
