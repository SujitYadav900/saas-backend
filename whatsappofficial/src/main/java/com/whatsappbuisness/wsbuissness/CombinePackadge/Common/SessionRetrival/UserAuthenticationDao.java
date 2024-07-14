package com.whatsappbuisness.wsbuissness.CombinePackadge.Common.SessionRetrival;
/*
 Author=Supreet Singh
 Date= 11/03/21 3:32 PM
*/

public class UserAuthenticationDao {
    @Override
    public String toString() {
        return "UserAuthenticationDao{" +
                "details=" + details +
                ", principal=" + principal +
                '}';
    }

    public DetailsUser getDetails() {
        return details;
    }

    public void setDetails(DetailsUser details) {
        this.details = details;
    }

    public UsermasterDao getPrincipal() {
        return principal;
    }

    public void setPrincipal(UsermasterDao principal) {
        this.principal = principal;
    }

    private DetailsUser details;
    private UsermasterDao principal;
}
