package com.whatsappbuisness.wsbuissness.CombinePackadge.Subscription;

import com.whatsappbuisness.wsbuissness.CombinePackadge.Common.SessionRetrival.SessionRetrievalService;
import com.whatsappbuisness.wsbuissness.CombinePackadge.Common.SessionRetrival.UsermasterDao;
import com.whatsappbuisness.wsbuissness.CombinePackadge.CounterGeneration.CounterGenerationService;
import com.whatsappbuisness.wsbuissness.CombinePackadge.Datetiming.DateTiming;
import com.whatsappbuisness.wsbuissness.CombinePackadge.RabbitMqQue.MongoQue.MongoDB.MessagePersistServiceImpl;
import com.whatsappbuisness.wsbuissness.newagentsupport.assignNumberAgentSupportServiceImpl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

/*
 Author=Supreet Singh
 Date= 11/03/21 11:05 AM
 */
@RestController
@RequestMapping("/susbcription")
public class SubscriptionController {

	private static final Logger logger = LoggerFactory.getLogger(SubscriptionController.class);

	@Autowired
	SubscriptionService subscriptionService;
	@Autowired
	SessionRetrievalService sessionRetrievalService;
	@Autowired
	CounterGenerationService counterGenerationService;

	@Autowired
	assignNumberAgentSupportServiceImpl serviceImpl;
	@Autowired
	MessagePersistServiceImpl persistServiceImpl;


	@PostMapping("/insert")
	public SubscriptionDao insert(@RequestBody SubscriptionDao subscriptionDao, Authentication authentication) throws Exception {
		UsermasterDao usermasterDao = sessionRetrievalService.get(authentication);
		subscriptionDao.setTransactionId(counterGenerationService.generateUid());
		subscriptionDao.setCreateBy(String.valueOf(usermasterDao.getId()));
		subscriptionDao.setUpdateBy(String.valueOf(usermasterDao.getId()));
		subscriptionDao.setCreateAt(DateTiming.getDateRedable());
		subscriptionDao.setUpdateAt(DateTiming.getDateRedable());
		subscriptionDao.setUpdateAtFilter(DateTiming.getDateFilterDateLong());
		subscriptionDao.setExpiryDateFilter(Integer.parseInt(subscriptionDao.getExpiry().replaceAll("-","")));
		return subscriptionService.insert(subscriptionDao);
	}

	@PostMapping("/update")
	public SubscriptionDao update(@RequestBody SubscriptionDao subscriptionDao, Authentication authentication) throws Exception {
		UsermasterDao usermasterDao = sessionRetrievalService.get(authentication);

		subscriptionDao.setUpdateBy(String.valueOf(usermasterDao.getId()));
		subscriptionDao.setUpdateAt(DateTiming.getDateRedable());
		subscriptionDao.setUpdateAtFilter(DateTiming.getDateFilterDateLong());
		subscriptionDao.setExpiryDateFilter(Integer.parseInt(subscriptionDao.getExpiry().replaceAll("-","")));
		return subscriptionService.update(subscriptionDao);
	}

	@GetMapping("/getbyaccountId")
	public SubscriptionDao getSubscriptionDao(@RequestParam("id") long id) throws Exception {
		SubscriptionDao subscriptionDao = null;
		long parentId = serviceImpl.getAdminAccount(id);
	//	boolean enableAgentSupport = persistServiceImpl.getAgentSupportActive(parentId);
		SubscriptionDao subscription = subscriptionService.getByActive(parentId);
		if ((subscription.isEnableAgentSupport() && parentId != 20004)) {
			try {
				subscriptionDao = subscriptionService.getByActive(parentId);
			} catch (Exception ew) {
				subscriptionDao = null;
			}
			if (subscriptionDao == null) {
				throw new Exception("No Subscription Found");
			}
			return subscriptionDao;
		}else {
			try {
				subscriptionDao = subscriptionService.getByActive(id);
			} catch (Exception ew) {
				subscriptionDao = null;
			}
			if (subscriptionDao == null) {
				throw new Exception("No Subscription Found");
			}

			return subscriptionDao;

		}
	}


	@GetMapping("/getcurrent")
	public SubscriptionDao getSubscriptionDao(Authentication authentication) throws Exception {
		UsermasterDao usermasterDao = sessionRetrievalService.get(authentication);
		long parentId = serviceImpl.getAdminAccount(usermasterDao.getAccountId());
	//	boolean enableAgentSupport = persistServiceImpl.getAgentSupportActive(parentId);
		SubscriptionDao subscription = subscriptionService.getByActive(parentId);
		if (subscription.isEnableAgentSupport() && parentId != 20004) {
			return subscriptionService.getByActive(parentId);
		}else {
			return subscriptionService.getByActive(usermasterDao.getAccountId());
		}
	}

	@GetMapping("/autoexpire")
	public String autoExpire(@RequestParam("password") String password) {
		if (password.equalsIgnoreCase("supreet@sforevery")) {
			subscriptionService.expireSubscriptions();
			return "All Ok";
		} else {
			return "Bad Password";
		}


	}

	@GetMapping("/restorelatest")
	SubscriptionDao restoreLatestSubscription(@RequestParam("accountId") long accountId){
		return subscriptionService.restoreLatestSubscription(accountId);
	}


}
