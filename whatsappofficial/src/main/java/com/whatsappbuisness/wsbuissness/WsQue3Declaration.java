package com.whatsappbuisness.wsbuissness;

import com.whatsappbuisness.wsbuissness.CombinePackadge.RabbitMqQue.QueName;
import org.hibernate.id.insert.Binder;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WsQue3Declaration {

    @Bean
    Queue SendWs_QUE3queue(){
        return new Queue(QueName.SendWs_QUE3.queName);
    }
    @Bean
    TopicExchange SendWs_QUE3topicExchange(){
        return new TopicExchange(QueName.SendWs_QUE3.topic);
    }
    @Bean
    Binding SendWs_QUE3binding(final Queue SendWs_QUE3queue, final TopicExchange SendWs_QUE3topicExchange){
        return BindingBuilder.bind(SendWs_QUE3queue).to(SendWs_QUE3topicExchange).with(QueName.SendWs_QUE3.routing);
    }
}
