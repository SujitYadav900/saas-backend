package com.whatsappbuisness.wsbuissness.CombinePackadge.KarixWaOfficial.KarixDao;
/*
 Author=Ritu Redhu
 Date= 06/01/23 10:00 AM
*/
import java.util.HashMap;

public class MediaTemplate {

    private String templateId;
    private Media media;
    private HashMap<Integer, String> bodyParameterValues;
    private Buttons buttons;

    public String getTemplateId() {
        return templateId;
    }

    public void setTemplateId(String templateId) {
        this.templateId = templateId;
    }

    public Media getMedia() {
        return media;
    }

    public void setMedia(Media media) {
        this.media = media;
    }

    public HashMap<Integer, String> getBodyParameterValues() {
        return bodyParameterValues;
    }

    public void setBodyParameterValues(HashMap<Integer, String> bodyParameterValues) {
        this.bodyParameterValues = bodyParameterValues;
    }

    public Buttons getButtons() {
        return buttons;
    }

    public void setButtons(Buttons buttons) {
        this.buttons = buttons;
    }

    @Override
    public String toString() {
        return "MediaTemplate{" +
                "templateId='" + templateId + '\'' +
                ", media=" + media +
                ", bodyParameterValues=" + bodyParameterValues +
                ", buttons=" + buttons +
                '}';
    }
}
