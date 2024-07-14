package com.example.maxcrm.MaxCrm.MBOPSRegistration.languages;

import javax.persistence.*;

@Entity
@Table(name = "Tbl_MBOPS_Language")
public class MBOPSLanguageDao {

    @Id
    //@GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String code;
    private String title;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "MBOPSLanguageDao{" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", title='" + title + '\'' +
                '}';
    }
}
