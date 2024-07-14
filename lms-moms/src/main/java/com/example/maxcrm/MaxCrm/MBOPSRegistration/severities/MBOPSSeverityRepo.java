package com.example.maxcrm.MaxCrm.MBOPSRegistration.severities;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MBOPSSeverityRepo extends JpaRepository<MBOPSSeverityDao,String> {

    MBOPSSeverityDao findBySerName(String severityName);
}
