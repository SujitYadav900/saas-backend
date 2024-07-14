package com.whatsappbuisness.wsbuissness.CombinePackadge.ClientSignUp;

import com.google.gson.Gson;
import com.whatsappbuisness.wsbuissness.CombinePackadge.AccountMasterDao.*;
import com.whatsappbuisness.wsbuissness.CombinePackadge.Common.SessionRetrival.UsermasterDao;
import com.whatsappbuisness.wsbuissness.CombinePackadge.CounterGeneration.CounterGenerationService;
import com.whatsappbuisness.wsbuissness.CombinePackadge.CountryWisePrice.CountryWisePriceDao;
import com.whatsappbuisness.wsbuissness.CombinePackadge.CountryWisePrice.CountryWisePriceService;
import com.whatsappbuisness.wsbuissness.CombinePackadge.CountryWisePriceDaoList.CountryWisePriceDaoListService;
import com.whatsappbuisness.wsbuissness.CombinePackadge.CountryWisePriceDaoList.CountryWisePriceListDao;
import com.whatsappbuisness.wsbuissness.CombinePackadge.CreditService.ResponseServiceDao;
import com.whatsappbuisness.wsbuissness.CombinePackadge.Datetiming.DateTiming;
import com.whatsappbuisness.wsbuissness.CombinePackadge.Filtering.FilterDao;
import com.whatsappbuisness.wsbuissness.CombinePackadge.Messages.Gateway;
import com.whatsappbuisness.wsbuissness.CombinePackadge.Messages.TemplateMessageDao.TemplateText.TemplateMessageDao;
import com.whatsappbuisness.wsbuissness.CombinePackadge.Subscription.SubscriptionDao;
import com.whatsappbuisness.wsbuissness.CombinePackadge.Subscription.SubscriptionService;
import com.whatsappbuisness.wsbuissness.CombinePackadge.SubscriptionPackage.ExpiryDateType;
import com.whatsappbuisness.wsbuissness.CombinePackadge.SubscriptionPackage.PlanType;
import com.whatsappbuisness.wsbuissness.CombinePackadge.SubscriptionPackage.SubScriptionPackageDao;
import com.whatsappbuisness.wsbuissness.CombinePackadge.SubscriptionPackage.SubscriptionPackageType;
import com.whatsappbuisness.wsbuissness.CombinePackadge.SubscriptionPackageTransaction.SubscriptionPackageTransactionDao;
import com.whatsappbuisness.wsbuissness.CombinePackadge.SubscriptionPackageTransaction.SubscriptionPackageTransactionService;
import com.whatsappbuisness.wsbuissness.CombinePackadge.Template.TemplateMessageService;
import com.whatsappbuisness.wsbuissness.CombinePackadge.WhatsappCloudTemplateMaster.CloudTemplateMasterDao;
import com.whatsappbuisness.wsbuissness.CombinePackadge.WhatsappCloudTemplateMaster.CloudTemplateMasterService;
import okhttp3.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class ClientSignUpServiceImpl implements ClientSignUpService {

    public static String websiteToken = "ya29.QQIBibTwvKkE39hY8mdkT_mXZoRh7Ub9cK9hNsqrxem4QJ6sQa36VHfyuBe";

     public Logger logger = LoggerFactory.getLogger(ClientSignUpServiceImpl.class);

    @Autowired
    ClientSignUpRepo clientSignUpRepo;

    @Autowired
    SubscriptionService subscriptionService;

    @Autowired
    CounterGenerationService counterGenerationService;

    @Autowired
    CountryWisePriceDaoListService countryWisePriceDaoListService;
    @Autowired
    CountryWisePriceService countryWisePriceService;
    @Autowired
    TemplateMessageService templateMessageService;
    @Autowired
    CloudTemplateMasterService cloudTemplateMasterService;
    @Autowired
    SubscriptionPackageTransactionService subscriptionPackageTransactionService;
    @Autowired
    MongoTemplate mongoTemplate;

    @Override
    public ClientSignUpDao insert(ClientSignUpDao clientSignUpDao) {
        try {
            AccountMasterDao accountMasterDao = createAccountInAccountService(clientSignUpDao);
            logger.info("The account created is "+ accountMasterDao);
            boolean accountCreationStatus = createUserMasterDao(clientSignUpDao);
            if(accountCreationStatus){
                deleteRoleByAccountID(clientSignUpDao, accountMasterDao.getId());
                if(clientSignUpDao.isDefaultSubscription()){
                    addRole(clientSignUpDao,40);
                }else{
                    addRole(clientSignUpDao,44);
                }
                addRole(clientSignUpDao,37);
            }
            SubscriptionDao subscriptionDao = addSubscription(accountMasterDao, clientSignUpDao);
            logger.info("The subscription created is "+ subscriptionDao);
            CountryWisePriceDao countryWisePriceDao = addCountryWiseRates(clientSignUpDao, subscriptionDao.getAccountId());
            logger.info("The countryWisePriceDao created is "+ countryWisePriceDao);
            addRechargeAmount(subscriptionDao, 10);
            addingdefaultTemplate(clientSignUpDao);
            sendEmailToClient(clientSignUpDao);
            generateLeadTOLMS(accountMasterDao,clientSignUpDao);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return clientSignUpRepo.save(clientSignUpDao);
    }

    public void sendEmailToClient(ClientSignUpDao clientSignUpDao) {
        Response response = null;
        List<Map<Object,Object>> hashMapList = new ArrayList<>();
        Map<Object,Object> hashMsg = new HashMap<>();
        Map<Object,Object> hashmap = new HashMap<>();
        hashmap.put("id","0");
        hashmap.put("notifyType","ACCOUNTCREATION");
        hashMsg.put("accountId",clientSignUpDao.getId());
        hashMsg.put("balance",clientSignUpDao.getClientNumber());
        hashMsg.put("emailTo",clientSignUpDao.getEmail());
        hashMsg.put("emailCc", "provisioning@prpservices.in");
        hashMapList.add(hashMsg);
        hashmap.put("messages",hashMapList);
        String json = new Gson().toJson(hashmap);
        RequestBody body = RequestBody.create(json,MediaType.parse("application/json"));
        Request request = new Request.Builder()
                .url("http://test.chatmybot.in/gateway/alert/v1/api/send/alertMessage")
                .method("POST", body)
                .build();
        try {
            response = new OkHttpClient().newCall(request).execute();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }finally {
            response.close();
        }
    }

    private void generateLeadTOLMS(AccountMasterDao accountMasterDao, ClientSignUpDao clientSignUpDao) {
        Response response = null;
        String msg = "";
        if(clientSignUpDao.isDefaultSubscription()){
            msg = "Whatsapp Verified Account Created from Direct Sign Up with User ID: "+ accountMasterDao.getId() + " and with Demo Credtionals";
        }else{
            msg = "Whatsapp Verified Account Created from Direct Sign Up with User ID: "+ accountMasterDao.getId() + " and with Client Credtionals";
        }
       String url =  "http://chat.chatmybot.in/lmstree/allow/lead/1/insertLeadWebsite?phonenumber="+clientSignUpDao.getClientNumber()+"&leadsource=Direct_Sign_Up&email="+clientSignUpDao.getEmail()+"&description="+msg+"&firstName="+clientSignUpDao.getClientName()+"&companyName="+clientSignUpDao.getCompanyName()+"&product=Whatsapp&token="+websiteToken;
        Request request = new Request.Builder()
               .url(url)
               .method("GET",null)
               .build();
        try {
            response = new OkHttpClient().newCall(request).execute();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }finally {
            response.close();
        }
    }

    @Override
    public ResponseServiceDao sendTextSms(TextSMSSendingDao textSMSSendingDao) {
        ResponseServiceDao responseServiceDao = new ResponseServiceDao();
        Response response = null;
        String encodedMessage = "";
        try {
            encodedMessage = URLEncoder.encode(textSMSSendingDao.getMsg(), "UTF-8");
            encodedMessage = encodedMessage.replace("+", "%20");
            Request request = new Request.Builder()
                    .method("GET",null)
                    .url("http://prpsms.co.in/API/SendMsg.aspx?uname=20220446&pass=newsms&send="+textSMSSendingDao.getSenderId()+"&dest="+ textSMSSendingDao.getNumber() +"&msg="+ encodedMessage)
                    .build();
            response = new OkHttpClient().newCall(request).execute();
            responseServiceDao.setMessage(response.body().string());
            responseServiceDao.setStatus(1);
            return responseServiceDao;
        }
        catch(Exception e) {
            e.printStackTrace();
            responseServiceDao.setMessage("Error Occured while hitting API");
            responseServiceDao.setStatus(0);
            return responseServiceDao;
        }
        finally {
            response.close();
        }
    }

    @Override
    public List<ClientSignUpDao> getClient(ClientSignUpDao clientSignUpDao) {
        List<ClientSignUpDao> clientSIgnUpDaoList = clientSignUpRepo.findByClientNumberAndWhatsappNumber(clientSignUpDao.getClientNumber(), clientSignUpDao.getWhatsappNumber());
        List<ClientSignUpDao> clientByOnlyClientNumber = getClientByOnlyClientNumber(clientSignUpDao);
        if(clientByOnlyClientNumber != null || !clientByOnlyClientNumber.isEmpty()){
            clientSIgnUpDaoList.addAll(clientByOnlyClientNumber);
        }
        if(!clientSignUpDao.isDefaultSubscription()){
            List<ClientSignUpDao> clientByOnlyWhatsappNumber = getClientByOnlyWhatsappNumber(clientSignUpDao.getWhatsappNumber());
            if(clientByOnlyWhatsappNumber != null || !clientByOnlyWhatsappNumber.isEmpty()){
                clientSIgnUpDaoList.addAll(clientByOnlyWhatsappNumber);
            }
        }
        return clientSIgnUpDaoList;
    }

    @Override
    public List<ClientSignUpDao> getClientByOnlyClientNumber(ClientSignUpDao clientSignUpDao) {
        return clientSignUpRepo.findByCreateByAndClientNumber(clientSignUpDao.getCreateBy(),clientSignUpDao.getClientNumber());
    }

    @Override
    public List<ClientSignUpDao> getClientByOnlyWhatsappNumber(String number) {
        return clientSignUpRepo.findByWhatsappNumber(number);
    }

    @Override
    public ClientSignUpDao addSubscriptionPackageToClient(ClientSignUpDao clientSignUpDao, SubScriptionPackageDao subScriptionPackageDao) {
        clientSignUpDao.setAuthToken(subScriptionPackageDao.getAccessToken());
        deleteRoleByAccountID(clientSignUpDao, Long.parseLong(clientSignUpDao.getId()));
        if(subScriptionPackageDao.getPlanType() == PlanType.BASIC){
            subScriptionPackageDao.setRoleId(40);
        }else if(subScriptionPackageDao.getPlanType() == PlanType.STANDARD){
            subScriptionPackageDao.setRoleId(41);
        }else if(subScriptionPackageDao.getPlanType() == PlanType.ADVANCED){
            subScriptionPackageDao.setRoleId(42);
        }
        addRole(clientSignUpDao, subScriptionPackageDao.getRoleId());
        addRole(clientSignUpDao, 37);
        SubscriptionDao subscriptionDao = subscriptionService.getByActive(subScriptionPackageDao.getAccountId());
        if(subScriptionPackageDao.getSubscriptionPackageType()!= SubscriptionPackageType.ONLYCREDIT){
            addSubcriptionExpiry(subscriptionDao,subScriptionPackageDao);
        }
        addRechargeAmount(subscriptionDao, subScriptionPackageDao.getRechargeAmount());
        subscriptionPackageTransactionService.changeAccountSubscriptionStatus(subscriptionDao.getAccountId(), true);
        SubscriptionPackageTransactionDao subscriptionPackageTransactionDao = new SubscriptionPackageTransactionDao();
        subscriptionPackageTransactionDao.setAccountId(subscriptionDao.getAccountId());
        subscriptionPackageTransactionDao.setCreateDate(DateTiming.getDateRedable());
        subscriptionPackageTransactionDao.setCreateDateFilter(DateTiming.getDateFilterDateLong());
        subscriptionPackageTransactionDao.setSubScriptionPackageDao(subScriptionPackageDao);
        subscriptionPackageTransactionDao.setStatus(true);
        subscriptionPackageTransactionService.insert(subscriptionPackageTransactionDao);
        return clientSignUpDao;
    }

    private void addSubcriptionExpiry(SubscriptionDao subscriptionDao, SubScriptionPackageDao subScriptionPackageDao) {
        if(subScriptionPackageDao.getExpireyDateType() == ExpiryDateType.MONTHLY){
            subscriptionDao.setExpiry(DateTiming.increateDay(30));
        } else if (subScriptionPackageDao.getExpireyDateType() == ExpiryDateType.QUARTERLY) {
            subscriptionDao.setExpiry(DateTiming.increateDay(90));
        } else if (subScriptionPackageDao.getExpireyDateType() == ExpiryDateType.SIXMONTH) {
            subscriptionDao.setExpiry(DateTiming.increateDay(180));
        }else if(subScriptionPackageDao.getExpireyDateType() == ExpiryDateType.YEARLY) {
            subscriptionDao.setExpiry(DateTiming.increateDay(365));
        }
        subscriptionDao.setExpiryDateFilter(Integer.parseInt(subscriptionDao.getExpiry().replaceAll("-","")));
        subscriptionService.update(subscriptionDao);
    }

    @Override
    public ClientSignUpDao getById(String accountId) {
        return clientSignUpRepo.findById(accountId);
    }

    @Override
    public ClientSignUpDao update(ClientSignUpDao clientSignUpDao) {
        return clientSignUpRepo.save(clientSignUpDao);
    }

    @Override
    public List<ClientSignUpDao> get(FilterDao filterDao) {
        Query query = new Query();
        if(filterDao.getAccountId() == 0){
            query.addCriteria(Criteria.where("dateFilter").gte(Long.parseLong(filterDao.getStartdate())).lte(Long.parseLong(filterDao.getEnddate()))).with(Sort.by(Sort.Order.desc("dateFilter")));
        }else{
            query.addCriteria(Criteria.where("_id").is(String.valueOf(filterDao.getAccountId())).and("dateFilter").gte(Long.parseLong(filterDao.getStartdate())).lte(Long.parseLong(filterDao.getEnddate()))).with(Sort.by(Sort.Order.desc("dateFilter")));
        }
        return mongoTemplate.find(query,ClientSignUpDao.class,"ClientSignUpDao");
    }

    private void addRole(ClientSignUpDao clientSignUpDao, int roleId) {
        UserRoleDao userRoleDao = new UserRoleDao(Long.parseLong(clientSignUpDao.getId()),roleId);
        Response response = null;
        String responseString = "";
        String json = new Gson().toJson(userRoleDao);
        MediaType mediaType = MediaType.parse("application/json");
        okhttp3.RequestBody body = okhttp3.RequestBody.create(mediaType, json);
        Request request = new Request.Builder()
                    .url("https://wa.chatmybot.in/gateway/auth/v1/api/rolemapping/")
//                .url("http://62.171.169.159/gateway/auth/v1/api/rolemapping/")
//                .url("http://38.242.218.197/gateway/auth/v1/api/rolemapping/")
                .addHeader("Authorization","Bearer "+ clientSignUpDao.getAuthToken())
                .method("POST",body)
                .build();
        try {
            response = new OkHttpClient().newCall(request).execute();
            responseString = response.body().string();
            logger.info("The responseString is "+responseString);
            int responseCode = response.code();
            if(responseCode == 200 || responseCode == 201){
                logger.info("Role Added");
            }else{
                logger.info("Role is not Added");
            }
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }finally {
            response.close();
        }
    }
    private void deleteRoleByAccountID(ClientSignUpDao clientSignUpDao,long accountId) {
        Response response = null;
        String responseString = "";
        Request request = new Request.Builder()
//                .url("http://62.171.169.159/gateway/auth/v1/api/rolemapping/deletebyaccountid?accountId="+ accountId)
//                .url("http://38.242.218.197/gateway/auth/v1/api/rolemapping/deletebyaccountid?accountId="+ accountId)
                .url("https://wa.chatmybot.in/gateway/auth/v1/api/rolemapping/deletebyaccountid?accountId="+ accountId)
                .addHeader("Authorization","Bearer "+ clientSignUpDao.getAuthToken())
                .method("GET",null)
                .build();
        try {
            response = new OkHttpClient().newCall(request).execute();
            responseString = response.body().string();
            logger.info("The responseString is "+responseString);
            int responseCode = response.code();
            if(responseCode == 200 || responseCode == 201){
                logger.info("Role Deleted");
            }else{
                logger.info("Role is not Deleted");
            }
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }finally {
            response.close();
        }
    }

    private AccountMasterDao createAccountInAccountService(ClientSignUpDao clientSignUpDao){
        Response response = null;
        String responseString = "";
        AccountMasterDao accountMasterDao = null;
        try{
            accountMasterDao = new AccountMasterDao();
            accountMasterDao.setAccountName(clientSignUpDao.getClientName());
            accountMasterDao.setAccountPhone(clientSignUpDao.getClientNumber());
            accountMasterDao.setAccountEmail(clientSignUpDao.getEmail());
            accountMasterDao.setCreateAt(DateTiming.getDateRedable());
            accountMasterDao.setUpdateAt(DateTiming.getDateRedable());
            accountMasterDao.setCreateBy("20004");
            accountMasterDao.setUpdateBy("20004");
            accountMasterDao.setActive(true);
            accountMasterDao.setClientType("DEMO");
            accountMasterDao.setAccountLogo("https://wa.chatmybot.in/Shareablelinks/876/PRP LOGO WHITE (1).png");
            accountMasterDao.setAccountMinLogo("https://prpservices.in/img/content/mbl-logo.png");
            accountMasterDao.setFooter("PRP Services PVT LTD");
            accountMasterDao.setTitle("PRP Services PVT LTD");
            accountMasterDao.setCreditType(AccountsCreditType.SELF);
            List<ServiceDao> serviceDaoList = new ArrayList<>();
            WhatsappCreditDao whatsappCreditDao = new WhatsappCreditDao("",0,0, CreditType.PREPAID,DeductionType.SENT,0);
            ServiceDao serviceDao = new ServiceDao("WHATSAPPOFFICIAL",AccountType.PRODUCTION,ServiceType.EXPIRY,true,whatsappCreditDao);
            serviceDaoList.add(serviceDao);
            accountMasterDao.setServices(serviceDaoList);
            String json = new Gson().toJson(accountMasterDao);
            MediaType mediaType = MediaType.parse("application/json");
            okhttp3.RequestBody body = okhttp3.RequestBody.create(mediaType, json);
            Request request = new Request.Builder()
                    .url("https://wa.chatmybot.in/gateway/accounts/v1/account/signup")
//                    .url("http://62.171.169.159/gateway/accounts/v1/account/signup")
//                    .url("http://38.242.218.197/gateway/accounts/v1/account/signup")
                    .method("POST",body)
                    .build();
            response = new OkHttpClient().newCall(request).execute();
            responseString = response.body().string();
            int responseCode = response.code();
            if(responseCode == 200 || responseCode == 201){
                accountMasterDao = new Gson().fromJson(responseString, AccountMasterDao.class);
                logger.info("accountMasterDao1 is "+ accountMasterDao);
                clientSignUpDao.setId(String.valueOf(accountMasterDao.getId()));
                return accountMasterDao;
            }else{
                logger.info("The Account is not created. ERROR Occured");
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            response.close();
        }
        return accountMasterDao;
    }

    private boolean createUserMasterDao(ClientSignUpDao clientSignUpDao){
        Response response = null;
        UsermasterDao usermasterDao = new UsermasterDao();
        usermasterDao.setCreateBy("20004");
        usermasterDao.setFullname(clientSignUpDao.getCompanyName());
        usermasterDao.setId(Long.parseLong(clientSignUpDao.getId()));
        usermasterDao.setActive(true);
        usermasterDao.setPassword(clientSignUpDao.getClientNumber());
        usermasterDao.setAccountId(Long.parseLong(clientSignUpDao.getId()));
        usermasterDao.setEmail(clientSignUpDao.getEmail());
        usermasterDao.setPhone(clientSignUpDao.getClientNumber());
        usermasterDao.setUpdateBy("20004");
        usermasterDao.setAccountType(UserAccountType.ADMIN);
        usermasterDao.setServicesList("WHATSAPPOFFICIAL");

        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType,new Gson().toJson(usermasterDao));
        Request request = new Request.Builder()
                .url("https://wa.chatmybot.in/gateway/auth/v1/user/insertouter")
//                .url("http://62.171.169.159/gateway/auth/v1/user/insertouter")
//                .url("http://38.242.218.197/gateway/auth/v1/user/insertouter")
                .addHeader("Authorization","Bearer "+ clientSignUpDao.getAuthToken())
                .method("POST",body)
                .build();
        try {
            response = new OkHttpClient().newCall(request).execute();
            String strresponse = response.body().string();
            logger.info("The mysql account is created"+strresponse);
            if(response.code() == 200 || response.code() ==201){
                return true;
            }
        } catch (IOException e) {
            logger.info("error occured while creating mysql account");
            e.printStackTrace();
            return false;
        }
        finally {
            response.close();
        }
        return true;
    }


    private SubscriptionDao addSubscription(AccountMasterDao accountMasterDao, ClientSignUpDao clientSignUpDao) throws Exception {
        SubscriptionDao subscriptionDao1 = subscriptionService.getByActive(20511);
        SubscriptionDao subscriptionDao = new SubscriptionDao();
//        subscriptionDao = subscriptionDao1;
        subscriptionDao.setTransactionId(counterGenerationService.generateUid());
        subscriptionDao.setGateway(Gateway.CLOUDAPI);
        subscriptionDao.setAccountId(accountMasterDao.getId());
        subscriptionDao.setCreateBy("20004");
        subscriptionDao.setUpdateBy("20004");
        subscriptionDao.setEnablePriorityQueue(true);
        subscriptionDao.setCreateAt(DateTiming.getDateRedable());
        subscriptionDao.setUpdateAt(DateTiming.getDateRedable());
        subscriptionDao.setFreeTemplates(10);
        subscriptionDao.setFreeTemplatesMessage(0);
        subscriptionDao.setActive(true);
        subscriptionDao.setMaximumPerCampaign(1000);
        subscriptionDao.setSubscriptionAmt(0);
        subscriptionDao.setTemplateCreated(0);
        subscriptionDao.setAutoRenew(false);
        subscriptionDao.setEmailCc("seo@prpservices.in");
        subscriptionDao.setEmailTo("seo@prpservices.in");
        subscriptionDao.setWebhookEnable(false);
        subscriptionDao.setExpiryDateFilter(Integer.parseInt(DateTiming.increateDay(14).replaceAll("-","")));
        subscriptionDao.setExpiry(DateTiming.increateDay(14));
        subscriptionDao.setUpdateAtFilter(DateTiming.getDateFilterDateLong());
        if (clientSignUpDao.isDefaultSubscription()){
            subscriptionDao.setCloudAPIWhatsappID(subscriptionDao1.getCloudAPIWhatsappID());
                subscriptionDao.setCloudAPIAppID(subscriptionDao1.getCloudAPIAppID());
                subscriptionDao.setCloudAPIPhoneNumberID(subscriptionDao1.getCloudAPIPhoneNumberID());
                subscriptionDao.setCloudAPIToken(subscriptionDao1.getCloudAPIToken());
        }else{
            subscriptionDao.setCloudAPIWhatsappID(clientSignUpDao.getWhatsappId());
            subscriptionDao.setCloudAPIAppID(clientSignUpDao.getWhatsappAppId());
            subscriptionDao.setCloudAPIPhoneNumberID(clientSignUpDao.getWhatsappPhoneId());
            subscriptionDao.setCloudAPIToken(clientSignUpDao.getWhatsappVerifiedToken());
        }

        return subscriptionService.insert(subscriptionDao);
    }

    private CountryWisePriceDao addCountryWiseRates(ClientSignUpDao clientSignUpDao, long accountId){
        CountryWisePriceListDao countryWisePriceListDao = countryWisePriceDaoListService.getCountryPrice(clientSignUpDao.getCountry());
        CountryWisePriceDao countryWisePriceDao = new CountryWisePriceDao(countryWisePriceListDao.getCountryCode(),accountId,countryWisePriceListDao.getUserInitiatedRates(),countryWisePriceListDao.getBsnsInitiatedRates(),countryWisePriceListDao.getMarketingRates(),countryWisePriceListDao.getAuthenicationRates(),countryWisePriceListDao.getUtilityRates(),true);
        logger.info("In the addCountryWiseRates part countryWisePriceDao is "+ countryWisePriceDao);
        return countryWisePriceService.update(countryWisePriceDao);
    }
    private RechargeDao addRechargeAmount(SubscriptionDao subscriptionDao, double amount){
        Response response = null;
        try{
        RechargeDao rechargeDao = new RechargeDao(amount,"20004",DateTiming.getDateRedable(),RechareType.RECHARE,"Demo Recharge", subscriptionDao.getAccountId(), 0,0,DateTiming.dateFilterDate(),Services.WHATSAPPOFFICIAL);
        String json = new Gson().toJson(rechargeDao);
        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType,json);
        Request request = new Request.Builder()
//                .url("http://38.242.218.197/gateway/accounts/v1/recharge/signuprecharge")
                .url("https://wa.chatmybot.in/gateway/accounts/v1/recharge/signuprecharge")
                .method("POST",body)
                .build();
        response = new OkHttpClient().newCall(request).execute();
            String stringreponse = response.body().string();
            logger.info("The recharge service response is " + stringreponse);
            if(response.code() == 200 || response.code() == 201){
                rechargeDao = new Gson().fromJson(stringreponse, RechargeDao.class);
                return rechargeDao;
            }else {
               logger.info("Error occured");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        finally {
              response.close();
        }
        return null;
    }

   private TemplateMessageDao addingdefaultTemplate(ClientSignUpDao clientSignUpDao){
       TemplateMessageDao templateMessageDao = templateMessageService.findById(clientSignUpDao.getTemplateId());
       templateMessageDao.setAccountId(Long.parseLong(clientSignUpDao.getId()));
       templateMessageDao.setId(null);
       logger.info("The client SignUp Dao is in templateMessageDao after"+ templateMessageDao);
       return templateMessageService.insert(templateMessageDao);
   }


}
