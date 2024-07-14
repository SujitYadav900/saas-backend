package com.whatsappbuisness.wsbuissness.CombinePackadge.RabbitMqQue.Webhooks.Webhook;
/*      
 Author=Supreet Singh
 Date= 09/02/21 3:46 PM
*/

import com.whatsappbuisness.wsbuissness.CombinePackadge.Messages.AudioMessage.AudioMessageDao;
import com.whatsappbuisness.wsbuissness.CombinePackadge.Messages.ButtonMessage.ButtonMessageDao;
import com.whatsappbuisness.wsbuissness.CombinePackadge.Messages.ChatSide;
import com.whatsappbuisness.wsbuissness.CombinePackadge.Messages.ContactsMessageDao.ContactsMessageDao;
import com.whatsappbuisness.wsbuissness.CombinePackadge.Messages.DocumentMessage.DocumentMessageDao;
import com.whatsappbuisness.wsbuissness.CombinePackadge.Messages.ImageMessage.ImageMessageDao;
import com.whatsappbuisness.wsbuissness.CombinePackadge.Messages.Location.LocationMessageDao;
import com.whatsappbuisness.wsbuissness.CombinePackadge.Messages.MessageDao;
import com.whatsappbuisness.wsbuissness.CombinePackadge.Messages.MessageStatus;
import com.whatsappbuisness.wsbuissness.CombinePackadge.Messages.MessageType;
import com.whatsappbuisness.wsbuissness.CombinePackadge.Messages.TextMessage.TextMessageDao;
import com.whatsappbuisness.wsbuissness.CombinePackadge.Messages.VideoMessage.VideoMessageDao;

public class WebhookMessasgeDao {

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

    public MessageType getType() {
        return type;
    }

    public void setType(MessageType type) {
        this.type = type;
    }

    public TextMessageDao getText() {
        return text;
    }

    public void setText(TextMessageDao text) {
        this.text = text;
    }

    public ImageMessageDao getImage() {
        return image;
    }

    public void setImage(ImageMessageDao image) {
        this.image = image;
    }

    public VideoMessageDao getVideo() {
        return video;
    }

    public void setVideo(VideoMessageDao video) {
        this.video = video;
    }

    public LocationMessageDao getLocation() {
        return location;
    }

    public void setLocation(LocationMessageDao location) {
        this.location = location;
    }

    public AudioMessageDao getAudio() {
        return audio;
    }

    public void setAudio(AudioMessageDao audio) {
        this.audio = audio;
    }

    public AudioMessageDao getVoice() {
        return voice;
    }

    public void setVoice(AudioMessageDao voice) {
        this.voice = voice;
    }

    public DocumentMessageDao getDocument() {
        return document;
    }

    public void setDocument(DocumentMessageDao document) {
        this.document = document;
    }

    public ContextWebhookDao getContext() {
        return context;
    }

    public void setContext(ContextWebhookDao context) {
        this.context = context;
    }

    public ButtonMessageDao getButton() {
        return button;
    }

    public void setButton(ButtonMessageDao button) {
        this.button = button;
    }

    private String from;
    private String id;
    private MessageType type;
    private TextMessageDao text;
    private ImageMessageDao image;
    private VideoMessageDao video;
    private LocationMessageDao location;
    private AudioMessageDao audio;
    private AudioMessageDao voice;
    private DocumentMessageDao document;
    private ContextWebhookDao context;
    private ButtonMessageDao button;
    private ErrorWebhookDao errors;

    @Override
    public String toString() {
        return "WebhookMessasgeDao{" +
                "from='" + from + '\'' +
                ", id='" + id + '\'' +
                ", type=" + type +
                ", text=" + text +
                ", image=" + image +
                ", video=" + video +
                ", location=" + location +
                ", audio=" + audio +
                ", voice=" + voice +
                ", document=" + document +
                ", context=" + context +
                ", button=" + button +
                ", errors=" + errors +
                ", contacts=" + contacts +
                '}';
    }

    public ContactsMessageDao getContacts() {
        return contacts;
    }

    public void setContacts(ContactsMessageDao contacts) {
        this.contacts = contacts;
    }

    private ContactsMessageDao contacts;

    public ErrorWebhookDao getErrors() {
        return errors;
    }

    public void setErrors(ErrorWebhookDao errors) {
        this.errors = errors;
    }

    public MessageDao toMessageDao(String date,int accountId)
    {
        MessageDao messageDao=new MessageDao();
        messageDao.setTo(from);
        messageDao.setMessageStatus(MessageStatus.VIEW);
        messageDao.setDate(date);
        messageDao.setChatSide(ChatSide.Client);
        messageDao.setId(id);
        messageDao.setAccountId(accountId);
        messageDao.setType(type);
        switch (type)
        {
            case text:
                messageDao.setText(text);
                break;
            case audio:
                messageDao.setAudio(audio);
                break;
            case location:
                messageDao.setLocation(location);
                break;
            case button:
                messageDao.setButton(button);
                break;
            case document:
                messageDao.setDocument(document);
                break;


            case video:
                messageDao.setVideo(video);
                break;
            case image:
                messageDao.setImage(image);
                break;
            case voice:
                messageDao.setAudio(voice);
                break;

            default:
                break;


        }

        return messageDao;

    }
}
