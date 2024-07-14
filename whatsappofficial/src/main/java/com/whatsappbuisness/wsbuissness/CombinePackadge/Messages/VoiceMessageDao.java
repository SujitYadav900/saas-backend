package com.whatsappbuisness.wsbuissness.CombinePackadge.Messages;

import java.io.Serializable;

public class VoiceMessageDao implements Serializable {
    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public String getLink() {
        return link;
    }


    public void setLink(String link) {
        this.link = link;
    }

    private String link;
    private String caption;
    private String id;

    @Override
    public String toString() {
        return "ImageMessageDao{" +
                "link='" + link + '\'' +
                ", caption='" + caption + '\'' +
                ", id='" + id + '\'' +
                ", file='" + file + '\'' +
                ", mime_type='" + mime_type + '\'' +
                ", sha256='" + sha256 + '\'' +
                '}';
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
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

    private String file;
    private String mime_type;
    private String sha256;
}
