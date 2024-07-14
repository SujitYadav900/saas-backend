package com.whatsappbuisness.wsbuissness.CombinePackadge.Messages.ContactsMessageDao;
/*      
 Author=Supreet Singh
 Date= 09/02/21 4:58 PM
*/

import java.io.Serializable;

public class EmailsContactsDao implements Serializable {
    private String email;
    private String type;

    @Override
    public String toString() {
        return "EmailsContactsDao{" +
                "email='" + email + '\'' +
                ", type='" + type + '\'' +
                '}';
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
