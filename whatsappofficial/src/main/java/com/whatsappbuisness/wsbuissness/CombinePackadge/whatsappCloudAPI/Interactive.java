package com.whatsappbuisness.wsbuissness.CombinePackadge.whatsappCloudAPI;


import java.io.Serializable;

public class Interactive {

    private String  type;
    private Body body;
    private Footer footer;
    private Action action;

    private Header header;


    public Header getHeader() {
        return header;
    }

    public void setHeader(Header header) {
        this.header = header;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Body getBody() {
        return body;
    }

    public void setBody(Body body) {
        this.body = body;
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
                ", body=" + body +
                ", footer=" + footer +
                ", action=" + action +
                ", header=" + header +
                '}';
    }

    public Footer getFooter() {
        return footer;
    }

    public void setFooter(Footer footer) {
        this.footer = footer;
    }


}

