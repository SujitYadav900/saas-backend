package com.example.maxcrm.MaxCrm.Dao;

import java.util.Objects;

public class StageOptionDocument {
    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    private String value;
    private String type;
    private String action;
    private String innerAction;

    public boolean isCanBeManuallyChanged() {
        return canBeManuallyChanged;
    }

    public void setCanBeManuallyChanged(boolean canBeManuallyChanged) {
        this.canBeManuallyChanged = canBeManuallyChanged;
    }

    private boolean canBeManuallyChanged;

    public String getInnerAction() {
        return innerAction;
    }

    public void setInnerAction(String innerAction) {
        this.innerAction = innerAction;
    }

    private String color;


    private boolean selected;

    @Override
    public String toString() {
        return "StageOptionDocument{" +
                "value='" + value + '\'' +
                ", type='" + type + '\'' +
                ", action='" + action + '\'' +
                ", innerAction='" + innerAction + '\'' +
                ", canBeManuallyChanged=" + canBeManuallyChanged +
                ", color='" + color + '\'' +
                ", selected=" + selected +
                ", templateId='" + templateId + '\'' +
                '}';
    }

    public String getTemplateId() {
        return templateId;
    }

    public void setTemplateId(String templateId) {
        this.templateId = templateId;
    }

    private String templateId;

//    @Override
//    public boolean equals(Object o) {
//
//        StageOptionDocument that = (StageOptionDocument) o;
//        return that.value.equalsIgnoreCase(value);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(value, type, action, innerAction, canBeManuallyChanged, color, selected, templateId);
//    }
}
