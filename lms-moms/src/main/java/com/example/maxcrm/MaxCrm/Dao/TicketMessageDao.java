package com.example.maxcrm.MaxCrm.Dao;

import javax.persistence.*;

@Entity
@Table(name="Tbl_Ticket_Message")
public class TicketMessageDao {

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getSenddate() {
        return senddate;
    }

    public void setSenddate(String senddate) {
        this.senddate = senddate;
    }

    public long getTicketid() {
        return ticketid;
    }

    public void setTicketid(long ticketid) {
        this.ticketid = ticketid;
    }

    public String getAttachmentlist() {
        return attachmentlist;
    }

    public void setAttachmentlist(String attachmentlist) {
        this.attachmentlist = attachmentlist;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String sender;
    @Transient
    private int reciever;
    private String message;
    private String senddate;
    private long ticketid;
    private String attachmentlist;

    public int getReciever() {
        return reciever;
    }

    @Override
    public String toString() {
        return "TicketMessageDao{" +
                "id=" + id +
                ", sender='" + sender + '\'' +
                ", reciever=" + reciever +
                ", message='" + message + '\'' +
                ", senddate='" + senddate + '\'' +
                ", ticketid=" + ticketid +
                ", attachmentlist='" + attachmentlist + '\'' +
                '}';
    }

    public void setReciever(int reciever) {
        this.reciever = reciever;
    }





}
