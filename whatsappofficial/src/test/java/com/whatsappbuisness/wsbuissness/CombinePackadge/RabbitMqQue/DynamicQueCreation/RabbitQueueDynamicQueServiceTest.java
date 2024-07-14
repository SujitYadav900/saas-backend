package com.whatsappbuisness.wsbuissness.CombinePackadge.RabbitMqQue.DynamicQueCreation;

import com.whatsappbuisness.wsbuissness.CombinePackadge.Messages.MessageDao;
import com.whatsappbuisness.wsbuissness.CombinePackadge.RabbitMqQue.QueName;
import com.whatsappbuisness.wsbuissness.CombinePackadge.RabbitMqQue.RabbitMqqueService;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static com.whatsappbuisness.wsbuissness.CombinePackadge.RabbitMqQue.QueName.Contact_Que_Api;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class RabbitQueueDynamicQueServiceTest {


}