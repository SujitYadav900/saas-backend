package com.example.maxcrm.MaxCrm.MBOPSRegistration.clinicalDomains;

import java.util.List;

public interface MBOPSClinicalDomainService {

    MBOPSClinicalDomainDao insert(MBOPSClinicalDomainDao clinicalDomainDao);
    MBOPSClinicalDomainDao update(MBOPSClinicalDomainDao clinicalDomainDao);
    void delete(int id);
    List<MBOPSClinicalDomainDao> getAll();
    MBOPSClinicalDomainDao findByName(String domainName);

}
