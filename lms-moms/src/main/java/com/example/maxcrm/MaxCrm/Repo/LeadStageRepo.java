package com.example.maxcrm.MaxCrm.Repo;

import com.example.maxcrm.MaxCrm.Dao.LeadStageDocument;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface LeadStageRepo extends MongoRepository<LeadStageDocument,String> {
    @Query("{stage:?0}")
    List<LeadStageDocument> findByName(String name);
    boolean existsLeadStageDocumentByStage(String stage);

}
