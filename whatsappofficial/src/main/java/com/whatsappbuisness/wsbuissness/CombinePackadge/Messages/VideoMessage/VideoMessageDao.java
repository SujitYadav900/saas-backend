package com.whatsappbuisness.wsbuissness.CombinePackadge.Messages.VideoMessage;
/*      
 Author=Supreet Singh
 Date= 04/02/21 5:10 PM
*/


import com.whatsappbuisness.wsbuissness.CombinePackadge.Messages.ProviderDao;

import java.io.Serializable;

public class VideoMessageDao implements Serializable {


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



    private String caption;
    private String id;
    public ProviderDao getProvider() {
        return provider;
    }

    public void setProvider(ProviderDao provider) {
        this.provider = provider;
    }

    private ProviderDao provider;




    private String mime_type;
    private String sha256;

    public String getLink() {
        return link;
    }

    @Override
    public String toString() {
        return "VideoMessageDao{" +
                "caption='" + caption + '\'' +
                ", id='" + id + '\'' +
                ", provider=" + provider +
                ", mime_type='" + mime_type + '\'' +
                ", sha256='" + sha256 + '\'' +
                ", link='" + link + '\'' +
                '}';
    }

    public void setLink(String link) {
        this.link = link;
    }

    private String link;

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

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    private String file;


}
