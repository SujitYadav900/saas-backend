package com.example.maxcrm.MaxCrm.Dao;

import org.bson.types.Binary;

public class AttachmentDao {
    @Override
    public String toString() {
        return "AttachmentDao{" +
                "fileName='" + fileName + '\'' +
                ", id='" + id + '\'' +
                '}';
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    private String fileName;
    private String id;

    public Binary getBinary() {
        return binary;
    }

    public void setBinary(Binary binary) {
        this.binary = binary;
    }

    private Binary binary;
}
