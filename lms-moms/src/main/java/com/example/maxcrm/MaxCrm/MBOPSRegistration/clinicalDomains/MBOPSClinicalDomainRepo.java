package com.example.maxcrm.MaxCrm.MBOPSRegistration.clinicalDomains;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface MBOPSClinicalDomainRepo extends JpaRepository<MBOPSClinicalDomainDao,Integer> {

    @Query("from MBOPSClinicalDomainDao where clinical_domain=1")
    MBOPSClinicalDomainDao findByClinical_domain(String domainName);
}
