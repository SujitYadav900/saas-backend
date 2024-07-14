package com.whatsappbuisness.wsbuissness.CombinePackadge.KarixEmailService;

import java.io.Serializable;

public class MailingMessageAttachments implements Serializable {
    public MailingMessageAttachments(String name, String attachmentData) {
        this.name = name;
        this.attachmentData = attachmentData;
    }

    @Override
    public String toString() {
        return "MailingMessageAttachments{" +
                "name='" + name + '\'' +
                ", attachmentData='" + attachmentData + '\'' +
                '}';
    }

    public String getAttachmentData() {
        return attachmentData;
    }

    public void setAttachmentData(String attachmentData) {
        this.attachmentData = attachmentData;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String name;
    private String attachmentData;

}
