package com.whatsappbuisness.wsbuissness.CombinePackadge.catalog;


import java.io.Serializable;

public class Interactive implements Serializable {
    private String type;
    private Header header;
    private Body body;
    private Footer footer;
    private Action action;

    public Interactive(String type, Header header, Body body, Footer footer, Action action) {
        this.type = type;
        this.header = header;
        this.body = body;
        this.footer = footer;
        this.action = action;
    }

    public Interactive() {
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Header getHeader() {
        return header;
    }

    public void setHeader(Header header) {
        this.header = header;
    }

    public Body getBody() {
        return body;
    }

    public void setBody(Body body) {
        this.body = body;
    }

    public Footer getFooter() {
        return footer;
    }

    public void setFooter(Footer footer) {
        this.footer = footer;
    }

    public Action getAction() {
        return action;
    }

    public void setAction(Action action) {
        this.action = action;
    }

    @Override
    public String toString() {
        return "Interactive{" +
                "type='" + type + '\'' +
                ", header=" + header +
                ", body=" + body +
                ", footer=" + footer +
                ", action=" + action +
                '}';
    }
}
