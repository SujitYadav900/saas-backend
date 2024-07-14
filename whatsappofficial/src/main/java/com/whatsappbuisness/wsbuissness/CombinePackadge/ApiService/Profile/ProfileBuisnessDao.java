package com.whatsappbuisness.wsbuissness.CombinePackadge.ApiService.Profile;
/*      
 Author=Supreet Singh
 Date= 11/02/21 10:27 PM
*/

import com.google.gson.Gson;

public class ProfileBuisnessDao {

    @Override
    public String toString() {
        return "ProfileBuisnessDao{" +
                "address='" + address + '\'' +
                ", description='" + description + '\'' +
                ", email='" + email + '\'' +
                ", vertical='" + vertical + '\'' +
                ", websites='" + websites + '\'' +
                '}';
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getVertical() {
        return vertical;
    }

    public void setVertical(String vertical) {
        this.vertical = vertical;
    }

    public String getWebsites() {
        return websites;
    }

    public void setWebsites(String websites) {
        this.websites = websites;
    }

    //    {
//        "address": "<Business Profile Address>",
//            "description": "<Business Profile Description>",
//            "email": "<Business Profile Email>",
//            "vertical": "<Business Profile Vertical>",
//            "websites": ["https://www.whatsapp.com", "https://www.facebook.com"]
//    }
    private String address;
    private String description;
    private String email;
    private String vertical;
    private String websites;
    public String toJson()
    {
        return new Gson().toJson(this);
    }
}
