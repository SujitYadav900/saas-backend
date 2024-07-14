package com.whatsappbuisness.wsbuissness.CombinePackadge;

import java.util.List;

public class PaginationDao<T> {
    @Override
    public String toString() {
        return "PaginationDao{" +
                "total=" + total +
                ", data=" + data +
                '}';
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }




    private long total;


    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    private List<T> data;
}
