package com.whatsappbuisness.wsbuissness.CombinePackadge.Filtering;
/*
 Author=Supreet Singh
 Date= 19/02/21 11:00 PM
*/

public class FilterDao {

    public String getStartdate() {
        return startdate;
    }

    public void setStartdate(String startdate) {
        this.startdate = startdate;
    }

    public String getEnddate() {
        return enddate;
    }

    public void setEnddate(String enddate) {
        this.enddate = enddate;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public long getAccountId() {
        return accountId;
    }

    public void setAccountId(long accountId) {
        this.accountId = accountId;
    }

    private String startdate;
    private String enddate;
    private int offset;
    private int limit;
    private long accountId;

    private boolean status;

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }


    public String getDst() {
        return dst;
    }

    public void setDst(String dst) {
        this.dst = dst;
    }

    private String dst;


    public DateFilterType getDateFilterType() {
        return dateFilterType;
    }

    public void setDateFilterType(DateFilterType dateFilterType) {
        this.dateFilterType = dateFilterType;
    }

    private DateFilterType dateFilterType;

    @Override
    public String toString() {
        return "FilterDao{" +
                "startdate='" + startdate + '\'' +
                ", enddate='" + enddate + '\'' +
                ", offset=" + offset +
                ", limit=" + limit +
                ", accountId=" + accountId +
                ", status=" + status +
                ", dst='" + dst + '\'' +
                ", dateFilterType=" + dateFilterType +
                '}';
    }
}

