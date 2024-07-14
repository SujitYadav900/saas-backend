package com.example.maxcrm.MaxCrm.Service;

import com.example.maxcrm.MaxCrm.Dao.LeadTransferDao;

import java.sql.SQLException;
import java.util.List;

public interface LeadTransferService {
    Iterable<LeadTransferDao> getByLeadId(long id);
    int insertBulk(List<LeadTransferDao> al,String username,boolean sendNotification) throws SQLException;


}
