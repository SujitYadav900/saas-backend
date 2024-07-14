package com.example.maxcrm.MaxCrm.Dao;

import javax.persistence.*;

@Entity
@Table(name="Tbl_Ticket_Update_log")
public class TicketUpdateLogDao {
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getTicketid() {
        return ticketid;
    }

    public void setTicketid(long ticketid) {
        this.ticketid = ticketid;
    }

    public String getTostatus() {
        return tostatus;
    }

    public void setTostatus(String tostatus) {
        this.tostatus = tostatus;
    }

    @Override
    public String toString() {
        return "TicketUpdateLogDao{" +
                "id=" + id +
                ", ticketid=" + ticketid +
                ", tostatus='" + tostatus + '\'' +
                ", timestamp='" + timestamp + '\'' +
                ", datefilter=" + datefilter +
                '}';
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public int getDatefilter() {
        return datefilter;
    }

    public void setDatefilter(int datefilter) {
        this.datefilter = datefilter;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private long ticketid;
    private String tostatus;
    private String timestamp;
    private int datefilter;




    }

