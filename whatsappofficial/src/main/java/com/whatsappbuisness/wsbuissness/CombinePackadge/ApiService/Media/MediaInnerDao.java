package com.whatsappbuisness.wsbuissness.CombinePackadge.ApiService.Media;
/*      
 Author=Supreet Singh
 Date= 12/02/21 2:47 PM
*/

public class MediaInnerDao {

    @Override
    public String toString() {
        return "MediaInnerDao{" +
                "id='" + id + '\'' +
                ", attachedFileBase64='" + attachedFileBase64 + '\'' +
                ", mimeType='" + mimeType + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAttachedFileBase64() {
        return attachedFileBase64;
    }

    public void setAttachedFileBase64(String attachedFileBase64) {
        this.attachedFileBase64 = attachedFileBase64;
    }

    public String getMimeType() {
        return mimeType;
    }

    public void setMimeType(String mimeType) {
        this.mimeType = mimeType;
    }

    private String id;
    private String attachedFileBase64;
    private String mimeType;
}
