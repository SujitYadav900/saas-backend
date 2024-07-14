package com.whatsappbuisness.wsbuissness.CombinePackadge.TokenStore;

import org.springframework.data.repository.CrudRepository;

public interface TokenStoreRepo extends CrudRepository<TokenStoreDao, String> {
    TokenStoreDao findAllByUserId(long userId);

}
