package com.whatsappbuisness.wsbuissness.CombinePackadge.Common.SessionRetrival;
/*
 Author=Supreet Singh
 Date= 11/03/21 3:42 PM
*/

public class DetailsUser {
    @Override
    public String toString() {
        return "DetailsUser{" +
                "userAuthentication=" + userAuthentication +
                '}';
    }

    public UserAuthenticationDao getUserAuthentication() {
        return userAuthentication;
    }

    public void setUserAuthentication(UserAuthenticationDao userAuthentication) {
        this.userAuthentication = userAuthentication;
    }

    private UserAuthenticationDao userAuthentication;

}
