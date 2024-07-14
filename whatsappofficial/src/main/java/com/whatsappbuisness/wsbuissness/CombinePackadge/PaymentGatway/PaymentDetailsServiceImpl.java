package com.whatsappbuisness.wsbuissness.CombinePackadge.PaymentGatway;

import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Service;
import org.springframework.data.mongodb.core.query.Query;
import java.util.List;

@Service
public class PaymentDetailsServiceImpl implements PaymentDetailsService{

    private static final Logger logger = LoggerFactory.getLogger(PaymentDetailsServiceImpl.class);
//    private static final String RAZORPAY_KEY = "rzp_test_3uQqXMp0RguouB";
//    private static final String RAZORPAY_KEY_SECRET = "8qTUjrhg5dkbAeZADPknhzfn";

    private static final String RAZORPAY_KEY = "rzp_live_jtyVIqABJtGxYQ";
    private static final String RAZORPAY_KEY_SECRET = "No9RGeklQ1cpda9gwnuhgsQ5";

    @Autowired
    PaymentDetailsRepo paymentDetailsRepo;

    @Autowired
    MongoTemplate mongoTemplate;
    @Override
    public PaymentDetailsDao insert(PaymentDetailsDao paymentDetailsDao) {
        if(paymentDetailsDao.getPaymentGatway() == PaymentGatway.RAZORPAY){
            paymentDetailsDao = callingRazorPayGatway(paymentDetailsDao);
        }
        return paymentDetailsRepo.save(paymentDetailsDao);
    }

    @Override
    public PaymentDetailsDao update(PaymentDetailsDao paymentDetailsDao) {
        return paymentDetailsRepo.save(paymentDetailsDao);
    }

    @Override
    public List<PaymentDetailsDao> get(String startDate, String endDate, long accountId) {
       Query query = new Query(Criteria.where("createDateFilter").gte(Long.parseLong(startDate.replaceAll("-",""))).lte(Long.parseLong(endDate.replaceAll("-","")))).with(Sort.by(Sort.Order.desc("createDateFilter")));
       return mongoTemplate.find(query,PaymentDetailsDao.class, "PaymentDetailsDao");
    }

    private PaymentDetailsDao callingRazorPayGatway(PaymentDetailsDao paymentDetailsDao) {
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("amount",paymentDetailsDao.getAmount() * 100);
            jsonObject.put("currency",paymentDetailsDao.getCurrency());
            RazorpayClient razorpayClient = new RazorpayClient(RAZORPAY_KEY,RAZORPAY_KEY_SECRET);
            Order order = razorpayClient.orders.create(jsonObject);
            logger.info("The value of razorpay response is {}", order);
            paymentDetailsDao.setOrderId(order.get("id"));
            paymentDetailsDao.setCurrency(order.get("currency"));
            paymentDetailsDao.setKeyId(RAZORPAY_KEY);
            paymentDetailsDao.setKeySecret(RAZORPAY_KEY_SECRET);
            return paymentDetailsDao;
        } catch (RazorpayException e) {
            throw new RuntimeException(e);
        }
    }
}
