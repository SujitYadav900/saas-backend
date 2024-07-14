package com.example.maxcrm.MaxCrm;

import com.example.maxcrm.MaxCrm.Dao.LeadMasterDao;
import com.example.maxcrm.MaxCrm.Dao.LeadsBridgeDao;
import com.example.maxcrm.MaxCrm.Dao.NotificationDao;
import com.example.maxcrm.MaxCrm.Dao.ZapierDao;
import com.example.maxcrm.MaxCrm.MBOPSRegistration.RegistrationService;
import com.example.maxcrm.MaxCrm.MBOPSRegistration.clinicalDomains.MBOPSClinicalDomainDao;
import com.example.maxcrm.MaxCrm.MBOPSRegistration.clinicalDomains.MBOPSClinicalDomainService;
import com.example.maxcrm.MaxCrm.MBOPSRegistration.countries.MBOPSCountryDao;
import com.example.maxcrm.MaxCrm.MBOPSRegistration.countries.MBOPSCountryService;
import com.example.maxcrm.MaxCrm.MBOPSRegistration.grades.MBOPSGradesDao;
import com.example.maxcrm.MaxCrm.MBOPSRegistration.grades.MBOPSGradesService;
import com.example.maxcrm.MaxCrm.MBOPSRegistration.languages.MBOPSLanguageDao;
import com.example.maxcrm.MaxCrm.MBOPSRegistration.languages.MBOPSLanguageService;
import com.example.maxcrm.MaxCrm.MBOPSRegistration.severities.MBOPSSeverityDao;
import com.example.maxcrm.MaxCrm.MBOPSRegistration.severities.MBOPSSeverityService;
import com.example.maxcrm.MaxCrm.MBOPSRegistration.states.MBOPSStateDao;
import com.example.maxcrm.MaxCrm.MBOPSRegistration.states.MBOPSStateService;
import com.example.maxcrm.MaxCrm.Repo.LeadMasterRepo;
import com.example.maxcrm.MaxCrm.Service.FacebookService;
import com.example.maxcrm.MaxCrm.Service.LeadMasterService;
import com.example.maxcrm.MaxCrm.Service.LogTaskService;
import com.example.maxcrm.MaxCrm.Service.NotificationService;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

@SpringBootTest
class NeelKanthApplicationTests {
    @Autowired
    RegistrationService registrationService;
    @Autowired
    MBOPSClinicalDomainService domainService;
    @Autowired
    MBOPSGradesService gradesService;
    @Autowired
    MBOPSSeverityService severityService;
    @Autowired
    LeadMasterService leadMasterService;
    @Autowired
    MBOPSCountryService countryService;
    @Autowired
    MBOPSStateService stateService;

    @Test
    void contextLoads() throws Exception {
       // System.out.println(leadMasterService.getAppointmentSlotCount("2020-12-07   09-12"));
        String email ="system.crm@mailinator.com";
        String password ="pwr@sys";
        String client_secret ="SfJdvJgkW8529mSp7AKBRnB5B2RIjrUaExeS1oia";
        String provider ="users";
        String client_id ="189";

        String accessToken = registrationService.getAccessToken(email,password,client_secret,provider,client_id).get("access_token").toString();
//        List<MBOPSCountryDao> list = registrationService.getCountries(accessToken);
//
//        for (MBOPSCountryDao dao : list){
//            countryService.insert(dao);
//        };


        List<MBOPSCountryDao> clist = countryService.getAll();
        for (MBOPSCountryDao cdao : clist){
            List<MBOPSStateDao> slist = registrationService.getStates(cdao.getId(),accessToken);

            for (MBOPSStateDao sdao : slist){
                sdao.setCountryId(cdao.getId());
                stateService.insert(sdao);
            }
        }


    }
}
