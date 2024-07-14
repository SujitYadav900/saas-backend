package com.whatsappbuisness.wsbuissness.CombinePackadge.whatsappCloudAPI.CloudAPIWebhook;

public class Video {
    private String caption;
    private String mime_type;
    private String sha256;
    private String id;

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public String getMime_type() {
        return mime_type;
    }

    public void setMime_type(String mime_type) {
        this.mime_type = mime_type;
    }

    public String getSha256() {
        return sha256;
    }

    public void setSha256(String sha256) {
        this.sha256 = sha256;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Video{" +
                "caption='" + caption + '\'' +
                ", mime_type='" + mime_type + '\'' +
                ", sha256='" + sha256 + '\'' +
                ", id='" + id + '\'' +
                '}';
    }
}
