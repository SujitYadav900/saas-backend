package com.whatsappbuisness.wsbuissness.CombinePackadge.AccountMasterDao;

import org.springframework.stereotype.Component;

@Component
public class RechargeDao {
    private String transactionId;
    private double amount;
    private String createBy;
    private String createAt;
    private RechareType rechareType;
    private String descri;
    private long accountId;

    private double totalCredit;
    private double totalDebit;
    private long dateFilter;

    private Services service;

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public String getCreateAt() {
        return createAt;
    }

    public void setCreateAt(String createAt) {
        this.createAt = createAt;
    }

    public RechareType getRechareType() {
        return rechareType;
    }

    public void setRechareType(RechareType rechareType) {
        this.rechareType = rechareType;
    }

    public String getDescri() {
        return descri;
    }

    public void setDescri(String descri) {
        this.descri = descri;
    }

    public long getAccountId() {
        return accountId;
    }

    public void setAccountId(long accountId) {
        this.accountId = accountId;
    }

    public double getTotalCredit() {
        return totalCredit;
    }

    public void setTotalCredit(double totalCredit) {
        this.totalCredit = totalCredit;
    }

    public double getTotalDebit() {
        return totalDebit;
    }

    public void setTotalDebit(double totalDebit) {
        this.totalDebit = totalDebit;
    }

    public long getDateFilter() {
        return dateFilter;
    }

    public void setDateFilter(long dateFilter) {
        this.dateFilter = dateFilter;
    }

    public Services getService() {
        return service;
    }

    public void setService(Services service) {
        this.service = service;
    }

    public RechargeDao() {
    }

    public RechargeDao(double amount, String createBy, String createAt, RechareType rechareType, String descri, long accountId, double totalCredit, double totalDebit, long dateFilter, Services service) {
        this.amount = amount;
        this.createBy = createBy;
        this.createAt = createAt;
        this.rechareType = rechareType;
        this.descri = descri;
        this.accountId = accountId;
        this.totalCredit = totalCredit;
        this.totalDebit = totalDebit;
        this.dateFilter = dateFilter;
        this.service = service;
    }
}
