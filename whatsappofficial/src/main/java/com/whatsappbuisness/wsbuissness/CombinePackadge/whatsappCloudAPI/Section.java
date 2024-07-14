package com.whatsappbuisness.wsbuissness.CombinePackadge.whatsappCloudAPI;

import java.io.Serializable;
import java.util.List;

public class Section {

    private String title;
    private List<Rows> rows;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Rows> getRows() {
        return rows;
    }

    public void setRows(List<Rows> rows) {
        this.rows = rows;
    }

    @Override
    public String toString() {
        return "Section{" +
                "title='" + title + '\'' +
                ", rows=" + rows +
                '}';
    }
}
