package com.example.maxcrm.MaxCrm.Repo;

import com.example.maxcrm.MaxCrm.Dao.FileDocument;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface FileDocumentRepo extends MongoRepository<FileDocument,String> {
        @Query( value="{createBy:?0}", fields="{ image : 0 } ")
        List<FileDocument> getFileByCreateBy(String createBy, Sort sort);

}
