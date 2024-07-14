package com.whatsappbuisness.wsbuissness.CombinePackadge.SubscriptionPackageTransaction;

import com.whatsappbuisness.wsbuissness.CombinePackadge.Common.SessionRetrival.SessionRetrievalService;
import com.whatsappbuisness.wsbuissness.CombinePackadge.Common.SessionRetrival.UsermasterDao;
import com.whatsappbuisness.wsbuissness.CombinePackadge.Datetiming.DateTiming;
import com.whatsappbuisness.wsbuissness.CombinePackadge.Filtering.FilterDao;
import com.whatsappbuisness.wsbuissness.CombinePackadge.TokenStore.TokenGenerationErrorHandling;
import com.whatsappbuisness.wsbuissness.CombinePackadge.TokenStore.TokenStoreDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/api/subscriptionpackagetransaction")
public class SubscriptionPackageTransactionController {

    @Autowired
    SubscriptionPackageTransactionService subscriptionPackageTransactionService;
    @Autowired
    SessionRetrievalService sessionRetrievalService;

    @PostMapping("/")
    SubscriptionPackageTransactionDao insert(@RequestBody SubscriptionPackageTransactionDao subscriptionPackageTransactionDao, HttpServletRequest httpServletRequest){
        try {
            TokenStoreDao tokenStoreDao = sessionRetrievalService.validate(httpServletRequest);
            subscriptionPackageTransactionDao.setAccountId(tokenStoreDao.getAccountId());
            subscriptionPackageTransactionDao.setCreateDate(DateTiming.getDateRedable());
            subscriptionPackageTransactionDao.setCreateDateFilter(DateTiming.getDateFilterDateLong());
            subscriptionPackageTransactionDao.setStatus(true);
            return subscriptionPackageTransactionService.insert(subscriptionPackageTransactionDao);
        } catch (TokenGenerationErrorHandling e) {
            throw new RuntimeException(e);
        }
    }
    @PostMapping("/get")
    List<SubscriptionPackageTransactionDao> get(@RequestBody FilterDao filterDao, Authentication authentication){
            UsermasterDao usermasterDao = sessionRetrievalService.get(authentication);
            return subscriptionPackageTransactionService.get(filterDao);
    }
}
