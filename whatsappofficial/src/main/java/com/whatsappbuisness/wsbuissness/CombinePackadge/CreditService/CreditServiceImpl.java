package com.whatsappbuisness.wsbuissness.CombinePackadge.CreditService;
/*
 Author=Supreet Singh
 Date= 09/03/21 1:02 PM
*/


import com.google.gson.Gson;
import com.whatsappbuisness.wsbuissness.CombinePackadge.Common.AccountTransactionDao;
import com.whatsappbuisness.wsbuissness.CombinePackadge.Common.Services;
import com.whatsappbuisness.wsbuissness.CombinePackadge.Common.TransationType;
import com.whatsappbuisness.wsbuissness.CombinePackadge.Common.VoucherType;
import com.whatsappbuisness.wsbuissness.CombinePackadge.RabbitMqQue.RabbitMqqueService;
import okhttp3.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

@Service
public class CreditServiceImpl implements CreditService {
    private static final Logger logger = LoggerFactory.getLogger(CreditService.class);
    public static final OkHttpClient client = new OkHttpClient.Builder()
            .readTimeout(25, TimeUnit.SECONDS)
            .writeTimeout(25, TimeUnit.SECONDS)
            .connectTimeout(25, TimeUnit.SECONDS)
            .connectionPool(new ConnectionPool(5, 120, TimeUnit.SECONDS))
            .build();
    static MediaType mediaType = MediaType.parse("application/json");
    @Autowired
    RabbitMqqueService rabbitMqqueService;
    @Value("${custom.rabbitmqquename.creditmanagmentque}")
    private String creditManagmentQue;


    @Value("${custom.gatewayBaseUrl}")
    private String gatewayBaseUrl;

    @Value("${custom.accountServiceBaseUrl}")
    private String accountServiceBaseUrl;

    @Override
    public void creditServer(TransationType transationType, double amount, String refId, VoucherType voucherType, long accountId) {
        AccountTransactionDao accountTransactionDao = new AccountTransactionDao(transationType, Services.WHATSAPPOFFICIAL, amount, refId, voucherType, accountId);
        String json = new Gson().toJson(accountTransactionDao);
        //logger.info("Will Send To Account Credit Managment {}", json);
        rabbitMqqueService.pushToQueJson(creditManagmentQue, creditManagmentQue, json);
    }

    @Override
    public ResponseServiceDao validateCredit(long accointId, double amount) {
        ResponseServiceDao responseServiceDao = new ResponseServiceDao();
        responseServiceDao.setStatus(500);
        responseServiceDao.setMessage("Error Occured");
        RequestBody body = RequestBody.create("{\"services\":\"WHATSAPPOFFICIAL\",\"accountId\":" + accointId + ",\"amount\":" + amount + "}", mediaType);
        Request request = new Request.Builder()
                .url(gatewayBaseUrl + accountServiceBaseUrl + "account/validate")
                .post(body)
                .addHeader("cookie", "PHPSESSID=ek8500afed9rb6p1egghb2mq9h; JSESSIONID=D311DD74B013F2D7951E2D7D757A62E2")
                .addHeader("content-type", "application/json")
                .build();

        Response response = null;
        try {
            response = client.newCall(request).execute();

            if (response.code() == 200) {
                responseServiceDao.setStatus(200);
                responseServiceDao.setMessage("All Ok");
                //logger.info("After Credit Validation Repsonse was {}",response.body().string());

            } else {
                String responseStr = response.body().string();
                responseServiceDao = new Gson().fromJson(responseStr, ResponseServiceDao.class);
                //logger.error("After Credit validation Error Occured was {}",responseServiceDao);
            }

        } catch (Exception e) {
            logger.info("Exception Occurs in CreditServiceImpl while validating Credit {}",e);
            String responseStr = null;
            try {
                responseStr = response.body().string();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            responseServiceDao = new Gson().fromJson(responseStr, ResponseServiceDao.class);
        } finally {
            response.close();
        }
        return responseServiceDao;

    }
}
