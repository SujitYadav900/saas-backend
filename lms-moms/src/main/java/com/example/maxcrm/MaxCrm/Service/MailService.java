package com.example.maxcrm.MaxCrm.Service;

import com.example.maxcrm.MaxCrm.Dao.MailObjectDao;

public interface MailService {

    String sendMail(MailObjectDao mailObjectDao);
}
