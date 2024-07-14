package com.example.maxcrm.MaxCrm.Service;

import com.example.maxcrm.MaxCrm.Dao.MailSentDocument;

import java.util.List;

public interface MailSentDocumentService {
    MailSentDocument insertSingle(MailSentDocument mailSentDocument);
    List<MailSentDocument> insertBulk(List<MailSentDocument> mailSentDocuments);
    List<MailSentDocument> getBetweenDate(int startdate,int enddate,int limit,int offset);
    List<MailSentDocument> getAll();
}
