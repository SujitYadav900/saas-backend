package com.whatsappbuisness.wsbuissness.CombinePackadge.RabbitMqQue.Webhooks.Webhook;
/*
 Author=Supreet Singh
 Date= 08/02/21 6:20 PM
*/

import com.whatsappbuisness.wsbuissness.CombinePackadge.Messages.MessageStatus;
import com.whatsappbuisness.wsbuissness.CombinePackadge.RabbitMqQue.ErrorReponse;

import javax.persistence.Transient;
import java.io.Serializable;
import java.util.List;

public class DlrUpdatesDao implements Serializable {

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRecipient_id() {
        return recipient_id;
    }

    public void setRecipient_id(String recipient_id) {
        this.recipient_id = recipient_id;
    }


    private String id;
    private String recipient_id;
    private MessageStatus status;
    @Transient
    private String messageTime;

    public String getMessageTime() {
        return messageTime;
    }

    public void setMessageTime(String messageTime) {
        this.messageTime = messageTime;
    }

    @Override
    public String toString() {
        return "DlrUpdatesDao{" +
                "id='" + id + '\'' +
                ", recipient_id='" + recipient_id + '\'' +
                ", status=" + status +
                ", messageTime='" + messageTime + '\'' +
                ", accountId=" + accountId +
                ", errors=" + errors +
                '}';
    }

    public MessageStatus getStatus() {
        return status;
    }

    public void setStatus(MessageStatus status) {
        this.status = status;
    }

    public long getAccountId() {
        return accountId;
    }

    public void setAccountId(long accountId) {
        this.accountId = accountId;
    }

    private long accountId;

    public List<ErrorReponse> getErrors() {
        return errors;
    }

    public void setErrors(List<ErrorReponse> errors) {
        this.errors = errors;
    }

    private List<ErrorReponse> errors;

}

