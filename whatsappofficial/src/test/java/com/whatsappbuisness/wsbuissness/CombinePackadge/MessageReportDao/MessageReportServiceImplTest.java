package com.whatsappbuisness.wsbuissness.CombinePackadge.MessageReportDao;

import com.whatsappbuisness.wsbuissness.CombinePackadge.Messages.MessageStatus;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

/*
 Author=Supreet Singh
 Date= 10/03/21 10:32 PM
*/
@SpringBootTest
    class MessageReportServiceImplTest {
    @Autowired
    MessageReportService messageReportService;

        @Test
        void removeLastChars() {
            messageReportService.updateDlr("gBEGkZmZQgWUAgn8pn6Fnrn4IkM", MessageStatus.SENT,1);
        }

        @Test
        void pushToCredit() {
        }
    }
