package com.whatsappbuisness.wsbuissness.CombinePackadge.KarixEmailService;

import java.util.List;

public class EmailInnerDao {

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getHtml() {
        return html;
    }

    public void setHtml(String html) {
        this.html = html;
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


    private String subject;
    private String html;
    private String fromEmail="prp@prp.com";
    private String fromName="LMS Reminder Service";
    private String replyTo="prp@prp.com";
    private String recipient;

    @Override
    public String toString() {
        return "EmailInnerDao{" +
                "subject='" + subject + '\'' +
                ", html='" + html + '\'' +
                ", fromEmail='" + fromEmail + '\'' +
                ", fromName='" + fromName + '\'' +
                ", replyTo='" + replyTo + '\'' +
                ", recipient='" + recipient + '\'' +
                ", attachments=" + attachments +
                '}';


    }

    public List<MailingMessageAttachments> getAttachments() {
        return attachments;
    }

    public void setAttachments(List<MailingMessageAttachments> attachments) {
        this.attachments = attachments;
    }

    private List<MailingMessageAttachments> attachments;
}
