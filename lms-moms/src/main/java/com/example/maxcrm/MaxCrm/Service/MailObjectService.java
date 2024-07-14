package com.example.maxcrm.MaxCrm.Service;

import com.example.maxcrm.MaxCrm.Dao.MailObjectDao;
import com.example.maxcrm.MaxCrm.Dao.PaginationDao;

import java.sql.SQLException;
import java.util.List;

public interface MailObjectService {

    void bulkInsertMail(List<MailObjectDao> mailObjectDaos) throws Exception;
}
