package com.whatsappbuisness.wsbuissness.CombinePackadge.CombinedQueue3MessageSend;

import com.whatsappbuisness.wsbuissness.CombinePackadge.Common.SessionRetrival.SessionRetrievalService;
import com.whatsappbuisness.wsbuissness.CombinePackadge.Common.SessionRetrival.UsermasterDao;
import com.whatsappbuisness.wsbuissness.CombinePackadge.MessageEntry.MessageEntryResponseDao;
import com.whatsappbuisness.wsbuissness.CombinePackadge.MessageEntry.ValidateFailedExeption;
import com.whatsappbuisness.wsbuissness.CombinePackadge.Messages.AudioMessage.AudioMessageDao;
import com.whatsappbuisness.wsbuissness.CombinePackadge.Messages.ContactsMessageDao.ContactsMessageDao;
import com.whatsappbuisness.wsbuissness.CombinePackadge.Messages.DocumentMessage.DocumentMessageDao;
import com.whatsappbuisness.wsbuissness.CombinePackadge.Messages.ImageMessage.ImageMessageDao;
import com.whatsappbuisness.wsbuissness.CombinePackadge.Messages.InteractiveMessageDao.InteractiveMessageDao;
import com.whatsappbuisness.wsbuissness.CombinePackadge.Messages.Location.LocationMessageDao;
import com.whatsappbuisness.wsbuissness.CombinePackadge.Messages.MessageDao;
import com.whatsappbuisness.wsbuissness.CombinePackadge.Messages.MessageStatus;
import com.whatsappbuisness.wsbuissness.CombinePackadge.Messages.MessageType;
import com.whatsappbuisness.wsbuissness.CombinePackadge.Messages.TemplateMessageDao.ComponentsDao;
import com.whatsappbuisness.wsbuissness.CombinePackadge.Messages.TemplateMessageDao.ParametersDao;
import com.whatsappbuisness.wsbuissness.CombinePackadge.Messages.TemplateMessageDao.TemplateText.TemplateMessageDao;
import com.whatsappbuisness.wsbuissness.CombinePackadge.Messages.TextMessage.TextMessageDao;
import com.whatsappbuisness.wsbuissness.CombinePackadge.Messages.VideoMessage.VideoMessageDao;
import com.whatsappbuisness.wsbuissness.CombinePackadge.Template.TemplateMessageService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/api/combinedqueue3")
public class CombinedQueue3MesasgeSendController {

    private final Logger logger = LoggerFactory.getLogger(CombinedQueue3MesasgeSendController.class);

    @Autowired
    SessionRetrievalService sessionRetrievalService;
    @Autowired
    CombinedQueue3MessageSendService combinedQueueMessageSendService;
    @Autowired
    TemplateMessageService templateMessageService;

    @PostMapping("/batchpanel")
    public ResponseEntity<MessageEntryResponseDao> MessageEntryResponseDaoPanel(@RequestBody List<MessageDao> messageDao, Authentication authentication) throws Exception {
        logger.info("batchpanel message dao {}",messageDao);
        UsermasterDao usermasterDao = sessionRetrievalService.get(authentication);
        MessageEntryResponseDao messageEntryResponseDao = new MessageEntryResponseDao();
        try {
            messageDao = prepareMessage(messageDao);
        } catch (ValidateFailedExeption ew) {
            logger.info("Validation Error occurs");
            messageEntryResponseDao = new MessageEntryResponseDao();
            messageEntryResponseDao.setMessage(ew.getMessage());
            messageEntryResponseDao.setStatus(MessageStatus.FAILED);
            messageEntryResponseDao.setIds(new ArrayList<>());
            return new ResponseEntity<MessageEntryResponseDao>(messageEntryResponseDao, HttpStatus.BAD_REQUEST);
        }
        logger.info("will hit messageBulkInsertService method");
        messageEntryResponseDao = combinedQueueMessageSendService.messageBulkInsertService(messageDao, usermasterDao.getAccountId(),true);
        if (messageEntryResponseDao.getStatus() == MessageStatus.SENT) {
            return new ResponseEntity<MessageEntryResponseDao>(messageEntryResponseDao, HttpStatus.OK);
        } else {
            return new ResponseEntity<MessageEntryResponseDao>(messageEntryResponseDao, HttpStatus.BAD_REQUEST);
        }
    }



    List<MessageDao> prepareMessage(List<MessageDao> messageDaos) throws ValidateFailedExeption {
        MessageDao messageDao;
        for (int i = 0; i < messageDaos.size(); i++) {
            messageDao = messageDaos.get(i);
            switch (messageDao.getType()) {
                case image:

                    checkImage(messageDao.getImage());
                    break;
                case text:
                    checkText(messageDao.getText());
                    break;
                case template:
                    messageDao.setTemplate(checkTemplate(messageDao.getTemplate()));
                    break;
      /*          case contacts:
                    checkContact(messageDao.getContacts());
                    break;*/
                case document:
                    checkDocument(messageDao.getDocument());
                    break;
                case location:
                    checkLocation(messageDao.getLocation());
                    break;
                case video:
                    checkVideo(messageDao.getVideo());
                    break;
                case audio:
                    checkAudio(messageDao.getAudio());
                    break;
                case interactive:
                    checkInteractive(messageDao.getInteractive());
                    break;

                case voice:
                    break;


            }


            messageDaos.set(i, messageDao);
        }


        return messageDaos;
    }



    private void checkAudio(AudioMessageDao messageDao) throws ValidateFailedExeption {
        if (messageDao == null) {
            throw new ValidateFailedExeption("No Audio Object Can be Found!!");
        }
        if (StringUtils.isEmpty(messageDao.getId()) && StringUtils.isEmpty(messageDao.getLink())) {
            throw new ValidateFailedExeption("No Audio Id or Link Can be Found!!");
        }
        if (!StringUtils.isEmpty(messageDao.getId())) {
            messageDao.setLink(null);
        }

        if (!StringUtils.isEmpty(messageDao.getLink())) {
            messageDao.setId(null);
        }
    }

    private void checkInteractive(InteractiveMessageDao messageDao) throws ValidateFailedExeption {
        if (messageDao == null) {
            throw new ValidateFailedExeption("No Interactive Object Can be Found!!");
        }
        if (messageDao.getType() != MessageType.list && messageDao.getType() != MessageType.button) {
            throw new ValidateFailedExeption("No supported type found!!");
        }
        if(messageDao.getType()==MessageType.list && messageDao.getAction().getSections() == null){
            throw new ValidateFailedExeption("Sections can't be null for type LIST message!!");
        }
        if(messageDao.getType()==MessageType.button && messageDao.getAction().getButtons() == null){
            throw new ValidateFailedExeption("Buttons can't be null for type BUTTON message!!");
        }

    }

    private void checkVideo(VideoMessageDao messageDao) throws ValidateFailedExeption {
        if (messageDao == null) {
            throw new ValidateFailedExeption("No Video Object Can be Found!!");
        }
        if (StringUtils.isEmpty(messageDao.getId()) && StringUtils.isEmpty(messageDao.getLink())) {
            throw new ValidateFailedExeption("No Video Id or Link Can be Found!!");
        }
        if (!StringUtils.isEmpty(messageDao.getId())) {
            messageDao.setLink(null);
        }

        if (!StringUtils.isEmpty(messageDao.getLink())) {
            messageDao.setId(null);
        }
    }

    private void checkLocation(LocationMessageDao location) throws ValidateFailedExeption {
        if (location == null) {
            throw new ValidateFailedExeption("No Location Object Can be Found!!");
        }
    }

    private void checkContact(ContactsMessageDao message) throws ValidateFailedExeption {
        if (message == null) {
            throw new ValidateFailedExeption("No Contact Object Can be Found!!");
        }

    }

    private void checkDocument(DocumentMessageDao messageDao) throws ValidateFailedExeption {
        if (messageDao == null) {
            throw new ValidateFailedExeption("No Document Object Can be Found!!");
        }
        if (StringUtils.isEmpty(messageDao.getId()) && StringUtils.isEmpty(messageDao.getLink())) {
            throw new ValidateFailedExeption("No Document Id or Link Can be Found!!");
        }
        if (!StringUtils.isEmpty(messageDao.getId())) {
            messageDao.setLink(null);
        }

        if (!StringUtils.isEmpty(messageDao.getLink())) {
            messageDao.setId(null);
        }
    }

    private void checkText(TextMessageDao message) throws ValidateFailedExeption {
        if (message == null) {
            throw new ValidateFailedExeption("No Text Object Can be Found!!");
        }
        if (StringUtils.isEmpty(message.getBody())) {
            throw new ValidateFailedExeption("No Text Found !!");
        }
    }

    private TemplateMessageDao checkTemplate(TemplateMessageDao message) throws ValidateFailedExeption {
        if (message == null) {
            throw new ValidateFailedExeption("No Template Object Can be Found!!");
        }
        if (StringUtils.isEmpty(message.getId())) {
            throw new ValidateFailedExeption("No Template Id found!!");
        }
        TemplateMessageDao templateMessageDao = templateMessageService.findById(message.getId());
        if (templateMessageDao == null) {

            throw new ValidateFailedExeption("Template Not Found!!");
        }
        if (!templateMessageDao.isStatus()) {
            throw new ValidateFailedExeption("Template Has Not Been Approved Yet!!");
        }
        HashMap<String, ValidateTemplate> hashMap = new HashMap<>();
        List<String> arr = null;

        for (ComponentsDao componentsDao : templateMessageDao.getComponents()) {
            arr = new ArrayList<>();

            for (ParametersDao parametersDao : componentsDao.getParameters()) {
                arr.add(parametersDao.getNameOfParams());
            }


            hashMap.put(componentsDao.getType(), new ValidateTemplate(String.join(",", arr), arr.size()));

        }

       /* if (templateMessageDao.getComponents().size() != message.getComponents().size()) {
            throw new ValidateFailedExeption("Number of Paramter Type Not Matching");
        }*/
        for (ComponentsDao componentsDao : message.getComponents()) {
            ValidateTemplate validateTemplate = hashMap.get(componentsDao.getType());
            if (componentsDao.getParameters().size() != validateTemplate.getSize()) {
                throw new ValidateFailedExeption("Required Parameter Cannot be Found  inside " + componentsDao.getType() + "!! Required are " + validateTemplate.getParams());
            }


        }
        templateMessageDao.setComponents(message.getComponents());
        return templateMessageDao;
    }

    private void checkImage(ImageMessageDao messageDao) throws ValidateFailedExeption {
        if (messageDao == null) {
            throw new ValidateFailedExeption("No Image Object Can be Found!!");
        }
        if (StringUtils.isEmpty(messageDao.getId()) && StringUtils.isEmpty(messageDao.getLink())) {
            throw new ValidateFailedExeption("No Image Id or Link Can be Found!!");
        }
        if (!StringUtils.isEmpty(messageDao.getId())) {
            messageDao.setLink(null);
        }
        if (!StringUtils.isEmpty(messageDao.getLink())) {
            messageDao.setId(null);
        }

    }

    static class ValidateTemplate {
        @Override
        public String toString() {
            return "ValidateTemplate{" +
                    "params='" + params + '\'' +
                    ", size=" + size +
                    '}';
        }

        public String getParams() {
            return params;
        }

        public void setParams(String params) {
            this.params = params;
        }

        public int getSize() {
            return size;
        }

        public void setSize(int size) {
            this.size = size;
        }

        public ValidateTemplate(String params, int size) {
            this.params = params;
            this.size = size;
        }

        private String params;
        private int size;
    }

}
