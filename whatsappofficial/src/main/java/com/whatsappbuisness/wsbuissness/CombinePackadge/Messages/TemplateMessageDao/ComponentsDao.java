package com.whatsappbuisness.wsbuissness.CombinePackadge.Messages.TemplateMessageDao;
/*
 Author=Supreet Singh
 Date= 04/02/21 5:31 PM
*/

import java.io.Serializable;
import java.util.List;

public class ComponentsDao implements Serializable {
    @Override
    public String toString() {
        return "ComponentsDao{" +
                "type='" + type + '\'' +
                ", sub_type='" + sub_type + '\'' +
                ", index=" + index +
                ", parameters=" + parameters +
                ", urlType='" + urlType + '\'' +
                '}';
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<ParametersDao> getParameters() {
        return parameters;
    }

    public void setParameters(List<ParametersDao> parameters) {
        this.parameters = parameters;
    }

    private String type;

    public String getSub_type() {
        return sub_type;
    }

    public void setSub_type(String sub_type) {
        this.sub_type = sub_type;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getUrlType() {
        return urlType;
    }

    public void setUrlType(String urlType) {
        this.urlType = urlType;
    }

    private String sub_type;
    private int index;
    private List<ParametersDao> parameters;
    private String urlType;
}
