package com.whatsappbuisness.wsbuissness.CombinePackadge.AccountMasterDao;

public class ServiceDao {

    private String serviceName;
    private AccountType accountMode;
    private ServiceType serviceType;
    private boolean active;
    private WhatsappCreditDao whatsappCreditDao;

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public AccountType getAccountMode() {
        return accountMode;
    }

    public void setAccountMode(AccountType accountMode) {
        this.accountMode = accountMode;
    }

    public ServiceType getServiceType() {
        return serviceType;
    }

    public void setServiceType(ServiceType serviceType) {
        this.serviceType = serviceType;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public WhatsappCreditDao getWhatsappCreditDao() {
        return whatsappCreditDao;
    }

    public void setWhatsappCreditDao(WhatsappCreditDao whatsappCreditDao) {
        this.whatsappCreditDao = whatsappCreditDao;
    }

    public ServiceDao() {
    }

    public ServiceDao(String serviceName, AccountType accountMode, ServiceType serviceType, boolean active, WhatsappCreditDao whatsappCreditDao) {
        this.serviceName = serviceName;
        this.accountMode = accountMode;
        this.serviceType = serviceType;
        this.active = active;
        this.whatsappCreditDao = whatsappCreditDao;
    }

    @Override
    public String toString() {
        return "ServiceDao{" +
                "serviceName='" + serviceName + '\'' +
                ", accountMode=" + accountMode +
                ", serviceType=" + serviceType +
                ", active=" + active +
                ", whatsappCreditDao=" + whatsappCreditDao +
                '}';
    }
}
