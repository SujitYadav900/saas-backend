package com.whatsappbuisness.wsbuissness.CombinePackadge.CountryWisePrice;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Objects;

@Entity
@IdClass(CountryWisePricePk.class)
@Table(name = "Tbl_Country_Rates")
public class CountryWisePriceDao implements Serializable {
    public CountryWisePriceDao() {
    }

    public CountryWisePriceDao(String countryCode, long accountId, double userInitiatedRates, double bsnsInitiatedRates, double marketingRates, double authenicationRates, double utilityRates, boolean active) {
        this.countryCode = countryCode;
        this.accountId = accountId;
        this.userInitiatedRates = userInitiatedRates;
        this.bsnsInitiatedRates = bsnsInitiatedRates;
        this.marketingRates = marketingRates;
        this.authenicationRates = authenicationRates;
        this.utilityRates = utilityRates;
        this.active = active;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public long getAccountId() {
        return accountId;
    }

    public void setAccountId(long accountId) {
        this.accountId = accountId;
    }



    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Id
    private String countryCode;
    @Id
    private long accountId;

    @Override
    public String toString() {
        return "CountryWisePriceDao{" +
                "countryCode='" + countryCode + '\'' +
                ", accountId=" + accountId +
                ", userInitiatedRates=" + userInitiatedRates +
                ", bsnsInitiatedRates=" + bsnsInitiatedRates +
                ", marketingRates=" + marketingRates +
                ", authenicationRates=" + authenicationRates +
                ", utilityRates=" + utilityRates +
                ", active=" + active +
                '}';
    }

    public double getUserInitiatedRates() {
        return userInitiatedRates;
    }

    public void setUserInitiatedRates(double userInitiatedRates) {
        this.userInitiatedRates = userInitiatedRates;
    }

    public double getBsnsInitiatedRates() {
        return bsnsInitiatedRates;
    }

    public void setBsnsInitiatedRates(double bsnsInitiatedRates) {
        this.bsnsInitiatedRates = bsnsInitiatedRates;
    }

    private double userInitiatedRates;
    private double bsnsInitiatedRates;
    private double marketingRates;
    private double authenicationRates;
    private double utilityRates;
    private boolean active;

    public double getMarketingRates() {
        return marketingRates;
    }

    public void setMarketingRates(double marketingRates) {
        this.marketingRates = marketingRates;
    }

    public double getAuthenicationRates() {
        return authenicationRates;
    }

    public void setAuthenicationRates(double authenicationRates) {
        this.authenicationRates = authenicationRates;
    }

    public double getUtilityRates() {
        return utilityRates;
    }

    public void setUtilityRates(double utilityRates) {
        this.utilityRates = utilityRates;
    }

    @Override
    public boolean equals(Object o) {

        CountryWisePriceDao that = (CountryWisePriceDao) o;
       return (that.accountId==accountId&&that.countryCode.equalsIgnoreCase(countryCode));
    }

    @Override
    public int hashCode() {
        return Objects.hash(countryCode, accountId);
    }
}
