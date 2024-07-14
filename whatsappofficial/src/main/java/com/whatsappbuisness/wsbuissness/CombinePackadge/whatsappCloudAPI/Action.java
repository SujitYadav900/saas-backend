package com.whatsappbuisness.wsbuissness.CombinePackadge.whatsappCloudAPI;

import java.io.Serializable;
import java.util.List;

public class Action {
    private List<Button> buttons;
    private String button;

    public List<Button> getButtons() {
        return buttons;
    }

    public void setButtons(List<Button> buttons) {
        this.buttons = buttons;
    }

    public void setButton(String button) {
        this.button = button;
    }

    private List<Section> sections;

    public List<Section> getSections() {
        return sections;
    }

    public void setSections(List<Section> sections) {
        this.sections = sections;
    }

    public String getButton() {
        return button;
    }

    @Override
    public String toString() {
        return "Action{" +
                "buttons=" + buttons +
                ", button='" + button + '\'' +
                ", sections=" + sections +
                '}';
    }
}
