package com.whatsappbuisness.wsbuissness.CombinePackadge.DriveService;

public class FileUploadDao {
    @Override
    public String toString() {
        return "FileUploadDao{" +
                "fileName='" + fileName + '\'' +
                ", mediaType='" + mediaType + '\'' +
                ", accountId=" + accountId +
                '}';
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getMediaType() {
        return mediaType;
    }

    public void setMediaType(String mediaType) {
        this.mediaType = mediaType;
    }

    public long getAccountId() {
        return accountId;
    }

    public void setAccountId(long accountId) {
        this.accountId = accountId;
    }

    public FileUploadDao(String fileName, String mediaType, long accountId,byte[] file) {
        this.fileName = fileName;
        this.mediaType = mediaType;
        this.accountId = accountId;
        this.file=file;
    }

    private String fileName;
    private String mediaType;
    private long accountId;

    public byte[] getFile() {
        return file;
    }

    public void setFile(byte[] file) {
        this.file = file;
    }

    private byte[] file;
}
