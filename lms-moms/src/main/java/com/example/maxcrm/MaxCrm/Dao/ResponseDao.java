package com.example.maxcrm.MaxCrm.Dao;

public class ResponseDao {
    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "ResponseDao{" +
                "statusCode=" + statusCode +
                ", id=" + id +
                ", msg='" + msg + '\'' +
                '}';
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    private int statusCode;
private long id;
private String msg;

}
