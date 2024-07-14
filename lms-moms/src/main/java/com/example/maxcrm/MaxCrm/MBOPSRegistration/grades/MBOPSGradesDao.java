package com.example.maxcrm.MaxCrm.MBOPSRegistration.grades;

import javax.persistence.*;

@Entity
@Table(name = "Tbl_MBOPS_Grades")
public class MBOPSGradesDao {

    @Id
    //@GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;

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

    @Override
    public String toString() {
        return "MBOPSGradesDao{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
