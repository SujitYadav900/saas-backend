package com.example.maxcrm.MaxCrm.Dao;

import java.util.List;

public class FacebookPayloadDao {
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "FacebookPayloadDao{" +
                "id='" + id + '\'' +
                ", time=" + time +
                ", changes=" + changes +
                '}';
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public List<FacebookPayloadInnerDao> getChanges() {
        return changes;
    }

    public void setChanges(List<FacebookPayloadInnerDao> changes) {
        this.changes = changes;
    }

    private String id;
    private long time;
    private List<FacebookPayloadInnerDao> changes;
}
