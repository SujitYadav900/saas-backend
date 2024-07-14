package com.example.maxcrm.MaxCrm.Service;

import com.example.maxcrm.MaxCrm.Dao.LeadStatusTransferDao;

import java.sql.SQLException;
import java.util.List;

public interface LeadStatusTransferService {
    Iterable<LeadStatusTransferDao> findAllByLeadId(long id);
    LeadStatusTransferDao insert(LeadStatusTransferDao dao) throws SQLException;
    void bulkInsert(List<LeadStatusTransferDao> dao) throws SQLException;
    LeadStatusTransferDao update(LeadStatusTransferDao dao);


}
