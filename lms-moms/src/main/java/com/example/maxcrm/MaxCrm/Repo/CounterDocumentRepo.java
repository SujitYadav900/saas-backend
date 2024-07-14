package com.example.maxcrm.MaxCrm.Repo;

import com.example.maxcrm.MaxCrm.Dao.CounterDocument;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CounterDocumentRepo extends MongoRepository<CounterDocument,String> {

}
