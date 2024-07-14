package com.whatsappbuisness.wsbuissness.CombinePackadge.Subscription;
/*
 Author=Supreet Singh
 Date= 09/03/21 11:18 AM
*/


import com.whatsappbuisness.wsbuissness.CombinePackadge.ApiService.Profile.ProfileService;
import com.whatsappbuisness.wsbuissness.CombinePackadge.Common.TransationType;
import com.whatsappbuisness.wsbuissness.CombinePackadge.Common.VoucherType;
import com.whatsappbuisness.wsbuissness.CombinePackadge.CounterGeneration.CounterGenerationService;
import com.whatsappbuisness.wsbuissness.CombinePackadge.CreditService.CreditService;
import com.whatsappbuisness.wsbuissness.CombinePackadge.Datetiming.DateTiming;
import com.whatsappbuisness.wsbuissness.CombinePackadge.KarixEmailService.EmailDao;
import com.whatsappbuisness.wsbuissness.CombinePackadge.KarixEmailService.EmailService;
import com.whatsappbuisness.wsbuissness.CombinePackadge.Messages.Gateway;
import com.whatsappbuisness.wsbuissness.CombinePackadge.RabbitMqQue.MongoQue.MongoDB.GroupByMongoDao;
import com.whatsappbuisness.wsbuissness.CombinePackadge.RabbitMqQue.MongoQue.MongoDB.MessagePersistService;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class SubscriptionServiceImpl implements SubscriptionService {
    private static final Logger logger = LoggerFactory.getLogger(SubscriptionServiceImpl.class);
    private static HashMap<Long, SubscriptionDao> hashMap = new HashMap<>();
    @Autowired
    SubscriptionRepo subscriptionRepo;
    @Autowired
    EmailService emailService;


    @Autowired
    DataSource dataSource;
    @Autowired
    CreditService creditService;
    @Autowired
    CounterGenerationService counterGenerationService;
    @Autowired
    MessagePersistService messagePersistService;
    @Autowired
    ProfileService profileService;


    @Bean
    public void refreshSubscription() {
        List<SubscriptionDao> al = subscriptionRepo.findActive();
        hashMap = new HashMap<>();
        for (SubscriptionDao subscriptionDao : al) {
            hashMap.put(subscriptionDao.getAccountId(), subscriptionDao);
        }

        logger.info("Will Update Subscription locally");
    }

    @Override
    public SubscriptionDao insert(SubscriptionDao subscriptionDao) throws Exception {


        if (hashMap.containsKey(subscriptionDao.getAccountId())) {
            throw new Exception("Subscription Already Exists for this account");
        }
        subscriptionDao.setActive(true);
        subscriptionDao = subscriptionRepo.save(subscriptionDao);
        hashMap.put(subscriptionDao.getAccountId(), subscriptionDao);
        creditService.creditServer(TransationType.DR, subscriptionDao.getSubscriptionAmt(), subscriptionDao.getTransactionId(), VoucherType.Debit_Subscription_Renew, subscriptionDao.getAccountId());
        try {
            if (subscriptionDao.getGateway() == Gateway.KARIX) {
                profileService.updateKarixWebhook(subscriptionDao.getAccountId(), subscriptionDao);
            } else {
//                profileService.updateWebhook(subscriptionDao.getAccountId(), subscriptionDao);
                logger.info("AMEYO Webhook Updated:");
            }
        } catch (Exception ew) {

        }
        logger.info("subscription at serviceImpl 2 {}", subscriptionDao);
        return subscriptionDao;

    }


    @Override
    public SubscriptionDao update(SubscriptionDao subscriptionDao) {
        subscriptionDao.setActive(true);
        subscriptionDao = subscriptionRepo.save(subscriptionDao);
        if (subscriptionDao.isActive()) {
            hashMap.put(subscriptionDao.getAccountId(), subscriptionDao);
        }
        try {
            if (subscriptionDao.getGateway() == Gateway.KARIX) {
                profileService.updateKarixWebhook(subscriptionDao.getAccountId(), subscriptionDao);
            } else {
//                profileService.updateWebhook(subscriptionDao.getAccountId(), subscriptionDao);
                logger.info("AMEYO Webhook Updated:");
            }
        } catch (Exception ew) {

        }
        return subscriptionDao;
    }

    @Override
    public synchronized void updateSendTemplate(long accountId, String ref, double ded, VoucherType voucherType, double creditAmount) {
        Connection con = null;
        SubscriptionDao subscriptionDao = hashMap.get(accountId);

        try {
            con = dataSource.getConnection();

            subscriptionDao.setSentTemplate(subscriptionDao.getSentTemplate() + ded);
            PreparedStatement stmt = con.prepareStatement("update Tbl_Subscription set sentTemplate=? where accountId=? and isActive=TRUE limit 1");
            stmt.setDouble(1, subscriptionDao.getSentTemplate());
            stmt.setLong(2, subscriptionDao.getAccountId());
            logger.info("Will Update Sent Template {}", stmt);
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            try {
                con.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            updateLocally(subscriptionDao);

            if (creditAmount > 0) {
                //   creditService.creditServer(TransationType.DR, creditAmount * subscriptionDao.getAfterConsumptionTemplateDed(), ref, voucherType, accountId);
            }


        }
    }


    @Override
    public synchronized void updateSendSession(long accountId, String ref, double ded, VoucherType voucherType, double creditAmount) {
        Connection con = null;
        SubscriptionDao subscriptionDao = hashMap.get(accountId);
        try {
            con = dataSource.getConnection();
            subscriptionDao.setSentSession(subscriptionDao.getSentSession() + ded);
            PreparedStatement stmt = con.prepareStatement("update Tbl_Subscription set sentSession=? where accountId=? and isActive=TRUE limit 1");
            stmt.setDouble(1, subscriptionDao.getSentSession());
            stmt.setLong(2, subscriptionDao.getAccountId());
            logger.info("Will Update Sent Session {}", stmt);
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            try {
                con.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

            if (creditAmount > 0) {


                // creditService.creditServer(TransationType.DR, creditAmount * subscriptionDao.getAfterConsumptionSessionDed(), ref, voucherType, accountId);

            }

            updateLocally(subscriptionDao);
        }
    }

    @Override
    public synchronized void updateTemplateCreation(long accountId, String ref, double ded) {
        Connection con = null;
        SubscriptionDao subscriptionDao = hashMap.get(accountId);
        try {
            con = dataSource.getConnection();

            subscriptionDao.setTemplateCreated(subscriptionDao.getTemplateCreated() + ded);
            PreparedStatement stmt = con.prepareStatement("update Tbl_Subscription set templateCreated=? where accountId=? and isActive=TRUE limit 1");
            stmt.setDouble(1, subscriptionDao.getTemplateCreated());
            stmt.setLong(2, subscriptionDao.getAccountId());
            logger.info("Will Update Template Created {}", stmt);
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                con.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            updateLocally(subscriptionDao);
            if (subscriptionDao.getTemplateCreated() > subscriptionDao.getFreeTemplates()) {
                double deduction = ded * subscriptionDao.getTemplateCreationDed();
                creditService.creditServer(TransationType.DR, deduction, ref, VoucherType.Debit_TemplateCreation, accountId);
            }
        }
    }

    public synchronized void updateLocally(SubscriptionDao subscriptionDao) {
        hashMap.put(subscriptionDao.getAccountId(), subscriptionDao);
    }

    @Override
    public SubscriptionDao restoreLatestSubscription(long accountId) {
        return subscriptionRepo.findByAccountId(accountId);
    }

    @Override
    public void expireSubscriptions() {
        int dateFilter = DateTiming.getDateFilterDate();

        List<SubscriptionDao> al = subscriptionRepo.get(DateTiming.getDateFilterDate());
        int size = al.size();
        String[] ids = new String[size];
        logger.info("Will Auto Expire of date {} Found Expiry Are {}", dateFilter, size);
        if (size == 0) {

            return;
        }
        rechareOrExpireEmails(al);
        SubscriptionDao subscriptionDao;
        for (int i = 0; i < size; i++) {
            subscriptionDao = al.get(i);
            ids[i] = subscriptionDao.getTransactionId();

        }
        expireUpd(StringUtils.join(ids, ","));
        al = subscriptionRepo.findActive();
        hashMap = new HashMap<>();
        for (SubscriptionDao subscriptionDaos : al) {
            hashMap.put(subscriptionDaos.getAccountId(), subscriptionDaos);
        }
        logger.info("Will Update Subscription locally");


    }

    private void rechareOrExpireEmails(List<SubscriptionDao> al) {

        StringBuilder sb = new StringBuilder();
        SubscriptionDao subscriptionDao;
        for (int i = 0; i < al.size(); i++) {
            subscriptionDao = al.get(i);

            sb.append("<h2> ");
            sb.append(subscriptionDao.getAccountId());
            sb.append("</h2> ");
            sb.append("<br>");


            sb.append("Free Template/Total Template Sent :");
            sb.append(subscriptionDao.getFreeTemplatesMessage());
            sb.append("/");
            sb.append(subscriptionDao.getSentTemplate());
            sb.append("<br>");

            sb.append("Free Session/Total Session Sent :");
            sb.append(subscriptionDao.getFreeSessionMessage());
            sb.append("/");
            sb.append(subscriptionDao.getSentSession());
            sb.append("<br>");

            sb.append("Template Free/Template Created :");
            sb.append(subscriptionDao.getTemplateCreated());
            sb.append("/");
            sb.append(subscriptionDao.getFreeTemplates());
            sb.append("<br>");


            sb.append("Subcription Monthly Amount :");
            sb.append(subscriptionDao.getSubscriptionAmt());
            sb.append("<br>");

            sb.append("Per Template Price :");
            sb.append(subscriptionDao.getAfterConsumptionTemplateDed());
            sb.append("<br>");

            sb.append("Per Session Price :");
            sb.append(subscriptionDao.getAfterConsumptionSessionDed());
            sb.append("<br>");

            sb.append("Per Template Creation Charge :");
            sb.append(subscriptionDao.getTemplateCreationDed());
            sb.append("<br>");
            sb.append("<br>");

            sb.append("<h3>");
            sb.append("Message Status");
            sb.append("</h3>");
            List<GroupByMongoDao> report = messagePersistService.get(subscriptionDao.getAccountId(), DateTiming.decreaseMonth(subscriptionDao.getExpiryDateFilter(), 1), subscriptionDao.getExpiryDateFilter());
            for (GroupByMongoDao groupByMongoDao : report) {
                sb.append(groupByMongoDao.get_id());
                sb.append(" :");
                sb.append(groupByMongoDao.getCount());
                sb.append("<br>");
            }
            sb.append("<br>");
            sb.append("<br>");

        }
        List<EmailDao> alEmail = new ArrayList<>();
        alEmail.add(new EmailDao(sb.toString(), "Subcription Expiry Whatsapp Official " + DateTiming.getMysqlDate(), "supreet@prpservices.in"));
        alEmail.add(new EmailDao(sb.toString(), "Subcription Expiry Whatsapp Official " + DateTiming.getMysqlDate(), "ndnc@prpservices.in"));

        emailService.sendEmail(alEmail);
    }

    private void expireUpd(String ids) {
        Connection con = null;
        String date = DateTiming.increateMonth(DateTiming.getMysqlDate(), 1);
        try {
            con = dataSource.getConnection();
            PreparedStatement stmt = con.prepareStatement("update Tbl_Subscription set Tbl_Subscription.isActive=false where find_in_set(Tbl_Subscription.transactionId,'" + ids + "')");
            logger.info("Running Update Query {}", stmt);
            stmt.executeUpdate();
            stmt = con.prepareStatement("INSERT INTO `WSBUISSNES`.`Tbl_Subscription` (`transactionId`, `accountId`, `afterConsumptionSessionDed`, `afterConsumptionTemplateDed`, `baseUrl`, `createAt`, `createBy`, `deductionType`, `expiry`, `expiryDateFilter`, `freeSessionMessage`, `freeTemplates`, `freeTemplatesMessage`, `isActive`, `maximumPerCampaign`, `password`, `sentSession`, `sentTemplate`, `subscriptionAmt`, `templateCreated`, `templateCreationDed`, `test`, `updateAt`, `updateBy`, `userName`, `autoRenew`) SELECT uuid(), `Tbl_Subscription`.`accountId`, `Tbl_Subscription`.`afterConsumptionSessionDed`, `Tbl_Subscription`.`afterConsumptionTemplateDed`, `Tbl_Subscription`.`baseUrl`, now(), 'AUTORENEW', `Tbl_Subscription`.`deductionType`, '" + date + "', " + date.replaceAll("-", "") + ", `Tbl_Subscription`.`freeSessionMessage`, `Tbl_Subscription`.`freeTemplates`, `Tbl_Subscription`.`freeTemplatesMessage`, TRUE, `Tbl_Subscription`.`maximumPerCampaign`, `Tbl_Subscription`.`password`, 0, 0, `Tbl_Subscription`.`subscriptionAmt`, 0, `Tbl_Subscription`.`templateCreationDed`, `Tbl_Subscription`.`test`, now(), 'AUTORENEW', `Tbl_Subscription`.`userName`, `Tbl_Subscription`.`autoRenew` FROM `WSBUISSNES`.`Tbl_Subscription` where find_in_set(transactionId,'" + ids + "') and autoRenew=TRUE;");
            stmt.executeUpdate();
        } catch (Exception ew) {
            logger.error("Error Occured While Expiring Subsription {}", ew);
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public SubscriptionDao getByActive(long accountId) {
        return hashMap.get(accountId);

    }
}
