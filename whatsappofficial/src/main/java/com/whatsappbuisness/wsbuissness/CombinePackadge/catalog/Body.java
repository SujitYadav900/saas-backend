package com.whatsappbuisness.wsbuissness.CombinePackadge.catalog;

import java.io.Serializable;

public class Body implements Serializable {
    private String text;

    public Body(String text) {
        this.text = text;
    }

    public Body() {
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "Body{" +
                "text='" + text + '\'' +
                '}';
    }
}
