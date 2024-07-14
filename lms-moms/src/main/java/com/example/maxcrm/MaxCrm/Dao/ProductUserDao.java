package com.example.maxcrm.MaxCrm.Dao;

import javax.persistence.*;

@Entity
@Table(name="Tbl_Product_user",uniqueConstraints={@UniqueConstraint(name = "unqproductuser",columnNames = {"product_id","user_id"})})
public class ProductUserDao {
    @Id
    @GeneratedValue
    private long id;
    private long product_id;
    private long user_id;
    @Transient
    private String name;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getProduct_id() {
        return product_id;
    }

    public void setProduct_id(long product_id) {
        this.product_id = product_id;
    }

    public long getUser_id() {
        return user_id;
    }

    public void setUser_id(long user_id) {
        this.user_id = user_id;
    }

    @Override
    public String toString() {
        return "LeadTypeUser{" +
                "id=" + id +
                ", lead_type_id=" + product_id +
                ", user_id=" + user_id +
                ", name='" + name + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
