package com.example.maxcrm.MaxCrm.Dao;

public class WhatsappResponseDao {

    //{"status":0,"responseMessage":"Sent","messageId":1616940,"id":"CCBBMTYYNTGXMTU0MDM1NJQ1OTK"}


    private int status;
    private String responseMessage;
    private String messageId;
    private String id;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getResponseMessage() {
        return responseMessage;
    }

    public void setResponseMessage(String responseMessage) {
        this.responseMessage = responseMessage;
    }

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "WhatsappResponseDao{" +
                "status=" + status +
                ", responseMessage='" + responseMessage + '\'' +
                ", messageId=" + messageId +
                ", id='" + id + '\'' +
                '}';
    }
}
