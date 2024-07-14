package com.example.maxcrm.MaxCrm.Dao;

import javax.persistence.*;


@Entity
@Table(name="Tbl_Ticket_Forward_Log")
public class TicketForwardLogDao {

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



    public String getToagent() {
        return toagent;
    }

    public void setToagent(String toagent) {
        this.toagent = toagent;
    }

    public int getDatefilter() {
        return datefilter;
    }

    public void setDatefilter(int datefilter) {
        this.datefilter = datefilter;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private long ticketid;

    public String getCreateBy() {
        return createBy;
    }

    @Override
    public String toString() {
        return "TicketForwardLogDao{" +
                "id=" + id +
                ", ticketid=" + ticketid +
                ", createBy='" + createBy + '\'' +
                ", toagent='" + toagent + '\'' +
                ", datefilter=" + datefilter +
                ", timestamp='" + timestamp + '\'' +
                '}';
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    private String createBy;

    private String toagent;
    private int datefilter;
    private String timestamp;


}
