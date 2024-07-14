package com.example.maxcrm.MaxCrm.Dao;


import javax.persistence.*;

@Entity
@Table(name = "Tbl_Property_Inner", uniqueConstraints={@UniqueConstraint(name = "unqproperty",columnNames = {"name","propertyId"})})
public class PropertyInnerDao {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private long propertyId;
    @Transient
    private String propertyType;
    private String name;
    private String value;
    private int createat;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getPropertyId() {
        return propertyId;
    }

    public void setPropertyId(long propertyId) {
        this.propertyId = propertyId;
    }

    public String getPropertyType() {
        return propertyType;
    }

    public void setPropertyType(String propertyType) {
        this.propertyType = propertyType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public int getCreateat() {
        return createat;
    }

    public void setCreateat(int createat) {
        this.createat = createat;
    }

    @Override
    public String toString() {
        return "PropertyInnerDao{" +
                "id=" + id +
                ", propertyId=" + propertyId +
                ", propertyType='" + propertyType + '\'' +
                ", name='" + name + '\'' +
                ", value='" + value + '\'' +
                ", createat=" + createat +
                '}';
    }
}
