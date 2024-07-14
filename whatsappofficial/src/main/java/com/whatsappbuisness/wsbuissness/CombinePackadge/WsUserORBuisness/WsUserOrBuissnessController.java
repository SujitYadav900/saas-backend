package com.whatsappbuisness.wsbuissness.CombinePackadge.WsUserORBuisness;

import com.whatsappbuisness.wsbuissness.CombinePackadge.Common.SessionRetrival.SessionRetrievalService;
import com.whatsappbuisness.wsbuissness.CombinePackadge.Common.SessionRetrival.UsermasterDao;
import com.whatsappbuisness.wsbuissness.CombinePackadge.Messages.ChatSide;
import com.whatsappbuisness.wsbuissness.CombinePackadge.Utility.CommonClassReturnWithStatus;
import com.whatsappbuisness.wsbuissness.CombinePackadge.Utility.GroupQueryResultDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/userorbsns")
public class WsUserOrBuissnessController {
    @Autowired
    SessionRetrievalService sessionRetrievalService;

    @Autowired
    UserOrBuisnessService userOrBuisnessService;

    @GetMapping("/groupQuery")
    public List<GroupQueryResultDao<String, String,String,String,String>> groupQuery(@RequestParam("startdate") int startdate, @RequestParam("enddate") int enddate, @RequestParam("type") TypeOfReport typeOfReport,Authentication authentication ) {
        UsermasterDao usermasterDao = sessionRetrievalService.get(authentication);
        return userOrBuisnessService.groupByQuery(usermasterDao.getAccountId(), startdate, enddate, typeOfReport);
    }

    @GetMapping("/gettrans")
    public List<UserOrBuisnessIntiatedDao> getTrans(Authentication authentication, @RequestParam("startdate") int startdate, @RequestParam("enddate") int enddate) {
        UsermasterDao usermasterDao = sessionRetrievalService.get(authentication);
        return userOrBuisnessService.getTransaction(usermasterDao.getAccountId(), startdate, enddate);
    }


//        @GetMapping("/getSession")
//   public UserOrBuisnessIntiatedDao getSession(@RequestParam String phn,@RequestParam String code,@RequestParam long accountId){
//
//        return userOrBuisnessService.getSessionByPhoneNumberAndCodeAndAccountId(phn,code,accountId);
//
//    }
    @GetMapping("/getSession")
   public UserOrBuisnessIntiatedDao getSessionByCategory(@RequestParam String phn,@RequestParam String code,@RequestParam long accountId){
        return userOrBuisnessService.getSessionByPhoneNumberAndCodeAndAccountIdAndCategory(phn,code,accountId,"USER");
    }

}

