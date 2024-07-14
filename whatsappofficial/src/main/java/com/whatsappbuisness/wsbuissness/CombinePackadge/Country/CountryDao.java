package com.whatsappbuisness.wsbuissness.CombinePackadge.Country;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Tbl_CountryMaster" )
public class CountryDao {
    @Override
    public String toString() {
        return "CountryDao{" +
                "code='" + code + '\'' +
                ", countryCode='" + countryCode + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Id
    private String code;
    private String countryCode;
    private String name;
}
