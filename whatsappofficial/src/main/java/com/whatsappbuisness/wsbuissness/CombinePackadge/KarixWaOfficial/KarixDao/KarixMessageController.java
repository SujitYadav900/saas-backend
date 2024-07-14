package com.whatsappbuisness.wsbuissness.CombinePackadge.KarixWaOfficial.KarixDao;


import com.whatsappbuisness.wsbuissness.CombinePackadge.MessageEntry.ValidateFailedExeption;
import com.whatsappbuisness.wsbuissness.CombinePackadge.Messages.MessageDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
/*
 Author=Ritu Redhu
 Date= 06/01/23 10:00 AM
*/
@RestController
@RequestMapping("/api/karixmessage")
public class KarixMessageController {

    @Autowired
    KarixMessageService karixMessageService;

    @PostMapping("/")
    MessageDao sendMessageKarix(@RequestBody KarixMessageDao karixMessageDao){
        MessageDao messageDao = new MessageDao();
        return karixMessageService.sendMessageKarix(karixMessageDao,messageDao);
    }


    /*     Will Prepare Message
    And REmove Unneccessary Things*/
//    List<KarixMessageDao> prepareMessage(List<KarixMessageDao> karixMessageDaos) throws ValidateFailedExeption {
//
//        }



}
