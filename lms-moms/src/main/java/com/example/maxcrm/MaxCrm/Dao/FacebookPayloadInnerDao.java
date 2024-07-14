package com.example.maxcrm.MaxCrm.Dao;

public class FacebookPayloadInnerDao {
    public FaceBookPayloadValue getValue() {
        return value;
    }

    public void setValue(FaceBookPayloadValue value) {
        this.value = value;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    @Override
    public String toString() {
        return "FacebookPayloadInnerDao{" +
                "value=" + value +
                ", field='" + field + '\'' +
                '}';
    }

    private FaceBookPayloadValue value;
private String field;
}
