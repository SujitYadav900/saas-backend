package com.example.maxcrm.MaxCrm.Dao;

public class MailObjectMessage {
    @Override
    public String toString() {
        return "MailObjectMessage{" +
                "custRef='" + custRef + '\'' +
                ", subject='" + subject + '\'' +
                ", fromEmail='" + fromEmail + '\'' +
                ", fromName='" + fromName + '\'' +
                ", replyTo='" + replyTo + '\'' +
                ", recipient='" + recipient + '\'' +
                ", Message='" + Message + '\'' +
                '}';
    }

    public String getCustRef() {
        return custRef;
    }

    public void setCustRef(String custRef) {
        this.custRef = custRef;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getFromEmail() {
        return fromEmail;
    }

    public void setFromEmail(String fromEmail) {
        this.fromEmail = fromEmail;
    }

    public String getFromName() {
        return fromName;
    }

    public void setFromName(String fromName) {
        this.fromName = fromName;
    }

    public String getReplyTo() {
        return replyTo;
    }

    public void setReplyTo(String replyTo) {
        this.replyTo = replyTo;
    }

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    private String custRef;
private String subject;
private String fromEmail;
private String fromName;
private String replyTo;
private String recipient;
private String Message;

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }
}
