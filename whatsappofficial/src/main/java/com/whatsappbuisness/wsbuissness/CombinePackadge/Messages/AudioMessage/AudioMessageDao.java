package com.whatsappbuisness.wsbuissness.CombinePackadge.Messages.AudioMessage;
/*      
 Author=Supreet Singh
 Date= 04/02/21 5:07 PM
*/


import com.whatsappbuisness.wsbuissness.CombinePackadge.Messages.ProviderDao;

import java.io.Serializable;

public class AudioMessageDao implements Serializable {

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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

    @Override
    public String toString() {
        return "CommonDao{" +
                "provider=" + provider +

                ", mime_type='" + mime_type + '\'' +
                ", sha256='" + sha256 + '\'' +
                '}';
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

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    private String link;

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    private String file;

}
