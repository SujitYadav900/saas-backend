package com.whatsappbuisness.wsbuissness.CombinePackadge.EventsWebhooks;

public class ProfileDao {
    @Override
    public String toString() {
        return "ProfileDao{" +
                "name='" + name + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String name;
}
