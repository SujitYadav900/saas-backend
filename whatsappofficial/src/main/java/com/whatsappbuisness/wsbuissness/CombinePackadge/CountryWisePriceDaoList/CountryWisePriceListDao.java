package com.whatsappbuisness.wsbuissness.CombinePackadge.CountryWisePriceDaoList;

import javax.persistence.*;

@Entity
@Table(name = "Tbl_CountryWisePriceList")
public class CountryWisePriceListDao {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private double authenicationRates;
    private double bsnsInitiatedRates;
    private String countryCode;
    private double marketingRates;
    private double userInitiatedRates;
    private double utilityRates;
    private String country;
    private String numberCode;


    public String getNumberCode() {
        return numberCode;
    }

    public void setNumberCode(String numberCode) {
        this.numberCode = numberCode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public double getAuthenicationRates() {
        return authenicationRates;
    }

    public void setAuthenicationRates(double authenicationRates) {
        this.authenicationRates = authenicationRates;
    }

    public double getBsnsInitiatedRates() {
        return bsnsInitiatedRates;
    }

    public void setBsnsInitiatedRates(double bsnsInitiatedRates) {
        this.bsnsInitiatedRates = bsnsInitiatedRates;
    }

    public double getMarketingRates() {
        return marketingRates;
    }

    public void setMarketingRates(double marketingRates) {
        this.marketingRates = marketingRates;
    }

    public double getUserInitiatedRates() {
        return userInitiatedRates;
    }

    public void setUserInitiatedRates(double userInitiatedRates) {
        this.userInitiatedRates = userInitiatedRates;
    }

    public double getUtilityRates() {
        return utilityRates;
    }

    public void setUtilityRates(double utilityRates) {
        this.utilityRates = utilityRates;
    }

    @Override
    public String toString() {
        return "CountryWisePriceListDao{" +
                "id=" + id +
                ", authenicationRates=" + authenicationRates +
                ", bsnsInitiatedRates=" + bsnsInitiatedRates +
                ", countryCode='" + countryCode + '\'' +
                ", marketingRates=" + marketingRates +
                ", userInitiatedRates=" + userInitiatedRates +
                ", utilityRates=" + utilityRates +
                ", country='" + country + '\'' +
                ", numberCode='" + numberCode + '\'' +
                '}';
    }
}
