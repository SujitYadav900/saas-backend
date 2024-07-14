package com.whatsappbuisness.wsbuissness.CombinePackadge.CreditService;
/*
 Author=Supreet Singh
 Date= 11/03/21 1:05 PM
*/

public class ResponseServiceDao {
    @Override
    public String toString() {
        return "ResponseServiceDao{" +
                "status=" + status +
                ", error='" + error + '\'' +
                ", message='" + message + '\'' +
                ", path='" + path + '\'' +
                '}';
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    private int status;
    private String error;
    private String message;
    private String path;


}
