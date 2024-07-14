package com.whatsappbuisness.wsbuissness.CombinePackadge.RabbitMqQue.Webhooks.Webhook;
/*      
 Author=Supreet Singh
 Date= 09/02/21 4:17 PM
*/

public class ContextWebhookDao {
    @Override
    public String toString() {
        return "ContextWebhookDao{" +
                "from='" + from + '\'' +
                ", id='" + id + '\'' +
                '}';
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    private String from;
    private String id;
}
