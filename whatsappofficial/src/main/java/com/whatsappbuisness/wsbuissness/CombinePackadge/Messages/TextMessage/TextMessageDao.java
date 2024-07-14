package com.whatsappbuisness.wsbuissness.CombinePackadge.Messages.TextMessage;
/*      
 Author=Supreet Singh
 Date= 04/02/21 4:54 PM
*/


import java.io.Serializable;

public class TextMessageDao implements Serializable {
    @Override
    public String toString() {
        return "TextMessageDao{" +
                "body='" + body + '\'' +
                '}';
    }
    public TextMessageDao() {
    }
    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public TextMessageDao(String body) {
        this.body = body;
    }

    private String body;

}

