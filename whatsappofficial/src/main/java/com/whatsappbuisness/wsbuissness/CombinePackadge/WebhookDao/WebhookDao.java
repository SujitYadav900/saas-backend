package com.whatsappbuisness.wsbuissness.CombinePackadge.WebhookDao;
/*
 Author=Supreet Singh
 Date= 12/03/21 5:25 PM
*/

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Tbl_Webhook" )
public class WebhookDao {

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public boolean isHasWebhook() {
        return hasWebhook;
    }

    public void setHasWebhook(boolean hasWebhook) {
        this.hasWebhook = hasWebhook;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public boolean isDlrUpdates() {
        return dlrUpdates;
    }

    public void setDlrUpdates(boolean dlrUpdates) {
        this.dlrUpdates = dlrUpdates;
    }

    public boolean isMsgUpdates() {
        return msgUpdates;
    }

    public void setMsgUpdates(boolean msgUpdates) {
        this.msgUpdates = msgUpdates;
    }
    @Id
    private long id;
    private boolean hasWebhook;
    private String url;
    private boolean dlrUpdates;
    private boolean msgUpdates;

    @Override
    public String toString() {
        return "WebhookDao{" +
                "id=" + id +
                ", hasWebhook=" + hasWebhook +
                ", url='" + url + '\'' +
                ", dlrUpdates=" + dlrUpdates +
                ", msgUpdates=" + msgUpdates +
                '}';
    }
}
