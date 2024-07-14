package com.whatsappbuisness.wsbuissness.CombinePackadge.KarixWaOfficial.KarixDao;
/*
 Author=Ritu Redhu
 Date= 06/01/23 10:00 AM
*/
public class Preferences {

    private String webHookDNId;

    public String getWebHookDNId() {
        return webHookDNId;
    }

    public void setWebHookDNId(String webHookDNId) {
        this.webHookDNId = webHookDNId;
    }

    @Override
    public String toString() {
        return "Preferences{" +
                "webHookDNId='" + webHookDNId + '\'' +
                '}';
    }
}
