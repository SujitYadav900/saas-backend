package com.example.maxcrm.MaxCrm.ServiceImpl;

import com.example.maxcrm.MaxCrm.Dao.MailSentDocument;
import com.example.maxcrm.MaxCrm.Service.SendSmptMailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.ByteArrayInputStream;

public class SendSmptMailServiceImpl implements SendSmptMailService {
    @Autowired
    private JavaMailSender javaMailSender;

    Logger logger = LoggerFactory.getLogger(SendSmptMailService.class);
    @Override
    @Async("MailSendingThread")
    public void sendMail(MailSentDocument mailSentDocument) throws MessagingException {

        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");
//        helper.setText(mailSentDocument.getMessage(), true);
        helper.setTo(mailSentDocument.getTo());
        helper.setSubject(mailSentDocument.getSubject());
        helper.setFrom(mailSentDocument.getFrom());
        for (int i = 0; i < mailSentDocument.getAttachmentDaos().size(); i++) {
            helper.addAttachment(mailSentDocument.getAttachmentDaos().get(i).getFileName(), new InputStreamResource(new ByteArrayInputStream((mailSentDocument.getAttachmentDaos().get(i).getBinary().getData()))));
        }
        javaMailSender.send(mimeMessage);
        logger.info("Mail send Successfull {}",mailSentDocument);
    }
}
