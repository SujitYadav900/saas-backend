package com.whatsappbuisness.wsbuissness.CombinePackadge.DrlReportOnEmail;


import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import com.whatsappbuisness.wsbuissness.CombinePackadge.Datetiming.DateTiming;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.whatsappbuisness.wsbuissness.CombinePackadge.Messages.MessageDao;
import com.whatsappbuisness.wsbuissness.CombinePackadge.Messages.TemplateMessageDao.ComponentsDao;
import com.whatsappbuisness.wsbuissness.CombinePackadge.Messages.TemplateMessageDao.ParametersDao;
import com.whatsappbuisness.wsbuissness.CombinePackadge.Subscription.SubscriptionDao;
import com.whatsappbuisness.wsbuissness.CombinePackadge.Subscription.SubscriptionRepo;
import com.whatsappbuisness.wsbuissness.CombinePackadge.Subscription.SubscriptionService;



@Service
public class SendDrlReportOnEmailServiceImpl implements SendDrlReportOnEmailService {

	@Autowired
	SubscriptionService subscriptionService;

	@Autowired
	SubscriptionRepo subsriptionRepo;

	@Autowired
	MongoTemplate mongoTemplate;



	private static final Logger logger = LoggerFactory.getLogger(SendDrlReportOnEmailServiceImpl.class);

	private final String COLLECTION_NAME = "MessageDocument_";

	@Override
	public ResponseEntity<?> sendDrlReportOnEmail() throws IOException {
		EmailDao emailDao = new EmailDao();
		String[] headers = {"Id", "DST", "Report Type","Message Type","Template Name", "Message Status", "SENT TIME", "DLR TIME", "VIEW TIME", "Text Message", "Media Message"};
		long dateFilter= Long.parseLong(DateTiming.decreaseDay(-1));
		List<SubscriptionDao> sendDrlReportOnEmaillist = subsriptionRepo.findByDlrReportOnEmailTrue();
		if(sendDrlReportOnEmaillist != null) {
			for(SubscriptionDao subscriptionDao: sendDrlReportOnEmaillist) {
				Criteria criteria = Criteria.where("dateFilter").is(dateFilter).and("type").is("template");
				Query query = new Query(criteria);
				List<MessageDao> messageDaos = mongoTemplate.find(query, MessageDao.class, COLLECTION_NAME +subscriptionDao.getAccountId());
				for(MessageDao messageDao: messageDaos) {
					if(messageDao.getTemplate().getComponents() !=null && !messageDao.getTemplate().getComponents().isEmpty()) {
						List<ComponentsDao> componentsDaos = messageDao.getTemplate().getComponents();
						for(ComponentsDao components :componentsDaos) {
							if(components.getType().equalsIgnoreCase("body")) {
								int i =1;
								Map<String,String> map = new HashMap<>();
								List<ParametersDao> parameters = components.getParameters();
								for(ParametersDao parametersDao: parameters) {
									if(parametersDao.getType().equalsIgnoreCase("text")){
										map.put("{{" + i + "}}",parametersDao.getText());
										i++;
									}
								}
								String templateBodyText = messageDao.getTemplate().getTemplateBodyText();
								for(Map.Entry<String, String> entry: map.entrySet()) {
									templateBodyText = templateBodyText.replace(entry.getKey(), entry.getValue());
								}
								messageDao.getTemplate().setTemplateBodyText(templateBodyText);
							}
						}
					}
				}
				
				try (Workbook workbook = new XSSFWorkbook()) {
					  Sheet sheet = workbook.createSheet("Report");
					  Row headerRow = sheet.createRow(0);
					  for (int i = 0; i < headers.length; i++) {
					    Cell cell = headerRow.createCell(i);
					    cell.setCellValue(headers[i]);
					  }
					  int rowNum = 1;
					  for (MessageDao messageDao : messageDaos) {
					    Row row = sheet.createRow(rowNum++);
							List<String> tempList = new ArrayList();
							tempList.add(messageDao.getMessageId());
							tempList.add(messageDao.getTo());
							tempList.add(messageDao.isPanel() ? "System" :"Api");
							tempList.add(messageDao.getType().toString());
							tempList.add(messageDao.getTemplate().getName());
							tempList.add(messageDao.getMessageStatus().toString());
							tempList.add(messageDao.getDate());
							tempList.add(messageDao.getDlrTime()!=null ? messageDao.getDlrTime() : "");
							tempList.add(messageDao.getViewTime()!= null ? messageDao.getViewTime() :"");
							tempList.add(messageDao.getTemplate().getTemplateBodyText());
							List<ComponentsDao> componentsDaos = messageDao.getTemplate().getComponents();
							for(ComponentsDao components :componentsDaos) {
							if(components.getType().equalsIgnoreCase("header")) {
								List<ParametersDao> parameters = components.getParameters();
								for(ParametersDao parametersDao: parameters) {
									if(parametersDao.getType().equalsIgnoreCase("image")) {
										tempList.add(parametersDao.getImage().getLink()!=null ? parametersDao.getImage().getLink() : "");
									}else if(parametersDao.getType().equalsIgnoreCase("document")) {
										tempList.add(parametersDao.getDocument().getLink()!=null ? parametersDao.getDocument().getLink() : "");
									}else if(parametersDao.getType().equalsIgnoreCase("video")) {
										tempList.add(parametersDao.getVideo().getLink()!=null ? parametersDao.getVideo().getLink() : "");
									}
								}
							}
						}
					      String[] rowData = tempList.toArray(new String[0]);
					      try {
					        for (int i = 0; i < rowData.length; i++) {
					          Cell cell = row.createCell(i);
					          if (rowData[i] != null) {
					            cell.setCellValue(rowData[i].toString());
					          } else {
					            cell.setCellValue("");
					          }
					        }
					      } catch (Exception e) {
					        logger.error("Error setting value in row " + rowNum, e);
					      }
					  }
					String excelFilePath = "messages.xlsx";
					try (FileOutputStream fileOut = new FileOutputStream(excelFilePath)) {
						workbook.write(fileOut);
					} catch (IOException e) {
						logger.info("Error writing Excel file: " + e.getMessage());
					}
					sendEmail(emailDao,subscriptionDao,excelFilePath);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return null;
	}

	private void sendEmail(EmailDao email,SubscriptionDao subscriptionDao,String filePath) {
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");
		Session session = Session.getInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(email.getFromEmail(),email.getPassword());
			}
		});
		MimeMessage mimeMessage = new MimeMessage(session);
		try {
			mimeMessage.setFrom(new InternetAddress(email.getFromEmail()));
			String[] splitEmails = subscriptionDao.getClientEmail().split(",");
			mimeMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(splitEmails[0]));
			for(int i=1;i< splitEmails.length;i++){
				mimeMessage.addRecipient(Message.RecipientType.CC, new InternetAddress(splitEmails[i]));
			}
			mimeMessage.setSubject("Daily Report of Messages Delivery");
			MimeBodyPart msgBodyPart = new MimeBodyPart();
			msgBodyPart.setText("Delivery Report of Date :- "+ DateTiming.getDateRedable());
			MimeMultipart multipart = new MimeMultipart();
			MimeBodyPart messageBodyPart = new MimeBodyPart();
			DataSource source = new FileDataSource(filePath); 
			messageBodyPart.setDataHandler(new DataHandler(source));
			messageBodyPart.setFileName("DlrReport.xlsx");
			multipart.addBodyPart(messageBodyPart);
			multipart.addBodyPart(msgBodyPart);
			mimeMessage.setContent(multipart);
			Transport.send(mimeMessage);
			logger.info("Mail sent its Magic");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
