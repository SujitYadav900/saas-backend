package com.whatsappbuisness.wsbuissness.CombinePackadge.Common;
/*
 Author=Supreet Singh
 Date= 19/02/21 10:44 PM
*/



public class AccountTransactionDao {


    private long id;
    private TransationType transationType;
    private Services service;

    private double crAmount;

    private double drAmount;
    private String refId;
    private int dateFilter;
    private int monthDateFilter;
    private VoucherType voucherType;
    private String date;
    private long accountId;

    @Override
    public String toString() {
        return "AccountTransactionDao{" +
                "id=" + id +
                ", transationType=" + transationType +
                ", service=" + service +
                ", crAmount=" + crAmount +
                ", drAmount=" + drAmount +
                ", refId='" + refId + '\'' +
                ", dateFilter=" + dateFilter +
                ", monthDateFilter=" + monthDateFilter +
                ", voucherType=" + voucherType +
                ", date='" + date + '\'' +
                ", accountId=" + accountId +
                ", amount=" + amount +
                '}';
    }

    public AccountTransactionDao() {

    }

    public AccountTransactionDao(TransationType transationType, Services service, double amount, String refId, VoucherType voucherType, long accountId) {
        this.transationType = transationType;
        this.service = service;
        if (getTransationType() == TransationType.CR) {
            this.setCrAmount(amount);
        } else {
            this.setDrAmount(amount);
        }
        this.amount=amount;
        this.refId = refId;
        this.voucherType = voucherType;
        this.accountId = accountId;
    }

    public double getCrAmount() {
        return crAmount;
    }

    public void setCrAmount(double crAmount) {
        this.crAmount = crAmount;
    }

    public double getDrAmount() {
        return drAmount;
    }

    public void setDrAmount(double drAmount) {
        this.drAmount = drAmount;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public TransationType getTransationType() {
        return transationType;
    }

    public void setTransationType(TransationType transationType) {
        this.transationType = transationType;
    }

    public Services getService() {
        return service;
    }

    public void setService(Services service) {
        this.service = service;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    private  double amount;

    public String getRefId() {
        return refId;
    }

    public void setRefId(String refId) {
        this.refId = refId;
    }

    public int getDateFilter() {
        return dateFilter;
    }

    public void setDateFilter(int dateFilter) {
        this.dateFilter = dateFilter;
    }

    public int getMonthDateFilter() {
        return monthDateFilter;
    }

    public void setMonthDateFilter(int monthDateFilter) {
        this.monthDateFilter = monthDateFilter;
    }

    public VoucherType getVoucherType() {
        return voucherType;
    }

    public void setVoucherType(VoucherType voucherType) {
        this.voucherType = voucherType;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public long getAccountId() {
        return accountId;
    }

    public void setAccountId(long accountId) {
        this.accountId = accountId;
    }



}
