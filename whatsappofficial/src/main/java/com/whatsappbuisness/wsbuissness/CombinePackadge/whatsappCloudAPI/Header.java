package com.whatsappbuisness.wsbuissness.CombinePackadge.whatsappCloudAPI;

import java.io.Serializable;

public class Header {
    private String type;
    private Image image;
    private Document document;
    private Video video;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    public Video getVideo() {
        return video;
    }

    public void setVideo(Video video) {
        this.video = video;
    }

    @Override
    public String toString() {
        return "Header{" +
                "type='" + type + '\'' +
                ", image=" + image +
                ", document=" + document +
                ", video=" + video +
                '}';
    }
}
