package com.whatsappbuisness.wsbuissness.CombinePackadge.Messages.ButtonMessage;
/*      
 Author=Supreet Singh
 Date= 09/02/21 4:20 PM
*/

import java.io.Serializable;

public class ButtonMessageDao implements Serializable {
    @Override
    public String toString() {
        return "ButtonWebhookDao{" +
                "payload='" + payload + '\'' +
                ", text='" + text + '\'' +
                '}';
    }

    public String getPayload() {
        return payload;
    }

    public void setPayload(String payload) {
        this.payload = payload;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    private String payload;
    private String text;

}
