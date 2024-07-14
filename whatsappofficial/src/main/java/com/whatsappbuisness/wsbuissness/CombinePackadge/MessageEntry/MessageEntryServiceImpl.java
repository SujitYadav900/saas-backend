package com.whatsappbuisness.wsbuissness.CombinePackadge.MessageEntry;
/*
 Author=Supreet Singh
 Date= 11/03/21 11:35 AM
*/

import com.whatsappbuisness.wsbuissness.CombinePackadge.CampaignSchedule.CampaignScheduleDao;
import com.whatsappbuisness.wsbuissness.CombinePackadge.CampaignSchedule.CampaignScheduleRepo;
import com.whatsappbuisness.wsbuissness.CombinePackadge.Common.VoucherType;
import com.whatsappbuisness.wsbuissness.CombinePackadge.CounterGeneration.CounterGenerationService;
import com.whatsappbuisness.wsbuissness.CombinePackadge.CountryWisePrice.*;
import com.whatsappbuisness.wsbuissness.CombinePackadge.CountryWisePriceDaoList.CountryWisePriceDaoListServiceImpl;
import com.whatsappbuisness.wsbuissness.CombinePackadge.CountryWisePriceDaoList.CountryWisePriceListDao;
import com.whatsappbuisness.wsbuissness.CombinePackadge.CreditService.CreditService;
import com.whatsappbuisness.wsbuissness.CombinePackadge.CreditService.ResponseServiceDao;
import com.whatsappbuisness.wsbuissness.CombinePackadge.Datetiming.DateTiming;
import com.whatsappbuisness.wsbuissness.CombinePackadge.Messages.ChatSide;
import com.whatsappbuisness.wsbuissness.CombinePackadge.Messages.MessageDao;
import com.whatsappbuisness.wsbuissness.CombinePackadge.Messages.MessageStatus;
import com.whatsappbuisness.wsbuissness.CombinePackadge.Messages.MessageType;
import com.whatsappbuisness.wsbuissness.CombinePackadge.RabbitMqQue.QueName;
import com.whatsappbuisness.wsbuissness.CombinePackadge.RabbitMqQue.RabbitMqqueService;
import com.whatsappbuisness.wsbuissness.CombinePackadge.Subscription.SubscriptionDao;
import com.whatsappbuisness.wsbuissness.CombinePackadge.Subscription.SubscriptionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class MessageEntryServiceImpl implements MessageEntryService {
    
    private final Logger logger = LoggerFactory.getLogger(MessageEntryService.class);
    @Autowired
    RabbitMqqueService rabbitMqqueService;
    @Autowired
    CreditService creditService;

    @Autowired
    SubscriptionService subscriptionService;
    @Autowired
    CounterGenerationService counterGenerationService;

    @Autowired
    private CampaignScheduleRepo scheduleRepo;
    @Autowired
    CountryWisePriceService countryWisePriceService;




    public static double deduct(double free, double amt, double sent) {
        double remainingBalance = free - sent;
        double debitAmount = amt;
        if (remainingBalance > 0) {

            debitAmount = remainingBalance - amt;
        } else {
            debitAmount = amt - (amt * 2);
        }

        if (debitAmount < 0) {
            return Math.abs(debitAmount);
        } else {
            return 0;
        }

    }

    void validateMessageAndCredit(MessageType messageType, long accountId, double amt, SubscriptionDao subscriptionDao, String refId, VoucherType voucherType) throws Exception {
        ResponseServiceDao responseServiceDao = new ResponseServiceDao();
        responseServiceDao.setStatus(200);
        double creditBalance = 0;

        switch (messageType) {
            case template:
                creditBalance = deduct(subscriptionDao.getFreeTemplatesMessage(), amt, subscriptionDao.getSentTemplate());
                if (creditBalance > 0) {
                    responseServiceDao = creditService.validateCredit(accountId, (subscriptionDao.getAfterConsumptionTemplateDed() * creditBalance));
                }
                break;
            default:
                creditBalance = deduct(subscriptionDao.getFreeSessionMessage(), amt, subscriptionDao.getSentSession());
                if (creditBalance > 0) {
                    responseServiceDao = creditService.validateCredit(accountId, (subscriptionDao.getAfterConsumptionSessionDed() * creditBalance));
                }

                break;

        }
        logger.info("After Balance Check Shadow Balance is  {}", creditBalance);
        if (responseServiceDao.getStatus() != 200) {
            throw new Exception(responseServiceDao.getMessage());
        }
        if (responseServiceDao.getStatus() == 200) {
            switch (messageType) {
                case template:
                   subscriptionService.updateSendTemplate(accountId, refId, amt, voucherType, creditBalance);
                    break;
                default:
                   subscriptionService.updateSendSession(accountId, refId, amt, voucherType, creditBalance);
                    break;

            }
        }

    }




    @Override
    public MessageEntryResponseDao messageInsertService(MessageDao messageDao,boolean isPanel) {
        MessageEntryResponseDao messageEntryResponseDao = new MessageEntryResponseDao();
        SubscriptionDao subscriptionDao = null;
        try {
            subscriptionDao = subscriptionService.getByActive(messageDao.getAccountId());
        } catch (Exception ew) {
            logger.error("No Subscription Can Be Found For the account");
            messageEntryResponseDao.setStatus(MessageStatus.FAILED);
            messageEntryResponseDao.setMessage("No Subscription Found!!");
            return messageEntryResponseDao;
        }
        try {

            messageDao.setSubscriptionDao(subscriptionDao);
            messageDao.setId(counterGenerationService.generateUid());
            messageDao.setMessageId(messageDao.getId());
            messageDao.setChatSide(ChatSide.User);

            messageDao.setPanel(isPanel);
            insertToQue(messageDao);
            messageEntryResponseDao.setStatus(MessageStatus.SENT);
            List<String> ids = new ArrayList<>();
            ids.add(messageDao.getMessageId());
            messageEntryResponseDao.setIds(ids);
           // logger.info("Pushed Data into Main Que {}", messageDao);

        } catch (Exception ew) {
            logger.error("Error In Pushing Data Inside Que  as error was {}", ew.getMessage());
            ew.printStackTrace();
            messageEntryResponseDao.setStatus(MessageStatus.FAILED);
            messageEntryResponseDao.setMessage(ew.getMessage());

        }
        return messageEntryResponseDao;

    }

    @Override
    public MessageEntryResponseDao messageBulkInsertService(List<MessageDao> al, long accountId,boolean isPanel) {
//        logger.info("MessageEntryResponseDao {}",al);
        MessageEntryResponseDao messageEntryResponseDao = new MessageEntryResponseDao();
        SubscriptionDao subscriptionDao = null;
        CampaignScheduleDao scheduleDao=null;
        CountryWiseRateRetDao countryWiseRateRetDao = null;
        try {
            subscriptionDao = subscriptionService.getByActive(accountId);
            al.get(0).setAccountId(accountId);
            countryWiseRateRetDao = checkCountryPrice(al.get(0)).getCountryWiseRateRetDao();
        }
        catch (CountryNotFoundExeption e) {
            logger.error("Country Not Found For the account");
            messageEntryResponseDao.setStatus(MessageStatus.FAILED);
            messageEntryResponseDao.setMessage("Country Not Found");
            return messageEntryResponseDao;
        } catch (CountryDisabledExeption e) {
            logger.error("Country Not Allowed For the account");
            messageEntryResponseDao.setStatus(MessageStatus.FAILED);
            messageEntryResponseDao.setMessage("Country Not Allowed");
            return messageEntryResponseDao;
        }
        catch (Exception ew) {
            logger.error("No Subscription Can Be Found For the account");
            messageEntryResponseDao.setStatus(MessageStatus.FAILED);
            messageEntryResponseDao.setMessage("No Subscription Found!!");
            return messageEntryResponseDao;
        }
        if (subscriptionDao.getMaximumPerCampaign() < al.size()) {
            messageEntryResponseDao.setStatus(MessageStatus.FAILED);
            messageEntryResponseDao.setMessage("Maximum Batch Limit Exceed of " + subscriptionDao.getMaximumPerCampaign());
            return messageEntryResponseDao;
        }

        int i = 0;
        for (MessageDao messageDao : al) {
            CountryWisePriceListDao countryWisePriceListDao = CountryWisePriceDaoListServiceImpl.countryHashmapByCountryCode.get(messageDao.getCountryCode());
            messageDao.setTo(messageDao.getTo().replaceAll("[^0-9]", ""));
            if(countryWisePriceListDao != null){
                if(!messageDao.getTo().startsWith(countryWisePriceListDao.getNumberCode())){
                    messageDao.setTo(countryWisePriceListDao.getNumberCode() + messageDao.getTo());
                }
            }
            messageDao.setId(counterGenerationService.generateUid());
            messageDao.setMessageId(messageDao.getId());
            messageDao.setAccountId(accountId);
            messageDao.setSubscriptionDao(subscriptionDao);
            messageDao.setChatSide(ChatSide.User);
            al.set(i, messageDao);
            i++;

        }
        messageEntryResponseDao.setStatus(MessageStatus.SENT);
        List<String> ids = new ArrayList<>();
        for (MessageDao messageDao : al) {
            messageDao.setPanel(isPanel);
             if (messageDao.getiScheduled()!=1)
            {
                if(messageDao.isPanel()){
                    insertToQue(messageDao);
                }else{
                    insertToApiQue(messageDao);
                }
                logger.info("Sending Data to queue");

            }
            else {
                  scheduleDao=new CampaignScheduleDao();
                  scheduleDao.setId(UUID.randomUUID().toString().replaceAll("-",""));
                  scheduleDao.setActive(true);
                  scheduleDao.setMessageDao(messageDao);
                  scheduleDao.setDate(DateTiming.getDateRedable());
                  scheduleDao.setDateFilter(DateTiming.getDateFilterDate());
                  scheduleRepo.save(scheduleDao);
                  logger.info("Campaign Scheduled {}",scheduleDao);
            }

            ids.add(messageDao.getMessageId());
        }
        messageEntryResponseDao.setIds(ids);


        return messageEntryResponseDao;


    }


    @Override
    public void insertToQue(MessageDao messageDao) {
        rabbitMqqueService.pushToQue(QueName.Main_Que.routing, QueName.Main_Que.queName, messageDao);
    }

    @Override
    public void insertToApiQue(MessageDao messageDao) {
        rabbitMqqueService.pushToQue(QueName.Main_Que_Api.routing, QueName.Main_Que_Api.queName, messageDao);
    }

    private MessageDao checkCountryPrice(MessageDao messageDao) throws CountryNotFoundExeption, CountryDisabledExeption {
//        logger.info("The message dao in checkCountryPrice {}", messageDao.getCountryCode());
        CountryWiseRateRetDao countryWiseRateRetDao = countryWisePriceService.get(new CountryWisePricePk(messageDao.getCountryCode(), messageDao.getAccountId()));
        switch (countryWiseRateRetDao.getStatus()) {
            case NOTFOUND:
                throw new CountryNotFoundExeption("Requested Country Code Cannot be Found!!");

            case NOTALLOWED:
                throw new CountryDisabledExeption("Requested Country Code has been disabled!!");

            case OK:
                messageDao.setCountryWiseRateRetDao(countryWiseRateRetDao);
                break;

        }
        return messageDao;
    }


}
