package com.whatsappbuisness.wsbuissness.CombinePackadge.catalog;

import javax.persistence.Id;
import javax.persistence.UniqueConstraint;
import java.io.Serializable;
import java.util.List;

public class Action implements Serializable {

    private String catalog_id;

    private List<Sections> sections;

    public Action(String catalog_id, List<Sections> sections) {
        this.catalog_id = catalog_id;
        this.sections = sections;
    }

    public Action() {
    }

    public String getCatalog_id() {
        return catalog_id;
    }

    public void setCatalog_id(String catalog_id) {
        this.catalog_id = catalog_id;
    }

    public List<Sections> getSections() {
        return sections;
    }

    public void setSections(List<Sections> sections) {
        this.sections = sections;
    }

    @Override
    public String toString() {
        return "Action{" +
                "catalog_id='" + catalog_id + '\'' +
                ", sections=" + sections +
                '}';
    }
}
