package com.example.maxcrm.MaxCrm.MBOPSRegistration.countries;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MBOPSCountryRepo extends JpaRepository<MBOPSCountryDao,Integer> {

    MBOPSCountryDao findByName(String name);
}
