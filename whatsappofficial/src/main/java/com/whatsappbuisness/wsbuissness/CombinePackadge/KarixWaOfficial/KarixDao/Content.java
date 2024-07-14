package com.whatsappbuisness.wsbuissness.CombinePackadge.KarixWaOfficial.KarixDao;

import com.whatsappbuisness.wsbuissness.CombinePackadge.Messages.InteractiveMessageDao.InteractiveMessageDao;
import com.whatsappbuisness.wsbuissness.CombinePackadge.Messages.Location.LocationMessageDao;

/*
 Author=Ritu Redhu
 Date= 06/01/23 10:00 AM
*/
public class Content {

    private Boolean preview_url;
    private String type;
    private LocationMessageDao location;
    private Template template;
    private String text;
    private MediaTemplate mediaTemplate;
    private Attachment attachment;
    private InteractiveMessageDao interactive;

    public Buttons getButtons() {
        return buttons;
    }

    public void setButtons(Buttons buttons) {
        this.buttons = buttons;
    }

    private Buttons buttons;



    public InteractiveMessageDao getInteractive() {
        return interactive;
    }

    public void setInteractive(InteractiveMessageDao interactive) {
        this.interactive = interactive;
    }

    public MediaTemplate getMediaTemplate() {
        return mediaTemplate;
    }

    public void setMediaTemplate(MediaTemplate mediaTemplate) {
        this.mediaTemplate = mediaTemplate;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Boolean getPreview_url() {
        return preview_url;
    }

    public void setPreview_url(Boolean preview_url) {
        this.preview_url = preview_url;
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

    public Attachment getAttachment() {
        return attachment;
    }

    public void setAttachment(Attachment attachment) {
        this.attachment = attachment;
    }

    public LocationMessageDao getLocation() {
        return location;
    }

    public void setLocation(LocationMessageDao location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return "Content{" +
                "preview_url=" + preview_url +
                ", type='" + type + '\'' +
                ", template=" + template +
                ", text='" + text + '\'' +
                ", mediaTemplate=" + mediaTemplate +
                ", attachment=" + attachment +
                ", interactive=" + interactive +
                ", buttons=" + buttons +
                ",location=" + location +
                '}';
    }
}
