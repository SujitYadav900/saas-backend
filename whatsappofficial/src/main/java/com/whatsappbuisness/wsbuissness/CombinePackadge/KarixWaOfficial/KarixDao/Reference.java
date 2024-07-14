package com.whatsappbuisness.wsbuissness.CombinePackadge.KarixWaOfficial.KarixDao;
/*
 Author=Ritu Redhu
 Date= 06/01/23 10:00 AM
*/
public class    Reference {

    private String cust_ref;
    private String messageTag1;
    private String conversationId;

    public String getCust_ref() {
        return cust_ref;
    }

    public void setCust_ref(String cust_ref) {
        this.cust_ref = cust_ref;
    }

    public String getMessageTag1() {
        return messageTag1;
    }

    public void setMessageTag1(String messageTag1) {
        this.messageTag1 = messageTag1;
    }

    public String getConversationId() {
        return conversationId;
    }

    public void setConversationId(String conversationId) {
        this.conversationId = conversationId;
    }

    @Override
    public String toString() {
        return "Reference{" +
                "cust_ref='" + cust_ref + '\'' +
                ", messageTag1='" + messageTag1 + '\'' +
                ", conversationId='" + conversationId + '\'' +
                '}';
    }
}
