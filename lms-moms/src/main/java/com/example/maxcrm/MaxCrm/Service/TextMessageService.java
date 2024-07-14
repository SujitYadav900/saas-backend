package com.example.maxcrm.MaxCrm.Service;


import com.example.maxcrm.MaxCrm.Dao.TextMessage;
import com.example.maxcrm.MaxCrm.Dao.TextMessageSimpleDao;
import com.example.maxcrm.MaxCrm.Dao.TextMessageTrans;

public interface TextMessageService {

    void sendTextMessage(TextMessage al) throws Exception;
    void sendTextMessageSingle(TextMessageSimpleDao textMessageSimpleDao) throws Exception;


}
