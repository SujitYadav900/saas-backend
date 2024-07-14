package com.example.maxcrm.MaxCrm.ServiceImpl;

import com.example.maxcrm.MaxCrm.Dao.MailSentDocument;
import com.example.maxcrm.MaxCrm.Repo.MailSentDocumentRepo;
import com.example.maxcrm.MaxCrm.Service.MailSentDocumentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

public class MailSentDocumentServiceImpl implements MailSentDocumentService {
    Logger logger = LoggerFactory.getLogger(MailSentDocumentService.class);
    @Autowired
    MailSentDocumentRepo mailSentDocumentRepo;
    @Override
    public MailSentDocument insertSingle(MailSentDocument mailSentDocument) {
        logger.info("Inserting {}",mailSentDocument);
        return mailSentDocumentRepo.insert(mailSentDocument);
    }

    @Override
    public List<MailSentDocument> insertBulk(List<MailSentDocument> mailSentDocuments) {
        logger.info("Inserting Bulk{}",mailSentDocuments);
        return mailSentDocumentRepo.insert(mailSentDocuments);
    }

    @Override
    public List<MailSentDocument> getBetweenDate(int startdate, int enddate, int limit, int offset) {
        Pageable pageable =
                PageRequest.of(offset, limit);
        return mailSentDocumentRepo.findBetweenDate(startdate,enddate,pageable);
    }

    @Override
    public List<MailSentDocument> getAll() {
        return mailSentDocumentRepo.findAll();
    }
}
