package com.whatsappbuisness.wsbuissness.CombinePackadge.Notification;

import java.util.List;
import java.util.Map;

public class AlertMessageDao {
    private String id;
    private NotifyType notifyType;
    private List<Map<String,String>> messages;
    private String date;
    private String dateFilter;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public NotifyType getNotifyType() {
        return notifyType;
    }

    public void setNotifyType(NotifyType notifyType) {
        this.notifyType = notifyType;
    }

    public List<Map<String, String>> getMessages() {
        return messages;
    }

    public void setMessages(List<Map<String, String>> messages) {
        this.messages = messages;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDateFilter() {
        return dateFilter;
    }

    public void setDateFilter(String dateFilter) {
        this.dateFilter = dateFilter;
    }

    @Override
    public String toString() {
        return "AlertMessageDao{" +
                "id='" + id + '\'' +
                ", notifyType=" + notifyType +
                ", messages=" + messages +
                ", date='" + date + '\'' +
                ", dateFilter='" + dateFilter + '\'' +
                '}';
    }
}
