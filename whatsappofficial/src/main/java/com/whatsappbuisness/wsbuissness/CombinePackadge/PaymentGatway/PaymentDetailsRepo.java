package com.whatsappbuisness.wsbuissness.CombinePackadge.PaymentGatway;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface PaymentDetailsRepo extends MongoRepository<PaymentDetailsDao, String> {

}
