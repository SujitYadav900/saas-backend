package com.whatsappbuisness.wsbuissness.CombinePackadge.whatsappCloudAPI;

import java.io.Serializable;

public class Video implements Serializable {

    private String link;
    private String id;

    private String caption;

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

    @Override
    public String toString() {
        return "Video{" +
                "link='" + link + '\'' +
                ", id='" + id + '\'' +
                ", caption='" + caption + '\'' +
                '}';
    }
}
