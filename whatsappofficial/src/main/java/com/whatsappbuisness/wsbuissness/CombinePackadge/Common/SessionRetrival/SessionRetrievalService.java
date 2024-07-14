package com.whatsappbuisness.wsbuissness.CombinePackadge.Common.SessionRetrival;
/*
 Author=Supreet Singh
 Date= 11/03/21 3:13 PM
*/

import com.whatsappbuisness.wsbuissness.CombinePackadge.TokenStore.TokenGenerationErrorHandling;
import com.whatsappbuisness.wsbuissness.CombinePackadge.TokenStore.TokenStoreDao;
import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public interface SessionRetrievalService {
    UsermasterDao get(Authentication authentication) ;
    TokenStoreDao validate(HttpServletRequest httpServletRequest) throws TokenGenerationErrorHandling;

}
