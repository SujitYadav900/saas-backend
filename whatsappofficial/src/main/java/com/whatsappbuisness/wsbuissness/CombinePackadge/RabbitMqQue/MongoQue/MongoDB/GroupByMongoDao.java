package com.whatsappbuisness.wsbuissness.CombinePackadge.RabbitMqQue.MongoQue.MongoDB;

import com.google.gson.Gson;

import java.util.Arrays;
import java.util.List;

public class GroupByMongoDao {


    @Override
    public String toString() {
        return "GroupByMongoDao{" +
                "reportName='" + reportName + '\'' +
                ", _id='" + _id + '\'' +
                ", count=" + count +
                '}';
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getReportName() {
        return reportName;
    }

    public void setReportName(String reportName) {
        this.reportName = reportName;
    }

    private String reportName;
    private String _id;
    private int count;
    public List<GroupByMongoDao> convertFRomJson(String json)
    {
        return Arrays.asList(new Gson().fromJson(json,GroupByMongoDao[].class));

    }

}
