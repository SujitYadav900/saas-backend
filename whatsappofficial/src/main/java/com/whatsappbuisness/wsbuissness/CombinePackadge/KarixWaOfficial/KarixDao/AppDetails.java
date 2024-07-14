package com.whatsappbuisness.wsbuissness.CombinePackadge.KarixWaOfficial.KarixDao;
/*
 Author=Ritu Redhu
 Date= 06/01/23 10:00 AM
*/
public class AppDetails {
    public String type;
    public String id;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "AppDetails{" +
                "type='" + type + '\'' +
                ", id='" + id + '\'' +
                '}';
    }
}
