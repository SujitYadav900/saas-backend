package com.whatsappbuisness.wsbuissness.CombinePackadge.whatsappCloudAPI.CloudAPIWebhook;

public class Contacts {
    private Profile profile;
    private String wa_id;

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    public String getWa_id() {
        return wa_id;
    }

    public void setWa_id(String wa_id) {
        this.wa_id = wa_id;
    }

    @Override
    public String toString() {
        return "Contacts{" +
                "profile=" + profile +
                ", wa_id='" + wa_id + '\'' +
                '}';
    }
}
