package com.whatsappbuisness.wsbuissness.CombinePackadge.catalog;

import java.io.Serializable;

public class Footer implements Serializable {

    private String text;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Footer(String text) {
        this.text = text;
    }

    public Footer() {
    }

    @Override
    public String toString() {
        return "Footer{" +
                "text='" + text + '\'' +
                '}';
    }
}
