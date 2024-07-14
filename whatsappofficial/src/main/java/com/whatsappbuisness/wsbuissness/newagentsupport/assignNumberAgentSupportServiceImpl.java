package com.whatsappbuisness.wsbuissness.newagentsupport;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.bson.BsonInt64;
import org.bson.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.whatsappbuisness.wsbuissness.CombinePackadge.Messages.MessageDao;
import com.whatsappbuisness.wsbuissness.CombinePackadge.RabbitMqQue.MongoQue.Conversation.ConversationDao;


@Service
public class assignNumberAgentSupportServiceImpl implements assignNumberAgentSupportService {

	@Autowired
	AgentCustNumberMappingRepo mappingRepo;

	@Autowired
	MongoTemplate mongoTemplate;

	@Autowired
	AgentIndexRepo agentIndexRepo;

	private static final Logger logger = LoggerFactory.getLogger(assignNumberAgentSupportServiceImpl.class);

	public static final String collectionBaseName = "AccountMasterDocument";
	public static final String wsbuissnesCollectionBaseName = "ConversationDocument_";
	private static final String messageCollectionName = "MessageDocument_";

	@Override
	public void sendAgentInfo(AgentSupportRequest request) {
		logger.info("save agent info into AgentCustNumberMapping ");
		AgentCustNumberMapping numberMapping = new AgentCustNumberMapping();

		AgentCustNumberMapping mapping = mappingRepo.findBycustMobileNumber(request.getMobileNumber());
		if(mapping !=null) {
			if(!request.getMobileNumber().equalsIgnoreCase(mapping.getCustMobileNumber()) && mapping.getAdminAccountId()!= request.getAdminAccountId()) {
				logger.info("Mobile Number is not Present in table:" +request.getMobileNumber());
				numberMapping.setAgentAccountId(request.getAgentAccountId());
				numberMapping.setCustMobileNumber(request.getMobileNumber());
				numberMapping.setAssignAgent(request.isAssignAgent());
				numberMapping.setAdminAccountId(request.getAdminAccountId());
				mappingRepo.save(numberMapping);

				if(request.getMobileNumber()!=null ) {
					logger.info("mobil number is not getting in mobile");
					long parentId = getAdminAccount(request.getAgentAccountId());
					Optional<ConversationDao> custDocumentOpt = findByIdInCustomCollection(request.getMobileNumber(),wsbuissnesCollectionBaseName +parentId);
					if (custDocumentOpt.isPresent()) {
						ConversationDao conversiondao = custDocumentOpt.get();
						mongoTemplate.insert(conversiondao, wsbuissnesCollectionBaseName +request.getAgentAccountId());

					}else {
						throw new RuntimeException("Document with ID " + custDocumentOpt.get().getId() + " not found in source collection");
					}
					Optional<MessageDao> agenstNumberMessage = findByMyMessageOfNumber(request.getMobileNumber(),messageCollectionName +parentId);
					if (agenstNumberMessage.isPresent()) {
						MessageDao messageDao = agenstNumberMessage.get();
						mongoTemplate.insert(messageDao, messageCollectionName +request.getAgentAccountId());

					}else {
						throw new RuntimeException("Document with ID " + agenstNumberMessage.get().getTo() + " not found in source collection");
					}

				}
			}else if(!mapping.isAssignAgent()){
				logger.info("Assign Agent is false available:" +request.getMobileNumber());
				numberMapping.setAgentAccountId(request.getAgentAccountId());
				numberMapping.setCustMobileNumber(request.getMobileNumber());
				numberMapping.setAssignAgent(request.isAssignAgent());
				numberMapping.setAdminAccountId(request.getAdminAccountId());
				mappingRepo.save(numberMapping);
				if(request.getMobileNumber()!=null ) {
					logger.info("mobil number is not getting in mobile");
					long parentId = getAdminAccount(request.getAgentAccountId());
					Optional<ConversationDao> custDocumentOpt = findByIdInCustomCollection(request.getMobileNumber(),wsbuissnesCollectionBaseName +parentId);
					if (custDocumentOpt.isPresent()) {
						ConversationDao conversiondao = custDocumentOpt.get();
						mongoTemplate.insert(conversiondao, wsbuissnesCollectionBaseName +request.getAgentAccountId());

					}else {
						throw new RuntimeException("Document with ID " + custDocumentOpt.get().getId() + " not found in source collection");
					}
					Optional<MessageDao> agenstNumberMessage = findByMyMessageOfNumber(request.getMobileNumber(),messageCollectionName +parentId);
					if (agenstNumberMessage.isPresent()) {
						MessageDao messageDao = agenstNumberMessage.get();
						mongoTemplate.insert(messageDao, messageCollectionName +request.getAgentAccountId());

					}else {
						throw new RuntimeException("Document with ID " + agenstNumberMessage.get().getTo() + " not found in source collection");
					}

				}

			}else {
				logger.info("Assign Agent is false available:" +request.getMobileNumber());
				numberMapping.setAgentAccountId(request.getAgentAccountId());
				numberMapping.setCustMobileNumber(request.getMobileNumber());
				numberMapping.setAssignAgent(request.isAssignAgent());
				numberMapping.setAdminAccountId(request.getAdminAccountId());
				mappingRepo.save(numberMapping);

				if(request.getMobileNumber()!=null ) {
					logger.info("mobil number is not getting in mobile");
					long parentId = getAdminAccount(request.getAgentAccountId());
					Optional<ConversationDao> custDocumentOpt = findByIdInCustomCollection(request.getMobileNumber(),wsbuissnesCollectionBaseName +parentId);
					if (custDocumentOpt.isPresent()) {
						ConversationDao conversiondao = custDocumentOpt.get();
						mongoTemplate.insert(conversiondao, wsbuissnesCollectionBaseName +request.getAgentAccountId());

					}else {
						throw new RuntimeException("Document with ID " + custDocumentOpt.get().getId() + " not found in source collection");
					}
					Optional<MessageDao> agenstNumberMessage = findByMyMessageOfNumber(request.getMobileNumber(),messageCollectionName +parentId);
					if (agenstNumberMessage.isPresent()) {
						MessageDao messageDao = agenstNumberMessage.get();
						mongoTemplate.insert(messageDao, messageCollectionName +request.getAgentAccountId());

					}else {
						throw new RuntimeException("Document with ID " + agenstNumberMessage.get().getTo() + " not found in source collection");
					}

				}
			}
		}else {

			logger.info("Assign Agent is false available:" +request.getMobileNumber());
			numberMapping.setAgentAccountId(request.getAgentAccountId());
			numberMapping.setCustMobileNumber(request.getMobileNumber());
			numberMapping.setAssignAgent(request.isAssignAgent());
			numberMapping.setAdminAccountId(request.getAdminAccountId());
			mappingRepo.save(numberMapping);

			if(request.getMobileNumber()!=null ) {
				logger.info("mobil number is not getting in mobile");
				long parentId = getAdminAccount(request.getAgentAccountId());
				Optional<ConversationDao> custDocumentOpt = findByIdInCustomCollection(request.getMobileNumber(),wsbuissnesCollectionBaseName +parentId);
				if (custDocumentOpt.isPresent()) {
					ConversationDao conversiondao = custDocumentOpt.get();
					mongoTemplate.insert(conversiondao, wsbuissnesCollectionBaseName +request.getAgentAccountId());

				}else {
					throw new RuntimeException("Document with ID " + custDocumentOpt.get().getId() + " not found in source collection");
				}
				Optional<MessageDao> agenstNumberMessage = findByMyMessageOfNumber(request.getMobileNumber(),messageCollectionName +parentId);
				if (agenstNumberMessage.isPresent()) {
					MessageDao messageDao = agenstNumberMessage.get();
					mongoTemplate.insert(messageDao, messageCollectionName +request.getAgentAccountId());

				}else {
					throw new RuntimeException("Document with ID " + agenstNumberMessage.get().getTo() + " not found in source collection");
				}

			}
		}


	}

	private Optional<MessageDao> findByMyMessageOfNumber(String mobileNumber, String collectionName) {		
		Query query = new Query(Criteria.where("to").is(mobileNumber));
		MessageDao incommingMessage= mongoTemplate.findOne(query, MessageDao.class, collectionName);
		if (incommingMessage != null) {
			logger.info("findByIdInCustom Collection :" + incommingMessage.toString());
		} else {
			logger.info("Document not found with _id: " + mobileNumber);
		}
		return Optional.ofNullable(incommingMessage);
	}

	private Optional<ConversationDao> findByIdInCustomCollection(String mobileNumber, String collectionName) {
		Query query = new Query(Criteria.where("_id").is(mobileNumber));
		ConversationDao conversation = mongoTemplate.findOne(query, ConversationDao.class, collectionName);

		if (conversation != null) {
			logger.info("findByIdInCustom Collection :" + conversation.toString());
		} else {
			logger.info("Document not found with _id: " + mobileNumber);
		}
		return Optional.ofNullable(conversation);
	}

	public long getAdminAccount(long agentAccountId) {
		logger.info("Get Admin Account from account master");
		try (MongoClient mongoClient = MongoClients.create(Constant.connectionString)) {
			MongoDatabase database = mongoClient.getDatabase(Constant.accountmasterdb);
			MongoCollection<Document> collection = database.getCollection(collectionBaseName);
			logger.info("Get Admin Account from account master");			
			Document queryFilter = new Document("_id", new BsonInt64(agentAccountId));
			try (MongoCursor<Document> cursor = collection.find(queryFilter).iterator()) {
				while (cursor.hasNext()) {
					Document doc = cursor.next();
					Object parentId = doc.get("parentId");
					if (parentId instanceof BsonInt64) {
						BsonInt64 parentIdBson = (BsonInt64) parentId;
						logger.info("Admin Account from account master: " + parentIdBson.longValue());
						return parentIdBson.longValue();
					} else if (parentId instanceof Long) {
						Long parentIdLong = (Long) parentId;
						logger.info("Admin Account from account master: " + parentIdLong);
						return parentIdLong;
					} else {
						logger.error("Unexpected parentId type: " + parentId.getClass().getName());
					}
				}
			}

		}catch (Exception e) {
			e.printStackTrace();
		}

		return 0;
	}

	@Override
	public void insertMessageAgentAccount(MessageDao messageDao) {
		logger.info("insert Message Agent Account into message dao");
		List<Long> agentAccount = getAgentAccountId(messageDao);
		AgentCustNumberMapping mappings = mappingRepo.findBycustMobileNumber(String.valueOf(messageDao.getTo()));
		if(mappings!=null) {
			if (!mappings.isAssignAgent() && mappings.getAdminAccountId() == messageDao.getAccountId()) {
				messageDao = mongoTemplate.insert(messageDao, messageCollectionName + mappings.getAgentAccountId());
				agentAccount.remove(Long.valueOf(mappings.getAgentAccountId()));
			}else {
				messageDao = mongoTemplate.insert(messageDao, messageCollectionName + mappings.getAgentAccountId());
				agentAccount.remove(Long.valueOf(mappings.getAgentAccountId()));
			}
		}else {
			roundRobinApproach(messageDao,agentAccount,null);
		}

	}

	private List<Long> getAgentAccountId(MessageDao messageDao) {
		logger.info("getAgentAccountId from account master");
		List<Long> agentAccount = new ArrayList<>();
		try (MongoClient mongoClient = MongoClients.create(Constant.connectionString)) {
			MongoDatabase database = mongoClient.getDatabase(Constant.accountmasterdb);
			MongoCollection<Document> collection = database.getCollection(collectionBaseName);
			logger.info("Run Query Agent");
			Document queryFilter1 = new Document("agentStatus", true).append("parentId",new Long(messageDao.getAccountId()));
			try (MongoCursor<Document> cursor = collection.find(queryFilter1).iterator()) {
				while (cursor.hasNext()) {
					Document doc = cursor.next();
					Long id = doc.getLong("_id");
					logger.info("Agent id is :" +id);
					if (id != null) {
						agentAccount.add(id);
					}
				}
			} catch (Exception e) {
				logger.error("Failed to retrieve agent accounts", e);
			}
		}
		return agentAccount;
	}


	private void roundRobinApproach(MessageDao messageDao, List<Long> agentAccount, Update update) {
		logger.info("Message ready for distribution in Present Agents:");

		AgentIndex agentIndexrepo = agentIndexRepo.findByAdminAccountId(messageDao.getAccountId());
		if (agentIndexrepo == null) {
			agentIndexrepo = new AgentIndex();
			agentIndexrepo.setAdminAccountId(messageDao.getAccountId());
			agentIndexrepo.setCurrentIndex(0);
		}

		int agentIndex = agentIndexrepo.getCurrentIndex();
		int agentCount = agentAccount.size();

		if (agentCount > 0) {
			if (agentIndex >= agentCount) {
				agentIndex = 0; // Reset index if it exceeds the number of agents
			}

			Long currentAgent = agentAccount.get(agentIndex);
			try {
				saveDataToDb(currentAgent, messageDao, update);
				agentIndex = (agentIndex + 1) % agentCount;
				agentIndexrepo.setCurrentIndex(agentIndex);
				agentIndexRepo.save(agentIndexrepo);
				logger.info("Message distributed to agent: " + currentAgent);
			} catch (Exception e) {
				logger.error("Failed to distribute message to agent: " + currentAgent + ", error: " + e.getMessage(), e);
			}
		} else {
			logger.warn("No agents available to distribute the message.");
		}
	}



	private void saveDataToDb(Long currentAgent, MessageDao messageDao, Update update) {
		try {
			logger.info("Ready message for saving into table and it follow Round Robin Approcch:");
			messageDao = mongoTemplate.insert(messageDao, messageCollectionName + currentAgent);
			logger.info("Document Save into:" +messageCollectionName + currentAgent);
			AgentCustNumberMapping custNumberMapping = new AgentCustNumberMapping();

			AgentCustNumberMapping mapping = mappingRepo.findBycustMobileNumber(messageDao.getTo());
			if(mapping !=null) {
				if(!messageDao.getTo().equalsIgnoreCase(mapping.getCustMobileNumber()) && mapping.getAdminAccountId()!= messageDao.getAccountId()) {
					custNumberMapping.setAgentAccountId(currentAgent);
					custNumberMapping.setAssignAgent(false);
					custNumberMapping.setCustMobileNumber(messageDao.getTo());
					custNumberMapping.setAdminAccountId(messageDao.getAccountId());
					mappingRepo.save(custNumberMapping);
				}else {
					custNumberMapping.setAgentAccountId(currentAgent);
					custNumberMapping.setAssignAgent(false);
					custNumberMapping.setCustMobileNumber(messageDao.getTo());
					custNumberMapping.setAdminAccountId(messageDao.getAccountId());
					mappingRepo.save(custNumberMapping);

				}
				if(update!=null) {
					logger.info("Update Message Status in :" +wsbuissnesCollectionBaseName + currentAgent);
					mongoTemplate.upsert(new Query(Criteria.where("_id").is(messageDao.getTo())), update, ConversationDao.class, wsbuissnesCollectionBaseName + currentAgent);
				}
			}else {
				custNumberMapping.setAgentAccountId(currentAgent);
				custNumberMapping.setAssignAgent(false);
				custNumberMapping.setCustMobileNumber(messageDao.getTo());
				custNumberMapping.setAdminAccountId(messageDao.getAccountId());
				mappingRepo.save(custNumberMapping);
			}
		}catch(Exception e) {
			logger.error("Failed to insert to database: " + e.getMessage(), e);

		}
	}

	@Override
	public void updateMessageConversion(MessageDao messageDao, Update update) {
		logger.info("Get for updateMessageConversion");
		List<Long> agentAccount = getAgentAccountId(messageDao);
		AgentCustNumberMapping mappings = mappingRepo.findBycustMobileNumber(String.valueOf(messageDao.getTo()));
		if(mappings!=null) {
			logger.info("Get for mappings");
			if (!mappings.isAssignAgent() && mappings.getAdminAccountId() == messageDao.getAccountId()) {
				mongoTemplate.upsert(new Query(Criteria.where("_id").is(messageDao.getTo())), update, ConversationDao.class, wsbuissnesCollectionBaseName + mappings.getAgentAccountId());
				agentAccount.remove(Long.valueOf(mappings.getAgentAccountId()));
			}else{
				mongoTemplate.upsert(new Query(Criteria.where("_id").is(messageDao.getTo())), update, ConversationDao.class, wsbuissnesCollectionBaseName + mappings.getAgentAccountId());
				agentAccount.remove(Long.valueOf(mappings.getAgentAccountId()));
			}
		}else {
			roundRobinApproach(messageDao,agentAccount,update);
		}

	}

	@Override
	public agentSupportResponse getassignedNumberInfo(AgentSupportRequest supportRequest) {
		AgentCustNumberMapping mapping = mappingRepo.findByAdminAccountIdAndCustMobileNumber(supportRequest.getAdminAccountId(), supportRequest.getMobileNumber());
		agentSupportResponse response = new agentSupportResponse();
		if(mapping!=null) {
			response.setAgentAccountId(mapping.getAgentAccountId());
			response.setAssignAgent(mapping.isAssignAgent());
		}
		return response;
	}


}
