package com.example.maxcrm.MaxCrm.Dao;

import javax.persistence.*;

@Entity
@Table(name = "Tbl_Reporting", uniqueConstraints=
@UniqueConstraint(columnNames={"userId", "reportTo"}),   indexes = {@Index(name = "UserIdIndex", columnList = "userId")})
public class ReportingDao {
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }



    @Override
    public String toString() {
        return "ReportingDao{" +
                "id=" + id +
                ", userId=" + userId +
                ", reportTo=" + reportTo +
                ", reportToName='" + reportToName + '\'' +
                '}';
    }

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;
    private int userId;
    private int reportTo;
    private String reportToName;

    public int getReportTo() {
        return reportTo;
    }

    public void setReportTo(int reportTo) {
        this.reportTo = reportTo;
    }

    public String getReportToName() {
        return reportToName;
    }

    public void setReportToName(String reportToName) {
        this.reportToName = reportToName;
    }


}
