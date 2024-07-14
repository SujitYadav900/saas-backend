package com.whatsappbuisness.wsbuissness.CombinePackadge.whatsappCloudAPI;

import java.io.Serializable;
import java.util.List;

public class Template {

    private String name;
    private Language language;

    private List<Components> components;

    public List<Components> getComponents() {
        return components;
    }

    public void setComponents(List<Components> components) {
        this.components = components;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

    @Override
    public String toString() {
        return "Template{" +
                "name='" + name + '\'' +
                ", language=" + language +
                ", components=" + components +
                '}';
    }
}
