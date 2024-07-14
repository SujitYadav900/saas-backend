package com.example.maxcrm.MaxCrm.CombinePackage.Webhooks;

import com.google.gson.Gson;

import java.util.HashMap;

public class WebhookDao {
    @Override
    public String toString() {
        return "WebhookDao{" +
                "url='" + url + '\'' +
                ", requestType='" + requestType + '\'' +
                ", parameters=" + parameters +
                ", isReply=" + isReply +
                '}';
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getRequestType() {
        return requestType;
    }

    public void setRequestType(String requestType) {
        this.requestType = requestType;
    }

    public HashMap<String, String> getParameters() {
        return parameters;
    }

    public void setParameters(HashMap<String, String> parameters) {
        this.parameters = parameters;
    }

    public boolean isReply() {
        return isReply;
    }

    public void setReply(boolean reply) {
        isReply = reply;
    }

    private String url;
    private String requestType;
    private HashMap<String,String> parameters;
    private boolean isReply;
    public String convertToJson()
    {
        return new Gson().toJson(this);
    }

}
