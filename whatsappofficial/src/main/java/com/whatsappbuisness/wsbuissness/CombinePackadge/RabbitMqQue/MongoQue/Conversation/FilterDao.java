package com.whatsappbuisness.wsbuissness.CombinePackadge.RabbitMqQue.MongoQue.Conversation;
/*
 Author=Supreet Singh
 Date= 10/03/21 1:04 PM
*/

public class FilterDao {

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public int getLimit() {
        return limit;
    }

    @Override
    public String toString() {
        return "FilterDao{" +
                "offset=" + offset +
                ", limit=" + limit +
                ", search='" + search + '\'' +
                ", accountId=" + accountId +
                '}';
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }

    public long getAccountId() {
        return accountId;
    }

    public void setAccountId(long accountId) {
        this.accountId = accountId;
    }

    private int offset;
    private int limit;
    private String search;
    private long accountId;

}
