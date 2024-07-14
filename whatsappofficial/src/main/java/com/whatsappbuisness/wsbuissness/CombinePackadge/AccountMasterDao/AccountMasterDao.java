package com.whatsappbuisness.wsbuissness.CombinePackadge.AccountMasterDao;

import org.springframework.stereotype.Component;

import javax.persistence.Id;
import java.util.List;

@Component
public class AccountMasterDao {
    @Id
    private long id;
    private String accountLogo;
    private String accountMinLogo;
    private String accountEmail;
    private String accountPhone;
    private String title;
    private String footer;
    private boolean active;
    private String accountName;
    private List<ServiceDao> services;
    private String createBy;
    private String updateBy;
    private String updateAt;
    private String createAt;
    private String password;
    private String clientType;
    private AccountsCreditType creditType;
    private ResellerReportDao resellerReportDao;
    private AccountTransactionReportDao accountTransactionReportDao;
    private boolean isReseller;
    private long parentId;

    public String getClientType() {
        return clientType;
    }

    public void setClientType(String clientType) {
        this.clientType = clientType;
    }

    public boolean isReseller() {
        return isReseller;
    }

    public void setReseller(boolean reseller) {
        isReseller = reseller;
    }

    public long getParentId() {
        return parentId;
    }

    public void setParentId(long parentId) {
        this.parentId = parentId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAccountLogo() {
        return accountLogo;
    }

    public void setAccountLogo(String accountLogo) {
        this.accountLogo = accountLogo;
    }

    public String getAccountMinLogo() {
        return accountMinLogo;
    }

    public void setAccountMinLogo(String accountMinLogo) {
        this.accountMinLogo = accountMinLogo;
    }

    public String getAccountEmail() {
        return accountEmail;
    }

    public void setAccountEmail(String accountEmail) {
        this.accountEmail = accountEmail;
    }

    public String getAccountPhone() {
        return accountPhone;
    }

    public void setAccountPhone(String accountPhone) {
        this.accountPhone = accountPhone;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFooter() {
        return footer;
    }

    public void setFooter(String footer) {
        this.footer = footer;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public List<ServiceDao> getServices() {
        return services;
    }

    public void setServices(List<ServiceDao> services) {
        this.services = services;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public String getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(String updateAt) {
        this.updateAt = updateAt;
    }

    public String getCreateAt() {
        return createAt;
    }

    public void setCreateAt(String createAt) {
        this.createAt = createAt;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public AccountsCreditType getCreditType() {
        return creditType;
    }

    public void setCreditType(AccountsCreditType creditType) {
        this.creditType = creditType;
    }

    public ResellerReportDao getResellerReportDao() {
        return resellerReportDao;
    }

    public void setResellerReportDao(ResellerReportDao resellerReportDao) {
        this.resellerReportDao = resellerReportDao;
    }

    public AccountTransactionReportDao getAccountTransactionReportDao() {
        return accountTransactionReportDao;
    }

    public void setAccountTransactionReportDao(AccountTransactionReportDao accountTransactionReportDao) {
        this.accountTransactionReportDao = accountTransactionReportDao;
    }

    @Override
    public String toString() {
        return "AccountMasterDao{" +
                "id=" + id +
                ", accountLogo='" + accountLogo + '\'' +
                ", accountMinLogo='" + accountMinLogo + '\'' +
                ", accountEmail='" + accountEmail + '\'' +
                ", accountPhone='" + accountPhone + '\'' +
                ", title='" + title + '\'' +
                ", footer='" + footer + '\'' +
                ", active=" + active +
                ", accountName='" + accountName + '\'' +
                ", services=" + services +
                ", createBy='" + createBy + '\'' +
                ", updateBy='" + updateBy + '\'' +
                ", updateAt='" + updateAt + '\'' +
                ", createAt='" + createAt + '\'' +
                ", password='" + password + '\'' +
                ", clientType='" + clientType + '\'' +
                ", creditType=" + creditType +
                ", resellerReportDao=" + resellerReportDao +
                ", accountTransactionReportDao=" + accountTransactionReportDao +
                ", isReseller=" + isReseller +
                ", parentId=" + parentId +
                '}';
    }
}
