package com.whatsappbuisness.wsbuissness.CombinePackadge.PaymentGatway;

import java.util.List;

public interface PaymentDetailsService {
    PaymentDetailsDao insert(PaymentDetailsDao paymentDetailsDao);

    PaymentDetailsDao update(PaymentDetailsDao paymentDetailsDao);

    List<PaymentDetailsDao> get(String startDate, String endDate, long accountId);

}
