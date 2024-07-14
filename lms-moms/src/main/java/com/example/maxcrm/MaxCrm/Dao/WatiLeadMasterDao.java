package com.example.maxcrm.MaxCrm.Dao;

public class WatiLeadMasterDao {

    private String id;
    private String conversationId;
    private String ticketId;
    private String text;
    private String type;
    //private String data;
    private String timestamp;
    private String owner;
    private String eventType;
    private String statusString;
    private String waId; //waId means mobile number
    private String senderName;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getConversationId() {
        return conversationId;
    }

    public void setConversationId(String conversationId) {
        this.conversationId = conversationId;
    }

    public String getTicketId() {
        return ticketId;
    }

    public void setTicketId(String ticketId) {
        this.ticketId = ticketId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

//    public String getData() {
//        return data;
//    }
//
//    public void setData(String data) {
//        this.data = data;
//    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public String getStatusString() {
        return statusString;
    }

    public void setStatusString(String statusString) {
        this.statusString = statusString;
    }

    public String getWaId() {
        return waId;
    }

    public void setWaId(String waId) {
        this.waId = waId;
    }

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    @Override
    public String toString() {
        return "WatiLeadMasterDao{" +
                "id='" + id + '\'' +
                ", conversationId='" + conversationId + '\'' +
                ", ticketId='" + ticketId + '\'' +
                ", text='" + text + '\'' +
                ", type='" + type + '\'' +
//                ", data='" + data + '\'' +
                ", timestamp='" + timestamp + '\'' +
                ", owner='" + owner + '\'' +
                ", eventType='" + eventType + '\'' +
                ", statusString='" + statusString + '\'' +
                ", waId='" + waId + '\'' +
                ", senderName='" + senderName + '\'' +
                '}';
    }
}
