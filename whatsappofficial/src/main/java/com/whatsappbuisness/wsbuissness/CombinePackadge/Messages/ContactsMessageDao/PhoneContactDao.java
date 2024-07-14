package com.whatsappbuisness.wsbuissness.CombinePackadge.Messages.ContactsMessageDao;
/*      
 Author=Supreet Singh
 Date= 09/02/21 5:01 PM
*/

import java.io.Serializable;

public class PhoneContactDao implements Serializable {
    @Override
    public String toString() {
        return "PhoneContactDao{" +
                "phone='" + phone + '\'' +
                ", type='" + type + '\'' +
                '}';
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    private String phone;
private String type;
}
