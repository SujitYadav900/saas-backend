package com.example.maxcrm.MaxCrm.Dao;

import javax.persistence.*;

@Entity
@Table(name="Tbl_ProductMaster",uniqueConstraints = {@UniqueConstraint(name="unqproduct", columnNames = "name")})
public class ProductMasterDao {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;
    private String productcode;
    private String description;
    private byte status;
    private double price;



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProductcode() {
        return productcode;
    }

    public void setProductcode(String productcode) {
        this.productcode = productcode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public byte getStatus() {
        return status;
    }

    public void setStatus(byte status) {
        this.status = status;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "ProductMasterDao{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", productcode='" + productcode + '\'' +
                ", description='" + description + '\'' +
                ", status=" + status +
                ", price=" + price +
                '}';
    }
}
