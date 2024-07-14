package com.whatsappbuisness.wsbuissness.CombinePackadge.CreditService;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

/*
 Author=Supreet Singh
 Date= 11/03/21 12:57 PM
*/
@SpringBootTest
    class CreditServiceImplTest {
    @Autowired
    CreditService creditService;

        @Test
        void creditServer() {
        }

        @Test
        void validateCredit() {

              ResponseServiceDao responseServiceDao=  creditService.validateCredit(20210334,160);
                System.out.println(responseServiceDao.toString());
        }
    }
