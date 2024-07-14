package com.whatsappbuisness.wsbuissness.CombinePackadge.PaymentGatway;

import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;

@Document( collection = "PaymentDetailsDao")
public class PaymentDetailsDao {
    @Id
    private String id;
    private String name;
    private String keyId;
    private String keySecret;
    private double amount;
    private String currency;
    private long accountId;
    private String createDate;
    private long createDateFilter;
    private String orderId;
    private String paymentId;
    private PaymentGatway paymentGatway;
    private String signature;
    private PaymentGatwayStatus paymentGatwayStatus;


    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public PaymentGatwayStatus getPaymentGatwayStatus() {
        return paymentGatwayStatus;
    }

    public void setPaymentGatwayStatus(PaymentGatwayStatus paymentGatwayStatus) {
        this.paymentGatwayStatus = paymentGatwayStatus;
    }

    public long getAccountId() {
        return accountId;
    }

    public void setAccountId(long accountId) {
        this.accountId = accountId;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public long getCreateDateFilter() {
        return createDateFilter;
    }

    public void setCreateDateFilter(long createDateFilter) {
        this.createDateFilter = createDateFilter;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }

    public PaymentGatway getPaymentGatway() {
        return paymentGatway;
    }

    public void setPaymentGatway(PaymentGatway paymentGatway) {
        this.paymentGatway = paymentGatway;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getKeyId() {
        return keyId;
    }

    public void setKeyId(String keyId) {
        this.keyId = keyId;
    }

    public String getKeySecret() {
        return keySecret;
    }

    public void setKeySecret(String keySecret) {
        this.keySecret = keySecret;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    @Override
    public String toString() {
        return "PaymentDetailsDao{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", keyId='" + keyId + '\'' +
                ", keySecret='" + keySecret + '\'' +
                ", amount=" + amount +
                ", currency='" + currency + '\'' +
                ", accountId=" + accountId +
                ", createDate='" + createDate + '\'' +
                ", createDateFilter=" + createDateFilter +
                ", orderId='" + orderId + '\'' +
                ", paymentId='" + paymentId + '\'' +
                ", paymentGatway=" + paymentGatway +
                ", signature='" + signature + '\'' +
                ", paymentGatwayStatus=" + paymentGatwayStatus +
                '}';
    }
}
