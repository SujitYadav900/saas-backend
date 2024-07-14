package com.whatsappbuisness.wsbuissness.CombinePackadge.Messages.ContactsMessageDao;
/*      
 Author=Supreet Singh
 Date= 09/02/21 4:55 PM
*/

import java.io.Serializable;

public class AddressContactDao implements Serializable {
    @Override
    public String toString() {
        return "AddressContactDao{" +
                "city='" + city + '\'' +
                ", country='" + country + '\'' +
                ", countryCode='" + countryCode + '\'' +
                ", state='" + state + '\'' +
                ", street='" + street + '\'' +
                ", zip='" + zip + '\'' +
                '}';
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    private String city;
    private String country;
    private String countryCode;
    private String state;
    private String street;
    private String zip;

}
