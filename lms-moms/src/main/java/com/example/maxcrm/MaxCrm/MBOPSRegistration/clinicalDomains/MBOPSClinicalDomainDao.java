package com.example.maxcrm.MaxCrm.MBOPSRegistration.clinicalDomains;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Tbl_MBOPS_ClinicalDomain")
public class MBOPSClinicalDomainDao {

    @Id
    private int id;
    private String clinical_domain;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getClinical_domain() {
        return clinical_domain;
    }

    public void setClinical_domain(String clinical_domain) {
        this.clinical_domain = clinical_domain;
    }

    @Override
    public String toString() {
        return "MBOPSClinicalDomainDao{" +
                "id=" + id +
                ", clinical_domain='" + clinical_domain + '\'' +
                '}';
    }
}
