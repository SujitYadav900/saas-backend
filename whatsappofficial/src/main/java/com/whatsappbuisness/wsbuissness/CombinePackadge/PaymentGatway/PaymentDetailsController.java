package com.whatsappbuisness.wsbuissness.CombinePackadge.PaymentGatway;
import com.whatsappbuisness.wsbuissness.CombinePackadge.Common.SessionRetrival.SessionRetrievalService;
import com.whatsappbuisness.wsbuissness.CombinePackadge.Common.SessionRetrival.UsermasterDao;
import com.whatsappbuisness.wsbuissness.CombinePackadge.CounterGeneration.CounterGenerationService;
import com.whatsappbuisness.wsbuissness.CombinePackadge.Datetiming.DateTiming;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/payment")
public class PaymentDetailsController {

    @Autowired
    PaymentDetailsService paymentDetailsService;
    @Autowired
    SessionRetrievalService sessionRetrievalService;
    @Autowired
    CounterGenerationService counterGenerationService;

    @PostMapping("/")
    PaymentDetailsDao insert(@RequestBody PaymentDetailsDao paymentDetailsDao, Authentication authentication) {
        UsermasterDao usermasterDao = sessionRetrievalService.get(authentication);
        paymentDetailsDao.setId(counterGenerationService.generateUid());
        paymentDetailsDao.setAccountId(usermasterDao.getId());
        paymentDetailsDao.setCreateDate(DateTiming.getDateRedable());
        paymentDetailsDao.setCreateDateFilter(DateTiming.getDateFilterDateLong());
        paymentDetailsDao.setPaymentGatway(PaymentGatway.RAZORPAY);
        paymentDetailsDao.setPaymentGatwayStatus(PaymentGatwayStatus.FAILED);
        paymentDetailsDao.setCurrency("INR");
        return paymentDetailsService.insert(paymentDetailsDao);
    }
    @PostMapping("/update")
    PaymentDetailsDao update(@RequestBody PaymentDetailsDao paymentDetailsDao, Authentication authentication) {
        UsermasterDao usermasterDao = sessionRetrievalService.get(authentication);
        paymentDetailsDao.setAccountId(usermasterDao.getId());
        paymentDetailsDao.setPaymentGatwayStatus(PaymentGatwayStatus.SUCCESFULL);
        return paymentDetailsService.update(paymentDetailsDao);
    }
    @PostMapping("/get")
    List<PaymentDetailsDao> get(@RequestParam("startdate") String startDate, @RequestParam("enddate") String endDate, Authentication authentication){
        UsermasterDao usermasterDao = sessionRetrievalService.get(authentication);
        return paymentDetailsService.get(startDate, endDate, usermasterDao.getAccountId());
    }
}
