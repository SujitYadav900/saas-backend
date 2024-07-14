package com.whatsappbuisness.wsbuissness.CombinePackadge.Common.SessionRetrival;
/*
 Author=Supreet Singh
 Date= 11/03/21 3:46 PM
*/

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class SessionRetrievalDao  {
    @Override
    public String toString() {
        return "SessionRetrievalDao{" +
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
