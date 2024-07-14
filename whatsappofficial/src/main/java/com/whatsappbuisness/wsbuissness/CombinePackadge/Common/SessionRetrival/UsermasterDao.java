package com.whatsappbuisness.wsbuissness.CombinePackadge.Common.SessionRetrival;


import com.whatsappbuisness.wsbuissness.CombinePackadge.AccountMasterDao.AccountType;
import com.whatsappbuisness.wsbuissness.CombinePackadge.AccountMasterDao.Services;
import com.whatsappbuisness.wsbuissness.CombinePackadge.AccountMasterDao.UserAccountType;

import java.io.Serializable;


/*
 Author=Supreet Singh
 Date= 19/02/21 12:14 PM
*/

public class UsermasterDao implements Serializable {


    @Override
    public String toString() {
        return "UsermasterDao{" +
                "id=" + id +
                ", fullname='" + fullname + '\'' +
                ", accountId=" + accountId +
                ", password='" + password + '\'' +
                ", createBy='" + createBy + '\'' +
                ", createAt='" + createAt + '\'' +
                ", updateBy='" + updateBy + '\'' +
                ", updateAt='" + updateAt + '\'' +
                ", active=" + active +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", accountType=" + accountType +
                ", servicesList='" + servicesList + '\'' +
                '}';
    }

    public String getServicesList() {
        return servicesList;
    }

    public void setServicesList(String servicesList) {
        this.servicesList = servicesList;
    }

    public UserAccountType getAccountType() {
        return accountType;
    }

    public void setAccountType(UserAccountType accountType) {
        this.accountType = accountType;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public long getAccountId() {
        return accountId;
    }

    public void setAccountId(long accountId) {
        this.accountId = accountId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCreateBy() {
        return createBy;
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

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public String getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(String updateAt) {
        this.updateAt = updateAt;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    private long id;
    private String fullname;
    private long accountId;
    private String password;
    private String createBy;
    private String createAt;
    private String updateBy;
    private String updateAt;
    private boolean active;
    private String email;
    private String phone;

    private UserAccountType accountType;
    private String servicesList;




}
