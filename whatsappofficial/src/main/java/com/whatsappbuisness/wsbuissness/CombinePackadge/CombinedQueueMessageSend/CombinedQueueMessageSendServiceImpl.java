package com.whatsappbuisness.wsbuissness.CombinePackadge.CombinedQueueMessageSend;

import com.whatsappbuisness.wsbuissness.CombinePackadge.ApiService.ContactCheck.ContactCheckService;
import com.whatsappbuisness.wsbuissness.CombinePackadge.ApiService.Media.MediaService;
import com.whatsappbuisness.wsbuissness.CombinePackadge.CampaignSchedule.CampaignScheduleDao;
import com.whatsappbuisness.wsbuissness.CombinePackadge.CampaignSchedule.CampaignScheduleRepo;
import com.whatsappbuisness.wsbuissness.CombinePackadge.CounterGeneration.CounterGenerationService;
import com.whatsappbuisness.wsbuissness.CombinePackadge.CountryWisePrice.*;
import com.whatsappbuisness.wsbuissness.CombinePackadge.CountryWisePriceDaoList.CountryWisePriceDaoListServiceImpl;
import com.whatsappbuisness.wsbuissness.CombinePackadge.CountryWisePriceDaoList.CountryWisePriceListDao;
import com.whatsappbuisness.wsbuissness.CombinePackadge.CreditService.CreditService;
import com.whatsappbuisness.wsbuissness.CombinePackadge.Datetiming.DateTiming;
import com.whatsappbuisness.wsbuissness.CombinePackadge.MessageEntry.MessageEntryResponseDao;
import com.whatsappbuisness.wsbuissness.CombinePackadge.Messages.*;
import com.whatsappbuisness.wsbuissness.CombinePackadge.RabbitMqQue.QueName;
import com.whatsappbuisness.wsbuissness.CombinePackadge.RabbitMqQue.RabbitMqqueService;
import com.whatsappbuisness.wsbuissness.CombinePackadge.Subscription.SubscriptionDao;
import com.whatsappbuisness.wsbuissness.CombinePackadge.Subscription.SubscriptionService;
import com.whatsappbuisness.wsbuissness.CombinePackadge.WsUserORBuisness.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;



@Service
public class CombinedQueueMessageSendServiceImpl implements CombinedQueueMessageSendService{
    private static final Logger logger = LoggerFactory.getLogger(CombinedQueueMessageSendServiceImpl.class);

    @Autowired
    SubscriptionService subscriptionService;
    @Autowired
    CounterGenerationService counterGenerationService;
    @Autowired
    private CampaignScheduleRepo scheduleRepo;
    @Autowired
    MediaService mediaService;
    @Autowired
    CreditService creditService;
    @Autowired
    UserOrBuisnessService userOrBuisnessService;

    @Autowired
    RabbitMqqueService mqqueService;
    @Autowired
    CountryWisePriceService countryWisePriceService;
    @Autowired
    ContactCheckService contactCheckService;
    @Autowired
    UserOrBuisnessRepo userOrBuisnessRepo;
    @Autowired
    DataSource dataSource;

    @Override
    public MessageEntryResponseDao messageBulkInsertService(List<MessageDao> al, long accountId, boolean isPanel) {

        logger.info("MessageEntryResponseDao {}",al);
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
//                    insertToApiQue(messageDao);
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



//        logger.info("MessageEntryResponseDao {}",al);
//        MessageEntryResponseDao messageEntryResponseDao = new MessageEntryResponseDao();
//        SubscriptionDao subscriptionDao = null;
//        CampaignScheduleDao scheduleDao=null;
//        CountryWiseRateRetDao countryWiseRateRetDao = null;
//        int chunkSize = 100;
//        int i = 0;
//        int totalMessage = 0;
//        try {
//            subscriptionDao = subscriptionService.getByActive(accountId);
//            al.get(0).setAccountId(accountId);
//            countryWiseRateRetDao = checkCountryPrice(al.get(0)).getCountryWiseRateRetDao();
//        }
//        catch (CountryNotFoundExeption e) {
//            logger.error("Country Not Found For the account");
//            messageEntryResponseDao.setStatus(MessageStatus.FAILED);
//            messageEntryResponseDao.setMessage("Country Not Found");
//            return messageEntryResponseDao;
//        } catch (CountryDisabledExeption e) {
//            logger.error("Country Not Allowed For the account");
//            messageEntryResponseDao.setStatus(MessageStatus.FAILED);
//            messageEntryResponseDao.setMessage("Country Not Allowed");
//            return messageEntryResponseDao;
//        }
//        catch (Exception ew) {
//            logger.error("No Subscription Can Be Found For the account");
//            messageEntryResponseDao.setStatus(MessageStatus.FAILED);
//            messageEntryResponseDao.setMessage("No Subscription Found!!");
//            return messageEntryResponseDao;
//        }
//        if (subscriptionDao.getMaximumPerCampaign() < al.size()) {
//            messageEntryResponseDao.setStatus(MessageStatus.FAILED);
//            messageEntryResponseDao.setMessage("Maximum Batch Limit Exceed of " + subscriptionDao.getMaximumPerCampaign());
//            return messageEntryResponseDao;
//        }
//        messageEntryResponseDao.setStatus(MessageStatus.SENT);
//        List<String> ids = new ArrayList<>();
//        List<MessageDao> chunkList = new ArrayList<MessageDao>();
//
//        for (MessageDao messageDao : al) {
//            chunkList.add(messageDao);
//            i++;
//            totalMessage++;
//            CountryWisePriceListDao countryWisePriceListDao = CountryWisePriceDaoListServiceImpl.countryHashmapByCountryCode.get(messageDao.getCountryCode());
//            if(countryWisePriceListDao != null){
//                if(messageDao.getTo().startsWith("+")){
//                    messageDao.setTo(messageDao.getTo().replace("+","").replaceAll(" ",""));
//                }
//                if(!messageDao.getTo().startsWith(countryWisePriceListDao.getNumberCode())){
//                    messageDao.setTo(countryWisePriceListDao.getNumberCode() + messageDao.getTo());
//                }
//            }
//            messageDao.setPanel(isPanel);
//            messageDao.setId(counterGenerationService.generateUid());
//            messageDao.setAccountId(subscriptionDao.getAccountId());
//            messageDao.setMessageId(messageDao.getId());
//            messageDao.setChatSide(ChatSide.User);
//            messageDao.setSubscriptionDao(subscriptionDao);
//            messageDao.setCountryWiseRateRetDao(countryWiseRateRetDao);
//            if (messageDao.getiScheduled()==1){
//                scheduleDao=new CampaignScheduleDao();
//                scheduleDao.setId(UUID.randomUUID().toString().replaceAll("-",""));
//                scheduleDao.setActive(true);
//                scheduleDao.setMessageDao(messageDao);
//                scheduleDao.setDate(DateTiming.getDateRedable());
//                scheduleDao.setDateFilter(DateTiming.getDateFilterDate());
//                scheduleRepo.save(scheduleDao);
//                logger.info("Campaign Scheduled {}",scheduleDao);
//            }
//            ids.add(messageDao.getMessageId());
//            if ((messageDao.getiScheduled()!=1 && i == chunkSize) || (messageDao.getiScheduled()!=1 && totalMessage == al.size()) ) {
//                insertToMainQue2(chunkList);
//                i=0;
//                chunkList.clear();
//            }
//        }
//        messageEntryResponseDao.setIds(ids);
//
//        return messageEntryResponseDao;
    }
    public void insertToQue(MessageDao messageDao) {
        mqqueService.pushToQue(QueName.Main_Que2.routing, QueName.Main_Que2.queName, messageDao);
    }

    public void insertToMainQue2(List<MessageDao> messageDaoList) {
        mqqueService.sendListOfMessages(messageDaoList, QueName.Main_Que2.routing, QueName.Main_Que2.queName );
    }
    private MessageDao checkCountryPrice(MessageDao messageDao) throws CountryNotFoundExeption, CountryDisabledExeption {
        logger.info("The message dao in checkCountryPrice {}", messageDao.getCountryCode());
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
    private void rollBackAndSaveToDb(MessageDao messageDao) {
        mqqueService.pushToQue(QueName.Mongo_Que.routing, QueName.Mongo_Que.queName, messageDao);
    }
    public String generateUid() {
        return UUID.randomUUID().toString();
    }
}
