package com.example.maxcrm.MaxCrm.Dao;

public class WhatsParameterDao {
    public String getParam() {
        return param;
    }

    public void setParam(String param) {
        this.param = param;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "WhatsParameterDao{" +
                "param='" + param + '\'' +
                ", value='" + value + '\'' +
                '}';
    }

    private String param;
    private String value;
}
