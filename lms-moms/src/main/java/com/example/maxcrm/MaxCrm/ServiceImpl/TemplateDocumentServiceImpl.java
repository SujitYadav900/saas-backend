package com.example.maxcrm.MaxCrm.ServiceImpl;

import com.example.maxcrm.MaxCrm.Dao.TemplateDocument;
import com.example.maxcrm.MaxCrm.Repo.TemplateDocumentRepo;
import com.example.maxcrm.MaxCrm.Service.TemplateDocumentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TemplateDocumentServiceImpl implements TemplateDocumentService {
    @Autowired
    MongoTemplate mongoTemplate;

    Logger logger = LoggerFactory.getLogger(TemplateDocumentService.class);
    @Autowired
    TemplateDocumentRepo templateDocumentRepo;



    @Override
    public List<TemplateDocument> getAll(String name) {


        return templateDocumentRepo.findAll(name,   Sort.by(Sort.Direction.DESC,"id"));
    }

    @Override
    public List<TemplateDocument> getAll() {
        return templateDocumentRepo.findAll(   Sort.by(Sort.Direction.DESC,"id"));
    }

    @Override
    public List<TemplateDocument> getActive(String name) {


        return templateDocumentRepo.findActive(name,  Sort.by(Sort.Direction.DESC,"id"));
    }

    @Override
    public List<TemplateDocument> getAllApproved(String name) {
        return templateDocumentRepo.findAllApproved(name,  Sort.by(Sort.Direction.DESC,"id"));
    }

    @Override
    public TemplateDocument findByNameAndCreateBy(String userId, String name) {
        return templateDocumentRepo.findByNameAndCreateBy(userId, name);
    }


    @Override
    public TemplateDocument insert(TemplateDocument templateDocument) {


        logger.info("Inserting {}", templateDocument);
        templateDocument= templateDocumentRepo.insert(templateDocument);

        return templateDocument;
    }

    @Override
    public TemplateDocument findById(String id) {
        return templateDocumentRepo.findById(id).get();
    }

    @Override
    public void updateMessageTemplateSts(String id, String value, boolean sts) {
        logger.info("Updating status of id {} {} {}", id, sts,value);
        Query query = new Query(Criteria.where("id").is(id));
        Update update = new Update();
        update.set("approved", sts);
        update.set("templateIdHidden", value);
        mongoTemplate.updateFirst(query, update, TemplateDocument.class);
    }

    @Override
    public void updateMailTemplateSts(String id, boolean sts) {
        logger.info("Updating status of id {} {}", id, sts);
        Query query = new Query(Criteria.where("id").is(id));
        Update update = new Update();
        update.set("approved", sts);
        mongoTemplate.updateFirst(query, update, TemplateDocument.class);

    }


    @Override
    public TemplateDocument update(TemplateDocument templateDocument) {

        logger.info("Updating {}", templateDocument);
        return templateDocumentRepo.save(templateDocument);
    }

    @Override
    public void delete(String templateName) {
        logger.info("Deleting {}", templateName);
        templateDocumentRepo.deleteById(templateName);

    }
}
