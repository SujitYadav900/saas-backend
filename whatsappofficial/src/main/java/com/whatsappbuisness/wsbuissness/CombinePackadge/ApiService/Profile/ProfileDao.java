package com.whatsappbuisness.wsbuissness.CombinePackadge.ApiService.Profile;
/*      
 Author=Supreet Singh
 Date= 11/02/21 10:04 PM
*/

import com.google.gson.Gson;

public class ProfileDao {
    @Override
    public String toString() {
        return "ProfileDao{" +
                "text='" + text + '\'' +
                '}';
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    private String text;
    public String toJson()
    {
        return new Gson().toJson(this);
    }
}
