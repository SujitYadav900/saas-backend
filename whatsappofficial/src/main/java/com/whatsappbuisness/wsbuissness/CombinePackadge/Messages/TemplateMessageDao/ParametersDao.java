package com.whatsappbuisness.wsbuissness.CombinePackadge.Messages.TemplateMessageDao;
/*
 Author=Supreet Singh
 Date= 04/02/21 5:32 PM
*/

import com.whatsappbuisness.wsbuissness.CombinePackadge.ApiService.Media.MediaDao;
import com.whatsappbuisness.wsbuissness.CombinePackadge.ApiService.Media.MediaInnerDao;
import com.whatsappbuisness.wsbuissness.CombinePackadge.Messages.MediaCommonObj;

import java.io.Serializable;

public class ParametersDao implements Serializable {

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    private String type;
    private String text;

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getPayload() {
        return payload;
    }

    public void setPayload(String payload) {
        this.payload = payload;
    }

    private String caption;
    private String link;
    private String payload;

    public CurrencyDao getCurrency() {
        return currency;
    }

    public void setCurrency(CurrencyDao currency) {
        this.currency = currency;
    }

    public DateTimeDao getDate_time() {
        return date_time;
    }

    public void setDate_time(DateTimeDao date_time) {
        this.date_time = date_time;
    }

    private CurrencyDao currency;
    private DateTimeDao date_time;
    private MediaCommonObj document;
    private MediaCommonObj video;
    private MediaCommonObj image;

    @Override
    public String toString() {
        return "ParametersDao{" +
                "type='" + type + '\'' +
                ", text='" + text + '\'' +
                ", caption='" + caption + '\'' +
                ", link='" + link + '\'' +
                ", payload='" + payload + '\'' +
                ", currency=" + currency +
                ", date_time=" + date_time +
                ", document=" + document +
                ", video=" + video +
                ", image=" + image +
                ", nameOfParams='" + nameOfParams + '\'' +
                '}';
    }

    public String getNameOfParams() {
        return nameOfParams;
    }

    public void setNameOfParams(String nameOfParams) {
        this.nameOfParams = nameOfParams;
    }

    private String nameOfParams;

    public MediaCommonObj getDocument() {
        return document;
    }

    public void setDocument(MediaCommonObj document) {
        this.document = document;
    }

    public MediaCommonObj getVideo() {
        return video;
    }

    public void setVideo(MediaCommonObj video) {
        this.video = video;
    }

    public MediaCommonObj getImage() {
        return image;
    }

    public void setImage(MediaCommonObj image) {
        this.image = image;
    }
}
