package com.example.maxcrm.MaxCrm.Service;

import com.example.maxcrm.MaxCrm.Dao.MailSentDocument;

import javax.mail.MessagingException;

public interface SendSmptMailService {
    void sendMail(MailSentDocument mailSentDocument) throws MessagingException;
}
