package com.example.maxcrm.MaxCrm.MBOPSRegistration.grades;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MBOPSGradesRepo  extends JpaRepository<MBOPSGradesDao,Integer> {

    MBOPSGradesDao findByName(String gradeName);

}
