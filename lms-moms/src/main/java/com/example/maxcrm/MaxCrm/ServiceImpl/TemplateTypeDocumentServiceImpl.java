package com.example.maxcrm.MaxCrm.ServiceImpl;

import com.example.maxcrm.MaxCrm.Dao.TemplateTypeDocument;
import com.example.maxcrm.MaxCrm.Repo.TemplateTypeDocumentRepo;
import com.example.maxcrm.MaxCrm.Service.TemplateTypeDocumentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class TemplateTypeDocumentServiceImpl implements TemplateTypeDocumentService {
    Logger logger = LoggerFactory.getLogger(TemplateTypeDocumentService.class);
    @Autowired
    TemplateTypeDocumentRepo templateTypeDocumentRepo;

    @Override
    public List<TemplateTypeDocument> findAll() {

        return templateTypeDocumentRepo.findAll();
    }

    @Override
    public List<TemplateTypeDocument> findAllActive() {
        return templateTypeDocumentRepo.findAllActive();
    }

    @Override
    public TemplateTypeDocument insert(TemplateTypeDocument templateTypeDocument) {
        logger.info("Inserting {}", templateTypeDocument);
        return templateTypeDocumentRepo.insert(templateTypeDocument);
    }

    @Override
    public TemplateTypeDocument update(TemplateTypeDocument templateTypeDocument) {
        logger.info("Updating {}", templateTypeDocument);
        return templateTypeDocumentRepo.save(templateTypeDocument);
    }

    @Override
    public void delete(String id) {
        logger.info("Deleting {}", id);
        templateTypeDocumentRepo.deleteById(id);
    }
}
