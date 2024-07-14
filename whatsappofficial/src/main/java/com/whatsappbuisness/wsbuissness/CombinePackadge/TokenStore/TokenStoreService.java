package com.whatsappbuisness.wsbuissness.CombinePackadge.TokenStore;


import com.whatsappbuisness.wsbuissness.CombinePackadge.Common.SessionRetrival.UsermasterDao;

public interface TokenStoreService {
    TokenStoreDao validate(String id);
    TokenStoreDao generate(UsermasterDao usermasterDao);
    TokenStoreDao getToken(UsermasterDao usermasterDao);


}
