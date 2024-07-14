package com.whatsappbuisness.wsbuissness.CombinePackadge.ClientSignUp;

import com.whatsappbuisness.wsbuissness.CombinePackadge.AccountMasterDao.TextSMSSendingDao;
import com.whatsappbuisness.wsbuissness.CombinePackadge.Common.SessionRetrival.SessionRetrievalService;
import com.whatsappbuisness.wsbuissness.CombinePackadge.Common.SessionRetrival.UsermasterDao;
import com.whatsappbuisness.wsbuissness.CombinePackadge.CreditService.ResponseServiceDao;
import com.whatsappbuisness.wsbuissness.CombinePackadge.Datetiming.DateTiming;
import com.whatsappbuisness.wsbuissness.CombinePackadge.Filtering.FilterDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clientsignup")
public class ClientSignUpController {

    @Autowired
    ClientSignUpService clientSignUpService;
    @Autowired
    SessionRetrievalService sessionRetrievalService;

    @PostMapping("/insert")
    public ClientSignUpDao insert(@RequestBody ClientSignUpDao clientSignUpDao){

        clientSignUpDao.setCreateAt(DateTiming.getDateRedable());
        clientSignUpDao.setDateFilter(DateTiming.getDateFilterDateLong());
        clientSignUpDao.setCreateBy("20004");
        clientSignUpDao.setClientType("DEMO");
        return clientSignUpService.insert(clientSignUpDao);
    }
    @PostMapping("/update")
    public ClientSignUpDao update(@RequestBody ClientSignUpDao clientSignUpDao, Authentication authentication){
        UsermasterDao usermasterDao = sessionRetrievalService.get(authentication);
        clientSignUpDao.setUpdateDate(DateTiming.getDateRedable());
        return clientSignUpService.update(clientSignUpDao);
    }

    @PostMapping("/sentextsms")
    public ResponseServiceDao sentTextSMS(@RequestBody TextSMSSendingDao textSMSSendingDao){
        return clientSignUpService.sendTextSms(textSMSSendingDao);
    }
    @PostMapping("/getclient")
    public List<ClientSignUpDao> getClient(@RequestBody ClientSignUpDao clientSignUpDao){
        return clientSignUpService.getClient(clientSignUpDao);
    }
    @PostMapping("/getclientbywhatsappnumber")
    public List<ClientSignUpDao> getClientByWhatsappNumber(@RequestParam("number") String number, Authentication authentication){
        UsermasterDao usermasterDao = sessionRetrievalService.get(authentication);
        return clientSignUpService.getClientByOnlyWhatsappNumber(number);
    }
    @GetMapping("/getbyid")
    public ClientSignUpDao getClient(Authentication authentication){
        UsermasterDao usermasterDao = sessionRetrievalService.get(authentication);
        return clientSignUpService.getById(String.valueOf(usermasterDao.getAccountId()));
    }
    @PostMapping("/get")
    public List<ClientSignUpDao> get(@RequestBody FilterDao filterDao, Authentication authentication){
        UsermasterDao usermasterDao = sessionRetrievalService.get(authentication);
        return clientSignUpService.get(filterDao);
    }


}
