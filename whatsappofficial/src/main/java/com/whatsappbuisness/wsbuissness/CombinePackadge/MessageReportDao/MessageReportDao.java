package com.whatsappbuisness.wsbuissness.CombinePackadge.MessageReportDao;
/*
 Author=Supreet Singh
 Date= 09/03/21 1:57 PM
*/

import com.whatsappbuisness.wsbuissness.CombinePackadge.Messages.MessageStatus;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Tbl_Day_Wise_Report" )
public class MessageReportDao {
    @Id
    private long id;
    private int sessionSent;
    private int sessionDlv;
    private int sessionViewed;
    private int sessionInvalid_dst;
    private int sessionExpired;

    private int sessionFailed;
    private int templateSent;
    private int templateDlv;
    private int templateViewed;
    private int templateInvalid_dst;
    private int templateFailed;

    public int getIncomingMsg() {
        return incomingMsg;
    }

    public void setIncomingMsg(int incomingMsg) {
        this.incomingMsg = incomingMsg;
    }

    private int incomingMsg;

    public int getSessionExpired() {
        return sessionExpired;
    }

    public void setSessionExpired(int sessionExpired) {
        this.sessionExpired = sessionExpired;
    }

    public int getTemplateExpired() {
        return templateExpired;
    }

    public void setTemplateExpired(int templateExpired) {
        this.templateExpired = templateExpired;
    }

    private int templateExpired;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getSessionSent() {
        return sessionSent;
    }

    public void setSessionSent(int sessionSent) {
        this.sessionSent = sessionSent;
    }

    public int getSessionDlv() {
        return sessionDlv;
    }

    public void setSessionDlv(int sessionDlv) {
        this.sessionDlv = sessionDlv;
    }

    public int getSessionViewed() {
        return sessionViewed;
    }

    public void setSessionViewed(int sessionViewed) {
        this.sessionViewed = sessionViewed;
    }

    public int getSessionInvalid_dst() {
        return sessionInvalid_dst;
    }

    public void setSessionInvalid_dst(int sessionInvalid_dst) {
        this.sessionInvalid_dst = sessionInvalid_dst;
    }

    public int getSessionFailed() {
        return sessionFailed;
    }

    public void setSessionFailed(int sessionFailed) {
        this.sessionFailed = sessionFailed;
    }

    public int getTemplateSent() {
        return templateSent;
    }

    public void setTemplateSent(int templateSent) {
        this.templateSent = templateSent;
    }

    public int getTemplateDlv() {
        return templateDlv;
    }

    public void setTemplateDlv(int templateDlv) {
        this.templateDlv = templateDlv;
    }

    public int getTemplateViewed() {
        return templateViewed;
    }

    public void setTemplateViewed(int templateViewed) {
        this.templateViewed = templateViewed;
    }

    public int getTemplateInvalid_dst() {
        return templateInvalid_dst;
    }

    public void setTemplateInvalid_dst(int templateInvalid_dst) {
        this.templateInvalid_dst = templateInvalid_dst;
    }

    public int getTemplateFailed() {
        return templateFailed;
    }

    public void setTemplateFailed(int templateFailed) {
        this.templateFailed = templateFailed;
    }

    public int getDateFilter() {
        return dateFilter;
    }

    public void setDateFilter(int dateFilter) {
        this.dateFilter = dateFilter;
    }

    public boolean isPicked() {
        return picked;
    }

    public void setPicked(boolean picked) {
        this.picked = picked;
    }

    private int dateFilter;
    private boolean picked;

    public String getSubscriptionId() {
        return subscriptionId;
    }

    public void setSubscriptionId(String subscriptionId) {
        this.subscriptionId = subscriptionId;
    }

    private String subscriptionId;


    @Override
    public String toString() {
        return "MessageReportDao{" +
                "id=" + id +
                ", sessionSent=" + sessionSent +
                ", sessionDlv=" + sessionDlv +
                ", sessionViewed=" + sessionViewed +
                ", sessionInvalid_dst=" + sessionInvalid_dst +
                ", sessionExpired=" + sessionExpired +
                ", sessionFailed=" + sessionFailed +
                ", templateSent=" + templateSent +
                ", templateDlv=" + templateDlv +
                ", templateViewed=" + templateViewed +
                ", templateInvalid_dst=" + templateInvalid_dst +
                ", templateFailed=" + templateFailed +
                ", incomingMsg=" + incomingMsg +
                ", templateExpired=" + templateExpired +
                ", dateFilter=" + dateFilter +
                ", picked=" + picked +
                ", subscriptionId='" + subscriptionId + '\'' +
                '}';
    }
}
