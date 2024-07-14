package com.whatsappbuisness.wsbuissness.CombinePackadge.RabbitMqQue.Webhooks.Webhook;
/*      
 Author=Supreet Singh
 Date= 09/02/21 4:22 PM
*/

public class ErrorWebhookDao {
    @Override
    public String toString() {
        return "ErrorWebhookDao{" +
                "code=" + code +
                ", details='" + details + '\'' +
                ", title='" + title + '\'' +
                '}';
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    private int code;
    private String details;
    private String title;
}
