package com.example.maxcrm.MaxCrm.MBOPSRegistration.states;

import java.util.List;

public interface MBOPSStateService {

    MBOPSStateDao insert(MBOPSStateDao dao);
    MBOPSStateDao update(MBOPSStateDao dao);
    void delete(int id);
    List<MBOPSStateDao> getAll();
    MBOPSStateDao findByName(String name);
    List<MBOPSStateDao> findByCountryId(int id);


}
