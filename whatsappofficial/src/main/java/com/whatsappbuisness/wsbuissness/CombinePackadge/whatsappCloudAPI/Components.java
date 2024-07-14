package com.whatsappbuisness.wsbuissness.CombinePackadge.whatsappCloudAPI;

import java.io.Serializable;
import java.util.List;

public class Components  {
    private String type;
    private List<Parameters> parameters;

    private String sub_type;
    private String index;

    public String getSub_type() {
        return sub_type;
    }

    public void setSub_type(String sub_type) {
        this.sub_type = sub_type;
    }

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<Parameters> getParameters() {
        return parameters;
    }

    public void setParameters(List<Parameters> parameters) {
        this.parameters = parameters;
    }

    @Override
    public String toString() {
        return "Components{" +
                "type='" + type + '\'' +
                ", parameters=" + parameters +
                ", sub_type='" + sub_type + '\'' +
                ", index='" + index + '\'' +
                '}';
    }
}
