package com.whatsappbuisness.wsbuissness.CombinePackadge.RabbitMqQue.Webhooks.Webhook;
/*      
 Author=Supreet Singh
 Date= 08/02/21 6:23 PM
*/

public class ContactsDao {
    @Override
    public String toString() {
        return "ContactsDao{" +
                "wa_id='" + wa_id + '\'' +
                ", profile=" + profile +
                '}';
    }

    public String getWa_id() {
        return wa_id;
    }

    public void setWa_id(String wa_id) {
        this.wa_id = wa_id;
    }

    public ProfileDao getProfile() {
        return profile;
    }

    public void setProfile(ProfileDao profile) {
        this.profile = profile;
    }

    private String wa_id;
    private ProfileDao profile;


}
