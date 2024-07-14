package com.example.maxcrm.MaxCrm.MBOPSRegistration.countries;

import javax.persistence.*;

@Entity
@Table(name = "Tbl_MBOPS_Country")
public class MBOPSCountryDao {

    @Id
   // @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;
    private String iso2;

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

    public String getIso2() {
        return iso2;
    }

    public void setIso2(String iso2) {
        this.iso2 = iso2;
    }

    @Override
    public String toString() {
        return "MBOPSCountryDao{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", iso2='" + iso2 + '\'' +
                '}';
    }
}
