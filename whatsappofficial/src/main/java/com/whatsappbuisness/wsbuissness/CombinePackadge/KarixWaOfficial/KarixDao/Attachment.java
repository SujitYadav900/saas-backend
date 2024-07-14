package com.whatsappbuisness.wsbuissness.CombinePackadge.KarixWaOfficial.KarixDao;
/*
 Author=Ritu Redhu
 Date= 06/01/23 10:00 AM
*/
public class Attachment {

    private String type;
    private String caption;
    private String mimeType;
    private String attachmentData;
    private String url;

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    private String fileName;



    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public String getMimeType() {
        return mimeType;
    }

    public void setMimeType(String mimeType) {
        this.mimeType = mimeType;
    }

    public String getAttachmentData() {
        return attachmentData;
    }

    @Override
    public String toString() {
        return "Attachment{" +
                "type='" + type + '\'' +
                ", caption='" + caption + '\'' +
                ", mimeType='" + mimeType + '\'' +
                ", attachmentData='" + attachmentData + '\'' +
                ", url='" + url + '\'' +
                ", fileName='" + fileName + '\'' +
                '}';
    }

    public void setAttachmentData(String attachmentData) {
        this.attachmentData = attachmentData;
    }


}
