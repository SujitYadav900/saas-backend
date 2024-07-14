package com.whatsappbuisness.wsbuissness.CombinePackadge.Messages.InteractiveMessageDao;

import com.whatsappbuisness.wsbuissness.CombinePackadge.Messages.MessageType;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class InteractiveMessageDao implements Serializable {
    private MessageType type;
    private InteractiveHeaderDao header;
    private HashMap<String,String> body;
    private HashMap<String,String> footer;
    private InteractiveActionDao action;
    private Map button_reply;
    private Map list_reply;


    @Override
    public String toString() {
        return "InteractiveMessageDao{" +
                "type=" + type +
                ", header=" + header +
                ", body=" + body +
                ", footer=" + footer +
                ", action=" + action +
                ", button_reply=" + button_reply +
                ", list_reply=" + list_reply +
                '}';
    }

    public HashMap<String, String> getFooter() {
        return footer;
    }

    public void setFooter(HashMap<String, String> footer) {
        this.footer = footer;
    }

    public Map getButton_reply() {
        return button_reply;
    }

    public void setButton_reply(Map button_reply) {
        this.button_reply = button_reply;
    }

    public Map getList_reply() {
        return list_reply;
    }

    public void setList_reply(Map list_reply) {
        this.list_reply = list_reply;
    }

    public MessageType getType() {
        return type;
    }

    public void setType(MessageType type) {
        this.type = type;
    }

    public InteractiveHeaderDao getHeader() {
        return header;
    }

    public void setHeader(InteractiveHeaderDao header) {
        this.header = header;
    }

    public HashMap<String, String> getBody() {
        return body;
    }

    public void setBody(HashMap<String, String> body) {
        this.body = body;
    }

    public InteractiveActionDao getAction() {
        return action;
    }

    public void setAction(InteractiveActionDao action) {
        this.action = action;
    }

}
