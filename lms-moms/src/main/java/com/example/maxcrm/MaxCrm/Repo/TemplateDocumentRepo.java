package com.example.maxcrm.MaxCrm.Repo;

import com.example.maxcrm.MaxCrm.Dao.TemplateDocument;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface TemplateDocumentRepo extends MongoRepository<TemplateDocument,String> {
        @Query("{templateStatus:1,createBy:?0}")
        List<TemplateDocument> findActive(String userId, Sort sort);
        @Query("{createBy:?0}")
        List<TemplateDocument> findAll(String userId, Sort sort);
        @Query("{createBy:?0,approved:true}")
        List<TemplateDocument> findAllApproved(String userId, Sort sort);
        @Query("{createBy:?0,templateName:?1}")
        TemplateDocument findByNameAndCreateBy(String userId,String name);





}
