package com.example.maxcrm.MaxCrm.Service;

import com.example.maxcrm.MaxCrm.Dao.LeadSourceDao;

import java.sql.SQLException;
import java.util.HashMap;

public interface LeadSourceService {
    LeadSourceDao insert(LeadSourceDao dao) throws Exception;
    LeadSourceDao update(LeadSourceDao dao) throws Exception;
    LeadSourceDao findByName(String sourceName);
    void delete(int id);
    Iterable<LeadSourceDao>  findAll();
    Iterable<LeadSourceDao> findActive();
    int findRandomUserId(int leadsource) throws SQLException;
    int findRanDomUserId(String leadsource);
    LeadSourceDao findLeadByLeadSource(String leadSource);
    void updateLeadSourceMemory();
    HashMap<String,String> getUser( byte type) throws SQLException;


}
