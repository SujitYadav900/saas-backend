package com.whatsappbuisness.wsbuissness.CombinePackadge.catalog;


import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface CatalogMessageRepo extends MongoRepository<CatalogMessageDao,String> {

    List<CatalogMessageDao> findAllByAccountIdOrderByAccountId(long accountID);
}
