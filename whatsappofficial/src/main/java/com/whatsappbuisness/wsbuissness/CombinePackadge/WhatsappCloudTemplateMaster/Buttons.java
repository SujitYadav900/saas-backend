package com.whatsappbuisness.wsbuissness.CombinePackadge.WhatsappCloudTemplateMaster;

import java.io.Serializable;
import java.util.List;

public class Buttons implements Serializable {
    private String type;
    private String text;
    private String phone_number;
    private String url;
    private List<String> example;
    private String urlType;


    public String getUrlType() {
        return urlType;
    }

    public void setUrlType(String urlType) {
        this.urlType = urlType;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<String> getExample() {
        return example;
    }

    public void setExample(List<String> example) {
        this.example = example;
    }

    @Override
    public String toString() {
        return "Buttons{" +
                "type='" + type + '\'' +
                ", text='" + text + '\'' +
                ", phone_number='" + phone_number + '\'' +
                ", url='" + url + '\'' +
                ", example=" + example +
                ", urlType='" + urlType + '\'' +
                '}';
    }
}
