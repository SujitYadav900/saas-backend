package com.example.maxcrm.MaxCrm.Repo;

import com.example.maxcrm.MaxCrm.Dao.TemplateTypeDocument;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface TemplateTypeDocumentRepo extends MongoRepository<TemplateTypeDocument,String> {
    @Query("{status:1}")
    List<TemplateTypeDocument> findAllActive();
}
