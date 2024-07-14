package com.whatsappbuisness.wsbuissness.CombinePackadge.RabbitMqQue.MongoQue.MongoDB;
import com.whatsappbuisness.wsbuissness.CombinePackadge.Messages.MessageStatus;

public class MessageDeliveryStatusDao {

    private String messageId;
    private MessageStatus messageStatus;
    private String dst;

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public MessageStatus getMessageStatus() {
        return messageStatus;
    }

    public void setMessageStatus(MessageStatus messageStatus) {
        this.messageStatus = messageStatus;
    }

    public String getDst() {
        return dst;
    }

    public void setDst(String dst) {
        this.dst = dst;
    }

    @Override
    public String toString() {
        return "MessageDeliveryStatusDao{" +
                "messageId='" + messageId + '\'' +
                ", messageStatus=" + messageStatus +
                ", dst='" + dst + '\'' +
                '}';
    }
}
