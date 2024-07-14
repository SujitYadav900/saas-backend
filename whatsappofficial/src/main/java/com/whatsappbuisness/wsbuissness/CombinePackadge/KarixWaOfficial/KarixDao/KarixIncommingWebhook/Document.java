package com.whatsappbuisness.wsbuissness.CombinePackadge.KarixWaOfficial.KarixDao.KarixIncommingWebhook;

public class Document {
    public String mime_type;
    public String sha256;
    public String fileLink;

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

    public String getFileLink() {
        return fileLink;
    }

    public void setFileLink(String fileLink) {
        this.fileLink = fileLink;
    }

    @Override
    public String toString() {
        return "Document{" +
                "mime_type='" + mime_type + '\'' +
                ", sha256='" + sha256 + '\'' +
                ", fileLink='" + fileLink + '\'' +
                '}';
    }
}
