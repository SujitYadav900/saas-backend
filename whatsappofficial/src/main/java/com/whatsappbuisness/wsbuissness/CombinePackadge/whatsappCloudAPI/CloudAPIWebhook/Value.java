package com.whatsappbuisness.wsbuissness.CombinePackadge.whatsappCloudAPI.CloudAPIWebhook;

import java.util.List;

public class Value {

    private String messaging_product;
    private Metadata metadata;
    private List<Statuses> statuses;
    private List<Contacts> contacts;
    private List<Messages> messages;

    private String event;
    private String message_template_id;
    private String message_template_name;
    private String message_template_language;
    private String reason;
    private String new_category;
    private String display_phone_number;
    private String current_limit;

    public String getDisplay_phone_number() {
        return display_phone_number;
    }

    public void setDisplay_phone_number(String display_phone_number) {
        this.display_phone_number = display_phone_number;
    }

    public String getCurrent_limit() {
        return current_limit;
    }

    public void setCurrent_limit(String current_limit) {
        this.current_limit = current_limit;
    }

    public String getNew_category() {
        return new_category;
    }

    public void setNew_category(String new_category) {
        this.new_category = new_category;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public String getMessage_template_id() {
        return message_template_id;
    }

    public void setMessage_template_id(String message_template_id) {
        this.message_template_id = message_template_id;
    }

    public String getMessage_template_name() {
        return message_template_name;
    }

    public void setMessage_template_name(String message_template_name) {
        this.message_template_name = message_template_name;
    }

    public String getMessage_template_language() {
        return message_template_language;
    }

    public void setMessage_template_language(String message_template_language) {
        this.message_template_language = message_template_language;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getMessaging_product() {
        return messaging_product;
    }

    public void setMessaging_product(String messaging_product) {
        this.messaging_product = messaging_product;
    }

    public Metadata getMetadata() {
        return metadata;
    }

    public void setMetadata(Metadata metadata) {
        this.metadata = metadata;
    }

    public List<Statuses> getStatuses() {
        return statuses;
    }

    @Override
    public String toString() {
        return "Value{" +
                "messaging_product='" + messaging_product + '\'' +
                ", metadata=" + metadata +
                ", statuses=" + statuses +
                ", contacts=" + contacts +
                ", messages=" + messages +
                ", event='" + event + '\'' +
                ", message_template_id='" + message_template_id + '\'' +
                ", message_template_name='" + message_template_name + '\'' +
                ", message_template_language='" + message_template_language + '\'' +
                ", reason='" + reason + '\'' +
                ", new_category='" + new_category + '\'' +
                ", display_phone_number='" + display_phone_number + '\'' +
                ", current_limit='" + current_limit + '\'' +
                '}';
    }

    public void setStatuses(List<Statuses> statuses) {
        this.statuses = statuses;
    }

    public List<Contacts> getContacts() {
        return contacts;
    }

    public void setContacts(List<Contacts> contacts) {
        this.contacts = contacts;
    }

    public List<Messages> getMessages() {
        return messages;
    }

    public void setMessages(List<Messages> messages) {
        this.messages = messages;
    }


}
