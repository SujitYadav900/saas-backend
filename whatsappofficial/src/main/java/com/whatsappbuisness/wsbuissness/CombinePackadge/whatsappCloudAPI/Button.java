package com.whatsappbuisness.wsbuissness.CombinePackadge.whatsappCloudAPI;

import java.io.Serializable;

public class Button {

    private String type;
    private Reply reply;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Reply getReply() {
        return reply;
    }

    public void setReply(Reply reply) {
        this.reply = reply;
    }

    @Override
    public String toString() {
        return "Button{" +
                "type='" + type + '\'' +
                ", reply=" + reply +
                '}';
    }
}

