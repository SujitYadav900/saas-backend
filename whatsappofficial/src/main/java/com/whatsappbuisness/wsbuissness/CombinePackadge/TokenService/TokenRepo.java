package com.whatsappbuisness.wsbuissness.CombinePackadge.TokenService;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface TokenRepo extends CrudRepository<TokenDao,Integer> {

    Optional<TokenDao> findAllByAccessToken(String accessToken);
    void deleteByAccountId(int accountId);
}
