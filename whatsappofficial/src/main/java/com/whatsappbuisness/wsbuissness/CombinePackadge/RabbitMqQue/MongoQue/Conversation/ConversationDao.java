package com.whatsappbuisness.wsbuissness.CombinePackadge.RabbitMqQue.MongoQue.Conversation;
/*
 Author=Supreet Singh
 Date= 10/03/21 12:32 PM
*/

import com.whatsappbuisness.wsbuissness.CombinePackadge.Messages.MessageType;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;

@Document(collection = "MessageConversation")
public class ConversationDao {

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isUnread() {
        return unread;
    }

    public void setUnread(boolean unread) {
        this.unread = unread;
    }

    public MessageType getLastMessageType() {
        return lastMessageType;
    }

    public void setLastMessageType(MessageType lastMessageType) {
        this.lastMessageType = lastMessageType;
    }

    public int getTotalMessage() {
        return totalMessage;
    }

    public void setTotalMessage(int totalMessage) {
        this.totalMessage = totalMessage;
    }



    public String getLastMessage() {
        return lastMessage;
    }

    public void setLastMessage(String lastMessage) {
        this.lastMessage = lastMessage;
    }

    @Id
    private String id;
    private boolean unread;
    private boolean isSaved;
    private String contactName;

    @Override
    public String toString() {
        return "ConversationDao{" +
                "id='" + id + '\'' +
                ", unread=" + unread +
                ", isSaved=" + isSaved +
                ", contactName='" + contactName + '\'' +
                ", lastMessageType=" + lastMessageType +
                ", totalMessage=" + totalMessage +
                ", unreadMessage=" + unreadMessage +
                ", lastMessageTime=" + lastMessageTime +
                ", lastMessage='" + lastMessage + '\'' +
                '}';
    }

    public boolean isSaved() {
        return isSaved;
    }

    public void setSaved(boolean saved) {
        isSaved = saved;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    private MessageType lastMessageType;
    private int totalMessage;
    private int unreadMessage;

    public int getUnreadMessage() {
        return unreadMessage;
    }

    public void setUnreadMessage(int unreadMessage) {
        this.unreadMessage = unreadMessage;
    }

    public long getLastMessageTime() {
        return lastMessageTime;
    }

    public void setLastMessageTime(long lastMessageTime) {
        this.lastMessageTime = lastMessageTime;
    }

    @Indexed
    private long lastMessageTime;

    private String lastMessage;



}
