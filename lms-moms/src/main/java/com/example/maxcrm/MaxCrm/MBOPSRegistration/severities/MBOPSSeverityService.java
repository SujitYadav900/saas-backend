package com.example.maxcrm.MaxCrm.MBOPSRegistration.severities;

import java.util.List;

public interface MBOPSSeverityService {

    MBOPSSeverityDao insert(MBOPSSeverityDao severityDao);
    MBOPSSeverityDao update(MBOPSSeverityDao severityDao);
    void delete(String name);
    List<MBOPSSeverityDao> getAll();
    MBOPSSeverityDao findByName(String severityName);
}
