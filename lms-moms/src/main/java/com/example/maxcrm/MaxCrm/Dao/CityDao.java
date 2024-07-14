package com.example.maxcrm.MaxCrm.Dao;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;

@Entity
@Table(name = "Tbl_City",   indexes = {@Index(name = "statename", columnList = "stateName")})
public class CityDao {
    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "CityDao{" +
                "cityName='" + cityName + '\'' +
                ", stateName='" + stateName + '\'' +
                ", status=" + status +
                '}';
    }

    @Id
    private String cityName;
    private String stateName;
    private int status;

}
