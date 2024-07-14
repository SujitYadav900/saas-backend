package com.example.maxcrm.MaxCrm.CombinePackage.Wati.WatiDaos;

public class CustomParamDao {
    private String name;
    private String value;

    public CustomParamDao(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}

//        "name": "string",
//        "value": "string"