package com.example.maxcrm.MaxCrm.Dao;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Tbl_Country")
public class CountryDao {
    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public byte getStatus() {
        return status;
    }

    public void setStatus(byte status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "CountryDao{" +
                "country='" + country + '\'' +
                ", status=" + status +
                '}';
    }
    @Id
    private String country;
    private byte status;
}
