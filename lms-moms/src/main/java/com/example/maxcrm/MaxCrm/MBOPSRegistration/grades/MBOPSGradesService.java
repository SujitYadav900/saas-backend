package com.example.maxcrm.MaxCrm.MBOPSRegistration.grades;

import java.util.List;

public interface MBOPSGradesService {

    MBOPSGradesDao insert(MBOPSGradesDao gradesDao);
    MBOPSGradesDao update(MBOPSGradesDao gradesDao);
    void delete(int id);
    List<MBOPSGradesDao> getAll();
    MBOPSGradesDao findByName(String gradeName);

}
