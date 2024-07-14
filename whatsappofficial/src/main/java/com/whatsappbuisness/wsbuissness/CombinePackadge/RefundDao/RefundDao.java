package com.whatsappbuisness.wsbuissness.CombinePackadge.RefundDao;
/*
 Author=Supreet Singh
 Date= 09/03/21 4:47 PM
*/

import com.whatsappbuisness.wsbuissness.CombinePackadge.Common.VoucherType;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Tbl_Refund_Day_Wise" )
public class RefundDao {
    public long getId() {
        return id;
    }

    @Override
    public String toString() {
        return "RefundDao{" +
                "id=" + id +
                ", accountId=" + accountId +
                ", date='" + date + '\'' +
                ", dateFilter=" + dateFilter +
                ", session=" + session +
                ", templates=" + templates +
                '}';
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getAccountId() {
        return accountId;
    }

    public void setAccountId(long accountId) {
        this.accountId = accountId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getDateFilter() {
        return dateFilter;
    }

    public void setDateFilter(int dateFilter) {
        this.dateFilter = dateFilter;
    }

    public double getSession() {
        return session;
    }

    public void setSession(double session) {
        this.session = session;
    }

    public double getTemplates() {
        return templates;
    }

    public void setTemplates(double templates) {
        this.templates = templates;
    }

    @Id
    private long id;
    private long accountId;
    private String date;
    private int dateFilter;
    private double session;
    private double templates;
}
