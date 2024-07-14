package com.whatsappbuisness.wsbuissness.CombinePackadge.ApiService.Media;

public class MediaReturnDao {
    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public byte[] getArr() {
        return arr;
    }

    public void setArr(byte[] arr) {
        this.arr = arr;
    }

    private String fileName;
    private byte[] arr;

    public MediaReturnDao(String fileName, byte[] arr) {
        this.fileName = fileName;
        this.arr = arr;
    }
}
