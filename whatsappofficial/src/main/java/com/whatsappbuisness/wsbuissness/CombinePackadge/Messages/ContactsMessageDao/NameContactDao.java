package com.whatsappbuisness.wsbuissness.CombinePackadge.Messages.ContactsMessageDao;
/*      
 Author=Supreet Singh
 Date= 09/02/21 4:59 PM
*/

import java.io.Serializable;

public class NameContactDao implements Serializable {
    private String first_name;
    private String formatted_name;
    private String last_name;

    @Override
    public String toString() {
        return "NameContactDao{" +
                "first_name='" + first_name + '\'' +
                ", formatted_name='" + formatted_name + '\'' +
                ", last_name='" + last_name + '\'' +
                '}';
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getFormatted_name() {
        return formatted_name;
    }

    public void setFormatted_name(String formatted_name) {
        this.formatted_name = formatted_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }
}
