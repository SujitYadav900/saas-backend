package com.example.maxcrm.MaxCrm.MBOPSRegistration.countries;

import java.util.List;

public interface MBOPSCountryService {

    MBOPSCountryDao insert(MBOPSCountryDao dao);
    MBOPSCountryDao update(MBOPSCountryDao dao);
    void delete(int id);
    List<MBOPSCountryDao> getAll();
    MBOPSCountryDao findByName(String name);

}
