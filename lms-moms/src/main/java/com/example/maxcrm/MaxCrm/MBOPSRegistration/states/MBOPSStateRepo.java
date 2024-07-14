package com.example.maxcrm.MaxCrm.MBOPSRegistration.states;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MBOPSStateRepo extends JpaRepository<MBOPSStateDao,Integer> {

    MBOPSStateDao findByName(String name);
    List<MBOPSStateDao> findByCountryId(int id);
}
