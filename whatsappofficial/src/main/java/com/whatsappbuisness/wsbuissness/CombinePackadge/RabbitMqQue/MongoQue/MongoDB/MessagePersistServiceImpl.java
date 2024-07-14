package com.whatsappbuisness.wsbuissness.CombinePackadge.RabbitMqQue.MongoQue.MongoDB;
/*
 Author=Supreet Singh
 Date= 06/02/21 11:22 PM
 */


import com.google.gson.Gson;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.whatsappbuisness.wsbuissness.CombinePackadge.ApiService.Media.MediaService;
import com.whatsappbuisness.wsbuissness.CombinePackadge.ConvertUnferified.UnVerifiedEntity;
import com.whatsappbuisness.wsbuissness.CombinePackadge.ConvertUnferified.UnverifiedMessages;
import com.whatsappbuisness.wsbuissness.CombinePackadge.Datetiming.DateTiming;
import com.whatsappbuisness.wsbuissness.CombinePackadge.Filtering.FilterDao;
import com.whatsappbuisness.wsbuissness.CombinePackadge.MessageEntry.MessageFilterDao;
import com.whatsappbuisness.wsbuissness.CombinePackadge.Messages.ChatSide;
import com.whatsappbuisness.wsbuissness.CombinePackadge.Messages.Gateway;
import com.whatsappbuisness.wsbuissness.CombinePackadge.Messages.MessageDao;
import com.whatsappbuisness.wsbuissness.CombinePackadge.Messages.MessageStatus;
import com.whatsappbuisness.wsbuissness.CombinePackadge.Messages.MessageType;
import com.whatsappbuisness.wsbuissness.CombinePackadge.PaginationDao;
import com.whatsappbuisness.wsbuissness.CombinePackadge.RabbitMqQue.MongoQue.Conversation.ConversationDao;
import com.whatsappbuisness.wsbuissness.CombinePackadge.RabbitMqQue.MongoQue.Conversation.ConversationRepo;
import com.whatsappbuisness.wsbuissness.CombinePackadge.RabbitMqQue.MongoQue.Conversation.ConversationServiceImpl;
import com.whatsappbuisness.wsbuissness.CombinePackadge.RabbitMqQue.QueName;
import com.whatsappbuisness.wsbuissness.CombinePackadge.RabbitMqQue.RabbitMqqueService;
import com.whatsappbuisness.wsbuissness.CombinePackadge.RabbitMqQue.Webhooks.Webhook.DlrUpdatesDao;
import com.whatsappbuisness.wsbuissness.CombinePackadge.RabbitMqQue.Webhooks.Webhook.WebhookDao;
import com.whatsappbuisness.wsbuissness.CombinePackadge.Subscription.SubscriptionDao;
import com.whatsappbuisness.wsbuissness.CombinePackadge.Subscription.SubscriptionRepo;
import com.whatsappbuisness.wsbuissness.CombinePackadge.Subscription.SubscriptionService;
import com.whatsappbuisness.wsbuissness.newagentsupport.Constant;
import com.whatsappbuisness.wsbuissness.newagentsupport.assignNumberAgentSupportService;

import okhttp3.*;

import org.bson.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.GroupOperation;
import org.springframework.data.mongodb.core.aggregation.MatchOperation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.*;

@Service
public class MessagePersistServiceImpl implements MessagePersistService {
	private static final Logger logger = LoggerFactory.getLogger(MessagePersistService.class);
	private static final String collectionName = "MessageDocument_";
	@Autowired
	MongoTemplate mongoTemplate;
	@Autowired
	RabbitMqqueService rabbitMqqueService;

	@Autowired
	SubscriptionService subscriptionService;
	@Autowired
	ConversationRepo conversationRepo;

	@Autowired
	assignNumberAgentSupportService agentSupportService;

	@Autowired
	MediaService mediaService;
	@Autowired
    SubscriptionRepo subscriptionRepo;


	private final String COLLECTION_NAME = "MessageDocument_";

	public static final String collectionBaseName = "AccountMasterDocument";

	//	public static final OkHttpClient client = new OkHttpClient.Builder()
	//			.readTimeout(20, TimeUnit.SECONDS)
	//			.writeTimeout(20, TimeUnit.SECONDS)
	//			.connectTimeout(20, TimeUnit.SECONDS)
	//			.connectionPool(new ConnectionPool(70, 60, TimeUnit.SECONDS))
	//			.build();
	public static final OkHttpClient client = new OkHttpClient.Builder()
			.readTimeout(20, TimeUnit.SECONDS)
			.writeTimeout(20, TimeUnit.SECONDS)
			.connectTimeout(20, TimeUnit.SECONDS)
			.connectionPool(new ConnectionPool(55, 60, TimeUnit.SECONDS))
			.build();


	void updateDlr(MessageDao messageDao) {
		DlrUpdatesDao dlrUpdatesDao = new DlrUpdatesDao();
		dlrUpdatesDao.setId(messageDao.getId());
		dlrUpdatesDao.setRecipient_id(messageDao.getTo());
		dlrUpdatesDao.setAccountId(messageDao.getAccountId());
		dlrUpdatesDao.setStatus(messageDao.getMessageStatus());
		rabbitMqqueService.pushToQue(QueName.DLR_Que.routing, QueName.DLR_Que.queName, dlrUpdatesDao);


	}

	@Override
	public MessageDao insert(MessageDao messageDao) throws CloneNotSupportedException {
		
		MessageStatus messageStatus = messageDao.getMessageStatus();
		String date = DateTiming.getDateRedable();
		int dateFilter = DateTiming.getDateFilterDate();
		long dateTimingLong = DateTiming.getDateFilterDateLong();
		messageDao.setDate(date);
		messageDao.setDateFilterLong(dateTimingLong);
		messageDao.setDateFilter(dateFilter);
		messageDao.setMessageStatus(MessageStatus.PENDING);
		messageDao = mongoTemplate.insert(messageDao, MessagePersistServiceImpl.collectionName + messageDao.getAccountId());
		logger.info("for agent Agent support");
		SubscriptionDao subscriptionDao = subscriptionService.getByActive(messageDao.getAccountId());
	//	boolean enableAgentSupport = getAgentSupportActive(messageDao.getAccountId());
		if (Boolean.TRUE.equals(subscriptionDao.isEnableAgentSupport()) && messageDao.getType() != MessageType.template) {
			logger.info("Hit the method for Enable Agent Support:" +subscriptionDao.isEnableAgentSupport());
			agentSupportService.insertMessageAgentAccount(messageDao);
		}
		logger.info("for agent enable AgentSupport");
		messageDao.setMessageStatus(messageStatus);
		updateDlr(messageDao);
		Update update = new Update();
		update.inc("totalMessage", 1);
		update.set("lastMessageType", messageDao.getType());
		update.set("lastMessage", date);
		update.set("lastMessageTime", dateTimingLong);
		if (messageDao.getChatSide() == ChatSide.Client) {

			//            if (messageDao.getAccountId() == 20511) {
			//                logger.info("Will Push Webhook Test");
			//                // rabbitMqqueService.pushToQue(QueName.Webhook_Que.routing, QueName.DLR_Que.queName, new WebhookDao(messageDao,new DlrUpdatesDao(),"https://gogreenbot.free.beeceptor.com"));
			//
			//                hitApi(new WebhookDao(messageDao, new DlrUpdatesDao(), "http://173.212.247.177:3001/test/"));
			//                logger.info("Push SuccessFull To Que Webhook Test");
			//
			//            }
			if(messageDao.getAccountId()==20055)
			{
				hitApiServer(new WebhookDao(messageDao, new DlrUpdatesDao(), "http://chat.chatmybot.in/lmstree/api/v1/waofficial/2022/?accessToken=8d264511-39ac-4b5f-bd41-5babc3fjkkjkk2"));
			}
			if(messageDao.getAccountId()==20062)
			{
				hitApi(new WebhookDao(messageDao, new DlrUpdatesDao(), "http://173.212.247.177:3001/spectrum/"));
			}




			update.set("unread", true);
			update.inc("unreadMessage", 1);
		}
		else if(messageDao.getChatSide()==ChatSide.User){
			logger.info("chat side user");
			// Now, you have a separate, independent copy of the original object as outWebhhokMsgDao.
			MessageDao outWebhhokMsgDao = (MessageDao) messageDao.clone();
			if(outWebhhokMsgDao.getSubscriptionDao().isOutWebhookEnable()){
				logger.info("outwebhook enabled");
				hitApi(new WebhookDao(outWebhhokMsgDao, new DlrUpdatesDao(), messageDao.getSubscriptionDao().getOutWebhookUrl()));
			}
			update.set("unread", false);

		}
		else {
			update.set("unread", false);
		}

		//original object
		mongoTemplate.upsert(new Query(Criteria.where("_id").is(messageDao.getTo())), update, ConversationDao.class, ConversationServiceImpl.collectionBaseName + messageDao.getAccountId());
		if (Boolean.TRUE.equals(subscriptionDao.isEnableAgentSupport() && messageDao.getType() != MessageType.template)) {
			logger.info("Hit the method for Enable Agent Support:" +subscriptionDao.isEnableAgentSupport());
			agentSupportService.updateMessageConversion(messageDao,update);
		}
		return messageDao;
	}
	@Override
	public MessageDao updateDlr(String id, MessageStatus messageStatus, long accountId) {

		Query query = new Query();
		query.addCriteria(Criteria.where("_id").is(id));
		Update update = new Update();
		update.set("messageStatus", messageStatus);
		if (messageStatus == MessageStatus.DLV) {
			update.set("dlrTime", DateTiming.getDateRedable());

		}
		if (messageStatus == MessageStatus.VIEW) {

			update.set("viewTime", DateTiming.getDateRedable());
		}
		MessageDao messageDao = mongoTemplate.findAndModify(query, update, MessageDao.class, MessagePersistServiceImpl.collectionName + accountId);
		return messageDao;
	}

	@Override
	public List<GroupByMongoDao> get(long accountId, long campaingId) {

		GroupOperation groupByStateAndSumPop = group("messageStatus")
				.count().as("count");
		MatchOperation filterStates = match(Criteria.where("campaingId").is(campaingId));
		Aggregation aggregation = newAggregation(filterStates, groupByStateAndSumPop);

		AggregationResults<GroupByMongoDao> alTemp = mongoTemplate.aggregate(aggregation, MessagePersistServiceImpl.collectionName + accountId, GroupByMongoDao.class);
		return alTemp.getMappedResults();
	}

	@Override
	public List<GroupByMongoDao> get(long accountId, int startdate, int enddate) {
		logger.info("Will Find Report of accountId {} startdate {} enddate {}",accountId,startdate,enddate);
		GroupOperation groupByStateAndSumPop = group("messageStatus")
				.count().as("count");
		MatchOperation filterStates = match(Criteria.where("dateFilter").gt(startdate).lte(enddate).and("chatSide").is("User"));
		Aggregation aggregation = newAggregation(filterStates, groupByStateAndSumPop);
		AggregationResults<GroupByMongoDao> alTemp = mongoTemplate.aggregate(aggregation, MessagePersistServiceImpl.collectionName + accountId, GroupByMongoDao.class);
		return alTemp.getMappedResults();
	}

	@Override
	public List<MessageDao> getReport(long campaingId, long accountId) {
		Query query = new Query(Criteria.where("campaingId").is(campaingId));
		return mongoTemplate.find(query, MessageDao.class, MessagePersistServiceImpl.collectionName + accountId);
	}

	@Override
	public List<MessageDao> getReport(FilterDao filterDao) {

		Query query = new Query(Criteria.where("to").is(filterDao.getDst())).with(PageRequest.of(filterDao.getOffset(), filterDao.getLimit(), Sort.by(Sort.Order.desc("dateFilterLong"))));

		return mongoTemplate.find(query, MessageDao.class, MessagePersistServiceImpl.collectionName + filterDao.getAccountId());

	}

	@Override
	public List<MessageDao> getMessages(FilterDao filterDao) {
		Query query = new Query();
		if (!filterDao.getDst().equals("0")) {
			query.addCriteria(Criteria.where("to").is(filterDao.getDst()));
		}
		if (!filterDao.getStartdate().equals("0")) {
			query.addCriteria(Criteria.where("dateFilterLong").gte(Long.parseLong(filterDao.getStartdate())));
		}
		if (!filterDao.getEnddate().equals("0")) {
			query.addCriteria(Criteria.where("dateFilterLong").lte(Long.parseLong(filterDao.getEnddate())));
		}
		query.with(PageRequest.of(filterDao.getOffset(), filterDao.getLimit(), Sort.by(Sort.Order.desc("dateFilterLong"))));
		return mongoTemplate.find(query,MessageDao.class,MessagePersistServiceImpl.collectionName+filterDao.getAccountId());
	}

	@Override
	public List<MessageDao> getafterDate(long accountId, long timing, String to) {
		Query query = new Query(Criteria.where("to").is(to).and("dateFilterLong").gt(timing)).with(Sort.by(Sort.Order.desc("dateFilterLong")));

		return mongoTemplate.find(query, MessageDao.class, MessagePersistServiceImpl.collectionName + accountId);

	}

	@Override
	public List<MessageDeliveryStatusDao> getDelieveryStatusDateBasis(String startDate, String endDate, long accountId) {
		startDate = startDate +" "+"000000";
		endDate = endDate+" "+"235959";
		MessageDeliveryStatusDao messageIdDeliveryStatusDao;
		List<MessageDeliveryStatusDao> messageIdDeliveryStatusDaoList = new ArrayList<MessageDeliveryStatusDao>();
		Query query = new Query();
		long startDateLong = Long.parseLong(startDate.replaceAll("-", "").replaceAll(" ", "").replaceAll(":", ""));
		long endDateLong = Long.parseLong(endDate.replaceAll("-", "").replaceAll(" ", "").replaceAll(":", ""));
		logger.info("The value of startDateLong and endDateLong is "+ startDateLong + "and endDateLong is "+ endDateLong);
		query.addCriteria(Criteria.where("dateFilterLong").gte(startDateLong).lte(endDateLong));
		List<MessageDao> messageDaos = mongoTemplate.find(query, MessageDao.class, MessagePersistServiceImpl.collectionName + accountId);
		logger.info("The value of messageDaos.size() is "+messageDaos.size());
		for(int i =0; i<messageDaos.size();i++){
			messageIdDeliveryStatusDao = new MessageDeliveryStatusDao();
			messageIdDeliveryStatusDao.setMessageStatus(messageDaos.get(i).getMessageStatus());
			messageIdDeliveryStatusDao.setDst(messageDaos.get(i).getTo());
			messageIdDeliveryStatusDao.setMessageId(messageDaos.get(i).getMessageId());
			messageIdDeliveryStatusDaoList.add(messageIdDeliveryStatusDao);
		}
		return messageIdDeliveryStatusDaoList;
	}

	@Override
	public void webhookCalling(MessageDao messageDao, DlrUpdatesDao dlrUpdatesDao) throws Exception {
		SubscriptionDao subscriptionDao = null;
		if(messageDao != null) {
			subscriptionDao = subscriptionService.getByActive(messageDao.getAccountId());
			if (subscriptionDao != null) {
				if (subscriptionDao.getGateway() == Gateway.CLOUDAPI) {
					if (subscriptionDao.isWebhookEnable()) {
						logger.info("When Webhook Enable....");
						Query query = new Query(Criteria.where("_id").is(messageDao.getId()));
						Update update = new Update();
						if (messageDao.getImage() != null) {
							String shareableURL = mediaService.getMultipartFileByCloudAPI(messageDao.getImage().getId(), subscriptionDao, messageDao.getImage().getMime_type());
							messageDao.getImage().setLink(shareableURL);
							update.set("image.link", shareableURL);
							mongoTemplate.updateFirst(query, update, MessageDao.class, COLLECTION_NAME + messageDao.getAccountId());
						}
						if (messageDao.getDocument() != null) {
							String shareableURL = mediaService.getMultipartFileByCloudAPI(messageDao.getDocument().getId(), subscriptionDao, messageDao.getDocument().getMime_type());
							messageDao.getDocument().setLink(shareableURL);
							update.set("document.link", shareableURL);
							mongoTemplate.updateFirst(query, update, MessageDao.class, COLLECTION_NAME + messageDao.getAccountId());
						}
						if (messageDao.getAudio() != null) {
							String shareableURL = mediaService.getMultipartFileByCloudAPI(messageDao.getAudio().getId(), subscriptionDao, messageDao.getAudio().getMime_type());
							messageDao.getAudio().setLink(shareableURL);
							update.set("audio.link", shareableURL);
							mongoTemplate.updateFirst(query, update, MessageDao.class, COLLECTION_NAME + messageDao.getAccountId());
						}
						if (messageDao.getVideo() != null) {
							String shareableURL = mediaService.getMultipartFileByCloudAPI(messageDao.getVideo().getId(), subscriptionDao, messageDao.getVideo().getMime_type());
							messageDao.getVideo().setLink(shareableURL);
							update.set("video.link", shareableURL);
							mongoTemplate.updateFirst(query, update, MessageDao.class, COLLECTION_NAME + messageDao.getAccountId());
						}
						WebhookDao webhookDao = new WebhookDao(messageDao, null, subscriptionDao.getWebhookURL());
						callingExternalUrl(webhookDao, subscriptionDao.getWebhookURL());

					}
					if (subscriptionDao.isEnableConvertUnverified()) {
						logger.info("When Convert Unverified Enable....");
						Query query = new Query(Criteria.where("_id").is(messageDao.getId()));
						Update update = new Update();
						if (messageDao.getImage() != null) {
							String shareableURL = mediaService.getMultipartFileByCloudAPI(messageDao.getImage().getId(), subscriptionDao, messageDao.getImage().getMime_type());
							messageDao.getImage().setLink(shareableURL);
							update.set("image.link", shareableURL);
							mongoTemplate.updateFirst(query, update, MessageDao.class, COLLECTION_NAME + messageDao.getAccountId());
						}
						if (messageDao.getDocument() != null) {
							String shareableURL = mediaService.getMultipartFileByCloudAPI(messageDao.getDocument().getId(), subscriptionDao, messageDao.getDocument().getMime_type());
							messageDao.getDocument().setLink(shareableURL);
							update.set("document.link", shareableURL);
							mongoTemplate.updateFirst(query, update, MessageDao.class, COLLECTION_NAME + messageDao.getAccountId());
						}
						if (messageDao.getAudio() != null) {
							String shareableURL = mediaService.getMultipartFileByCloudAPI(messageDao.getAudio().getId(), subscriptionDao, messageDao.getAudio().getMime_type());
							messageDao.getAudio().setLink(shareableURL);
							update.set("audio.link", shareableURL);
							mongoTemplate.updateFirst(query, update, MessageDao.class, COLLECTION_NAME + messageDao.getAccountId());
						}
						if (messageDao.getVideo() != null) {
							String shareableURL = mediaService.getMultipartFileByCloudAPI(messageDao.getVideo().getId(), subscriptionDao, messageDao.getVideo().getMime_type());
							messageDao.getVideo().setLink(shareableURL);
							update.set("video.link", shareableURL);
							mongoTemplate.updateFirst(query, update, MessageDao.class, COLLECTION_NAME + messageDao.getAccountId());
						}
						UnVerifiedEntity unVerifiedEntity = convertObjectUnferified(messageDao);
						callConvertedUnverifiedUrl(unVerifiedEntity, subscriptionDao.getConvertUnverifiedUrl());

					}
					if (!subscriptionDao.isWebhookEnable() && subscriptionDao.isMediaStoretoDriveStatus()) {
						logger.info("When Store Data to DB is Enable....");
						Query query = new Query(Criteria.where("_id").is(messageDao.getId()));
						Update update = new Update();
						if (messageDao.getImage() != null) {
							String shareableURL = mediaService.getMultipartFileByCloudAPI(messageDao.getImage().getId(), subscriptionDao, messageDao.getImage().getMime_type());
							messageDao.getImage().setLink(shareableURL);
							update.set("image.link", shareableURL);
							mongoTemplate.updateFirst(query, update, MessageDao.class, COLLECTION_NAME + messageDao.getAccountId());
						}
						if (messageDao.getDocument() != null) {
							String shareableURL = mediaService.getMultipartFileByCloudAPI(messageDao.getDocument().getId(), subscriptionDao, messageDao.getDocument().getMime_type());
							messageDao.getDocument().setLink(shareableURL);
							update.set("document.link", shareableURL);
							mongoTemplate.updateFirst(query, update, MessageDao.class, COLLECTION_NAME + messageDao.getAccountId());
						}
						if (messageDao.getAudio() != null) {
							String shareableURL = mediaService.getMultipartFileByCloudAPI(messageDao.getAudio().getId(), subscriptionDao, messageDao.getAudio().getMime_type());
							messageDao.getAudio().setLink(shareableURL);
							update.set("audio.link", shareableURL);
							mongoTemplate.updateFirst(query, update, MessageDao.class, COLLECTION_NAME + messageDao.getAccountId());
						}
						if (messageDao.getVideo() != null) {
							String shareableURL = mediaService.getMultipartFileByCloudAPI(messageDao.getVideo().getId(), subscriptionDao, messageDao.getVideo().getMime_type());
							messageDao.getVideo().setLink(shareableURL);
							update.set("video.link", shareableURL);
							mongoTemplate.updateFirst(query, update, MessageDao.class, COLLECTION_NAME + messageDao.getAccountId());
						}
					} else {
						logger.info("The Webhook is not Configured and also msg not store in DB");
					}
				}
				if (subscriptionDao.getGateway() == Gateway.AMEYO) {

					if (subscriptionDao.isWebhookEnable()) {
						Query query = new Query(Criteria.where("_id").is(messageDao.getId()));
						Update update = new Update();
						if (messageDao.getImage() != null) {
							String shareableURL = mediaService.getMultipartFileByAmeyoServer(messageDao.getImage().getId(), subscriptionDao, messageDao.getImage().getMime_type());
							messageDao.getImage().setLink(shareableURL);
							update.set("image.link", shareableURL);
							mongoTemplate.updateFirst(query, update, MessageDao.class, COLLECTION_NAME + messageDao.getAccountId());
						}
						if (messageDao.getDocument() != null) {
							String shareableURL = mediaService.getMultipartFileByAmeyoServer(messageDao.getDocument().getId(), subscriptionDao, messageDao.getDocument().getMime_type());
							messageDao.getDocument().setLink(shareableURL);
							update.set("document.link", shareableURL);
							mongoTemplate.updateFirst(query, update, MessageDao.class, COLLECTION_NAME + messageDao.getAccountId());
						}
						if (messageDao.getAudio() != null) {
							String shareableURL = mediaService.getMultipartFileByAmeyoServer(messageDao.getAudio().getId(), subscriptionDao, messageDao.getAudio().getMime_type());
							messageDao.getAudio().setLink(shareableURL);
							update.set("audio.link", shareableURL);
							mongoTemplate.updateFirst(query, update, MessageDao.class, COLLECTION_NAME + messageDao.getAccountId());
						}
						if (messageDao.getVideo() != null) {
							String shareableURL = mediaService.getMultipartFileByAmeyoServer(messageDao.getVideo().getId(), subscriptionDao, messageDao.getVideo().getMime_type());
							messageDao.getVideo().setLink(shareableURL);
							update.set("video.link", shareableURL);
							mongoTemplate.updateFirst(query, update, MessageDao.class, COLLECTION_NAME + messageDao.getAccountId());
						}
						WebhookDao webhookDao = new WebhookDao(messageDao, null, subscriptionDao.getWebhookURL());
						callingExternalUrl(webhookDao, subscriptionDao.getWebhookURL());

					}
					if (!subscriptionDao.isWebhookEnable() && subscriptionDao.isMediaStoretoDriveStatus()) {
						Query query = new Query(Criteria.where("_id").is(messageDao.getId()));
						Update update = new Update();
						if (messageDao.getImage() != null) {
							String shareableURL = mediaService.getMultipartFileByAmeyoServer(messageDao.getImage().getId(), subscriptionDao, messageDao.getImage().getMime_type());
							messageDao.getImage().setLink(shareableURL);
							update.set("image.link", shareableURL);
							mongoTemplate.updateFirst(query, update, MessageDao.class, COLLECTION_NAME + messageDao.getAccountId());
						}
						if (messageDao.getDocument() != null) {
							String shareableURL = mediaService.getMultipartFileByAmeyoServer(messageDao.getDocument().getId(), subscriptionDao, messageDao.getDocument().getMime_type());
							messageDao.getDocument().setLink(shareableURL);
							update.set("document.link", shareableURL);
							mongoTemplate.updateFirst(query, update, MessageDao.class, COLLECTION_NAME + messageDao.getAccountId());
						}
						if (messageDao.getAudio() != null) {
							String shareableURL = mediaService.getMultipartFileByAmeyoServer(messageDao.getAudio().getId(), subscriptionDao, messageDao.getAudio().getMime_type());
							messageDao.getAudio().setLink(shareableURL);
							update.set("audio.link", shareableURL);
							mongoTemplate.updateFirst(query, update, MessageDao.class, COLLECTION_NAME + messageDao.getAccountId());
						}
						if (messageDao.getVideo() != null) {
							String shareableURL = mediaService.getMultipartFileByAmeyoServer(messageDao.getVideo().getId(), subscriptionDao, messageDao.getVideo().getMime_type());
							messageDao.getVideo().setLink(shareableURL);
							update.set("video.link", shareableURL);
							mongoTemplate.updateFirst(query, update, MessageDao.class, COLLECTION_NAME + messageDao.getAccountId());
						}
					} else {
						logger.info("The Webhook is not Configured and also msg not store in DB");
					}
				}
				if (subscriptionDao.getGateway() == Gateway.KARIX) {
					if (subscriptionDao.isWebhookEnable()) {
						Query query = new Query(Criteria.where("_id").is(messageDao.getId()));
						Update update = new Update();
						if (messageDao.getImage() != null) {
							String shareableURL = mediaService.getMultipartFileByKarixServer(messageDao.getImage().getId(), subscriptionDao, messageDao.getImage().getMime_type());
							messageDao.getImage().setLink(shareableURL);
							update.set("image.link", shareableURL);
							mongoTemplate.updateFirst(query, update, MessageDao.class, COLLECTION_NAME + messageDao.getAccountId());
						}
						if (messageDao.getDocument() != null) {
							String shareableURL = mediaService.getMultipartFileByKarixServer(messageDao.getDocument().getId(), subscriptionDao, messageDao.getDocument().getMime_type());
							messageDao.getDocument().setLink(shareableURL);
							update.set("document.link", shareableURL);
							mongoTemplate.updateFirst(query, update, MessageDao.class, COLLECTION_NAME + messageDao.getAccountId());
						}
						if (messageDao.getAudio() != null) {
							String shareableURL = mediaService.getMultipartFileByKarixServer(messageDao.getAudio().getId(), subscriptionDao, messageDao.getAudio().getMime_type());
							messageDao.getAudio().setLink(shareableURL);
							update.set("audio.link", shareableURL);
							mongoTemplate.updateFirst(query, update, MessageDao.class, COLLECTION_NAME + messageDao.getAccountId());
						}
						if (messageDao.getVideo() != null) {
							String shareableURL = mediaService.getMultipartFileByKarixServer(messageDao.getVideo().getId(), subscriptionDao, messageDao.getVideo().getMime_type());
							messageDao.getVideo().setLink(shareableURL);
							update.set("video.link", shareableURL);
							mongoTemplate.updateFirst(query, update, MessageDao.class, COLLECTION_NAME + messageDao.getAccountId());
						}
						WebhookDao webhookDao = new WebhookDao(messageDao, null, subscriptionDao.getWebhookURL());
						callingExternalUrl(webhookDao, subscriptionDao.getWebhookURL());
					}
					if (subscriptionDao.isEnableConvertUnverified()) {
						logger.info("When Convert Unverified Enable....");
						Query query = new Query(Criteria.where("_id").is(messageDao.getId()));
						Update update = new Update();
						if (messageDao.getImage() != null) {
							String shareableURL = mediaService.getMultipartFileByCloudAPI(messageDao.getImage().getId(), subscriptionDao, messageDao.getImage().getMime_type());
							messageDao.getImage().setLink(shareableURL);
							update.set("image.link", shareableURL);
							mongoTemplate.updateFirst(query, update, MessageDao.class, COLLECTION_NAME + messageDao.getAccountId());
						}
						if (messageDao.getDocument() != null) {
							String shareableURL = mediaService.getMultipartFileByCloudAPI(messageDao.getDocument().getId(), subscriptionDao, messageDao.getDocument().getMime_type());
							messageDao.getDocument().setLink(shareableURL);
							update.set("document.link", shareableURL);
							mongoTemplate.updateFirst(query, update, MessageDao.class, COLLECTION_NAME + messageDao.getAccountId());
						}
						if (messageDao.getAudio() != null) {
							String shareableURL = mediaService.getMultipartFileByCloudAPI(messageDao.getAudio().getId(), subscriptionDao, messageDao.getAudio().getMime_type());
							messageDao.getAudio().setLink(shareableURL);
							update.set("audio.link", shareableURL);
							mongoTemplate.updateFirst(query, update, MessageDao.class, COLLECTION_NAME + messageDao.getAccountId());
						}
						if (messageDao.getVideo() != null) {
							String shareableURL = mediaService.getMultipartFileByCloudAPI(messageDao.getVideo().getId(), subscriptionDao, messageDao.getVideo().getMime_type());
							messageDao.getVideo().setLink(shareableURL);
							update.set("video.link", shareableURL);
							mongoTemplate.updateFirst(query, update, MessageDao.class, COLLECTION_NAME + messageDao.getAccountId());
						}

						UnVerifiedEntity unVerifiedEntity = convertObjectUnferified(messageDao);
						callConvertedUnverifiedUrl(unVerifiedEntity, subscriptionDao.getConvertUnverifiedUrl());

					}
					if (!subscriptionDao.isWebhookEnable() && subscriptionDao.isMediaStoretoDriveStatus()) {
						Query query = new Query(Criteria.where("_id").is(messageDao.getId()));
						Update update = new Update();
						if (messageDao.getImage() != null) {
							String shareableURL = mediaService.getMultipartFileByKarixServer(messageDao.getImage().getId(), subscriptionDao, messageDao.getImage().getMime_type());
							messageDao.getImage().setLink(shareableURL);
							update.set("image.link", shareableURL);
							mongoTemplate.updateFirst(query, update, MessageDao.class, COLLECTION_NAME + messageDao.getAccountId());
						}
						if (messageDao.getDocument() != null) {
							String shareableURL = mediaService.getMultipartFileByKarixServer(messageDao.getDocument().getId(), subscriptionDao, messageDao.getDocument().getMime_type());
							messageDao.getDocument().setLink(shareableURL);
							update.set("document.link", shareableURL);
							mongoTemplate.updateFirst(query, update, MessageDao.class, COLLECTION_NAME + messageDao.getAccountId());
						}
						if (messageDao.getAudio() != null) {
							String shareableURL = mediaService.getMultipartFileByKarixServer(messageDao.getAudio().getId(), subscriptionDao, messageDao.getAudio().getMime_type());
							messageDao.getAudio().setLink(shareableURL);
							update.set("audio.link", shareableURL);
							mongoTemplate.updateFirst(query, update, MessageDao.class, COLLECTION_NAME + messageDao.getAccountId());
						}
						if (messageDao.getVideo() != null) {
							String shareableURL = mediaService.getMultipartFileByKarixServer(messageDao.getVideo().getId(), subscriptionDao, messageDao.getVideo().getMime_type());
							messageDao.getVideo().setLink(shareableURL);
							update.set("video.link", shareableURL);
							mongoTemplate.updateFirst(query, update, MessageDao.class, COLLECTION_NAME + messageDao.getAccountId());
						}
					} else {
						logger.info("The Webhook is not Configured and also msg not store in DB");
					}
				}
			} else {
				logger.info("Incoming Msg will not store because no Subscription found for accountid "+ messageDao.getAccountId());
			}
		}else{
			subscriptionDao = subscriptionService.getByActive(dlrUpdatesDao.getAccountId());
			if(subscriptionDao!=null){
				if(subscriptionDao.isWebhookEnable()){
					dlrUpdatesDao.setMessageTime(DateTiming.getDateRedable());
					WebhookDao webhookDao = new WebhookDao(null,dlrUpdatesDao, subscriptionDao.getWebhookURL() );
					callingExternalUrl(webhookDao, subscriptionDao.getWebhookURL());
				}else{
					logger.info("The Webhook is not Configured");
				}
			}else{
				logger.info("SubScription not found for account id "+ dlrUpdatesDao.getAccountId());
			}
		}
	}

	private void callConvertedUnverifiedUrl(UnVerifiedEntity unVerifiedEntity, String convertUnverifiedUrl) {
		logger.info("callConvertedUnverifiedUrl:"+unVerifiedEntity+ "URL:" +convertUnverifiedUrl);
		String json = new Gson().toJson(unVerifiedEntity);
		MediaType mediaType = MediaType.parse("application/json");
		okhttp3.RequestBody body = okhttp3.RequestBody.create(mediaType, json);
		Request request = new Request.Builder()
				.url(convertUnverifiedUrl)
				.method("POST", body)
				.addHeader("Content-Type", "application/json")
				.build();
		Response response = null;
		try {
			OkHttpClient httpClient = new OkHttpClient().newBuilder().build();
			response = httpClient.newCall(request).execute();
			//			response = client.newCall(request).execute();
		} catch (IOException e) {
			//            throw new RuntimeException(e);
			logger.info("Error Ocurred in MessagePersistServiceImpl : callConvertedUnverifiedUrl ",e);
		}finally {
			if(response!=null){
				response.close();
			}
		}

	}

	private UnVerifiedEntity convertObjectUnferified(MessageDao messageDao) {

		logger.info("convert message into unverified:");
		UnVerifiedEntity unverified = new UnVerifiedEntity();
		UnverifiedMessages  unvemessage =  new UnverifiedMessages();
		MessageType type = messageDao.getType();
		String typeAsString = type.name();

		if(typeAsString.equalsIgnoreCase("text")) {
			unvemessage.setContent(messageDao.getText().getBody());
			unvemessage.setMessageId("0");
			unvemessage.setUnqId(messageDao.getId());
			unvemessage.setContentType("FILE");
			unvemessage.setSenderName(messageDao.getTo());
			unvemessage.setMessageType("TEXT");
			unvemessage.setPhone(messageDao.getTo());
			unvemessage.setCaption("");
			unvemessage.setTime(String.valueOf(messageDao.getDateFilterLong()));
			unvemessage.setGroupName("");
			unvemessage.setGroupMsg(false);
			unvemessage.setFileName("");
			unverified.setMessages(unvemessage);
			unverified.setAccountId(messageDao.getAccountId());
			unverified.setIncId(0);
			unverified.setAccessToken("");
			logger.info("convert text message unverified:");
		}else if(typeAsString.equalsIgnoreCase("image")) {
			unvemessage.setContent(messageDao.getImage().getLink());
			unvemessage.setMessageId("0");
			unvemessage.setUnqId(messageDao.getId());
			unvemessage.setContentType("CONTACT");
			unvemessage.setSenderName(messageDao.getTo());
			unvemessage.setMessageType("FILE");
			unvemessage.setPhone(messageDao.getTo());
			unvemessage.setCaption("");
			unvemessage.setTime(String.valueOf(messageDao.getDateFilterLong()));
			unvemessage.setGroupName("");
			unvemessage.setGroupMsg(false);
			unvemessage.setFileName(messageDao.getImage().getMime_type());
			unverified.setMessages(unvemessage);
			unverified.setAccountId(messageDao.getAccountId());
			unverified.setIncId(0);
			unverified.setAccessToken("");
			logger.info("convert Image message unverified:");
		}
		else if(typeAsString.equalsIgnoreCase("document")) {
			unvemessage.setContent(messageDao.getDocument().getLink());
			unvemessage.setMessageId("0");
			unvemessage.setUnqId(messageDao.getId());
			unvemessage.setContentType("CONTACT");
			unvemessage.setSenderName(messageDao.getTo());
			unvemessage.setMessageType("FILE");
			unvemessage.setPhone(messageDao.getTo());
			unvemessage.setCaption("");
			unvemessage.setTime(String.valueOf(messageDao.getDateFilterLong()));
			unvemessage.setGroupName("");
			unvemessage.setGroupMsg(false);
			unvemessage.setFileName(messageDao.getDocument().getMime_type());
			unverified.setMessages(unvemessage);
			unverified.setAccountId(messageDao.getAccountId());
			unverified.setIncId(0);
			unverified.setAccessToken("");
			logger.info("convert document message unverified:");
		}
		else if(typeAsString.equalsIgnoreCase("video")) {
			unvemessage.setContent(messageDao.getVideo().getLink());
			unvemessage.setMessageId("0");
			unvemessage.setUnqId(messageDao.getId());
			unvemessage.setContentType("CONTACT");
			unvemessage.setSenderName(messageDao.getTo());
			unvemessage.setContentType("FILE");
			unvemessage.setPhone(messageDao.getTo());
			unvemessage.setCaption("");
			unvemessage.setTime(String.valueOf(messageDao.getDateFilterLong()));
			unvemessage.setGroupName("");
			unvemessage.setGroupMsg(false);
			unvemessage.setFileName("");
			unverified.setMessages(unvemessage);
			unverified.setAccountId(messageDao.getAccountId());
			unverified.setIncId(0);
			unverified.setAccessToken("");
			logger.info("convert video message unverified:");
		}
		return unverified;

	}

	@Override
	public void callingExternalUrl(WebhookDao webhookDao, String webhookUrl) {
		String json = new Gson().toJson(webhookDao);
		MediaType mediaType = MediaType.parse("application/json");
		okhttp3.RequestBody body = okhttp3.RequestBody.create(mediaType, json);
		Request request = new Request.Builder()
				.url(webhookUrl)
				.method("POST", body)
				.addHeader("Content-Type", "application/json")
				.build();
		Response response = null;
		try {
			//			OkHttpClient httpClient = new OkHttpClient().newBuilder().build();
			//			response = httpClient.newCall(request).execute();
			response = client.newCall(request).execute();
		} catch (Exception e) {
			//            throw new RuntimeException(e);
			logger.info("Error Ocurred in MessagePersistServiceImpl : callingExternalUrl ",e);
		}finally {
			if(response!=null){
				response.close();
			}
		}
	}

	@Override
	public PaginationDao<MessageDao> getIncomingMessageReport(FilterDao filterDao) {
		//	public List<MessageDao> getIncomingMessageReport(FilterDao filterDao) {
		//		Query query = new Query();
		//		List<Criteria> criteriaList = new ArrayList<>();
		//		criteriaList.add(Criteria.where("dateFilterLong").gte(Long.parseLong(filterDao.getStartdate())).lte(Long.parseLong(filterDao.getEnddate())));
		//		criteriaList.add(Criteria.where("chatSide").is("Client"));
		//		query.addCriteria(new Criteria().andOperator(criteriaList.toArray(new Criteria[criteriaList.size()]))).with(Sort.by(Sort.Order.desc("dateFilterLong")));
		//		return mongoTemplate.find(query, MessageDao.class, MessagePersistServiceImpl.collectionName + filterDao.getAccountId());
		PaginationDao paginationDao = new PaginationDao();
		Query query = new Query().addCriteria(Criteria.where("dateFilterLong").gte(Long.parseLong(filterDao.getStartdate())).lte(Long.parseLong(filterDao.getEnddate())))
				.addCriteria(Criteria.where("chatSide").is("Client"));
		long count = mongoTemplate.count(query, MessageDao.class, MessagePersistServiceImpl.collectionName + filterDao.getAccountId());
		if(count ==0){
			paginationDao.setTotal(count);
			return paginationDao;
		}
		Query query2 = new Query().addCriteria(Criteria.where("dateFilterLong").gte(Long.parseLong(filterDao.getStartdate())).lte(Long.parseLong(filterDao.getEnddate())))
				.addCriteria(Criteria.where("chatSide").is("Client"))
				.with(PageRequest.of(filterDao.getOffset(), filterDao.getLimit(), Sort.by(Sort.Order.desc("dateFilterLong"))));

		paginationDao.setData(mongoTemplate.find(query2, MessageDao.class, MessagePersistServiceImpl.collectionName + filterDao.getAccountId()));
		paginationDao.setTotal(count);
		return paginationDao;

	}

	private static void hitApiServer(WebhookDao webhookDao)
	{

		WsWebhookDao wsWebhookDao=new WsWebhookDao(webhookDao.getMsg().getTo(),"Whatsapp","Whatsapp","","","");
		//logger.info("Getting Data inside Webhook Data {}", wsWebhookDao);
		Runnable runnable = new Runnable() {

			@Override
			public void run() {
				String json = new Gson().toJson(wsWebhookDao);
				MediaType mediaType = MediaType.parse("application/json");
				RequestBody body = RequestBody.create(json, mediaType);
				Request request = new Request.Builder()
						.url(webhookDao.getWebhookUrl())
						.post(body)
						.addHeader("cookie", "PHPSESSID=ek8500afed9rb6p1egghb2mq9h; JSESSIONID=D311DD74B013F2D7951E2D7D757A62E2")
						.addHeader("content-type", "application/json")
						.build();
				Response response = null;
				try {
					response = client.newCall(request).execute();

					//logger.info("After Hitting Api Response  is {} and text is {}", response, response.body().string());
				} catch (IOException e) {
					logger.error("Failed To Hit API ", e.getMessage());
				} finally {
					if (response != null) {
						response.close();
					}
				}
			}
		};
		new Thread(runnable).start();
	}

	private static void hitApi(WebhookDao webhookDao) {
		//test code
		webhookDao.getMsg().setSubscriptionDao(null);
		logger.info("to send msg for outgoing subscription set to be null:{}",webhookDao.getMsg().getSubscriptionDao());
		//
		//logger.info("Getting Data inside Webhook Data {}", webhookDao);
		Runnable runnable = new Runnable() {

			@Override
			public void run() {
				String json = new Gson().toJson(webhookDao);
				MediaType mediaType = MediaType.parse("application/json");
				RequestBody body = RequestBody.create(json, mediaType);
				Request request = new Request.Builder()
						.url(webhookDao.getWebhookUrl())
						.post(body)
						.addHeader("cookie", "PHPSESSID=ek8500afed9rb6p1egghb2mq9h; JSESSIONID=D311DD74B013F2D7951E2D7D757A62E2")
						.addHeader("content-type", "application/json")
						.build();
				Response response = null;
				try {
					response = client.newCall(request).execute();

					// logger.info("After Hitting Api Response  is {} and text is {}", response, response.body().string());
				} catch (IOException e) {
					logger.error("Failed To Hit API ", e.getMessage());
				} finally {
					if (response != null) {
						response.close();
					}
				}
			}
		};
		new Thread(runnable).start();


	}


	public boolean getAgentSupportActive(long accountId) {
		boolean enableAgentSupport = false;
		logger.error("Agent Support is Active ");
		try (MongoClient mongoClient = MongoClients.create(Constant.connectionString)) {
			MongoDatabase database = mongoClient.getDatabase(Constant.accountmasterdb);
			MongoCollection<Document> collection = database.getCollection(collectionBaseName);

			logger.error("Agent Support is Document: " +accountId);
			Document queryFilter = new Document("_id", new Long(accountId));
			try (MongoCursor<Document> cursor = collection.find(queryFilter).iterator()) {
				if (cursor.hasNext()) {
					Document doc = cursor.next();
					enableAgentSupport = doc.getBoolean("enableAgentSupport", false);
					logger.error("Agent Support is enableAgentSupport: " +enableAgentSupport);
				}
			} catch (Exception e) {
				logger.error("Failed to get Agent Support status", e);
			}

			return enableAgentSupport;
		}
	}

}
