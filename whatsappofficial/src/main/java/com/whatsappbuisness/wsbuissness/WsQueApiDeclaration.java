package com.whatsappbuisness.wsbuissness;


import com.whatsappbuisness.wsbuissness.CombinePackadge.RabbitMqQue.QueName;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WsQueApiDeclaration {
 

    @Bean
    Queue SendWs_QUE_APIqueue() {
        // Creating a queue.
        return new Queue(QueName.SendWs_QUE_API.queName, Boolean.FALSE);
    }
 
    @Bean
    TopicExchange SendWs_QUE_APItopicExchange() {
        // Creating a topic exchange.
        return new TopicExchange(QueName.SendWs_QUE_API.topic);
    }
 
    @Bean
    Binding SendWs_QUE_APIbinding(final Queue SendWs_QUE_APIqueue, final TopicExchange SendWs_QUE_APItopicExchange) {
        // Binding the queue to the topic with a routing key.
        return BindingBuilder.bind(SendWs_QUE_APIqueue).to(SendWs_QUE_APItopicExchange).with(QueName.SendWs_QUE_API.routing);
    }
}
