package com.example.maxcrm.MaxCrm.Dao;

import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Arrays;
import java.util.List;

@Document(collection = "MailSentDocument")
public class MailSentDocument {
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String[] getTo() {
        return to;
    }

    public void setTo(String[] to) {
        this.to = to;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public List<AttachmentDao> getAttachmentDaos() {
        return attachmentDaos;
    }

    public void setAttachmentDaos(List<AttachmentDao> attachmentDaos) {
        this.attachmentDaos = attachmentDaos;
    }

    @Override
    public String toString() {
        return "MailSentDocument{" +
                "id='" + id + '\'' +
                ", message='" + message + '\'' +
                ", subject='" + subject + '\'' +
                ", to=" + Arrays.toString(to) +
                ", from='" + from + '\'' +
                ", attachmentDaos=" + attachmentDaos +
                ", createBy='" + createBy + '\'' +
                ", createDate='" + createDate + '\'' +
                ", datefilter=" + datefilter +
                '}';
    }

    private String id;
    private String message;
    private String subject;
    private String[] to;
    private String from;
    private List<AttachmentDao> attachmentDaos;
    private String createBy;
    private String createDate;
    @Indexed(name = "datefilter_index", direction = IndexDirection.DESCENDING)
    private int datefilter;

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public int getDatefilter() {
        return datefilter;
    }

    public void setDatefilter(int datefilter) {
        this.datefilter = datefilter;
    }
}
