package com.example.maxcrm.MaxCrm.Dao;

import com.google.gson.Gson;

import java.util.Arrays;
import java.util.List;

public class WhatsappDao {
    @Override
    public String toString() {
        return "WhatsappDao{" +
                "phone='" + phone + '\'' +
                ", contentType='" + contentType + '\'' +
                ", content='" + content + '\'' +
                ", caption='" + caption + '\'' +
                ", fileName='" + fileName + '\'' +
                '}';
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public byte getContentType() {
        return contentType;
    }

    public void setContentType(byte contentType) {
        this.contentType = contentType;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    private String phone;
   private byte contentType;
   private String content;
   private String caption;
   private String fileName;
   public String convertToJson(List<WhatsappDao> al)
   {
       return new Gson().toJson(al);
   }

}
