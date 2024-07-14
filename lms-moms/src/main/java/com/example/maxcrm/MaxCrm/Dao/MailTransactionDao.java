package com.example.maxcrm.MaxCrm.Dao;

import javax.persistence.*;

@Entity
@Table(name="Tbl_Mail_Transaction")
public class MailTransactionDao {
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public byte getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return "MailTransactionDao{" +
                "id=" + id +
                ", status=" + status +
                ", datefiler=" + datefiler +
                ", count=" + count +
                '}';
    }

    public void setStatus(byte status) {
        this.status = status;
    }

    public int getDatefiler() {
        return datefiler;
    }

    public void setDatefiler(int datefiler) {
        this.datefiler = datefiler;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private byte status;
    private int datefiler;
    private int count;
}
