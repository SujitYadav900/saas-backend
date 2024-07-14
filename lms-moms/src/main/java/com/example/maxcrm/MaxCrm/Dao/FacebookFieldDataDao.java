package com.example.maxcrm.MaxCrm.Dao;

import java.util.Arrays;

public class FacebookFieldDataDao {
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String[] getValues() {
        return values;
    }

    @Override
    public String toString() {
        return "FacebookFieldDataDao{" +
                "name='" + name + '\'' +
                ", values=" + Arrays.toString(values) +
                '}';
    }

    public void setValues(String[] values) {
        this.values = values;
    }

    private String name;
    private String[] values;
}
