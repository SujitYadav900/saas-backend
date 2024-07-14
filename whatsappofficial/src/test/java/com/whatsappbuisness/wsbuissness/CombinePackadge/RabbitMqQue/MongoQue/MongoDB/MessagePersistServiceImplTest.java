package com.whatsappbuisness.wsbuissness.CombinePackadge.RabbitMqQue.MongoQue.MongoDB;

import com.whatsappbuisness.wsbuissness.CombinePackadge.MessageReportDao.MessageReportService;
import com.whatsappbuisness.wsbuissness.CombinePackadge.Messages.MessageStatus;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

/*
 Author=Supreet Singh
 Date= 16/03/21 4:01 PM
*/
@SpringBootTest
    class MessagePersistServiceImplTest {
        @Autowired
        MessageReportService messageReportService;
        @Test
        void updateDlr() {
            messageReportService.updateDlr("58c3544d-6fea-4c83-9df2-af14355fc4a9", MessageStatus.DLV,20210334);
        }
    }
