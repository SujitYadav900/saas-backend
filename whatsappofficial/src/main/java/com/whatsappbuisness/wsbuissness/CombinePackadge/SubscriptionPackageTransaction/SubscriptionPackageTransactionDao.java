package com.whatsappbuisness.wsbuissness.CombinePackadge.SubscriptionPackageTransaction;

import com.whatsappbuisness.wsbuissness.CombinePackadge.SubscriptionPackage.SubScriptionPackageDao;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "SubscriptionPackageTransactionDao")
public class SubscriptionPackageTransactionDao {
    private String id;
    private String createDate;
    private long createDateFilter;
    private long accountId;
    private SubScriptionPackageDao subScriptionPackageDao;
    private boolean status;

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public long getCreateDateFilter() {
        return createDateFilter;
    }

    public void setCreateDateFilter(long createDateFilter) {
        this.createDateFilter = createDateFilter;
    }

    public long getAccountId() {
        return accountId;
    }

    public void setAccountId(long accountId) {
        this.accountId = accountId;
    }
    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public SubScriptionPackageDao getSubScriptionPackageDao() {
        return subScriptionPackageDao;
    }

    public void setSubScriptionPackageDao(SubScriptionPackageDao subScriptionPackageDao) {
        this.subScriptionPackageDao = subScriptionPackageDao;
    }

    @Override
    public String toString() {
        return "SubscriptionPackageTransactionDao{" +
                "id='" + id + '\'' +
                ", createDate='" + createDate + '\'' +
                ", createDateFilter=" + createDateFilter +
                ", accountId=" + accountId +
                ", subScriptionPackageDao=" + subScriptionPackageDao +
                ", status=" + status +
                '}';
    }
}
