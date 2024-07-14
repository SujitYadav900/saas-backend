package com.whatsappbuisness.wsbuissness;


import com.whatsappbuisness.wsbuissness.CombinePackadge.RabbitMqQue.QueName;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WsQueDeclaration {
 

    @Bean
    Queue SendWs_QUEqueue() {
        // Creating a queue.
        return new Queue(QueName.SendWs_QUE.queName, Boolean.FALSE);
    }
 
    @Bean
    TopicExchange SendWs_QUEtopicExchange() {
        // Creating a topic exchange.
        return new TopicExchange(QueName.SendWs_QUE.topic);
    }
 
    @Bean
    Binding SendWs_QUEbinding(final Queue SendWs_QUEqueue, final TopicExchange SendWs_QUEtopicExchange) {
        // Binding the queue to the topic with a routing key.
        return BindingBuilder.bind(SendWs_QUEqueue).to(SendWs_QUEtopicExchange).with(QueName.SendWs_QUE.routing);
    }
}
