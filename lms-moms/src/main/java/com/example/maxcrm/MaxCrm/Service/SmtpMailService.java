package com.example.maxcrm.MaxCrm.Service;

import com.example.maxcrm.MaxCrm.Dao.MailObjectDao;

import java.util.List;

public interface SmtpMailService {
     void sendMail(List<MailObjectDao> al) throws Exception;
}
