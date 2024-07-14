package com.example.maxcrm.MaxCrm.Dao;

import org.bson.types.Binary;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;

@Document(collection = "FileDocument")
public class FileDocument {
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Binary getImage() {
        return image;
    }

    public void setImage(Binary image) {
        this.image = image;
    }

    public String getCreateBy() {
        return createBy;
    }

    @Override
    public String toString() {
        return "FileDocument{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", image=" + image +
                ", createBy='" + createBy + '\'' +
                ", createAt='" + createAt + '\'' +
                '}';
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public String getCreateAt() {
        return createAt;
    }

    public void setCreateAt(String createAt) {
        this.createAt = createAt;
    }

    @Id
    private String id;
    private String name;
    private Binary image;
    private String createBy;
    private String createAt;
}
