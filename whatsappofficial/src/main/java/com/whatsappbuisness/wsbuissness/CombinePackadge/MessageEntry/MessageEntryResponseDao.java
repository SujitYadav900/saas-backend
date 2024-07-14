package com.whatsappbuisness.wsbuissness.CombinePackadge.MessageEntry;
/*
 Author=Supreet Singh
 Date= 11/03/21 2:41 PM
*/

import com.whatsappbuisness.wsbuissness.CombinePackadge.Messages.MessageStatus;

import java.util.List;

public class MessageEntryResponseDao {
    @Override
    public String toString() {
        return "MessageEntryResponseDao{" +
                "ids=" + ids +
                ", status=" + status +
                ", message='" + message + '\'' +
                '}';
    }

    public List<String> getIds() {
        return ids;
    }

    public void setIds(List<String> ids) {
        this.ids = ids;
    }

    public MessageStatus getStatus() {
        return status;
    }

    public void setStatus(MessageStatus status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    private List<String> ids;
    private MessageStatus status;
    private String message;
}
