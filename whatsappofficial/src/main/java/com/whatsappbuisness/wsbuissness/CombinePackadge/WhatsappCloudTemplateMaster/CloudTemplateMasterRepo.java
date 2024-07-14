package com.whatsappbuisness.wsbuissness.CombinePackadge.WhatsappCloudTemplateMaster;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface CloudTemplateMasterRepo extends MongoRepository<CloudTemplateMasterDao,Integer> {
    CloudTemplateMasterDao findById(String id);
    List<CloudTemplateMasterDao> findAllByAccountId(long accountId);


}
