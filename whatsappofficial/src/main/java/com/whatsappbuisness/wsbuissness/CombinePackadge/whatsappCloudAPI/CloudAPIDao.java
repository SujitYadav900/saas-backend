package com.whatsappbuisness.wsbuissness.CombinePackadge.whatsappCloudAPI;

import com.whatsappbuisness.wsbuissness.CombinePackadge.Messages.InteractiveMessageDao.InteractiveMessageDao;
import com.whatsappbuisness.wsbuissness.CombinePackadge.Messages.Location.LocationMessageDao;

import java.io.Serializable;
import java.util.List;

public class CloudAPIDao {

    private String messaging_product;
    private boolean preview_url;

    private String recipient_type;
    private String to;
    private String type;

    private Text text;
    private Image image;
    private Document document;
    private Audio audio;
    private Video video;
    private LocationMessageDao location;
    private InteractiveMessageDao interactive;



    private Template template;

    public LocationMessageDao getLocation() {
        return location;
    }

    public void setLocation(LocationMessageDao location) {
        this.location = location;
    }

    public boolean isPreview_url() {
        return preview_url;
    }

    public InteractiveMessageDao getInteractive() {
        return interactive;
    }

    public void setInteractive(InteractiveMessageDao interactive) {
        this.interactive = interactive;
    }

    public void setPreview_url(boolean preview_url) {
        this.preview_url = preview_url;
    }

    public Text getText() {
        return text;
    }

    public void setText(Text text) {
        this.text = text;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public Document getDocument() {
        return document;
    }

    public void setDocument(Document document) {
        this.document = document;
    }

    public Audio getAudio() {
        return audio;
    }

    public void setAudio(Audio audio) {
        this.audio = audio;
    }

    public Video getVideo() {
        return video;
    }

    public void setVideo(Video video) {
        this.video = video;
    }



    public String getMessaging_product() {
        return messaging_product;
    }

    public void setMessaging_product(String messaging_product) {
        this.messaging_product = messaging_product;
    }

    public String getRecipient_type() {
        return recipient_type;
    }

    public void setRecipient_type(String recipient_type) {
        this.recipient_type = recipient_type;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Template getTemplate() {
        return template;
    }

    public void setTemplate(Template template) {
        this.template = template;
    }

    @Override
    public String toString() {
        return "CloudAPIDao{" +
                "messaging_product='" + messaging_product + '\'' +
                ", preview_url=" + preview_url +
                ", recipient_type='" + recipient_type + '\'' +
                ", to='" + to + '\'' +
                ", type='" + type + '\'' +
                ", text=" + text +
                ", image=" + image +
                ", document=" + document +
                ", audio=" + audio +
                ", video=" + video +
                ", location=" + location +
                ", interactive=" + interactive +
                ", template=" + template +
                '}';
    }
}
