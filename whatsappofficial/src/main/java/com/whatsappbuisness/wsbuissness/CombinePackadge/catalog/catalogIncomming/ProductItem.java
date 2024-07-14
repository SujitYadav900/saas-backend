package com.whatsappbuisness.wsbuissness.CombinePackadge.catalog.catalogIncomming;

public class ProductItem {
    private String product_retailer_id;
    private String quantity;
    private String item_price;
    private String currency;

    public ProductItem() {
    }

    public ProductItem(String product_retailer_id, String quantity, String item_price, String currency) {


        this.product_retailer_id = product_retailer_id;
        this.quantity = quantity;
        this.item_price = item_price;
        this.currency = currency;
    }

    public String getProduct_retailer_id() {
        return product_retailer_id;
    }

    public void setProduct_retailer_id(String product_retailer_id) {
        this.product_retailer_id = product_retailer_id;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getItem_price() {
        return item_price;
    }

    public void setItem_price(String item_price) {
        this.item_price = item_price;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    @Override
    public String toString() {
        return "ProductItem{" +
                "product_retailer_id='" + product_retailer_id + '\'' +
                ", quantity='" + quantity + '\'' +
                ", item_price='" + item_price + '\'' +
                ", currency='" + currency + '\'' +
                '}';
    }
}
