package com.whatsappbuisness.wsbuissness.CombinePackadge.EventsWebhooks;

public class IncomingContactDao {
    @Override
    public String toString() {
        return "IncomingContactDao{" +
                "profile=" + profile +
                ", wa_id='" + wa_id + '\'' +
                '}';
    }

    public ProfileDao getProfile() {
        return profile;
    }

    public void setProfile(ProfileDao profile) {
        this.profile = profile;
    }

    public String getWa_id() {
        return wa_id;
    }

    public void setWa_id(String wa_id) {
        this.wa_id = wa_id;
    }

    private ProfileDao profile;
    private String wa_id;

}
