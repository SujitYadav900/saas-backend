package com.whatsappbuisness.wsbuissness.CombinePackadge.ClientSignUp;

import com.whatsappbuisness.wsbuissness.CombinePackadge.AccountMasterDao.AccountMasterDao;
import com.whatsappbuisness.wsbuissness.CombinePackadge.Subscription.SubscriptionDao;
import org.springframework.data.mongodb.core.mapping.Document;
import javax.persistence.Id;
import javax.persistence.Transient;

@Document(collection = "ClientSignUpDao")
public class ClientSignUpDao {

    @Id
    private String id;
    private String clientType;
    private String createAt;
    private long dateFilter;
    private String createBy;
    private String updateDate;
    private boolean facebookVerifyStatus;
    private boolean whatsappVerifyStatus;
    private String clientNumber;
    private String clientName;
    private String country;
    private boolean verifyPhoneStatus;
    private String email;
    private String verifyEmailStatus;
    private String whatsappNumber;
    private boolean defaultSubscription;
    private boolean accountId;
    private String companyName;
    private String templateId;


    @Transient
    private SubscriptionDao subscriptionDao;
    @Transient
    private AccountMasterDao accountMasterDao;

    private String whatsappPhoneId;
    private String whatsappAppId;
    private String whatsappVerifiedToken;
    private String whatsappId;
    private String authToken;

    public String getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }

    public String getAuthToken() {
        return authToken;
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }

    public String getTemplateId() {
        return templateId;
    }

    public void setTemplateId(String templateId) {
        this.templateId = templateId;
    }

    public String getWhatsappPhoneId() {
        return whatsappPhoneId;
    }

    public void setWhatsappPhoneId(String whatsappPhoneId) {
        this.whatsappPhoneId = whatsappPhoneId;
    }

    public String getWhatsappAppId() {
        return whatsappAppId;
    }

    public void setWhatsappAppId(String whatsappAppId) {
        this.whatsappAppId = whatsappAppId;
    }

    public String getWhatsappVerifiedToken() {
        return whatsappVerifiedToken;
    }

    public void setWhatsappVerifiedToken(String whatsappVerifiedToken) {
        this.whatsappVerifiedToken = whatsappVerifiedToken;
    }

    public String getWhatsappId() {
        return whatsappId;
    }

    public void setWhatsappId(String whatsappId) {
        this.whatsappId = whatsappId;
    }


    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public boolean isAccountId() {
        return accountId;
    }

    public void setAccountId(boolean accountId) {
        this.accountId = accountId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public long getDateFilter() {
        return dateFilter;
    }

    public void setDateFilter(long dateFilter) {
        this.dateFilter = dateFilter;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getClientType() {
        return clientType;
    }

    public void setClientType(String clientType) {
        this.clientType = clientType;
    }

    public String getCreateAt() {
        return createAt;
    }

    public void setCreateAt(String createAt) {
        this.createAt = createAt;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public boolean isFacebookVerifyStatus() {
        return facebookVerifyStatus;
    }

    public void setFacebookVerifyStatus(boolean facebookVerifyStatus) {
        this.facebookVerifyStatus = facebookVerifyStatus;
    }

    public boolean isWhatsappVerifyStatus() {
        return whatsappVerifyStatus;
    }

    public void setWhatsappVerifyStatus(boolean whatsappVerifyStatus) {
        this.whatsappVerifyStatus = whatsappVerifyStatus;
    }

    public String getClientNumber() {
        return clientNumber;
    }

    public void setClientNumber(String clientNumber) {
        this.clientNumber = clientNumber;
    }

    public boolean isVerifyPhoneStatus() {
        return verifyPhoneStatus;
    }

    public void setVerifyPhoneStatus(boolean verifyPhoneStatus) {
        this.verifyPhoneStatus = verifyPhoneStatus;
    }

    public String getVerifyEmailStatus() {
        return verifyEmailStatus;
    }

    public void setVerifyEmailStatus(String verifyEmailStatus) {
        this.verifyEmailStatus = verifyEmailStatus;
    }

    public String getWhatsappNumber() {
        return whatsappNumber;
    }

    public void setWhatsappNumber(String whatsappNumber) {
        this.whatsappNumber = whatsappNumber;
    }

    public boolean isDefaultSubscription() {
        return defaultSubscription;
    }

    public void setDefaultSubscription(boolean defaultSubscription) {
        this.defaultSubscription = defaultSubscription;
    }

    public SubscriptionDao getSubscriptionDao() {
        return subscriptionDao;
    }


    public void setSubscriptionDao(SubscriptionDao subscriptionDao) {
        this.subscriptionDao = subscriptionDao;
    }

    public AccountMasterDao getAccountMasterDao() {
        return accountMasterDao;
    }

    public void setAccountMasterDao(AccountMasterDao accountMasterDao) {
        this.accountMasterDao = accountMasterDao;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "ClientSignUpDao{" +
                "id='" + id + '\'' +
                ", clientType='" + clientType + '\'' +
                ", createAt='" + createAt + '\'' +
                ", dateFilter=" + dateFilter +
                ", createBy='" + createBy + '\'' +
                ", updateDate='" + updateDate + '\'' +
                ", facebookVerifyStatus=" + facebookVerifyStatus +
                ", whatsappVerifyStatus=" + whatsappVerifyStatus +
                ", clientNumber='" + clientNumber + '\'' +
                ", clientName='" + clientName + '\'' +
                ", country='" + country + '\'' +
                ", verifyPhoneStatus=" + verifyPhoneStatus +
                ", email='" + email + '\'' +
                ", verifyEmailStatus='" + verifyEmailStatus + '\'' +
                ", whatsappNumber='" + whatsappNumber + '\'' +
                ", defaultSubscription=" + defaultSubscription +
                ", accountId=" + accountId +
                ", companyName='" + companyName + '\'' +
                ", templateId='" + templateId + '\'' +
                ", subscriptionDao=" + subscriptionDao +
                ", accountMasterDao=" + accountMasterDao +
                ", whatsappPhoneId='" + whatsappPhoneId + '\'' +
                ", whatsappAppId='" + whatsappAppId + '\'' +
                ", whatsappVerifiedToken='" + whatsappVerifiedToken + '\'' +
                ", whatsappId='" + whatsappId + '\'' +
                ", authToken='" + authToken + '\'' +
                '}';
    }
}
