package com.whatsappbuisness.wsbuissness.CombinePackadge.whatsappCloudAPI.CloudAPIWebhook;

import com.whatsappbuisness.wsbuissness.CombinePackadge.Messages.ButtonMessage.ButtonMessageDao;
import com.whatsappbuisness.wsbuissness.CombinePackadge.Messages.InteractiveMessageDao.InteractiveMessageDao;
import com.whatsappbuisness.wsbuissness.CombinePackadge.Messages.Location.LocationMessageDao;
import com.whatsappbuisness.wsbuissness.CombinePackadge.WhatsappCloudTemplateMaster.Buttons;
import com.whatsappbuisness.wsbuissness.CombinePackadge.catalog.catalogIncomming.Order;
import com.whatsappbuisness.wsbuissness.CombinePackadge.whatsappCloudAPI.Text;

public class Messages {

    private String from;
    private String type;
    private String timestamp;
    private String id;

    private Context context;

    private Text text;

    private Image image;

    private Document document;

    private Audio audio;

    private Video video;
    private ButtonMessageDao button;
    private LocationMessageDao location;


    private InteractiveMessageDao interactive;
    private Order order;

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public LocationMessageDao getLocation() {
        return location;
    }

    public void setLocation(LocationMessageDao location) {
        this.location = location;
    }

    public ButtonMessageDao getButton() {
        return button;
    }

    public void setButton(ButtonMessageDao button) {
        this.button = button;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public InteractiveMessageDao getInteractive() {
        return interactive;
    }

    public void setInteractive(InteractiveMessageDao interactive) {
        this.interactive = interactive;
    }

    public Video getVideo() {
        return video;
    }

    public void setVideo(Video video) {
        this.video = video;
    }

    public Audio getAudio() {
        return audio;
    }

    public void setAudio(Audio audio) {
        this.audio = audio;
    }

    public Document getDocument() {
        return document;
    }

    public void setDocument(Document document) {
        this.document = document;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Text getText() {
        return text;
    }

    public void setText(Text text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "Messages{" +
                "from='" + from + '\'' +
                ", type='" + type + '\'' +
                ", timestamp='" + timestamp + '\'' +
                ", id='" + id + '\'' +
                ", context=" + context +
                ", text=" + text +
                ", image=" + image +
                ", document=" + document +
                ", audio=" + audio +
                ", video=" + video +
                ", button=" + button +
                ", location=" + location +
                ", interactive=" + interactive +
                ", order=" + order +
                '}';
    }
}
