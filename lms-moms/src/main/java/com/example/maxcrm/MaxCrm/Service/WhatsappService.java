package com.example.maxcrm.MaxCrm.Service;

import com.example.maxcrm.MaxCrm.Dao.TextMessageSimpleDao;
import com.example.maxcrm.MaxCrm.Dao.WhatsappDao;
import com.example.maxcrm.MaxCrm.Dao.WhatsappResponseDao;

import java.util.List;

public interface WhatsappService {
        List<WhatsappResponseDao> sendMessageWhatsapp(List<WhatsappDao> whatsappDao);


}
