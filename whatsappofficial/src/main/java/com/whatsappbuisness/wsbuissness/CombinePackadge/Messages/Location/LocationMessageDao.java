package com.whatsappbuisness.wsbuissness.CombinePackadge.Messages.Location;
/*      
 Author=Supreet Singh
 Date= 09/02/21 3:55 PM
*/

import java.io.Serializable;

public class LocationMessageDao implements Serializable {

    @Override
    public String toString() {
        return "LocationWebhookDao{" +
                "address='" + address + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", name='" + name + '\'' +
                ", url='" + url + '\'' +
                '}';
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    /*     "address":"Main Street Beach, Santa Cruz, CA",
                 "latitude":38.9806263495,
                 "longitude":-131.9428612257,
                 "name":"Main Street Beach",
                 "url":"https://foursquare.com/v/4d7031d35b5df7744"},*/
    private String address;
    private double latitude;
    private double longitude;
    private String name;
    private String url;
}
