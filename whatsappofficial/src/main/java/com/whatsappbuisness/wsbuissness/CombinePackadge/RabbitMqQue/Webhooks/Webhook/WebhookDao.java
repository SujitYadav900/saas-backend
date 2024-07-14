package com.whatsappbuisness.wsbuissness.CombinePackadge.RabbitMqQue.Webhooks.Webhook;
/*
 Author=Supreet Singh
 Date= 09/02/21 3:41 PM
*/



import com.whatsappbuisness.wsbuissness.CombinePackadge.Messages.MessageDao;
import com.whatsappbuisness.wsbuissness.CombinePackadge.Messages.MessageType;

import java.io.Serializable;
import java.util.List;

public class WebhookDao implements Serializable {
    public MessageDao getMsg() {
        return msg;
    }

    public void setMsg(MessageDao msg) {
        this.msg = msg;
    }

    public DlrUpdatesDao getDlr() {
        return dlr;
    }

    public void setDlr(DlrUpdatesDao dlr) {
        this.dlr = dlr;
    }

    public WebhookDao(MessageDao msg, DlrUpdatesDao dlr,String url) {
        this.msg = msg;
        this.dlr = dlr;
        this.webhookUrl=url;
    }

    private MessageDao msg;
    private DlrUpdatesDao dlr;

    @Override
    public String toString() {
        return "WebhookDao{" +
                "msg=" + msg +
                ", dlr=" + dlr +
                ", webhookUrl='" + webhookUrl + '\'' +
                '}';
    }

    public String getWebhookUrl() {
        return webhookUrl;
    }

    public void setWebhookUrl(String webhookUrl) {
        this.webhookUrl = webhookUrl;
    }

    private String webhookUrl;



}
