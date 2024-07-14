package com.example.maxcrm.MaxCrm.Dao;

import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
@Document(collection = "Counter")
public class CounterDocument {
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getUqid() {
        return uqid;
    }

    @Override
    public String toString() {
        return "CounterDocument{" +
                "id='" + id + '\'' +
                ", uqid=" + uqid +
                ", seq=" + seq +
                ", desc='" + desc + '\'' +
                '}';
    }

    public void setUqid(int uqid) {
        this.uqid = uqid;
    }

    public long getSeq() {
        return seq;
    }

    public void setSeq(long seq) {
        this.seq = seq;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    @Id
    private String id;
    private int uqid;
    private long seq;
    private String desc;
}
