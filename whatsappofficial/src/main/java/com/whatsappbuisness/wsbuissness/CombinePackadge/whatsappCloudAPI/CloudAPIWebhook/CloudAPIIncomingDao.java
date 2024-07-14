package com.whatsappbuisness.wsbuissness.CombinePackadge.whatsappCloudAPI.CloudAPIWebhook;

import com.whatsappbuisness.wsbuissness.CombinePackadge.catalog.catalogIncomming.Order;

import java.util.List;

public class CloudAPIIncomingDao {
    private String object;
    private List<Entry> entry;

    public String getObject() {
        return object;
    }

    public void setObject(String object) {
        this.object = object;
    }

    public List<Entry> getEntry() {
        return entry;
    }

    public void setEntry(List<Entry> entry) {
        this.entry = entry;
    }

    @Override
    public String toString() {
        return "CloudAPIIncomingDao{" +
                "object='" + object + '\'' +
                ", entry=" + entry +
                '}';
    }
}
