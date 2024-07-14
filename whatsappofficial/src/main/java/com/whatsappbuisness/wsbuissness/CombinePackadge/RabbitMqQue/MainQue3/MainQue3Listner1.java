package com.whatsappbuisness.wsbuissness.CombinePackadge.RabbitMqQue.MainQue3;

import com.rabbitmq.client.Channel;
import com.whatsappbuisness.wsbuissness.CombinePackadge.ApiService.ContactCheck.ContactCheckService;
import com.whatsappbuisness.wsbuissness.CombinePackadge.ApiService.ContactCheck.ContactNotFoundExepction;
import com.whatsappbuisness.wsbuissness.CombinePackadge.ApiService.Media.MediaDao;
import com.whatsappbuisness.wsbuissness.CombinePackadge.ApiService.Media.MediaService;
import com.whatsappbuisness.wsbuissness.CombinePackadge.Common.TransationType;
import com.whatsappbuisness.wsbuissness.CombinePackadge.Common.VoucherType;
import com.whatsappbuisness.wsbuissness.CombinePackadge.CounterGeneration.CounterGenerationService;
import com.whatsappbuisness.wsbuissness.CombinePackadge.CountryWisePrice.*;
import com.whatsappbuisness.wsbuissness.CombinePackadge.CreditService.CreditService;
import com.whatsappbuisness.wsbuissness.CombinePackadge.CreditService.NoCreditFound;
import com.whatsappbuisness.wsbuissness.CombinePackadge.CreditService.ResponseServiceDao;
import com.whatsappbuisness.wsbuissness.CombinePackadge.Datetiming.DateTiming;
import com.whatsappbuisness.wsbuissness.CombinePackadge.Messages.*;
import com.whatsappbuisness.wsbuissness.CombinePackadge.RabbitMqQue.MainQue.MainQue;
import com.whatsappbuisness.wsbuissness.CombinePackadge.RabbitMqQue.MainQue2.MainQue2Listner1;
import com.whatsappbuisness.wsbuissness.CombinePackadge.RabbitMqQue.QueName;
import com.whatsappbuisness.wsbuissness.CombinePackadge.RabbitMqQue.RabbitMqqueService;
import com.whatsappbuisness.wsbuissness.CombinePackadge.UserOrBsnsSessionRedis.UserOrBsnsRedisService;
import com.whatsappbuisness.wsbuissness.CombinePackadge.Utility.CommonClassReturnWithStatus;
import com.whatsappbuisness.wsbuissness.CombinePackadge.WsUserORBuisness.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;
import java.util.UUID;
@Component
public class MainQue3Listner1 {

    private static final Logger logger = LoggerFactory.getLogger(MainQue3Listner1.class);
    @Autowired
    RabbitMqqueService mqqueService;

    @Autowired
    CountryWisePriceService countryWisePriceService;

    @Autowired
    UserOrBuisnessService userOrBuisnessService;
    @Autowired
    CreditService creditService;
    @Autowired
    MediaService mediaService;
    @Autowired
    UserOrBsnsRedisService userOrBsnsRedisService;


    @RabbitListener(queues = "Main_Que3", concurrency = "3")
    public void WebPanelQue(MessageDao messageDao, Channel channel, @Header(AmqpHeaders.DELIVERY_TAG) long tag) throws IOException {
        messageDao = check(messageDao);
        if (messageDao.getMessageStatus() == MessageStatus.PASSED) {
            if (messageDao.isPanel()) {
                logger.info("******* Will Push To Panel Que *****");
                mqqueService.pushToQue(QueName.Contact_Que3.routing, QueName.Contact_Que3.queName, messageDao);
            } else {
                if(messageDao.getSubscriptionDao().getGateway() == Gateway.KARIX){
                    try {
                        if (messageDao.getType() == MessageType.image) {

                            MediaDao mediaDao = mediaService.shareableLinkToBase64(messageDao.getImage().getLink(), messageDao.getImage().getFile());
                            messageDao.setAttachementBase64(mediaDao.getMedia().get(0).getAttachedFileBase64());
                            messageDao.setMimeType(mediaDao.getMedia().get(0).getMimeType());
                        } else if (messageDao.getType() == MessageType.document) {
                            MediaDao mediaDao = mediaService.shareableLinkToBase64(messageDao.getDocument().getLink(), messageDao.getDocument().getFilename());
                            messageDao.setAttachementBase64(mediaDao.getMedia().get(0).getAttachedFileBase64());
                            messageDao.setMimeType(mediaDao.getMedia().get(0).getMimeType());
                        } else if (messageDao.getType() == MessageType.video) {
                            MediaDao mediaDao = mediaService.shareableLinkToBase64(messageDao.getVideo().getLink(), messageDao.getVideo().getFile());
                            messageDao.setAttachementBase64(mediaDao.getMedia().get(0).getAttachedFileBase64());
                            messageDao.setMimeType(mediaDao.getMedia().get(0).getMimeType());
                        } else if (messageDao.getType() == MessageType.audio) {
                            MediaDao mediaDao = mediaService.shareableLinkToBase64(messageDao.getAudio().getLink(), messageDao.getAudio().getFile());
                            messageDao.setAttachementBase64(mediaDao.getMedia().get(0).getAttachedFileBase64());
                            messageDao.setMimeType(mediaDao.getMedia().get(0).getMimeType());
                        }
                    }catch (Exception e){
                        channel.basicAck(tag,false);
                        e.printStackTrace();
                    }
                }
                logger.info("Will Push To Api Que");
                mqqueService.pushToQue(QueName.Contact_Que_Api.routing, QueName.Contact_Que_Api.queName, messageDao);
            }
        } else {
            rollBackAndSaveToDb(messageDao);
        }

    }

    /**
     * This function wil set country code,check user side of business
     *
     * @param messageDao
     * @return
     */
    public MessageDao check(MessageDao messageDao) {
        try {
            messageDao = checkCountryPrice(messageDao);
            checkUsrOrBsns(messageDao);
            messageDao.setMessageStatus(MessageStatus.PASSED);
        } catch (CountryNotFoundExeption e) {
            messageDao.setMessageStatus(MessageStatus.INVALIDCOUNTRYCODE);
        } catch (CountryDisabledExeption e) {
            messageDao.setMessageStatus(MessageStatus.COUNTRYNOTALLOWED);
        } catch (NoCreditFound e) {
            messageDao.setMessageStatus(MessageStatus.LOWBALANCEFAIL);

        } catch (Exception e) {
            e.printStackTrace();
            messageDao.setMessageStatus(MessageStatus.FAILED);

            logger.error("Contact Checked Other Exception Happened {}", messageDao);
        }
        return messageDao;
    }

    private MessageDao checkCountryPrice(MessageDao messageDao) throws CountryNotFoundExeption, CountryDisabledExeption {
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

    private void checkUsrOrBsns(MessageDao messageDao) throws NoCreditFound {
//        CommonClassReturnWithStatus<UserOrBuisnessIntiatedDao, StatusDaoUsrOrBsn> commonClassReturnWithStatus = userOrBuisnessService.checkSession(messageDao.getTo(), messageDao.getCountryCode(), messageDao.getAccountId(), messageDao.getChatSide(), messageDao.getCountryWiseRateRetDao().getCountryWisePriceDao().getBsnsInitiatedRates());
        CommonClassReturnWithStatus<UserOrBuisnessIntiatedDao, StatusDaoUsrOrBsn> commonClassReturnWithStatus = null;
        if(messageDao.getType() != MessageType.template){
            commonClassReturnWithStatus = userOrBuisnessService.checkSessionWithCatagoryRedis(messageDao.getTo(), messageDao.getCountryCode(), messageDao.getAccountId(), messageDao.getChatSide(), messageDao.getCountryWiseRateRetDao().getCountryWisePriceDao().getUserInitiatedRates(), "USER");
        }else{
//            commonClassReturnWithStatus = userOrBuisnessService.checkSessionWithCatagory(messageDao.getTo(), messageDao.getCountryCode(), messageDao.getAccountId(), messageDao.getChatSide(), messageDao.getCountryWiseRateRetDao().getCountryWisePriceDao().getBsnsInitiatedRates(), messageDao.getTemplate().getCategory());
            if(messageDao.getTemplate().getCategory().equalsIgnoreCase("MARKETING")){
                commonClassReturnWithStatus = userOrBuisnessService.checkSessionWithCatagoryRedis(messageDao.getTo(), messageDao.getCountryCode(), messageDao.getAccountId(), messageDao.getChatSide(), messageDao.getCountryWiseRateRetDao().getCountryWisePriceDao().getMarketingRates(), messageDao.getTemplate().getCategory());
            }else if(messageDao.getTemplate().getCategory().equalsIgnoreCase("UTILITY")){
                commonClassReturnWithStatus = userOrBuisnessService.checkSessionWithCatagoryRedis(messageDao.getTo(), messageDao.getCountryCode(), messageDao.getAccountId(), messageDao.getChatSide(), messageDao.getCountryWiseRateRetDao().getCountryWisePriceDao().getUtilityRates(), messageDao.getTemplate().getCategory());
            }else if(messageDao.getTemplate().getCategory().equalsIgnoreCase("AUTHENTICATION")){
                commonClassReturnWithStatus = userOrBuisnessService.checkSessionWithCatagoryRedis(messageDao.getTo(), messageDao.getCountryCode(), messageDao.getAccountId(), messageDao.getChatSide(), messageDao.getCountryWiseRateRetDao().getCountryWisePriceDao().getAuthenicationRates(), messageDao.getTemplate().getCategory());
            }
        }
        switch (commonClassReturnWithStatus.getStatus()) {
            case NEW:
                ResponseServiceDao responseServiceDao = null;
                if(messageDao.getType() != MessageType.template){
                    responseServiceDao = creditService.validateCredit(messageDao.getAccountId(), messageDao.getCountryWiseRateRetDao().getCountryWisePriceDao().getUserInitiatedRates());
                }else{
                    if(messageDao.getTemplate().getCategory().equalsIgnoreCase("MARKETING")){
                        responseServiceDao = creditService.validateCredit(messageDao.getAccountId(), messageDao.getCountryWiseRateRetDao().getCountryWisePriceDao().getMarketingRates());
                    }else if(messageDao.getTemplate().getCategory().equalsIgnoreCase("UTILITY")){
                        responseServiceDao = creditService.validateCredit(messageDao.getAccountId(), messageDao.getCountryWiseRateRetDao().getCountryWisePriceDao().getUtilityRates());
                    }else if(messageDao.getTemplate().getCategory().equalsIgnoreCase("AUTHENTICATION")){
                        responseServiceDao = creditService.validateCredit(messageDao.getAccountId(), messageDao.getCountryWiseRateRetDao().getCountryWisePriceDao().getAuthenicationRates());
                    }
                }
                if (responseServiceDao.getStatus() != 200) {
                    if(messageDao.getType() != MessageType.template){
                        userOrBsnsRedisService.deleteSessionByKey(messageDao.getTo(), messageDao.getCountryCode(), messageDao.getAccountId(), messageDao.getChatSide(), "USER");
                    }else{
                        if(messageDao.getTemplate().getCategory().equalsIgnoreCase("MARKETING")){
                            userOrBsnsRedisService.deleteSessionByKey(messageDao.getTo(), messageDao.getCountryCode(), messageDao.getAccountId(), messageDao.getChatSide(), messageDao.getTemplate().getCategory());
                        }else if(messageDao.getTemplate().getCategory().equalsIgnoreCase("UTILITY")){
                            userOrBsnsRedisService.deleteSessionByKey(messageDao.getTo(), messageDao.getCountryCode(), messageDao.getAccountId(), messageDao.getChatSide(), messageDao.getTemplate().getCategory());
                        }else if(messageDao.getTemplate().getCategory().equalsIgnoreCase("AUTHENTICATION")){
                            userOrBsnsRedisService.deleteSessionByKey(messageDao.getTo(), messageDao.getCountryCode(), messageDao.getAccountId(), messageDao.getChatSide(), messageDao.getTemplate().getCategory());
                        }
                    }
                    UserOrBuisnessIntiatedDao userOrBuisnessIntiatedDao = commonClassReturnWithStatus.getData();
                    userOrBuisnessService.deleteUserOrBuisnessIntiatedDaoById(userOrBuisnessIntiatedDao.getId());
                    logger.info("Session Deleted");
                    throw new NoCreditFound("Credit Cannot be Found");
                }
                if(messageDao.getType() != MessageType.template){
                    creditService.creditServer(TransationType.DR, messageDao.getCountryWiseRateRetDao().getCountryWisePriceDao().getBsnsInitiatedRates(), commonClassReturnWithStatus.getData().getRefId(), VoucherType.Debit_Usr_Session, messageDao.getAccountId());
                }else{
                    if(messageDao.getTemplate().getCategory().equalsIgnoreCase("MARKETING")){
                        creditService.creditServer(TransationType.DR, messageDao.getCountryWiseRateRetDao().getCountryWisePriceDao().getMarketingRates(), commonClassReturnWithStatus.getData().getRefId(), VoucherType.Debit_Bsns_Session, messageDao.getAccountId());
                    }else if(messageDao.getTemplate().getCategory().equalsIgnoreCase("UTILITY")){
                        creditService.creditServer(TransationType.DR, messageDao.getCountryWiseRateRetDao().getCountryWisePriceDao().getUtilityRates(), commonClassReturnWithStatus.getData().getRefId(), VoucherType.Debit_Bsns_Session, messageDao.getAccountId());
                    }else if(messageDao.getTemplate().getCategory().equalsIgnoreCase("AUTHENTICATION")){
                        creditService.creditServer(TransationType.DR, messageDao.getCountryWiseRateRetDao().getCountryWisePriceDao().getAuthenicationRates(), commonClassReturnWithStatus.getData().getRefId(), VoucherType.Debit_Bsns_Session, messageDao.getAccountId());
                    }
                }
                break;
            case EXISTS:
                break;


        }

    }

    private void rollBackAndSaveToDb(MessageDao messageDao) {
        mqqueService.pushToQue(QueName.Mongo_Que.routing, QueName.Mongo_Que.queName, messageDao);
    }

}
