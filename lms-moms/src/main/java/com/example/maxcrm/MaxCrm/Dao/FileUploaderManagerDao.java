package com.example.maxcrm.MaxCrm.Dao;

public class FileUploaderManagerDao {

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFileId() {
        return fileId;
    }

    public void setFileId(String fileId) {
        this.fileId = fileId;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public long getRefId() {
        return refId;
    }

    public void setRefId(long refId) {
        this.refId = refId;
    }

    public byte getType() {
        return type;
    }

    public void setType(byte type) {
        this.type = type;
    }

    public long getFileSize() {
        return fileSize;
    }

    public void setFileSize(long fileSize) {
        this.fileSize = fileSize;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    private long id;
    private String fileId;
    private String createBy;
    private String createDate;
    private long refId;
    private byte type; // 1 for user //2 for lead // 3 for ticketing
    private long fileSize;

    @Override
    public String toString() {
        return "FileUploaderManagerDao{" +
                "id=" + id +
                ", fileId='" + fileId + '\'' +
                ", createBy='" + createBy + '\'' +
                ", createDate='" + createDate + '\'' +
                ", refId=" + refId +
                ", type=" + type +
                ", fileSize=" + fileSize +
                ", filename='" + filename + '\'' +
                '}';
    }

    private String filename;

}
