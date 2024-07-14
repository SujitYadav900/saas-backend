package com.example.maxcrm.MaxCrm.MBOPSRegistration;

import com.example.maxcrm.MaxCrm.Dao.LeadMasterDao;
import com.example.maxcrm.MaxCrm.MBOPSRegistration.clinicalDomains.MBOPSClinicalDomainDao;
import com.example.maxcrm.MaxCrm.MBOPSRegistration.countries.MBOPSCountryDao;
import com.example.maxcrm.MaxCrm.MBOPSRegistration.grades.MBOPSGradesDao;
import com.example.maxcrm.MaxCrm.MBOPSRegistration.languages.MBOPSLanguageDao;
import com.example.maxcrm.MaxCrm.MBOPSRegistration.severities.MBOPSSeverityDao;
import com.example.maxcrm.MaxCrm.MBOPSRegistration.states.MBOPSStateDao;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface RegistrationService {

    Map<String, Object> getAccessToken(String email,String password,String clientSecret,String provider,String clientId) throws IOException;
    //Map<String, Object> getUserDetails(String accessToken) throws IOException;
    List<MBOPSCountryDao> getCountries(String accessToken) throws IOException;
    List<MBOPSStateDao> getStates(int countryId, String accessToken) throws IOException;
    List<MBOPSLanguageDao> getLanguages(String accessToken) throws IOException;
    List<MBOPSSeverityDao> getSeverities(String locId,String orgId,String accessToken) throws IOException;
    List<MBOPSGradesDao> getGrades(String locId,String orgId,String accessToken) throws IOException;
    List<MBOPSClinicalDomainDao> getClinicalDomains(String locId,String orgId,String accessToken) throws IOException;


    //Map<String,Object> createParent(String accessToken, long leadid) throws IOException;
    //Map<String,Object> updateParentContact(String accessToken, long leadid) throws IOException;
    //Map<String,Object> updateClinicalDetails(String locId,String orgId,String accessToken, String childId,String program,String[] files) throws IOException;

    int registerParent(long leadId, boolean useCustomIDs,String flag) throws Exception;



}
