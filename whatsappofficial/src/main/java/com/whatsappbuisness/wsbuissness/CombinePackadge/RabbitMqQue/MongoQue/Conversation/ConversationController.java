package com.whatsappbuisness.wsbuissness.CombinePackadge.RabbitMqQue.MongoQue.Conversation;

import com.whatsappbuisness.wsbuissness.CombinePackadge.Common.SessionRetrival.SessionRetrievalService;
import com.whatsappbuisness.wsbuissness.CombinePackadge.Common.SessionRetrival.UsermasterDao;
import com.whatsappbuisness.wsbuissness.CombinePackadge.PaginationDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/*
 Author=Supreet Singh
 Date= 19/03/21 10:59 AM
*/
@RestController
@RequestMapping("/conversation")
public class ConversationController {
    @Autowired
    ConversationService conversationService;
    @Autowired
    SessionRetrievalService sessionRetrievalService;

    @PostMapping("/")
    public PaginationDao<ConversationDao> getConversation(@RequestBody FilterDao filterDao, Authentication authentication) {
        UsermasterDao usermasterDao = sessionRetrievalService.get(authentication);
        filterDao.setAccountId(usermasterDao.getAccountId());
        return conversationService.getAll(filterDao);
    }

    @GetMapping("/updatests")
    public void updateSts(@RequestParam("id") String id, Authentication authentication) {
        UsermasterDao usermasterDao = sessionRetrievalService.get(authentication);
        conversationService.unread(id, usermasterDao.getAccountId());
    }

    @GetMapping("/savecontact")
    public void saveContact(@RequestParam("id") String id, @RequestParam("contact") String contact, Authentication authentication) {
        UsermasterDao usermasterDao = sessionRetrievalService.get(authentication);
        conversationService.saveContact(contact, id, usermasterDao.getAccountId());
    }
    @GetMapping("/getunreadconversation")
    public List<ConversationDao> getUnread( Authentication authentication)
    {
        UsermasterDao usermasterDao = sessionRetrievalService.get(authentication);
       return conversationService.getOnlyUnread(usermasterDao.getAccountId());
    }

}
