package com.example.maxcrm.MaxCrm.Dao;

public class TextMessageTrans {
    public String getDest() {
        return dest;
    }

    public void setDest(String dest) {
        this.dest = dest;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "TextMessageTrans{" +
                "dest='" + dest + '\'' +
                ", msg='" + msg + '\'' +
                '}';
    }

    private String dest;
    private String msg;





}
