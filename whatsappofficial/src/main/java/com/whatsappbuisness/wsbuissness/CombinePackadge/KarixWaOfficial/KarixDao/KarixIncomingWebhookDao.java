package com.whatsappbuisness.wsbuissness.CombinePackadge.KarixWaOfficial.KarixDao;

/*
 Author=Ritu Redhu
 Date= 06/01/23 10:00 AM
*/
import com.whatsappbuisness.wsbuissness.CombinePackadge.KarixWaOfficial.KarixDao.KarixIncommingWebhook.EventContent;

public class KarixIncomingWebhookDao {

    public String channel;
    public AppDetails appDetails;
    public Recipient recipient;
    public String batchId;
    public String campaignId;
    public String templateId;
    public Sender sender;
    public Events events;
    public EventContent eventContent;
    public NotificationAttributes notificationAttributes;
    public ConvDetails convDetails;

    public EventContent getEventContent() {
        return eventContent;
    }

    public void setEventContent(EventContent eventContent) {
        this.eventContent = eventContent;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public AppDetails getAppDetails() {
        return appDetails;
    }

    public void setAppDetails(AppDetails appDetails) {
        this.appDetails = appDetails;
    }

    public Recipient getRecipient() {
        return recipient;
    }

    public void setRecipient(Recipient recipient) {
        this.recipient = recipient;
    }

    public String getBatchId() {
        return batchId;
    }

    public void setBatchId(String batchId) {
        this.batchId = batchId;
    }

    public String getCampaignId() {
        return campaignId;
    }

    public void setCampaignId(String campaignId) {
        this.campaignId = campaignId;
    }

    public String getTemplateId() {
        return templateId;
    }

    public void setTemplateId(String templateId) {
        this.templateId = templateId;
    }

    public Sender getSender() {
        return sender;
    }

    public void setSender(Sender sender) {
        this.sender = sender;
    }

    public Events getEvents() {
        return events;
    }

    public void setEvents(Events events) {
        this.events = events;
    }

    public NotificationAttributes getNotificationAttributes() {
        return notificationAttributes;
    }

    public void setNotificationAttributes(NotificationAttributes notificationAttributes) {
        this.notificationAttributes = notificationAttributes;
    }

    public ConvDetails getConvDetails() {
        return convDetails;
    }

    public void setConvDetails(ConvDetails convDetails) {
        this.convDetails = convDetails;
    }

    @Override
    public String toString() {
        return "KarixIncomingWebhookDao{" +
                "channel='" + channel + '\'' +
                ", appDetails=" + appDetails +
                ", recipient=" + recipient +
                ", batchId='" + batchId + '\'' +
                ", campaignId='" + campaignId + '\'' +
                ", templateId='" + templateId + '\'' +
                ", sender=" + sender +
                ", events=" + events +
                ", eventContent=" + eventContent +
                ", notificationAttributes=" + notificationAttributes +
                ", convDetails=" + convDetails +
                '}';
    }
}
