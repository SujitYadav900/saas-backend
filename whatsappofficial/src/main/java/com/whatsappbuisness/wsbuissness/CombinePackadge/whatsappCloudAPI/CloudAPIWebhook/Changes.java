package com.whatsappbuisness.wsbuissness.CombinePackadge.whatsappCloudAPI.CloudAPIWebhook;

import java.util.List;

public class Changes {

    private Value value;
    private String field;

    public Value getValue() {
        return value;
    }

    public void setValue(Value value) {
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
        return "Changes{" +
                "value=" + value +
                ", field='" + field + '\'' +
                '}';
    }
}
