package com.whatsappbuisness.wsbuissness.CombinePackadge.Template;

import com.whatsappbuisness.wsbuissness.CombinePackadge.Common.SessionRetrival.SessionRetrievalService;
import com.whatsappbuisness.wsbuissness.CombinePackadge.Common.SessionRetrival.UsermasterDao;
import com.whatsappbuisness.wsbuissness.CombinePackadge.CounterGeneration.CounterGenerationService;
import com.whatsappbuisness.wsbuissness.CombinePackadge.CreditService.CreditService;
import com.whatsappbuisness.wsbuissness.CombinePackadge.Messages.TemplateMessageDao.TemplateText.TemplateMessageDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/*
 Author=Supreet Singh
 Date= 09/03/21 10:53 AM
*/
@RestController
@RequestMapping("/template")
public class TemplateMessageController {
    @Autowired
    TemplateMessageService templateMessageService;
    @Autowired
    SessionRetrievalService retrievalService;
    @Autowired
    CounterGenerationService counterGenerationService;
    @Autowired
    CreditService creditService;

    @GetMapping("/getbyaccountid")
    public List<TemplateMessageDao> getByAccountId(Authentication authentication) {
        UsermasterDao usermasterDao = retrievalService.get(authentication);
        return templateMessageService.findByAccountAll(usermasterDao.getAccountId());
    }
    @GetMapping("/getbyaccountactive")
    public List<TemplateMessageDao> getByAccountIdActive(Authentication authentication) {
        UsermasterDao usermasterDao = retrievalService.get(authentication);
        return templateMessageService.findByAccountAllActive(usermasterDao.getAccountId());
    }


    @PostMapping("/insert")
    public TemplateMessageDao insert(@RequestBody TemplateMessageDao templateMessageDao,Authentication authentication) {

        templateMessageDao.setId(counterGenerationService.generateUid());
        UsermasterDao usermasterDao = retrievalService.get(authentication);

        templateMessageDao.setAccountId(usermasterDao.getAccountId());
        return templateMessageService.insert(templateMessageDao);
    }

    @PostMapping("/update")
    public TemplateMessageDao update(@RequestBody TemplateMessageDao templateMessageDao,Authentication authentication) {
        UsermasterDao usermasterDao = retrievalService.get(authentication);
        templateMessageDao.setAccountId(usermasterDao.getAccountId());
        return templateMessageService.update(templateMessageDao);
    }


}
