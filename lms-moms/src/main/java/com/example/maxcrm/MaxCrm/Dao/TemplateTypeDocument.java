package com.example.maxcrm.MaxCrm.Dao;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "TemplateTypeDocument")
public class TemplateTypeDocument {
    public String getTemplateType() {
        return templateType;
    }

    public void setTemplateType(String templateType) {
        this.templateType = templateType;
    }


    @Override
    public String toString() {
        return "TemplateTypeDocument{" +
                "templateType='" + templateType + '\'' +
                ", variable=" + variable +
                ", status=" + status +
                ", leadSource=" + source +
                '}';
    }

    public List<VariableOrSource> getVariable() {
        return variable;
    }

    public void setVariable(List<VariableOrSource> variable) {
        this.variable = variable;
    }

    public byte getStatus() {
        return status;
    }

    public void setStatus(byte status) {
        this.status = status;
    }



    @Id
    private String templateType;
    private List<VariableOrSource> variable;
    private byte status;

    public List<VariableOrSource> getSource() {
        return source;
    }

    public void setSource(List<VariableOrSource> source) {
        this.source = source;
    }

    private List<VariableOrSource> source;


}

