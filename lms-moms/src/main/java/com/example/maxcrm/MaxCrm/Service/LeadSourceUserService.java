package com.example.maxcrm.MaxCrm.Service;

import com.example.maxcrm.MaxCrm.Dao.LeadSourceUser;

public interface LeadSourceUserService  {

    Iterable<LeadSourceUser> getAll();
    LeadSourceUser insert(LeadSourceUser leadSourceUser) throws Exception;
    void delete(long id);

}
