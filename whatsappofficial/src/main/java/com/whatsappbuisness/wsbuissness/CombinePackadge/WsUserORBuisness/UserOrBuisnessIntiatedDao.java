package com.whatsappbuisness.wsbuissness.CombinePackadge.WsUserORBuisness;

import com.whatsappbuisness.wsbuissness.CombinePackadge.CountryWisePrice.CountryWiseRateRetDao;
import com.whatsappbuisness.wsbuissness.CombinePackadge.Features.DirectChatFeature.UserAsignDao.UserCompositeKeyDao;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "Tbl_Bs_Usr_Session")
public class UserOrBuisnessIntiatedDao  implements Serializable {

    public UserOrBuisnessIntiatedDao() {
    }

    public UserOrBuisnessIntiatedDao(String countryCode, String dst, String sessionStartTime, String sessionEndTime, int dateFilter, long expiryLong, String category, String refId, double deduction, long accountId) {
        this.countryCode = countryCode;
        this.dst = dst;
        this.sessionStartTime = sessionStartTime;
        this.sessionEndTime = sessionEndTime;
        this.dateFilter = dateFilter;
        this.expiryLong = expiryLong;
        this.category = category;
        this.refId = refId;
        this.deduction = deduction;
        this.accountId = accountId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getDst() {
        return dst;
    }

    public void setDst(String dst) {
        this.dst = dst;
    }

    public String getSessionStartTime() {
        return sessionStartTime;
    }

    public void setSessionStartTime(String sessionStartTime) {
        this.sessionStartTime = sessionStartTime;
    }

    public String getSessionEndTime() {
        return sessionEndTime;
    }

    public void setSessionEndTime(String sessionEndTime) {
        this.sessionEndTime = sessionEndTime;
    }

    public int getUsrMessageCount() {
        return usrMessageCount;
    }

    public void setUsrMessageCount(int usrMessageCount) {
        this.usrMessageCount = usrMessageCount;
    }

    public int getBsnsMessageCount() {
        return bsnsMessageCount;
    }

    public void setBsnsMessageCount(int bsnsMessageCount) {
        this.bsnsMessageCount = bsnsMessageCount;
    }

    public int getDateFilter() {
        return dateFilter;
    }

    public void setDateFilter(int dateFilter) {
        this.dateFilter = dateFilter;
    }

    public long getExpiryLong() {
        return expiryLong;
    }

    public void setExpiryLong(long expiryLong) {
        this.expiryLong = expiryLong;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String countryCode;
    private String dst;
    private String sessionStartTime;
    private String sessionEndTime;
    private int usrMessageCount;
    private int bsnsMessageCount;
    private int marketingMessageCount;
    private int utiltityMessageCount;
    private int authenticationMessageCount;


    private int dateFilter;
    private long expiryLong;
    private String category;

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getRefId() {
        return refId;
    }

    public void setRefId(String refId) {
        this.refId = refId;
    }

    private String refId;


    public CountryWiseRateRetDao getCountryWiseRateRetDao() {
        return countryWiseRateRetDao;
    }

    public void setCountryWiseRateRetDao(CountryWiseRateRetDao countryWiseRateRetDao) {
        this.countryWiseRateRetDao = countryWiseRateRetDao;
    }

    @Transient
    private CountryWiseRateRetDao countryWiseRateRetDao;

    public double getDeduction() {
        return deduction;
    }

    public void setDeduction(double deduction) {
        this.deduction = deduction;
    }

    private double deduction;

    public SessionType getSessionType() {
        return sessionType;
    }

    public void setSessionType(SessionType sessionType) {
        this.sessionType = sessionType;
    }

    private SessionType sessionType;

    public boolean isNew() {
        return isNew;
    }

    public void setNew(boolean aNew) {
        isNew = aNew;
    }


    public int getMarketingMessageCount() {
        return marketingMessageCount;
    }

    public void setMarketingMessageCount(int marketingMessageCount) {
        this.marketingMessageCount = marketingMessageCount;
    }

    public int getUtiltityMessageCount() {
        return utiltityMessageCount;
    }

    public void setUtiltityMessageCount(int utiltityMessageCount) {
        this.utiltityMessageCount = utiltityMessageCount;
    }

    public int getAuthenticationMessageCount() {
        return authenticationMessageCount;
    }

    public void setAuthenticationMessageCount(int authenticationMessageCount) {
        this.authenticationMessageCount = authenticationMessageCount;
    }

    @Transient
    private boolean isNew;

    @Override
    public String toString() {
        return "UserOrBuisnessIntiatedDao{" +
                "id=" + id +
                ", countryCode='" + countryCode + '\'' +
                ", dst='" + dst + '\'' +
                ", sessionStartTime='" + sessionStartTime + '\'' +
                ", sessionEndTime='" + sessionEndTime + '\'' +
                ", usrMessageCount=" + usrMessageCount +
                ", bsnsMessageCount=" + bsnsMessageCount +
                ", marketingMessageCount=" + marketingMessageCount +
                ", utiltityMessageCount=" + utiltityMessageCount +
                ", authenticationMessageCount=" + authenticationMessageCount +
                ", dateFilter=" + dateFilter +
                ", expiryLong=" + expiryLong +
                ", category='" + category + '\'' +
                ", refId='" + refId + '\'' +
                ", countryWiseRateRetDao=" + countryWiseRateRetDao +
                ", deduction=" + deduction +
                ", sessionType=" + sessionType +
                ", isNew=" + isNew +
                ", accountId=" + accountId +
                '}';
    }

    public long getAccountId() {
        return accountId;
    }

    public void setAccountId(long accountId) {
        this.accountId = accountId;
    }

    private long accountId;



}
