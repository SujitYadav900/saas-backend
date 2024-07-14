package com.whatsappbuisness.wsbuissness.CombinePackadge.Messages.InteractiveMessageDao;

import com.whatsappbuisness.wsbuissness.CombinePackadge.Messages.DocumentMessage.DocumentMessageDao;
import com.whatsappbuisness.wsbuissness.CombinePackadge.Messages.ImageMessage.ImageMessageDao;
import com.whatsappbuisness.wsbuissness.CombinePackadge.Messages.MessageType;
import com.whatsappbuisness.wsbuissness.CombinePackadge.Messages.VideoMessage.VideoMessageDao;

import java.io.Serializable;

public class InteractiveHeaderDao implements Serializable {
    private MessageType type;
    private ImageMessageDao image;
    private DocumentMessageDao document;
    private VideoMessageDao video;

    public DocumentMessageDao getDocument() {
        return document;
    }

    public void setDocument(DocumentMessageDao document) {
        this.document = document;
    }

    public VideoMessageDao getVideo() {
        return video;
    }

    public void setVideo(VideoMessageDao video) {
        this.video = video;
    }

    public MessageType getType() {
        return type;
    }

    public void setType(MessageType type) {
        this.type = type;
    }

    public ImageMessageDao getImage() {
        return image;
    }

    public void setImage(ImageMessageDao image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "InteractiveHeaderDao{" +
                "type=" + type +
                ", image=" + image +
                ", document=" + document +
                ", video=" + video +
                '}';
    }
}
