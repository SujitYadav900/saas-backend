package com.whatsappbuisness.wsbuissness.CombinePackadge.SubscriptionPackage;

import com.whatsappbuisness.wsbuissness.CombinePackadge.WsUserORBuisness.UserOrBuisnessIntiatedDao;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Document(collection = "SubscriptionPackageDao")
public class SubScriptionPackageDao {

   @Id
    private String id;
    private String createDate;
    private long createDateFilter;
    private String updateDate;
    private long accountId;

    private double rentalAmount;
    private double rechargeAmount;
    private int roleId;
    private ExpiryDateType expireyDateType;
    private PlanType planType;

    private SubscriptionPackageType subscriptionPackageType;
    private String remarks;
    private boolean status;
    private double marketingRates;
    private double utilityRates;
    private double authenticationRates;
    private double userInitiatedRates;
    private String country;

    private String accessToken;

    private double razorPayAmount;
    private  double totalAMount;
    private double gst;
    private double amountWithGST;

    public double getAmountWithGST() {
        return amountWithGST;
    }

    public void setAmountWithGST(double amountWithGST) {
        this.amountWithGST = amountWithGST;
    }

    public double getRazorPayAmount() {
        return razorPayAmount;
    }

    public void setRazorPayAmount(double razorPayAmount) {
        this.razorPayAmount = razorPayAmount;
    }

    public double getTotalAMount() {
        return totalAMount;
    }

    public void setTotalAMount(double totalAMount) {
        this.totalAMount = totalAMount;
    }

    public double getGst() {
        return gst;
    }

    public void setGst(double gst) {
        this.gst = gst;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public double getMarketingRates() {
        return marketingRates;
    }

    public void setMarketingRates(double marketingRates) {
        this.marketingRates = marketingRates;
    }

    public double getUtilityRates() {
        return utilityRates;
    }

    public void setUtilityRates(double utilityRates) {
        this.utilityRates = utilityRates;
    }

    public double getAuthenticationRates() {
        return authenticationRates;
    }

    public void setAuthenticationRates(double authenticationRates) {
        this.authenticationRates = authenticationRates;
    }

    public double getUserInitiatedRates() {
        return userInitiatedRates;
    }

    public void setUserInitiatedRates(double userInitiatedRates) {
        this.userInitiatedRates = userInitiatedRates;
    }

    public double getRentalAmount() {
        return rentalAmount;
    }

    public void setRentalAmount(double rentalAmount) {
        this.rentalAmount = rentalAmount;
    }

    public SubscriptionPackageType getSubscriptionPackageType() {
        return subscriptionPackageType;
    }

    public void setSubscriptionPackageType(SubscriptionPackageType subscriptionPackageType) {
        this.subscriptionPackageType = subscriptionPackageType;
    }

    public long getCreateDateFilter() {
        return createDateFilter;
    }

    public void setCreateDateFilter(long createDateFilter) {
        this.createDateFilter = createDateFilter;
    }

    public String getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }

    public long getAccountId() {
        return accountId;
    }

    public void setAccountId(long accountId) {
        this.accountId = accountId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public double getRechargeAmount() {
        return rechargeAmount;
    }

    public void setRechargeAmount(double rechargeAmount) {
        this.rechargeAmount = rechargeAmount;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public ExpiryDateType getExpireyDateType() {
        return expireyDateType;
    }

    public void setExpireyDateType(ExpiryDateType expireyDateType) {
        this.expireyDateType = expireyDateType;
    }

    public PlanType getPlanType() {
        return planType;
    }

    public void setPlanType(PlanType planType) {
        this.planType = planType;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    @Override
    public String toString() {
        return "SubScriptionPackageDao{" +
                "id='" + id + '\'' +
                ", createDate='" + createDate + '\'' +
                ", createDateFilter=" + createDateFilter +
                ", updateDate='" + updateDate + '\'' +
                ", accountId=" + accountId +
                ", rentalAmount=" + rentalAmount +
                ", rechargeAmount=" + rechargeAmount +
                ", roleId=" + roleId +
                ", expireyDateType=" + expireyDateType +
                ", planType=" + planType +
                ", subscriptionPackageType=" + subscriptionPackageType +
                ", remarks='" + remarks + '\'' +
                ", status=" + status +
                ", marketingRates=" + marketingRates +
                ", utilityRates=" + utilityRates +
                ", authenticationRates=" + authenticationRates +
                ", userInitiatedRates=" + userInitiatedRates +
                ", country='" + country + '\'' +
                ", accessToken='" + accessToken + '\'' +
                ", razorPayAmount=" + razorPayAmount +
                ", totalAMount=" + totalAMount +
                ", gst=" + gst +
                ", amountWithGST=" + amountWithGST +
                '}';
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }


}
