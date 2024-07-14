package com.example.maxcrm.MaxCrm.Dao;


import com.google.gson.Gson;

import java.util.List;

public class TextMessage {


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public String toString() {
        return "TextMessage{" +
                "userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", sender='" + sender + '\'' +
                ", smsTemplateParams=" + smsTemplateParams +
                '}';
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public List<InnerTextMessageDao> getSmsTemplateParams() {
        return smsTemplateParams;
    }

    public void setSmsTemplateParams(List<InnerTextMessageDao> smsTemplateParams) {
        this.smsTemplateParams = smsTemplateParams;
    }

    private String userName;
    private String password;
    private String sender;
    private List<InnerTextMessageDao> smsTemplateParams;
    public String converObjectToJson()
    {
        return new Gson().toJson(this);
    }



}
