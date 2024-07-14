package com.example.maxcrm.MaxCrm.Dao;

public class GoogleAddwordInnerDetails {
    public String getColumn_name() {
        return column_name;
    }

    public void setColumn_name(String column_name) {
        this.column_name = column_name;
    }

    @Override
    public String toString() {
        return "GoogleAddwordInnerDetails{" +
                "column_name='" + column_name + '\'' +
                ", string_value='" + string_value + '\'' +
                '}';
    }

    public String getString_value() {
        return string_value;
    }

    public void setString_value(String string_value) {
        this.string_value = string_value;
    }

    private String column_name;
    private String string_value;
}
