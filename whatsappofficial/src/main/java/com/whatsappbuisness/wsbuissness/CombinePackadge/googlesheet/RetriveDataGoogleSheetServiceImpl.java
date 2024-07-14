package com.whatsappbuisness.wsbuissness.CombinePackadge.googlesheet;

import java.io.InputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.SheetsScopes;
import com.google.api.services.sheets.v4.model.ValueRange;
import com.whatsappbuisness.wsbuissness.CombinePackadge.MessageEntry.MessageEntryController;
import com.whatsappbuisness.wsbuissness.CombinePackadge.MessageEntry.MessageEntryService;
import com.whatsappbuisness.wsbuissness.CombinePackadge.Messages.MediaCommonObj;
import com.whatsappbuisness.wsbuissness.CombinePackadge.Messages.MessageDao;
import com.whatsappbuisness.wsbuissness.CombinePackadge.Messages.MessageType;
import com.whatsappbuisness.wsbuissness.CombinePackadge.Messages.TemplateMessageDao.ComponentsDao;
import com.whatsappbuisness.wsbuissness.CombinePackadge.Messages.TemplateMessageDao.ParametersDao;
import com.whatsappbuisness.wsbuissness.CombinePackadge.Messages.TemplateMessageDao.TemplateText.TemplateMessageDao;
import com.whatsappbuisness.wsbuissness.CombinePackadge.Subscription.SubscriptionDao;
import com.whatsappbuisness.wsbuissness.CombinePackadge.Subscription.SubscriptionRepo;
import com.whatsappbuisness.wsbuissness.CombinePackadge.whatsappCloudAPI.Components;
import com.whatsappbuisness.wsbuissness.CombinePackadge.whatsappCloudAPI.Document;
import com.whatsappbuisness.wsbuissness.CombinePackadge.whatsappCloudAPI.Image;
import com.whatsappbuisness.wsbuissness.CombinePackadge.whatsappCloudAPI.Parameters;
import com.whatsappbuisness.wsbuissness.CombinePackadge.whatsappCloudAPI.Template;
import com.whatsappbuisness.wsbuissness.CombinePackadge.whatsappCloudAPI.Video;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class RetriveDataGoogleSheetServiceImpl implements RetriveDataGoogleSheetService {

	@Autowired
	SubscriptionRepo subsriptionRepo;

	@Autowired
	MongoTemplate mongoTemplate;

	@Autowired
	MessageEntryService entryService; 

	private static final Logger logger = LoggerFactory.getLogger(RetriveDataGoogleSheetServiceImpl.class);

	private final static String APPLICATION_NAME = "Google Sheets API Java Quickstart";
	private final static JsonFactory JSON_FACTORY = GsonFactory.getDefaultInstance();
	private final static String TOKENS_DIRECTORY_PATH = "tokens";
	private final static List<String> SCOPES = Collections.singletonList(SheetsScopes.SPREADSHEETS_READONLY);
	final static String CREDENTIALS_FILE_PATH = "/credentials.json";
	//private static final String spreadsheetId = "12HOEkhdG5HMkkmSVFyflCpQKWg3QH2OBb58IUCTAZ1w";

	@Override
	public void getRetriveDataFromGoogleSheet() throws IOException, GeneralSecurityException {
		logger.info("Retrive Data from Google");
		final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
		//String range = "A2:E";
		List<SubscriptionDao> enableGoogleSheet = subsriptionRepo.findByEnableGoogleSheetTrue();
		for(SubscriptionDao googlesheet: enableGoogleSheet) {
			
			if(googlesheet.getSpreadsheetId() !=null && googlesheet.getRange()!=null ) {
				logger.info("Getting data from subscriptionDao: Range - " + googlesheet.getRange() + ", SpreadsheetId - " + googlesheet.getSpreadsheetId());
				List<List<Object>> valuesA2E = getDataFromSheet(googlesheet.getRange(),googlesheet.getSpreadsheetId(),HTTP_TRANSPORT);
				Map<String, List<String>> mapC2E = new HashMap<>();
				for (List<Object> row : valuesA2E) {
					Object keyObject = row.get(0);
					if(keyObject != null) {
						String key = keyObject.toString();
						List<String> valueList = new ArrayList<>();
						for (int i = 1; i < row.size(); i++) {
							Object valueObject = row.get(i);
							if(valueObject != null) {
								valueList.add(valueObject.toString());
							}
						}
						mapC2E.put(key, valueList);
					} else {
						logger.warn("Null key found in row: " + row.toString());
					}
				}

				for (Map.Entry<String, List<String>> entry : mapC2E.entrySet()) {
					String key = entry.getKey();
					List<String> values = entry.getValue();
					System.out.println("Key: " + key);
					System.out.println("Values: " + values);
				}
				for(Map.Entry<String, List<String>> entry:mapC2E.entrySet()) {

					List<MessageDao>  messageList = new ArrayList<>();
					MessageDao messageDao = new MessageDao(); 
					messageDao.setTo(entry.getKey());
					messageDao.setType(MessageType.template);

					List<String> values = entry.getValue();
					TemplateMessageDao template = findTemplateById(values.get(0));

					List<ComponentsDao> components = new ArrayList<>();
					for (ComponentsDao componentsDao : template.getComponents()) {

						if(componentsDao.getType().equalsIgnoreCase("header")){
							logger.info("componentsDao calling for header");
							ComponentsDao component = new ComponentsDao();
							component.setType("header");
							List<ParametersDao> parameters = new ArrayList<>();
							for(int j = 0;j<componentsDao.getParameters().size();j++){
								ParametersDao parameter = new ParametersDao();
								if(componentsDao.getParameters().get(j).getType().equalsIgnoreCase("image")){

									parameter.setType("image");
									MediaCommonObj image = new MediaCommonObj();
									image.setLink(values.get(1));
									parameter.setImage(image);
									parameters.add(parameter);

								}else if(componentsDao.getParameters().get(j).getType().equalsIgnoreCase("document")){

									parameter.setType("document");
									MediaCommonObj document = new MediaCommonObj();
									document.setLink(values.get(1));
									parameter.setDocument(document);
									parameters.add(parameter);
								}
								else if(componentsDao.getParameters().get(j).getType().equalsIgnoreCase("video")){

									parameter.setType("video");
									MediaCommonObj video = new MediaCommonObj();
									video.setLink(values.get(1));
									parameter.setVideo(video);
									parameters.add(parameter);
								}
							}
							component.setParameters(parameters);
							components.add(component);

						}
						if(componentsDao.getType().equalsIgnoreCase("body")){
							logger.info("componentsDao calling for body");
							ComponentsDao component = new ComponentsDao();
							component.setType("body");
							List<ParametersDao> parameters = new ArrayList<>();
							for(int j = 0;j<componentsDao.getParameters().size();j++){
								int i = 2;
								if(componentsDao.getParameters().get(j).getType().equalsIgnoreCase("text")){
									ParametersDao parameter = new ParametersDao();
									parameter.setType("text");
									parameter.setText(values.get(i));
									parameters.add(parameter);
									i++;
								}
							}
							component.setParameters(parameters);
							components.add(component);
						}

					}

					template.setComponents(components);
					messageDao.setTemplate(template);
					messageList.add(messageDao);
					entryService.messageBulkInsertService(messageList,googlesheet.getAccountId(),true);

				}
			}
		}

	}


	private TemplateMessageDao findTemplateById(String id) {
		logger.info("TemplateMessageDao calling {}");
		Query query = new Query();
		query.addCriteria(Criteria.where("_id").is(id));
		return mongoTemplate.findOne(query, TemplateMessageDao.class);
	}

	private static Credential getCredentials(final NetHttpTransport HTTP_TRANSPORT)
			throws IOException {
		InputStream in = RetriveDataGoogleSheetServiceImpl.class.getResourceAsStream(CREDENTIALS_FILE_PATH);
		if (in == null) {
			throw new FileNotFoundException("Resource not found: " + CREDENTIALS_FILE_PATH);
		}
		GoogleClientSecrets clientSecrets =
				GoogleClientSecrets.load(JSON_FACTORY, new InputStreamReader(in));
		GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
				HTTP_TRANSPORT, JSON_FACTORY, clientSecrets, SCOPES)
				.setDataStoreFactory(new FileDataStoreFactory(new java.io.File(TOKENS_DIRECTORY_PATH)))
				.setAccessType("offline")
				.build();
		LocalServerReceiver receiver = new LocalServerReceiver.Builder().setPort(8084).build();
		return new AuthorizationCodeInstalledApp(flow, receiver).authorize("user");
	}

	private static List<List<Object>> getDataFromSheet(String range,String spreadsheetId,NetHttpTransport hTTP_TRANSPORT) throws IOException {
		Sheets service =
				new Sheets.Builder(hTTP_TRANSPORT, JSON_FACTORY, getCredentials(hTTP_TRANSPORT))
				.setApplicationName(APPLICATION_NAME)
				.build();
		ValueRange response = service.spreadsheets().values()
				.get(spreadsheetId, range)
				.execute();
		List<List<Object>> values = response.getValues();
		return values;
	}
}
