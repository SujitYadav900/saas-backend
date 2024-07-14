package com.whatsappbuisness.wsbuissness.CombinePackadge.KarixWaOfficial.KarixDao;
/*
 Author=Ritu Redhu
 Date= 06/01/23 10:00 AM
*/
public class QuickReplies {

    public String index;

    public String payload;

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public String getPayload() {
        return payload;
    }

    public void setPayload(String payload) {
        this.payload = payload;
    }

    @Override
    public String toString() {
        return "QuickReplies{" +
                "index='" + index + '\'' +
                ", payload='" + payload + '\'' +
                '}';
    }
}
