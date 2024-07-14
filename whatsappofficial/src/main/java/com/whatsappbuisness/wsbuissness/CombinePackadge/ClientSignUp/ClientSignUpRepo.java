package com.whatsappbuisness.wsbuissness.CombinePackadge.ClientSignUp;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ClientSignUpRepo extends MongoRepository<ClientSignUpDao,Integer> {

    List<ClientSignUpDao> findByClientNumberAndWhatsappNumber(String clientNumber, String whatsappNumber);
    List<ClientSignUpDao> findByCreateByAndClientNumber(String createBy, String clientNumber);
    List<ClientSignUpDao> findByCreateByAndWhatsappNumber(String createBy, String whatsappNumber);
    List<ClientSignUpDao> findByWhatsappNumber(String whatsappNumber);
    ClientSignUpDao findById(String id);
}
