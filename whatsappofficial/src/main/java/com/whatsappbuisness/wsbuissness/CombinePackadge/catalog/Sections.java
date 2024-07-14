package com.whatsappbuisness.wsbuissness.CombinePackadge.catalog;

import java.io.Serializable;
import java.util.List;

public class Sections implements Serializable {
    private String title;
    private List<ProductItems> product_items;

    public Sections(String title, List<ProductItems> product_items) {
        this.title = title;
        this.product_items = product_items;
    }

    public Sections() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<ProductItems> getProduct_items() {
        return product_items;
    }

    public void setProduct_items(List<ProductItems> product_items) {
        this.product_items = product_items;
    }

    @Override
    public String toString() {
        return "Sections{" +
                "title='" + title + '\'' +
                ", product_items=" + product_items +
                '}';
    }
}
