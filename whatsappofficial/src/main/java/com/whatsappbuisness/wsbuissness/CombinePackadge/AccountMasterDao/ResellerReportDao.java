package com.whatsappbuisness.wsbuissness.CombinePackadge.AccountMasterDao;


public class ResellerReportDao {
    private long accountId;
    private  long totallSentMsg;
    private  long totalRemMsg;
    private  long totalMsg;
    private boolean connected;
    private ServiceType serviceType;
    private String expiry;

    public long getAccountId() {
        return accountId;
    }

    public void setAccountId(long accountId) {
        this.accountId = accountId;
    }

    public long getTotallSentMsg() {
        return totallSentMsg;
    }

    public void setTotallSentMsg(long totallSentMsg) {
        this.totallSentMsg = totallSentMsg;
    }

    public long getTotalRemMsg() {
        return totalRemMsg;
    }

    public void setTotalRemMsg(long totalRemMsg) {
        this.totalRemMsg = totalRemMsg;
    }

    public long getTotalMsg() {
        return totalMsg;
    }

    public void setTotalMsg(long totalMsg) {
        this.totalMsg = totalMsg;
    }

    public boolean isConnected() {
        return connected;
    }

    public void setConnected(boolean connected) {
        this.connected = connected;
    }

    public ServiceType getServiceType() {
        return serviceType;
    }

    public void setServiceType(ServiceType serviceType) {
        this.serviceType = serviceType;
    }

    public String getExpiry() {
        return expiry;
    }

    public void setExpiry(String expiry) {
        this.expiry = expiry;
    }

    @Override
    public String toString() {
        return "ResellerReportDao{" +
                "accountId=" + accountId +
                ", totallSentMsg=" + totallSentMsg +
                ", totalRemMsg=" + totalRemMsg +
                ", totalMsg=" + totalMsg +
                ", connected=" + connected +
                ", serviceType=" + serviceType +
                ", expiry='" + expiry + '\'' +
                '}';
    }
}
