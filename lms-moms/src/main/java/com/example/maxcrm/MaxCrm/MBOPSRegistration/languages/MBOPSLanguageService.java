package com.example.maxcrm.MaxCrm.MBOPSRegistration.languages;

import java.util.List;

public interface MBOPSLanguageService {
    MBOPSLanguageDao insert(MBOPSLanguageDao dao);
    MBOPSLanguageDao update(MBOPSLanguageDao dao);
    void delete(int id);
    List<MBOPSLanguageDao> getAll();
}
