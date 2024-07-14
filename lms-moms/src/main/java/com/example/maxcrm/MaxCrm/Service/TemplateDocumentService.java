package com.example.maxcrm.MaxCrm.Service;

import com.example.maxcrm.MaxCrm.Dao.TemplateDocument;

import java.util.List;

public interface TemplateDocumentService {
    List<TemplateDocument> getAll(String name);
    List<TemplateDocument> getAll();

    List<TemplateDocument> getActive(String name);
    List<TemplateDocument> getAllApproved(String name);
    TemplateDocument findByNameAndCreateBy(String userId,String name);
    TemplateDocument insert(TemplateDocument templateDocument);
    TemplateDocument findById(String id);
    void updateMessageTemplateSts(String id,String value,boolean sts);
    void updateMailTemplateSts(String id,boolean sts);


    TemplateDocument update(TemplateDocument templateDocument);

    void delete(String templateName);
}
