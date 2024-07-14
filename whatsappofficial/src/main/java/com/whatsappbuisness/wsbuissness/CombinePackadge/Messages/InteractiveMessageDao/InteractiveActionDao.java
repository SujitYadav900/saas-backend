package com.whatsappbuisness.wsbuissness.CombinePackadge.Messages.InteractiveMessageDao;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InteractiveActionDao implements Serializable {
    private String button;
    private List<Map> buttons;
    private List<Map> sections;

    public String getButton() {
        return button;
    }

    public void setButton(String button) {
        this.button = button;
    }

    public List<Map> getButtons() {
        return buttons;
    }

    public void setButtons(List<Map> buttons) {
        this.buttons = buttons;
    }

    public List<Map> getSections() {
        return sections;
    }

    public void setSections(List<Map> sections) {
        this.sections = sections;
    }

    @Override
    public String toString() {
        return "InteractiveActionDao{" +
                "button='" + button + '\'' +
                ", buttons=" + buttons +
                ", sections=" + sections +
                '}';
    }
}
