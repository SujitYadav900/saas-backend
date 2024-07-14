package com.whatsappbuisness.wsbuissness;

import com.whatsappbuisness.wsbuissness.CombinePackadge.RabbitMqQue.QueName;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MainQueueAPIDeclaration {


    @Bean
    Queue Main_Que_Apiqueue() {
        return new Queue(QueName.Main_Que_Api.queName, Boolean.FALSE);
    }

    @Bean
    TopicExchange Main_Que_ApitopicExchange() {
        return new TopicExchange(QueName.Main_Que_Api.topic);
    }

    @Bean
    Binding Main_QueApibinding(final Queue Main_Que_Apiqueue, final TopicExchange Main_Que_ApitopicExchange) {
        return BindingBuilder.bind(Main_Que_Apiqueue).to(Main_Que_ApitopicExchange).with(QueName.Main_Que_Api.routing);
    }
}
