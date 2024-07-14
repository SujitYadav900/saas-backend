package com.whatsappbuisness.wsbuissness.CombinePackadge.Messages;
/*
 Author=Supreet Singh
 Date= 09/03/21 10:40 AM
*/

import java.io.Serializable;

public class MediaCommonObj implements Serializable {
    @Override
    public String toString() {
        return "MediaCommonObj{" +
                "link='" + link + '\'' +
                ", filename='" + filename + '\'' +
                ", baseContent='" + baseContent + '\'' +
                '}';
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getBaseContent() {
        return baseContent;
    }

    public void setBaseContent(String baseContent) {
        this.baseContent = baseContent;
    }

    private String link;
    private String filename;
    private String baseContent;
}
