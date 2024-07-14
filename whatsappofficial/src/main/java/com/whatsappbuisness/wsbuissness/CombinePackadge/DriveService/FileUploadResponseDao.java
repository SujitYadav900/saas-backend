package com.whatsappbuisness.wsbuissness.CombinePackadge.DriveService;

public class FileUploadResponseDao {
    @Override
    public String toString() {
        return "DriveReponseDao{" +
                "id=" + id +
                ", fileUrl='" + fileUrl + '\'' +
                '}';
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }

    private long id;
private String fileUrl;
}
