package com.whatsappbuisness.wsbuissness.CombinePackadge.ApiService.TokenServiceWs;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/*
 Author=Supreet Singh
 Date= 10/03/21 5:19 PM
*/
@SpringBootTest
class TokenServiceTest {
    @Autowired
    TokenService tokenService;

    @Test
    void getToken() {
        try {
           String accessToken= tokenService.getToken(1);
            System.out.println("Access Token  "+accessToken);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
