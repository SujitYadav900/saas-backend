package com.whatsappbuisness.wsbuissness.CombinePackadge.KarixWaOfficial.KarixDao;

import java.util.HashMap;
/*
 Author=Ritu Redhu
 Date= 06/01/23 10:00 AM
*/
public class Template {

    private String templateId;

    private HashMap<Integer, String> parameterValues;

    public String getTemplateId() {
        return templateId;
    }

    public void setTemplateId(String templateId) {
        this.templateId = templateId;
    }

    public HashMap<Integer, String> getParameterValues() {
        return parameterValues;
    }

    public void setParameterValues(HashMap<Integer, String> parameterValues) {
        this.parameterValues = parameterValues;
    }

    @Override
    public String toString() {
        return "Template{" +
                "templateId='" + templateId + '\'' +
                ", parameterValues=" + parameterValues +
                '}';
    }
}
