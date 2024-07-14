package com.example.maxcrm.MaxCrm.MBOPSRegistration.severities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Tbl_MBOPS_Severity")
public class MBOPSSeverityDao {

    @Id
   private String serName;
    private String serKey;

    public String getSerName() {
        return serName;
    }

    public void setSerName(String serName) {
        this.serName = serName;
    }

    public String getSerKey() {
        return serKey;
    }

    public void setSerKey(String serKey) {
        this.serKey = serKey;
    }

    @Override
    public String toString() {
        return "MBOPSSeverityDao{" +
                "serName='" + serName + '\'' +
                ", serKey='" + serKey + '\'' +
                '}';
    }
}
