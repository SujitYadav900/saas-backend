package com.whatsappbuisness.wsbuissness.CombinePackadge.RabbitMqQue;

import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QueNameServiceImpl implements QueNameService {


    @Override
    public String[] getName() {
        String[] queName={"Main_Que"};

        return queName;
    }
}
