package com.whatsappbuisness.wsbuissness;

import com.whatsappbuisness.wsbuissness.CombinePackadge.RabbitMqQue.QueName;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WsQue2Declaration {

    @Bean
    Queue SendWs_QUE2queue(){
        return new Queue(QueName.SendWs_QUE2.queName,Boolean.FALSE);
    }
    @Bean
    TopicExchange SendWs_QUE2topicExchange(){
        return new TopicExchange(QueName.SendWs_QUE2.topic);
    }

    @Bean
    Binding SendWs_QUE2binding(final Queue SendWs_QUE2queue, final TopicExchange SendWs_QUE2topicExchange ){
        return BindingBuilder.bind(SendWs_QUE2queue).to(SendWs_QUE2topicExchange).with(QueName.SendWs_QUE2.routing);
    }

}
