package com.whatsappbuisness.wsbuissness.CombinePackadge.catalog;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.whatsappbuisness.wsbuissness.CombinePackadge.PaginationDao;
import com.whatsappbuisness.wsbuissness.CombinePackadge.Subscription.SubscriptionDao;
import com.whatsappbuisness.wsbuissness.CombinePackadge.TokenStore.TokenGenerationErrorHandling;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpServletRequest;

public interface CatalogMessageService{
    ResponseEntity<?> save(CatalogMessageDao catalogMessageDao, HttpServletRequest httpServletRequest) throws TokenGenerationErrorHandling;

    ResponseEntity<?> saveFromPanel(CatalogMessageDao catalogMessageDao, Authentication authentication);

    ResponseEntity<?> update(CatalogMessageDao catalogMessageDao, Authentication authentication);

    ResponseEntity<?> deleteById(String id);
     void sendCatalogueMessage(CatalogMessageDao catalogMessageDao, SubscriptionDao subscriptionDao) throws JsonProcessingException;

    PaginationDao<?> get(Authentication accountId, int offset, int limit);

    CatalogMessageDao getById(String id) throws Exception;

    String getVariantsTitleByCatalogIdAndRetailerProductId(String catalogId, String productRetailerId);
}
