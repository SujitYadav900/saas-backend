package com.whatsappbuisness.wsbuissness.CombinePackadge.EventsWebhooks;

import com.whatsappbuisness.wsbuissness.CombinePackadge.Messages.MessageDao;
import com.whatsappbuisness.wsbuissness.CombinePackadge.RabbitMqQue.Webhooks.Webhook.ContactsDao;

import java.util.List;

public class IncomingWebhookDao {

    public List<DlrStatusDao> getStatuses() {
        return statuses;
    }

    public void setStatuses(List<DlrStatusDao> statuses) {
        this.statuses = statuses;
    }

    private List<DlrStatusDao> statuses;

    public List<MessageDao> getMessages() {
        return messages;
    }

    public void setMessages(List<MessageDao> messages) {
        this.messages = messages;
    }

    private List<MessageDao> messages;

    @Override
    public String toString() {
        return "IncomingWebhookDao{" +
                "statuses=" + statuses +
                ", messages=" + messages +
                ", contacts=" + contacts +
                '}';
    }

    public List<ContactsDao> getContacts() {
        return contacts;
    }

    public void setContacts(List<ContactsDao> contacts) {
        this.contacts = contacts;
    }

    private List<ContactsDao> contacts;
}
