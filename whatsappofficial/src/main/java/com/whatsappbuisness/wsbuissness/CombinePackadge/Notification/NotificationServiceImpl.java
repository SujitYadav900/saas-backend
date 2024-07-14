package com.whatsappbuisness.wsbuissness.CombinePackadge.Notification;

import com.google.gson.Gson;
import com.whatsappbuisness.wsbuissness.CombinePackadge.Datetiming.DateTiming;
import com.whatsappbuisness.wsbuissness.CombinePackadge.MessageReportDao.MessageReportService;
import com.whatsappbuisness.wsbuissness.CombinePackadge.Subscription.SubscriptionDao;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.sql.DataSource;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;


@Service
public class NotificationServiceImpl implements NotificationService {

    private static final Logger logger = LoggerFactory.getLogger(NotificationServiceImpl.class);
    private static final String BASE_URL = "http://test.chatmybot.in/gateway/alert/v1/api/send/alertMessage";

    private static final int EXPIRY_DAYS_THRESHOLD_1 = 7;
    private static final int EXPIRY_DAYS_THRESHOLD_2 = 2;
    private static final int EXPIRY_DAYS_THRESHOLD_3 = 0;

    @Autowired
    private DataSource dataSource;


    @Autowired
    MessageReportService messageReportService;

    @Override
    public void informed() throws NoSuchFieldException, IllegalAccessException {
        List<Map<String, String>> mapMessage = new ArrayList<>();

        List<SubscriptionDao> daoList = checkSubscriptionExpiryDate();
        logger.info("Getting list of expire account details {}", daoList);

        for (SubscriptionDao subscriptionDao : daoList) {
            Map<String, String> hashMap = new HashMap<>();
            hashMap.put("emailTo", subscriptionDao.getEmailTo());
            hashMap.put("emailCc", subscriptionDao.getEmailCc());
            hashMap.put("accountId", String.valueOf(subscriptionDao.getAccountId()));
            hashMap.put("companyName", getAccountDetils(subscriptionDao.getAccountId()));
            hashMap.put("balance", String.valueOf(roundedUp(getAccountBalance(subscriptionDao.getAccountId()))));
            hashMap.put("expiryDays", String.valueOf(subscriptionDao.getExpiryDateFilter()));

            hashMap.put("salesPersonName", subscriptionDao.getEmailCc().split("@")[0]);
            mapMessage.add(hashMap);
        }

        if (!mapMessage.isEmpty()) {
            AlertMessageDao alertMessageDao = new AlertMessageDao();
            alertMessageDao.setNotifyType(NotifyType.SUBSCRIPTIONEXPIRY);
            alertMessageDao.setMessages(mapMessage);
            sendSubscriptionExpiryMail(alertMessageDao);
        }
    }


    private void sendSubscriptionExpiryMail(AlertMessageDao arrayList) {
        logger.info("Getting data for send mail {}", arrayList);
        Gson gson = new Gson();
        String json = gson.toJson(arrayList);
        logger.info("JSON {}", json);
        OkHttpClient client = new OkHttpClient().newBuilder().build();
        okhttp3.MediaType mediaType = okhttp3.MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType, json);
        Request request = new Request.Builder().url(BASE_URL).method("POST", body).addHeader("Content-Type", "application/json").build();
        Response response = null;
        try {
            response = client.newCall(request).execute();
            logger.info("After Hitting request is {}", response.body().string());
        } catch (IOException e) {
            logger.error("ERROR OCCURRED {}", e.getMessage());
            throw new RuntimeException(e);
        } finally {
            response.close();
        }
    }

    @Override
    public void dailyReport() {


    }


    private List<SubscriptionDao> checkSubscriptionExpiryDate() {
        List<SubscriptionDao> subscriptionDaoList = new ArrayList<>();

        try (Connection con = dataSource.getConnection()) {
            int dateFilter = DateTiming.dateFilterDate();
            String sql = "SELECT accountId, emailTo, emailCc, DATEDIFF(expiry, CURDATE()) AS expirydays " + "FROM WSBUISSNES.Tbl_Subscription " + "WHERE isActive = TRUE";

            try (PreparedStatement stmt = con.prepareStatement(sql)) {
                ResultSet rs = stmt.executeQuery();
                while (rs.next()) {
                    SubscriptionDao subscriptionDao = new SubscriptionDao();
                    subscriptionDao.setAccountId(rs.getLong("accountId"));
                    subscriptionDao.setEmailTo(rs.getString("emailTo"));
                    subscriptionDao.setEmailCc(rs.getString("emailCc"));
                    subscriptionDao.setExpiryDateFilter(rs.getInt("expirydays"));

                    int expiryDateFilter = subscriptionDao.getExpiryDateFilter();
                    if (expiryDateFilter == EXPIRY_DAYS_THRESHOLD_1 || expiryDateFilter == EXPIRY_DAYS_THRESHOLD_2 || expiryDateFilter == EXPIRY_DAYS_THRESHOLD_3) {
                        subscriptionDaoList.add(subscriptionDao);
                    }
                }
            }
        } catch (SQLException e) {
            logger.error("Error occurred: {}", e.getMessage(), e);
        }

        return subscriptionDaoList;
    }


    private String getAccountDetils(long accountId) throws NoSuchFieldException, IllegalAccessException {
        String url = "https://wa.chatmybot.in/gateway/accounts/v1/account/getbyidaccount?id=" + accountId;
        Map<String, String> newObj = new HashMap<String, String>();
        RestTemplate restTemplate = new RestTemplate();
        newObj = restTemplate.getForObject(url, Map.class);
        return newObj.get("accountName");
    }

    private double getAccountBalance(long accountId) {
        String url = "https://wa.chatmybot.in/gateway/accounts/v1/account/getaccountBalance?id=" + accountId;
        RestTemplate restTemplate = new RestTemplate();
        AccountBalance accountBalance = restTemplate.getForObject(url, AccountBalance.class);
        return accountBalance.getBalance();
    }


    public double roundedUp(double value) {
        double returnValue = 0.0;
        BigDecimal roundofValue = new BigDecimal(value);
        BigDecimal finalTotal = roundofValue.setScale(2, RoundingMode.HALF_UP);
        returnValue = finalTotal.doubleValue();
        return returnValue;
    }

    


}
