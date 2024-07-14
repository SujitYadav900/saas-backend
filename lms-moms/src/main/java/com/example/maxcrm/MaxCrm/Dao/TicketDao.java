package com.example.maxcrm.MaxCrm.Dao;

import javax.persistence.*;

@Entity
@Table(name = "Tbl_Ticket")
public class TicketDao {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String createdby;

    private long leadid;
    private String ticketstatus;
    private String subject;

    private String type;
    private String department;
    @Transient
    private String username;
    private String priority;
    private String description;
    private String attachmentlist;
    private String createdate;
    private int lastforward;//current responder to the ticket
    private String updatedate;
    private String updateby;
    private int datefilter;
    private int updatedatefilter;

    @Override
    public String toString() {
        return "TicketDao{" +
                "id=" + id +
                ", createdby='" + createdby + '\'' +
                ", leadid=" + leadid +
                ", ticketstatus='" + ticketstatus + '\'' +
                ", subject='" + subject + '\'' +
                ", type='" + type + '\'' +
                ", department='" + department + '\'' +
                ", username='" + username + '\'' +
                ", priority='" + priority + '\'' +
                ", description='" + description + '\'' +
                ", attachmentlist='" + attachmentlist + '\'' +
                ", createdate='" + createdate + '\'' +
                ", lastforward=" + lastforward +
                ", updatedate='" + updatedate + '\'' +
                ", updateby='" + updateby + '\'' +
                ", datefilter=" + datefilter +
                ", updatedatefilter=" + updatedatefilter +
                ", closeDateFilter=" + closeDateFilter +
                ", datefilterClose=" + datefilterClose +
                ", closeDate='" + closeDate + '\'' +
                ", isClose=" + isClose +
                ", closeTimeTaked=" + closeTimeTaked +
                '}';
    }

    public int getUpdatedatefilter() {
        return updatedatefilter;
    }

    public void setUpdatedatefilter(int updatedatefilter) {
        this.updatedatefilter = updatedatefilter;
    }

    public boolean isClose() {
        return isClose;
    }

    public void setClose(boolean close) {
        isClose = close;
    }

    public int getCloseDateFilter() {
        return closeDateFilter;
    }

    public void setCloseDateFilter(int closeDateFilter) {
        this.closeDateFilter = closeDateFilter;
    }

    private int closeDateFilter;



    private int datefilterClose;
    private String closeDate;
    private boolean isClose;
    private long closeTimeTaked;

    public long getCloseTimeTaked() {
        return closeTimeTaked;
    }

    public void setCloseTimeTaked(long closeTimeTaked) {
        this.closeTimeTaked = closeTimeTaked;
    }

    public int getDatefilterClose() {
        return datefilterClose;
    }

    public void setDatefilterClose(int datefilterClose) {
        this.datefilterClose = datefilterClose;
    }

    public String getCloseDate() {
        return closeDate;
    }

    public void setCloseDate(String closeDate) {
        this.closeDate = closeDate;
    }




    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


    public String getCreatedby() {
        return createdby;
    }

    public void setCreatedby(String createdby) {
        this.createdby = createdby;
    }

    public long getLeadid() {
        return leadid;
    }

    public void setLeadid(long leadid) {
        this.leadid = leadid;
    }

    public String getTicketstatus() {
        return ticketstatus;
    }

    public void setTicketstatus(String ticketstatus) {
        this.ticketstatus = ticketstatus;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAttachmentlist() {
        return attachmentlist;
    }

    public void setAttachmentlist(String attachmentlist) {
        this.attachmentlist = attachmentlist;
    }

    public String getCreatedate() {
        return createdate;
    }

    public void setCreatedate(String createdate) {
        this.createdate = createdate;
    }

    public int getLastforward() {
        return lastforward;
    }

    public void setLastforward(int lastforward) {
        this.lastforward = lastforward;
    }

    public String getUpdatedate() {
        return updatedate;
    }

    public void setUpdatedate(String updatedate) {
        this.updatedate = updatedate;
    }

    public String getUpdateby() {
        return updateby;
    }

    public void setUpdateby(String updateby) {
        this.updateby = updateby;
    }

    public int getDatefilter() {
        return datefilter;
    }

    public void setDatefilter(int datefilter) {
        this.datefilter = datefilter;
    }
}
