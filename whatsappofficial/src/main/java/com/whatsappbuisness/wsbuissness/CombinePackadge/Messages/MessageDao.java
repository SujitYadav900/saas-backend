package com.whatsappbuisness.wsbuissness.CombinePackadge.Messages;
/*
 Author=Supreet Singh
 Date= 04/02/21 4:46 PM
*/

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.Gson;
import com.whatsappbuisness.wsbuissness.CombinePackadge.CountryWisePrice.CountryWiseRateRetDao;
import com.whatsappbuisness.wsbuissness.CombinePackadge.Messages.AudioMessage.AudioMessageDao;
import com.whatsappbuisness.wsbuissness.CombinePackadge.Messages.ButtonMessage.ButtonMessageDao;
import com.whatsappbuisness.wsbuissness.CombinePackadge.Messages.DocumentMessage.DocumentMessageDao;
import com.whatsappbuisness.wsbuissness.CombinePackadge.Messages.ImageMessage.ImageMessageDao;
import com.whatsappbuisness.wsbuissness.CombinePackadge.Messages.InteractiveMessageDao.InteractiveMessageDao;
import com.whatsappbuisness.wsbuissness.CombinePackadge.Messages.Location.LocationMessageDao;
import com.whatsappbuisness.wsbuissness.CombinePackadge.Messages.TemplateMessageDao.TemplateText.TemplateMessageDao;
import com.whatsappbuisness.wsbuissness.CombinePackadge.Messages.TextMessage.TextMessageDao;
import com.whatsappbuisness.wsbuissness.CombinePackadge.Messages.VideoMessage.VideoMessageDao;
import com.whatsappbuisness.wsbuissness.CombinePackadge.Subscription.SubscriptionDao;
import com.whatsappbuisness.wsbuissness.CombinePackadge.Utility.CommonClassReturnWithStatus;
import com.whatsappbuisness.wsbuissness.CombinePackadge.WsUserORBuisness.StatusDaoUsrOrBsn;
import com.whatsappbuisness.wsbuissness.CombinePackadge.WsUserORBuisness.UserOrBuisnessIntiatedDao;
import com.whatsappbuisness.wsbuissness.CombinePackadge.catalog.CatalogMessageDao;
import com.whatsappbuisness.wsbuissness.CombinePackadge.whatsappCloudAPI.CloudAPIWebhook.Context;
import com.whatsappbuisness.wsbuissness.CombinePackadge.whatsappCloudAPI.Interactive;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import javax.persistence.Transient;
import java.io.Serializable;

@Document(collection = "MessageObj")
public class MessageDao implements Serializable,Cloneable {
    @Id
    private String id;
    @Indexed
    private String to;
    private MessageType type;
    private String recipient_type;
    private long campaingId;
    @Transient
    private CountryWiseRateRetDao countryWiseRateRetDao;
    private String countryCode;
    private TextMessageDao text;
    private boolean preview_url;
    private ImageMessageDao image;
    private AudioMessageDao audio;
    private VideoMessageDao video;
    @JsonProperty("document")
    private DocumentMessageDao document;
    private MessageStatus messageStatus;
    private ButtonMessageDao button;
    private Interactive cloudInteractive;
    private CatalogMessageDao catalogMessageDao;
    private String messageContent;

    public String getMessageContent() {
        return messageContent;
    }

    public void setMessageContent(String messageContent) {
        this.messageContent = messageContent;
    }

    public CatalogMessageDao getCatalogMessageDao() {
        return catalogMessageDao;
    }

    public void setCatalogMessageDao(CatalogMessageDao catalogMessageDao) {
        this.catalogMessageDao = catalogMessageDao;
    }


    public MessageDao(String id, String to, MessageType type, String recipient_type, long campaingId, CountryWiseRateRetDao countryWiseRateRetDao, String countryCode, TextMessageDao text, boolean preview_url, ImageMessageDao image, AudioMessageDao audio, VideoMessageDao video, DocumentMessageDao document, MessageStatus messageStatus, ButtonMessageDao button, Interactive cloudInteractive, CatalogMessageDao catalogMessageDao, Context context, String profileName, TemplateMessageDao template, LocationMessageDao location, VoiceMessageDao voice, long accountId, String date, ChatSide chatSide, String messageId, long dateFilterLong, String dlrTime, String viewTime, SubscriptionDao subscriptionDao, int dateFilter, boolean isPanel, MessageOf messageOf, Gateway gatway, String attachementBase64, String mimeType, byte iScheduled, long scheduleTime, InteractiveMessageDao interactive, CommonClassReturnWithStatus<UserOrBuisnessIntiatedDao, StatusDaoUsrOrBsn> commonClassReturnWithStatus) {
        this.id = id;
        this.to = to;
        this.type = type;
        this.recipient_type = recipient_type;
        this.campaingId = campaingId;
        this.countryWiseRateRetDao = countryWiseRateRetDao;
        this.countryCode = countryCode;
        this.text = text;
        this.preview_url = preview_url;
        this.image = image;
        this.audio = audio;
        this.video = video;
        this.document = document;
        this.messageStatus = messageStatus;
        this.button = button;
        this.cloudInteractive = cloudInteractive;
        this.catalogMessageDao = catalogMessageDao;
        this.context = context;
        this.profileName = profileName;
        this.template = template;
        this.location = location;
        this.voice = voice;
        this.accountId = accountId;
        this.date = date;
        this.chatSide = chatSide;
        this.messageId = messageId;
        this.dateFilterLong = dateFilterLong;
        this.dlrTime = dlrTime;
        this.viewTime = viewTime;
        this.subscriptionDao = subscriptionDao;
        this.dateFilter = dateFilter;
        this.isPanel = isPanel;
        this.messageOf = messageOf;
        this.gatway = gatway;
        this.attachementBase64 = attachementBase64;
        this.mimeType = mimeType;
        this.iScheduled = iScheduled;
        this.scheduleTime = scheduleTime;
        this.interactive = interactive;
        this.commonClassReturnWithStatus = commonClassReturnWithStatus;
    }

    private Context context;

    private String profileName;

    public String getProfileName() {
        return profileName;
    }

    public void setProfileName(String profileName) {
        this.profileName = profileName;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }


    public Interactive getCloudInteractive() {
        return cloudInteractive;
    }

    public void setCloudInteractive(Interactive cloudInteractive) {
        this.cloudInteractive = cloudInteractive;
    }

    private TemplateMessageDao template;
    /*    private ContactsMessageDao contacts;*/
    private LocationMessageDao location;
    private VoiceMessageDao voice;
    private long accountId;
    private String date;
    private ChatSide chatSide;
    private String messageId;
    private long dateFilterLong;
    private String dlrTime;
    private String viewTime;
    @Transient
    private SubscriptionDao subscriptionDao;
    private int dateFilter;
    private boolean isPanel;
    private MessageOf messageOf;
    private Gateway gatway;
    private String attachementBase64;
    private String mimeType;

    private byte iScheduled;
    private long scheduleTime;


    public byte getiScheduled() {
        return iScheduled;
    }

    public void setiScheduled(byte iScheduled) {
        this.iScheduled = iScheduled;
    }

    public long getScheduleTime() {
        return scheduleTime;
    }

    public void setScheduleTime(long scheduleTime) {
        this.scheduleTime = scheduleTime;
    }

    private InteractiveMessageDao interactive;
    private CommonClassReturnWithStatus<UserOrBuisnessIntiatedDao, StatusDaoUsrOrBsn> commonClassReturnWithStatus;


    public CommonClassReturnWithStatus<UserOrBuisnessIntiatedDao, StatusDaoUsrOrBsn> getCommonClassReturnWithStatus() {
        return commonClassReturnWithStatus;
    }

    public void setCommonClassReturnWithStatus(CommonClassReturnWithStatus<UserOrBuisnessIntiatedDao, StatusDaoUsrOrBsn> commonClassReturnWithStatus) {
        this.commonClassReturnWithStatus = commonClassReturnWithStatus;
    }

    public String getMimeType() {
        return mimeType;
    }

    public void setMimeType(String mimeType) {
        this.mimeType = mimeType;
    }

    public String getAttachementBase64() {
        return attachementBase64;
    }

    public void setAttachementBase64(String attachementBase64) {
        this.attachementBase64 = attachementBase64;
    }

    public InteractiveMessageDao getInteractive() {
        return interactive;
    }

    public void setInteractive(InteractiveMessageDao interactive) {
        this.interactive = interactive;
    }

    public MessageDao() {
        this.messageOf = MessageOf.WAOF;
    }

    public String getCountryCode() {

        if (StringUtils.isEmpty(this.countryCode)) {
            return "IND";
        } else {
            return countryCode;
        }

    }


    public VoiceMessageDao getVoice() {
        return voice;
    }

    public void setVoice(VoiceMessageDao voice) {
        this.voice = voice;
    }

    public Gateway getGatway() {
        return gatway;
    }

    public void setGatway(Gateway gatway) {
        this.gatway = gatway;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public long getCampaingId() {
        return campaingId;
    }

    public void setCampaingId(long campaingId) {
        this.campaingId = campaingId;
    }

    public long getDateFilterLong() {
        return dateFilterLong;
    }

    public void setDateFilterLong(long dateFilterLong) {
        this.dateFilterLong = dateFilterLong;
    }

    public CountryWiseRateRetDao getCountryWiseRateRetDao() {
        return countryWiseRateRetDao;
    }

    public void setCountryWiseRateRetDao(CountryWiseRateRetDao countryWiseRateRetDao) {
        this.countryWiseRateRetDao = countryWiseRateRetDao;
    }

    public String getDlrTime() {
        return dlrTime;
    }

    public void setDlrTime(String dlrTime) {
        this.dlrTime = dlrTime;
    }

    public String getViewTime() {
        return viewTime;
    }

    public void setViewTime(String viewTime) {
        this.viewTime = viewTime;
    }

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public SubscriptionDao getSubscriptionDao() {
        return subscriptionDao;
    }

    public void setSubscriptionDao(SubscriptionDao subscriptionDao) {
        this.subscriptionDao = subscriptionDao;
    }

    public int getDateFilter() {
        return dateFilter;
    }

    public void setDateFilter(int dateFilter) {
        this.dateFilter = dateFilter;
    }

/*    public ContactsMessageDao getContacts() {
        return contacts;
    }

    public void setContacts(ContactsMessageDao contacts) {
        this.contacts = contacts;
    }*/

    public TemplateMessageDao getTemplate() {
        return template;
    }

    public void setTemplate(TemplateMessageDao template) {
        this.template = template;
    }

    public ButtonMessageDao getButton() {
        return button;
    }

    public void setButton(ButtonMessageDao button) {
        this.button = button;
    }

    public LocationMessageDao getLocation() {
        return location;
    }

    public void setLocation(LocationMessageDao location) {
        this.location = location;
    }

    public MessageStatus getMessageStatus() {
        return messageStatus;
    }

    public void setMessageStatus(MessageStatus messageStatus) {
        this.messageStatus = messageStatus;
    }

    public long getAccountId() {
        return accountId;
    }

    public void setAccountId(long accountId) {
        this.accountId = accountId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public ChatSide getChatSide() {
        return chatSide;
    }

    public void setChatSide(ChatSide chatSide) {
        this.chatSide = chatSide;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public MessageType getType() {
        return type;
    }

    public void setType(MessageType type) {
        this.type = type;
    }

    public String getRecipient_type() {
        return recipient_type;
    }

    public void setRecipient_type(String recipient_type) {
        this.recipient_type = recipient_type;
    }

    public TextMessageDao getText() {
        return text;
    }

    public void setText(TextMessageDao text) {
        this.text = text;
    }

    public boolean isPreview_url() {
        return preview_url;
    }

    public void setPreview_url(boolean preview_url) {
        this.preview_url = preview_url;
    }

    public ImageMessageDao getImage() {
        return image;
    }

    public void setImage(ImageMessageDao image) {
        this.image = image;
    }

    public AudioMessageDao getAudio() {
        return audio;
    }

    public void setAudio(AudioMessageDao audio) {
        this.audio = audio;
    }

    public VideoMessageDao getVideo() {
        return video;
    }

    public void setVideo(VideoMessageDao video) {
        this.video = video;
    }

    public DocumentMessageDao getDocument() {
        return document;
    }

    public void setDocument(DocumentMessageDao document) {
        this.document = document;
    }

    public String toJson() {
        return new Gson().toJson(this);
    }

    public boolean isPanel() {
        return isPanel;
    }

    public void setPanel(boolean panel) {
        isPanel = panel;
    }

    public MessageOf getMessageOf() {
        return messageOf;
    }

    public void setMessageOf(MessageOf messageOf) {
        this.messageOf = messageOf;
    }

    @Override
    public String toString() {
        return "MessageDao{" +
                "id='" + id + '\'' +
                ", to='" + to + '\'' +
                ", type=" + type +
                ", recipient_type='" + recipient_type + '\'' +
                ", campaingId=" + campaingId +
                ", countryWiseRateRetDao=" + countryWiseRateRetDao +
                ", countryCode='" + countryCode + '\'' +
                ", text=" + text +
                ", preview_url=" + preview_url +
                ", image=" + image +
                ", audio=" + audio +
                ", video=" + video +
                ", document=" + document +
                ", messageStatus=" + messageStatus +
                ", button=" + button +
                ", cloudInteractive=" + cloudInteractive +
                ", catalogMessageDao=" + catalogMessageDao +
                ", messageContent='" + messageContent + '\'' +
                ", context=" + context +
                ", profileName='" + profileName + '\'' +
                ", template=" + template +
                ", location=" + location +
                ", voice=" + voice +
                ", accountId=" + accountId +
                ", date='" + date + '\'' +
                ", chatSide=" + chatSide +
                ", messageId='" + messageId + '\'' +
                ", dateFilterLong=" + dateFilterLong +
                ", dlrTime='" + dlrTime + '\'' +
                ", viewTime='" + viewTime + '\'' +
                ", subscriptionDao=" + subscriptionDao +
                ", dateFilter=" + dateFilter +
                ", isPanel=" + isPanel +
                ", messageOf=" + messageOf +
                ", gatway=" + gatway +
                ", attachementBase64='" + attachementBase64 + '\'' +
                ", mimeType='" + mimeType + '\'' +
                ", iScheduled=" + iScheduled +
                ", scheduleTime=" + scheduleTime +
                ", interactive=" + interactive +
                ", commonClassReturnWithStatus=" + commonClassReturnWithStatus +
                '}';
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
