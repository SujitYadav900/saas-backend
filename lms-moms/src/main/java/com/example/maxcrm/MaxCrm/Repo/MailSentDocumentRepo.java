package com.example.maxcrm.MaxCrm.Repo;

import com.example.maxcrm.MaxCrm.Dao.MailSentDocument;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;


import java.util.List;

public interface MailSentDocumentRepo extends MongoRepository<MailSentDocument,String> {
    @Query("{datefilter: {$gte: ?1, $lt: ?2}}")
    List<MailSentDocument> findBetweenDate(int startDate, int enddate, Pageable pageable);



}
