package com.example.maxcrm.MaxCrm.ServiceImpl;

import com.example.maxcrm.MaxCrm.Dao.FileDocument;
import com.example.maxcrm.MaxCrm.Dao.MailObjectDao;
import com.example.maxcrm.MaxCrm.Service.MailObjectService;
import com.example.maxcrm.MaxCrm.Utility.UtilityClass;
import okhttp3.*;
import org.apache.commons.text.StringEscapeUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.jms.JmsProperties;
import org.springframework.stereotype.Service;

import javax.activation.DataHandler;
import javax.activation.URLDataSource;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

@Service
public class MailObjectServiceImpl implements MailObjectService {


    public static final OkHttpClient client = new OkHttpClient.Builder()
            .readTimeout(120, TimeUnit.SECONDS)
            .writeTimeout(120, TimeUnit.SECONDS)
            .connectTimeout(120, TimeUnit.SECONDS)
            .connectionPool(new ConnectionPool(10, 120, TimeUnit.SECONDS))

            .build();
    private final Logger logger = LoggerFactory.getLogger(MailObjectServiceImpl.class);

    @Override
    public void bulkInsertMail(List<MailObjectDao> mailObjectDaos) throws Exception {
        String from= UtilityClass.propertyService.findProperty("SMTPConfigs","fromEmail");
        String fromName=UtilityClass.propertyService.findProperty("SMTPConfigs","fromName");
        String replyTo=UtilityClass.propertyService.findProperty("SMTPConfigs","replyTo");
        String username=UtilityClass.propertyService.findProperty("SMTPConfigs","usernameEmail");
        String password=UtilityClass.propertyService.findProperty("SMTPConfigs","passwordEmail");
        System.out.println("from "+from);
        System.out.println(fromName);
        System.out.println(replyTo);
        System.out.println(username);
        System.out.println(password);

        for (MailObjectDao mailObjectDao : mailObjectDaos) {
            mailObjectDao.getMessage().setFromName(fromName);
            mailObjectDao.getMessage().setReplyTo(replyTo);
            mailObjectDao.getMessage().setFromEmail(from);
            mailObjectDao.setUserName(username);
            mailObjectDao.setPassword(password);
            //mailObjectDao.getMessage().setMessage();
            mailObjectDao.setIncludeFooter("yes");
            mailObjectDao.setVersion("1.0");
//            makeARequest(mailObjectDao.convertToJson());
            sendGmail(mailObjectDao);
        }

    }


    private void makeARequest(String json) {

        logger.info("Making Mail Request {}", json);
        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType, json);
        Request request = new Request.Builder()
                .url("http://eapi.mgage.solutions/sendEmail")
                .post(body)
                .addHeader("content-type", "application/json")

                .build();

        try {
            Response response = client.newCall(request).execute();
            logger.info("Response From Mail Api {}", response.body().string());
        } catch (IOException e) {
            logger.error("Error Occured {}", e);
            e.printStackTrace();
        }

    }
//    ------------------------------------

    private void sendGmail(MailObjectDao mailObjectDao) {

    logger.info("Sending email with Google Mail Service, MailService");
//        logger.info("Sending email with Google Mail Service, Username :: {}",accountMasterDao.getEmailUsername());
//        logger.info("Sending email with Google Mail Service, Password :: {}",accountMasterDao.getEmailPassword());
        System.out.println("Mail send karte time Mail Dao ka Object "+ mailObjectDao);
        try {
            Properties props = new Properties();
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.host", "smtp.gmail.com");
            props.put("mail.smtp.port", "587");

            Session session = Session.getInstance(props, new javax.mail.Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(mailObjectDao.getUserName(), mailObjectDao.getPassword());
                }
            });
            Message msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress(mailObjectDao.getUserName(), false));
            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(mailObjectDao.getMessage().getReplyTo()));
            msg.setSubject(mailObjectDao.getMessage().getSubject());
            // msg.setContent(emailDao.getHtml());
            msg.setSentDate(new Date());
//            msg.setContent(mailObjectDao.getMessage().getMessage());
//          messageBodyPart.setContent(emailDao.getHtml(), "text/html");
//            String s = "<!DOCTYPE html><html lang=\"en\"><head><title>CSS Template</title><meta charset=\"utf-8\"><meta name=\"viewport\" content=\"width=device-width, initial-scale=1\"><style>*{box-sizing: border-box;}body {font-family: Arial, Helvetica, sans-serif;}/* Style the header */header {background-color: #666;padding: 30px;text-align: center;font-size: 35px;color: white;}/* Create two columns/boxes that floats next to each other */nav {float: left;width: 30%;height: 300px; /* only for demonstration, should be removed */background: #ccc;padding: 20px;}/* Style the list inside the menu */nav ul {list-style-type: none;padding: 0;}article {float: left;padding: 20px;width: 70%;background-color: #f1f1f1;height: 300px; /* only for demonstration, should be removed */}/* Clear floats after the columns */section::after {content: \"\";display: table;clear: both;}/* Style the footer */footer {background-color: #777;padding: 10px;text-align: center;color: white;}/* Responsive layout - makes the two columns/boxes stack on top of each other instead of next to each other, on small screens */@media (max-width: 600px) {nav, article {width: 100%;height: auto;}}</style></head><body><h2>CSS Layout Float</h2><p>In this example, we have created a header, two columns/boxes and a footer. On smaller screens, the columns will stack on top of each other.</p><p>Resize the browser window to see the responsive effect (you will learn more about this in our next chapter - HTML Responsive.)</p><header><h2>Cities</h2></header><section><nav><ul><li><a href=\"#\">London</a></li><li><a href=\"#\">Paris</a></li><li><a href=\"#\">Tokyo</a></li></ul></nav><article><h1>London</h1><p>"+mailObjectDao.getMessage().getMessage()+"</p></article></section><footer><p>Footer</p></footer></body></html>";
//            String s = mailObjectDao.getMessage().getMessage().substring(42).trim();
//            String[] splits = s.split("</pre>");
//            String s2 = splits[0].replaceAll("&lt;","<").replaceAll("&gt;",">");
//            System.out.println("s is "+ s2);
            String unEscapedHTML = null;
            if(mailObjectDao.getMessage().getMessage().contains("<html>")){
                unEscapedHTML = StringEscapeUtils.unescapeHtml4(mailObjectDao.getMessage().getMessage().substring(42).trim());
            }else{
                unEscapedHTML = StringEscapeUtils.unescapeHtml4(mailObjectDao.getMessage().getMessage().trim());
            }
            Multipart multipart = new MimeMultipart();
            MimeBodyPart messageBodyParthtml = new MimeBodyPart();
//            messageBodyParthtml.setContent("<h1 style = ' color: red'>"+mailObjectDao.getMessage().getMessage()+"</h1>", "text/html");
//            messageBodyParthtml.setContent(mailObjectDao.getMessage().getMessage(),"text/html");
//            messageBodyParthtml.setContent(splits[0],"text/html");
            messageBodyParthtml.setContent(unEscapedHTML,"text/html");
            multipart.addBodyPart(messageBodyParthtml);

//            if(emailDao.getAttachment()!=null){
//                logger.info("Attachment is Present");
//                String[] split = emailDao.getAttachment().split("id=");
//                String id = split[1];
//                logger.info(" ID is "+ id);
                //Random random = new Random(999999);
                //String StorePath=new ClassPathResource("static/").getFile().getAbsolutePath();
                //logger.info("Store Path is "+StorePath);
//                    System.out.println(StorePath);
//                    String Path = accountMasterDao.getAccountName()+"/temp/"+fileDocument.getName();
//                    logger.info("Path is "+Path);
//                    File file = new File(Path);
//                    Files.copy(new URL(emailDao.getAttachment()).openStream(), Paths.get(Path));
//                    fileupload.attachFile(file);
//                    multipart.addBodyPart(fileupload);
                //-------------------------------------------------
//                MimeBodyPart attachPart = new MimeBodyPart();
//                logger.info("this is attachment "+ emailDao.getAttachment());
//                FileDocument fileDocument = fileDocumentService.getFile(id);
//                URL url = new URL(emailDao.getAttachment());
//                URLDataSource uds = new URLDataSource(url);
//                attachPart.setDataHandler(new DataHandler(uds));
//                attachPart.setDisposition(Part.ATTACHMENT);
//                attachPart.setFileName(fileDocument.getName());
//                multipart.addBodyPart(attachPart);
//            }
            msg.setContent(multipart);
            Transport.send(msg);
            logger.info("Mail Sennnnnnt ");
        } catch (Exception e) {
            logger.error("Error Occurred While Sending email with Google Service :: {}",e);
            e.printStackTrace();
        }

    }


}
