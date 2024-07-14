package com.whatsappbuisness.wsbuissness.CombinePackadge.EventsWebhooks;

public class DlrStatusDao {
    @Override
    public String toString() {
        return "DlrStatusDao{" +
                "id='" + id + '\'' +
                ", recipient_id='" + recipient_id + '\'' +
                ", status='" + status + '\'' +
                ", timestamp='" + timestamp + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRecipient_id() {
        return recipient_id;
    }

    public void setRecipient_id(String recipient_id) {
        this.recipient_id = recipient_id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    private String id;
    private String recipient_id;
    private String status;
    private String timestamp;

}
