package com.example.maxcrm.MaxCrm.Dao;

import java.util.List;

public class FacebookDao {
    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "FacebookDao{" +
                "createTime='" + createTime + '\'' +
                ", id='" + id + '\'' +
                ", field_data=" + field_data +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<FacebookFieldDataDao> getField_data() {
        return field_data;
    }

    public void setField_data(List<FacebookFieldDataDao> field_data) {
        this.field_data = field_data;
    }

    private String createTime;
    private String id;
    List<FacebookFieldDataDao> field_data;

}
