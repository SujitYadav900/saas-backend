package com.example.maxcrm.MaxCrm.Dao;

import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import java.util.Arrays;
import java.util.List;

@Document(collection = "TemplateDocument")
public class TemplateDocument {
    @Id
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "TemplateDocument{" +
                "id='" + id + '\'' +
                ", templateName='" + templateName + '\'' +
                ", template='" + template + '\'' +
                ", templateSubject='" + templateSubject + '\'' +
                ", templateType='" + templateType + '\'' +
                ", templateContent='" + templateContent + '\'' +
                ", templateStatus=" + templateStatus +
                ", createBy='" + createBy + '\'' +
                ", createAt='" + createAt + '\'' +
                ", variable='" + variable + '\'' +
                ", contentType=" + contentType +
                ", params=" + Arrays.toString(params) +
                ", templateIdHidden='" + templateIdHidden + '\'' +
                ", approved=" + approved +
                ", hasUrl=" + hasUrl +
                ", datefilter=" + datefilter +
                ", templateDetails=" + templateDetails +
                '}';
    }

    public String getTemplateName() {
        return templateName;
    }

    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }

    public String getTemplateContent() {
        return templateContent;
    }

    public void setTemplateContent(String templateContent) {
        this.templateContent = templateContent;
    }

    public byte getTemplateStatus() {
        return templateStatus;
    }

    public void setTemplateStatus(byte templateStatus) {
        this.templateStatus = templateStatus;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public String getCreateAt() {
        return createAt;
    }

    public void setCreateAt(String createAt) {
        this.createAt = createAt;
    }



    public String getTemplateType() {
        return templateType;
    }

    public void setTemplateType(String templateType) {
        this.templateType = templateType;
    }



    private String templateName;

    public String getTemplateSubject() {
        return templateSubject;
    }

    public void setTemplateSubject(String templateSubject) {
        this.templateSubject = templateSubject;
    }

    public String getTemplate() {
        return template;
    }

    public void setTemplate(String template) {
        this.template = template;
    }

    private String template;
    private String templateSubject;
    private String templateType;
    private String templateContent;
    private byte templateStatus;
    private String createBy;
    private String createAt;
    private String variable;

    public byte getContentType() {
        return contentType;
    }

    public void setContentType(byte contentType) {
        this.contentType = contentType;
    }

    private byte contentType;

    public String[] getParams() {
        return params;
    }

    public void setParams(String[] params) {
        this.params = params;
    }

    private String[] params;

    public String getTemplateIdHidden() {
        return templateIdHidden;
    }

    public void setTemplateIdHidden(String templateIdHidden) {
        this.templateIdHidden = templateIdHidden;
    }

    private String templateIdHidden;

    public boolean isApproved() {
        return approved;
    }

    public void setApproved(boolean approved) {
        this.approved = approved;
    }

    private boolean approved;


    public boolean isHasUrl() {
        return hasUrl;
    }

    public void setHasUrl(boolean hasUrl) {
        this.hasUrl = hasUrl;
    }

    private boolean hasUrl;

    public String getVariable() {
        return variable;
    }

    public void setVariable(String variable) {
        this.variable = variable;
    }

    public int getDatefilter() {
        return datefilter;
    }

    public void setDatefilter(int datefilter) {
        this.datefilter = datefilter;
    }



    @Indexed
    private int datefilter;










    private List<TemplateDetails> templateDetails;

    public List<TemplateDetails> getTemplateDetails() {
        return templateDetails;
    }

    public void setTemplateDetails(List<TemplateDetails> templateDetails) {
        this.templateDetails = templateDetails;
    }








}
