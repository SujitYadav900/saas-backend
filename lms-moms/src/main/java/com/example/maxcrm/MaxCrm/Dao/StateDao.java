package com.example.maxcrm.MaxCrm.Dao;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;

@Entity
@Table(name = "Tbl_State",   indexes = {@Index(name = "countryIndex", columnList = "country")})
public class StateDao {
    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

    public byte getStatus() {
        return status;
    }

    public void setStatus(byte status) {
        this.status = status;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public String toString() {
        return "StateDao{" +
                "stateName='" + stateName + '\'' +
                ", status=" + status +
                ", country='" + country + '\'' +
                '}';
    }

    @Id
    private String stateName;
    private byte status;
    private String country;
}
