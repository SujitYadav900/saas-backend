package com.whatsappbuisness.wsbuissness.CombinePackadge.catalog;

import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import java.io.Serializable;

@Document(collection = "CatalogMessageDao")
public class CatalogMessageDao implements Serializable {

    @Id
    private String id;

    private String catalogName;
    private String messaging_product;
    private String to;
    private String type;
    private Interactive interactive;
    private long accountId;
    private String date;
    private int dateFilter;
    private String updateDate;
    private boolean active;

    public CatalogMessageDao(String id, String catalogName, String messaging_product, String to, String type, Interactive interactive, long accountId, String date, int dateFilter, String updateDate, boolean active) {
        this.id = id;
        this.catalogName = catalogName;
        this.messaging_product = messaging_product;
        this.to = to;
        this.type = type;
        this.interactive = interactive;
        this.accountId = accountId;
        this.date = date;
        this.dateFilter = dateFilter;
        this.updateDate = updateDate;
        this.active = active;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public CatalogMessageDao() {
    }


    public String getMessaging_product() {
        return messaging_product;
    }

    public void setMessaging_product(String messaging_product) {
        this.messaging_product = messaging_product;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Interactive getInteractive() {
        return interactive;
    }

    public void setInteractive(Interactive interactive) {
        this.interactive = interactive;
    }

    public long getAccountId() {
        return accountId;
    }

    public void setAccountId(long accountId) {
        this.accountId = accountId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getDateFilter() {
        return dateFilter;
    }

    public void setDateFilter(int dateFilter) {
        this.dateFilter = dateFilter;
    }

    public String getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }

    public String getCatalogName() {
        return catalogName;
    }

    public void setCatalogName(String catalogName) {
        this.catalogName = catalogName;
    }

    @Override
    public String toString() {
        return "CatalogMessageDao{" +
                "id='" + id + '\'' +
                ", catalogName='" + catalogName + '\'' +
                ", messaging_product='" + messaging_product + '\'' +
                ", to='" + to + '\'' +
                ", type='" + type + '\'' +
                ", interactive=" + interactive +
                ", accountId=" + accountId +
                ", date='" + date + '\'' +
                ", dateFilter=" + dateFilter +
                ", updateDate='" + updateDate + '\'' +
                ", active=" + active +
                '}';
    }
}
