package com.example.maxcrm.MaxCrm.Dao;

import java.util.HashMap;

public class TextMessageSimpleDao {
    public String getDst() {
        return dst;
    }

    public void setDst(String dst) {
        this.dst = dst;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSenderId() {
        return senderId;
    }

    public void setSenderId(String senderId) {
        this.senderId = senderId;
    }

    @Override
    public String toString() {
        return "TextMessageSimpleDao{" +
                "dst='" + dst + '\'' +
                ", content='" + content + '\'' +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", senderId='" + senderId + '\'' +
                '}';
    }

    private String dst;
    private String content;
    private String userName;
    private String password;
    private String senderId;

}
