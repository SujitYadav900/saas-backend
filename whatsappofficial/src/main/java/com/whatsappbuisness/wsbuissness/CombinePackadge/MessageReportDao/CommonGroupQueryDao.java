package com.whatsappbuisness.wsbuissness.CombinePackadge.MessageReportDao;
/*
Author: Sujit Yadav
Dare: 14/March/2023  12:01 PM
**/
public class CommonGroupQueryDao {

    @Override
    public String toString() {
        return "CommonGroupQueryDao{" +
                "_id='" + _id + '\'' +
                ", count=" + count +
                '}';
    }
    public String get_id() {
        return _id;
    }
    public void set_id(String _id) {
        this._id = _id;
    }
    public int getCount() {
        return count;
    }
    public void setCount(int count) {
        this.count = count;
    }
    private String _id;
    private int count;
}
