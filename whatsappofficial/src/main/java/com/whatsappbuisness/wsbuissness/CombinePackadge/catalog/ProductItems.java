package com.whatsappbuisness.wsbuissness.CombinePackadge.catalog;

import org.hibernate.validator.constraints.UniqueElements;

import java.io.Serializable;

public class ProductItems implements Serializable {
    private String product_retailer_id;
    private String variantsTitle;
    private int quantity;
    private double item_price;
    private String INR;

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getItem_price() {
        return item_price;
    }

    public void setItem_price(double item_price) {
        this.item_price = item_price;
    }

    public String getINR() {
        return INR;
    }

    public void setINR(String INR) {
        this.INR = INR;
    }

    public String getVariantsTitle() {
        return variantsTitle;
    }

    public void setVariantsTitle(String variantsTitle) {
        this.variantsTitle = variantsTitle;
    }

    public ProductItems(String product_retailer_id, String variantsTitle) {
        this.product_retailer_id = product_retailer_id;
        this.variantsTitle = variantsTitle;
    }

    public ProductItems() {
    }

    public String getProduct_retailer_id() {
        return product_retailer_id;
    }

    public void setProduct_retailer_id(String product_retailer_id) {
        this.product_retailer_id = product_retailer_id;
    }

    @Override
    public String toString() {
        return "ProductItems{" +
                "product_retailer_id='" + product_retailer_id + '\'' +
                ", variantsTitle='" + variantsTitle + '\'' +
                ", quantity=" + quantity +
                ", item_price=" + item_price +
                ", INR='" + INR + '\'' +
                '}';
    }
}
