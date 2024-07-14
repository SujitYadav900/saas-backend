package com.whatsappbuisness.wsbuissness.CombinePackadge.KarixWaOfficial.KarixDao.KarixIncommingWebhook;


import com.whatsappbuisness.wsbuissness.CombinePackadge.Messages.ButtonMessage.ButtonMessageDao;
import com.whatsappbuisness.wsbuissness.CombinePackadge.Messages.InteractiveMessageDao.InteractiveMessageDao;
import com.whatsappbuisness.wsbuissness.CombinePackadge.Messages.Location.LocationMessageDao;

public class Message {

    private String from;
    private String id;
    private Text text;
    private String to;
    private Video video;
    private Image image;
    private Document document;
    private ButtonMessageDao button;
    private InteractiveMessageDao interactive;
    private LocationMessageDao location;
    private String contentType;
    private String profileName;
    
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
	public Text getText() {
		return text;
	}
	public void setText(Text text) {
		this.text = text;
	}
	public String getTo() {
		return to;
	}
	public void setTo(String to) {
		this.to = to;
	}
	public Video getVideo() {
		return video;
	}
	public void setVideo(Video video) {
		this.video = video;
	}
	public Image getImage() {
		return image;
	}
	public void setImage(Image image) {
		this.image = image;
	}
	public Document getDocument() {
		return document;
	}
	public void setDocument(Document document) {
		this.document = document;
	}
	public ButtonMessageDao getButton() {
		return button;
	}
	public void setButton(ButtonMessageDao button) {
		this.button = button;
	}
	public InteractiveMessageDao getInteractive() {
		return interactive;
	}
	public void setInteractive(InteractiveMessageDao interactive) {
		this.interactive = interactive;
	}
	public LocationMessageDao getLocation() {
		return location;
	}
	public void setLocation(LocationMessageDao location) {
		this.location = location;
	}
	public String getContentType() {
		return contentType;
	}
	public void setContentType(String contentType) {
		this.contentType = contentType;
	}
	public String getProfileName() {
		return profileName;
	}
	public void setProfileName(String profileName) {
		this.profileName = profileName;
	}
	@Override
	public String toString() {
		return "Message [from=" + from + ", id=" + id + ", text=" + text + ", to=" + to + ", video=" + video
				+ ", image=" + image + ", document=" + document + ", button=" + button + ", interactive=" + interactive
				+ ", location=" + location + ", contentType=" + contentType + ", profileName=" + profileName + "]";
	}

}
