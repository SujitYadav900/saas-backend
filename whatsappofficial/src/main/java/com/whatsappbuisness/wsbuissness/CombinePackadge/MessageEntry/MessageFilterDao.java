package com.whatsappbuisness.wsbuissness.CombinePackadge.MessageEntry;
/*
 Author=Supreet Singh
 Date= 16/03/21 2:28 PM
*/

import com.whatsappbuisness.wsbuissness.CombinePackadge.Filtering.FilterDao;

public class MessageFilterDao extends FilterDao {
    @Override
    public String toString() {
        return "MessageFilterDao{" +
                "campaignId=" + campaignId +
                '}';
    }

    public long getCampaignId() {
        return campaignId;
    }

    public void setCampaignId(long campaignId) {
        this.campaignId = campaignId;
    }

    private long campaignId;
}
