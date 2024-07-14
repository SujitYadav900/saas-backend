package com.whatsappbuisness.wsbuissness.CombinePackadge.catalog.catalogIncomming;


import java.util.List;

public class Order {
    private String catalog_id;
    private String text;
    private List<ProductItem> product_items;


    public Order() {

    }

    public Order(String catalog_id, String text, List<ProductItem> product_items) {
        this.catalog_id = catalog_id;
        this.text = text;
        this.product_items = product_items;
    }

    public String getCatalog_id() {
        return catalog_id;
    }

    public void setCatalog_id(String catalog_id) {
        this.catalog_id = catalog_id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<ProductItem> getProduct_items() {
        return product_items;
    }

    public void setProduct_items(List<ProductItem> product_items) {
        this.product_items = product_items;
    }

    @Override
    public String toString() {
        return "Order{" +
                "catalog_id='" + catalog_id + '\'' +
                ", text='" + text + '\'' +
                ", product_items=" + product_items +
                '}';
    }
}
