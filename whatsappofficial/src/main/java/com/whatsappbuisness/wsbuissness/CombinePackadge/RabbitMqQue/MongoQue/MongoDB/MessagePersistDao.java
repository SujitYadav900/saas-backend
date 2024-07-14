package com.whatsappbuisness.wsbuissness.CombinePackadge.RabbitMqQue.MongoQue.MongoDB;
/*      
 Author=Supreet Singh
 Date= 06/02/21 10:45 PM
*/

import com.whatsappbuisness.wsbuissness.CombinePackadge.Messages.MessageDao;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "MessageDocument")
public class MessagePersistDao {



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }




    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getLastMessage() {
        return lastMessage;
    }

    public void setLastMessage(String lastMessage) {
        this.lastMessage = lastMessage;
    }

    public boolean isUnread() {
        return unread;
    }

    public void setUnread(boolean unread) {
        this.unread = unread;
    }

    public int getTotalMessage() {
        return totalMessage;
    }

    public void setTotalMessage(int totalMessage) {
        this.totalMessage = totalMessage;
    }

    public List<MessageDao> getMessages() {
        return messages;
    }

    public void setMessages(List<MessageDao> messages) {
        this.messages = messages;
    }
    @Id
    private String id;


    @Override
    public String toString() {
        return "MessagePersistDao{" +
                "id='" + id + '\'' +


                ", date='" + date + '\'' +
                ", lastMessage='" + lastMessage + '\'' +
                ", unread=" + unread +
                ", totalMessage=" + totalMessage +
                ", messages=" + messages +
                '}';
    }

    public long getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(long lastUpdate) {
        this.lastUpdate = lastUpdate;
    }
    @Indexed
    private long lastUpdate;
    private String date;
    private String lastMessage;
    private boolean unread;
    private int totalMessage;
    private List<MessageDao> messages;


}
