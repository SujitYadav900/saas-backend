package com.example.maxcrm.MaxCrm.Service;

import com.example.maxcrm.MaxCrm.Dao.TemplateTypeDocument;

import java.util.List;

public interface TemplateTypeDocumentService {
    List<TemplateTypeDocument> findAll();
    List<TemplateTypeDocument> findAllActive();
    TemplateTypeDocument insert(TemplateTypeDocument templateTypeDocument);
    TemplateTypeDocument update(TemplateTypeDocument templateTypeDocument);
    void delete(String id);
}
