package com.example.maxcrm.MaxCrm.Dao;

import javax.persistence.*;

@Entity
@Table(name = "Tbl_Login_History", indexes = {@Index(name = "useridindex", columnList="userId",  unique = false)})

public class LoginHistoryDao {
    @Override
    public String toString() {
        return "LoginHistoryDao{" +
                "id=" + id +
                ", userId=" + userId +
                ", ip='" + ip + '\'' +
                ", loginTiming='" + loginTiming + '\'' +
                '}';
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getLoginTiming() {
        return loginTiming;
    }

    public void setLoginTiming(String loginTiming) {
        this.loginTiming = loginTiming;
    }

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;
    private int userId;
    private String ip;
    private String loginTiming;

}
