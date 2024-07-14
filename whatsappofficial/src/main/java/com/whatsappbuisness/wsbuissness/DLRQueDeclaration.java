package com.whatsappbuisness.wsbuissness;


import com.whatsappbuisness.wsbuissness.CombinePackadge.RabbitMqQue.QueName;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DLRQueDeclaration {
 

    @Bean
    Queue DLR_Quequeue() {
        // Creating a queue.
        return new Queue(QueName.DLR_Que.queName, Boolean.FALSE);
    }
 
    @Bean
    TopicExchange DLR_QuetopicExchange() {
        // Creating a topic exchange.
        return new TopicExchange(QueName.DLR_Que.topic);
    }
 
    @Bean
    Binding DLR_Quebinding(final Queue DLR_Quequeue, final TopicExchange DLR_QuetopicExchange) {
        // Binding the queue to the topic with a routing key.
        return BindingBuilder.bind(DLR_Quequeue).to(DLR_QuetopicExchange).with(QueName.DLR_Que.routing);
    }
}
