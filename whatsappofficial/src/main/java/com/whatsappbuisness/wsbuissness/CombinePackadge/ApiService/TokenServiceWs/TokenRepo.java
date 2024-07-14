package com.whatsappbuisness.wsbuissness.CombinePackadge.ApiService.TokenServiceWs;
/*
 Author=Supreet Singh
 Date= 10/03/21 10:56 AM
*/

import org.springframework.data.mongodb.repository.MongoRepository;

public interface TokenRepo extends MongoRepository<TokenServiceDao,Long> {
}
