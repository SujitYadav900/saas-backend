package com.example.maxcrm.MaxCrm.Dao;

import javax.persistence.*;

@Entity
@Table(name = "Tbl_LeadTransfer")
public class LeadTransferDao {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private long leadId;
    private String createBy;
    private String datetiming;
    private int toId;
    private String fromusername;
    private String tousername;
    private String clientType;// FOR CHANGING LEAD TYPE ALONG WITH TRANSFER(OPTIONAL)

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public long getLeadId() {
        return leadId;
    }

    public String getFromusername() {
        return fromusername;
    }

    public void setFromusername(String fromusername) {
        this.fromusername = fromusername;
    }

    @Override
    public String toString() {
        return "LeadTransferDao{" +
                "id=" + id +
                ", leadId=" + leadId +
                ", createBy='" + createBy + '\'' +
                ", datetiming='" + datetiming + '\'' +
                ", toId=" + toId +
                ", fromusername='" + fromusername + '\'' +
                ", tousername='" + tousername + '\'' +
                ", clientType='" + clientType + '\'' +
                '}';
    }

    public String getClientType() {
        return clientType;
    }

    public void setClientType(String clientType) {
        this.clientType = clientType;
    }

    public void setLeadId(long leadId) {
        this.leadId = leadId;
    }

    public String getDatetiming() {
        return datetiming;
    }

    public void setDatetiming(String datetiming) {
        this.datetiming = datetiming;
    }

    public int getToId() {
        return toId;
    }

    public void setToId(int toId) {
        this.toId = toId;
    }

    public String getTousername() {
        return tousername;
    }

    public void setTousername(String tousername) {
        this.tousername = tousername;
    }
}
