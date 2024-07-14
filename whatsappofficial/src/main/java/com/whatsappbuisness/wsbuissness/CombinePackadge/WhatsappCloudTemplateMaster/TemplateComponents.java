package com.whatsappbuisness.wsbuissness.CombinePackadge.WhatsappCloudTemplateMaster;

import java.io.Serializable;
import java.util.List;

public class TemplateComponents implements Serializable {

    private String type;
    private String text;
    private String format;
    private Example example;
    private List<Buttons> buttons;

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

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public Example getExample() {
        return example;
    }

    public void setExample(Example example) {
        this.example = example;
    }

    public List<Buttons> getButtons() {
        return buttons;
    }

    public void setButtons(List<Buttons> buttons) {
        this.buttons = buttons;
    }

    @Override
    public String toString() {
        return "TemplateComponents{" +
                "type='" + type + '\'' +
                ", text='" + text + '\'' +
                ", format='" + format + '\'' +
                ", example=" + example +
                ", buttons=" + buttons +
                '}';
    }
}
