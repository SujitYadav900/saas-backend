package com.example.maxcrm.MaxCrm.Dao;

import javax.persistence.*;

@Entity
@Table(name = "Tbl_Property", uniqueConstraints={@UniqueConstraint(name = "unqpropertytype",columnNames = "type")})
public class PropertyDao {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String type;
    private int createat;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getCreateat() {
        return createat;
    }

    public void setCreateat(int createat) {
        this.createat = createat;
    }

    @Override
    public String toString() {
        return "PropertyDao{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", createat=" + createat +
                '}';
    }
}
